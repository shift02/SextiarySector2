package shift.sextiarysector.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.api.machine.energy.EnergyStorage;
import shift.sextiarysector.api.machine.energy.IEnergyHandler;

public class TileEntityGearBox extends TileEntityDirection implements IEnergyHandler {

	public EnergyStorage storage = new EnergyStorage("Base", 1, 960, 160);

	public TileEntityGearBox() {
	}

	public TileEntityGearBox(int type) {
		this.storage.setPowerCapacity(type);
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

	}

	public void updateServerEntity() {

		if(this.getConnect()==0||storage.getSpeedStored()==0)return;

		int i = this.storage.getSpeedStored()/this.getConnect();

		for(ForgeDirection d:ForgeDirection.VALID_DIRECTIONS){

			if(d.ordinal()==this.getDirection().ordinal())continue;

			int x = this.xCoord + d.offsetX;
			int y = this.yCoord + d.offsetY;
			int z = this.zCoord + d.offsetZ;

			TileEntity t = this.worldObj.getTileEntity(x, y, z);

			if(t instanceof IEnergyHandler){

				int j = ((IEnergyHandler) t).addEnergy(d.getOpposite(), this.storage.getMaxPowerStored(), i, false);

				this.storage.drawEnergy(this.storage.getMaxPowerStored(), j, false);

			}

		}

	}

	public int getConnect(){

		int i = 0;

		for(ForgeDirection d:ForgeDirection.VALID_DIRECTIONS){

			if(d.ordinal()==this.getDirection().ordinal())continue;

			int x = this.xCoord + d.offsetX;
			int y = this.yCoord + d.offsetY;
			int z = this.zCoord + d.offsetZ;

			TileEntity t = this.worldObj.getTileEntity(x, y, z);

			if(t instanceof IEnergyHandler && ((IEnergyHandler) t).addEnergy(d.getOpposite(), this.storage.getMaxPowerStored(), 1, true)>0){
				i++;
			}

		}

		return i;

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

	// EnergyStorageの利用
	@Override
	public int addEnergy(ForgeDirection from, int power, int speed,boolean simulate) {

		if (this.getDirection().ordinal()!=from.ordinal())return 0;

		return storage.addEnergy(power, speed, simulate);

	}

	@Override
	public int drawEnergy(ForgeDirection from, int power, int speed,boolean simulate) {

		if (!this.canInterface(from))return 0;

		return 0;// storage.drawEnergy(power, speed, simulate);

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
	public long getSpeedStored(ForgeDirection from) {

		return storage.getSpeedStored();

	}

	@Override
	public int getMaxPowerStored(ForgeDirection from) {

		return storage.getMaxPowerStored();

	}

	@Override
	public long getMaxSpeedStored(ForgeDirection from) {

		return storage.getMaxSpeedStored();

	}

}
