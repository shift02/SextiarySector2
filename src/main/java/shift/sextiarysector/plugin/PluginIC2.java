package shift.sextiarysector.plugin;

import net.minecraft.block.Block;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.block.BlockElectricMotor;
import shift.sextiarysector.item.ItemBlockDirection;
import shift.sextiarysector.renderer.block.RendereElectricMotor;
import shift.sextiarysector.tileentity.TileEntityElectricMotor;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PluginIC2 implements IPlugin {

	@Override
	public String getModName() {
		return "IC2";
	}

	public static int electricMotorType;
	public static Block electricMotor;

	@Override
	public void prePlugin(FMLPreInitializationEvent event) {

		electricMotor = new BlockElectricMotor().setBlockName("ss.electric_motor").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerBlock(electricMotor, ItemBlockDirection.class, "ElectricMotor");
		GameRegistry.registerTileEntity(TileEntityElectricMotor.class, "ElectricMotor");

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void preClientPlugin(FMLPreInitializationEvent event) {

		electricMotorType = cpw.mods.fml.client.registry.RenderingRegistry.getNextAvailableRenderId();
		cpw.mods.fml.client.registry.RenderingRegistry.registerBlockHandler(new RendereElectricMotor());
		cpw.mods.fml.client.registry.ClientRegistry.bindTileEntitySpecialRenderer(TileEntityElectricMotor.class, new RendereElectricMotor());

	}

	@Override
	public void initPlugin(FMLInitializationEvent event) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void postPlugin(FMLPostInitializationEvent event) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
