package shift.sextiarysector.recipe;

import net.minecraft.item.ItemStack;
import shift.sextiarysector.SSItems;

public class RecipesLoom {

	public static void addRecipes(RecipeSimpleMachine recipe)
    {

		//風車
		recipe.add(new ItemStack(SSItems.stringBobbin,1),new ItemStack(SSItems.cloth, 1));


    }

}
