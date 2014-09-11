package shift.sextiarysector.tileentity;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.container.InventoryBuffer;
import shift.sextiarysector.container.InventoryFurnaceCrafting;
import shift.sextiarysector.recipe.FurnaceCraftingManager;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityLargeFurnace extends TileEntity implements ISidedInventory{

	public ForgeDirection direction = ForgeDirection.UNKNOWN;

	private static final int[] slots_top = new int[] {0};
    private static final int[] slots_bottom = new int[] {2, 1};
    private static final int[] slots_sides = new int[] {18};

	protected ItemStack[] furnaceItemStacks = new ItemStack[3];//0が素材置場
	public InventoryFurnaceCrafting craftMatrix = new InventoryFurnaceCrafting(3, 3);
	public InventoryBuffer buffer = new InventoryBuffer(this);

	public int furnaceBurnTime;

    /**
     * The number of ticks that a fresh copy of the currently-burning item would keep the furnace burning for
     */
    public int currentItemBurnTime;

    /** The number of ticks that the current item has been cooking for */
    public int furnaceCookTime;

	@Override
	public int getSizeInventory()
    {
        return this.furnaceItemStacks.length;
    }

    /**
     * Returns the stack in slot i
     */
    @Override
	public ItemStack getStackInSlot(int par1)
    {
    	if(par1==18||par1==19){
    		return this.furnaceItemStacks[par1-17];
    	}

        return buffer.getStackInSlot(par1);
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    @Override
	public ItemStack decrStackSize(int par1, int par2)
    {

    	ItemStack[] ItemStacks =buffer.bufferItemStacks;

    	int i = par1;

    	if(par1==18||par1==19){
    		ItemStacks =this.furnaceItemStacks;
    		i-=17;
    	}

        if (ItemStacks[i] != null)
        {
            ItemStack itemstack;

            if (ItemStacks[i].stackSize <= par2)
            {
                itemstack = ItemStacks[i];
                ItemStacks[i] = null;
                return itemstack;
            }
            else
            {
                itemstack = ItemStacks[i].splitStack(par2);

                if (ItemStacks[i].stackSize == 0)
                {
                	ItemStacks[i] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    @Override
	public ItemStack getStackInSlotOnClosing(int par1)
    {

    	ItemStack[] ItemStacks =buffer.bufferItemStacks;

    	int i = par1;

    	if(par1==18||par1==19){
    		ItemStacks =this.furnaceItemStacks;
    		i-=17;
    	}

        if (ItemStacks[i] != null)
        {
            ItemStack itemstack = ItemStacks[i];
            ItemStacks[i] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    @Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {

    	ItemStack[] ItemStacks =buffer.bufferItemStacks;

    	int i = par1;

    	if(par1==18||par1==19){
    		ItemStacks =this.furnaceItemStacks;
    		i-=17;
    	}

    	ItemStacks[i] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
        {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
    }

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
		super.readFromNBT(par1NBTTagCompound);
		direction = ForgeDirection.getOrientation(par1NBTTagCompound.getInteger("direction"));
        NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items",10);
        this.furnaceItemStacks = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.furnaceItemStacks.length)
            {
                this.furnaceItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        this.furnaceBurnTime = par1NBTTagCompound.getShort("BurnTime");
        this.furnaceCookTime = par1NBTTagCompound.getShort("CookTime");
        this.currentItemBurnTime = TileEntityFurnace.getItemBurnTime(this.furnaceItemStacks[1]);

        craftMatrix.readFromNBT(par1NBTTagCompound);
        buffer.readFromNBT(par1NBTTagCompound);

    }

    /**
     * Writes a tile entity to NBT.
     */
    @Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
    	super.writeToNBT(par1NBTTagCompound);
    	par1NBTTagCompound.setInteger("direction", direction.ordinal());
        par1NBTTagCompound.setShort("BurnTime", (short)this.furnaceBurnTime);
        par1NBTTagCompound.setShort("CookTime", (short)this.furnaceCookTime);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.furnaceItemStacks.length; ++i)
        {
            if (this.furnaceItemStacks[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.furnaceItemStacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        par1NBTTagCompound.setTag("Items", nbttaglist);

        craftMatrix.writeToNBT(par1NBTTagCompound);
        buffer.writeToNBT(par1NBTTagCompound);

    }

    @Override
	public void updateEntity()
    {

        boolean flag = this.furnaceBurnTime > 0;
        boolean flag1 = false;

        if (this.furnaceBurnTime > 0)
        {
            --this.furnaceBurnTime;
        }

        if (!this.worldObj.isRemote)
        {
            if (this.furnaceBurnTime == 0 && this.canSmelt())
            {
                this.currentItemBurnTime = this.furnaceBurnTime = TileEntityFurnace.getItemBurnTime(this.furnaceItemStacks[1]);



                if (this.furnaceBurnTime > 0)
                {
                    flag1 = true;

                    if (this.furnaceItemStacks[1] != null)
                    {
                        --this.furnaceItemStacks[1].stackSize;

                        if (this.furnaceItemStacks[1].stackSize == 0)
                        {
                            this.furnaceItemStacks[1] = this.furnaceItemStacks[1].getItem().getContainerItem(furnaceItemStacks[1]);
                        }
                    }
                }
            }

            if (this.isBurning() && this.canSmelt())
            {
                ++this.furnaceCookTime;

                if (this.furnaceCookTime == 200)
                {
                    this.furnaceCookTime = 0;
                    this.smeltItem();
                    flag1 = true;
                }
            }
            else
            {
                this.furnaceCookTime = 0;
            }

            if (flag != this.furnaceBurnTime > 0)
            {
                flag1 = true;
                for(EntityPlayerMP p:(ArrayList<EntityPlayerMP>)this.worldObj.playerEntities){
                	p.playerNetServerHandler.sendPacket(getDescriptionPacket());
                }
                //this.worldObj.markBlockRangeForRenderUpdate(this.xCoord,this.yCoord,this.zCoord,this.xCoord,this.yCoord,this.zCoord);
                //BlockFurnace.updateFurnaceBlockState(this.furnaceBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }

        if (flag1)
        {
        	//System.out.println("2 :"+this.worldObj.isRemote);

        	//this.worldObj.markBlocksDirtyVertical(xCoord, yCoord, zCoord, 0);
            //this.worldObj.markBlockRangeForRenderUpdate(this.xCoord,this.yCoord,this.zCoord,this.xCoord,this.yCoord,this.zCoord);
            this.markDirty();
            this.craftMatrix.markDirty();
        }
    }

    private boolean canSmelt()
    {
    	int j = 0 ;
    	for(int i = 0;i<craftMatrix.getSizeInventory();i++){
    		if (craftMatrix.getStackInSlot(i) != null)
            {
                j++;
            }
    	}
        if (j == 0)
        {
            return false;
        }
        else
        {

            ItemStack itemstack = FurnaceCraftingManager.getInstance().findMatchingRecipe(craftMatrix, worldObj);
            if (itemstack == null) return false;
            if (this.furnaceItemStacks[2] == null) return true;
            if (!this.furnaceItemStacks[2].isItemEqual(itemstack)) return false;
            int result = furnaceItemStacks[2].stackSize + itemstack.stackSize;
            return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
        }
    }

    /**
     * Turn one item from the furnace source stack into the appropriate smelted item in the furnace result stack
     */
    public void smeltItem()
    {
        if (this.canSmelt())
        {
            ItemStack itemstack = FurnaceCraftingManager.getInstance().findMatchingRecipe(craftMatrix, worldObj);

            if (this.furnaceItemStacks[2] == null)
            {
                this.furnaceItemStacks[2] = itemstack.copy();
            }
            else if (this.furnaceItemStacks[2].isItemEqual(itemstack))
            {
                furnaceItemStacks[2].stackSize += itemstack.stackSize;
            }

            //System.out.println("canSmelt");

            for(int i = 0;i<craftMatrix.getSizeInventory();i++){
        		if (craftMatrix.getStackInSlot(i) != null)
                {


        			ItemStack item = buffer.getStackItem(this.craftMatrix.stackList[i]);

        			ItemStack itemC = this.craftMatrix.stackList[i].getItem().getContainerItem(this.craftMatrix.stackList[i]);

        			if(item!=null){
        				--item.stackSize;

        				if (item.stackSize <= 0)
                        {
        					item = null;
        					buffer.setNull();
                        }

        			}else{
        				--this.craftMatrix.stackList[i].stackSize;

                        if (this.craftMatrix.stackList[i].stackSize <= 0)
                        {
                        	this.craftMatrix.stackList[i] = null;
                        }
        			}

        			if(itemC!=null){
        				buffer.addStackItem(itemC);
        				if(itemC.stackSize==0){
        					itemC=null;
        				}

        			}

                }
        	}

        }
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't
     * this more of a set than a get?*
     */
    @Override
	public int getInventoryStackLimit()
    {
        return 64;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns an integer between 0 and the passed value representing how close the current item is to being completely
     * cooked
     */
    public int getCookProgressScaled(int par1)
    {
        return this.furnaceCookTime * par1 / 200;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns an integer between 0 and the passed value representing how much burn time is left on the current fuel
     * item, where 0 means that the item is exhausted and the passed value means that the item is fresh
     */
    public int getBurnTimeRemainingScaled(int par1)
    {
        if (this.currentItemBurnTime == 0)
        {
            this.currentItemBurnTime = 200;
        }

        return this.furnaceBurnTime * par1 / this.currentItemBurnTime;
    }

    /**
     * Returns true if the furnace is currently burning
     */
    public boolean isBurning()
    {
        return this.furnaceBurnTime > 0;
    }

    @Override
	public Packet getDescriptionPacket() {
		NBTTagCompound var1 = new NBTTagCompound();
        this.writeToNBT(var1);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, var1);
	}

	@Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt){
		this.readFromNBT(pkt.func_148857_g());
    	this.worldObj.func_147479_m(xCoord, yCoord, zCoord);
	}

	/**
     * Return true if item is a fuel source (getItemBurnTime() > 0).
     */
    public static boolean isItemFuel(ItemStack par0ItemStack)
    {
        return TileEntityFurnace.getItemBurnTime(par0ItemStack) > 0;
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    @Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
    }

    @Override
	public void openInventory() {}

    @Override
	public void closeInventory() {}

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     */
    @Override
	public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack)
    {
        return par1 >= 9&&par1<=17 ? false : (par1 == 18 ? isItemFuel(par2ItemStack) : true);
    }

    /**
     * Returns an array containing the indices of the slots that can be accessed by automation on the given side of this
     * block.
     */
    @Override
	public int[] getAccessibleSlotsFromSide(int par1)
    {
        return par1 == 0 ? buffer.slots_bottom : (par1 == 1 ? buffer.slots_top : slots_sides);
    }

    /**
     * Returns true if automation can insert the given item in the given slot from the given side. Args: Slot, item,
     * side
     */
    @Override
	public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3)
    {
        return this.isItemValidForSlot(par1, par2ItemStack);
    }

    /**
     * Returns true if automation can extract the given item in the given slot from the given side. Args: Slot, item,
     * side
     */
    @Override
	public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3)
    {
        return par1 >= 9&&par1<=17;
    }

    @Override
	public String getInventoryName()
    {
        return  "container.large_furnace";
    }

    /**
     * If this returns false, the inventory name will be used as an unlocalized name, and translated into the player's
     * language. Otherwise it will be used directly.
     */
    @Override
	public boolean hasCustomInventoryName()
    {
        return false;
    }


}
