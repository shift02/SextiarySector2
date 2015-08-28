package shift.sextiarysector.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import shift.sextiarysector.api.equipment.EquipmentType;
import shift.sextiarysector.api.equipment.IEquipment;

public class ItemRing extends Item implements IEquipment {

	public ItemRing() {
		this.setMaxStackSize(1);
	}

	@Override
	public boolean canTakeStack(EquipmentType equipment, ItemStack stack, EntityPlayer player) {
		return equipment.ordinal() == EquipmentType.Other.ordinal();
	}

	@Override
	public boolean isItemValid(EquipmentType equipment, ItemStack stack) {
		return equipment.ordinal() == EquipmentType.Other.ordinal();
	}

	@Override
	public void onUpdate(EquipmentType equipment, ItemStack stack, World world, Entity player, int slot) {

	}

	@Override
	public boolean canDrop(EquipmentType equipment, ItemStack stack, EntityPlayer player) {
		return true;
	}

}
