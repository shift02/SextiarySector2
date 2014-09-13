package shift.sextiarysector;

import net.minecraft.item.Item;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.item.ItemGearStorage;
import cpw.mods.fml.common.registry.GameRegistry;

public class SSItems {



	public static Item woodGFStorage;

	//素材
	public static Item dustWaterLily;

	public static Item blueStoneDust;
	public static Item yellowStoneDust;

	public static void initItems(){



		woodGFStorage = new ItemGearStorage(1, 4000, 1).setUnlocalizedName("ss.wood_gf_storage").setTextureName("sextiarysector:gearforce/gear_storage").setCreativeTab(SextiarySectorAPI.TabSSMachine);
		GameRegistry.registerItem(woodGFStorage, "WoodGFStorage");

		dustWaterLily = new Item().setUnlocalizedName("ss.dust_waterlily").setTextureName("sextiarysector:dust/waterlily_dust").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(dustWaterLily, "DustWaterLily");

		blueStoneDust = new Item().setUnlocalizedName("ss.dust_blue_stone").setTextureName("sextiarysector:dust/bluestone_dust").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(blueStoneDust, "DustBlueStone");

		yellowStoneDust = new Item().setUnlocalizedName("ss.dust_yellow_stone").setTextureName("sextiarysector:dust/yellowstone_dust").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(yellowStoneDust, "DustYellowStone");

	}

}
