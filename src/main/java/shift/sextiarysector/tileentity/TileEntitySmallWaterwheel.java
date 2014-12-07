package shift.sextiarysector.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.api.machine.energy.IEnergyHandler;

public class TileEntitySmallWaterwheel extends TileEntityDirection{

	public float rotateStep = 0;


	@Override
	public void updateEntity()
	{
		super.updateEntity();
		if (this.worldObj.isRemote) {
			this.updateClientEntity();
		} else {

			this.updateServerEntity();

		}

	}

	public void updateClientEntity()
	{
		if(!this.isWork()){
			return;
		}

		if(this.rotateStep>360){
			this.rotateStep-=360;
		}

		this.rotateStep+=2;

	}

	public void updateServerEntity()
	{
		TileEntity t =this.worldObj.getTileEntity(xCoord-this.direction.offsetX, yCoord-this.direction.offsetY, zCoord-this.direction.offsetZ);
		if(t!=null && t instanceof IEnergyHandler && this.isWork()){

			((IEnergyHandler)t).addEnergy(this.direction, 3, 20, false);
		}

	}

	public float getRotateStep() {
		return -rotateStep;
	}

	boolean isWork(){

		ForgeDirection d1 = this.getDirection().getRotation(ForgeDirection.UP);
		ForgeDirection d2 = this.getDirection().getRotation(ForgeDirection.UP).getOpposite();

		Block b1 = this.getWorldObj().getBlock(xCoord + d1.offsetX, yCoord - 1, zCoord + d1.offsetZ);
		Block b2 = this.getWorldObj().getBlock(xCoord, yCoord - 1, zCoord);
		Block b3 = this.getWorldObj().getBlock(xCoord + d2.offsetX, yCoord - 1, zCoord + d1.offsetZ);

		if(!(b1 instanceof BlockLiquid) || !(b2 instanceof BlockLiquid) || !(b3 instanceof BlockLiquid)){
			return false;
		}

		return true;
	}



}
