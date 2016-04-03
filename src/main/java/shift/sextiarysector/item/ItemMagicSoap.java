/*
* 作成者: Shift02
* 作成日: 2016/03/29 - 12:52:19
*/
package shift.sextiarysector.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemMagicSoap extends ItemSoap {

    private Potion potion;

    public ItemMagicSoap(Potion potion) {
        super();
        this.potion = potion;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {

        if (potion != null) {
            player.addPotionEffect(new PotionEffect(potion.getId(), 200 * 10, 0));
        }

        return super.onItemRightClick(itemStack, world, player);
    }

}
