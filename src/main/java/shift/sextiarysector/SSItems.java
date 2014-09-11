package shift.sextiarysector;

import net.minecraft.item.Item;
import shift.sextiarysector.api.SextiarySectorAPI;
import cpw.mods.fml.common.registry.GameRegistry;

public class SSItems {

	public static Item dustWaterLily;

	public static void initItems(){

		dustWaterLily = new Item().setUnlocalizedName("ss.dust_waterlily").setTextureName("sextiarysector:dust_waterlily").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(dustWaterLily, "DustWaterLily");

	}

}
