package shift.sextiarysector.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.api.machine.energy.EnergyStorage;
import shift.sextiarysector.api.machine.energy.IEnergyHandler;

public class TileEntityGearShaft extends TileEntityDirection implements IEnergyHandler {

	public float rotateUpStep = 0;
	public float rotateDownStep = 0;
	private EnergyStorage storage = new EnergyStorage("Base", 1, 320, 160);

	public TileEntityGearShaft(){

	}

	public TileEntityGearShaft(int i){
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
		this.rotateUpStep+=2;
		this.rotateDownStep+=8;
	}

	private void updateServerEntity() {
		// TODO 自動生成されたメソッド・スタブ

	}

	// NBT関係
		@Override
		public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
			super.readFromNBT(par1nbtTagCompound);
			storage.readFromNBT(par1nbtTagCompound);
			//this.lastSpeed = par1nbtTagCompound.getInteger("lastSpeed");
		}

		@Override
		public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
			super.writeToNBT(par1nbtTagCompound);
			storage.writeToNBT(par1nbtTagCompound);
			//par1nbtTagCompound.setInteger("lastSpeed", lastSpeed);
		}

		@Override
		public int addEnergy(ForgeDirection from, int power, int speed,
				boolean simulate) {
			// TODO 自動生成されたメソッド・スタブ
			return 0;
		}

		@Override
		public int drawEnergy(ForgeDirection from, int power, int speed,
				boolean simulate) {
			// TODO 自動生成されたメソッド・スタブ
			return 0;
		}

		@Override
		public boolean canInterface(ForgeDirection from) {
			// TODO 自動生成されたメソッド・スタブ
			return false;
		}

		@Override
		public int getPowerStored(ForgeDirection from) {
			// TODO 自動生成されたメソッド・スタブ
			return 0;
		}

		@Override
		public long getSpeedStored(ForgeDirection from) {
			// TODO 自動生成されたメソッド・スタブ
			return 0;
		}

		@Override
		public int getMaxPowerStored(ForgeDirection from) {
			// TODO 自動生成されたメソッド・スタブ
			return 0;
		}

		@Override
		public long getMaxSpeedStored(ForgeDirection from) {
			// TODO 自動生成されたメソッド・スタブ
			return 0;
		}
}
