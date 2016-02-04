package shift.sextiarysector.module;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.item.ItemStack;
import shift.sextiarysector.api.agriculture.FarmlandType;
import shift.sextiarysector.api.agriculture.IFertilizer2;
import shift.sextiarysector.api.agriculture.IFertilizerManager2;

public class FertilizerManager implements IFertilizerManager2 {

    //public static HashMap<ItemStack,IFertilizer> fertilizers = new HashMap<ItemStack,IFertilizer>();
    public static ArrayList<IFertilizer2> fertilizerIcons = new ArrayList<IFertilizer2>();

    public static ArrayList<IFertilizer2> fertilizers = new ArrayList<IFertilizer2>();

    public static ArrayList<IFertilizer2> normal = new ArrayList<IFertilizer2>();
    public static ArrayList<IFertilizer2> paddy = new ArrayList<IFertilizer2>();
    //public static HashMap<String,IFertilizer> fertilizersS = new HashMap<String,IFertilizer>();

    private static Random r = new Random();

    @Override
    public void registerFertilizer(FarmlandType type, IFertilizer2 fertilizer) {
        //fertilizersS.put(fertilizer.getName(), fertilizer);
        fertilizers.add(fertilizer);

        this.addIcon(fertilizer);

        switch (type) {

            case Normal:
                normal.add(fertilizer);
                break;
            case Paddy:
                paddy.add(fertilizer);
                break;

        }
    }

    private void addIcon(IFertilizer2 fertilizer) {

        for (IFertilizer2 f : fertilizerIcons) {

            if (this.checkItem(fertilizer.getFertilizer(), f.getFertilizer())) return;

        }

        fertilizerIcons.add(fertilizer);

    }

    public static IFertilizer2 getFertilizer(ItemStack item) {
        if (item == null) {
            return null;
        }

        for (IFertilizer2 f : fertilizers) {

            if (checkItem(item, f.getFertilizer())) {
                return f;
            }

        }

        return null;

    }

    public static IFertilizer2 getFertilizerIcon(ItemStack item) {
        if (item == null) {
            return null;
        }

        for (IFertilizer2 f : fertilizerIcons) {

            if (checkItem(item, f.getFertilizer())) {
                return f;
            }

        }

        return null;
    }

    private static boolean checkItem(ItemStack p_151397_1_, ItemStack p_151397_2_) {
        return p_151397_2_.getItem() == p_151397_1_.getItem() && (p_151397_2_.getItemDamage() == 32767 || p_151397_2_.getItemDamage() == p_151397_1_.getItemDamage());
    }

    public static boolean hasFertilizer(ItemStack item) {
        return getFertilizer(item) != null;
    }

    public static ItemStack getAfter(ItemStack fertilizer, ItemStack before) {

        if (fertilizer == null || before == null) {
            return null;
        }

        for (IFertilizer2 f : fertilizers) {

            if (checkItem(fertilizer, f.getFertilizer()) && checkItem(before, f.getBefore())) {
                return f.getAfter().copy();
            }

        }

        return null;
    }

    public static boolean canMutation(ItemStack fertilizer, ItemStack before) {

        if (fertilizer == null || before == null) {
            return false;
        }

        for (IFertilizer2 f : fertilizers) {

            if (checkItem(fertilizer, f.getFertilizer()) && checkItem(before, f.getBefore())) {

                if (f.getProbability() == 0) {
                    return true;
                }

                return 0 == r.nextInt(f.getProbability());
            }

        }

        return false;

    }

}
