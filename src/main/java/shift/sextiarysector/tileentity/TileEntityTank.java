package shift.sextiarysector.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TileEntityTank extends TileEntity implements IFluidHandler {

	public FluidTank tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME * 32);
	protected int lastAmount;

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

	}

	private void updateServerEntity() {

		if (this.tank.getFluidAmount() * (0.01f) != lastAmount) {
			//System.out.println(this.tank.getFluidAmount());
			lastAmount = (int) (this.tank.getFluidAmount() * (0.01f));
			this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

		}
	}

	public float getRendererAmount() {
		return (float) this.tank.getFluidAmount() / (float) this.tank.getCapacity();
	}

	public boolean hasFluidStack() {
		return tank.getFluidAmount() > 0;
	}

	public FluidStack getFluidStack() {
		return tank.getFluid();
	}

	@Override
	public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
		super.readFromNBT(par1nbtTagCompound);
		tank.readFromNBT(par1nbtTagCompound);
		if (par1nbtTagCompound.hasKey("Empty") && tank.getFluidAmount() > 0) this.tank.setFluid(null);
	}

	@Override
	public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
		super.writeToNBT(par1nbtTagCompound);
		tank.writeToNBT(par1nbtTagCompound);
	}

	/* IFluidHandler */
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
	{
		return tank.fill(resource, doFill);
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
	{
		if (resource == null || !resource.isFluidEqual(tank.getFluid()))
		{
			return null;
		}
		return tank.drain(resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
	{
		return tank.drain(maxDrain, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid)
	{
		return true;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid)
	{
		return true;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from)
	{
		return new FluidTankInfo[] { tank.getInfo() };
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		writeToNBT(nbt);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbt);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		readFromNBT(pkt.func_148857_g());
		//this.worldObj.func_147479_m(xCoord, yCoord, zCoord);
		this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}

}
