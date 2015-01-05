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
import shift.sextiarysector.api.agriculture.IFarmland;
import shift.sextiarysector.api.season.Season;
import shift.sextiarysector.api.season.SeasonAPI;
import shift.sextiarysector.block.BlockSSCrop;
import shift.sextiarysector.block.BlockSSCrop.CropStatus;

public class TileEntitySSCrop extends TileEntity implements IFluidHandler{

	private int lastMinutes = 0;
	private int lastDay =0;

	//成長何日目か
	private int day=0;

	//再収穫
	private int day2=0;

	public void updateEntity() {

		if(!this.worldObj.isRemote){
			this.updateServerEntity();
		}

	}

	public void updateServerEntity() {

		if(this.getBlockMetadata()==4 && !this.getStatus().isReHarvest()){
			this.worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 3, 0);
		}

		if(lastMinutes==0){
			lastMinutes=SeasonAPI.getMinute(getWorldObj());
		}

		if(lastMinutes==SeasonAPI.getMinute(getWorldObj())&&lastDay!=SeasonAPI.getDay(getWorldObj())){

			Season[] s = this.getStatus().getSeason();

			boolean notB = false;
			for(int i = 0;i<s.length;i++){
				if(s[i].equals(SeasonAPI.getSeason(getWorldObj()))){
					notB =true;
					break;
				}
			}

			if(!notB){
				this.worldObj.func_147480_a(this.xCoord, this.yCoord, this.zCoord, false);
				this.worldObj.removeTileEntity(xCoord, yCoord, zCoord);
				return ;
			}

			if(this.hasFarmland()&&this.canGrowth()){

				this.growth();

			}

		}

	}

	public boolean hasFarmland(){
		return this.worldObj.getTileEntity(this.xCoord,this.yCoord-1,this.zCoord) instanceof IFarmland;
	}

	private boolean canGrowth(){

		if(this.getBlockMetadata()==3){
			return false;
		}

		return ((IFarmland)this.worldObj.getTileEntity(this.xCoord,this.yCoord-1,this.zCoord)).canGrowth();

	}

	private void growth(){

		day++;

		this.lastDay = SeasonAPI.getDay(getWorldObj());

		((IFarmland)this.worldObj.getTileEntity(this.xCoord,this.yCoord-1,this.zCoord)).growth();

		BlockSSCrop crop = (BlockSSCrop) this.getBlockType();

		if(this.getBlockMetadata()<3){

			if(this.day < this.getStatus().getDays()[this.getBlockMetadata()])return;

			this.worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, getBlockMetadata()+1, 4);
			this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);


		}else if( crop.canReHarvest() && this.getBlockMetadata()==4 && this.getStatus().isReHarvest() ){

			day2++;

			if(day2 < this.getStatus().getDays()[3])return ;

			this.worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 3, 4);
			this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

		}

	}

	public void onHarvest(){

		this.worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 4, 4);
		this.day2=0;
		this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

	}

	private CropStatus getStatus(){
		return ((BlockSSCrop)this.getBlockType()).getStatus();
	}

	private IFarmland getFarmland(){
		return ((IFarmland)this.worldObj.getTileEntity(this.xCoord,this.yCoord-1,this.zCoord));
	}

	private BlockSSCrop getCrop(){
		return (BlockSSCrop) this.worldObj.getBlock(xCoord, yCoord, zCoord);
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
			return this.getFarmland().fill(from, resource, doFill);
		}

		return 0;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource,boolean doDrain) {

		if(this.hasFarmland()){
			return this.getFarmland().drain(from, resource, doDrain);
		}

		return null;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {

		if(this.hasFarmland()){
			return this.getFarmland().drain(from, maxDrain, doDrain);
		}

		return null;
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {

		if(this.hasFarmland()){
			return this.getFarmland().canFill(from, fluid);
		}

		return false;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {

		if(this.hasFarmland()){
			return this.getFarmland().canDrain(from, fluid);
		}

		return false;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {

		if(this.hasFarmland()){
			return this.getFarmland().getTankInfo(from);
		}

		return null;
	}

}
