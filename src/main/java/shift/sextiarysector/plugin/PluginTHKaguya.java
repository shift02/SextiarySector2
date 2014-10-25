package shift.sextiarysector.plugin;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import shift.mceconomy2.api.MCEconomyAPI;
import cpw.mods.fml.common.registry.GameRegistry;

public class PluginTHKaguya {

	public static Item thSpellCard;

	public static void initPlugin() {

		System.out.println("BBBBBBBBBBBBBBBBB");
		thSpellCard = GameRegistry.findItem("THKaguyaMod", "Spell Card");

		MCEconomyAPI.addPurchaseItem(new ItemStack(thSpellCard,1,OreDictionary.WILDCARD_VALUE), 500);

	}

}
