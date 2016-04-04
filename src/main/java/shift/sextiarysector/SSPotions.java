package shift.sextiarysector;

import net.minecraft.potion.Potion;
import shift.sextiarysector.api.potion.PotionBurn;
import shift.sextiarysector.api.potion.PotionHotSprings;

public class SSPotions {

    public static Potion burn;
    public static Potion hotSprings;

    public static void initPotions() {
        burn = new PotionBurn(SSConfig.potionBurn, true, 0xd60330, 0).setPotionName("ss.potion.burn");
        hotSprings = new PotionHotSprings(SSConfig.potionHotSprings, false, 0x0349d6, 3).setPotionName("ss.potion.hot_springs");
    }

}
