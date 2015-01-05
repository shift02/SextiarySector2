package shift.sextiarysector;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.api.season.Season;
import shift.sextiarysector.block.BlcokPaddy;
import shift.sextiarysector.block.BlockBlueFire;
import shift.sextiarysector.block.BlockChunkLoader;
import shift.sextiarysector.block.BlockFan;
import shift.sextiarysector.block.BlockFluidCrafter;
import shift.sextiarysector.block.BlockFluidFurnace;
import shift.sextiarysector.block.BlockFoodSmokers;
import shift.sextiarysector.block.BlockGFTank;
import shift.sextiarysector.block.BlockGearBox;
import shift.sextiarysector.block.BlockGearShaft;
import shift.sextiarysector.block.BlockHole;
import shift.sextiarysector.block.BlockLargeFurnace;
import shift.sextiarysector.block.BlockLargeOre;
import shift.sextiarysector.block.BlockMagicFurnace;
import shift.sextiarysector.block.BlockMonitor;
import shift.sextiarysector.block.BlockPowerStone;
import shift.sextiarysector.block.BlockSSChest;
import shift.sextiarysector.block.BlockSSCrop;
import shift.sextiarysector.block.BlockSSCrop.CropStatus;
import shift.sextiarysector.block.BlockSSCrop.CropType;
import shift.sextiarysector.block.BlockSSFarmland;
import shift.sextiarysector.block.BlockSSFluid;
import shift.sextiarysector.block.BlockSSOre;
import shift.sextiarysector.block.BlockSSPane;
import shift.sextiarysector.block.BlockSandpit;
import shift.sextiarysector.block.BlockShaft;
import shift.sextiarysector.block.BlockShippingBox;
import shift.sextiarysector.block.BlockSimpleMachine;
import shift.sextiarysector.block.BlockSmallWaterwheel;
import shift.sextiarysector.block.BlockSmallWindmill;
import shift.sextiarysector.block.BlockWindmill;
import shift.sextiarysector.block.BlockWoodHopper;
import shift.sextiarysector.item.ItemBlockCrop;
import shift.sextiarysector.item.ItemBlockDirection;
import shift.sextiarysector.item.ItemBlockFluidCrafter;
import shift.sextiarysector.item.ItemBlockGearShaft;
import shift.sextiarysector.item.ItemBlockMonitor;
import shift.sextiarysector.tileentity.TileEntityFan;
import shift.sextiarysector.tileentity.TileEntityFarmland;
import shift.sextiarysector.tileentity.TileEntityFluidCrafter;
import shift.sextiarysector.tileentity.TileEntityFluidFurnace;
import shift.sextiarysector.tileentity.TileEntityFoodSmokers;
import shift.sextiarysector.tileentity.TileEntityGFTank;
import shift.sextiarysector.tileentity.TileEntityGearBox;
import shift.sextiarysector.tileentity.TileEntityGearShaft;
import shift.sextiarysector.tileentity.TileEntityLargeFurnace;
import shift.sextiarysector.tileentity.TileEntityMagicFurnace;
import shift.sextiarysector.tileentity.TileEntityMonitor;
import shift.sextiarysector.tileentity.TileEntityPaddy;
import shift.sextiarysector.tileentity.TileEntitySSChest;
import shift.sextiarysector.tileentity.TileEntitySSCrop;
import shift.sextiarysector.tileentity.TileEntityShaft;
import shift.sextiarysector.tileentity.TileEntitySimpleMachine;
import shift.sextiarysector.tileentity.TileEntitySmallWaterwheel;
import shift.sextiarysector.tileentity.TileEntitySmallWindmill;
import shift.sextiarysector.tileentity.TileEntityWindmill;
import cpw.mods.fml.common.registry.GameRegistry;

public class SSBlocks {

	public static String ID = "sextiarysector";

	public static Block LargeFurnace;

	//public static Block craftFurnace;
	//public static Block stoneFrame;

	public static Block fluidFurnace;
	public static Block foodSmokers;
	public static Block magicFurnace;



	//public static Block bottle;
	public static Block fluidCrafter;

	public static Block woodHopper;

	public static Block blueFire;

	//液体
	public static Block drinkingWater;

