package shift.sextiarysector;

import net.minecraft.potion.Potion;
import shift.sextiarysector.potion.PotionBurn;
import shift.sextiarysector.potion.PotionHotSprings;

public class SSPotions {

	public static Potion burn;
	public static Potion hotSprings;

	public static void initPotions() {
		burn = new PotionBurn(Config.burn, true, 0, 0).setPotionName("ss.potion.burn");
		hotSprings = new PotionHotSprings(Config.hotSprings, false, 0, 3).setPotionName("ss.potion.hot_springs");
	}

}
