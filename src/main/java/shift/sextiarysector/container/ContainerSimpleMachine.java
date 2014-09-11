package shift.sextiarysector.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import shift.sextiarysector.api.machine.item.GearForceItem;
import shift.sextiarysector.tileentity.TileEntitySimpleMachine;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerSimpleMachine  extends Container{

	private final TileEntitySimpleMachine machine;
    private int lastWorkTime;
    private int lastInPower;
    private int lastInSpeed;
    private int lastPower;
    private int lastSpeed;

	public ContainerSimpleMachine(InventoryPlayer par1InventoryPlayer, TileEntitySimpleMachine par2TileEntityFurnace)
    {
        this.machine = par2TileEntityFurnace;
        this.addSlotToContainer(new Slot(par2TileEntityFurnace, 0, 56, 17));
        this.addSlotToContainer(new SlotGF(par2TileEntityFurnace, 1, 56, 53,1));
        this.addSlotToContainer(new SlotFurnace(par1InventoryPlayer.player, par2TileEntityFurnace, 2, 116, 35));
        int i;

        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, i, 8 + i * 18, 142));
        }
    }

	@Override
	public void addCraftingToCrafters(ICrafting par1ICrafting)
    {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate(this, 0, this.machine.machineWorkProgressTime);
        par1ICrafting.sendProgressBarUpdate(this, 1, this.machine.inPower);
        par1ICrafting.sendProgressBarUpdate(this, 2, this.machine.inSpeed);
        par1ICrafting.sendProgressBarUpdate(this, 3, this.machine.storage.getPowerStored());
        par1ICrafting.sendProgressBarUpdate(this, 4, (int) this.machine.storage.getSpeedStored());
    }

	@Override
	public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);

            if (this.lastWorkTime != this.machine.machineWorkProgressTime)
            {
                icrafting.sendProgressBarUpdate(this, 0, this.machine.machineWorkProgressTime);
            }

            if (this.lastInPower != this.machine.inPower)
            {
                icrafting.sendProgressBarUpdate(this, 1, this.machine.inPower);
            }

            if (this.lastInSpeed != this.machine.inSpeed)
            {
                icrafting.sendProgressBarUpdate(this, 2, this.machine.inSpeed);
            }

            if (this.lastPower != this.machine.storage.getPowerStored())
            {
                icrafting.sendProgressBarUpdate(this, 3, this.machine.storage.getPowerStored());
            }

            if (this.lastSpeed != this.machine.storage.getSpeedStored())
            {
                icrafting.sendProgressBarUpdate(this, 4, (int) this.machine.storage.getSpeedStored());
            }

        }

        this.lastWorkTime = this.machine.machineWorkProgressTime;
        this.lastInPower = this.machine.inPower;
        this.lastInSpeed = this.machine.inSpeed;
        this.lastPower = this.machine.storage.getPowerStored();
        this.lastSpeed = (int) this.machine.storage.getSpeedStored();
    }

	@Override
	@SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2)
    {
        if (par1 == 0)
        {
            this.machine.machineWorkProgressTime = par2;
        }

        if (par1 == 1)
        {
            this.machine.inPower = par2;
        }

        if (par1 == 2)
        {
            this.machine.inSpeed = par2;
        }

        if (par1 == 3)
        {
            this.machine.storage.setPowerStored(par2);
        }

        if (par1 == 4)
        {
            this.machine.storage.setEnergyStored(par2);
        }

    }

	@Override
	public boolean canInteractWith(EntityPlayer par1EntityPlayer)
    {
        return this.machine.isUseableByPlayer(par1EntityPlayer);
    }

	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 == 2)
            {
                if (!this.mergeItemStack(itemstack1, 3, 39, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (par2 != 1 && par2 != 0)
            {
                if (machine.getResult(itemstack1) != null)
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (GearForceItem.manager.isGearForceItem(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
                    {
                        return null;
                    }
                }
                else if (par2 >= 3 && par2 < 30)
                {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false))
                    {
                        return null;
                    }
                }
                else if (par2 >= 30 && par2 < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
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

            slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
        }

        return itemstack;
    }

}
