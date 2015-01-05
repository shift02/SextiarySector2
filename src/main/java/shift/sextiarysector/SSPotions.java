package shift.sextiarysector;

import net.minecraft.potion.Potion;
import shift.sextiarysector.potion.PotionBurn;

public class SSPotions {

	public static Potion burn;

	public static void initPotions(){
		burn = new PotionBurn(Config.burn, true, 0, 0).setPotionName("ss.potion.burn");
	}

}
