package shift.sextiarysector.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import shift.sextiarysector.SSRecipes;
import shift.sextiarysector.tileentity.TileEntityFreezer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerFreezer extends Container
{
    private TileEntityFreezer tileFurnace;
    private int lastCookTime;
    private int lastBurnTime;
    private int lastItemBurnTime;

    public ContainerFreezer(InventoryPlayer p_i1812_1_, TileEntityFreezer p_i1812_2_)
    {
        this.tileFurnace = p_i1812_2_;
        this.addSlotToContainer(new Slot(p_i1812_2_, 0, 56, 53));
        this.addSlotToContainer(new Slot(p_i1812_2_, 1, 56, 17));
        this.addSlotToContainer(new SlotFurnace(p_i1812_1_.player, p_i1812_2_, 2, 116, 35));
        int i;

        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(p_i1812_1_, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
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

            if (p_82846_2_ == 2)
            {
                if (!this.mergeItemStack(itemstack1, 3, 39, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (p_82846_2_ != 1 && p_82846_2_ != 0)
            {
                if (SSRecipes.freezer.getResult(itemstack1) != null)
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (tileFurnace.isItemFuel(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
                    {
                        return null;
                    }
                }
                else if (p_82846_2_ >= 3 && p_82846_2_ < 30)
                {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false))
                    {
                        return null;
                    }
                }
                else if (p_82846_2_ >= 30 && p_82846_2_ < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 3, 39, false))
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
