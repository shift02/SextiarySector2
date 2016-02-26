package shift.sextiarysector.api.agriculture;

import net.minecraft.item.ItemStack;

/**
 *
 * 突然変異用のクラス
 * @author Shift02
 *
 */
public interface IMutation {

    /**
     * どの肥料で変異が発生するか
     * @return 肥料
     */
    public IFertilizer getFertilizer();

    /**
     * 変異前の作物
     * @return 作物
     */
    public ItemStack getBefore();

    /**
     * 変異後の作物
     * @return 作物
     */
    public ItemStack getAfter();

    /**
     * 突然変異の発生しやすさ<br>
     * 0だと確実に発生する
     * @return  0 <=
     */
    public int getProbability();

}
