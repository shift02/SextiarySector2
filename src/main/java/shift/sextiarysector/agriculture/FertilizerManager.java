package shift.sextiarysector.agriculture;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.ItemStack;
import shift.sextiarysector.api.agriculture.IFertilizer;
import shift.sextiarysector.api.agriculture.IFertilizerManager;

public class FertilizerManager implements IFertilizerManager {

    public Map<String, IFertilizer> fertilizers = new HashMap<String, IFertilizer>();

    @Override
    public void registerFertilizer(IFertilizer fertilizer) {

        if (fertilizers.containsKey(fertilizer.getName())) throw new IllegalArgumentException("This name is already registered : " + fertilizer.getName());

        fertilizers.put(fertilizer.getName(), fertilizer);

    }

    public IFertilizer getFertilizer(ItemStack item) {

        for (IFertilizer f : fertilizers.values()) {

            if (f.getFertilizer().isItemEqual(item)) return f;

        }

        return null;

    }

    public IFertilizer getFertilizer(String name) {

        for (IFertilizer f : fertilizers.values()) {

            if (f.getName().equals(name)) return f;

        }

        return null;

    }

}
