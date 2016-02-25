/*
* 作成者: Shift02
* 作成日: 2016/02/25 - 15:20:09
*/
package shift.sextiarysector.agriculture;

import net.minecraft.item.ItemStack;
import shift.sextiarysector.api.agriculture.AgricultureAPI;
import shift.sextiarysector.api.agriculture.CropRendererType;
import shift.sextiarysector.api.agriculture.TileFarmland;
import shift.sextiarysector.api.season.Season;

/**
 * キノコ用の作物クラス
 * @author Shift02
 *
 */
public class CropMushroom extends CropBase {

    public CropMushroom(String name, ItemStack crop, Season[] season, int[] day) {
        super(name, crop, season, day);
    }

    @Override
    public boolean canBlockStay(String name, TileFarmland farmland) {
        return name.equals(AgricultureAPI.WOOD);
    }

    @Override
    public CropRendererType getRenderType() {
        return CropRendererType.Cross;
    }

}
