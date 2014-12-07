package shift.sextiarysector.recipe;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import shift.sextiarysector.SSBlocks;

public class RecipesSawmill {

	public static void addRecipes(RecipeSimpleMachine recipe)
    {

		//
		recipe.add(new ItemStack(Blocks.planks,1),new ItemStack(SSBlocks.woodPlate, 4));


    }

}
