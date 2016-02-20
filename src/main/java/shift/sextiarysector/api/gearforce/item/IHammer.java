package shift.sextiarysector.api.gearforce.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IHammer {

    public boolean canUse(ItemStack item, EntityPlayer player, int damage);

    public boolean use(ItemStack item, EntityPlayer player, int damage);

}
