package shift.sextiarysector.recipe;

import net.minecraftforge.fluids.FluidStack;
import shift.sextiarysector.SSFluids;

public class RecipesManaSqueezer {

	public static void addRecipes(RecipeSimpleFluid recipe)
	{

		recipe.add("logWood", null, new FluidStack(SSFluids.mana, 200));

	}

}
