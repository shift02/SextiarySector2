package shift.sextiarysector;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.item.ItemCalendar;
import shift.sextiarysector.item.ItemCrop;
import shift.sextiarysector.item.ItemFoodDrink;
import shift.sextiarysector.item.ItemGearStorage;
import shift.sextiarysector.item.ItemHammer;
import shift.sextiarysector.item.ItemScoop;
import shift.sextiarysector.item.ItemSeasonStone;
import shift.sextiarysector.item.ItemWateringCan;
import cpw.mods.fml.common.registry.GameRegistry;

public class SSItems {


	//Gear

	public static Item unit;

	public static Item woodGear;
	public static Item woodUnitGear;

	public static Item woodGFStorage;

	public static Item hammer;

	public static Item calendar;
	public static Item seasonStone;

	//素材
	public static Item dustWaterLily;

	public static Item stoneDust;

	public static Item blueStoneDust;
	public static Item yellowStoneDust;

	public static Item blueStoneSlimeBall;

	//飲み物
	public static Item drinkingWaterSmallBottle;

	//道具
	public static Item woodScoop;
	public static Item stoneScoop;
	public static Item ironScoop;
	public static Item goldScoop;
	public static Item diamondScoop;

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

	public static void initItems(){

		unit = new Item().setUnlocalizedName("ss.unit").setTextureName("sextiarysector:machine/unit").setCreativeTab(SextiarySectorAPI.TabSSMachine);
		GameRegistry.registerItem(unit, "Unit");

		woodGear = new Item().setUnlocalizedName("ss.wood_gear").setTextureName("sextiarysector:machine/wood_gear").setCreativeTab(SextiarySectorAPI.TabSSMachine);
		GameRegistry.registerItem(woodGear, "WoodGear");

		woodUnitGear = new Item().setUnlocalizedName("ss.wood_unit_gear").setTextureName("sextiarysector:machine/wood_unit_gear").setCreativeTab(SextiarySectorAPI.TabSSMachine);
		GameRegistry.registerItem(woodUnitGear, "WoodUnitGear");

		woodGFStorage = new ItemGearStorage(1, 10000, 1).setUnlocalizedName("ss.wood_gf_storage").setTextureName("sextiarysector:gearforce/gear_storage").setCreativeTab(SextiarySectorAPI.TabSSMachine);
		GameRegistry.registerItem(woodGFStorage, "WoodGFStorage");

		hammer = new ItemHammer().setUnlocalizedName("ss.hammer").setTextureName("sextiarysector:machine/hammer").setCreativeTab(SextiarySectorAPI.TabSSMachine);
		GameRegistry.registerItem(hammer, "Hammer");

		calendar = new ItemCalendar().setUnlocalizedName("ss.calendar").setTextureName("sextiarysector:calendar").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(calendar, "Calendar");

		seasonStone = new ItemSeasonStone().setUnlocalizedName("ss.season_stone").setTextureName("sextiarysector:season_stone").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(seasonStone, "SeasonStone");

		dustWaterLily = new Item().setUnlocalizedName("ss.dust_waterlily").setTextureName("sextiarysector:dust/waterlily_dust").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(dustWaterLily, "DustWaterLily");

		stoneDust = new Item().setUnlocalizedName("ss.stone_dust").setTextureName("sextiarysector:dust/stone_dust").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(stoneDust, "StoneDust");

		blueStoneDust = new Item().setUnlocalizedName("ss.dust_blue_stone").setTextureName("sextiarysector:dust/bluestone_dust").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(blueStoneDust, "DustBlueStone");

		yellowStoneDust = new Item().setUnlocalizedName("ss.dust_yellow_stone").setTextureName("sextiarysector:dust/yellowstone_dust").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(yellowStoneDust, "DustYellowStone");

		blueStoneSlimeBall = new Item().setUnlocalizedName("ss.blue_stone_slime_ball").setTextureName("sextiarysector:slimeball/bluestone_slimeball").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(blueStoneSlimeBall, "BlueStoneSlimeBall");

		//飲み物
		drinkingWaterSmallBottle  = new ItemFoodDrink(0, 3.5f, 2, 6.5f, 0, 0, false).setUnlocalizedName("ss.drinking_water_small_bottle").setTextureName("sextiarysector:drink/drinking_water_small_bottle");
		GameRegistry.registerItem(drinkingWaterSmallBottle, "DrinkingWaterSmallBottle");


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

	}

}
