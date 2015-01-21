package shift.sextiarysector.recipe;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import shift.sextiarysector.SSFluids;

public class RecipesFluidFurnace {

	public static void addRecipes(RecipeSimpleFluid recipe)
    {

		//recipe.add(new ItemStack(Blocks.netherrack,1),null,  new FluidStack(SSFluids.drinkingWater, 50));
		recipe.add(new ItemStack(Items.water_bucket,1),new ItemStack(Items.stick, 1), new FluidStack(SSFluids.drinkingWater, 1000));

		recipe.add(new ItemStack(Items.iron_door,1), null, new FluidStack(SSFluids.iron, 5000));
		recipe.add(new ItemStack(Items.iron_ingot,1), null, new FluidStack(SSFluids.iron, 1000));


		recipe.add(new ItemStack(Items.gold_ingot,1), null, new FluidStack(SSFluids.gold, 1000));

    }

}
