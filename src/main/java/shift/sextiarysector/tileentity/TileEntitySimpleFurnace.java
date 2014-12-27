package shift.sextiarysector.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import shift.sextiarysector.container.ItemBox;

public abstract class TileEntitySimpleFurnace extends TileEntityDirection implements ISidedInventory{

	protected static final int[] slots_top = new int[] { 0 };
	protected static final int[] slots_bottom = new int[] { 2, 1 };
	protected static final int[] slots_sides = new int[] { 1 };

	//0 素材 ,1 燃料 ,2 完成品
	//protected ItemBox items = new ItemBox("Base", 3);

	//作業の進捗
	public int machineWorkProgressTime;

	//作業の進捗の最大値 この数字になると完了する
	public int machineMaxProgressTime = 200;

	//燃料と投入されている燃料のマックス状態の量
	public int fuel;
	public int fuelMax;

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

	}


	protected void chargeFuel(){

		if(this.isItemFuel(this.getFuelItem() )){
			this.fuel = this.fuelMax = this.getItemFuelTime(this.getFuelItem());

			if(this.getItems().getStackInSlot(getFuelItemSlot()).stackSize == 1){
				this.getItems().setInventorySlotContents(1, this.getItems().getStackInSlot(getFuelItemSlot()).getItem().getContainerItem(this.getItems().getStackInSlot(getFuelItemSlot())));
			}else{
				this.reduceFuelStackSize();
			}

			//this.reduceFuelStackSize();
			this.markDirty();
			this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}

	}

	abstract protected ItemStack getFuelItem();

	abstract protected int getFuelItemSlot();

	abstract protected void reduceFuelStackSize();

	public boolean isFuel()
    {
        return this.fuel > 0;
    }

	public boolean canWork()
    {
        if (this.getMaterialItem() == null)
        {
            return false;
        }
        else
        {
            ItemStack itemstack = this.getResult(this.getMaterialItem());
            if (itemstack == null) return false;
            return this.checkItem(itemstack) ;
        }
    }

	protected boolean checkItem(ItemStack itemstack){

		if (this.getItems().getStackInSlot(getResultItemSlot()) == null || itemstack == null) return true;
		if (!this.getItems().getStackInSlot(getResultItemSlot()).isItemEqual(itemstack)) return false;
		int result = this.getItems().getStackInSlot(getResultItemSlot()).stackSize + itemstack.stackSize;
        return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
	}

	public void workItem()
    {
        if (this.canWork())
        {
            ItemStack itemstack = this.getResult(this.getMaterialItem());

            //item
            if (this.getItems().getStackInSlot(getResultItemSlot()) == null)
            {
                this.setInventorySlotContents(getResultItemSlot(), itemstack.copy());
            }
            else if (this.getItems().getStackInSlot(getResultItemSlot()).isItemEqual(itemstack))
            {
            	this.getItems().getStackInSlot(getResultItemSlot()).stackSize += itemstack.stackSize;
            }

            this.reduceMaterialStackSize();

            this.markDirty();

            this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

        }
    }

	abstract protected ItemStack getMaterialItem();

	abstract protected int getResultItemSlot();

	abstract protected void reduceMaterialStackSize();

	abstract protected ItemStack getResult(ItemStack stackInSlot);

	abstract public int getItemFuelTime(ItemStack p_145952_0_);

    public boolean isItemFuel(ItemStack p_145954_0_)
    {
        return getItemFuelTime(p_145954_0_) > 0;
    }

    abstract protected ItemBox getItems();

	//GUI
	public int getWorkProgressScaled(int par1)
    {
        return this.machineWorkProgressTime / (machineMaxProgressTime / par1);
    }

	public int getEnergyProgressScaled(int par1)
    {
        return (int) (this.fuel / (this.fuelMax / par1));
    }


	//IInventory関係
	@Override
	public int getSizeInventory() {
		return getItems().getSizeInventory();
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return getItems().getStackInSlot(i);
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		return getItems().decrStackSize(i, j);
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		return getItems().getStackInSlotOnClosing(i);
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		getItems().setInventorySlotContents(i, itemstack);
	}

	@Override
	public String getInventoryName() {
		return "gui.ss.magic_furnace";//+SimpleMachine.values()[this.worldObj.getBlockMetadata(xCoord, yCoord, zCoord)].icon+".name";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return getItems().getInventoryStackLimit();
	}

	@Override
	public void markDirty(){
		super.markDirty();
		getItems().onInventoryChanged();
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

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {

		if (i == 1) {
			return this.getItemFuelTime(itemstack) > 0;
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

	//NBT関係
	@Override
	public void readFromNBT(NBTTagCompound nbt) {

		super.readFromNBT(nbt);
		getItems().readFromNBT(nbt);
		this.machineWorkProgressTime = nbt.getShort("WorkTime");
		this.fuel = nbt.getInteger("fuel");
		this.fuelMax = nbt.getInteger("fuelMax");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {

		super.writeToNBT(nbt);
		getItems().writeToNBT(nbt);
		nbt.setShort("WorkTime", (short)this.machineWorkProgressTime);
		nbt.setInteger("fuel", this.fuel);
		nbt.setInteger("fuelMax", this.fuelMax);
	}

	@Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt){
		this.readFromNBT(pkt.func_148857_g());
    	this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}


}
