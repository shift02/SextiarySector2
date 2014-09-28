package shift.sextiarysector.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import shift.sextiarysector.api.machine.item.IHammer;

public class ItemHammer extends Item implements IHammer{

	@Override
	public boolean canUse(ItemStack item) {
		return true;
	}

	@Override
	public boolean use(ItemStack item) {
		return true;
	}

}
