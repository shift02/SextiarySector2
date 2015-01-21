package shift.sextiarysector;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class SSOreDictionary {

	public static void init(){

		OreDictionary.registerOre("condimentSugar", Items.sugar);

		OreDictionary.registerOre("eggChicken", Items.egg);
		OreDictionary.registerOre("cookingEgg", Items.egg);

		OreDictionary.registerOre("cropRedMushroom", Blocks.brown_mushroom);
		OreDictionary.registerOre("cropBrownMushroom", Blocks.brown_mushroom);
		OreDictionary.registerOre("cookingMushroom", Blocks.red_mushroom);
		OreDictionary.registerOre("cookingMushroom", Blocks.brown_mushroom);

		OreDictionary.registerOre("cookingVegetable", Items.potato);
		OreDictionary.registerOre("cookingVegetable", Items.carrot);

		OreDictionary.registerOre("containerWoodBowl", Items.bowl);
		OreDictionary.registerOre("containerBucket", Items.bucket);

		OreDictionary.registerOre("fluidWater", Items.water_bucket);
		OreDictionary.registerOre("fluidWater", new ItemStack(Items.potionitem, 1 ,0));

		//素材 プレート
		OreDictionary.registerOre("plateWood", SSBlocks.woodOakPlate);
		OreDictionary.registerOre("plateWood", SSBlocks.woodBirchPlate);
		OreDictionary.registerOre("plateWood", SSBlocks.woodSprucePlate);
		OreDictionary.registerOre("plateWood", SSBlocks.woodJunglePlate);
		OreDictionary.registerOre("plateWood", SSBlocks.woodAcaciaPlate);
		OreDictionary.registerOre("plateWood", SSBlocks.woodBigOakPlate);

		/*
		OreDictionary.registerOre("plankWood", SSBlocks.woodOakPlate);
		OreDictionary.registerOre("plankWood", SSBlocks.woodBirchPlate);
		OreDictionary.registerOre("plankWood", SSBlocks.woodSprucePlate);
		OreDictionary.registerOre("plankWood", SSBlocks.woodJunglePlate);
		OreDictionary.registerOre("plankWood", SSBlocks.woodAcaciaPlate);
		OreDictionary.registerOre("plankWood", SSBlocks.woodBigOakPlate);
		*/

		OreDictionary.registerOre("gearWood", SSItems.woodGear);
		OreDictionary.registerOre("gearStone", SSItems.stoneGear);
		OreDictionary.registerOre("gearSteel", SSItems.steelGear);
		OreDictionary.registerOre("gearNinja", SSItems.ninjaGear);
		OreDictionary.registerOre("gearOrichalcum", SSItems.orichalcumGear);

		OreDictionary.registerOre("dustCoal", SSItems.coalDust);
		OreDictionary.registerOre("dustIron", SSItems.ironDust);
		OreDictionary.registerOre("dustGold", SSItems.goldDust);
		OreDictionary.registerOre("dustDiamond", SSItems.diamondDust);
		OreDictionary.registerOre("dustMithril", SSItems.mithrilDust);
		OreDictionary.registerOre("dustBluestone", SSItems.blueStoneDust);
		OreDictionary.registerOre("dustYellowstone", SSItems.yellowStoneDust);

		OreDictionary.registerOre("ingotSteel", SSItems.steelIngot);
		OreDictionary.registerOre("ingotBrass", SSItems.brassIngot);
		OreDictionary.registerOre("ingotBluestone", SSItems.blueStoneIngot);
		OreDictionary.registerOre("ingotYellowstone", SSItems.yellowStoneIngot);
		OreDictionary.registerOre("ingotNinja", SSItems.ninjaIngot);
		OreDictionary.registerOre("ingotMithril", SSItems.mithrilIngot);

		OreDictionary.registerOre("gemOrichalcum", SSItems.orichalcumGem);

		OreDictionary.registerOre("oreMithril", SSBlocks.mithrilOre);

		OreDictionary.registerOre("oreIron", SSItems.ironTurnip);
		OreDictionary.registerOre("oreGold", SSItems.goldenCorn);

		OreDictionary.registerOre("ringIron", SSItems.ironRing);


		//液体金属
		OreDictionary.registerOre("fluidIron", new ItemStack(SSBlocks.fluidCrafter, 1, SSFluids.iron.getID()));
		OreDictionary.registerOre("fluidGold", new ItemStack(SSBlocks.fluidCrafter, 1, SSFluids.gold.getID()));

		Item[] Knife = new Item[]{SSItems.woodKnife, SSItems.stoneKnife, SSItems.ironKnife, SSItems.goldKnife, SSItems.diamondKnife};
		for(int i =0;i<Knife.length;i++){
			OreDictionary.registerOre("craftingToolWireCutter", new ItemStack(Knife[i],1,OreDictionary.WILDCARD_VALUE));
			OreDictionary.registerOre("craftingToolKnife", new ItemStack(Knife[i],1,OreDictionary.WILDCARD_VALUE));
		}

		//食べ物

		OreDictionary.registerOre("condimentCurryPowder", SSItems.curryPowder);

		OreDictionary.registerOre("cropTurnip", SSItems.turnip);
		OreDictionary.registerOre("cookingVegetable", SSItems.turnip);
		OreDictionary.registerOre("cropCucumber", SSItems.cucumber);
		OreDictionary.registerOre("cookingVegetable", SSItems.cucumber);


		OreDictionary.registerOre("cropOnion", SSItems.onion);
		OreDictionary.registerOre("cookingVegetable", SSItems.onion);
		OreDictionary.registerOre("cropTomato", SSItems.tomato);
		OreDictionary.registerOre("cookingVegetable", SSItems.tomato);
		OreDictionary.registerOre("cropCorn", SSItems.corn);


		OreDictionary.registerOre("cropEggplant", SSItems.eggplant);
		OreDictionary.registerOre("cookingVegetable", SSItems.eggplant);
		OreDictionary.registerOre("cropSweetPotato", SSItems.sweetPotato);
		OreDictionary.registerOre("cropGreenPepper", SSItems.greenPepper);
		OreDictionary.registerOre("cookingVegetable", SSItems.greenPepper);


		OreDictionary.registerOre("cropRadish", SSItems.radish);
		OreDictionary.registerOre("cookingVegetable", SSItems.radish);


		OreDictionary.registerOre("cropRice", SSItems.rice);

		OreDictionary.registerOre("cookingRice", SSItems.whiteRice);

		//飲み物
		OreDictionary.registerOre("fluidWater", new ItemStack(SSBlocks.fluidCrafter, 1, FluidRegistry.WATER.getID()));



	}

}
