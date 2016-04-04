/*
* 作成者: Shift02
* 作成日: 2016/03/28 - 16:55:25
*/
package shift.sextiarysector.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import shift.sextiarysector.api.SextiarySectorAPI;

public class ItemSoap extends Item {

    public ItemSoap() {
        this.maxStackSize = 1;
        this.setMaxDamage(7);
        this.setCreativeTab(SextiarySectorAPI.TabSSCore);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {

        itemStack.damageItem(1, player);
        SextiarySectorAPI.addStaminaStats(player, 2, 2.0f);

        generateRandomParticles(player, "happyVillager");
        return itemStack;

    }

    protected void generateRandomParticles(EntityPlayer player, String particleName) {
        for (int i = 0; i < 8; ++i) {

            double d0 = player.getRNG().nextGaussian() * 0.02D;
            double d1 = player.getRNG().nextGaussian() * 0.02D;
            double d2 = player.getRNG().nextGaussian() * 0.02D;
            player.worldObj.spawnParticle(particleName,
                    player.posX + player.getRNG().nextFloat() * player.width * 2.0F - player.width,
                    player.posY - 2.2D + player.getRNG().nextFloat() * player.height,
                    player.posZ + player.getRNG().nextFloat() * player.width * 2.0F - player.width,
                    d0, d1, d2);
        }

    }

}
