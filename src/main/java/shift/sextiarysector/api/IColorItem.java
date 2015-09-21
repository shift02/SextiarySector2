package shift.sextiarysector.api;

import net.minecraft.item.ItemStack;

public interface IColorItem {

    public EnumColor getColor(ItemStack item);

    public void useItem(ItemStack item);

    public boolean canUse(ItemStack item);

}
