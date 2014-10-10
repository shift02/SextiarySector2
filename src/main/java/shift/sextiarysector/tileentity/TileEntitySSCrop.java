package shift.sextiarysector.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.api.season.SeasonAPI;
import shift.sextiarysector.block.BlockSSCrop;
import shift.sextiarysector.block.BlockSSCrop.CropStatus;

public class TileEntitySSCrop extends TileEntity implements IFluidHandler{

	private int lastMinutes = 0;
	private int lastDay =0;

	//成長何日目か
	private int day=0;

	public void updateEntity() {

		if(!this.worldObj.isRemote){
			this.updateServerEntity();
		}

	}

	public void updateServerEntity() {

		if(lastMinutes==0){
			lastMinutes=SeasonAPI.getMinute(getWorldObj());
			System.out.println("AAAAAA"+lastMinutes);
		}

		if(lastMinutes==SeasonAPI.getMinute(getWorldObj())&&lastDay!=SeasonAPI.getDay(getWorldObj())){

			System.out.println("BBBBB"+lastMinutes);

			if(this.hasFarmland()&&this.canGrowth()){

				this.growth();
				day++;

				if(this.getStatus().i[this.getBlockMetadata()]<=day){
					this.worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, getBlockMetadata()+1, 4);
					this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
				}

				this.lastDay = SeasonAPI.getDay(getWorldObj());

			}

		}

	}

	public boolean hasFarmland(){
		return this.worldObj.getBlock(this.xCoord,this.yCoord-1,this.zCoord)==SSBlocks.farmland;
	}

	private boolean canGrowth(){

		return ((TileEntityFarmland)this.worldObj.getTileEntity(this.xCoord,this.yCoord-1,this.zCoord)).canGrowth();

	}

	private void growth(){

		((TileEntityFarmland)this.worldObj.getTileEntity(this.xCoord,this.yCoord-1,this.zCoord)).growth();
		System.out.println("GGGGGG");

	}

	private CropStatus getStatus(){
		return ((BlockSSCrop)this.getBlockType()).getStatus();
	}

	private TileEntityFarmland getTileEntityFarmland(){
		return ((TileEntityFarmland)this.worldObj.getTileEntity(this.xCoord,this.yCoord-1,this.zCoord));
	}

	@Override
	public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
		super.readFromNBT(par1nbtTagCompound);
		this.lastMinutes = par1nbtTagCompound.getInteger("lastminutes");
		this.lastDay = par1nbtTagCompound.getInteger("lastday");

		this.day = par1nbtTagCompound.getInteger("day");
	}

	@Override
	public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
		super.writeToNBT(par1nbtTagCompound);
		par1nbtTagCompound.setInteger("lastminutes", this.lastMinutes);
		par1nbtTagCompound.setInteger("lastday", this.lastDay);

		par1nbtTagCompound.setInteger("day", this.day);
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

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {

		if(this.hasFarmland()){
			return this.getTileEntityFarmland().fill(from, resource, doFill);
		}

		return 0;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource,boolean doDrain) {

		if(this.hasFarmland()){
			return this.getTileEntityFarmland().drain(from, resource, doDrain);
		}

		return null;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {

		if(this.hasFarmland()){
			return this.getTileEntityFarmland().drain(from, maxDrain, doDrain);
		}

		return null;
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {

		if(this.hasFarmland()){
			return this.getTileEntityFarmland().canFill(from, fluid);
		}

		return false;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {

		if(this.hasFarmland()){
			return this.getTileEntityFarmland().canDrain(from, fluid);
		}

		return false;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {

		if(this.hasFarmland()){
			return this.getTileEntityFarmland().getTankInfo(from);
		}

		return null;
	}

}
