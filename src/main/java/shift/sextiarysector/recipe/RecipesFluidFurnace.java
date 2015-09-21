package shift.sextiarysector.recipe;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import shift.sextiarysector.SSFluids;
import shift.sextiarysector.SSItems;

public class RecipesFluidFurnace {

    public static void addRecipes(RecipeSimpleFluid recipe) {

        //recipe.add(new ItemStack(Blocks.netherrack,1),null,  new FluidStack(SSFluids.drinkingWater, 50));
        recipe.add(new ItemStack(Items.water_bucket, 1), new ItemStack(Items.stick, 1), new FluidStack(SSFluids.drinkingWater, 1000));

        recipe.add(new ItemStack(Items.rotten_flesh, 1), new ItemStack(SSItems.dryingFlesh, 1), new FluidStack(FluidRegistry.WATER, 500));

        recipe.add(new ItemStack(Items.iron_door, 1), null, new FluidStack(SSFluids.iron, 5000));
        recipe.add(new ItemStack(Items.iron_ingot, 1), null, new FluidStack(SSFluids.iron, 1000));
        recipe.add(new ItemStack(Items.iron_axe, 1), new ItemStack(Items.stick, 2), new FluidStack(SSFluids.iron, 2500));
        recipe.add(new ItemStack(Items.iron_hoe, 1), new ItemStack(Items.stick, 2), new FluidStack(SSFluids.iron, 1500));
        recipe.add(new ItemStack(Items.iron_pickaxe, 1), new ItemStack(Items.stick, 2), new FluidStack(SSFluids.iron, 2500));
        recipe.add(new ItemStack(Items.iron_shovel, 1), new ItemStack(Items.stick, 2), new FluidStack(SSFluids.iron, 500));
        recipe.add(new ItemStack(Items.iron_sword, 1), new ItemStack(Items.stick, 1), new FluidStack(SSFluids.iron, 1500));

        recipe.add(new ItemStack(Items.gold_ingot, 1), null, new FluidStack(SSFluids.gold, 1000));
        recipe.add(new ItemStack(Items.golden_axe, 1), new ItemStack(Items.stick, 2), new FluidStack(SSFluids.gold, 2500));
        recipe.add(new ItemStack(Items.golden_hoe, 1), new ItemStack(Items.stick, 2), new FluidStack(SSFluids.gold, 1500));
        recipe.add(new ItemStack(Items.golden_pickaxe, 1), new ItemStack(Items.stick, 2), new FluidStack(SSFluids.gold, 2500));
        recipe.add(new ItemStack(Items.golden_shovel, 1), new ItemStack(Items.stick, 2), new FluidStack(SSFluids.gold, 500));
        recipe.add(new ItemStack(Items.golden_sword, 1), new ItemStack(Items.stick, 1), new FluidStack(SSFluids.gold, 1500));

        recipe.add("logWood", new ItemStack(Items.coal, 1, 1), new FluidStack(SSFluids.sap, 500));

    }

}
