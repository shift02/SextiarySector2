package shift.sextiarysector;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.item.ItemCalendar;
import shift.sextiarysector.item.ItemCrop;
import shift.sextiarysector.item.ItemFoodDrink;
import shift.sextiarysector.item.ItemGearStorage;
import shift.sextiarysector.item.ItemGuiUnit;
import shift.sextiarysector.item.ItemHammer;
import shift.sextiarysector.item.ItemKnife;
import shift.sextiarysector.item.ItemScoop;
import shift.sextiarysector.item.ItemSeasonStone;
import shift.sextiarysector.item.ItemShiftHat;
import shift.sextiarysector.item.ItemWateringCan;
import cpw.mods.fml.common.registry.GameRegistry;

public class SSItems {

	//Gear
	public static Item unit;

	public static Item woodGear;
	public static Item stoneGear;
	public static Item steelGear;

	public static Item woodUnitGear;
	public static Item stoneUnitGear;
	public static Item steelUnitGear;

	public static Item woodGFStorage;
	public static Item stoneGFStorage;
	public static Item steelGFStorage;

	public static Item hammer;

	public static Item calendar;
	public static Item seasonStone;

	//素材
	public static Item dustWaterLily;

	public static Item stoneDust;

	public static Item blueStoneDust;
	public static Item yellowStoneDust;

	public static Item coalDust;
	public static Item ironDust;
	public static Item goldDust;

	public static Item steelIngot;

	public static Item mithrilIngot;
	public static Item orichalcumGem;

	public static Item blueStoneSlimeBall;

	//unit
	public static Item craftUnit;

	//魔法
	public static Item magicDust;

	//布
	public static Item stringBobbin;
	public static Item cloth;
	public static Item canvas;

	//飲み物
	public static Item drinkingWaterSmallBottle;

	//public static Item bottle;

	//道具
	public static Item woodScoop;
	public static Item stoneScoop;
	public static Item ironScoop;
	public static Item goldScoop;
	public static Item diamondScoop;

	public static Item woodKnife;
	public static Item stoneKnife;
	public static Item ironKnife;
	public static Item goldKnife;
	public static Item diamondKnife;

	public static Item woodWateringCan;


	//野菜
	public static Item turnip;
	public static Item cucumber;

	public static Item ironTurnip;

	public static Item onion;
	public static Item tomato;
	public static Item corn;

	public static Item goldenCorn;


	public static Item eggplant;
	public static Item sweetPotato;
	public static Item greenPepper;

	public static Item radish;

	//装備
	public static Item shiftHat;

