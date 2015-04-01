package shift.sextiarysector.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.api.gearforce.tileentity.EnergyStorage;
import shift.sextiarysector.api.gearforce.tileentity.IGearForceHandler;
import shift.sextiarysector.api.gearforce.tileentity.IGearForceGrid;
import shift.sextiarysector.fmp.IShaft;

public class TileEntityShaft extends TileEntityDirection implements IGearForceHandler, IGearForceGrid, IShaft {

	public float rotateStep = 360;
	private final EnergyStorage storage = new EnergyStorage("Base", 1, 320, 160);
	// 表示用
	public int lastSpeed = 0;
	private final int cooltime = 0;

	public TileEntityShaft() {

	}

	public TileEntityShaft(int i) {
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

		if (t instanceof IShaft && ((IShaft) t).getDirection().ordinal() == this.direction.ordinal()) {
			return;
		}

		/*
		 * if(this.rotateStep>=360){ this.rotateStep-=360; }
		 */

		this.rotateStep += this.lastSpeed / 10.0f;

		t = this.worldObj.getTileEntity(xCoord + this.getOutDirection().offsetX, yCoord + this.getOutDirection().offsetY, zCoord + this.getOutDirection().offsetZ);

		for (int i = 2; t instanceof IShaft && ((IShaft) t).getDirection().ordinal() == this.direction.ordinal(); i++) {
			// System.out.println("b");
			((IShaft) t).setRotateStep(this.rotateStep);

			//if(this.worldObj.rand.nextInt(30)==1)this.worldObj.spawnParticle("reddust", t.xCoord+0.5f, t.yCoord+0.5f, t.zCoord+0.5f, -0.3D, 0.0D, 1.0D);

			t = this.worldObj.getTileEntity(xCoord + this.getOutDirection().offsetX * i, yCoord + this.getOutDirection().offsetY * i, zCoord + this.getOutDirection().offsetZ * i);
		}

	}

	public void updateServerEntity() {

		if (this.getStorage().getSpeedStored() != lastSpeed) {
			lastSpeed = (this.getStorage().getSpeedStored());
			// PacketDispatcher.sendPacketToAllPlayers(this.getDescriptionPacket());
			this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

		}

		if (this.getStorage().getSpeedStored() > 0) {
			this.getStorage().drawEnergy(this.getStorage().getMaxPower(), 10, false);
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

		TileEntity in = (TileEntity) this.getInTileEntityShaft();
		TileEntity out = (TileEntity) this.getOutTileEntityShaft();

		int x = Math.abs(in.xCoord - out.yCoord);
		int y = Math.abs(in.yCoord - out.yCoord);
		int z = Math.abs(in.zCoord - out.zCoord);

		return x + y + z;

	}

	@Override
	public IShaft getInTileEntityShaft() {

		if (this.isInShaft()) {
			return this;
		} else {
			return ((IShaft) this.getInTileEntity()).getInTileEntityShaft();
		}

	}

	@Override
	public IShaft getOutTileEntityShaft() {

		if (this.isOutShaft()) {
			return this;
		} else {
			return ((IShaft) this.getOutTileEntity()).getOutTileEntityShaft();
		}

	}

	public boolean isInShaft() {
		return !(this.getInTileEntity() instanceof IShaft) || ((IShaft) this.getInTileEntity()).getDirection().ordinal() != this.getDirection().ordinal();
	}

	public boolean isOutShaft() {
		return !(this.getOutTileEntity() instanceof IShaft) || ((IShaft) this.getOutTileEntity()).getDirection().ordinal() != this.getDirection().ordinal();
	}

	public TileEntity getInTileEntity() {
		return this.worldObj.getTileEntity(xCoord + this.getInDirection().offsetX, yCoord + this.getInDirection().offsetY, zCoord + this.getInDirection().offsetZ);
	}

	public TileEntity getOutTileEntity() {
		return this.worldObj.getTileEntity(xCoord + this.getOutDirection().offsetX, yCoord + this.getOutDirection().offsetY, zCoord + this.getOutDirection().offsetZ);
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

	@Override
	public float getRotateStep() {
		return rotateStep / 2.0f;
	}

	@Override
	public void setRotateStep(float r) {
		this.rotateStep = r;
	}

	@Override
	public ForgeDirection getDirection() {
		return direction;
	}

	@Override
	public void setDirection(ForgeDirection d) {
		direction = d;
	}

	@Override
	public EnergyStorage getStorage() {

		//if(storage==null){
		//	storage = new EnergyStorage("base", ((BlockShaft)this.getBlockType()).getType(), 320, 160);
		//}

		return storage;
	}

	// EnergyStorageの利用
	@Override
	public int addEnergy(ForgeDirection from, int power, int speed, boolean simulate) {

		if (this.getInDirection().ordinal() != from.ordinal())
			return 0;

		if (!(this.getOutTileEntity() instanceof IGearForceHandler) || power != this.getStorage().getMaxPower())
			return 0;

		int i = ((IGearForceHandler) this.getOutTileEntity()).addEnergy(from, power, speed, simulate);

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
	public int getSpeedStored(ForgeDirection from) {

		return this.getStorage().getSpeedStored();

	}

	@Override
	public int getMaxPowerStored(ForgeDirection from) {

		return this.getStorage().getMaxPower();

	}

	@Override
	public int getMaxSpeedStored(ForgeDirection from) {

		return this.getStorage().getMaxSpeed();

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
