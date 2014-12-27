package shift.sextiarysector.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import shift.sextiarysector.SSRecipes;
import shift.sextiarysector.container.ItemBox;

public class TileEntityMagicFurnace extends TileEntitySimpleFurnace{

	protected static final int[] slots_top = new int[] { 0 };
	protected static final int[] slots_bottom = new int[] { 2, 1 };
	protected static final int[] slots_sides = new int[] { 1 };

	//0 素材 ,1 燃料 ,2 完成品
	protected ItemBox items = new ItemBox("Base", 3);

	protected ItemStack getFuelItem(){
		return this.items.getStackInSlot(1);
	}

	protected int getFuelItemSlot(){
		return 1;
	}

	protected void reduceFuelStackSize(){
		this.items.reduceStackSize(1, 1);
	}

	protected ItemStack getMaterialItem(){
		return this.items.getStackInSlot(0);
	}

	protected int getResultItemSlot(){
		return 2;
	}

	protected void reduceMaterialStackSize(){
		this.items.reduceStackSize(0, 1);
	}

	protected ItemStack getResult(ItemStack stackInSlot) {
		return SSRecipes.magicFurnace.getResult(stackInSlot);
	}

	public int getItemFuelTime(ItemStack p_145952_0_)
    {
        if (p_145952_0_ == null)
        {
            return 0;
        }
        else
        {
        	return SSRecipes.magicFuel.getResult(p_145952_0_);
        }
    }

    protected ItemBox getItems(){
		return items;
    }

	//NBT関係
	@Override
	public void readFromNBT(NBTTagCompound nbt) {

		super.readFromNBT(nbt);
		items.readFromNBT(nbt);

	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {

		super.writeToNBT(nbt);
		items.writeToNBT(nbt);

	}

	@Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt){
		this.readFromNBT(pkt.func_148857_g());
    	this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}

}
