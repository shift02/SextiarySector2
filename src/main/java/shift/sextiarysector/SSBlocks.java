package shift.sextiarysector;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.block.BlockChunkLoader;
import shift.sextiarysector.block.BlockGFTank;
import shift.sextiarysector.block.BlockGearBox;
import shift.sextiarysector.block.BlockHole;
import shift.sextiarysector.block.BlockLargeFurnace;
import shift.sextiarysector.block.BlockPowerStone;
import shift.sextiarysector.block.BlockSSChest;
import shift.sextiarysector.block.BlockSSPane;
import shift.sextiarysector.block.BlockShaft;
import shift.sextiarysector.block.BlockShippingBox;
import shift.sextiarysector.block.BlockSimpleMachine;
import shift.sextiarysector.block.BlockSmallWindmill;
import shift.sextiarysector.item.ItemBlockShaft;
import shift.sextiarysector.tileentity.TileEntityGFTank;
import shift.sextiarysector.tileentity.TileEntityGearBox;
import shift.sextiarysector.tileentity.TileEntityLargeFurnace;
import shift.sextiarysector.tileentity.TileEntitySSChest;
import shift.sextiarysector.tileentity.TileEntityShaft;
import shift.sextiarysector.tileentity.TileEntitySimpleMachine;
import shift.sextiarysector.tileentity.TileEntitySmallWindmill;
import cpw.mods.fml.common.registry.GameRegistry;

public class SSBlocks {

	public static String ID = "sextiarysector";

	public static Block LargeFurnace;

	public static Block shaft;
	public static Block woodGearBox;

	public static Block woodGFTank;

	public static Block smallWindmill;
	public static Block millstone;

	public static Block hole;

	public static Block woodGrate;

	public static Block chunkLoader;

	public static Block blueStoneOre;
	public static Block yellowStoneOre;

	public static Block shippingBox;
	public static Block creeperChest;


	public static void initBlicks(){

		LargeFurnace = new BlockLargeFurnace().setBlockName("ss.large_furnace").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerBlock(LargeFurnace, "LargeFurnace");
		GameRegistry.registerTileEntity(TileEntityLargeFurnace.class, "LargeFurnace");

		hole = new BlockHole().setBlockName("ss.hole").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerBlock(hole, "Hole");

		woodGrate = (new BlockSSPane(ID+":wood_grate", ID+":wood_grate", Material.wood, false)).setHardness(0.5F).setBlockName("ss.wood_grate").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerBlock(woodGrate, "WoodGrate");

		chunkLoader = new BlockChunkLoader().setHardness(1.5F).setBlockName("ss.chunk_loader").setBlockTextureName("sextiarysector:time_loader");
		GameRegistry.registerBlock(chunkLoader, "ChunkLoader");

		blueStoneOre = new BlockPowerStone().setBlockName("ss.blue_stone").setBlockTextureName("sextiarysector:bluestone_ore").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerBlock(blueStoneOre, "BlueStoneOre");

		yellowStoneOre = new BlockPowerStone().setBlockName("ss.yellow_stone").setBlockTextureName("sextiarysector:yellowstone_ore").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerBlock(yellowStoneOre, "YellowStoneOre");

		shaft = new BlockShaft().setBlockName("ss.wood_shaft").setBlockTextureName("planks_oak").setStepSound(Block.soundTypeWood);
		GameRegistry.registerBlock(shaft,ItemBlockShaft.class, "WoodShaft");
		GameRegistry.registerTileEntity(TileEntityShaft.class, "Shaft");

		woodGearBox = new BlockGearBox(Material.wood).setBlockName("ss.wood_gear_box").setBlockTextureName("sextiarysector:machine/wood_gear_box").setStepSound(Block.soundTypeWood);
		GameRegistry.registerBlock(woodGearBox, "WoodGearBox");
		GameRegistry.registerTileEntity(TileEntityGearBox.class, "GearBox");

		woodGFTank = new BlockGFTank(Material.wood,40).setBlockName("ss.wood_gf_tank").setBlockTextureName("sextiarysector:machine/wood_gf_tank").setStepSound(Block.soundTypeWood);
		GameRegistry.registerBlock(woodGFTank, "WoodGFTank");
		GameRegistry.registerTileEntity(TileEntityGFTank.class, "GFTank");

		smallWindmill = new BlockSmallWindmill().setBlockName("ss.small_windmill").setBlockTextureName("planks_oak").setCreativeTab(SextiarySectorAPI.TabSSMachine);
		GameRegistry.registerTileEntity(TileEntitySmallWindmill.class, "SmallWindmill");
		GameRegistry.registerBlock(smallWindmill, "SmallWindmill");

		millstone = new BlockSimpleMachine("millstone",20,SSRecipes.millstone).setBlockName("ss.millstone").setCreativeTab(SextiarySectorAPI.TabSSMachine);

		GameRegistry.registerTileEntity(TileEntitySimpleMachine.class, "ss_millstone");
		GameRegistry.registerBlock(millstone, "Millstone");

		shippingBox = new BlockShippingBox().setBlockName("ss.shipping_box").setBlockTextureName("sextiarysector:shipping_box").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerBlock(shippingBox, "ShippingBox");

		creeperChest = new BlockSSChest(6).setBlockName("ss.creeper_chest").setHardness(2.5F).setStepSound(Block.soundTypeWood).setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerBlock(creeperChest, "CreeperChest");
		GameRegistry.registerTileEntity(TileEntitySSChest.class, "SSChest");

	}

}
