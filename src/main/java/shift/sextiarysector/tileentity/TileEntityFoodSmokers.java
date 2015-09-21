package shift.sextiarysector.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.fluids.FluidStack;
import shift.sextiarysector.SSRecipes;

public class TileEntityFoodSmokers extends TileEntityFluidMachineBase {

    @Override
    protected ItemStack getResult(ItemStack stackInSlot) {
        return SSRecipes.foodSmokers.getResult(stackInSlot);
    }

    @Override
    protected FluidStack getFluidResult(ItemStack stackInSlot) {
        return SSRecipes.foodSmokers.getFluidResult(stackInSlot);
    }

    protected void chargeFuel() {

        if (TileEntityFurnace.isItemFuel(this.items.getStackInSlot(1))) {
            this.fuel = this.fuelMax = (TileEntityFurnace.getItemBurnTime(this.items.getStackInSlot(1)) / 2);
            this.items.reduceStackSize(1, 1);
            this.markDirty();
            this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        }

    }

    @Override
    public String getInventoryName() {
        return "gui.ss.food_smokers";
    }

}
