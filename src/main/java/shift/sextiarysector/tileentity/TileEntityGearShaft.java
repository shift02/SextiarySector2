package shift.sextiarysector.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.api.gearforce.tileentity.EnergyStorage;
import shift.sextiarysector.api.gearforce.tileentity.IGearForceHandler;
import shift.sextiarysector.api.gearforce.tileentity.IGearForceGrid;

public class TileEntityGearShaft extends TileEntityDirection implements IGearForceHandler, IGearForceGrid {

	public float rotateUpStep = 0;
	public float rotateDownStep = 0;
	private final EnergyStorage storage = new EnergyStorage("Base", 1, 320, 160);

	public TileEntityGearShaft() {

	}

	public TileEntityGearShaft(int i) {
		storage.setPowerCapacity(i);
	}

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
		this.rotateUpStep += 2;
		this.rotateDownStep += 8;
	}

	private void updateServerEntity() {

		if (!(this.getOutTileEntity() instanceof IGearForceHandler)) {
			return;
		}

		IGearForceHandler out = (IGearForceHandler) this.getOutTileEntity();

		int i = storage.drawEnergy(storage.getMaxPower(), storage.getMaxSpeed(), true);

		if (this.isUP()) {

			int add = (int) (i / 8.0f);
			int a = out.addEnergy(getDirection().getOpposite(), this.storage.getMaxPower() + 1, add, true);
			storage.drawEnergy(storage.getMaxPower(), a * 8, false);
			out.addEnergy(getDirection().getOpposite(), this.storage.getMaxPower() + 1, add, false);

		} else {

			int a = out.addEnergy(getDirection().getOpposite(), this.storage.getMaxPower(), i, true);
			storage.drawEnergy(storage.getMaxPower(), a, false);
			out.addEnergy(getDirection().getOpposite(), this.storage.getMaxPower(), i, false);

		}

	}

	private TileEntity getOutTileEntity() {
		return this.worldObj.getTileEntity(xCoord + this.direction.offsetX, yCoord + this.direction.offsetY, zCoord + this.direction.offsetZ);
	}

	private boolean isUP() {
		return this.getBlockMetadata() == 0;
	}

	// NBT関係
	@Override
	public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
		super.readFromNBT(par1nbtTagCompound);
		storage.readFromNBT(par1nbtTagCompound);
	}

	@Override
	public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
		super.writeToNBT(par1nbtTagCompound);
		storage.writeToNBT(par1nbtTagCompound);
	}

	@Override
	public int addEnergy(ForgeDirection from, int power, int speed, boolean simulate) {

		if (!this.canInterface(from)) return 0;

		if (power == this.storage.getMaxPower() && this.isUP()) {
			return this.storage.addEnergy(power, speed, simulate);
		} else if (power == this.storage.getMaxPower() + 1 && !this.isUP()) {
			return (int) (this.storage.addEnergy(power - 1, speed * 4, simulate) / 4.0f);
		}

		return 0;
	}

	@Override
	public int drawEnergy(ForgeDirection from, int power, int speed,
			boolean simulate) {
		return 0;
	}

	@Override
	public boolean canInterface(ForgeDirection from) {
		return this.direction.getOpposite().equals(from);
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
		return this.direction.getOpposite().ordinal() == from.ordinal();
	}

	@Override
	public boolean canOut(ForgeDirection from) {
		return this.direction.ordinal() == from.ordinal();
	}
}
