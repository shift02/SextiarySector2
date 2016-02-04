package shift.sextiarysector.api.agriculture;

import net.minecraft.item.ItemStack;

/**
 *
 * 突然変異用のクラス
 * @author Shift02
 *
 */
public interface IMutation {

    public IFertilizer getFertilizer();

    public ItemStack getBefore();

    public ItemStack getAfter();

    /**
     * 突然変異の発生しやすさ<br>
     * 0だと確実に発生する
     * @return  0 <=
     */
    public int getProbability();

}
