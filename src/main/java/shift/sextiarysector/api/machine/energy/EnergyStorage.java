package shift.sextiarysector.api.machine.energy;

import net.minecraft.nbt.NBTTagCompound;

/**
 * EnergyStorage {@link IEnergyStorage}の実装例。
 * @see IEnergyStorage
 * @version 1.0.0
 * @author Shift02
 */
public class EnergyStorage implements IEnergyStorage {

	protected String name;
	protected int powerEnergy;
	protected int speedEnergy;
	protected int powerCapacity;
	protected int speedCapacity;
	protected boolean isTransmission = false;
	protected int maxAdd;
	protected int maxDarw;

	public EnergyStorage(String name,int powerCapacity,int speedCapacity) {

		this(name,powerCapacity,speedCapacity, speedCapacity);
	}

	public EnergyStorage(String name,int powerCapacity,int speedCapacity, int maxTransfer) {

		this(name,powerCapacity,speedCapacity, maxTransfer, maxTransfer);
	}

	public EnergyStorage(String name,int powerCapacity,int speedCapacity, int maxAdd, int maxDarw) {

		this.name = name;
		this.powerCapacity = powerCapacity;
		this.speedCapacity = speedCapacity;
		this.maxAdd = maxAdd;
		this.maxDarw = maxDarw;
	}

	public EnergyStorage readFromNBT(NBTTagCompound nbt) {

		this.powerCapacity = nbt.getInteger(name+"PowerCapacity");

		this.powerEnergy = nbt.getInteger(name+"GearForcePower");
		this.speedEnergy = nbt.getInteger(name+"GearForceSpeed");

		if (powerEnergy > powerCapacity) {
			powerEnergy = powerCapacity;
		}
		if (speedEnergy > speedCapacity) {
			speedEnergy = speedCapacity;
		}

		this.isTransmission = nbt.getBoolean(name+"GearForceTransmission");

		return this;
	}

	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {

		if (powerEnergy < 0) {
			powerEnergy = 0;
		}
		if (speedEnergy < 0) {
			speedEnergy = 0;
		}

		nbt.setInteger(name+"PowerCapacity", this.powerCapacity);

		nbt.setInteger(name+"GearForcePower", powerEnergy);
		nbt.setInteger(name+"GearForceSpeed", speedEnergy);
		nbt.setBoolean(name+"GearForceTransmission", isTransmission);
		return nbt;
	}

	public void setCapacity(int capacity) {

		this.speedCapacity = capacity;

		if (speedEnergy > capacity) {
			speedEnergy = capacity;
		}
	}

	public void setMaxTransfer(int maxTransfer) {

		setMaxAdd(maxTransfer);
		setMaxDarw(maxTransfer);
	}

	public void setMaxAdd(int maxAdd) {

		this.maxAdd = maxAdd;
	}

	public void setMaxDarw(int maxDarw) {

		this.maxDarw = maxDarw;
	}

	public int getMaxAdd() {

		return maxAdd;
	}

	public int getMaxDarw() {

		return maxDarw;
	}

	public void setEnergyStored(int energy) {

		this.speedEnergy = energy;

		if (this.speedEnergy > speedCapacity)
		{
			this.speedEnergy = speedCapacity;
		}
		else if (this.speedEnergy < 0)
		{
			this.speedEnergy = 0;
		}

	}

	public void modifyEnergyStored(int energy) {

		this.speedEnergy += energy;

		if (this.speedEnergy > speedCapacity) {
			this.speedEnergy = speedCapacity;
		} else if (this.speedEnergy < 0) {
			this.speedEnergy = 0;
		}
	}

	@Override
	public int addEnergy(int maxPower, int maxSpeed, boolean simulate) {

		if(!this.isTransmissionGear()&&maxPower!=this.getMaxPowerStored()){
			return 0;
		}

		int addEnergy = Math.min(speedCapacity - this.speedEnergy, Math.min(this.maxAdd, maxSpeed));

		if (!simulate) {
			this.speedEnergy += addEnergy;
			this.powerEnergy = maxPower;
		}
		return addEnergy;

	}

	@Override
	public int drawEnergy(int maxPower, int maxSpeed, boolean simulate) {

		if(!this.isTransmissionGear()&&maxPower!=this.getMaxPowerStored()){
			return 0;
		}

		int drawEnergy = Math.min(this.speedEnergy, Math.min(this.maxDarw, maxSpeed));

		if (!simulate) {
			this.speedEnergy -= drawEnergy;
		}
		return drawEnergy;

	}

	public void setTransmissionGear(boolean transmission){
		this.isTransmission = transmission;
	}

	public void setPowerStored(int power){
		this.powerEnergy = power;
	}

	@Override
	public boolean isTransmissionGear() {

		return this.isTransmission;

	}

	@Override
	public int getPowerStored() {
		return powerEnergy;
	}

	@Override
	public int getSpeedStored() {
		return speedEnergy;
	}

	@Override
	public int getMaxPowerStored() {
		return powerCapacity;
	}

	@Override
	public int getMaxSpeedStored() {
		return speedCapacity;
	}

	public void setPowerCapacity(int powerCapacity) {
		this.powerCapacity = powerCapacity;
	}

}
