package shift.sextiarysector.api.machine.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public interface IGearForceItemManager {

	public int reduceEnergy(ItemStack itemStack, int power, int speed, boolean simulate);

	public int addEnergy(ItemStack itemStack, int power, int speed, boolean simulate);

	public int getSpeed(ItemStack theItem);

	public int getPower(ItemStack theItem);

	public boolean setEnergy(ItemStack itemStack, int power, int speed);

	boolean canUse(ItemStack itemStack, int speed);

	boolean use(ItemStack itemStack, int speed, EntityLivingBase entity);

	boolean isGearForceItem(ItemStack item);

}
