package shift.sextiarysector.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import shift.sextiarysector.api.machine.energy.IEnergyHandler;
import tsuteto.tofu.api.tileentity.ITfConsumer;


public class TileEntityTofuMotor  extends TileEntityDirection implements ITfConsumer{

	private double lastEnergy;

	public float rotateStep = 360;

	public double tofu = 0;
	public final double MAX_TOFU = 80.0d;

	@Override
	public void updateEntity() {

		if (this.worldObj.isRemote) {
			this.updateClientEntity();
		} else {

			this.updateServerEntity();

		}

	}

	public void updateClientEntity() {
		if(this.tofu>0){
			rotateStep-=8;
			if(rotateStep<0){
				rotateStep+=360;
			}
		}
	}

	private void updateServerEntity() {

		if(this.tofu!=lastEnergy){
			this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			this.lastEnergy = this.tofu;
		}

		if(this.tofu>0.4){

			TileEntity t =this.worldObj.getTileEntity(xCoord-this.direction.offsetX, yCoord-this.direction.offsetY, zCoord-this.direction.offsetZ);

			if(t!=null && t instanceof IEnergyHandler){

				int i = ((IEnergyHandler)t).addEnergy(this.direction, 2, 40, false);
				this.tofu-=(i/100.0);

			}

		}

	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);

		tofu = tag.getDouble("tofu");

	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);

		tag.setDouble("tofu", tofu);

	}

	@Override
	public double getMaxTfCapacity() {
		return MAX_TOFU;
	}

	@Override
	public double getCurrentTfConsumed() {
		return 40;
	}

	@Override
	public void chargeTf(double amount) {
		tofu = Math.max(0, Math.min(tofu+amount, MAX_TOFU));
	}

	public float getRotateStep() {
		return rotateStep;
	}

}
