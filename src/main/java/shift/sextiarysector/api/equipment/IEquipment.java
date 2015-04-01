package shift.sextiarysector.api.equipment;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IEquipment {

	boolean canTakeStack(EquipmentType equipment, ItemStack stack, EntityPlayer player);

	boolean isItemValid(EquipmentType equipment, ItemStack stack);

	void onUpdate(EquipmentType equipment, ItemStack stack, World world, Entity player, int slot);

	boolean canDrop(EquipmentType equipment, ItemStack stack, EntityPlayer player);

}
