package shift.sextiarysector.tileentity;

import ic2.api.energy.prefab.BasicSink;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityElectricMotor extends TileEntityDirection {

	private BasicSink ic2EnergySink = new BasicSink(this, 1000, 1){

		@Override
		public boolean acceptsEnergyFrom(TileEntity emitter, ForgeDirection direction) {

			return ((TileEntityDirection)this.parent).direction.ordinal() == direction.ordinal();

		}

	};

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

	}

}
