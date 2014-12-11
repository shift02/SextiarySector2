package shift.sextiarysector.tileentity;

import ic2.api.energy.prefab.BasicSink;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.api.machine.energy.IEnergyHandler;

public class TileEntityElectricMotor extends TileEntityDirection {

	private BasicSink ic2EnergySink = new BasicSink(this, 2000, 1){

		@Override
		public boolean acceptsEnergyFrom(TileEntity emitter, ForgeDirection direction) {

			return ((TileEntityDirection)this.parent).direction.ordinal() == direction.ordinal();

		}

	};

	private double lastEnergy;

	public float rotateStep = 360;

	@Override
	public void invalidate() {
		ic2EnergySink.invalidate(); // notify the energy sink

		super.invalidate(); // this is important for mc!
	}

	@Override
	public void onChunkUnload() {
		ic2EnergySink.onChunkUnload(); // notify the energy sink

	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);

		ic2EnergySink.readFromNBT(tag);

	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);

		ic2EnergySink.writeToNBT(tag);

	}

	@Override
	public void updateEntity() {
		ic2EnergySink.updateEntity();



		if (this.worldObj.isRemote) {
			this.updateClientEntity();
		} else {

			this.updateServerEntity();

		}

	}


	public void updateClientEntity() {
		if(this.ic2EnergySink.getEnergyStored()>0){
			rotateStep-=8;
			if(rotateStep<0){
				rotateStep+=360;
			}
		}
	}

	private void updateServerEntity() {

		if(this.ic2EnergySink.getEnergyStored()!=lastEnergy){
			this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			this.lastEnergy = this.ic2EnergySink.getEnergyStored();
		}

		if(this.ic2EnergySink.canUseEnergy(20)){

			TileEntity t =this.worldObj.getTileEntity(xCoord-this.direction.offsetX, yCoord-this.direction.offsetY, zCoord-this.direction.offsetZ);
			if(t!=null && t instanceof IEnergyHandler){

				int i = ((IEnergyHandler)t).addEnergy(this.direction, 3, 40, false);
				this.ic2EnergySink.useEnergy(i);


			}

		}

	}



	public float getRotateStep() {
		return rotateStep;
	}


}
