package shift.sextiarysector;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.block.BlockLargeFurnace;
import shift.sextiarysector.block.BlockSSPane;
import shift.sextiarysector.block.BlockShaft;
import shift.sextiarysector.block.BlockSimpleMachine;
import shift.sextiarysector.block.BlockSmallWindmill;
import shift.sextiarysector.item.ItemBlockShaft;
import shift.sextiarysector.tileentity.TileEntityLargeFurnace;
import shift.sextiarysector.tileentity.TileEntityShaft;
import shift.sextiarysector.tileentity.TileEntitySimpleMachine;
import shift.sextiarysector.tileentity.TileEntitySmallWindmill;
import cpw.mods.fml.common.registry.GameRegistry;

public class SSBlocks {

	public static String ID = "sextiarysector";

	public static Block LargeFurnace;

	public static Block shaft;
	public static Block smallWindmill;
	public static Block millstone;


	public static Block woodGrate;

	public static void initBlicks(){

		LargeFurnace = new BlockLargeFurnace().setBlockName("ss.large_furnace").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerBlock(LargeFurnace, "LargeFurnace");
		GameRegistry.registerTileEntity(TileEntityLargeFurnace.class, "LargeFurnace");

		woodGrate = (new BlockSSPane(ID+":wood_grate", ID+":wood_grate", Material.wood, false)).setHardness(0.5F).setBlockName("ss.wood_grate").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerBlock(woodGrate, "WoodGrate");

		shaft = new BlockShaft().setBlockName("ss.wood_shaft").setCreativeTab(SextiarySectorAPI.TabSSMachine);
		GameRegistry.registerBlock(shaft,ItemBlockShaft.class, "WoodShaft");
		GameRegistry.registerTileEntity(TileEntityShaft.class, "Shaft");

		smallWindmill = new BlockSmallWindmill().setBlockName("ss.small_windmill").setCreativeTab(SextiarySectorAPI.TabSSMachine);
		GameRegistry.registerTileEntity(TileEntitySmallWindmill.class, "SmallWindmill");
		GameRegistry.registerBlock(smallWindmill, "SmallWindmill");

		millstone = new BlockSimpleMachine("millstone",20,SSRecipes.millstone).setBlockName("ss.millstone").setCreativeTab(SextiarySectorAPI.TabSSMachine);

		GameRegistry.registerTileEntity(TileEntitySimpleMachine.class, "ss_millstone");
		GameRegistry.registerBlock(millstone, "ss_millstone");

	}

}
