package shift.sextiarysector.container;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class ItemBox{

	protected String name;
	protected ItemStack[] itemStacks;
	protected int stackLimit;

	public ItemBox(String name,int size){

		this(name, size, 64);

	}

	public ItemBox(String name,int size ,int StackLimit){

		this.name = name;
		this.itemStacks = new ItemStack[size];
		this.stackLimit = StackLimit;

	}

	public int getSizeInventory() {
		return itemStacks.length;
	}

	public ItemStack getStackInSlot(int slot) {
		return itemStacks[slot];
	}

	public int reduceStackSize(int slot, int size){

		if (this.itemStacks[slot] != null)
        {
            int s ;

            if (this.itemStacks[slot].stackSize <= size)
            {
            	s = this.itemStacks[slot].stackSize;
                this.itemStacks[slot] = null;
                return s;
            }
            else
            {
                s = size;

                this.itemStacks[slot].stackSize-=s;

                if (this.itemStacks[slot].stackSize <= 0)
                {
                    this.itemStacks[slot] = null;
                }

                return s;
            }
        }
        else
        {
            return 0;
        }

	}

	public ItemStack decrStackSize(int slot, int size) {

		if (this.itemStacks[slot] != null)
        {
            ItemStack itemstack;

            if (this.itemStacks[slot].stackSize <= size)
            {
                itemstack = this.itemStacks[slot];
                this.itemStacks[slot] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.itemStacks[slot].splitStack(size);

                if (this.itemStacks[slot].stackSize == 0)
                {
                    this.itemStacks[slot] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }

	}

	public ItemStack getStackInSlotOnClosing(int slot) {

		if (this.itemStacks[slot] != null)
        {
            ItemStack itemstack = this.itemStacks[slot];
            this.itemStacks[slot] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
	}

	public void setInventorySlotContents(int slot, ItemStack itemstack) {

		this.itemStacks[slot] = itemstack;

        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit())
        {
            itemstack.stackSize = this.getInventoryStackLimit();
        }

	}

	public String getInvName() {
		return name;
	}

	public boolean isInvNameLocalized() {
		return false;
	}

	public int getInventoryStackLimit() {
		return this.stackLimit;
	}

	public void onInventoryChanged() {

		for(int i=0;i<itemStacks.length;i++){

			if(itemStacks[i]!=null&&itemStacks[i].stackSize<=0){
				itemStacks[i]=null;
			}

		}

	}

	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {

		NBTTagList nbttaglist = par1NBTTagCompound.getTagList(this.getInvName()+"Items",10);
        this.itemStacks = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.itemStacks.length)
            {
                this.itemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
    }


	public void writeToNBT(NBTTagCompound par1NBTTagCompound)
	{

		NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.itemStacks.length; ++i)
        {
            if (this.itemStacks[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.itemStacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        par1NBTTagCompound.setTag(this.getInvName()+"Items", nbttaglist);

	}

}
