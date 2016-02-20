package shift.sextiarysector.recipe;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import shift.sextiarysector.SSItems;

public class RecipesMagicFuel {

    public static void addRecipes(RecipeSimpleFuel recipe) {
        recipe.add(new ItemStack(Items.ender_pearl, 1), 1000);
        recipe.add("dustRedstone", 400);
        recipe.add("dustBluestone", 400);
        recipe.add("dustYellowstone", 400);
        recipe.add(new ItemStack(SSItems.magicDust, 1), 1200);
    }

}
