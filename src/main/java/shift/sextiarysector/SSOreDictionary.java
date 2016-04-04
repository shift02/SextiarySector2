package shift.sextiarysector;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class SSOreDictionary {

    public static void init() {

        OreDictionary.registerOre("condimentSugar", Items.sugar);

        OreDictionary.registerOre("eggChicken", Items.egg);
        OreDictionary.registerOre("cookingEgg", Items.egg);

        OreDictionary.registerOre("cropRedMushroom", Blocks.brown_mushroom);
        OreDictionary.registerOre("cropBrownMushroom", Blocks.brown_mushroom);
        OreDictionary.registerOre("cookingMushroom", Blocks.red_mushroom);
        OreDictionary.registerOre("cookingMushroom", Blocks.brown_mushroom);

        OreDictionary.registerOre("cookingVegetable", Items.potato);
        OreDictionary.registerOre("cookingVegetable", Items.carrot);

        OreDictionary.registerOre("condimentCocoa", new ItemStack(Items.dye, 1, 3));

        OreDictionary.registerOre("containerWoodBowl", Items.bowl);
        OreDictionary.registerOre("containerBucket", Items.bucket);
        OreDictionary.registerOre("containerBottle", Items.glass_bottle);

        OreDictionary.registerOre("dustRedstone", Items.redstone);

        OreDictionary.registerOre("craftingString", new ItemStack(Items.string, 1, 0));

        for (int i = 0; i < 16; i++) {
            OreDictionary.registerOre("blockWool", new ItemStack(Blocks.wool, 1, i));
        }

        OreDictionary.registerOre("fluidWater", Items.water_bucket);
        OreDictionary.registerOre("fluidWater", new ItemStack(Items.potionitem, 1, 0));

        OreDictionary.registerOre("craftingMagic", Items.nether_star);

        //素材 プレート
        OreDictionary.registerOre("plateWood", SSBlocks.woodOakPlate);
        OreDictionary.registerOre("plateWood", SSBlocks.woodBirchPlate);
        OreDictionary.registerOre("plateWood", SSBlocks.woodSprucePlate);
        OreDictionary.registerOre("plateWood", SSBlocks.woodJunglePlate);
        OreDictionary.registerOre("plateWood", SSBlocks.woodAcaciaPlate);
        OreDictionary.registerOre("plateWood", SSBlocks.woodBigOakPlate);

        OreDictionary.registerOre("plateIron", SSBlocks.ironPlate);
        OreDictionary.registerOre("plateGold", SSBlocks.goldPlate);

        OreDictionary.registerOre("plateCopper", SSBlocks.copperPlate);
        OreDictionary.registerOre("plateZinc", SSBlocks.zincPlate);
        OreDictionary.registerOre("plateSilver", SSBlocks.silverPlate);

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
        OreDictionary.registerOre("dustCopper", SSItems.copperDust);
        OreDictionary.registerOre("dustZinc", SSItems.zincDust);
        OreDictionary.registerOre("dustSilver", SSItems.silverDust);
        OreDictionary.registerOre("dustMithril", SSItems.mithrilDust);
        OreDictionary.registerOre("dustBluestone", SSItems.blueStoneDust);
        OreDictionary.registerOre("dustYellowstone", SSItems.yellowStoneDust);

        OreDictionary.registerOre("nuggetIron", SSItems.ironNugget);
        OreDictionary.registerOre("nuggetCopper", SSItems.copperNugget);
        OreDictionary.registerOre("nuggetZinc", SSItems.zincNugget);
        OreDictionary.registerOre("nuggetSilver", SSItems.silverNugget);

        OreDictionary.registerOre("nuggetSteel", SSItems.steelNugget);
        OreDictionary.registerOre("nuggetNinja", SSItems.ninjaNugget);

        OreDictionary.registerOre("nuggetObsidian", SSItems.obsidianNugget);

        OreDictionary.registerOre("ingotSteel", SSItems.steelIngot);
        OreDictionary.registerOre("ingotBrass", SSItems.brassIngot);
        OreDictionary.registerOre("ingotBluestone", SSItems.blueStoneIngot);
        OreDictionary.registerOre("ingotYellowstone", SSItems.yellowStoneIngot);
        OreDictionary.registerOre("ingotNinja", SSItems.ninjaIngot);
        OreDictionary.registerOre("ingotCopper", SSItems.copperIngot);
        OreDictionary.registerOre("ingotZinc", SSItems.zincIngot);
        OreDictionary.registerOre("ingotSilver", SSItems.silverIngot);
        OreDictionary.registerOre("ingotMithril", SSItems.mithrilIngot);

        OreDictionary.registerOre("gemOrichalcum", SSItems.orichalcumGem);

        OreDictionary.registerOre("blockCopper", SSBlocks.copperBlock);
        OreDictionary.registerOre("blockZinc", SSBlocks.zincBlock);
        OreDictionary.registerOre("blockSilver", SSBlocks.silverBlock);
        OreDictionary.registerOre("blockSteel", SSBlocks.steelBlock);
        OreDictionary.registerOre("blockBrass", SSBlocks.brassBlock);
        OreDictionary.registerOre("blockNinja", SSBlocks.ninjaBlock);
        OreDictionary.registerOre("blockMithril", SSBlocks.mithrilBlock);
        OreDictionary.registerOre("blockOrichalcum", SSBlocks.orichalcumBlock);

        OreDictionary.registerOre("oreCopper", SSBlocks.copperOre);
        OreDictionary.registerOre("oreZinc", SSBlocks.zincOre);
        OreDictionary.registerOre("oreSilver", SSBlocks.silverOre);

        OreDictionary.registerOre("oreMithril", SSBlocks.mithrilOre);

        OreDictionary.registerOre("oreIron", SSItems.ironTurnip);
        OreDictionary.registerOre("oreGold", SSItems.goldenCorn);

        OreDictionary.registerOre("gelRedstone", SSItems.redGel);
        OreDictionary.registerOre("gelBluestone", SSItems.blueGel);
        OreDictionary.registerOre("gelYellowstone", SSItems.yellowGel);

        OreDictionary.registerOre("ringIron", SSItems.ironRing);

        OreDictionary.registerOre("treeLeaves", SSBlocks.leafBlock);

        OreDictionary.registerOre("itemAsh", SSItems.ash);
        OreDictionary.registerOre("dyeLightGray", SSItems.ash);

        //布
        OreDictionary.registerOre("massString", SSItems.stringMass);
        OreDictionary.registerOre("blockWool", SSItems.stringMass);

        OreDictionary.registerOre("clothSilk", SSItems.silkCloth);
        OreDictionary.registerOre("itemCloth", SSItems.silkCloth);
        OreDictionary.registerOre("blockWool", SSItems.silkCloth);
        //OreDictionary.registerOre("craftingFilterCloth", SSItems.silkCloth);

        //OreDictionary.registerOre("craftingSmallCloth", SSItems.smallCloth);
        //OreDictionary.registerOre("craftingFilterCloth", SSItems.smallCloth);

        //液体
        OreDictionary.registerOre("fluidIron", new ItemStack(SSBlocks.fluidCrafter, 1, SSFluids.iron.getID()));
        OreDictionary.registerOre("fluidGold", new ItemStack(SSBlocks.fluidCrafter, 1, SSFluids.gold.getID()));

        OreDictionary.registerOre("fluidIron", new ItemStack(SSItems.ironFluidBucket));
        OreDictionary.registerOre("fluidGold", new ItemStack(SSItems.goldFluidBucket));

        Item[] Knife = new Item[] {
                SSItems.woodKnife, SSItems.stoneKnife, SSItems.ironKnife, SSItems.goldKnife, SSItems.diamondKnife,
                SSItems.copperKnife, SSItems.brassKnife, SSItems.ninjaKnife };
        for (int i = 0; i < Knife.length; i++) {
            OreDictionary.registerOre("craftingToolWireCutter", new ItemStack(Knife[i], 1, OreDictionary.WILDCARD_VALUE));
            OreDictionary.registerOre("craftingToolKnife", new ItemStack(Knife[i], 1, OreDictionary.WILDCARD_VALUE));
        }

        OreDictionary.registerOre("fluidSap", new ItemStack(SSItems.sapBottle));
        OreDictionary.registerOre("fluidSap", new ItemStack(SSBlocks.fluidCrafter, 1, SSFluids.sap.getID()));

        OreDictionary.registerOre("slimeball", new ItemStack(SSItems.sapBottle));
        OreDictionary.registerOre("slimeball", new ItemStack(SSBlocks.fluidCrafter, 1, SSFluids.sap.getID()));

        OreDictionary.registerOre("containerBottle", new ItemStack(SSItems.emptyBottle));

        //食べ物

        OreDictionary.registerOre("condimentCurryPowder", SSItems.curryPowder);
        OreDictionary.registerOre("condimentSalt", SSItems.salt);
        //OreDictionary.registerOre("salt", SSItems.salt);
        //OreDictionary.registerOre("itemSalt", SSItems.salt);

        OreDictionary.registerOre("algaLaver", SSItems.laver);
        OreDictionary.registerOre("cookingLaver", SSItems.laverRoasted);

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
        OreDictionary.registerOre("fluidSteam", new ItemStack(SSItems.steamBucket));

    }

}
