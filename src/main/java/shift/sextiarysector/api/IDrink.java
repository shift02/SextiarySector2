package shift.sextiarysector.api;

import net.minecraft.item.ItemStack;

/**
 * 食べ物に実装すると水分を回復するようになります
 * @author Shift02
 */
public interface IDrink {

    /**
     * @return 水分の回復量
     */
    int getMoisture(ItemStack drink);

    /**
     * @return 隠し水分の回復量
     */
    float getMoistureSaturation(ItemStack drink);

}
