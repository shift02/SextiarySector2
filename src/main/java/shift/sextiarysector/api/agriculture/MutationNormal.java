package shift.sextiarysector.api.agriculture;

import net.minecraft.item.ItemStack;

/**
 * SS2で追加される「普通の肥料」の突然変異用のクラス
 * @author Shift02
 *
 */
public class MutationNormal extends MutationBase {

    public MutationNormal(ItemStack before, ItemStack after) {
        super(AgricultureAPI.normal, before, after);
    }

}
