package shift.sextiarysector.plugin;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import shift.mceconomy2.api.MCEconomyAPI;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class PluginTHKaguya implements IPlugin{

	public static Item thSpellCard;


	@Override
	public void prePlugin(FMLPreInitializationEvent event) {

	}

	@Override
	public void initPlugin(FMLInitializationEvent event) {

		thSpellCard = GameRegistry.findItem("THKaguyaMod", "Spell Card");

		MCEconomyAPI.addPurchaseItem(new ItemStack(thSpellCard,1,OreDictionary.WILDCARD_VALUE), 500);

	}

	@Override
	public void postPlugin(FMLPostInitializationEvent event) {

	}

	@Override
	public String getModName() {
		return "THKaguya";
	}

}
