package shift.sextiarysector.tileentity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.api.machine.energy.EnergyStorage;
import shift.sextiarysector.api.machine.energy.IEnergyHandler;
import shift.sextiarysector.api.machine.energy.IGearForceGrid;

public class TileEntityFan extends TileEntityDirection  implements IEnergyHandler ,IGearForceGrid{

	public float rotateStep = 360;
	private int speed = 0;
	private int time = 0;

	public EnergyStorage storage = new EnergyStorage("Base", 3, 3000);

	@Override
	public void updateEntity() {

		super.updateEntity();

		if (this.worldObj.isRemote) {
			this.updateClientEntity();
		} else {
			this.updateServerEntity();
		}

		if(this.canWork()){
			this.workFan();
			this.addFire();
		}

	}

	public void updateClientEntity() {

		this.rotateStep += speed;

	}

	private void updateServerEntity() {

		if(time>=10){
			this.changeSpeed();
			time=0;
		}else{
			time++;
		}

	}

	private void addFire(){

		for(int i=1; i<6; i++){

			int x = this.xCoord + this.direction.offsetX * i;
			int y = this.yCoord + this.direction.offsetY * i;
			int z = this.zCoord + this.direction.offsetZ * i;

			if(this.worldObj.getBlock(x, y, z) != Blocks.fire)continue;

			this.worldObj.setBlock(x, y, z, SSBlocks.blueFire);

		}

	}

	private void changeSpeed(){

		int use = this.storage.drawEnergy(3, 200, false);

		int i = this.speed;

		if(use>=180){
			speed = Math.min(16, speed + 1);
		}else{
			speed = Math.max(0, speed - 1);
		}

		if(i!=speed){
			this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}

	}

	private boolean canWork(){

		return speed >= 14;

	}

	private void workFan(){

		AxisAlignedBB aabb = getDirectionAABB();

		List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)null, aabb, null);

		for(Entity e :list){
			if(e instanceof EntityPlayer)continue;
			e.motionX+=(this.direction.offsetX/10.0f);
			e.motionY+=(this.direction.offsetY/10.0f);
			e.motionZ+=(this.direction.offsetZ/10.0f);
		}

	}

	private AxisAlignedBB getDirectionAABB(){

		int minX = 0, minY = 0, minZ = 0;
		int maxX = 1, maxY = 1, maxZ = 1;

		switch(direction){
		case DOWN: minY = -5; break;
		case UP: maxY = 6; break;
		case NORTH: minZ = -5; break;
		case SOUTH: maxZ = 6; break;
		case WEST: minX = -5; break;
		case EAST: maxX = 6; break;
		default:break;
		}

		return AxisAlignedBB.getBoundingBox(this.xCoord + minX, this.yCoord + minY, this.zCoord + minZ, this.xCoord + maxX, this.yCoord + maxY, this.zCoord + maxZ);

	}

	public float getRotateStep() {
		return rotateStep;
	}

	@Override
	public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
		super.readFromNBT(par1nbtTagCompound);
		this.speed = par1nbtTagCompound.getInteger("speed");
	}

	@Override
	public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
		super.writeToNBT(par1nbtTagCompound);
		par1nbtTagCompound.setInteger("speed", this.speed);
	}

	@Override
	public int addEnergy(ForgeDirection from, int power, int speed,boolean simulate) {

		if(!canInterface(from))return 0;

		int i = storage.addEnergy(power, speed, simulate);

		return i;
	}

	@Override
	public int drawEnergy(ForgeDirection from, int power, int speed,boolean simulate) {

		return 0;

	}

	@Override
	public boolean canInterface(ForgeDirection from) {
		return this.getDirection().getOpposite().ordinal() == from.ordinal();
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

	@Override
	public boolean canIn(ForgeDirection from) {
		return this.getDirection().getOpposite().ordinal() == from.ordinal();
	}

	@Override
	public boolean canOut(ForgeDirection from) {
		return false;
	}

}
