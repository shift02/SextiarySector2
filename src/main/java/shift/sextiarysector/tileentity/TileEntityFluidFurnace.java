package shift.sextiarysector.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import shift.sextiarysector.SSRecipes;

public class TileEntityFluidFurnace extends TileEntityFluidMachineBase {

    @Override
    protected ItemStack getResult(ItemStack stackInSlot) {
        return SSRecipes.fluidFurnace.getResult(stackInSlot);
    }

    @Override
    protected FluidStack getFluidResult(ItemStack stackInSlot) {
        return SSRecipes.fluidFurnace.getFluidResult(stackInSlot);
    }

    @Override
    public String getInventoryName() {
        return "gui.ss.fluid_furnace";
    }

}
