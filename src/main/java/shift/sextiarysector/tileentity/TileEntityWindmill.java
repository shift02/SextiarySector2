package shift.sextiarysector.tileentity;

import net.minecraft.tileentity.TileEntity;
import shift.sextiarysector.api.machine.energy.IEnergyHandler;

public class TileEntityWindmill extends TileEntityDirection{

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
		if(this.rotateStep>360){
			this.rotateStep-=360;
		}

		this.rotateStep+=2;

	}

	public void updateServerEntity()
	{
		TileEntity t =this.worldObj.getTileEntity(xCoord-this.direction.offsetX, yCoord-this.direction.offsetY, zCoord-this.direction.offsetZ);
		if(t!=null && t instanceof IEnergyHandler){

			((IEnergyHandler)t).addEnergy(this.direction, 2, 20, false);
		}

	}

	public float getRotateStep() {
		return -rotateStep;
	}

}
