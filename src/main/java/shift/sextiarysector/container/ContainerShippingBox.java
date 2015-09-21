package shift.sextiarysector.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import shift.sextiarysector.SSItems;
import shift.sextiarysector.player.EntityPlayerManager;

public class ContainerShippingBox extends Container {

    public InventoryShippingBox shippingBox;
    private final EntityPlayer player;
    private final int numRows;
    private static final String __OBFID = "CL_00001742";

    public ContainerShippingBox(InventoryPlayer par1InventoryPlayer, EntityPlayer p_i1819_3_) {
        this.player = p_i1819_3_;
        this.shippingBox = EntityPlayerManager.getShippingBoxStats(p_i1819_3_).inventory;
        this.numRows = 3;

        int i = 0;

        for (int j = 0; j < 3; ++j) {
            for (int k = 0; k < 9; ++k) {
                this.addSlotToContainer(new Slot(shippingBox, k + j * 9, 8 + k * 18, 17 + j * 18));
            }
        }

        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new SlotContainer(par1InventoryPlayer, i, 8 + i * 18, 142, SSItems.rucksack));
        }

        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new SlotContainer(par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18, SSItems.rucksack));
            }
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer p_75145_1_) {
        return true;//this.lowerChestInventory.isUseableByPlayer(p_75145_1_);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer p_82846_1_, int p_82846_2_) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(p_82846_2_);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (p_82846_2_ < this.numRows * 9) {
                if (!this.mergeItemStack(itemstack1, this.numRows * 9, this.inventorySlots.size(), true)) {
                    return null;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, this.numRows * 9, false)) {
                return null;
            }

            if (itemstack1.stackSize == 0) {
                slot.putStack((ItemStack) null);
            } else {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }

    @Override
    public void onContainerClosed(EntityPlayer p_75134_1_) {
        super.onContainerClosed(p_75134_1_);
    }

}
