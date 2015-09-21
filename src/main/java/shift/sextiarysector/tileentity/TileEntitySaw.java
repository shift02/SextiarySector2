package shift.sextiarysector.tileentity;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.api.gearforce.tileentity.EnergyStorage;
import shift.sextiarysector.api.gearforce.tileentity.IGearForceHandler;
import shift.sextiarysector.api.gearforce.tileentity.IGearForceGrid;

public class TileEntitySaw extends TileEntityDirection implements IGearForceHandler, IGearForceGrid {

    public float rotateStep = 360;
    private int speed = 0;
    private int time = 0;

    public EnergyStorage storage = new EnergyStorage("Base", 3, 3000);

    @Override
    public void updateEntity() {

        super.updateEntity();

        if (this.worldObj.isRemote) {
            this.updateClientEntity();
        } else {
            this.updateServerEntity();
        }

        if (this.canWork()) {
            this.workSaw();
            this.addDamage();
        }

    }

    public void updateClientEntity() {

        this.rotateStep += speed;

    }

    private void updateServerEntity() {

        if (time >= 10) {
            this.changeSpeed();
            time = 0;
        } else {
            time++;
        }

    }

    private void changeSpeed() {

        int use = this.storage.drawEnergy(3, 200, false);

        int i = this.speed;

        if (use >= 180) {
            speed = Math.min(16, speed + 1);
        } else {
            speed = Math.max(0, speed - 1);
        }

        if (i != speed) {
            this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        }

    }

    public boolean canWork() {

        return speed >= 14;

    }

    private void workSaw() {

        Block b = this.worldObj.getBlock(xCoord + this.getDirection().offsetX, yCoord + this.getDirection().offsetY, zCoord + this.getDirection().offsetZ);

        if (b != null && b.isNormalCube() && b.getBlockHardness(worldObj, xCoord + this.getDirection().offsetX, yCoord + this.getDirection().offsetY, zCoord + this.getDirection().offsetZ) != -1.0f) {
            this.worldObj.func_147480_a(this.xCoord + this.getDirection().offsetX, this.yCoord + this.getDirection().offsetY, this.zCoord + this.getDirection().offsetZ, true);
            this.worldObj.removeTileEntity(xCoord + this.getDirection().offsetX, yCoord + this.getDirection().offsetY, zCoord + this.getDirection().offsetZ);
        }

    }

    private void addDamage() {

        AxisAlignedBB aabb = getDirectionAABB();

        List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity((Entity) null, aabb, null);

        for (Entity e : list) {
            if (e instanceof EntityItem) continue;
            e.attackEntityFrom(DamageSource.cactus, 1.0f);
        }

    }

    private AxisAlignedBB getDirectionAABB() {

        int minX = 0, minY = 0, minZ = 0;
        int maxX = 1, maxY = 1, maxZ = 1;

        switch (direction) {
            case DOWN:
                minY = -1;
                break;
            case UP:
                maxY = 2;
                break;
            case NORTH:
                minZ = -1;
                break;
            case SOUTH:
                maxZ = 2;
                break;
            case WEST:
                minX = -1;
                break;
            case EAST:
                maxX = 2;
                break;
            default:
                break;
        }

        return AxisAlignedBB.getBoundingBox(this.xCoord + minX, this.yCoord + minY, this.zCoord + minZ, this.xCoord + maxX, this.yCoord + maxY, this.zCoord + maxZ);

    }

    public float getRotateStep() {
        return rotateStep;
    }

    @Override
    public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
        super.readFromNBT(par1nbtTagCompound);
        this.speed = par1nbtTagCompound.getInteger("speed");
    }

    @Override
    public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
        super.writeToNBT(par1nbtTagCompound);
        par1nbtTagCompound.setInteger("speed", this.speed);
    }

    @Override
    public int addEnergy(ForgeDirection from, int power, int speed, boolean simulate) {

        if (!canInterface(from)) return 0;

        int i = storage.addEnergy(power, speed, simulate);

        return i;
    }

    @Override
    public int drawEnergy(ForgeDirection from, int power, int speed, boolean simulate) {

        return 0;

    }

    @Override
    public boolean canInterface(ForgeDirection from) {
        return this.getDirection().getOpposite().ordinal() == from.ordinal();
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
        return this.getDirection().getOpposite().ordinal() == from.ordinal();
    }

    @Override
    public boolean canOut(ForgeDirection from) {
        return false;
    }
}
