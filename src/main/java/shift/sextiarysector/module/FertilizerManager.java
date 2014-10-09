package shift.sextiarysector.module;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import net.minecraft.item.ItemStack;
import shift.sextiarysector.api.agriculture.IFertilizer;
import shift.sextiarysector.api.agriculture.IFertilizerManager;

public class FertilizerManager implements IFertilizerManager {

	public static HashMap<ItemStack,IFertilizer> fertilizers = new HashMap<ItemStack,IFertilizer>();
	public static HashMap<String,IFertilizer> fertilizersS = new HashMap<String,IFertilizer>();

	@Override
	public void registerFertilizer(ItemStack item, IFertilizer fertilizer) {
		fertilizers.put(item, fertilizer);
		fertilizersS.put(fertilizer.getName(), fertilizer);
	}

	public static IFertilizer getFertilizer(ItemStack item)
    {
		if (item == null)
        {
            return null;
        }

		Iterator iterator = fertilizers.entrySet().iterator();
        Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return null;
            }

            entry = (Entry)iterator.next();
        }
        while (!checkItem(item, (ItemStack)entry.getKey()));

        return (IFertilizer) entry.getValue();

    }

	private static boolean checkItem(ItemStack p_151397_1_, ItemStack p_151397_2_)
    {
        return p_151397_2_.getItem() == p_151397_1_.getItem() && (p_151397_2_.getItemDamage() == 32767 || p_151397_2_.getItemDamage() == p_151397_1_.getItemDamage());
    }

}
