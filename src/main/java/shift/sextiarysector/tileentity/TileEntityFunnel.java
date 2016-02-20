package shift.sextiarysector.tileentity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import shift.sextiarysector.container.ItemBox;

public class TileEntityFunnel extends TileEntity implements ISidedInventory, IFluidHandler {

    protected static final int[] slots_top = new int[] { 0 };
    protected static final int[] slots_bottom = new int[] { 1 };
    protected static final int[] slots_sides = new int[] { 0, 1 };

    protected ItemBox items = new ItemBox("Base", 2);

    public FluidTank tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME * 32);
    protected int lastAmount;

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

    private void updateServerEntity() {

        /*
        if (this.tank.getFluidAmount() * (0.01f) != lastAmount) {
        	//System.out.println(this.tank.getFluidAmount());
        	lastAmount = (int) (this.tank.getFluidAmount() * (0.01f));
        	this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        
        }*/

        if (items.getStackInSlot(0) != null) {
            this.doSlotFluid();
        }

        this.addDownFluidEntity();
        this.getUPFluidEntity();

        this.addFluidBlock();
        this.getUPFluidBlock();

    }

    private void addFluidBlock() {

        if (this.tank.getFluidAmount() == 0) return;

        TileEntity t = this.worldObj.getTileEntity(xCoord, yCoord - 1, zCoord);

        if (!(t instanceof IFluidHandler)) return;

        IFluidHandler f = (IFluidHandler) t;

        FluidStack fs = this.tank.getFluid().copy();
        if (fs.amount > 50) fs.amount = 50;

        int i = f.fill(ForgeDirection.DOWN.getOpposite(), fs, true);
        this.tank.drain(i, true);

    }

    private void getUPFluidBlock() {

        TileEntity t = this.worldObj.getTileEntity(xCoord, yCoord + 1, zCoord);

        if (!(t instanceof IFluidHandler)) return;

        IFluidHandler f = (IFluidHandler) t;

        if (this.tank.getFluid() == null) {

            if (f.getTankInfo(ForgeDirection.UP.getOpposite()) != null && f.getTankInfo(ForgeDirection.UP.getOpposite())[0].fluid != null
                    && f.canDrain(ForgeDirection.UP.getOpposite(), f.getTankInfo(ForgeDirection.UP.getOpposite())[0].fluid.getFluid())) {

                FluidStack fs = f.drain(ForgeDirection.UP.getOpposite(), 50, true);
                this.tank.fill(fs, true);
            }

        } else if (f.canDrain(ForgeDirection.UP.getOpposite(), this.tank.getFluid().getFluid())) {
            FluidStack fs = f.drain(ForgeDirection.UP.getOpposite(), 50, false);

            int fill = this.tank.fill(fs, true);

            f.drain(ForgeDirection.UP.getOpposite(), fill, true);

        }

    }

    private void getUPFluidEntity() {

        //if (this.tank.getFluidAmount() == 0) return;

        AxisAlignedBB aabb = getDirectionAABB(ForgeDirection.UP);

        List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity((Entity) null, aabb, null);
        IFluidHandler f = null;

        for (Entity e : list) {
            if (!(e instanceof IFluidHandler)) continue;
            f = (IFluidHandler) e;
            break;
        }

        if (f == null) return;

        if (this.tank.getFluid() == null) {

            if (f.getTankInfo(ForgeDirection.UP.getOpposite()) != null && f.getTankInfo(ForgeDirection.UP.getOpposite())[0].fluid != null
                    && f.canDrain(ForgeDirection.UP.getOpposite(), f.getTankInfo(ForgeDirection.UP.getOpposite())[0].fluid.getFluid())) {

                FluidStack fs = f.drain(ForgeDirection.UP.getOpposite(), 50, true);
                this.tank.fill(fs, true);
            }

        } else if (f.canDrain(ForgeDirection.UP.getOpposite(), this.tank.getFluid().getFluid())) {
            FluidStack fs = f.drain(ForgeDirection.UP.getOpposite(), 50, false);

            int fill = this.tank.fill(fs, true);

            f.drain(ForgeDirection.UP.getOpposite(), fill, true);

        }

        //this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

    }

    private void addDownFluidEntity() {

        if (this.tank.getFluidAmount() == 0) return;

        AxisAlignedBB aabb = getDirectionAABB(ForgeDirection.DOWN);

        List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity((Entity) null, aabb, null);
        IFluidHandler f = null;

        for (Entity e : list) {
            if (!(e instanceof IFluidHandler)) continue;
            f = (IFluidHandler) e;
            break;
        }

        if (f == null) return;

        if (f.canFill(ForgeDirection.DOWN.getOpposite(), this.tank.getFluid().getFluid())) {

            FluidStack fs = this.tank.getFluid().copy();
            if (fs.amount > 50) fs.amount = 50;

            int i = f.fill(ForgeDirection.DOWN.getOpposite(), fs, true);
            this.tank.drain(i, true);

            //this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

        }

    }

    private AxisAlignedBB getDirectionAABB(ForgeDirection d) {

        int minX = 0, minY = 0, minZ = 0;
        int maxX = 1, maxY = 1, maxZ = 1;

        switch (d) {
            case DOWN:
                minY = -2;
                break;
            case UP:
                maxY = 3;
                break;
            default:
                break;
        }

        return AxisAlignedBB.getBoundingBox(this.xCoord + minX, this.yCoord + minY, this.zCoord + minZ, this.xCoord + maxX, this.yCoord + maxY, this.zCoord + maxZ);
    }

    private void doSlotFluid() {

        if (this.canChargeFluid()) {
            this.chargeFluid();
        }

        if (this.canDrainFluid()) {
            this.drainFluid();
        }

    }

    public void chargeFluid() {

        FluidStack f = FluidContainerRegistry.getFluidForFilledItem(items.getStackInSlot(0));
        this.fill(ForgeDirection.UP, f, true);
        ItemStack item = FluidContainerRegistry.drainFluidContainer(items.getStackInSlot(0));//items.getStackInSlot(0).getItem().getContainerItem(items.getStackInSlot(0));

        if (item != null) {

            if (this.items.getStackInSlot(1) == null) {
                this.setInventorySlotContents(1, item.copy());
            } else if (this.items.getStackInSlot(1).isItemEqual(item)) {
                this.items.getStackInSlot(1).stackSize += item.stackSize;
            }

        }

        this.items.reduceStackSize(0, 1);

        this.markDirty();

        //this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

    }

    public boolean canChargeFluid() {

        FluidStack f = FluidContainerRegistry.getFluidForFilledItem(items.getStackInSlot(0));

        if (f == null) return false;

        int i = this.fill(ForgeDirection.UP, f, false);

        if (i != f.amount) return false;

        if (items.getStackInSlot(0) == null) return false;
        if (this.items.getStackInSlot(1) == null) return true;
        ItemStack item = FluidContainerRegistry.drainFluidContainer(items.getStackInSlot(0));// items.getStackInSlot(0).getItem().getContainerItem(items.getStackInSlot(0).copy());
        if (item == null) return true;
        int result = this.items.getStackInSlot(1).stackSize + item.stackSize;
        return (result <= getInventoryStackLimit() && result <= item.getMaxStackSize());

    }

    private void drainFluid() {

        if (canDrainFluid()) {

            ItemStack empty = items.getStackInSlot(0);

            for (FluidContainerData f : FluidContainerRegistry.getRegisteredFluidContainerData()) {

                if (f.emptyContainer.isItemEqual(empty) && f.fluid.isFluidEqual(this.tank.getFluid()) && f.fluid.amount <= this.tank.getFluidAmount()) {

                    ItemStack item = FluidContainerRegistry.fillFluidContainer(this.tank.getFluid(), empty);
                    if (this.items.getStackInSlot(1) == null) {
                        this.setInventorySlotContents(1, item.copy());
                    } else if (this.items.getStackInSlot(1).isItemEqual(item)) {
                        this.items.getStackInSlot(1).stackSize += item.stackSize;
                    }

                    this.tank.drain(f.fluid.amount, true);

                    this.items.reduceStackSize(0, 1);

                    this.markDirty();

                    //this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

                    return;
                }

            }

        }

    }

    public boolean canDrainFluid() {
        if (items.getStackInSlot(0) == null) return false;
        ItemStack item = FluidContainerRegistry.fillFluidContainer(this.tank.getFluid(), items.getStackInSlot(0));
        if (item == null) return false;
        if (this.items.getStackInSlot(1) == null) return true;
        int result = this.items.getStackInSlot(1).stackSize + item.stackSize;
        return (result <= getInventoryStackLimit() && result <= item.getMaxStackSize());
    }

    public float getRendererAmount() {
        return (float) this.tank.getFluidAmount() / (float) this.tank.getCapacity();
    }

    public boolean hasFluidStack() {
        return tank.getFluidAmount() > 0;
    }

    public FluidStack getFluidStack() {
        return tank.getFluid();
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
        return "gui.ss.funnel";
    }

    /* IFluidHandler */
    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        return tank.fill(resource, doFill);
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        if (resource == null || !resource.isFluidEqual(tank.getFluid())) {
            return null;
        }
        return tank.drain(resource.amount, doDrain);
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        return tank.drain(maxDrain, doDrain);
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return true;
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return true;
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        return new FluidTankInfo[] { tank.getInfo() };
    }

    //NBT
    @Override
    public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
        super.readFromNBT(par1nbtTagCompound);
        this.items.readFromNBT(par1nbtTagCompound);
        tank.readFromNBT(par1nbtTagCompound);
        if (par1nbtTagCompound.hasKey("Empty") && tank.getFluidAmount() > 0) this.tank.setFluid(null);
    }

    @Override
    public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
        super.writeToNBT(par1nbtTagCompound);
        this.items.writeToNBT(par1nbtTagCompound);
        tank.writeToNBT(par1nbtTagCompound);
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        writeToNBT(nbt);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        readFromNBT(pkt.func_148857_g());
        //this.worldObj.func_147479_m(xCoord, yCoord, zCoord);
        this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }

}
