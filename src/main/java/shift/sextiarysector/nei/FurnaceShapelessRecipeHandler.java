package shift.sextiarysector.nei;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import shift.sextiarysector.recipe.FurnaceCraftingManager;
import codechicken.core.ReflectionManager;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;

public class FurnaceShapelessRecipeHandler extends FurnaceShapedRecipeHandler {

    public int[][] stackorder = new int[][] {
            { 0, 0 },
            { 1, 0 },
            { 0, 1 },
            { 1, 1 },
            { 0, 2 },
            { 1, 2 },
            { 2, 0 },
            { 2, 1 },
            { 2, 2 } };

    public class CachedShapelessRecipe extends CachedRecipe {
        public CachedShapelessRecipe() {
            ingredients = new ArrayList<PositionedStack>();
        }

        public CachedShapelessRecipe(ItemStack output) {
            this();
            setResult(output);
        }

        public CachedShapelessRecipe(ShapelessRecipes recipe) {
            this(recipe.getRecipeOutput());
            setIngredients(recipe);
        }

        public CachedShapelessRecipe(Object[] input, ItemStack output) {
            this(Arrays.asList(input), output);
        }

        public CachedShapelessRecipe(List<?> input, ItemStack output) {
            this(output);
            setIngredients(input);
        }

        public void setIngredients(List<?> items) {
            ingredients.clear();
            for (int ingred = 0; ingred < items.size(); ingred++) {
                //怪しい
                PositionedStack stack = new PositionedStack(items.get(ingred), 36 + stackorder[ingred][0] * 18, 6 + stackorder[ingred][1] * 18);
                stack.setMaxSize(1);
                ingredients.add(stack);
            }
        }

        public void setIngredients(ShapelessRecipes recipe) {
            List<ItemStack> items;
            try {
                items = ReflectionManager.getField(ShapelessRecipes.class, List.class, recipe, 1);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }

            setIngredients(items);
        }

        public void setResult(ItemStack output) {
            //怪しい
            result = new PositionedStack(output, 130, 24);
        }

        @Override
        public List<PositionedStack> getIngredients() {
            return getCycledIngredients(cycleticks / 20, ingredients);
        }

        @Override
        public PositionedStack getResult() {
            return result;
        }

        @Override
        public PositionedStack getOtherStack() {
            return afuels.get((cycleticks / 48) % afuels.size()).stack;
        }

        public ArrayList<PositionedStack> ingredients;
        public PositionedStack result;
    }

    @Override
    public String getRecipeName() {
        return I18n.format("nei.ss.furnace.shapeless");
    }

    @Override
    public void loadCraftingRecipes(String outputId, Object... results) {
        if (outputId.equals("furnaceCrafting") && getClass() == FurnaceShapelessRecipeHandler.class) {
            List<IRecipe> allrecipes = FurnaceCraftingManager.getInstance().getRecipeList();
            for (IRecipe irecipe : allrecipes) {
                CachedShapelessRecipe recipe = null;
                if (irecipe instanceof ShapelessRecipes)
                    recipe = new CachedShapelessRecipe((ShapelessRecipes) irecipe);
                else if (irecipe instanceof ShapelessOreRecipe)
                    recipe = forgeShapelessRecipe((ShapelessOreRecipe) irecipe);

                if (recipe == null)
                    continue;

                arecipes.add(recipe);
            }
        } else {
            super.loadCraftingRecipes(outputId, results);
        }
    }

    @Override
    public void loadCraftingRecipes(ItemStack result) {
        List<IRecipe> allrecipes = FurnaceCraftingManager.getInstance().getRecipeList();
        for (IRecipe irecipe : allrecipes) {
            if (NEIServerUtils.areStacksSameTypeCrafting(irecipe.getRecipeOutput(), result)) {
                CachedShapelessRecipe recipe = null;
                if (irecipe instanceof ShapelessRecipes)
                    recipe = new CachedShapelessRecipe((ShapelessRecipes) irecipe);
                else if (irecipe instanceof ShapelessOreRecipe)
                    recipe = forgeShapelessRecipe((ShapelessOreRecipe) irecipe);

                if (recipe == null)
                    continue;

                arecipes.add(recipe);
            }
        }
    }

    @Override
    public void loadUsageRecipes(ItemStack ingredient) {
        List<IRecipe> allrecipes = FurnaceCraftingManager.getInstance().getRecipeList();
        for (IRecipe irecipe : allrecipes) {
            CachedShapelessRecipe recipe = null;
            if (irecipe instanceof ShapelessRecipes)
                recipe = new CachedShapelessRecipe((ShapelessRecipes) irecipe);
            else if (irecipe instanceof ShapelessOreRecipe)
                recipe = forgeShapelessRecipe((ShapelessOreRecipe) irecipe);

            if (recipe == null)
                continue;

            if (recipe.contains(recipe.ingredients, ingredient)) {
                recipe.setIngredientPermutation(recipe.ingredients, ingredient);
                arecipes.add(recipe);
            }
        }
    }

    public CachedShapelessRecipe forgeShapelessRecipe(ShapelessOreRecipe recipe) {
        ArrayList<?> items;
        try {
            items = ReflectionManager.getField(ShapelessOreRecipe.class, ArrayList.class, recipe, 1);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) instanceof List && ((List<?>) items.get(i)).isEmpty()) //ore handler, no ores
                return null;
        }

        return new CachedShapelessRecipe(items, recipe.getRecipeOutput());
    }

    @Override
    public boolean isRecipe2x2(int recipe) {
        return getIngredientStacks(recipe).size() <= 4;
    }

}
