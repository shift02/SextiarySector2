package shift.sextiarysector.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.api.gearforce.tileentity.IGearForceGrid;
import shift.sextiarysector.api.gearforce.tileentity.IGearForceHandler;

public class TileEntityWindmill extends TileEntityDirection implements IGearForceHandler, IGearForceGrid {

    public float rotateStep = 0;

    public int size = 2;

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

        if (!this.isWork()) {
            return;
        }

        if (this.rotateStep > 360) {
            this.rotateStep -= 360;
        }

        this.rotateStep += 2;

    }

    public void updateServerEntity() {
        TileEntity t = this.worldObj.getTileEntity(xCoord - this.direction.offsetX, yCoord - this.direction.offsetY, zCoord - this.direction.offsetZ);
        if (t != null && t instanceof IGearForceHandler && this.isWork()) {

            ((IGearForceHandler) t).addEnergy(this.direction, 2, 20, false);
        }

    }

    boolean isWork() {

        ForgeDirection d1 = this.getDirection().getRotation(ForgeDirection.UP);

        int ra1 = 3;
        for (int i = ra1 * -1; i <= ra1; i++) {

            int ra2 = 3;
            for (int j = ra2 * -1; j <= ra2; j++) {
                int x = xCoord + d1.offsetX * j;
                int y = yCoord + i;
                int z = zCoord + d1.offsetZ * j;

                if (i == 0 && j == 0) continue;

                if (!this.worldObj.isAirBlock(x, y, z)) return false;

            }

        }

        return true;
    }

    public float getRotateStep() {
        return -rotateStep;
    }

    @Override
    public int addEnergy(ForgeDirection from, int power, int speed, boolean simulate) {
        return 0;
    }

    @Override
    public int drawEnergy(ForgeDirection from, int power, int speed, boolean simulate) {
        return 0;
    }

    @Override
    public boolean canInterface(ForgeDirection from) {
        return this.direction.getOpposite().ordinal() == from.ordinal();
    }

    @Override
    public int getPowerStored(ForgeDirection from) {
        return 0;
    }

    @Override
    public int getSpeedStored(ForgeDirection from) {
        return 0;
    }

    @Override
    public int getMaxPowerStored(ForgeDirection from) {
        return 0;
    }

    @Override
    public int getMaxSpeedStored(ForgeDirection from) {
        return 0;
    }

    @Override
    public boolean canIn(ForgeDirection direction) {
        return false;
    }

    @Override
    public boolean canOut(ForgeDirection direction) {
        return this.direction.getOpposite().ordinal() == direction.ordinal();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        AxisAlignedBB bb = INFINITE_EXTENT_AABB;

        ForgeDirection d1 = this.getDirection().getRotation(ForgeDirection.UP);

        bb = AxisAlignedBB.getBoundingBox(xCoord - Math.abs(d1.offsetX * size), yCoord - size, zCoord - Math.abs(d1.offsetZ * size), xCoord + 1 + Math.abs(d1.offsetX * size), yCoord + 1 + size, zCoord + 1 + Math.abs(d1.offsetZ * size));

        return bb;
    }

}
