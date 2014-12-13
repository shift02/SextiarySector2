package shift.sextiarysector;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class SSOreDictionary {

	public static void init(){

		OreDictionary.registerOre("craftingSugar", Items.sugar);

		//素材 プレート
		OreDictionary.registerOre("plateWood", SSBlocks.woodPlate);

		OreDictionary.registerOre("gearWood", SSItems.woodGear);
		OreDictionary.registerOre("gearStone", SSItems.stoneGear);
		OreDictionary.registerOre("gearSteel", SSItems.steelGear);

		OreDictionary.registerOre("dustCoal", SSItems.coalDust);
		OreDictionary.registerOre("dustIron", SSItems.ironDust);
		OreDictionary.registerOre("dustGold", SSItems.goldDust);
		OreDictionary.registerOre("dustBluestone", SSItems.blueStoneDust);
		OreDictionary.registerOre("dustYellowstone", SSItems.yellowStoneDust);

		OreDictionary.registerOre("ingotSteel", SSItems.steelIngot);
		OreDictionary.registerOre("ingotBluestone", SSItems.blueStoneIngot);
		OreDictionary.registerOre("ingotYellowstone", SSItems.yellowStoneIngot);
		OreDictionary.registerOre("ingotNinja", SSItems.ninjaIngot);

		Item[] Knife = new Item[]{SSItems.woodKnife, SSItems.stoneKnife, SSItems.ironKnife, SSItems.goldKnife, SSItems.diamondKnife};
		for(int i =0;i<Knife.length;i++){
			OreDictionary.registerOre("craftingToolWireCutter", new ItemStack(Knife[i],1,OreDictionary.WILDCARD_VALUE));
			OreDictionary.registerOre("craftingToolKnife", new ItemStack(Knife[i],1,OreDictionary.WILDCARD_VALUE));
		}


	}

}
