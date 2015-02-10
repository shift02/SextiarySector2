package shift.sextiarysector.plugin;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import shift.sextiarysector.SSRecipes;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PluginAppleMilk implements IPlugin {

	public static Item chalcedonyKnife;
	public static Item princessClam;

	@Override
	public void prePlugin(FMLPreInitializationEvent event) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void initPlugin(FMLInitializationEvent event) {

		this.chalcedonyKnife = GameRegistry.findItem("DCsAppleMilk", "defeatedcrow.chalcedonyKnife");
		princessClam = GameRegistry.findItem("DCsAppleMilk", "defeatedcrow.princessClam");

		OreDictionary.registerOre("craftingToolWireCutter", new ItemStack(chalcedonyKnife,1,OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("craftingToolKnife", new ItemStack(chalcedonyKnife,1,OreDictionary.WILDCARD_VALUE));

		OreDictionary.registerOre("craftingMagic", new ItemStack(princessClam,1, 0));

		SSRecipes.magicFuel.add(new ItemStack(princessClam,1, 0), 3200);

	}

	@Override
	public void postPlugin(FMLPostInitializationEvent event) {



	}

	@Override
	public String getModName() {
		return "AppleMilk";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void preClientPlugin(FMLPreInitializationEvent event) {

	}


}
