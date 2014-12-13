package shift.sextiarysector.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import shift.sextiarysector.api.machine.item.GearForceItem;
import shift.sextiarysector.container.ItemBox;

public abstract class TileEntityFluidMachineBase  extends TileEntityDirection  implements ISidedInventory, IFluidHandler {

	protected static final int[] slots_top = new int[] { 0 };
	protected static final int[] slots_bottom = new int[] { 2, 1 };
	protected static final int[] slots_sides = new int[] { 1 };

	//0 素材 ,1 燃料 ,2 完成品 ,3 素材のコンテナ ,4 空のボトル , 5 液体の入ったボトル
	protected ItemBox items = new ItemBox("Base", 6);

	//液体
	private FluidTank tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME * 10);

	//作業の進捗
	public int machineWorkProgressTime;

	//作業の進捗の最大値 この数字になると完了する
	public int machineMaxProgressTime = 200;

	//燃料と投入されている燃料のマックス状態の量
	public int fuel;
	public int fuelMax;

	public boolean on;

	@Override
	public void updateEntity()
	{
		super.updateEntity();
		if (this.worldObj.isRemote) {
			this.updateClientEntity();
		} else {

			this.updateServerEntity();

		}

	}

	public void updateClientEntity()
	{
	}

	public void updateServerEntity()
	{
		if(fuel>0){
			fuel--;
			if(fuel==0){
				this.fuelMax=0;
				this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			}
			//this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}else{
			if(this.canWork()){
				this.chargeFuel();
			}

		}

		if(this.isFuel() && this.canWork()){

			machineWorkProgressTime++;

			if(machineWorkProgressTime>=machineMaxProgressTime){
				this.workItem();
				machineWorkProgressTime=0;
			}

		}

		this.chargeFluid();
		if(this.on && this.tank.getFluidAmount() > 0)this.chargeUPFluid();

	}

	private void chargeUPFluid(){

		if(this.worldObj.getTileEntity(xCoord, yCoord + 1, zCoord) instanceof IFluidHandler){
			IFluidHandler f = (IFluidHandler) this.worldObj.getTileEntity(xCoord, yCoord + 1, zCoord);

			if(f.canFill(ForgeDirection.DOWN, this.tank.getFluid().getFluid())){
				int i = f.fill(ForgeDirection.DOWN, this.tank.getFluid(), true);
				this.tank.drain(i, true);
				this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			}

		}

	}

	private void chargeFluid(){

		if(canChargeFluid()){

			ItemStack empty = items.getStackInSlot(4);

			for(FluidContainerData f : FluidContainerRegistry.getRegisteredFluidContainerData()){

				if(f.emptyContainer.isItemEqual(empty) && f.fluid.isFluidEqual(this.tank.getFluid()) && f.fluid.amount <= this.tank.getFluidAmount()){

					ItemStack item = FluidContainerRegistry.fillFluidContainer(this.tank.getFluid(), empty);
					if (this.items.getStackInSlot(5) == null)
		            {
		                this.setInventorySlotContents(5, item.copy());
		            }
		            else if (this.items.getStackInSlot(5).isItemEqual(item))
		            {
		            	this.items.getStackInSlot(5).stackSize += item.stackSize;
		            }

					this.tank.drain(f.fluid.amount, true);

					this.items.reduceStackSize(4, 1);

					this.markDirty();

		            this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

					return ;
				}

			}

		}

	}

	public boolean canChargeFluid()
    {
		if(items.getStackInSlot(4) == null)return false;
		ItemStack item = FluidContainerRegistry.fillFluidContainer(this.tank.getFluid(), items.getStackInSlot(4));
		if(item == null)return false;
		if(this.items.getStackInSlot(5) == null)return true;
		int result = this.items.getStackInSlot(5).stackSize + item.stackSize;
        return (result <= getInventoryStackLimit() && result <= item.getMaxStackSize());
    }

	protected void chargeFuel(){

		if(TileEntityFurnace.isItemFuel(this.items.getStackInSlot(1))){
			this.fuel = this.fuelMax = TileEntityFurnace.getItemBurnTime(this.items.getStackInSlot(1));
			this.items.reduceStackSize(1, 1);
			this.markDirty();
			this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}

	}

	public boolean isFuel()
    {
        return this.fuel > 0;
    }

	public boolean canWork()
    {
        if (this.items.getStackInSlot(0) == null)
        {
            return false;
        }
        else
        {
            ItemStack itemstack = this.getResult(this.items.getStackInSlot(0));
            FluidStack fluidstack = this.getFluidResult(this.items.getStackInSlot(0));
            if (itemstack == null && fluidstack == null) return false;
            return this.checkItem(itemstack) && this.checkFluid(fluidstack) && this.checkContainerItem(this.items.getStackInSlot(0));
        }
    }

	private boolean checkItem(ItemStack itemstack){

		if (this.items.getStackInSlot(2) == null || itemstack == null) return true;
		if (!this.items.getStackInSlot(2).isItemEqual(itemstack)) return false;
		int result = this.items.getStackInSlot(2).stackSize + itemstack.stackSize;
        return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
	}

	private boolean checkContainerItem(ItemStack itemstack){

		if(!itemstack.getItem().hasContainerItem(itemstack)) return true;
		ItemStack itemstackC = itemstack.getItem().getContainerItem(itemstack.copy());
		if (this.items.getStackInSlot(3) == null || itemstackC == null) return true;
		if (!this.items.getStackInSlot(3).isItemEqual(itemstackC)) return false;
		int result = this.items.getStackInSlot(3).stackSize + itemstackC.stackSize;
        return (result <= getInventoryStackLimit() && result <= itemstackC.getMaxStackSize());

	}

	private boolean checkFluid(FluidStack fluidstack){

		if (this.getTank().getFluidAmount()==0 || fluidstack == null) return true;
		if (!this.getTank().getFluid().isFluidEqual(fluidstack)) return false;
		int result = this.getTank().getFluidAmount() + fluidstack.amount;
        return (result <= this.getTank().getCapacity());

	}

	public void workItem()
    {
        if (this.canWork())
        {
            ItemStack itemstack = this.getResult(this.items.getStackInSlot(0));
            FluidStack fluidstack = this.getFluidResult(this.items.getStackInSlot(0));

            //item
            if (this.items.getStackInSlot(2) == null)
            {
                this.setInventorySlotContents(2, itemstack.copy());
            }
            else if (this.items.getStackInSlot(2).isItemEqual(itemstack))
            {
            	this.items.getStackInSlot(2).stackSize += itemstack.stackSize;
            }

            //CItem
            if(this.items.getStackInSlot(0).getItem().hasContainerItem(this.items.getStackInSlot(0))){

            	ItemStack itemC = this.items.getStackInSlot(0).getItem().getContainerItem(this.items.getStackInSlot(0).copy());

            	if (this.items.getStackInSlot(3) == null)
                {
                    this.setInventorySlotContents(3, itemC);
                }
                else if (this.items.getStackInSlot(3).isItemEqual(itemC))
                {
                	this.items.getStackInSlot(3).stackSize += itemC.stackSize;
                }
            }


            //fluid
            this.getTank().fill(fluidstack, true);

            this.items.reduceStackSize(0, 1);

            this.markDirty();

            this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

        }
    }


	abstract protected ItemStack getResult(ItemStack stackInSlot);

	abstract protected FluidStack getFluidResult(ItemStack stackInSlot);


	//GUI
	public int getWorkProgressScaled(int par1)
    {
        return this.machineWorkProgressTime / (machineMaxProgressTime / par1);
    }

	public int getEnergyProgressScaled(int par1)
    {
        return (int) (this.fuel / (this.fuelMax / par1));
    }

	public FluidTank getTank() {
		return tank;
	}

	public boolean isFluid(){
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
	public void markDirty(){
		super.markDirty();
		items.onInventoryChanged();
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : entityplayer
				.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {

		if (i == 1) {
			return GearForceItem.manager.isGearForceItem(itemstack);
		}

		return i != 2;
	}

	//ISidedInventory関係
	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {
		return var1 == 0 ? slots_bottom : (var1 == 1 ? slots_top : slots_sides);
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j) {
		return this.isItemValidForSlot(i, itemstack);
	}

	@Override
	public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_)
    {
        return p_102008_3_ != 0 || p_102008_1_ != 1 || p_102008_2_.getItem() == Items.bucket;
    }

	//IFluidHandler関係
    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
    {
        return 0;//tank.fill(resource, doFill);
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
    {
        if (resource == null || !resource.isFluidEqual(getTank().getFluid()))
        {
            return null;
        }
        return getTank().drain(resource.amount, doDrain);
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
    {
        return getTank().drain(maxDrain, doDrain);
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid)
    {
        return false;
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid)
    {
        return true;
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from)
    {
        return new FluidTankInfo[] { getTank().getInfo() };
    }

	//NBT関係
	@Override
	public void readFromNBT(NBTTagCompound nbt) {

		super.readFromNBT(nbt);
		items.readFromNBT(nbt);
		getTank().readFromNBT(nbt);
		if(nbt.hasKey("Empty") && tank.getFluidAmount() > 0)this.tank.setFluid(null);
		this.machineWorkProgressTime = nbt.getShort("WorkTime");
		this.fuel = nbt.getInteger("fuel");
		this.fuelMax = nbt.getInteger("fuelMax");
		this.on = nbt.getBoolean("on");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {

		super.writeToNBT(nbt);
		items.writeToNBT(nbt);
		getTank().writeToNBT(nbt);
		nbt.setShort("WorkTime", (short)this.machineWorkProgressTime);
		nbt.setInteger("fuel", this.fuel);
		nbt.setInteger("fuelMax", this.fuelMax);
		nbt.setBoolean("on", this.on);
	}

	@Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt){
		this.readFromNBT(pkt.func_148857_g());
    	this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}

}
