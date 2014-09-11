package shift.sextiarysector.api.machine.item;

import net.minecraft.item.ItemStack;

public interface IGearForceItem {

	int getMaxPowerStored(ItemStack container);

	int getMaxSpeedStored(ItemStack container);


}
