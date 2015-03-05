package shift.sextiarysector.plugin;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import shift.mceconomy2.api.shop.ProductItem;
import shift.sextiarysector.SSFluids;
import shift.sextiarysector.SSItems;
import shift.sextiarysector.SSRecipes;
import shift.sextiarysector.SSShops;
import shift.sextiarysector.item.ItemFigureBox;
import shift.sextiarysector.module.ModuleFigure;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PluginCleaver implements IPlugin {

	public static Item itemCleaverNormal;
	public static Item itemCleaverBlaze;
	public static Item itemCleaverSoul;
	public static Item itemCleaverOgre;

	@Override
	public void prePlugin(FMLPreInitializationEvent event) {

	}

	@Override
	public void initPlugin(FMLInitializationEvent event) {

		this.itemCleaverNormal = GameRegistry.findItem("schr0.cleaver", "itemCleaverNormal");
		this.itemCleaverBlaze = GameRegistry.findItem("schr0.cleaver", "itemCleaverBlaze");
		this.itemCleaverSoul = GameRegistry.findItem("schr0.cleaver", "itemCleaverSoul");
		this.itemCleaverOgre = GameRegistry.findItem("schr0.cleaver", "itemCleaverOgre");

		OreDictionary.registerOre("craftingToolWireCutter", new ItemStack(itemCleaverNormal, 1, 0));
		OreDictionary.registerOre("craftingToolKnife", new ItemStack(itemCleaverNormal, 1, 0));

		SSRecipes.fluidFurnace.add(new ItemStack(this.itemCleaverNormal, 1), new ItemStack(Items.stick, 1), new FluidStack(SSFluids.iron, 3500));
		SSRecipes.magicFuel.add(new ItemStack(this.itemCleaverSoul, 1), 3200);

	}

	@Override
	public void postPlugin(FMLPostInitializationEvent event) {

		ModuleFigure.addFigure("schr0_cleaver", new ItemStack(itemCleaverNormal, 1), 20);
		ModuleFigure.addFigure("schr0_cleaver", new ItemStack(itemCleaverBlaze, 1), 20);
		ModuleFigure.addFigure("schr0_cleaver", new ItemStack(itemCleaverSoul, 1), 20);
		ModuleFigure.addFigure("schr0_cleaver", new ItemStack(itemCleaverOgre, 1), 20);

		for (int i = 0; i < 4; i++) {
			SSShops.creepers[i].addItemProduct(new ProductItem(((ItemFigureBox) SSItems.figureBox).getEditionFigureBox("schr0_cleaver"), 500));
		}

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
