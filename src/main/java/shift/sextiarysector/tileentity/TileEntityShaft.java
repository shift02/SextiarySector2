package shift.sextiarysector.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.api.machine.energy.EnergyStorage;
import shift.sextiarysector.api.machine.energy.IEnergyHandler;
import shift.sextiarysector.api.machine.energy.IGearForceGrid;

public class TileEntityShaft extends TileEntityDirection implements IEnergyHandler  ,IGearForceGrid{

	public float rotateStep = 360;
	private EnergyStorage storage = new EnergyStorage("Base", 1, 320, 160);
	// 表示用
	public int lastSpeed = 0;
	private final int cooltime = 0;

	public TileEntityShaft(){

	}

	public TileEntityShaft(int i){
		this.getStorage().setPowerCapacity(i);
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

		TileEntity t = this.worldObj.getTileEntity(xCoord + this.getInDirection().offsetX,
				yCoord + this.getInDirection().offsetY,
				zCoord + this.getInDirection().offsetZ);

		if (t instanceof TileEntityShaft&& ((TileEntityShaft) t).getDirection().ordinal() == this.direction.ordinal()) {
			return;
		}

		/*
		 * if(this.rotateStep>=360){ this.rotateStep-=360; }
		 */

		this.rotateStep += (float)this.lastSpeed/10.0f;

		t = this.worldObj.getTileEntity(xCoord + this.getOutDirection().offsetX,yCoord + this.getOutDirection().offsetY,zCoord + this.getOutDirection().offsetZ);

		for (int i = 2; t instanceof TileEntityShaft&& ((TileEntityShaft) t).getDirection().ordinal() == this.direction.ordinal(); i++) {
			// System.out.println("b");
			((TileEntityShaft) t).rotateStep = this.rotateStep;

			//if(this.worldObj.rand.nextInt(30)==1)this.worldObj.spawnParticle("reddust", t.xCoord+0.5f, t.yCoord+0.5f, t.zCoord+0.5f, -0.3D, 0.0D, 1.0D);

			t = this.worldObj.getTileEntity(xCoord+ this.getOutDirection().offsetX * i,yCoord + this.getOutDirection().offsetY * i,zCoord + this.getOutDirection().offsetZ * i);
		}

	}

	public void updateServerEntity() {

		if (this.getStorage().getSpeedStored() != lastSpeed) {
			lastSpeed = (int) (this.getStorage().getSpeedStored());
			// PacketDispatcher.sendPacketToAllPlayers(this.getDescriptionPacket());
			this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

		}

		if (this.getStorage().getSpeedStored() > 0) {
			this.getStorage().drawEnergy(this.getStorage().getMaxPowerStored(), 10, false);
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
		this.getStorage().readFromNBT(par1nbtTagCompound);
		this.lastSpeed = par1nbtTagCompound.getInteger("lastSpeed");
	}

	@Override
	public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
		super.writeToNBT(par1nbtTagCompound);
		this.getStorage().writeToNBT(par1nbtTagCompound);
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

	public EnergyStorage getStorage() {

		//if(storage==null){
		//	storage = new EnergyStorage("base", ((BlockShaft)this.getBlockType()).getType(), 320, 160);
		//}

		return storage;
	}

	// EnergyStorageの利用
	@Override
	public int addEnergy(ForgeDirection from, int power, int speed,boolean simulate) {

		if (this.getInDirection().ordinal() != from.ordinal())
			return 0;

		if (!(this.getOutTileEntity() instanceof IEnergyHandler) || power != this.getStorage().getMaxPowerStored())
			return 0;

		int i = ((IEnergyHandler) this.getOutTileEntity()).addEnergy(from,power, speed, simulate);

		// storage.addEnergy(power, speed, simulate);
		if (!simulate)
			this.getStorage().setEnergyStored(i);

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

		return this.getStorage().getPowerStored();

	}

	@Override
	public long getSpeedStored(ForgeDirection from) {

		return this.getStorage().getSpeedStored();

	}

	@Override
	public int getMaxPowerStored(ForgeDirection from) {

		return this.getStorage().getMaxPowerStored();

	}

	@Override
	public long getMaxSpeedStored(ForgeDirection from) {

		return this.getStorage().getMaxSpeedStored();

	}

	@Override
	public boolean canIn(ForgeDirection from) {
		return this.getInDirection().ordinal() == from.ordinal();
	}

	@Override
	public boolean canOut(ForgeDirection from) {
		return this.getOutDirection().ordinal() == from.ordinal();
	}

}
