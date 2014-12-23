package shift.sextiarysector.recipe;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class RecipesMagicFuel {

	public static void addRecipes(RecipeSimpleFuel recipe)
    {
		recipe.add(new ItemStack(Items.ender_pearl,1), 1000);
		recipe.add("redBluestone", 400);
		recipe.add("dustBluestone", 400);
		recipe.add("dustYellowstone", 400);
    }

}
