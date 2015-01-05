package shift.sextiarysector.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import shift.sextiarysector.tileentity.TileEntityFluidMachineBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerFluidMachineBase extends Container
{
    private TileEntityFluidMachineBase tileFurnace;
    private int lastFuel;
    private int lastFuelMax;
    private int workProgress;
    //private int lastFluid;
    //private int lastFluidID;

    public ContainerFluidMachineBase(InventoryPlayer p_i1812_1_, TileEntityFluidMachineBase p_i1812_2_)
    {
        this.tileFurnace = p_i1812_2_;
        this.addSlotToContainer(new Slot(p_i1812_2_, 0, 56, 17));
        this.addSlotToContainer(new Slot(p_i1812_2_, 1, 56, 53));
        this.addSlotToContainer(new SlotFurnace(p_i1812_1_.player, p_i1812_2_, 2, 107, 53));
        this.addSlotToContainer(new SlotFurnace(p_i1812_1_.player, p_i1812_2_, 3, 125, 53));
        this.addSlotToContainer(new SlotFluid(p_i1812_2_, 4, 152, 17));
        this.addSlotToContainer(new SlotFluid(p_i1812_2_, 5, 152, 53));
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
        p_75132_1_.sendProgressBarUpdate(this, 0, this.tileFurnace.fuel);
        p_75132_1_.sendProgressBarUpdate(this, 1, this.tileFurnace.fuelMax);
        p_75132_1_.sendProgressBarUpdate(this, 2, this.tileFurnace.machineWorkProgressTime);
        p_75132_1_.sendProgressBarUpdate(this, 3, this.tileFurnace.getTank().getFluidAmount());
        if(this.tileFurnace.getTank().getFluid()!=null){
        	p_75132_1_.sendProgressBarUpdate(this, 4, this.tileFurnace.getTank().getFluid().fluidID);
        }else{
        	p_75132_1_.sendProgressBarUpdate(this, 4, 0);
        }

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

            if (this.lastFuel != this.tileFurnace.fuel)
            {
                icrafting.sendProgressBarUpdate(this, 0, this.tileFurnace.fuel);
            }

            if (this.lastFuelMax != this.tileFurnace.fuelMax)
            {
                icrafting.sendProgressBarUpdate(this, 1, this.tileFurnace.fuelMax);
            }

            if (this.workProgress != this.tileFurnace.machineWorkProgressTime)
            {
                icrafting.sendProgressBarUpdate(this, 2, this.tileFurnace.machineWorkProgressTime);
            }

            /*if (this.lastFluid != this.tileFurnace.getTank().getFluidAmount())
            {
                icrafting.sendProgressBarUpdate(this, 3, this.tileFurnace.getTank().getFluidAmount());

                ////if(this.tileFurnace.getTank().getFluidAmount()>0){
                //	icrafting.sendProgressBarUpdate(this, 4, this.tileFurnace.getTank().getFluid().fluidID);
               // }

            }*/
        }

        this.lastFuel = this.tileFurnace.fuel;
        this.lastFuelMax = this.tileFurnace.fuelMax;
        this.workProgress = this.tileFurnace.machineWorkProgressTime;
        //this.lastFluid = this.tileFurnace.getTank().getFluidAmount();
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int p_75137_1_, int p_75137_2_)
    {
        if (p_75137_1_ == 0)
        {
            this.tileFurnace.fuel = p_75137_2_;
        }

        if (p_75137_1_ == 1)
        {
            this.tileFurnace.fuelMax = p_75137_2_;
        }

        if (p_75137_1_ == 2)
        {
            this.tileFurnace.machineWorkProgressTime = p_75137_2_;
        }

        /*if (p_75137_1_ == 3)
        {
            this.tileFurnace.amount = p_75137_2_;
        }*/

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
                if (FurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null)
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (TileEntityFurnace.isItemFuel(itemstack1))
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
