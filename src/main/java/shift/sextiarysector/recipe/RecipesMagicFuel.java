package shift.sextiarysector.recipe;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class RecipesMagicFuel {

	public static void addRecipes(RecipeSimpleFuel recipe)
    {
		recipe.add(new ItemStack(Items.ender_pearl,1), 1000);
    }

}
