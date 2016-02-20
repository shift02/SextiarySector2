package shift.sextiarysector.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import shift.sextiarysector.api.equipment.EquipmentType;

public class ItemProtectionRing extends ItemRing {

    @Override
    public boolean canDrop(EquipmentType equipment, ItemStack stack, EntityPlayer player) {
        return false;
    }

}
