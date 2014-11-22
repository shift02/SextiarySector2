package shift.sextiarysector.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import shift.sextiarysector.player.EquipmentType;

public interface IEquipment {

	boolean canTakeStack(EquipmentType equipment,ItemStack stack, EntityPlayer player);

	boolean isItemValid(EquipmentType equipment, ItemStack stack);

}
