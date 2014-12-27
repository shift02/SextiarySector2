package shift.sextiarysector.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import shift.sextiarysector.tileentity.TileEntityCraftFurnace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerCraftFurnace extends Container
{
    private TileEntityCraftFurnace tileFurnace;
    private InventoryFurnaceCrafting craftMatrix;
    private int lastCookTime;
    private int lastBurnTime;
    private int lastItemBurnTime;

    public ContainerCraftFurnace(InventoryPlayer p_i1812_1_, TileEntityCraftFurnace p_i1812_2_)
    {
        this.tileFurnace = p_i1812_2_;
        this.craftMatrix = p_i1812_2_.craftMatrix;
        this.addSlotToContainer(new Slot(p_i1812_2_, 0, 15, 46));//0
        this.addSlotToContainer(new SlotFurnace(p_i1812_1_.player, p_i1812_2_, 1, 135, 35));//1
        int i;

        for (i = 0; i < 3; ++i)//2-10
        {
            for (int j = 0; j < 3; ++j)
            {
            	this.addSlotToContainer(new Slot(this.craftMatrix, i + j * 3, 41 + i * 18, 17 + j * 18));
            }
        }

        for (i = 0; i < 3; ++i)//11-37
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(p_i1812_1_, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)//38-46
        {
            this.addSlotToContainer(new Slot(p_i1812_1_, i, 8 + i * 18, 142));
        }



    }

    public void addCraftingToCrafters(ICrafting p_75132_1_)
    {
        super.addCraftingToCrafters(p_75132_1_);
        p_75132_1_.sendProgressBarUpdate(this, 0, this.tileFurnace.machineWorkProgressTime);
        p_75132_1_.sendProgressBarUpdate(this, 1, this.tileFurnace.fuel);
        p_75132_1_.sendProgressBarUpdate(this, 2, this.tileFurnace.fuelMax);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);

            if (this.lastCookTime != this.tileFurnace.machineWorkProgressTime)
            {
                icrafting.sendProgressBarUpdate(this, 0, this.tileFurnace.machineWorkProgressTime);
            }

            if (this.lastBurnTime != this.tileFurnace.fuel)
            {
                icrafting.sendProgressBarUpdate(this, 1, this.tileFurnace.fuel);
            }

            if (this.lastItemBurnTime != this.tileFurnace.fuelMax)
            {
                icrafting.sendProgressBarUpdate(this, 2, this.tileFurnace.fuelMax);
            }
        }

        this.lastCookTime = this.tileFurnace.machineWorkProgressTime;
        this.lastBurnTime = this.tileFurnace.fuel;
        this.lastItemBurnTime = this.tileFurnace.fuelMax;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int p_75137_1_, int p_75137_2_)
    {
        if (p_75137_1_ == 0)
        {
            this.tileFurnace.machineWorkProgressTime = p_75137_2_;
        }

        if (p_75137_1_ == 1)
        {
            this.tileFurnace.fuel = p_75137_2_;
        }

        if (p_75137_1_ == 2)
        {
            this.tileFurnace.fuelMax = p_75137_2_;
        }
    }

    public boolean canInteractWith(EntityPlayer p_75145_1_)
    {
        return this.tileFurnace.isUseableByPlayer(p_75145_1_);
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    public ItemStack transferStackInSlot(EntityPlayer p_82846_1_, int p_82846_2_)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(p_82846_2_);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (p_82846_2_ == 1)
            {
                if (!this.mergeItemStack(itemstack1, 3, 39, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if ( !(1<=p_82846_2_&& p_82846_2_ <= 10))
            {
                if (tileFurnace.isItemFuel(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (p_82846_2_ >= 11 && p_82846_2_ < 38)
                {
                    if (!this.mergeItemStack(itemstack1, 38, 46, false))
                    {
                        return null;
                    }
                }
                else if (p_82846_2_ >= 38 && p_82846_2_ < 46 && !this.mergeItemStack(itemstack1, 11, 38, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 11, 46, false))
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

            slot.onPickupFromSlot(p_82846_1_, itemstack1);
        }

        return itemstack;
    }
}
