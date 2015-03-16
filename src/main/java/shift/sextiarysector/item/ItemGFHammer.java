package shift.sextiarysector.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import shift.sextiarysector.api.machine.item.IHammer;
import shift.sextiarysector.api.machine.item.ItemGearForce;

public class ItemGFHammer extends ItemGearForce implements IHammer {

	public ItemGFHammer(int power, int maxSpeed, int slot) {
		super(power, maxSpeed, slot);
		this.setMaxStackSize(1);
	}

	@Override
	public boolean canUse(ItemStack item, EntityPlayer player, int damage) {
		return false;
	}

	@Override
	public boolean use(ItemStack item, EntityPlayer player, int damage) {
		return false;
	}

}
