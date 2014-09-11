package shift.sextiarysector;

import net.minecraft.item.Item;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.item.ItemGearStorage;
import cpw.mods.fml.common.registry.GameRegistry;

public class SSItems {

	public static Item dustWaterLily;

	public static Item woodGFStorage;

	public static void initItems(){

		dustWaterLily = new Item().setUnlocalizedName("ss.dust_waterlily").setTextureName("sextiarysector:dust_waterlily").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(dustWaterLily, "DustWaterLily");

		woodGFStorage = new ItemGearStorage(1, 4000, 1).setUnlocalizedName("ss.wood_gf_storage").setTextureName("sextiarysector:gearforce/gear_storage").setCreativeTab(SextiarySectorAPI.TabSSMachine);
		GameRegistry.registerItem(woodGFStorage, "WoodGFStorage");

	}

}
