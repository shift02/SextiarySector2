package shift.sextiarysector.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import shift.sextiarysector.tileentity.TileEntityLargeFurnace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerLargeFurnace  extends Container{

	public InventoryFurnaceCrafting craftMatrix;// = new InventoryCrafting(this, 3, 3);
	public InventoryBuffer buffer;

	private final TileEntityLargeFurnace furnace;
    private int lastCookTime;
    private int lastBurnTime;
    private int lastItemBurnTime;

    public ContainerLargeFurnace(InventoryPlayer par1InventoryPlayer, TileEntityLargeFurnace par2TileEntityFurnace)
    {
        this.furnace = par2TileEntityFurnace;
        this.craftMatrix = par2TileEntityFurnace.craftMatrix;
        this.buffer = par2TileEntityFurnace.buffer;
        this.craftMatrix.setEventHandler(this);
        //this.addSlotToContainer(new Slot(par2TileEntityFurnace, 0, 56, 17));
        //2 1
        this.addSlotToContainer(new Slot(par2TileEntityFurnace, 18, 15, 46));//1
        this.addSlotToContainer(new SlotFurnace(par1InventoryPlayer.player, par2TileEntityFurnace, 19, 135, 35));//2
        int i;

        //9 10
        for (int l = 0; l < 3; ++l)
        {
            for (int i1 = 0; i1 < 3; ++i1)
            {
                this.addSlotToContainer(new Slot(this.craftMatrix, i1 + l * 3, 41 + i1 * 18, 17 + l * 18));
            }
        }

        //Buffer 18 28
        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(buffer, i, 8 + i * 18, 88));
        }
        for (i = 9; i < 18; ++i)
        {
            this.addSlotToContainer(new Slot(buffer, i, 8 + (i-9) * 18, 108));
        }

        //27 55
        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84+56 + i * 18));
            }
        }

        //9 64
        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, i, 8 + i * 18, 142+56));
        }
    }

    @Override
	public void addCraftingToCrafters(ICrafting par1ICrafting)
    {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate(this, 0, this.furnace.furnaceCookTime);
        par1ICrafting.sendProgressBarUpdate(this, 1, this.furnace.furnaceBurnTime);
        par1ICrafting.sendProgressBarUpdate(this, 2, this.furnace.currentItemBurnTime);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    @Override
	public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);

            if (this.lastCookTime != this.furnace.furnaceCookTime)
            {
                icrafting.sendProgressBarUpdate(this, 0, this.furnace.furnaceCookTime);
            }

            if (this.lastBurnTime != this.furnace.furnaceBurnTime)
            {
                icrafting.sendProgressBarUpdate(this, 1, this.furnace.furnaceBurnTime);
            }

            if (this.lastItemBurnTime != this.furnace.currentItemBurnTime)
            {
                icrafting.sendProgressBarUpdate(this, 2, this.furnace.currentItemBurnTime);
            }
        }

        this.lastCookTime = this.furnace.furnaceCookTime;
        this.lastBurnTime = this.furnace.furnaceBurnTime;
        this.lastItemBurnTime = this.furnace.currentItemBurnTime;
    }

    @Override
	@SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2)
    {
        if (par1 == 0)
        {
            this.furnace.furnaceCookTime = par2;
        }

        if (par1 == 1)
        {
            this.furnace.furnaceBurnTime = par2;
        }

        if (par1 == 2)
        {
            this.furnace.currentItemBurnTime = par2;
        }
    }

    @Override
	public boolean canInteractWith(EntityPlayer par1EntityPlayer)
    {
        return this.furnace.isUseableByPlayer(par1EntityPlayer);
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    @Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 == 1)
            {
                if (!this.mergeItemStack(itemstack1, 29, 65, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (par2 >= 11 && par2 <= 64)
            {
                if (TileEntityFurnace.isItemFuel(itemstack1))
                {


                	if(par2<=19){
                		if (!this.mergeItemStack(itemstack1, 0, 1, false))
                        {
                            return null;
                        }
                	}
                	else  if (!this.mergeItemStack(itemstack1, 11, 20, false))
                    {
                		if (!this.mergeItemStack(itemstack1, 20, 65, false))
                        {
                            return null;
                        }
                    }
                }
                else if (par2 >= 11 && par2 <= 28)
                {
                    if (!this.mergeItemStack(itemstack1, 29, 65, false))
                    {
                        return null;
                    }
                }else if (par2 >= 29 && par2 <= 55)
                {
                    if (!this.mergeItemStack(itemstack1, 11, 20, false))
                    {

                    	if (!this.mergeItemStack(itemstack1, 56, 65, false))
                        {
                            return null;
                        }

                    }
                }else  if (par2 >= 56 && par2 <= 64)
                {
                    if (!this.mergeItemStack(itemstack1, 11,20, false))
                    {

                    	 if (!this.mergeItemStack(itemstack1, 29, 56, false))
                         {
                             return null;
                         }

                    }
                }
            }
            else if (!this.mergeItemStack(itemstack1, 29, 65, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }


            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
        }

        return itemstack;
    }

}
