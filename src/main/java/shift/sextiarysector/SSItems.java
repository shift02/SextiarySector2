package shift.sextiarysector;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.item.ItemCalendar;
import shift.sextiarysector.item.ItemGearStorage;
import shift.sextiarysector.item.ItemHammer;
import shift.sextiarysector.item.ItemScoop;
import shift.sextiarysector.item.ItemSeasonStone;
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

	public static Item blueStoneDust;
	public static Item yellowStoneDust;

	public static Item blueStoneSlimeBall;

	//道具
	public static Item woodScoop;
	public static Item stoneScoop;
	public static Item ironScoop;
	public static Item goldScoop;
	public static Item diamondScoop;

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

		blueStoneDust = new Item().setUnlocalizedName("ss.dust_blue_stone").setTextureName("sextiarysector:dust/bluestone_dust").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(blueStoneDust, "DustBlueStone");

		yellowStoneDust = new Item().setUnlocalizedName("ss.dust_yellow_stone").setTextureName("sextiarysector:dust/yellowstone_dust").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(yellowStoneDust, "DustYellowStone");

		blueStoneSlimeBall = new Item().setUnlocalizedName("ss.blue_stone_slime_ball").setTextureName("sextiarysector:slimeball/bluestone_slimeball").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(blueStoneSlimeBall, "BlueStoneSlimeBall");

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

	}

}
