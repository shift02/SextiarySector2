package shift.sextiarysector.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import shift.sextiarysector.api.IEquipment;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.api.machine.item.IGearForceGridItem;
import shift.sextiarysector.player.EquipmentType;

public class ItemGFContactLenses  extends Item implements IEquipment, IGearForceGridItem{

	public ItemGFContactLenses(){
		this.setCreativeTab(SextiarySectorAPI.TabSSPlayer);
	}


	@Override
	public boolean canTakeStack(EquipmentType equipment, ItemStack stack,EntityPlayer player) {
		return equipment.ordinal() == EquipmentType.Face.ordinal();
	}

	@Override
	public boolean isItemValid(EquipmentType equipment, ItemStack stack) {
		return equipment.ordinal() == EquipmentType.Face.ordinal();
	}

}
