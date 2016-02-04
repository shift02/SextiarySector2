package shift.sextiarysector.api.agriculture;

import net.minecraft.item.ItemStack;

/**
 * 突然変異用のシンプルなクラス
 *
 * @author Shift02
 *
 */
public class MutationBase implements IMutation {

    private IFertilizer fertilizer;

    private ItemStack before;
    private ItemStack after;

    public MutationBase(IFertilizer fertilizer, ItemStack before, ItemStack after) {

        this.fertilizer = fertilizer;
        this.before = before;
        this.after = after;

    }

    @Override
    public IFertilizer getFertilizer() {
        return this.fertilizer;
    }

    @Override
    public ItemStack getBefore() {
        return this.before;
    }

    @Override
    public ItemStack getAfter() {
        return this.after;
    }

    @Override
    public int getProbability() {
        return 255;
    }

}
