package shift.sextiarysector.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fluids.IFluidHandler;
import shift.sextiarysector.api.gearforce.tileentity.EnergyStorage;
import shift.sextiarysector.api.gearforce.tileentity.IGearForceGrid;
import shift.sextiarysector.api.gearforce.tileentity.IGearForceHandler;

public class TileEntityPump extends TileEntityDirection implements IFluidHandler, IGearForceHandler, IGearForceGrid {

    protected FluidTank tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME);

    public EnergyStorage storage = new EnergyStorage("Base", 2, 3000);

    private int cooltime = 0;

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (this.worldObj.isRemote) {
            this.updateClientEntity();
        } else {
            this.updateServerEntity();
        }

    }

    public void updateClientEntity() {
    }

    public void updateServerEntity() {

        cooltime++;
        if (cooltime > 10) {
            cooltime = 0;

            if (storage.drawEnergy(2, 20, false) >= 18) {
                this.updateServerOutWorkEntity();
                this.updateServerInWorkEntity();
            }

        }

    }

    public void updateServerInWorkEntity() {

        int x = this.xCoord + this.direction.offsetX;
        int y = this.yCoord + this.direction.offsetY;
        int z = this.zCoord + this.direction.offsetZ;

        Block block = this.getWorldObj().getBlock(x, y, z);

        Fluid f = FluidRegistry.lookupFluidForBlock(block);

        if (block instanceof BlockLiquid || block instanceof IFluidBlock) {
            if (this.getWorldObj().getBlockMetadata(x, y, z) != 0) return;
        }

        if (f != null && this.tank.getFluid() == null) {

            this.worldObj.removeTileEntity(x, y, z);
            this.worldObj.setBlock(x, y, z, Blocks.air, 0, 3);
            this.tank.setFluid(new FluidStack(f, FluidContainerRegistry.BUCKET_VOLUME));

        } else if (this.worldObj.getTileEntity(x, y, z) instanceof IFluidHandler) {

            IFluidHandler t = (IFluidHandler) this.worldObj.getTileEntity(x, y, z);

            if (this.tank.getFluid() == null) {

                if (t.getTankInfo(direction.getOpposite()) != null && t.getTankInfo(direction.getOpposite())[0].fluid != null
                        && t.canDrain(this.direction.getOpposite(), t.getTankInfo(direction.getOpposite())[0].fluid.getFluid())) {
                    FluidStack fs = t.drain(direction.getOpposite(), FluidContainerRegistry.BUCKET_VOLUME, true);
                    this.tank.fill(fs, true);
                }

            } else if (t.canDrain(this.direction.getOpposite(), this.tank.getFluid().getFluid())) {
                FluidStack fs = t.drain(direction.getOpposite(), FluidContainerRegistry.BUCKET_VOLUME - this.tank.getFluidAmount(), true);
                this.tank.fill(fs, true);
            }

        }

    }

    public void updateServerOutWorkEntity() {
        int x = this.xCoord + ForgeDirection.UP.offsetX;
        int y = this.yCoord + ForgeDirection.UP.offsetY;
        int z = this.zCoord + ForgeDirection.UP.offsetZ;

        FluidStack f = this.tank.getFluid();

        if (f != null) {

            if (this.worldObj.isAirBlock(x, y, z) && f.getFluid().canBePlacedInWorld()) {

                this.worldObj.setBlock(x, y, z, this.tank.getFluid().getFluid().getBlock());
                this.tank.setFluid(null);

            } else if (this.worldObj.getTileEntity(x, y, z) instanceof IFluidHandler
                    && ((IFluidHandler) this.worldObj.getTileEntity(x, y, z)).canFill(ForgeDirection.DOWN, f.getFluid())) {

                int i = ((IFluidHandler) this.worldObj.getTileEntity(x, y, z)).fill(ForgeDirection.DOWN, f, true);
                this.tank.drain(i, true);

            } else
                if (f.getFluid() == FluidRegistry.WATER && this.worldObj.getBlock(x, y, z) == Blocks.cauldron && this.worldObj.getBlockMetadata(x, y, z) != 3) {
                this.worldObj.setBlockMetadataWithNotify(x, y, z, 3, 2);
                this.worldObj.func_147453_f(x, y, z, this.worldObj.getBlock(x, y, z));
            }

        }

    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        tank.readFromNBT(tag);
        storage.readFromNBT(tag);
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tank.writeToNBT(tag);
        storage.writeToNBT(tag);
    }

    @Override
    public int addEnergy(ForgeDirection from, int power, int speed, boolean simulate) {

        if (this.getDirection().ordinal() == from.ordinal()) return 0;

        int i = storage.addEnergy(power, speed, simulate);
        return i;
    }

    @Override
    public int drawEnergy(ForgeDirection from, int power, int speed, boolean simulate) {
        return 0;
    }

    @Override
    public boolean canInterface(ForgeDirection from) {
        return true;
    }

    @Override
    public int getPowerStored(ForgeDirection from) {
        return storage.getPowerStored();
    }

    @Override
    public int getSpeedStored(ForgeDirection from) {
        return storage.getSpeedStored();
    }

    @Override
    public int getMaxPowerStored(ForgeDirection from) {
        return storage.getMaxPower();
    }

    @Override
    public int getMaxSpeedStored(ForgeDirection from) {
        return storage.getMaxSpeed();
    }

    @Override
    public boolean canIn(ForgeDirection from) {
        return this.getDirection().ordinal() != from.ordinal();
    }

    @Override
    public boolean canOut(ForgeDirection from) {
        return false;
    }

    //IFluidHandler
    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        return 0;
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        return null;
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        return null;
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return from.ordinal() == ForgeDirection.UP.ordinal();
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return from.ordinal() == ForgeDirection.UP.ordinal();
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        return new FluidTankInfo[] { tank.getInfo() };
    }

}
