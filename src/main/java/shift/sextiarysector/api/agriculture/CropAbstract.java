/*
* 作成者: Shift02
* 作成日: 2016/03/03 - 15:32:33
*/
package shift.sextiarysector.api.agriculture;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

/**
 * 作物を追加するための基礎クラス <br>
 * 特に理由がない場合はこのクラスを継承してください。
 * @author Shift02
 *
 */
public abstract class CropAbstract implements ICrop {

    @Override
    public void update(TileCrop crop, TileFarmland farmland) {

    }

    @Override
    public boolean click(TileCrop crop, TileFarmland farmland, EntityPlayer player) {
        return false;
    }

    @Override
    public float getHardness(TileCrop crop, TileFarmland farmland) {
        return 3.5f;
    }

    @Override
    public int getLightValue(TileCrop crop, TileFarmland farmland) {
        return 0;
    }

    @Override
    public void onEntityCollidedWithCrop(TileCrop crop, TileFarmland farmland, Entity entity) {

    }

    @Override
    public boolean isBurning(TileCrop crop, TileFarmland farmland) {
        return false;
    }

    @Override
    public float getEnchantPowerBonus(TileCrop crop, TileFarmland farmland) {
        return 0;
    }

}
