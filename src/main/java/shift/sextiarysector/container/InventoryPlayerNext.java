package shift.sextiarysector.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import shift.sextiarysector.api.equipment.EquipmentType;
import shift.sextiarysector.api.equipment.IEquipment;

public class InventoryPlayerNext implements IInventory {

    ItemBox items = new ItemBox("Base", 20, 1);

    //private final EntityPlayer player;

    public InventoryPlayerNext() {
        //this.player = player;
    }

    @Override
    public int getSizeInventory() {
        return items.getSizeInventory();
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return items.getStackInSlot(i);
    }

    @Override
    public ItemStack decrStackSize(int i, int j) {
        return items.decrStackSize(i, j);
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        return items.getStackInSlotOnClosing(i);
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack) {
        items.setInventorySlotContents(i, itemstack);
    }

    @Override
    public String getInventoryName() {
        return "gui";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return items.getInventoryStackLimit();
    }

    @Override
    public void markDirty() {
        items.onInventoryChanged();
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        return true;
    }

    @Override
    public void openInventory() {
        this.markDirty();
    }

    @Override
    public void closeInventory() {
        this.markDirty();
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {

        return true;
    }

    public void dropAllItems(EntityPlayer player) {
        int i;

        for (i = 0; i < this.items.getSizeInventory(); ++i) {

            ItemStack item = this.items.getStackInSlot(i);

            if (item == null) continue;

            if (item.getItem() instanceof IEquipment) {

                if (!((IEquipment) item.getItem()).canDrop(EquipmentType.getEquipmentTypeFromSlot(i), item, player)) continue;
                player.func_146097_a(item, true, false);
                this.items.setInventorySlotContents(i, null);

            } else {
                player.func_146097_a(item, true, false);
                this.items.setInventorySlotContents(i, null);
            }

        }

    }

    //NBT
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        this.items.writeToNBT(nbt);
        ;
        return nbt;
    }

    public void readFromNBT(NBTTagCompound nbt) {
        this.items.readFromNBT(nbt);
    }

}