	public static void initItems(){

		unit = new Item().setUnlocalizedName("ss.unit").setTextureName("sextiarysector:machine/unit").setCreativeTab(SextiarySectorAPI.TabSSMachine);
		GameRegistry.registerItem(unit, "Unit");

		woodGear = new Item().setUnlocalizedName("ss.wood_gear").setTextureName("sextiarysector:machine/wood_gear").setCreativeTab(SextiarySectorAPI.TabSSMachine);
		GameRegistry.registerItem(woodGear, "WoodGear");

		stoneGear = new Item().setUnlocalizedName("ss.stone_gear").setTextureName("sextiarysector:machine/stone_gear").setCreativeTab(SextiarySectorAPI.TabSSMachine);
		GameRegistry.registerItem(stoneGear, "StoneGear");

		steelGear = new Item().setUnlocalizedName("ss.steel_gear").setTextureName("sextiarysector:machine/steel_gear").setCreativeTab(SextiarySectorAPI.TabSSMachine);
		GameRegistry.registerItem(steelGear, "SteelGear");

		woodUnitGear = new Item().setUnlocalizedName("ss.wood_unit_gear").setTextureName("sextiarysector:machine/wood_unit_gear").setCreativeTab(SextiarySectorAPI.TabSSMachine);
		GameRegistry.registerItem(woodUnitGear, "WoodUnitGear");

		stoneUnitGear = new Item().setUnlocalizedName("ss.stone_unit_gear").setTextureName("sextiarysector:machine/stone_unit_gear").setCreativeTab(SextiarySectorAPI.TabSSMachine);
		GameRegistry.registerItem(stoneUnitGear, "StoneUnitGear");

		steelUnitGear = new Item().setUnlocalizedName("ss.steel_unit_gear").setTextureName("sextiarysector:machine/steel_unit_gear").setCreativeTab(SextiarySectorAPI.TabSSMachine);
		GameRegistry.registerItem(steelUnitGear, "steelUnitGear");

		woodGFStorage = new ItemGearStorage(1, 10000, 1).setUnlocalizedName("ss.wood_gf_storage").setTextureName("sextiarysector:gearforce/wood_gear_storage").setCreativeTab(SextiarySectorAPI.TabSSMachine);
		GameRegistry.registerItem(woodGFStorage, "WoodGFStorage");

		stoneGFStorage = new ItemGearStorage(2, 10000, 2).setUnlocalizedName("ss.stone_gf_storage").setTextureName("sextiarysector:gearforce/stone_gear_storage").setCreativeTab(SextiarySectorAPI.TabSSMachine);
		GameRegistry.registerItem(stoneGFStorage, "StoneGFStorage");

		steelGFStorage = new ItemGearStorage(3, 10000, 3).setUnlocalizedName("ss.steel_gf_storage").setTextureName("sextiarysector:gearforce/steel_gear_storage").setCreativeTab(SextiarySectorAPI.TabSSMachine);
		GameRegistry.registerItem(steelGFStorage, "SteelGFStorage");

		hammer = new ItemHammer().setUnlocalizedName("ss.hammer").setTextureName("sextiarysector:machine/hammer").setCreativeTab(SextiarySectorAPI.TabSSMachine);
		GameRegistry.registerItem(hammer, "Hammer");

		calendar = new ItemCalendar().setUnlocalizedName("ss.calendar").setTextureName("sextiarysector:calendar").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(calendar, "Calendar");

		seasonStone = new ItemSeasonStone().setUnlocalizedName("ss.season_stone").setTextureName("sextiarysector:season_stone").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(seasonStone, "SeasonStone");

		//素材
		dustWaterLily = new Item().setUnlocalizedName("ss.dust_waterlily").setTextureName("sextiarysector:dust/waterlily_dust").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(dustWaterLily, "DustWaterLily");

		stoneDust = new Item().setUnlocalizedName("ss.stone_dust").setTextureName("sextiarysector:dust/stone_dust").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(stoneDust, "StoneDust");

		blueStoneDust = new Item().setUnlocalizedName("ss.dust_blue_stone").setTextureName("sextiarysector:dust/bluestone_dust").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(blueStoneDust, "DustBlueStone");

		yellowStoneDust = new Item().setUnlocalizedName("ss.dust_yellow_stone").setTextureName("sextiarysector:dust/yellowstone_dust").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(yellowStoneDust, "DustYellowStone");

		coalDust = new Item().setUnlocalizedName("ss.coal_dust").setTextureName("sextiarysector:dust/coal_dust").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(coalDust, "CoalDust");

		ironDust = new Item().setUnlocalizedName("ss.iron_dust").setTextureName("sextiarysector:dust/iron_dust").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(ironDust, "IronDust");

		goldDust = new Item().setUnlocalizedName("ss.gold_dust").setTextureName("sextiarysector:dust/gold_dust").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(goldDust, "GoldDust");

		steelIngot = new Item().setUnlocalizedName("ss.steel_ingot").setTextureName("sextiarysector:ingot/steel_ingot").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(steelIngot, "SteelIngot");

		mithrilIngot = new Item().setUnlocalizedName("ss.mithril_ingot").setTextureName("sextiarysector:ingot/mithril_ingot").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(mithrilIngot, "MithrilIngot");

		orichalcumGem = new Item().setUnlocalizedName("ss.orichalcum_gem").setTextureName("sextiarysector:gem/orichalcum_gem").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(orichalcumGem, "OrichalcumGem");

		blueStoneSlimeBall = new Item().setUnlocalizedName("ss.blue_stone_slime_ball").setTextureName("sextiarysector:slimeball/bluestone_slimeball").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(blueStoneSlimeBall, "BlueStoneSlimeBall");

		//Unit
		craftUnit = new ItemGuiUnit(201).setUnlocalizedName("ss.craft_unit").setTextureName("sextiarysector:unit/craft_unit").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(craftUnit, "CraftUnit");

		//魔法
		magicDust = new Item().setUnlocalizedName("ss.magic_dust").setTextureName("sextiarysector:dust/magic_dust").setCreativeTab(SextiarySectorAPI.TabSSMagic);
		GameRegistry.registerItem(magicDust, "MagicDust");

		//布
		stringBobbin = new Item().setUnlocalizedName("ss.string_bobbin").setTextureName("sextiarysector:loom/string_bobbin").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(stringBobbin, "StringBobbin");

		cloth = new Item().setUnlocalizedName("ss.cloth").setTextureName("sextiarysector:loom/cloth").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(cloth, "Cloth");

		canvas = new Item().setUnlocalizedName("ss.canvas").setTextureName("sextiarysector:loom/canvas").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(canvas, "Canvas");

		//飲み物
		drinkingWaterSmallBottle  = new ItemFoodDrink(0, 3.5f, 2, 6.5f, 0, 0, false).setDrink().setUnlocalizedName("ss.drinking_water_small_bottle").setTextureName("sextiarysector:drink/drinking_water_small_bottle").setContainerItem(Items.glass_bottle);
		GameRegistry.registerItem(drinkingWaterSmallBottle, "DrinkingWaterSmallBottle");

		//bottle  = new ItemBlockBottle().setUnlocalizedName("ss.bottle").setTextureName("sextiarysector:drink/empty_bottle");
		//GameRegistry.registerItem(bottle, "Bottle");


		//道具
		woodScoop = new ItemScoop(ToolMaterial.WOOD).setUnlocalizedName("ss.wood_scoop").setTextureName("wood_scoop");
		stoneScoop = new ItemScoop(ToolMaterial.STONE).setUnlocalizedName("ss.stone_scoop").setTextureName("stone_scoop");
		ironScoop = new ItemScoop(ToolMaterial.IRON).setUnlocalizedName("ss.iron_scoop").setTextureName("iron_scoop");
		goldScoop = new ItemScoop(ToolMaterial.GOLD).setUnlocalizedName("ss.gold_scoop").setTextureName("gold_scoop");
		diamondScoop = new ItemScoop(ToolMaterial.EMERALD).setUnlocalizedName("ss.diamond_scoop").setTextureName("diamond_scoop");
		GameRegistry.registerItem(woodScoop, "WoodScoop");
		GameRegistry.registerItem(stoneScoop, "StoneScoop");
		GameRegistry.registerItem(ironScoop, "IronScoop");
		GameRegistry.registerItem(goldScoop, "GoldScoop");
		GameRegistry.registerItem(diamondScoop, "DiamondScoop");

		woodKnife = new ItemKnife(ToolMaterial.WOOD).setUnlocalizedName("ss.wood_knife").setTextureName("wood_knife");
		stoneKnife = new ItemKnife(ToolMaterial.STONE).setUnlocalizedName("ss.stone_knife").setTextureName("stone_knife");
		ironKnife = new ItemKnife(ToolMaterial.IRON).setUnlocalizedName("ss.iron_knife").setTextureName("iron_knife");
		goldKnife = new ItemKnife(ToolMaterial.GOLD).setUnlocalizedName("ss.gold_knife").setTextureName("gold_knife");
		diamondKnife = new ItemKnife(ToolMaterial.EMERALD).setUnlocalizedName("ss.diamond_knife").setTextureName("diamond_knife");
		GameRegistry.registerItem(woodKnife, "WoodKnife");
		GameRegistry.registerItem(stoneKnife, "StoneKnife");
		GameRegistry.registerItem(ironKnife, "IronKnife");
		GameRegistry.registerItem(goldKnife, "GoldKnife");
		GameRegistry.registerItem(diamondKnife, "DiamondKnife");

		woodWateringCan = new ItemWateringCan(ToolMaterial.WOOD).setUnlocalizedName("ss.wood_watering_can").setTextureName("wood_watering_can");
		GameRegistry.registerItem(woodWateringCan, "WoodWateringCan");

		turnip = new ItemCrop(3, 1, 1, 4, 0, 0, false).setUnlocalizedName("ss.turnip").setTextureName("sextiarysector:crop/turnip");
		GameRegistry.registerItem(turnip, "Turnip");

		cucumber = new ItemCrop(1, 1, 3, 4, 0, 2, false).setUnlocalizedName("ss.cucumber").setTextureName("sextiarysector:crop/cucumber");
		GameRegistry.registerItem(cucumber, "Cucumber");

		ironTurnip = new ItemCrop(0, 1, 0, 0, 0, 0, false).setUnlocalizedName("ss.iron_turnip").setTextureName("sextiarysector:crop/iron_turnip");
		GameRegistry.registerItem(ironTurnip, "IronTurnip");


		onion = new ItemCrop(2, 1, 1, 0, 0, 0, false).setUnlocalizedName("ss.onion").setTextureName("sextiarysector:crop/onion");
		GameRegistry.registerItem(onion, "Onion");

		tomato = new ItemCrop(1, 1, 4, 5, 0, 0, false).setUnlocalizedName("ss.tomato").setTextureName("sextiarysector:crop/tomato");
		GameRegistry.registerItem(tomato, "Tomato");

		corn = new ItemCrop(0, 1, 1, 6, 4, 2, false).setUnlocalizedName("ss.corn").setTextureName("sextiarysector:crop/corn");
		GameRegistry.registerItem(corn, "corn");

		goldenCorn = new ItemCrop(0, 2, 0, 0, 0, 0, false).setUnlocalizedName("ss.golden_corn").setTextureName("sextiarysector:crop/golden_corn");
		GameRegistry.registerItem(goldenCorn, "GoldCorn");


		eggplant = new ItemCrop(1, 1, 4, 2, 0, 0, false).setUnlocalizedName("ss.eggplant").setTextureName("sextiarysector:crop/eggplant");
		GameRegistry.registerItem(eggplant, "Eggplant");

		sweetPotato = new ItemCrop(4, 1, 0, 0, 6, 0, false).setUnlocalizedName("ss.sweet_potato").setTextureName("sextiarysector:crop/sweet_potato");
		GameRegistry.registerItem(sweetPotato, "SweetPotato");

		greenPepper = new ItemCrop(2, 1, 2, 1, 0, 0, false).setUnlocalizedName("ss.green_pepper").setTextureName("sextiarysector:crop/green_pepper");
		GameRegistry.registerItem(greenPepper, "GreenPepper");


		radish = new ItemCrop(3, 1, 2, 1, 0, 0, false).setUnlocalizedName("ss.radish").setTextureName("sextiarysector:crop/radish");
		GameRegistry.registerItem(radish, "Radish");

		shiftHat = new ItemShiftHat().setUnlocalizedName("ss.shift_hat").setTextureName("sextiarysector:shift_hat");
		GameRegistry.registerItem(shiftHat, "ShiftHat");

	}

}
