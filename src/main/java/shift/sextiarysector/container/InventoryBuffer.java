package shift.sextiarysector.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class InventoryBuffer  implements ISidedInventory{

	public static final int[] slots_top = new int[] {0,1,2,3,4,5,6,7,8};
	public static final int[] slots_sides = new int[] {0,1,2,3,4,5,6,7,8};
	public static final int[] slots_bottom = new int[] {9,10,11,12,13,14,15,16,17};

	public ItemStack[] bufferItemStacks = new ItemStack[18];

    protected IInventory MainInventory;

    public InventoryBuffer(IInventory mainInventory)
    {
    	this.MainInventory = mainInventory;
    }

	public ItemStack getStackItem(ItemStack par1)
    {

		for(int i = 0 ; i < 8;i++){
			if(bufferItemStacks[i]!=null&&par1.isItemEqual(bufferItemStacks[i])){

				if(bufferItemStacks[i].stackSize<=0){
					bufferItemStacks[i]=null;
				}else{
					return bufferItemStacks[i];
				}


			}
		}

        return null;
    }

	public void setNull(){

		for(int i = 0 ; i < 8;i++){

			if(bufferItemStacks[i]!=null&&bufferItemStacks[i].stackSize<=0){
				bufferItemStacks[i]=null;
			}

		}
	}


	public boolean addStackItem(ItemStack item)
    {

		int j = item.stackSize;

		for(int i = 0;i<8&&item.stackSize>0;i++){
			this.mergeStackItem(item);
			if(i==7||j==item.stackSize){
				for(int k = 9 ; k < 18;k++){

					if(bufferItemStacks[k]==null){
						bufferItemStacks[k]=item.copy();
						item.stackSize =0;
						break;
					}

				}
			}

		}

		return item.stackSize==0;

    }

	public ItemStack mergeStackItem(ItemStack item)
    {

		for(int i = 9 ; i < 18;i++){

			if(bufferItemStacks[i]!=null&&item.isItemEqual(bufferItemStacks[i])){

				if(item.stackSize+bufferItemStacks[i].stackSize<=item.getMaxStackSize()){
					bufferItemStacks[i].stackSize+=item.stackSize;
					item.stackSize=0;
					break;
				}else if(bufferItemStacks[i].stackSize==bufferItemStacks[i].getMaxStackSize()){

				}else {
					int s = bufferItemStacks[i].getMaxStackSize()-bufferItemStacks[i].stackSize;
					bufferItemStacks[i].stackSize = bufferItemStacks[i].getMaxStackSize();
					item.stackSize -=s;

				}
			}



		}

		return item;

    }

    @Override
	public int getSizeInventory()
    {
        return this.bufferItemStacks.length;
    }

    @Override
	public ItemStack getStackInSlot(int par1)
    {
        return this.bufferItemStacks[par1];
    }

    @Override
	public ItemStack decrStackSize(int par1, int par2)
    {
        if (this.bufferItemStacks[par1] != null)
        {
            ItemStack itemstack;

            if (this.bufferItemStacks[par1].stackSize <= par2)
            {
                itemstack = this.bufferItemStacks[par1];
                this.bufferItemStacks[par1] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.bufferItemStacks[par1].splitStack(par2);

                if (this.bufferItemStacks[par1].stackSize == 0)
                {
                    this.bufferItemStacks[par1] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    @Override
	public ItemStack getStackInSlotOnClosing(int par1)
    {
        if (this.bufferItemStacks[par1] != null)
        {
            ItemStack itemstack = this.bufferItemStacks[par1];
            this.bufferItemStacks[par1] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    @Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {
        this.bufferItemStacks[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
        {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
    }

	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        NBTTagList nbttaglist = par1NBTTagCompound.getTagList("BufferItems",10);
        this.bufferItemStacks = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.bufferItemStacks.length)
            {
                this.bufferItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

    }

	public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.bufferItemStacks.length; ++i)
        {
            if (this.bufferItemStacks[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.bufferItemStacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        par1NBTTagCompound.setTag("BufferItems", nbttaglist);

    }

	@Override
	public String getInventoryName() {
		return "InventoryBuffer";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void markDirty() {
		if(MainInventory!=null){
			MainInventory.markDirty();
		}
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return true;
    }

	@Override
	public void openInventory() {

	}

	@Override
	public void closeInventory() {
	}

	@Override
	public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack)
    {
        return par1<8;
    }

	@Override
	public int[] getAccessibleSlotsFromSide(int par1)
    {
        return par1 == 0 ? slots_bottom : (par1 == 1 ? slots_top : slots_sides);
    }

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j) {
		return this.isItemValidForSlot(i, itemstack);
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		return i>=8;
	}



}
