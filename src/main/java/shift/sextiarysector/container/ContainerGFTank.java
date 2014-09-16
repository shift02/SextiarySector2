package shift.sextiarysector.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import shift.sextiarysector.api.machine.item.GearForceItem;
import shift.sextiarysector.tileentity.TileEntityGFTank;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerGFTank extends Container{

	private final TileEntityGFTank tank;
    //private int lastWorkTime;
    private int lastInPower;
    private int lastInSpeed;
    private int lastOutPower;
    private int lastOutSpeed;
    private int lastPower;
    private int lastSpeed;

	public ContainerGFTank(InventoryPlayer par1InventoryPlayer, TileEntityGFTank par2TileEntityFurnace)
    {
        this.tank = par2TileEntityFurnace;
        this.addSlotToContainer(new SlotGF(par2TileEntityFurnace, 0, 116, 17,1));
        this.addSlotToContainer(new SlotGF(par2TileEntityFurnace, 1, 44, 53,1));
        //this.addSlotToContainer(new SlotFurnace(par1InventoryPlayer.player, par2TileEntityFurnace, 2, 116, 35));
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
        //par1ICrafting.sendProgressBarUpdate(this, 0, this.tank.machineWorkProgressTime);
        par1ICrafting.sendProgressBarUpdate(this, 1, this.tank.inPower);
        par1ICrafting.sendProgressBarUpdate(this, 2, this.tank.inSpeed);
        par1ICrafting.sendProgressBarUpdate(this, 3, this.tank.outPower);
        par1ICrafting.sendProgressBarUpdate(this, 4, this.tank.outSpeed);
        par1ICrafting.sendProgressBarUpdate(this, 5, this.tank.storage.getPowerStored());
        par1ICrafting.sendProgressBarUpdate(this, 6, (int) this.tank.storage.getSpeedStored());
    }

	@Override
	public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);

            /*
            if (this.lastWorkTime != this.tank.machineWorkProgressTime)
            {
                icrafting.sendProgressBarUpdate(this, 0, this.tank.machineWorkProgressTime);
            }*/

            if (this.lastInPower != this.tank.inPower)
            {
                icrafting.sendProgressBarUpdate(this, 1, this.tank.inPower);
            }

            if (this.lastInSpeed != this.tank.inSpeed)
            {
                icrafting.sendProgressBarUpdate(this, 2, this.tank.inSpeed);
            }

            if (this.lastOutPower != this.tank.outPower)
            {
                icrafting.sendProgressBarUpdate(this, 3, this.tank.outPower);
            }

            if (this.lastOutSpeed != this.tank.outSpeed)
            {
                icrafting.sendProgressBarUpdate(this, 4, this.tank.outSpeed);
            }

            if (this.lastPower != this.tank.storage.getPowerStored())
            {
                icrafting.sendProgressBarUpdate(this, 5, this.tank.storage.getPowerStored());
            }

            if (this.lastSpeed != this.tank.storage.getSpeedStored())
            {
                icrafting.sendProgressBarUpdate(this, 6, (int) this.tank.storage.getSpeedStored());
            }

        }

        //this.lastWorkTime = this.tank.machineWorkProgressTime;
        this.lastInPower = this.tank.inPower;
        this.lastInSpeed = this.tank.inSpeed;
        this.lastOutPower = this.tank.outPower;
        this.lastOutSpeed = this.tank.outSpeed;
        this.lastPower = this.tank.storage.getPowerStored();
        this.lastSpeed = (int) this.tank.storage.getSpeedStored();
    }

	@Override
	@SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2)
    {
        /*if (par1 == 0)
        {
            this.tank.machineWorkProgressTime = par2;
        }*/

        if (par1 == 1)
        {
            this.tank.inPower = par2;
        }

        if (par1 == 2)
        {
            this.tank.inSpeed = par2;
        }

        if (par1 == 3)
        {
            this.tank.outPower = par2;
        }

        if (par1 == 4)
        {
            this.tank.outSpeed = par2;
        }

        if (par1 == 5)
        {
            this.tank.storage.setPowerStored(par2);
        }

        if (par1 == 6)
        {
            this.tank.storage.setEnergyStored(par2);
        }

    }

	@Override
	public boolean canInteractWith(EntityPlayer par1EntityPlayer)
    {
        return this.tank.isUseableByPlayer(par1EntityPlayer);
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
                /*if (false)//)tank.getResult(itemstack1) != null)
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return null;
                    }
                }
                else */if (GearForceItem.manager.isGearForceItem(itemstack1))
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
