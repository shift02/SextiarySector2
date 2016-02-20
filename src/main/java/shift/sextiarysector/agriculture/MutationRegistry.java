package shift.sextiarysector.agriculture;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.item.ItemStack;
import shift.sextiarysector.api.agriculture.IFertilizer;
import shift.sextiarysector.api.agriculture.IMutation;
import shift.sextiarysector.api.agriculture.IMutationRegistry;

public class MutationRegistry implements IMutationRegistry {

    public ArrayList<IMutation> mutations = new ArrayList<IMutation>();

    private Random r = new Random();

    @Override
    public void registeMutation(IMutation mutation) {

        mutations.add(mutation);

    }

    @Override
    public ItemStack getMutationItem(IFertilizer fertilizer, ItemStack crop) {

        if (crop == null) return null;
        if (fertilizer == null) return crop;

        IMutation m = null;

        for (IMutation f : mutations) {

            if (!fertilizer.getName().equals(f.getFertilizer().getName())) continue;
            if (!f.getBefore().isItemEqual(crop)) continue;

            m = f;
            break;

        }

        if (m == null) return crop;

        if (m.getProbability() == 0) return m.getAfter().copy();

        if (r.nextInt(m.getProbability()) == 0) return m.getAfter().copy();

        return crop;

    }

}
