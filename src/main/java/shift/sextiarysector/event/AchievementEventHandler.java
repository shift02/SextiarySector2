package shift.sextiarysector.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.event.entity.player.AchievementEvent;
import shift.mceconomy2.api.MCEconomyAPI;
import shift.mceconomy2.api.shop.ShopBuyEvent;
import shift.sextiarysector.SSAchievement;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.SSConfig;
import shift.sextiarysector.block.BlockFigure;
import shift.sextiarysector.item.ItemSeed;

public class AchievementEventHandler {

    @SubscribeEvent
    public void useEvent(ShopBuyEvent event) {
        if (event.buy.getItem() instanceof ItemSeed) event.entityPlayer.addStat(SSAchievement.seed, 1);
    }

    @SubscribeEvent
    public void onAchievementEvent(AchievementEvent event) {

        Achievement achievement = event.achievement;
        EntityPlayer player = event.entityPlayer;
        ItemStack item = achievement.theItemStack;

        if (item == null) return;

        if (((EntityPlayerMP) player).func_147099_x().hasAchievementUnlocked(achievement)) return;
        if (!((EntityPlayerMP) player).func_147099_x().canUnlockAchievement(achievement)) return;

        ItemStack f = new ItemStack(SSBlocks.figure);
        BlockFigure.setFigureItem(f, item.copy(), "achievement");

        /*
        if (!player.inventory.addItemStackToInventory(f))
        {
        	System.out.println("AA");
        	player.dropPlayerItemWithRandomChoice(f, false);
        }*/

        MCEconomyAPI.addPlayerMP(player, SSConfig.achievementReward, false);

        EntityItem eItem = new EntityItem(player.worldObj, player.posX + 0.5d, player.posY + 0.5d, player.posZ + 0.5d, f);
        eItem.lifespan = 12000;

        player.worldObj.spawnEntityInWorld(eItem);

        //player.inventory.markDirty();

    }

}
