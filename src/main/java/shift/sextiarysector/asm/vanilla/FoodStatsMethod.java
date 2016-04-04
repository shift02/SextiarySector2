package shift.sextiarysector.asm.vanilla;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import shift.sextiarysector.SSConfig;
import shift.sextiarysector.api.SextiarySectorAPI;

public class FoodStatsMethod {

    public static void onExhaustion(EntityPlayer player, float amount) {

        if (SSConfig.statusStamina) {
            SextiarySectorAPI.addStaminaExhaustion(player, amount);
        } else {
            player.attackEntityFrom(DamageSource.starve, 1.0F);
        }
    }
}