package shift.sextiarysector;

import net.minecraft.init.Items;
import net.minecraftforge.oredict.OreDictionary;

public class SSOreDictionary {

	public static void init(){

		OreDictionary.registerOre("craftingSugar", Items.sugar);

		//素材 プレート
		OreDictionary.registerOre("plateWood", SSBlocks.woodPlate);

		OreDictionary.registerOre("gearWood", SSItems.woodGear);
		OreDictionary.registerOre("gearStone", SSItems.stoneGear);

		OreDictionary.registerOre("dustIron", SSItems.ironDust);
		OreDictionary.registerOre("dustGold", SSItems.goldDust);

	}

}
