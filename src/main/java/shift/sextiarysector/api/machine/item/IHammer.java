package shift.sextiarysector.api.machine.item;

import net.minecraft.item.ItemStack;

public interface IHammer {

	public boolean canUse(ItemStack item);

	public boolean use(ItemStack item);

}
