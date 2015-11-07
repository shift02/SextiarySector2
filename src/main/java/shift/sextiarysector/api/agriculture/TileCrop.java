package shift.sextiarysector.api.agriculture;

import net.minecraft.world.World;

/**
 * 作物本体にアクセスするためのinterface
 * @author Shift02
 */
public interface TileCrop {

    /**
     * 作物の成長日数
     * @return 日
     */
    public int getDay();

    /**
     * 作物を強制的に成長させる
     * @param day 成長させる日<br>  例 作物の成長が2の時にdoGrowth(2)を呼ぶと4になる
     */
    public void doGrowth(int day);

    /**
     * 作物の成長を戻す トマトみたいな作物で使用する
     * @param day 成長を戻す日
     */
    public void doDecline(int day);

    /**
     * 作物を枯らす
     */
    public void doWither();

    public World getWorld();

    public int getX();

    public int getY();

    public int getZ();

}
