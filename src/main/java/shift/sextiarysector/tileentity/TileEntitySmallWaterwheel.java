package shift.sextiarysector.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.api.gearforce.tileentity.IGearForceGrid;
import shift.sextiarysector.api.gearforce.tileentity.IGearForceHandler;

public class TileEntitySmallWaterwheel extends TileEntityDirection implements IGearForceHandler, IGearForceGrid {

    public float rotateStep = 0;

    public int size = 1;

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

            ((IGearForceHandler) t).addEnergy(this.direction, 2, 40, false);
        }

    }

    public float getRotateStep() {
        return -rotateStep;
    }

    boolean isWork() {

        ForgeDirection d1 = this.getDirection().getRotation(ForgeDirection.UP);
        ForgeDirection d2 = this.getDirection().getRotation(ForgeDirection.UP).getOpposite();

        for (int i = 0; i < 2; i++) {

            int ra = 1;
            for (int j = ra * -1; j <= ra; j++) {
                int x = xCoord + d1.offsetX * j;
                int y = yCoord + i;
                int z = zCoord + d1.offsetZ * j;

                if (i == 0 && j == 0) continue;

                if (!this.worldObj.isAirBlock(x, y, z)) return false;

            }

        }

        Block b31 = this.getWorldObj().getBlock(xCoord + d1.offsetX, yCoord - 1, zCoord + d1.offsetZ);
        Block b32 = this.getWorldObj().getBlock(xCoord, yCoord - 1, zCoord);
        Block b33 = this.getWorldObj().getBlock(xCoord + d2.offsetX, yCoord - 1, zCoord + d1.offsetZ);

        if (!(b31 instanceof BlockLiquid) || !(b32 instanceof BlockLiquid) || !(b33 instanceof BlockLiquid)) {
            return false;
        }

        return true;
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
    public boolean canIn(ForgeDirection from) {
        return false;
    }

    @Override
    public boolean canOut(ForgeDirection from) {
        return this.direction.getOpposite().ordinal() == from.ordinal();
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
