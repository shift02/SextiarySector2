package shift.sextiarysector.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.api.equipment.EquipmentType;
import shift.sextiarysector.api.gearforce.item.IGearForceGridItem;

public class ItemGFContactLenses extends ItemContactLenses implements IGearForceGridItem {

	public ItemGFContactLenses() {
		this.setCreativeTab(SextiarySectorAPI.TabSSPlayer);
	}

	@Override
	public boolean canTakeStack(EquipmentType equipment, ItemStack stack, EntityPlayer player) {
		return equipment.ordinal() == EquipmentType.Face.ordinal();
	}

	@Override
	public boolean isItemValid(EquipmentType equipment, ItemStack stack) {
		return equipment.ordinal() == EquipmentType.Face.ordinal();
	}

	@Override
	public void onUpdate(EquipmentType equipment, ItemStack stack, World world, Entity player, int slot) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public boolean canDrop(EquipmentType equipment, ItemStack stack, EntityPlayer player) {
		return true;
	}

}
