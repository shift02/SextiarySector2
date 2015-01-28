package shift.sextiarysector.plugin;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import shift.sextiarysector.SSFluids;
import shift.sextiarysector.SSRecipes;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PluginCleaver  implements IPlugin {

	public static Item itemCleaverNormal;
	public static Item itemCleaverBlaze;
	public static Item itemCleaverSoul;

	@Override
	public void prePlugin(FMLPreInitializationEvent event) {

	}

	@Override
	public void initPlugin(FMLInitializationEvent event) {

		this.itemCleaverNormal = GameRegistry.findItem("schr0.cleaver", "itemCleaverNormal");

		this.itemCleaverSoul = GameRegistry.findItem("schr0.cleaver", "itemCleaverSoul");

		OreDictionary.registerOre("craftingToolWireCutter", new ItemStack(itemCleaverNormal,1,0));
		OreDictionary.registerOre("craftingToolKnife", new ItemStack(itemCleaverNormal,1,0));

		SSRecipes.fluidFurnace.add(new ItemStack(this.itemCleaverNormal,1), new ItemStack(Items.stick,1), new FluidStack(SSFluids.iron, 3500));
		SSRecipes.magicFuel.add(new ItemStack(this.itemCleaverSoul,1), 3200);

	}

	@Override
	public void postPlugin(FMLPostInitializationEvent event) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public String getModName() {
		return "schr0.cleaver";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void preClientPlugin(FMLPreInitializationEvent event) {

	}
}
