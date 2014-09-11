package shift.sextiarysector.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.api.machine.energy.EnergyStorage;
import shift.sextiarysector.api.machine.energy.IEnergyHandler;

public class TileEntityShaft extends TileEntityDirection implements IEnergyHandler {

	public float rotateStep = 360;
	public EnergyStorage storage = new EnergyStorage("Base", 1, 320, 160);
	// 表示用
	public int lastSpeed = 0;
	private final int cooltime = 0;

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

		TileEntity t = this.worldObj.getTileEntity(
				xCoord + this.getInDirection().offsetX,
				yCoord + this.getInDirection().offsetY,
				zCoord + this.getInDirection().offsetZ);

		if (t instanceof TileEntityShaft
				&& ((TileEntityShaft) t).getDirection().ordinal() == this.direction
						.ordinal()) {
			return;
		}

		/*
		 * if(this.rotateStep>=360){ this.rotateStep-=360; }
		 */

		this.rotateStep += (float)this.lastSpeed/10.0f;

		t = this.worldObj.getTileEntity(
				xCoord + this.getOutDirection().offsetX,
				yCoord + this.getOutDirection().offsetY,
				zCoord + this.getOutDirection().offsetZ);

		for (int i = 2; t instanceof TileEntityShaft
				&& ((TileEntityShaft) t).getDirection().ordinal() == this.direction
						.ordinal(); i++) {
			// System.out.println("b");
			((TileEntityShaft) t).rotateStep = this.rotateStep;
			t = this.worldObj.getTileEntity(xCoord
					+ this.getOutDirection().offsetX * i,
					yCoord + this.getOutDirection().offsetY * i,
					zCoord + this.getOutDirection().offsetZ * i);
		}

	}

	public void updateServerEntity() {

		if (this.storage.getSpeedStored() != lastSpeed) {
			lastSpeed = (int) (this.storage.getSpeedStored());
			// PacketDispatcher.sendPacketToAllPlayers(this.getDescriptionPacket());
			this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

		}

		if (storage.getSpeedStored() > 0) {
			storage.drawEnergy(storage.getMaxPowerStored(), 10, false);
		}

		/*
		 * if(cooltime<=2){ this.cooltime++; }else {
		 *
		 * cooltime=0;
		 */

		/*
		 * if(storage.getSpeedStored()>0){
		 *
		 *
		 * TileEntity t =this.getOutTileEntity(); if(t!=null && t instanceof
		 * IEnergyHandler){
		 *
		 * int d =((IEnergyHandler)t).addEnergy(this.getInDirection(),
		 * 1,Math.min(160, this.storage.getSpeedStored()) , true);
		 *
		 * ((IEnergyHandler)t).addEnergy(this.getInDirection(), 1,d , false);
		 *
		 * this.storage.drawEnergy(1, d, false);
		 *
		 * } }
		 */

		// }

	}

	public int getShaftLength() {

		TileEntityShaft in = this.getInTileEntityShaft();
		TileEntityShaft out = this.getOutTileEntityShaft();

		int x = Math.abs(in.xCoord - out.yCoord);
		int y = Math.abs(in.yCoord - out.yCoord);
		int z = Math.abs(in.zCoord - out.zCoord);

		return x + y + z;

	}

	public TileEntityShaft getInTileEntityShaft() {

		if (this.isInShaft()) {
			return this;
		} else {
			return ((TileEntityShaft) this.getInTileEntity())
					.getInTileEntityShaft();
		}

	}

	public TileEntityShaft getOutTileEntityShaft() {

		if (this.isOutShaft()) {
			return this;
		} else {
			return ((TileEntityShaft) this.getOutTileEntity()).getOutTileEntityShaft();
		}

	}

	public boolean isInShaft() {
		return !(this.getInTileEntity() instanceof TileEntityShaft)|| ((TileEntityShaft) this.getInTileEntity()).getDirection().ordinal() != this.getDirection().ordinal();
	}

	public boolean isOutShaft() {
		return !(this.getOutTileEntity() instanceof TileEntityShaft)|| ((TileEntityShaft) this.getOutTileEntity()).getDirection().ordinal() != this.getDirection().ordinal();
	}

	public TileEntity getInTileEntity() {
		return this.worldObj.getTileEntity(xCoord+ this.getInDirection().offsetX, yCoord+ this.getInDirection().offsetY, zCoord+ this.getInDirection().offsetZ);
	}

	public TileEntity getOutTileEntity() {
		return this.worldObj.getTileEntity(xCoord+ this.getOutDirection().offsetX,yCoord + this.getOutDirection().offsetY,zCoord + this.getOutDirection().offsetZ);
	}

	public ForgeDirection getInDirection() {
		return direction.getOpposite();
	}

	public ForgeDirection getOutDirection() {
		return direction;
	}

	// NBT関係
	@Override
	public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
		super.readFromNBT(par1nbtTagCompound);
		storage.readFromNBT(par1nbtTagCompound);
		this.lastSpeed = par1nbtTagCompound.getInteger("lastSpeed");
	}

	@Override
	public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
		super.writeToNBT(par1nbtTagCompound);
		storage.writeToNBT(par1nbtTagCompound);
		par1nbtTagCompound.setInteger("lastSpeed", lastSpeed);
	}

	public float getRotateStep() {
		return rotateStep / 2.0f;
	}

	@Override
	public ForgeDirection getDirection() {
		return direction;
	}

	public void setDirection(ForgeDirection d) {
		direction = d;
	}

	// EnergyStorageの利用
	@Override
	public int addEnergy(ForgeDirection from, int power, int speed,
			boolean simulate) {

		if (this.getInDirection().ordinal() != from.ordinal())
			return 0;

		if (!(this.getOutTileEntity() instanceof IEnergyHandler))
			return 0;

		int i = ((IEnergyHandler) this.getOutTileEntity()).addEnergy(from,
				power, speed, simulate);

		// storage.addEnergy(power, speed, simulate);
		if (!simulate)
			storage.setEnergyStored(i);

		return i;

		// return storage.addEnergy(power, speed, simulate);

	}

	@Override
	public int drawEnergy(ForgeDirection from, int power, int speed,
			boolean simulate) {

		if (!this.canInterface(from))
			return 0;

		return 0;// storage.drawEnergy(power, speed, simulate);

	}

	@Override
	public boolean canInterface(ForgeDirection from) {

		return this.getInDirection().ordinal() == from.ordinal()
				|| this.getOutDirection().ordinal() == from.ordinal();

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
