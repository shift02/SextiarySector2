package shift.sextiarysector.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;
import shift.sextiarysector.SSItems;

public class RecipesArmor {

    public static void addRecipes(CraftingManager p_77608_1_) {

        String[][] recipePatterns = new String[][] { { "XXX", "X X" }, { "X X", "XXX", "XXX" }, { "XXX", "X X", "X X" }, { "X X", "X X" } };
        Object[] materialItems = new String[] {
                "ingotCopper",
                "ingotNinja"
        };
        ItemStack[][] recipeItems = new ItemStack[][] {
                { new ItemStack(SSItems.copperHelmet), new ItemStack(SSItems.copperChestplate), new ItemStack(SSItems.copperLeggings), new ItemStack(SSItems.copperBoots) },
                { new ItemStack(SSItems.ninjaHelmet), new ItemStack(SSItems.ninjaChestplate), new ItemStack(SSItems.ninjaLeggings), new ItemStack(SSItems.ninjaBoots) }
        };

        for (int i = 0; i < materialItems.length; ++i) {
            Object object = materialItems[i];

            for (int j = 0; j < recipeItems[i].length; ++j) {
                ItemStack item = recipeItems[i][j];
                p_77608_1_.getRecipeList().add(new ShapedOreRecipe(item, new Object[] { recipePatterns[j], 'X', object }));
            }

        }

    }

}
