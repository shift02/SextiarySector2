package shift.sextiarysector.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class InventoryFurnaceCrafting extends InventoryCrafting implements IInventory {

    /** List of the stacks in the crafting matrix. */
    public ItemStack[] stackList;

    /** the width of the crafting inventory */
    private final int inventoryWidth;

    /**
     * Class containing the callbacks for the events on_GUIClosed and on_CraftMaxtrixChanged.
     */
    private Container eventHandler;

    public InventoryFurnaceCrafting(int par2, int par3) {
        super(null, par2, par3);
        int k = par2 * par3;
        this.stackList = new ItemStack[k];
        //this.eventHandler = par1Container;
        this.inventoryWidth = par2;
    }

    public void setEventHandler(Container par1Container) {
        this.eventHandler = par1Container;
    }

    /**
     * Returns the number of slots in the inventory.
     */
    @Override
    public int getSizeInventory() {
        return this.stackList.length;
    }

    /**
     * Returns the stack in slot i
     */
    @Override
    public ItemStack getStackInSlot(int par1) {
        return par1 >= this.getSizeInventory() ? null : this.stackList[par1];
    }

    /**
     * Returns the itemstack in the slot specified (Top left is 0, 0). Args: row, column
     */
    @Override
    public ItemStack getStackInRowAndColumn(int par1, int par2) {
        if (par1 >= 0 && par1 < this.inventoryWidth) {
            int k = par1 + par2 * this.inventoryWidth;
            return this.getStackInSlot(k);
        } else {
            return null;
        }
    }

    /**
     * Returns the name of the inventory.
     *//*
       @Override
       public String getInvName()
       {
        return "container.crafting";
       }*/

    /**
     * If this returns false, the inventory name will be used as an unlocalized name, and translated into the player's
     * language. Otherwise it will be used directly.
     */
    /*
    @Override
    public boolean isInvNameLocalized()
    {
        return false;
    }*/

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    @Override
    public ItemStack getStackInSlotOnClosing(int par1) {
        if (this.stackList[par1] != null) {
            ItemStack itemstack = this.stackList[par1];
            this.stackList[par1] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    @Override
    public ItemStack decrStackSize(int par1, int par2) {
        if (this.stackList[par1] != null) {
            ItemStack itemstack;

            if (this.stackList[par1].stackSize <= par2) {
                itemstack = this.stackList[par1];
                this.stackList[par1] = null;
                if (this.eventHandler != null) {
                    this.eventHandler.onCraftMatrixChanged(this);
                }

                return itemstack;
            } else {
                itemstack = this.stackList[par1].splitStack(par2);

                if (this.stackList[par1].stackSize == 0) {
                    this.stackList[par1] = null;
                }
                if (this.eventHandler != null) {
                    this.eventHandler.onCraftMatrixChanged(this);
                }
                return itemstack;
            }
        } else {
            return null;
        }
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    @Override
    public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
        this.stackList[par1] = par2ItemStack;
        if (this.eventHandler != null) {
            this.eventHandler.onCraftMatrixChanged(this);
        }
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't
     * this more of a set than a get?*
     */
    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    /**
     * Called when an the contents of an Inventory change, usually
     */
    //@Override
    //public void onInventoryChanged() {}

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    @Override
    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
        return true;
    }

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     */
    @Override
    public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack) {
        return true;
    }

    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {

        NBTTagList nbttaglist = par1NBTTagCompound.getTagList("CraftingItems", 10);
        this.stackList = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.stackList.length) {
                this.stackList[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

    }

    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.stackList.length; ++i) {
            if (this.stackList[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte) i);
                this.stackList[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        par1NBTTagCompound.setTag("CraftingItems", nbttaglist);

    }

}
