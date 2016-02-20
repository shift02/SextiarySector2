package shift.sextiarysector.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import shift.sextiarysector.api.gearforce.tileentity.IGearForceGrid;
import shift.sextiarysector.api.gearforce.tileentity.IGearForceHandler;
import shift.sextiarysector.container.ItemBox;

public class TileEntitySteamMotor extends TileEntityDirection implements IGearForceHandler, IGearForceGrid, ISidedInventory, IFluidHandler {

    protected static final int[] slots_top = new int[] { 0 };
    protected static final int[] slots_bottom = new int[] { 1 };
    protected static final int[] slots_sides = new int[] { 0 };

    protected ItemBox items = new ItemBox("Base", 2);

    //液体
    private final FluidTank tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME * 15);

    private int lastSteam = 0;
    private boolean lastWork = false;
    public float rotateStep = 0;

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
        if (lastWork && this.canWork()) {
            rotateStep -= 10;
        }
    }

    public void updateServerEntity() {

        if (lastSteam != this.tank.getFluidAmount()) {
            lastSteam = this.tank.getFluidAmount();
            this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        }

        if (this.canWork()) {
            this.work();
        }

        if (this.canCharge()) {
            this.chargeSteam();
        }

    }

    public boolean canWork() {
        return this.worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord) && this.tank.getFluidAmount() > 0;
    }

    public void work() {

        int use = this.tank.drain(20, false) == null ? 0 : this.tank.drain(20, true).amount;

        if (use == 0) {
            if (lastWork) {
                lastWork = false;
                this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
            }
            return;
        }

        if (!lastWork) {
            lastWork = true;
            this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        }

        TileEntity t = this.worldObj.getTileEntity(xCoord - this.direction.offsetX, yCoord - this.direction.offsetY, zCoord - this.direction.offsetZ);
        if (t != null && t instanceof IGearForceHandler && this.canWork()) {

            ((IGearForceHandler) t).addEnergy(this.direction, 3, (int) (use * 2.2f), false);

        }

    }

    public void chargeSteam() {

        FluidStack f = FluidContainerRegistry.getFluidForFilledItem(items.getStackInSlot(0));
        this.fill(ForgeDirection.UP, f, true);
        ItemStack item = items.getStackInSlot(0).getItem().getContainerItem(items.getStackInSlot(0));

        if (item != null) {

            if (this.items.getStackInSlot(1) == null) {
                this.setInventorySlotContents(1, item.copy());
            } else if (this.items.getStackInSlot(1).isItemEqual(item)) {
                this.items.getStackInSlot(1).stackSize += item.stackSize;
            }

        }

        this.items.reduceStackSize(0, 1);

        this.markDirty();

        this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

    }

    public boolean canCharge() {

        FluidStack f = FluidContainerRegistry.getFluidForFilledItem(items.getStackInSlot(0));

        if (f == null) return false;

        int i = this.fill(ForgeDirection.UP, f, false);

        if (i != f.amount) return false;

        if (items.getStackInSlot(0) == null) return false;
        if (this.items.getStackInSlot(1) == null) return true;
        ItemStack item = items.getStackInSlot(0).getItem().getContainerItem(items.getStackInSlot(0).copy());
        if (item == null) return true;
        int result = this.items.getStackInSlot(1).stackSize + item.stackSize;
        return (result <= getInventoryStackLimit() && result <= item.getMaxStackSize());

    }

    public float getRotateStep() {
        return rotateStep;
    }

    //GUI
    public FluidTank getTank() {
        return tank;
    }

    public boolean isFluid() {
        return this.getTank().getFluidAmount() > 0;
    }

    //IInventory関係
    @Override
    public int getSizeInventory() {
        return items.getSizeInventory();
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return items.getStackInSlot(i);
    }

    @Override
    public ItemStack decrStackSize(int i, int j) {
        return items.decrStackSize(i, j);
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        return items.getStackInSlotOnClosing(i);
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack) {
        items.setInventorySlotContents(i, itemstack);
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return items.getInventoryStackLimit();
    }

    @Override
    public void markDirty() {
        super.markDirty();
        items.onInventoryChanged();
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : entityplayer.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory() {
    }

    @Override
    public void closeInventory() {
    }

    /*入れる*/
    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {

        return i == 0;

    }

    //ISidedInventory関係
    //0 素材 ,1 燃料 ,2 完成品 ,3 素材のコンテナ ,4 空のボトル , 5 液体の入ったボトル
    @Override
    public int[] getAccessibleSlotsFromSide(int var1) {

        if (var1 == 0) {
            return slots_bottom;
        }

        if (var1 == 1) {
            return slots_top;
        }

        return slots_sides;

    }

    @Override
    public boolean canInsertItem(int i, ItemStack itemstack, int j) {
        return this.isItemValidForSlot(i, itemstack);
    }

    @Override
    public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {

        if (p_102008_1_ == 1) {
            return true;
        }

        return false;
    }

    @Override
    public String getInventoryName() {
        return "gui.ss.steam_motor";
    }

    //GF
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

    //IFluidHandler関係
    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        if (resource == null || resource.getFluid() == null) return 0;
        if (!canFill(from, resource.getFluid())) return 0;

        return tank.fill(resource, doFill);
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        if (resource == null || !resource.isFluidEqual(getTank().getFluid())) {
            return null;
        }
        return getTank().drain(resource.amount, doDrain);

    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        return getTank().drain(maxDrain, doDrain);
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        if (!fluid.getName().equals("steam")) return false;

        return true;
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return true;
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        return new FluidTankInfo[] { getTank().getInfo() };
    }

    //NBT
    @Override
    public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
        super.readFromNBT(par1nbtTagCompound);
        this.items.readFromNBT(par1nbtTagCompound);
        this.tank.readFromNBT(par1nbtTagCompound);
        if (par1nbtTagCompound.hasKey("Empty") && this.tank.getFluidAmount() > 0) this.tank.setFluid(null);
        this.lastWork = par1nbtTagCompound.getBoolean("lastwork");
    }

    @Override
    public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
        super.writeToNBT(par1nbtTagCompound);
        this.items.writeToNBT(par1nbtTagCompound);
        this.tank.writeToNBT(par1nbtTagCompound);
        par1nbtTagCompound.setBoolean("lastwork", this.lastWork);
    }

}
