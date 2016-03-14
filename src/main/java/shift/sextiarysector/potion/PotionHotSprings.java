package shift.sextiarysector.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import shift.sextiarysector.player.EntityPlayerManager;
import shift.sextiarysector.player.MoistureStats;
import shift.sextiarysector.player.StaminaStats;

public class PotionHotSprings extends PotionSS {

    public PotionHotSprings(int id, boolean isBadEffect, int liquidColor, int icon) {
        super(id, isBadEffect, liquidColor, icon);
    }

    @Override
    public void performEffect(EntityLivingBase p_76394_1_, int p_76394_2_) {

        if (!(p_76394_1_ instanceof EntityPlayer)) return;

        EntityPlayer player = (EntityPlayer) p_76394_1_;

        if (player.worldObj.getTotalWorldTime() % 60 != 0)
            return;

        StaminaStats stats = EntityPlayerManager.getStaminaStats(player);
        if (stats == null) return;

        if (!stats.needStamina()) return;

        MoistureStats moistStats = EntityPlayerManager.getMoistureStats(player);
        if (moistStats == null) return;

        if (moistStats.getMoistureLevel() < 3) return;

        stats.addStats(player, 3, 0.7f);
        moistStats.addExhaustion(player, 1.9f);

    }

    @Override
    public boolean isReady(int p_76397_1_, int amplifier) {
        return p_76397_1_ % (5 - Math.min(4, amplifier)) == 0;
    }
}
