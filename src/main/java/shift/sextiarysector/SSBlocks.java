package shift.sextiarysector;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.api.season.Season;
import shift.sextiarysector.block.BlockChunkLoader;
import shift.sextiarysector.block.BlockGFTank;
import shift.sextiarysector.block.BlockGearBox;
import shift.sextiarysector.block.BlockHole;
import shift.sextiarysector.block.BlockLargeFurnace;
import shift.sextiarysector.block.BlockMonitor;
import shift.sextiarysector.block.BlockPowerStone;
import shift.sextiarysector.block.BlockSSChest;
import shift.sextiarysector.block.BlockSSCrop;
import shift.sextiarysector.block.BlockSSCrop.CropStatus;
import shift.sextiarysector.block.BlockSSCrop.CropType;
import shift.sextiarysector.block.BlockSSFarmland;
import shift.sextiarysector.block.BlockSSOre;
import shift.sextiarysector.block.BlockSSPane;
import shift.sextiarysector.block.BlockShaft;
import shift.sextiarysector.block.BlockShippingBox;
import shift.sextiarysector.block.BlockSimpleMachine;
import shift.sextiarysector.block.BlockSmallWindmill;
import shift.sextiarysector.item.ItemBlockCrop;
import shift.sextiarysector.item.ItemBlockMonitor;
import shift.sextiarysector.item.ItemBlockShaft;
import shift.sextiarysector.tileentity.TileEntityFarmland;
import shift.sextiarysector.tileentity.TileEntityGFTank;
import shift.sextiarysector.tileentity.TileEntityGearBox;
import shift.sextiarysector.tileentity.TileEntityLargeFurnace;
import shift.sextiarysector.tileentity.TileEntityMonitor;
import shift.sextiarysector.tileentity.TileEntitySSChest;
import shift.sextiarysector.tileentity.TileEntitySSCrop;
import shift.sextiarysector.tileentity.TileEntityShaft;
import shift.sextiarysector.tileentity.TileEntitySimpleMachine;
import shift.sextiarysector.tileentity.TileEntitySmallWindmill;
import cpw.mods.fml.common.registry.GameRegistry;

public class SSBlocks {

	public static String ID = "sextiarysector";

	public static Block LargeFurnace;

	public static Block woodShaft;
	public static Block stoneShaft;


	public static Block woodGearBox;

	public static Block woodGFTank;
	public static Block stoneGFTank;

	public static Block smallWindmill;

	public static Block millstone;
	public static Block loom;


	public static Block hole;

	public static Block woodGrate;

	public static Block chunkLoader;

	public static Block blueStoneOre;
	public static Block yellowStoneOre;

	public static Block coalLargeOre;

	public static Block shippingBox;
	public static Block creeperChest;

	public static Block monitor;

	public static Block farmland;

	public static Block turnip;
	public static Block cucumber;

	public static Block onion;
	public static Block tomato;
	public static Block corn;

	public static Block eggplant;
	public static Block sweetPotato;
	public static Block greenPepper;

	public static Block radish;

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

		coalLargeOre = new BlockSSOre(SSItems.coalDust,Blocks.coal_ore,1).setBlockName("ss.coal_large_ore").setBlockTextureName("sextiarysector:ore/coal_large_ore");
		GameRegistry.registerBlock(coalLargeOre, "CoalLargeOre");


		GameRegistry.registerTileEntity(TileEntityShaft.class, "Shaft");

		woodShaft = new BlockShaft(1).setBlockName("ss.wood_shaft").setBlockTextureName("planks_oak").setStepSound(Block.soundTypeWood);
		GameRegistry.registerBlock(woodShaft,ItemBlockShaft.class, "WoodShaft");

		stoneShaft = new BlockShaft(2).setBlockName("ss.stone_shaft").setBlockTextureName("planks_oak").setStepSound(Block.soundTypeStone);
		GameRegistry.registerBlock(stoneShaft,ItemBlockShaft.class, "StoneShaft");

		woodGearBox = new BlockGearBox(Material.wood).setBlockName("ss.wood_gear_box").setBlockTextureName("sextiarysector:machine/wood_gear_box").setStepSound(Block.soundTypeWood);
		GameRegistry.registerBlock(woodGearBox, "WoodGearBox");
		GameRegistry.registerTileEntity(TileEntityGearBox.class, "GearBox");


		GameRegistry.registerTileEntity(TileEntityGFTank.class, "GFTank");
		woodGFTank = new BlockGFTank(Material.wood,40,1).setBlockName("ss.wood_gf_tank").setBlockTextureName("sextiarysector:machine/wood_gf_tank").setStepSound(Block.soundTypeWood);
		GameRegistry.registerBlock(woodGFTank, "WoodGFTank");

		stoneGFTank = new BlockGFTank(Material.rock,41,2).setBlockName("ss.stone_gf_tank").setBlockTextureName("sextiarysector:machine/stone_gf_tank").setStepSound(Block.soundTypeWood);
		GameRegistry.registerBlock(stoneGFTank, "StoneGFTank");

