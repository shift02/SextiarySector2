package shift.sextiarysector.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import thaumcraft.api.IGoggles;

public class ItemMagicContactLenses extends ItemContactLenses implements IGoggles {

	@Override
	public boolean showIngamePopups(ItemStack itemstack, EntityLivingBase player) {
		return true;
	}

}
