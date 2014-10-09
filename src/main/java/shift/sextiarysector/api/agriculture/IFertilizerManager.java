package shift.sextiarysector.api.agriculture;

import net.minecraft.item.ItemStack;

public interface IFertilizerManager {

	public void registerFertilizer(ItemStack item, IFertilizer fertilizer);

}
