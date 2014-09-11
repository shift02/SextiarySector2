package shift.sextiarysector;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

public class SSItems {

	public static Item dustWaterLily;

	public static void initItems(){

		dustWaterLily = new Item().setUnlocalizedName("ss.dust_waterlily").setTextureName("sextiarysector:dust_waterlily").setCreativeTab(CreativeTabs.tabMaterials);
		GameRegistry.registerItem(dustWaterLily, "DustWaterLily");

	}

}
