package shift.sextiarysector.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import shift.sextiarysector.api.IEquipment;
import shift.sextiarysector.player.EquipmentType;

public class ItemUnit extends Item implements IEquipment {

	public ItemUnit() {
		this.maxStackSize = 1;
	}

	@Override
	public boolean canTakeStack(EquipmentType equipment, ItemStack stack, EntityPlayer player) {
		return equipment.equals(EquipmentType.Unit);
	}

	@Override
	public boolean isItemValid(EquipmentType equipment, ItemStack stack) {
		return equipment.equals(EquipmentType.Unit);
	}

}
