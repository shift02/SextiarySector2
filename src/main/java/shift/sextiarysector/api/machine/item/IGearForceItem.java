package shift.sextiarysector.api.machine.item;

import net.minecraft.item.ItemStack;

/**
 * IGearForceItem ItemでGFを扱えるようになるインターフェース<br>
 * Itemに実装してください
 * @see ItemGearForce
 * @version 1.0.0
 * @author Shift02
 */
public interface IGearForceItem {

	int getMaxPowerStored(ItemStack container);

	int getMaxSpeedStored(ItemStack container);

	boolean canSetSlot(int power);

	boolean isCustomDamage(ItemStack container);

}
