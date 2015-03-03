package shift.sextiarysector.tileentity;

import net.minecraft.item.ItemStack;
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
import shift.sextiarysector.SSFluids;
import shift.sextiarysector.api.agriculture.IFarmland;

public class TileEntityFarmland extends TileEntity implements IFluidHandler, IFarmland {

	//水
	protected FluidTank water = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME);

	@Override
	public void updateEntity() {

		if (!this.worldObj.isRemote) {
			this.updateServerEntity();
		}

	}

	public void updateServerEntity() {

		if (this.getBlockMetadata() == 0 && water.getFluidAmount() > 500) {
			this.worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 4);
			this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}

		if (this.getBlockMetadata() == 1 && water.getFluidAmount() <= 500) {
			this.worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 4);
			this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}

	}

	//肥料
	//public String fertilizer;
	private ItemStack fertilizerItem;

	public ItemStack getFertilizer() {
		return fertilizerItem;
	}

	public void setFertilizer(ItemStack fertilizer) {
		if (fertilizer == null) {
			this.fertilizerItem = null;
		} else {
			this.fertilizerItem = fertilizer.copy();
		}

	}

	public void clearFertilizer() {
		this.fertilizerItem = null;
	}

	@Override
	public boolean canGrowth() {
		return water.getFluidAmount() >= 500;
	}

	@Override
	public void growth() {
		this.water.drain(500, true);
	}

	@Override
	public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
		super.readFromNBT(par1nbtTagCompound);
		//if(par1nbtTagCompound.hasKey("fertilizer")){
		//	this.fertilizer = par1nbtTagCompound.getString("fertilizer");
		//}
		if (par1nbtTagCompound.hasKey("fertilizeritem")) {
			this.fertilizerItem = ItemStack.loadItemStackFromNBT(par1nbtTagCompound.getCompoundTag("fertilizeritem"));
		}

		this.water.readFromNBT(par1nbtTagCompound);
	}

	@Override
	public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
		super.writeToNBT(par1nbtTagCompound);
		//if(fertilizer!=null)par1nbtTagCompound.setString("fertilizer", fertilizer);
		if (fertilizerItem != null) {
			NBTTagCompound itemNBT = new NBTTagCompound();
			fertilizerItem.writeToNBT(itemNBT);
			par1nbtTagCompound.setTag("fertilizeritem", itemNBT);
		}
		this.water.writeToNBT(par1nbtTagCompound);
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
		this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}

	//液体関係
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {

		if (!this.canFill(from, resource.getFluid())) {
			return 0;
		}

		return this.water.fill(resource, doFill);

	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		return null;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return null;
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return fluid.getID() == SSFluids.drinkingWater.getID();
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return false;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[] { this.water.getInfo() };
	}

}
