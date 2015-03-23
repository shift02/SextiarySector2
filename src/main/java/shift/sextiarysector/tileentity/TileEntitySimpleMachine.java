package shift.sextiarysector.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.api.machine.energy.EnergyStorage;
import shift.sextiarysector.api.machine.energy.IGFEnergyHandler;
import shift.sextiarysector.api.machine.energy.IGearForceGrid;
import shift.sextiarysector.api.machine.item.GearForceItem;
import shift.sextiarysector.block.BlockSimpleMachine;
import shift.sextiarysector.container.ItemBox;

public class TileEntitySimpleMachine extends TileEntityDirection implements ISidedInventory, IGFEnergyHandler, IGearForceGrid {

	protected static final int[] slots_top = new int[] { 0 };
	protected static final int[] slots_bottom = new int[] { 2, 1 };
	protected static final int[] slots_sides = new int[] { 1 };

	public EnergyStorage storage = new EnergyStorage("Base", 1, 10000);

	//0 素材 ,1 Gear ,2 完成品
	ItemBox items = new ItemBox("Base", 3);

	//作業の進捗
	public int machineWorkProgressTime;

	//作業の進捗の最大値 この数字になると完了する 200Speed消費
	public int machineMaxProgressTime = 2000;

	//表示用
	public int inPower = 0, inSpeed = 0;

	public int lastSeed = 0;

	public boolean In;

	private int cooltime = 0;

	public TileEntitySimpleMachine() {
	}

	public TileEntitySimpleMachine(int type) {
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
		//System.out.println("updateServerEntity"+this.storage.getSpeedStored());

		if (this.inSpeed == 0) inPower = 0;
		if (lastSeed != this.storage.getSpeedStored()) {
			this.inSpeed = this.storage.getSpeedStored() - lastSeed;
			this.lastSeed = this.storage.getSpeedStored();
		} else {
			this.inSpeed = 0;
		}

		if (this.storage.getSpeedStored() <= 0) {
			this.storage.setPowerStored(0);
		}

		if (this.isCharging()) {
			this.updateChargeEntity();
		}

		if (cooltime <= 10) {
			this.cooltime++;
		} else {

			this.cooltime = 0;

			if (this.canWork())
			{
				if (this.storage.getSpeedStored() >= 1) {

					this.machineWorkProgressTime += this.storage.drawEnergy(this.storage.getMaxPower(), 100, false);

					if (this.machineWorkProgressTime >= machineMaxProgressTime)
					{
						this.machineWorkProgressTime = 0;
						this.workItem();

						if (this.storage.getSpeedStored() == 0) {
							this.storage.setPowerStored(0);
						}

						this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
						this.markDirty();

					}
				}

			}
			else
			{

				this.machineWorkProgressTime = 0;
			}

		}

	}

	public void updateChargeEntity()
	{
		if (this.items.getStackInSlot(1) == null) return;

		if (GearForceItem.manager.reduceEnergy(this.items.getStackInSlot(1), this.storage.getMaxPower(), 1, true) > 0) {
			int s = GearForceItem.manager.reduceEnergy(this.items.getStackInSlot(1), this.storage.getMaxPower(), 20, true);

			int i = this.storage.addEnergy(this.storage.getMaxPower(), s, false);
			GearForceItem.manager.reduceEnergy(this.items.getStackInSlot(1), this.storage.getMaxPower(), i, false);
			if (i > 0) this.inPower = this.storage.getMaxPower();
			//this.inSpeed += (int) i;

			this.markDirty();

		}
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
			if (itemstack == null) return false;
			if (this.items.getStackInSlot(2) == null) return true;
			if (!this.items.getStackInSlot(2).isItemEqual(itemstack)) return false;
			int result = this.items.getStackInSlot(2).stackSize + itemstack.stackSize;
			return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
		}
	}

	public void workItem()
	{
		if (this.canWork())
		{
			ItemStack itemstack = this.getResult(this.items.getStackInSlot(0));

			if (this.items.getStackInSlot(2) == null)
			{
				this.setInventorySlotContents(2, itemstack.copy());
			}
			else if (this.items.getStackInSlot(2).isItemEqual(itemstack))
			{
				this.items.getStackInSlot(2).stackSize += itemstack.stackSize;
			}

			this.items.reduceStackSize(0, 1);

		}
	}

	public ItemStack getResult(ItemStack itemstack) {
		return ((BlockSimpleMachine) this.getBlockType()).getResult(itemstack);
	}

	public boolean isWorking()
	{
		return this.machineWorkProgressTime > 0 && this.storage.getPowerStored() > 0;
	}

	public boolean isCharging()
	{

		boolean f1, f2, f3;

		if (this.items.getStackInSlot(1) == null) return false;

		f1 = this.storage.getMaxSpeed() > this.storage.getSpeedStored();
		f2 = (this.items.getStackInSlot(1) != null && GearForceItem.manager.isGearForceItem(this.items.getStackInSlot(1)));
		f3 = GearForceItem.manager.reduceEnergy(this.items.getStackInSlot(1), this.storage.getMaxPower(), 1, true) > 0;

		//System.out.println(f1+" "+f2+" "+f3);

		return f1 && f2 && f3;
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
	public String getInventoryName() {
		return "gui." + ((BlockSimpleMachine) this.blockType).getGUIUnlocalizedName();//+SimpleMachine.values()[this.worldObj.getBlockMetadata(xCoord, yCoord, zCoord)].icon+".name";
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

	//EnergyStorageの利用
	@Override
	public int addEnergy(ForgeDirection from, int power, int speed, boolean simulate) {

		if (!this.canInterface(from))
			return 0;

		int i = storage.addEnergy(power, speed, simulate);

		if (!simulate && i > 0) {
			this.inPower = power;
			//this.inSpeed+=i;
		}

		return i;

	}

	@Override
	public int drawEnergy(ForgeDirection from, int power, int speed, boolean simulate) {

		if (!this.canInterface(from))
			return 0;

		return 0;//storage.drawEnergy(power, speed, simulate);

	}

	@Override
	public boolean canInterface(ForgeDirection from) {

		return this.direction.ordinal() != from.ordinal();

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
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		return j != 0 || i != 1
				|| (GearForceItem.manager.isGearForceItem(itemstack) && !GearForceItem.manager.canUse(itemstack, 1));
	}

	public int getWorkProgressScaled(int par1)
	{
		return this.machineWorkProgressTime / (machineMaxProgressTime / par1);
	}

	public int getEnergyProgressScaled(int par1)
	{
		return this.storage.getSpeedStored() / (this.storage.getMaxSpeed() / par1);
	}

	//NBT関係
	@Override
	public void readFromNBT(NBTTagCompound nbt) {

		super.readFromNBT(nbt);
		storage.readFromNBT(nbt);
		items.readFromNBT(nbt);
		this.machineWorkProgressTime = nbt.getShort("WorkTime");
		this.inPower = nbt.getInteger("inPower");
		this.inSpeed = nbt.getInteger("inSpeed");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {

		super.writeToNBT(nbt);
		storage.writeToNBT(nbt);
		items.writeToNBT(nbt);
		nbt.setShort("WorkTime", (short) this.machineWorkProgressTime);
		nbt.setInteger("inPower", this.inPower);
		nbt.setInteger("inSpeed", this.inSpeed);
	}

	@Override
	public boolean canIn(ForgeDirection from) {
		return true;
	}

	@Override
	public boolean canOut(ForgeDirection from) {
		return false;
	}

}
