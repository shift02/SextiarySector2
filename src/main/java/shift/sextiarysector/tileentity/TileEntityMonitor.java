package shift.sextiarysector.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.api.machine.energy.EnergyStorage;
import shift.sextiarysector.api.machine.energy.IEnergyHandler;
import shift.sextiarysector.block.BlockMonitor.MonitorType;

public class TileEntityMonitor extends TileEntityDirection implements IEnergyHandler{

	public EnergyStorage storage = new EnergyStorage("Base", 1, 10000);
	public MonitorType type = MonitorType.unknown;

	public boolean on;

	@Override
	public void updateEntity() {


		if(this.storage.getSpeedStored()>0&&on){
			this.storage.drawEnergy(1, 20, false);

			if(this.storage.getSpeedStored()==0){
				this.changeON();
			}

		}

	}

	public void changeON(){

		if(!this.worldObj.isRemote){
			if(!this.on&&this.storage.getSpeedStored()>0){
				this.on=true;
			}else{
				this.on=false;;
			}
			this.worldObj.playSoundEffect((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D, "random.click", 0.3F, 0.6F);
		}

		this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}


	//NBT関係
	@Override
	public void readFromNBT(NBTTagCompound nbt) {

		super.readFromNBT(nbt);
		storage.readFromNBT(nbt);
		this.type = MonitorType.values()[nbt.getInteger("mtype")];
		this.on = nbt.getBoolean("on");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {

		super.writeToNBT(nbt);
		storage.writeToNBT(nbt);
		nbt.setInteger("mtype", this.type.ordinal());
		nbt.setBoolean("on", on);
	}

	@Override
	public int addEnergy(ForgeDirection from, int power, int speed,boolean simulate) {
		if(!this.canInterface(from))return 0;
		int i =this.storage.addEnergy(power, speed, simulate);
		return i;
	}

	@Override
	public int drawEnergy(ForgeDirection from, int power, int speed,boolean simulate) {
		if(!this.canInterface(from))return 0;
		return 0;
	}

	@Override
	public boolean canInterface(ForgeDirection from) {
		return ForgeDirection.DOWN.equals(from);
	}

	@Override
	public int getPowerStored(ForgeDirection from) {
		if(!this.canInterface(from))return 0;
		return 0;
	}

	@Override
	public long getSpeedStored(ForgeDirection from) {
		if(!this.canInterface(from))return 0;
		return 0;
	}

	@Override
	public int getMaxPowerStored(ForgeDirection from) {
		if(!this.canInterface(from))return 0;
		return this.storage.getMaxPowerStored();
	}

	@Override
	public long getMaxSpeedStored(ForgeDirection from) {
		if(!this.canInterface(from))return 0;
		return this.storage.getMaxSpeedStored();
	}

}
