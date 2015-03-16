package shift.sextiarysector.api.machine.energy;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEnergyHandler extends TileEntity implements IGFEnergyHandler {

	EnergyStorage storage = new EnergyStorage("Base",1,1000);


	//EnergyStorageの利用
	@Override
	public int addEnergy(ForgeDirection from, int power, int speed, boolean simulate) {

		return storage.addEnergy(power, speed, simulate);

	}

	@Override
	public int drawEnergy(ForgeDirection from, int power, int speed, boolean simulate) {

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


	//NBT関係
	@Override
	public void readFromNBT(NBTTagCompound nbt) {

		super.readFromNBT(nbt);
		storage.readFromNBT(nbt);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {

		super.writeToNBT(nbt);
		storage.writeToNBT(nbt);
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbt);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt){
		this.readFromNBT(pkt.func_148857_g());
	}

}
