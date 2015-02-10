package shift.sextiarysector.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

public class TileEntityFigure extends TileEntityDirection{

	private ItemStack figure;
	private String edition = "";
	//public float rotateStep = 0;

	@Override
	public void updateEntity() {

		super.updateEntity();

		if (this.worldObj.isRemote) {
			this.updateClientEntity();
		} else {
			this.updateServerEntity();
		}

	}

	public void updateClientEntity() {

		//this.rotateStep -= 1.2f;

	}

	private void updateServerEntity() {

	}

	public ItemStack getFigure() {
		return figure;
	}

	public void setFigure(ItemStack figure) {
		this.figure = figure;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	@Override
	public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
		super.readFromNBT(par1nbtTagCompound);
		if(par1nbtTagCompound.hasKey("figure")){
			NBTTagCompound fNBT = par1nbtTagCompound.getCompoundTag("figure");
			figure = ItemStack.loadItemStackFromNBT(fNBT);
			edition = par1nbtTagCompound.getString("edition");
		}

	}

	@Override
	public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
		super.writeToNBT(par1nbtTagCompound);
		if(figure!=null){
			NBTTagCompound fNBT = new NBTTagCompound();
			figure.writeToNBT(fNBT);
			par1nbtTagCompound.setTag("figure", fNBT);
			par1nbtTagCompound.setString("edition", edition);
		}
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
}