	//GF
	public static Block woodShaft;
	public static Block stoneShaft;
	public static Block steelShaft;

	public static Block woodGearBox;
	public static Block stoneGearBox;
	public static Block steelGearBox;

	public static Block woodGFTank;
	public static Block stoneGFTank;
	public static Block steelGFTank;

	public static Block woodStoneGearShaft;

	public static Block smallWindmill;
	public static Block windmill;
	public static Block smallWaterwheel;

	public static Block millstone;
	public static Block loom;

	public static Block sawmill;

	public static Block pulverizer;

	public static Block fan;

	public static Block hole;

	public static Block woodGrate;

	public static Block woodPlate;

	public static Block chunkLoader;

	//鉱石
	public static Block blueStoneOre;
	public static Block yellowStoneOre;

	public static Block mithrilOre;
	public static Block orichalcumOre;

	public static Block coalLargeOre;
	public static Block ironLargeOre;
	public static Block goldLargeOre;

	public static Block shippingBox;
	public static Block creeperChest;

	//経済
	public static Block monitor;

	//農業
	public static Block farmland;
	public static Block paddy;

	public static Block turnip;
	public static Block cucumber;

	public static Block onion;
	public static Block tomato;
	public static Block corn;

	public static Block eggplant;
	public static Block sweetPotato;
	public static Block greenPepper;

	public static Block radish;

	public static Block rice;


	//水産
	public static Block sandpit;

