package shift.sextiarysector.recipe;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import shift.sextiarysector.SSFluids;
import shift.sextiarysector.SSItems;

public class RecipesFoodSmokers {

	public static void addRecipes(RecipeSimpleFluid recipe)
    {

		recipe.add(new ItemStack(Items.water_bucket,1),new ItemStack(SSItems.salt, 1), new FluidStack(SSFluids.steam, 500));

    }

}
