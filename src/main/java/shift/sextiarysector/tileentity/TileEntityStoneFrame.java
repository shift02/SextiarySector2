package shift.sextiarysector.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityStoneFrame  extends TileEntity{

	public boolean large;

	public int largeX;
	public int largeY;
	public int largeZ;

	public void setLarge(int x, int y, int z){

		this.largeX = x;
		this.largeY = y;
		this.largeZ = z;

		this.large = true;

		this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

	}

	public void breakLarge(){

		if(!large)return;

		large = false;

		this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

		TileEntityCraftFurnace t = (TileEntityCraftFurnace) this.worldObj.getTileEntity(largeX, largeY, largeZ);

		t.breakLarge();

	}

	public TileEntityCraftFurnace getCraftFurnace(){

		if(!large)return null;

		return  (TileEntityCraftFurnace) this.worldObj.getTileEntity(largeX, largeY, largeZ);

	}

	@Override
	public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
		super.readFromNBT(par1nbtTagCompound);
		large = par1nbtTagCompound.getBoolean("large");
		largeX = par1nbtTagCompound.getInteger("largex");
		largeY = par1nbtTagCompound.getInteger("largey");
		largeZ = par1nbtTagCompound.getInteger("largez");
	}

	@Override
	public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
		super.writeToNBT(par1nbtTagCompound);
		par1nbtTagCompound.setBoolean("large", large);
		par1nbtTagCompound.setInteger("largex", largeX);
		par1nbtTagCompound.setInteger("largey", largeY);
		par1nbtTagCompound.setInteger("largez", largeZ);
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