	public static void initBlicks(){

		LargeFurnace = new BlockLargeFurnace().setBlockName("ss.large_furnace").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerBlock(LargeFurnace, "LargeFurnace");
		GameRegistry.registerTileEntity(TileEntityLargeFurnace.class, "LargeFurnace");

		//craftFurnace = new BlockCraftFurnace().setBlockName("ss.large_furnace").setCreativeTab(SextiarySectorAPI.TabSSCore);
		//GameRegistry.registerBlock(craftFurnace, "CraftFurnace");
		//GameRegistry.registerTileEntity(TileEntityCraftFurnace.class, "CraftFurnace");
		//stoneFrame = new BlockStoneFrame().setBlockName("ss.stone_frame").setCreativeTab(SextiarySectorAPI.TabSSCore);
		//GameRegistry.registerBlock(stoneFrame, "StoneFrame");
		//GameRegistry.registerTileEntity(TileEntityStoneFrame.class, "StoneFrame");

		fluidFurnace = new BlockFluidFurnace().setBlockName("ss.fluid_furnace").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerBlock(fluidFurnace, "FluidFurnace");
		GameRegistry.registerTileEntity(TileEntityFluidFurnace.class, "FluidFurnace");

		foodSmokers = new BlockFoodSmokers().setBlockName("ss.food_smokers").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerBlock(foodSmokers, "FoodSmokers");
		GameRegistry.registerTileEntity(TileEntityFoodSmokers.class, "FoodSmokers");

		magicFurnace = new BlockMagicFurnace().setBlockName("ss.magic_furnace").setCreativeTab(SextiarySectorAPI.TabSSMagic);
		GameRegistry.registerBlock(magicFurnace, "MagicFurnace");
		GameRegistry.registerTileEntity(TileEntityMagicFurnace.class, "MagicFurnace");

		//bottle = new BlockBottle().setBlockName("ss.bottle").setBlockTextureName("glass");
		//GameRegistry.registerBlock(bottle,ItemBlockBottle.class, "Bottle");
		//GameRegistry.registerTileEntity(TileEntityBlockBottle.class, "Bottle");

		fluidCrafter = new BlockFluidCrafter().setBlockName("ss.fluid_crafter").setBlockTextureName("glass");
		GameRegistry.registerBlock(fluidCrafter, ItemBlockFluidCrafter.class, "FluidCrafter");
		GameRegistry.registerTileEntity(TileEntityFluidCrafter.class, "FluidCrafter");

		hole = new BlockHole().setBlockName("ss.hole").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerBlock(hole, "Hole");

		woodHopper = new BlockWoodHopper().setBlockName("ss.wood_hopper").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerBlock(woodHopper, "WoodHopper");

		woodGrate = (new BlockSSPane(ID+":wood_grate", ID+":wood_grate", Material.wood, false,0)).setHardness(0.5F).setBlockName("ss.wood_grate").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerBlock(woodGrate, "WoodGrate");

		woodPlate = (new BlockSSPane("planks_oak", "planks_oak", Material.wood, false,1)).setHardness(0.5F).setBlockName("ss.wood_plate").setBlockTextureName(ID+":plate/wood_plate").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerBlock(woodPlate, "WoodPlate");

		chunkLoader = new BlockChunkLoader().setHardness(1.5F).setBlockName("ss.chunk_loader").setBlockTextureName("sextiarysector:time_loader");
		GameRegistry.registerBlock(chunkLoader, "ChunkLoader");

		blueFire = new BlockBlueFire().setBlockName("ss.blue_fire").setBlockTextureName("sextiarysector:blue_fire");
		GameRegistry.registerBlock(blueFire, "BlueFire");

		drinkingWater = new BlockSSFluid(SSFluids.drinkingWater).setBlockName("ss.drinking_water").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerBlock(drinkingWater, "DrinkingWater");

		//鉱石
		blueStoneOre = new BlockPowerStone().setBlockName("ss.blue_stone").setBlockTextureName("sextiarysector:bluestone_ore").setCreativeTab(SextiarySectorAPI.TabSSMining);
		GameRegistry.registerBlock(blueStoneOre, "BlueStoneOre");

		yellowStoneOre = new BlockPowerStone().setBlockName("ss.yellow_stone").setBlockTextureName("sextiarysector:yellowstone_ore").setCreativeTab(SextiarySectorAPI.TabSSMining);
		GameRegistry.registerBlock(yellowStoneOre, "YellowStoneOre");

		mithrilOre = new BlockSSOre(null,2).setBlockName("ss.mithril_ore").setBlockTextureName("sextiarysector:ore/mithril_ore");
		GameRegistry.registerBlock(mithrilOre, "MithrilOre");

		orichalcumOre = new BlockSSOre(SSItems.orichalcumGem,3).setBlockName("ss.orichalcum_ore").setBlockTextureName("sextiarysector:ore/orichalcum_ore");
		GameRegistry.registerBlock(orichalcumOre, "OrichalcumOre");

		coalLargeOre = new BlockLargeOre(SSItems.coalDust,Blocks.coal_ore,1).setBlockName("ss.coal_large_ore").setBlockTextureName("sextiarysector:ore/coal_large_ore");
		GameRegistry.registerBlock(coalLargeOre, "CoalLargeOre");

		ironLargeOre = new BlockLargeOre(SSItems.ironDust,Blocks.iron_ore,2).setBlockName("ss.iron_large_ore").setBlockTextureName("sextiarysector:ore/iron_large_ore");
		GameRegistry.registerBlock(ironLargeOre, "IronLargeOre");

		goldLargeOre = new BlockLargeOre(SSItems.goldDust,Blocks.gold_ore,2).setBlockName("ss.gold_large_ore").setBlockTextureName("sextiarysector:ore/gold_large_ore");
		GameRegistry.registerBlock(goldLargeOre, "GoldLargeOre");

		//GF
		GameRegistry.registerTileEntity(TileEntityShaft.class, "Shaft");

		woodShaft = new BlockShaft(1).setBlockName("ss.wood_shaft").setBlockTextureName("planks_oak").setStepSound(Block.soundTypeWood);
		GameRegistry.registerBlock(woodShaft,ItemBlockDirection.class, "WoodShaft");

		stoneShaft = new BlockShaft(2).setBlockName("ss.stone_shaft").setBlockTextureName("planks_oak").setStepSound(Block.soundTypeStone);
		GameRegistry.registerBlock(stoneShaft,ItemBlockDirection.class, "StoneShaft");

		steelShaft = new BlockShaft(3).setBlockName("ss.steel_shaft").setBlockTextureName("planks_oak").setStepSound(Block.soundTypeMetal);
		GameRegistry.registerBlock(steelShaft,ItemBlockDirection.class, "SteelShaft");


		GameRegistry.registerTileEntity(TileEntityGearBox.class, "GearBox");
		woodGearBox = new BlockGearBox(Material.wood,1).setBlockName("ss.wood_gear_box").setBlockTextureName("sextiarysector:machine/wood_gear_box").setStepSound(Block.soundTypeWood);
		GameRegistry.registerBlock(woodGearBox, "WoodGearBox");

		stoneGearBox = new BlockGearBox(Material.rock,2).setBlockName("ss.stone_gear_box").setBlockTextureName("sextiarysector:machine/stone_gear_box").setStepSound(Block.soundTypeStone);
		GameRegistry.registerBlock(stoneGearBox, "StoneGearBox");

		steelGearBox = new BlockGearBox(Material.iron,3).setBlockName("ss.steel_gear_box").setBlockTextureName("sextiarysector:machine/steel_gear_box").setStepSound(Block.soundTypeMetal);
		GameRegistry.registerBlock(steelGearBox, "SteelGearBox");


		GameRegistry.registerTileEntity(TileEntityGFTank.class, "GFTank");
		woodGFTank = new BlockGFTank(Material.wood,50,1).setBlockName("ss.wood_gf_tank").setBlockTextureName("sextiarysector:machine/wood_gf_tank").setStepSound(Block.soundTypeWood);
		GameRegistry.registerBlock(woodGFTank, "WoodGFTank");

		stoneGFTank = new BlockGFTank(Material.rock,51,2).setBlockName("ss.stone_gf_tank").setBlockTextureName("sextiarysector:machine/stone_gf_tank").setStepSound(Block.soundTypeWood);
		GameRegistry.registerBlock(stoneGFTank, "StoneGFTank");

		steelGFTank = new BlockGFTank(Material.iron,52,3).setBlockName("ss.steel_gf_tank").setBlockTextureName("sextiarysector:machine/steel_gf_tank").setStepSound(Block.soundTypeMetal);
		GameRegistry.registerBlock(steelGFTank, "SteelGFTank");


		GameRegistry.registerTileEntity(TileEntityGearShaft.class, "GearShaft");
		woodStoneGearShaft = new BlockGearShaft(1).setBlockName("ss.wood_stone_gear_shaft").setBlockTextureName("planks_oak").setStepSound(Block.soundTypeWood);
		GameRegistry.registerBlock(woodStoneGearShaft,ItemBlockGearShaft.class, "WoodStoneGearShaft");

		smallWindmill = new BlockSmallWindmill().setBlockName("ss.small_windmill").setBlockTextureName("planks_oak").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
		GameRegistry.registerTileEntity(TileEntitySmallWindmill.class, "SmallWindmill");
		GameRegistry.registerBlock(smallWindmill, "SmallWindmill");

		windmill = new BlockWindmill().setBlockName("ss.windmill").setBlockTextureName("planks_oak").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
		GameRegistry.registerTileEntity(TileEntityWindmill.class, "Windmill");
		GameRegistry.registerBlock(windmill, "Windmill");

		smallWaterwheel = new BlockSmallWaterwheel().setBlockName("ss.small_waterwheel").setBlockTextureName("planks_oak").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
		GameRegistry.registerTileEntity(TileEntitySmallWaterwheel.class, "SmallWaterwheel");
		GameRegistry.registerBlock(smallWaterwheel, "SmallWaterwheel");

		GameRegistry.registerTileEntity(TileEntitySimpleMachine.class, "SimpleMachine");
		millstone = new BlockSimpleMachine("millstone",20,SSRecipes.millstone,1).setBlockName("ss.millstone").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
		GameRegistry.registerBlock(millstone, "Millstone");

		loom = new BlockSimpleMachine("loom",21,SSRecipes.loom,1).setBlockName("ss.loom").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
		GameRegistry.registerBlock(loom, "Loom");

		sawmill = new BlockSimpleMachine("sawmill",25,SSRecipes.sawmill,2).setBlockName("ss.sawmill").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
		GameRegistry.registerBlock(sawmill, "Sawmill");

		pulverizer = new BlockSimpleMachine("pulverizer",30,SSRecipes.pulverizer,3).setBlockName("ss.pulverizer").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
		GameRegistry.registerBlock(pulverizer, "Pulverizer");

		fan =  new BlockFan().setBlockName("ss.fan");
		GameRegistry.registerTileEntity(TileEntityFan.class, "Fan");
		GameRegistry.registerBlock(fan, ItemBlockDirection.class, "Fan");

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

		paddy = new BlcokPaddy().setBlockName("ss.paddy").setBlockTextureName("farmland").setCreativeTab(SextiarySectorAPI.TabSSAgriculture);
		GameRegistry.registerBlock(paddy,"Paddy");
		GameRegistry.registerTileEntity(TileEntityPaddy.class, "SSPaddy");

		//野菜
		GameRegistry.registerTileEntity(TileEntitySSCrop.class, "SSCrop");

		turnip = new BlockSSCrop(CropType.Normal, new CropStatus(new int[]{2,3,4},Season.SPRING), farmland, SSItems.turnip, false).setBlockName("ss.turnip").setBlockTextureName("turnip");
		GameRegistry.registerBlock(turnip,ItemBlockCrop.class,"BlockTurnip");

		cucumber = new BlockSSCrop(CropType.Close, new CropStatus(new int[]{2,6,10,3},Season.SPRING), farmland, SSItems.cucumber, true).setBlockName("ss.cucumber").setBlockTextureName("cucumber");
		GameRegistry.registerBlock(cucumber,ItemBlockCrop.class,"BlockCucumber");

		onion = new BlockSSCrop(CropType.Normal, new CropStatus(new int[]{2,4,8},Season.SUMMER), farmland, SSItems.onion, false).setBlockName("ss.onion").setBlockTextureName("onion");
		GameRegistry.registerBlock(onion,ItemBlockCrop.class,"BlockOnion");

		tomato = new BlockSSCrop(CropType.Close, new CropStatus(new int[]{4,10,14,2},Season.SUMMER), farmland, SSItems.tomato, true).setBlockName("ss.tomato").setBlockTextureName("tomato");
		GameRegistry.registerBlock(tomato,ItemBlockCrop.class,"BlockTomato");

		corn = new BlockSSCrop(CropType.Close, new CropStatus(new int[]{5,10,16,4},Season.SUMMER), farmland, SSItems.corn, true).setBlockName("ss.corn").setBlockTextureName("corn");
		GameRegistry.registerBlock(corn,ItemBlockCrop.class,"BlockCorn");


		eggplant = new BlockSSCrop(CropType.Close, new CropStatus(new int[]{3,7,12,3},Season.AUTUMN), farmland, SSItems.eggplant, true).setBlockName("ss.eggplant").setBlockTextureName("eggplant");
		GameRegistry.registerBlock(eggplant,ItemBlockCrop.class,"BlockEggplant");

		sweetPotato = new BlockSSCrop(CropType.Normal, new CropStatus(new int[]{2,4,6,2},Season.AUTUMN), farmland, SSItems.sweetPotato, true).setBlockName("ss.sweet_potato").setBlockTextureName("sweet_potato");
		GameRegistry.registerBlock(sweetPotato,ItemBlockCrop.class,"BlockSweetPotato");

		greenPepper = new BlockSSCrop(CropType.Close, new CropStatus(new int[]{4,8,14,3},Season.AUTUMN), farmland, SSItems.greenPepper, true).setBlockName("ss.green_pepper").setBlockTextureName("green_pepper");
		GameRegistry.registerBlock(greenPepper,ItemBlockCrop.class,"BlockGreenPepper");

		radish = new BlockSSCrop(CropType.Normal, new CropStatus(new int[]{2,3,4},Season.WINTER), farmland, SSItems.radish, false).setBlockName("ss.radish").setBlockTextureName("radish");
		GameRegistry.registerBlock(radish,ItemBlockCrop.class,"BlockRadish");

		//米

		rice = new BlockSSCrop(CropType.Normal, new CropStatus(new int[]{28,46,68},Season.SPRING,Season.SUMMER,Season.AUTUMN), paddy, SSItems.radish, false).setBlockName("ss.rice").setBlockTextureName("rice");
		GameRegistry.registerBlock(rice,ItemBlockCrop.class,"BlockRice");


		//水産
		sandpit = new BlockSandpit().setBlockName("ss.sandpit").setBlockTextureName("sand");
		GameRegistry.registerBlock(sandpit, "Sandpit");

		//initVanillaBlock();


	}

}
