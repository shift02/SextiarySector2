package shift.sextiarysector.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.api.gearforce.item.GearForceItemAPI;
import shift.sextiarysector.tileentity.TileEntityFluidFGFMachineBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerFluidGFMachineBase extends Container {
    private final TileEntityFluidFGFMachineBase tileFurnace;
    private int lastWorkTime;
    private int lastInPower;
    private int lastInSpeed;
    private int lastPower;
    private int lastSpeed;

    //private int lastFluid;
    //private int lastFluidID;

    public ContainerFluidGFMachineBase(InventoryPlayer p_i1812_1_, TileEntityFluidFGFMachineBase p_i1812_2_) {
        this.tileFurnace = p_i1812_2_;
        this.addSlotToContainer(new Slot(p_i1812_2_, 0, 56, 17));
        this.addSlotToContainer(new SlotGF(p_i1812_2_, 1, 56, 53, this.tileFurnace.getMaxPowerStored(ForgeDirection.UP)));
        this.addSlotToContainer(new SlotFurnace(p_i1812_1_.player, p_i1812_2_, 2, 107, 53));
        this.addSlotToContainer(new SlotFurnace(p_i1812_1_.player, p_i1812_2_, 3, 125, 53));
        this.addSlotToContainer(new SlotFluid(p_i1812_2_, 4, 152, 17));
        this.addSlotToContainer(new SlotFluid(p_i1812_2_, 5, 152, 53));
        int i;

        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(p_i1812_1_, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(p_i1812_1_, i, 8 + i * 18, 142));
        }
    }

    @Override
    public void addCraftingToCrafters(ICrafting par1ICrafting) {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate(this, 0, this.tileFurnace.machineWorkProgressTime);
        par1ICrafting.sendProgressBarUpdate(this, 1, this.tileFurnace.inPower);
        par1ICrafting.sendProgressBarUpdate(this, 2, this.tileFurnace.inSpeed);
        par1ICrafting.sendProgressBarUpdate(this, 3, this.tileFurnace.storage.getPowerStored());
        par1ICrafting.sendProgressBarUpdate(this, 4, this.tileFurnace.storage.getSpeedStored());
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i) {
            ICrafting icrafting = (ICrafting) this.crafters.get(i);

            if (this.lastWorkTime != this.tileFurnace.machineWorkProgressTime) {
                icrafting.sendProgressBarUpdate(this, 0, this.tileFurnace.machineWorkProgressTime);
            }

            if (this.lastInPower != this.tileFurnace.inPower) {
                icrafting.sendProgressBarUpdate(this, 1, this.tileFurnace.inPower);
            }

            if (this.lastInSpeed != this.tileFurnace.inSpeed) {
                icrafting.sendProgressBarUpdate(this, 2, this.tileFurnace.inSpeed);
            }

            if (this.lastPower != this.tileFurnace.storage.getPowerStored()) {
                icrafting.sendProgressBarUpdate(this, 3, this.tileFurnace.storage.getPowerStored());
            }

            if (this.lastSpeed != this.tileFurnace.storage.getSpeedStored()) {
                icrafting.sendProgressBarUpdate(this, 4, this.tileFurnace.storage.getSpeedStored());
            }

        }

        this.lastWorkTime = this.tileFurnace.machineWorkProgressTime;
        this.lastInPower = this.tileFurnace.inPower;
        this.lastInSpeed = this.tileFurnace.inSpeed;
        this.lastPower = this.tileFurnace.storage.getPowerStored();
        this.lastSpeed = this.tileFurnace.storage.getSpeedStored();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
        if (par1 == 0) {
            this.tileFurnace.machineWorkProgressTime = par2;
        }

        if (par1 == 1) {
            this.tileFurnace.inPower = par2;
        }

        if (par1 == 2) {
            this.tileFurnace.inSpeed = par2;
        }

        if (par1 == 3) {
            this.tileFurnace.storage.setPowerStored(par2);
        }

        if (par1 == 4) {
            this.tileFurnace.storage.setEnergyStored(par2);
        }

    }

    @Override
    public boolean canInteractWith(EntityPlayer p_75145_1_) {
        return this.tileFurnace.isUseableByPlayer(p_75145_1_);
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    @Override
    public ItemStack transferStackInSlot(EntityPlayer p_82846_1_, int p_82846_2_) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(p_82846_2_);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (p_82846_2_ == 2) {
                if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } else if (p_82846_2_ != 1 && p_82846_2_ != 0) {
                if (tileFurnace.getResult(itemstack1) != null) {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
                        return null;
                    }
                } else if (GearForceItemAPI.manager.isGearForceItem(itemstack1)) {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
                        return null;
                    }
                } else if (p_82846_2_ >= 6 && p_82846_2_ < 33) {
                    if (!this.mergeItemStack(itemstack1, 33, 42, false)) {
                        return null;
                    }
                } else if (p_82846_2_ >= 33 && p_82846_2_ < 42 && !this.mergeItemStack(itemstack1, 6, 33, false)) {
                    return null;
                }
            } else if (!this.mergeItemStack(itemstack1, 6, 42, false)) {
                return null;
            }

            if (itemstack1.stackSize == 0) {
                slot.putStack((ItemStack) null);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(p_82846_1_, itemstack1);
        }

        return itemstack;
    }
}
