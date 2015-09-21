package shift.sextiarysector.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.api.gearforce.tileentity.EnergyStorage;
import shift.sextiarysector.api.gearforce.tileentity.IGearForceGrid;
import shift.sextiarysector.api.gearforce.tileentity.IGearForceHandler;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;

public class TileEntityGFDynamo extends TileEntityDirection implements IGearForceHandler, IGearForceGrid, IEnergyProvider {

    public float rotateStep = 0;
    public EnergyStorage storage = new EnergyStorage("Base", 3, 30000);
    public boolean isActivity = false;

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

        if (!isActivity) return;

        if (this.rotateStep > 360) {
            this.rotateStep -= 360;
        }

        this.rotateStep += 6;

    }

    public void updateServerEntity() {

        ForgeDirection d = this.getDirection().getOpposite();

        TileEntity t = this.worldObj.getTileEntity(xCoord + d.offsetX, yCoord + d.offsetY, zCoord + d.offsetZ);

        if (t instanceof IEnergyReceiver && this.canWork()) {

            IEnergyReceiver rf = (IEnergyReceiver) t;

            int add = this.storage.drawEnergy(3, 14, true);
            int add2 = rf.receiveEnergy(direction, add, false);
            this.storage.drawEnergy(3, add2, false);

            if (isActivity == false && add2 != 0) {
                this.isActivity = true;
                this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
            }

        } else if (isActivity) {
            this.isActivity = false;
            this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        }

    }

    public boolean canWork() {
        return this.worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord) && this.storage.getSpeedStored() > 10;
    }

    public float getRotateStep() {
        return rotateStep;
    }

    //GF
    @Override
    public int addEnergy(ForgeDirection from, int power, int speed, boolean simulate) {

        if (!this.canInterface(from)) return 0;

        int i = storage.addEnergy(power, speed, simulate);

        return i;
    }

    @Override
    public int drawEnergy(ForgeDirection from, int power, int speed, boolean simulate) {
        return 0;
    }

    @Override
    public boolean canInterface(ForgeDirection from) {
        return this.getDirection().ordinal() == from.ordinal();
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
        return this.getDirection().ordinal() == from.ordinal();
    }

    @Override
    public boolean canOut(ForgeDirection from) {
        return false;
    }

    //RF
    @Override
    public boolean canConnectEnergy(ForgeDirection paramForgeDirection) {
        return this.getDirection().getOpposite().ordinal() == paramForgeDirection.ordinal();
    }

    @Override
    public int extractEnergy(ForgeDirection paramForgeDirection, int paramInt, boolean paramBoolean) {
        return 0;
    }

    @Override
    public int getEnergyStored(ForgeDirection paramForgeDirection) {
        return 0;
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection paramForgeDirection) {
        return 10000;
    }

    //NBT
    @Override
    public void readFromNBT(NBTTagCompound par1nbtTagCompound) {

        super.readFromNBT(par1nbtTagCompound);
        this.storage.readFromNBT(par1nbtTagCompound);
        this.isActivity = par1nbtTagCompound.getBoolean("isActivity");

    }

    @Override
    public void writeToNBT(NBTTagCompound par1nbtTagCompound) {

        super.writeToNBT(par1nbtTagCompound);
        this.storage.writeToNBT(par1nbtTagCompound);
        par1nbtTagCompound.setBoolean("isActivity", isActivity);

    }

}
