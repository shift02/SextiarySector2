package shift.sextiarysector.recipe;

import net.minecraft.item.ItemStack;
import shift.sextiarysector.SSBlocks;

public class RecipesRollingMachine {

	public static void addRecipes(RecipeSimpleMachine recipe)
    {

		recipe.add("ingotIron",new ItemStack(SSBlocks.ironPlate, 1));
		recipe.add("ingotGold",new ItemStack(SSBlocks.goldPlate, 1));

    }

}
