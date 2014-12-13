package shift.sextiarysector.plugin;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.SSItems;
import shift.sextiarysector.SSRecipes;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.block.BlockElectricMotor;
import shift.sextiarysector.item.ItemBlockDirection;
import shift.sextiarysector.renderer.block.RendererElectricMotor;
import shift.sextiarysector.tileentity.TileEntityElectricMotor;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PluginIC2 implements IPlugin {


	//1EU -> 3Power 2Speed

	@Override
	public String getModName() {
		return "IC2";
	}

	public static int electricMotorType;
	public static Block electricMotor;

	public static Item ic2Dust;//3 Copper , 7 Tin
	public static Item cable;

	@Override
	public void prePlugin(FMLPreInitializationEvent event) {

		electricMotor = new BlockElectricMotor().setBlockName("ss.electric_motor").setCreativeTab(SextiarySectorAPI.TabSSMachine);
		GameRegistry.registerBlock(electricMotor, ItemBlockDirection.class, "ElectricMotor");
		GameRegistry.registerTileEntity(TileEntityElectricMotor.class, "ElectricMotor");

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void preClientPlugin(FMLPreInitializationEvent event) {

		electricMotorType = cpw.mods.fml.client.registry.RenderingRegistry.getNextAvailableRenderId();
		cpw.mods.fml.client.registry.RenderingRegistry.registerBlockHandler(new RendererElectricMotor());
		cpw.mods.fml.client.registry.ClientRegistry.bindTileEntitySpecialRenderer(TileEntityElectricMotor.class, new RendererElectricMotor());

	}

	@Override
	public void initPlugin(FMLInitializationEvent event) {

		ic2Dust = GameRegistry.findItem("IC2", "itemDust");
		cable = GameRegistry.findItem("IC2", "itemCable");

		SSRecipes.pulverizer.add("oreCopper", new ItemStack(ic2Dust,2,3));
		SSRecipes.pulverizer.add("oreTin", new ItemStack(ic2Dust,2,7));

		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(this.electricMotor, 1),
					new Object[] { "xzx", "xyx","pbp",
				Character.valueOf('x'), "paneGlassColorless",
				Character.valueOf('y'), SSItems.energyRing,
				Character.valueOf('z'), SSBlocks.steelShaft,
				Character.valueOf('b'), new ItemStack(cable,1,0),
				Character.valueOf('p'), "plateDenseTin"
				}));


	}

	@Override
	public void postPlugin(FMLPostInitializationEvent event) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