		smallWindmill = new BlockSmallWindmill().setBlockName("ss.small_windmill").setBlockTextureName("planks_oak").setCreativeTab(SextiarySectorAPI.TabSSMachine);
		GameRegistry.registerTileEntity(TileEntitySmallWindmill.class, "SmallWindmill");
		GameRegistry.registerBlock(smallWindmill, "SmallWindmill");

		GameRegistry.registerTileEntity(TileEntitySimpleMachine.class, "SimpleMachine");
		millstone = new BlockSimpleMachine("millstone",20,SSRecipes.millstone,1).setBlockName("ss.millstone").setCreativeTab(SextiarySectorAPI.TabSSMachine);
		GameRegistry.registerBlock(millstone, "Millstone");

		loom = new BlockSimpleMachine("loom",21,SSRecipes.loom,1).setBlockName("ss.loom").setCreativeTab(SextiarySectorAPI.TabSSMachine);
		GameRegistry.registerBlock(loom, "Loom");

		shippingBox = new BlockShippingBox().setBlockName("ss.shipping_box").setBlockTextureName("sextiarysector:shipping_box").setCreativeTab(SextiarySectorAPI.TabSSEconomy);
		GameRegistry.registerBlock(shippingBox, "ShippingBox");

		creeperChest = new BlockSSChest(6).setBlockName("ss.creeper_chest").setHardness(2.5F).setStepSound(Block.soundTypeWood).setCreativeTab(SextiarySectorAPI.TabSSEconomy);
		GameRegistry.registerBlock(creeperChest, "CreeperChest");
		GameRegistry.registerTileEntity(TileEntitySSChest.class, "SSChest");

		monitor = new BlockMonitor().setBlockName("ss.monitor").setBlockTextureName("stone");
		GameRegistry.registerBlock(monitor,ItemBlockMonitor.class, "Monitor");
		GameRegistry.registerTileEntity(TileEntityMonitor.class, "SSMonitor");


		farmland = new BlockSSFarmland().setBlockName("ss.farmland").setBlockTextureName("farmland").setCreativeTab(SextiarySectorAPI.TabSSAgriculture);
		GameRegistry.registerBlock(farmland,"Farmland");
		GameRegistry.registerTileEntity(TileEntityFarmland.class, "SSFarmland");

		//野菜
		GameRegistry.registerTileEntity(TileEntitySSCrop.class, "SSCrop");

		turnip = new BlockSSCrop(CropType.Normal, new CropStatus(new int[]{2,3,4},Season.SPRING), SSItems.turnip, false).setBlockName("ss.turnip").setBlockTextureName("turnip");
		GameRegistry.registerBlock(turnip,ItemBlockCrop.class,"BlockTurnip");

		cucumber = new BlockSSCrop(CropType.Close, new CropStatus(new int[]{2,6,10,3},Season.SPRING), SSItems.cucumber, true).setBlockName("ss.cucumber").setBlockTextureName("cucumber");
		GameRegistry.registerBlock(cucumber,ItemBlockCrop.class,"BlockCucumber");


		onion = new BlockSSCrop(CropType.Normal, new CropStatus(new int[]{2,4,8},Season.SUMMER), SSItems.onion, false).setBlockName("ss.onion").setBlockTextureName("onion");
		GameRegistry.registerBlock(onion,ItemBlockCrop.class,"BlockOnion");

		tomato = new BlockSSCrop(CropType.Close, new CropStatus(new int[]{4,10,14,2},Season.SUMMER), SSItems.tomato, true).setBlockName("ss.tomato").setBlockTextureName("tomato");
		GameRegistry.registerBlock(tomato,ItemBlockCrop.class,"BlockTomato");

		corn = new BlockSSCrop(CropType.Close, new CropStatus(new int[]{5,10,16,4},Season.SUMMER), SSItems.corn, true).setBlockName("ss.corn").setBlockTextureName("corn");
		GameRegistry.registerBlock(corn,ItemBlockCrop.class,"BlockCorn");


		eggplant = new BlockSSCrop(CropType.Close, new CropStatus(new int[]{3,7,12,3},Season.AUTUMN), SSItems.eggplant, true).setBlockName("ss.eggplant").setBlockTextureName("eggplant");
		GameRegistry.registerBlock(eggplant,ItemBlockCrop.class,"BlockEggplant");

		sweetPotato = new BlockSSCrop(CropType.Normal, new CropStatus(new int[]{2,4,6,2},Season.AUTUMN), SSItems.sweetPotato, true).setBlockName("ss.sweet_potato").setBlockTextureName("sweet_potato");
		GameRegistry.registerBlock(sweetPotato,ItemBlockCrop.class,"BlockSweetPotato");

		greenPepper = new BlockSSCrop(CropType.Close, new CropStatus(new int[]{4,8,14,3},Season.AUTUMN), SSItems.greenPepper, true).setBlockName("ss.green_pepper").setBlockTextureName("green_pepper");
		GameRegistry.registerBlock(greenPepper,ItemBlockCrop.class,"BlockGreenPepper");

		radish = new BlockSSCrop(CropType.Normal, new CropStatus(new int[]{2,3,4},Season.WINTER), SSItems.radish, false).setBlockName("ss.radish").setBlockTextureName("radish");
		GameRegistry.registerBlock(radish,ItemBlockCrop.class,"BlockRadish");

		//initVanillaBlock();


	}

}
