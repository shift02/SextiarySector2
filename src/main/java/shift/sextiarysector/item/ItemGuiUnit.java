package shift.sextiarysector.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import shift.sextiarysector.api.equipment.EquipmentType;
import shift.sextiarysector.api.equipment.IEquipment;
import shift.sextiarysector.packet.PacketGuiId;
import shift.sextiarysector.packet.SSPacketHandler;

public class ItemGuiUnit extends Item implements IEquipment, ISSEquipment {

    public int guiID;

    public ItemGuiUnit(int gui) {
        this.guiID = gui;
        this.setMaxStackSize(1);
    }

    @Override
    public boolean canTakeStack(EquipmentType equipment, ItemStack stack, EntityPlayer player) {
        return equipment.equals(EquipmentType.Unit);
    }

    @Override
    public boolean isItemValid(EquipmentType equipment, ItemStack stack) {
        return equipment.equals(EquipmentType.Unit);
    }

    @Override
    public boolean shouldAddToList(EquipmentType equipment, ItemStack stack, EntityPlayer player) {
        return true;
    }

    @Override
    public void onTabClicked(EquipmentType equipment, ItemStack stack, EntityPlayer player) {
        SSPacketHandler.INSTANCE.sendToServer(new PacketGuiId(guiID));

    }

    @Override
    public String getTabName(EquipmentType equipment, ItemStack stack, EntityPlayer player) {
        return this.getUnlocalizedName();
    }

    @Override
    public void onUpdate(EquipmentType equipment, ItemStack stack, World world, Entity player, int slot) {

    }

    @Override
    public boolean canDrop(EquipmentType equipment, ItemStack stack, EntityPlayer player) {
        return true;
    }

}
