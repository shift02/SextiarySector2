package shift.sextiarysector.nei;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.oredict.ShapedOreRecipe;
import shift.sextiarysector.gui.GuiLargeFurnace;
import shift.sextiarysector.recipe.FurnaceCraftingManager;
import codechicken.core.ReflectionManager;
import codechicken.nei.ItemList;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.api.DefaultOverlayRenderer;
import codechicken.nei.api.IOverlayHandler;
import codechicken.nei.api.IRecipeOverlayRenderer;
import codechicken.nei.api.IStackPositioner;
import codechicken.nei.recipe.RecipeInfo;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class FurnaceShapedRecipeHandler extends TemplateRecipeHandler {

    public class CachedShapedRecipe extends CachedRecipe {
        public ArrayList<PositionedStack> ingredients;
        public PositionedStack result;

        public CachedShapedRecipe(int width, int height, Object[] items, ItemStack out) {
            //ここも怪しい
            result = new PositionedStack(out, 130, 24);
            ingredients = new ArrayList<PositionedStack>();
            setIngredients(width, height, items);
        }

        public CachedShapedRecipe(ShapedRecipes recipe) {
            this(recipe.recipeWidth, recipe.recipeHeight, recipe.recipeItems, recipe.getRecipeOutput());
        }

        /**
         * @param width
         * @param height
         * @param items an ItemStack[] or ItemStack[][]
         */
        public void setIngredients(int width, int height, Object[] items) {
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    if (items[y * width + x] == null)
                        continue;
                    //ここが怪しい
                    PositionedStack stack = new PositionedStack(items[y * width + x], 36 + x * 18, 6 + y * 18, false);
                    stack.setMaxSize(1);
                    ingredients.add(stack);
                }
            }
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

        public void computeVisuals() {
            for (PositionedStack p : ingredients)
                p.generatePermutations();

            result.generatePermutations();
        }
    }

    public static class FuelPair {
        public FuelPair(ItemStack ingred, int burnTime) {
            //ここが怪しい
            this.stack = new PositionedStack(ingred, 10, 35, false);
            this.burnTime = burnTime;
        }

        public PositionedStack stack;
        public int burnTime;
    }

    public static ArrayList<FuelPair> afuels;
    public static TreeSet<Integer> efuels;

    @Override
    public void loadTransferRects() {
        transferRects.add(new RecipeTransferRect(new Rectangle(9, 16, 18, 18), "fuel"));
        transferRects.add(new RecipeTransferRect(new Rectangle(96, 30, 24, 18), "furnaceCrafting"));
    }

    @Override
    public Class<? extends GuiContainer> getGuiClass() {
        return GuiLargeFurnace.class;
    }

    @Override
    public String getRecipeName() {
        return I18n.format("nei.ss.furnace.shaped");
    }

    @Override
    public TemplateRecipeHandler newInstance() {
        if (afuels == null) findFuels();
        return super.newInstance();
    }

    @Override
    public void loadCraftingRecipes(String outputId, Object... results) {
        if (outputId.equals("furnaceCrafting") && getClass() == FurnaceShapedRecipeHandler.class) {
            List<IRecipe> allrecipes = FurnaceCraftingManager.getInstance().getRecipeList();
            for (IRecipe irecipe : allrecipes) {
                CachedShapedRecipe recipe = null;
                if (irecipe instanceof ShapedRecipes)
                    recipe = new CachedShapedRecipe((ShapedRecipes) irecipe);
                else if (irecipe instanceof ShapedOreRecipe)
                    recipe = forgeShapedRecipe((ShapedOreRecipe) irecipe);

                if (recipe == null)
                    continue;

                recipe.computeVisuals();
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
                CachedShapedRecipe recipe = null;
                if (irecipe instanceof ShapedRecipes)
                    recipe = new CachedShapedRecipe((ShapedRecipes) irecipe);
                else if (irecipe instanceof ShapedOreRecipe)
                    recipe = forgeShapedRecipe((ShapedOreRecipe) irecipe);

                if (recipe == null)
                    continue;

                recipe.computeVisuals();
                arecipes.add(recipe);
            }
        }
    }

    @Override
    public void loadUsageRecipes(ItemStack ingredient) {
        List<IRecipe> allrecipes = FurnaceCraftingManager.getInstance().getRecipeList();
        for (IRecipe irecipe : allrecipes) {
            CachedShapedRecipe recipe = null;
            if (irecipe instanceof ShapedRecipes)
                recipe = new CachedShapedRecipe((ShapedRecipes) irecipe);
            else if (irecipe instanceof ShapedOreRecipe)
                recipe = forgeShapedRecipe((ShapedOreRecipe) irecipe);

            if (recipe == null || !recipe.contains(recipe.ingredients, ingredient))
                continue;

            recipe.computeVisuals();
            if (recipe.contains(recipe.ingredients, ingredient)) {
                recipe.setIngredientPermutation(recipe.ingredients, ingredient);
                arecipes.add(recipe);
            }
        }
    }

    @Override
    public void loadUsageRecipes(String inputId, Object... ingredients) {
        if (inputId.equals("fuel") && getClass() == FurnaceShapedRecipeHandler.class) //don't want subclasses getting a hold of this
        {
            loadCraftingRecipes("furnaceCrafting");
        } else {
            super.loadUsageRecipes(inputId, ingredients);
        }
    }

    public CachedShapedRecipe forgeShapedRecipe(ShapedOreRecipe recipe) {
        int width;
        int height;
        Object[] items;
        try {
            width = ReflectionManager.getField(ShapedOreRecipe.class, Integer.class, recipe, 4);
            height = ReflectionManager.getField(ShapedOreRecipe.class, Integer.class, recipe, 5);
            items = ReflectionManager.getField(ShapedOreRecipe.class, Object[].class, recipe, 3);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        for (int i = 0; i < items.length; i++) {
            if (items[i] instanceof List && ((List<?>) items[i]).isEmpty()) //ore handler, no ores
                return null;
        }

        return new CachedShapedRecipe(width, height, items, recipe.getRecipeOutput());
    }

    @Override
    public String getGuiTexture() {
        return "sextiarysector:textures/guis/crafting_furnace_nei.png";
    }

    @Override
    public boolean hasOverlay(GuiContainer gui, Container container, int recipe) {
        return super.hasOverlay(gui, container, recipe) ||
                isRecipe2x2(recipe) && RecipeInfo.hasDefaultOverlay(gui, "crafting2x2");
    }

    @Override
    public IRecipeOverlayRenderer getOverlayRenderer(GuiContainer gui, int recipe) {
        IRecipeOverlayRenderer renderer = super.getOverlayRenderer(gui, recipe);
        if (renderer != null)
            return renderer;

        IStackPositioner positioner = RecipeInfo.getStackPositioner(gui, "crafting2x2");
        if (positioner == null)
            return null;
        return new DefaultOverlayRenderer(getIngredientStacks(recipe), positioner);
    }

    @Override
    public IOverlayHandler getOverlayHandler(GuiContainer gui, int recipe) {
        IOverlayHandler handler = super.getOverlayHandler(gui, recipe);
        if (handler != null)
            return handler;

        return RecipeInfo.getOverlayHandler(gui, "crafting2x2");
    }

    public boolean isRecipe2x2(int recipe) {
        for (PositionedStack stack : getIngredientStacks(recipe))
            if (stack.relx > 43 || stack.rely > 24)
                return false;

        return true;
    }

    @Override
    public void drawExtras(int recipe) {
        drawProgressBar(10, 18, 176, 0, 14, 14, 48, 7);
        drawProgressBar(95, 23, 176, 14, 24, 16, 48, 0);
    }

    private static Set<Item> excludedFuels() {
        Set<Item> efuels = new HashSet<Item>();
        efuels.add(Item.getItemFromBlock(Blocks.brown_mushroom));
        efuels.add(Item.getItemFromBlock(Blocks.red_mushroom));
        efuels.add(Item.getItemFromBlock(Blocks.standing_sign));
        efuels.add(Item.getItemFromBlock(Blocks.wall_sign));
        efuels.add(Item.getItemFromBlock(Blocks.wooden_door));
        efuels.add(Item.getItemFromBlock(Blocks.trapped_chest));
        return efuels;
    }

    private static void findFuels() {
        afuels = new ArrayList<FuelPair>();
        Set<Item> efuels = excludedFuels();
        for (ItemStack item : ItemList.items)
            if (!efuels.contains(item.getItem())) {
                int burnTime = TileEntityFurnace.getItemBurnTime(item);
                if (burnTime > 0)
                    afuels.add(new FuelPair(item.copy(), burnTime));
            }
    }

    @Override
    public String getOverlayIdentifier() {
        return "furnaceCrafting";
    }

    static {
        excludedFuels();
    }

}
