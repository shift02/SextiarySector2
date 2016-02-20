/*
* 作成者: Shift02
* 作成日: 2016/02/15 - 16:45:28
*/
package shift.sextiarysector.agriculture;

import net.minecraft.item.Item;
import shift.sextiarysector.api.agriculture.AgricultureAPI;
import shift.sextiarysector.api.agriculture.TileFarmland;
import shift.sextiarysector.api.season.Season;

/**
 * 水田用の作物
 * @author Shift02
 *
 */
public class CropPaddy extends CropBase {

    public CropPaddy(String name, Item crop, Season[] season, int[] day) {
        super(name, crop, season, day);
    }

    @Override
    public boolean canBlockStay(String name, TileFarmland farmland) {
        return name.equals(AgricultureAPI.PADDY);
    }

}
