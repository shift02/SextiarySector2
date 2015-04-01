package shift.sextiarysector.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import shift.sextiarysector.api.equipment.EquipmentType;

public interface ISSEquipment {

	String getTabName(EquipmentType equipment, ItemStack stack, EntityPlayer player);

	boolean shouldAddToList(EquipmentType equipment, ItemStack stack, EntityPlayer player);

	void onTabClicked(EquipmentType equipment, ItemStack stack, EntityPlayer player);

}
