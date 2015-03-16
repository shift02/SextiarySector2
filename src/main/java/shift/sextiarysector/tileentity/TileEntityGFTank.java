package shift.sextiarysector.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.api.machine.energy.EnergyStorage;
import shift.sextiarysector.api.machine.energy.IGFEnergyHandler;
import shift.sextiarysector.api.machine.energy.IGearForceGrid;
import shift.sextiarysector.api.machine.item.GearForceItem;
import shift.sextiarysector.container.ItemBox;

public class TileEntityGFTank extends TileEntityDirection implements ISidedInventory, IGFEnergyHandler ,IGearForceGrid{

	protected static final int[] slots_top = new int[] { 0 };
	protected static final int[] slots_bottom = new int[] { 1 };
	protected static final int[] slots_sides = new int[] { 1 };

	public EnergyStorage storage = new EnergyStorage("Base", 1, 30000);

	//0  Out,1 In
	ItemBox items = new ItemBox("Base", 2);

	//表示用
	public int inPower = 0,inSpeed = 0;

	public int lastInSeed = 0;

	public int outPower = 0,outSpeed = 0;

	public int lastOutSeed = 0;

	public TileEntityGFTank(){

	}

	public TileEntityGFTank(int type) {
		this.storage.setPowerCapacity(type);
	}

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
		//System.out.println("updateClientEntity"+this.storage.getSpeedStored());
	}

	public void updateServerEntity()
	{

		if(this.inSpeed==0)inPower=0;
		if(lastInSeed!=this.storage.getSpeedStored()){
			this.inSpeed = this.storage.getSpeedStored()-lastInSeed;
			this.lastInSeed = this.storage.getSpeedStored();
			if(this.inSpeed<0)this.inSpeed=0;
		}else{
			this.inSpeed = 0;
		}

		if(this.storage.getSpeedStored()<=0){
			this.storage.setPowerStored(0);
		}

		if(this.outSpeed==0)this.outPower=0;
		outSpeed=0;

		//Item -> Block
		this.updateChargeEntity();

		//Block -> Item
		this.updateChargeItem();

		this.addOutEnergy();

	}

	public void updateChargeEntity()
	{

		if(this.items.getStackInSlot(1)==null)return;

		if(GearForceItem.manager.reduceEnergy(this.items.getStackInSlot(1), this.storage.getMaxPower(), 1, true)>0){
			int s = GearForceItem.manager.reduceEnergy(this.items.getStackInSlot(1), this.storage.getMaxPower(), 20, true);

			int i = this.storage.addEnergy(this.storage.getMaxPower(), s, false);
			GearForceItem.manager.reduceEnergy(this.items.getStackInSlot(1), this.storage.getMaxPower(), i, false);
			if(i>0)this.inPower = this.storage.getMaxPower();
			//this.inSpeed += (int) i;

			this.markDirty();

		}
	}

	public void updateChargeItem(){

		if(this.items.getStackInSlot(0)==null)return;

		if(this.storage.drawEnergy(this.storage.getMaxPower(), 1, true)>0&&this.items.getStackInSlot(0)!=null){

			int i = this.storage.drawEnergy(this.storage.getMaxPower(), 20, true);

			int s = GearForceItem.manager.addEnergy(this.items.getStackInSlot(0), this.storage.getMaxPower(), i, false);

			int j = this.storage.drawEnergy(this.storage.getMaxPower(), s, false);

			this.outSpeed+=j;
			if(j>0)this.outPower=this.storage.getMaxPower();

			//int i = this.storage.addEnergy(this.storage.getMaxPowerStored(), s, false);
			//if(i>0)this.inPower = this.storage.getMaxPowerStored();
			//this.inSpeed += (int) i;

			this.markDirty();

		}

	}

	public void addOutEnergy(){

		TileEntity t = this.worldObj.getTileEntity(xCoord + this.getDirection().offsetX, yCoord + this.getDirection().offsetY, zCoord + this.getDirection().offsetZ);

		if(t instanceof IGFEnergyHandler && ((IGFEnergyHandler)t).canInterface(getDirection().getOpposite())){

			int i = ((IGFEnergyHandler)t).addEnergy(getDirection().getOpposite(), this.storage.getMaxPower(), Math.min(160, this.storage.getSpeedStored()), true);

			int j = this.storage.drawEnergy(this.storage.getMaxPower(), i, false);

			((IGFEnergyHandler)t).addEnergy(getDirection().getOpposite(), this.storage.getMaxPower(), j, false);

			this.outSpeed+=j;
			if(j>0)this.outPower=this.storage.getMaxPower();

		}

	}

	//ISidedInventory
	@Override
	public int getSizeInventory() {
		return items.getSizeInventory();
	}

	@Override
	public ItemStack getStackInSlot(int p_70301_1_) {
		return items.getStackInSlot(p_70301_1_);
	}

	@Override
	public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_) {
		return items.decrStackSize(p_70298_1_, p_70298_2_);
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
		return items.getStackInSlotOnClosing(p_70304_1_);
	}

	@Override
	public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_) {
		items.setInventorySlotContents(p_70299_1_, p_70299_2_);
	}

	@Override
	public String getInventoryName() {
		return "gui.ss.gf_tank_"+this.storage.getMaxPower();
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
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;

	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
	}

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
		return p_94041_1_==0;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int par1) {
		return par1 == 0 ? slots_bottom : (par1 == 1 ? slots_top : slots_sides);
	}

	@Override
	public boolean canInsertItem(int par1, ItemStack par2ItemStack,int p_102007_3_) {
		return this.isItemValidForSlot(par1, par2ItemStack);
	}

	@Override//取り出し
	public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_,int p_102008_3_) {
		return true;
	}

	//GF
	@Override
	public int addEnergy(ForgeDirection from, int power, int speed,boolean simulate) {
		if(this.getDirection().ordinal()==from.ordinal())return 0;

		int i = storage.addEnergy(power, speed, simulate);
		if(i>0 && !simulate)inPower=power;

		return i;
	}

	@Override
	public int drawEnergy(ForgeDirection from, int power, int speed,boolean simulate) {
		if(this.getDirection().ordinal()!=from.ordinal())return 0;
		return storage.drawEnergy(power, speed, simulate);
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

	//gui
	public int getEnergyProgressScaled(int par1)
    {
        return (int) (this.storage.getSpeedStored() / ((float)this.storage.getMaxSpeed() / (float)par1));
    }

	//NBT関係
	@Override
	public void readFromNBT(NBTTagCompound nbt) {

		super.readFromNBT(nbt);
		storage.readFromNBT(nbt);
		items.readFromNBT(nbt);
		this.inPower = nbt.getInteger("inPower");
		this.inSpeed = nbt.getInteger("inSpeed");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {

		super.writeToNBT(nbt);
		storage.writeToNBT(nbt);
		items.writeToNBT(nbt);
		nbt.setInteger("inPower", this.inPower);
		nbt.setInteger("inSpeed", this.inSpeed);
	}

	@Override
	public boolean canIn(ForgeDirection from) {
		return this.getDirection().ordinal() != from.ordinal();
	}

	@Override
	public boolean canOut(ForgeDirection from) {
		return this.getDirection().ordinal() == from.ordinal();
	}

}
