package shift.sextiarysector;

import net.minecraft.item.Item;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.item.ItemGearStorage;
import cpw.mods.fml.common.registry.GameRegistry;

public class SSItems {


	//Gear

	public static Item unit;

	public static Item woodGear;
	public static Item woodUnitGear;

	public static Item woodGFStorage;

	//素材
	public static Item dustWaterLily;

	public static Item blueStoneDust;
	public static Item yellowStoneDust;

	public static Item blueStoneSlimeBall;

	public static void initItems(){

		unit = new Item().setUnlocalizedName("ss.unit").setTextureName("sextiarysector:machine/unit").setCreativeTab(SextiarySectorAPI.TabSSMachine);
		GameRegistry.registerItem(unit, "Unit");

		woodGear = new Item().setUnlocalizedName("ss.wood_gear").setTextureName("sextiarysector:machine/wood_gear").setCreativeTab(SextiarySectorAPI.TabSSMachine);
		GameRegistry.registerItem(woodGear, "WoodGear");

		woodUnitGear = new Item().setUnlocalizedName("ss.wood_unit_gear").setTextureName("sextiarysector:machine/wood_unit_gear").setCreativeTab(SextiarySectorAPI.TabSSMachine);
		GameRegistry.registerItem(woodUnitGear, "WoodUnitGear");

		woodGFStorage = new ItemGearStorage(1, 10000, 1).setUnlocalizedName("ss.wood_gf_storage").setTextureName("sextiarysector:gearforce/gear_storage").setCreativeTab(SextiarySectorAPI.TabSSMachine);
		GameRegistry.registerItem(woodGFStorage, "WoodGFStorage");

		dustWaterLily = new Item().setUnlocalizedName("ss.dust_waterlily").setTextureName("sextiarysector:dust/waterlily_dust").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(dustWaterLily, "DustWaterLily");

		blueStoneDust = new Item().setUnlocalizedName("ss.dust_blue_stone").setTextureName("sextiarysector:dust/bluestone_dust").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(blueStoneDust, "DustBlueStone");

		yellowStoneDust = new Item().setUnlocalizedName("ss.dust_yellow_stone").setTextureName("sextiarysector:dust/yellowstone_dust").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(yellowStoneDust, "DustYellowStone");

		blueStoneSlimeBall = new Item().setUnlocalizedName("ss.blue_stone_slime_ball").setTextureName("sextiarysector:slimeball/bluestone_slimeball").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(blueStoneSlimeBall, "BlueStoneSlimeBall");

	}

}
