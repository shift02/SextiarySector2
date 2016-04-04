package shift.sextiarysector;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.block.BlockBlueFire;
import shift.sextiarysector.block.BlockBoardingBuoy;
import shift.sextiarysector.block.BlockChunkLoader;
import shift.sextiarysector.block.BlockCrop;
import shift.sextiarysector.block.BlockDetectorBuoy;
import shift.sextiarysector.block.BlockFan;
import shift.sextiarysector.block.BlockFarmland;
import shift.sextiarysector.block.BlockFigure;
import shift.sextiarysector.block.BlockFluidCrafter;
import shift.sextiarysector.block.BlockFluidFGFMachine;
import shift.sextiarysector.block.BlockFluidFurnace;
import shift.sextiarysector.block.BlockFoodSmokers;
import shift.sextiarysector.block.BlockFreezer;
import shift.sextiarysector.block.BlockFunnel;
import shift.sextiarysector.block.BlockGFTank;
import shift.sextiarysector.block.BlockGearBox;
import shift.sextiarysector.block.BlockGearShaft;
import shift.sextiarysector.block.BlockGutter;
import shift.sextiarysector.block.BlockHalfGutter;
import shift.sextiarysector.block.BlockHole;
import shift.sextiarysector.block.BlockHotSprings;
import shift.sextiarysector.block.BlockKawara;
import shift.sextiarysector.block.BlockKnife;
import shift.sextiarysector.block.BlockLargeFurnace;
import shift.sextiarysector.block.BlockLargeOre;
import shift.sextiarysector.block.BlockLargeWindmill;
import shift.sextiarysector.block.BlockLeaf;
import shift.sextiarysector.block.BlockLeafBed;
import shift.sextiarysector.block.BlockMagiFurnace;
import shift.sextiarysector.block.BlockMonitor;
import shift.sextiarysector.block.BlockMotor;
import shift.sextiarysector.block.BlockOil;
import shift.sextiarysector.block.BlockOreBlock;
import shift.sextiarysector.block.BlockPaddy;
import shift.sextiarysector.block.BlockPipe;
import shift.sextiarysector.block.BlockPowerStone;
import shift.sextiarysector.block.BlockPoweredBuoy;
import shift.sextiarysector.block.BlockPump;
import shift.sextiarysector.block.BlockSSChest;
import shift.sextiarysector.block.BlockSSFluid;
import shift.sextiarysector.block.BlockSSOre;
import shift.sextiarysector.block.BlockSSPane;
import shift.sextiarysector.block.BlockSandpit;
import shift.sextiarysector.block.BlockSaw;
import shift.sextiarysector.block.BlockShaft;
import shift.sextiarysector.block.BlockShippingBox;
import shift.sextiarysector.block.BlockShopMonitor;
import shift.sextiarysector.block.BlockSimpleMachine;
import shift.sextiarysector.block.BlockSmallWaterwheel;
import shift.sextiarysector.block.BlockSmallWindmill;
import shift.sextiarysector.block.BlockSquare;
import shift.sextiarysector.block.BlockSteamMotor;
import shift.sextiarysector.block.BlockSuctionMachine;
import shift.sextiarysector.block.BlockTank;
import shift.sextiarysector.block.BlockTrap;
import shift.sextiarysector.block.BlockWaterSupplyMachine;
import shift.sextiarysector.block.BlockWindmill;
import shift.sextiarysector.block.BlockWood;
import shift.sextiarysector.block.BlockWoodHopper;
import shift.sextiarysector.item.ItemBlockBuoyBase;
import shift.sextiarysector.item.ItemBlockDirection;
import shift.sextiarysector.item.ItemBlockFigure;
import shift.sextiarysector.item.ItemBlockFluidCrafter;
import shift.sextiarysector.item.ItemBlockGearShaft;
import shift.sextiarysector.item.ItemBlockMeta;
import shift.sextiarysector.item.ItemBlockMonitor;
import shift.sextiarysector.item.ItemBlockSSFluid;
import shift.sextiarysector.tileentity.TileEntityCrop;
import shift.sextiarysector.tileentity.TileEntityFan;
import shift.sextiarysector.tileentity.TileEntityFarmland;
import shift.sextiarysector.tileentity.TileEntityFigure;
import shift.sextiarysector.tileentity.TileEntityFluidCrafter;
import shift.sextiarysector.tileentity.TileEntityFluidFGFMachineBase;
import shift.sextiarysector.tileentity.TileEntityFluidFurnace;
import shift.sextiarysector.tileentity.TileEntityFoodSmokers;
import shift.sextiarysector.tileentity.TileEntityFreezer;
import shift.sextiarysector.tileentity.TileEntityFunnel;
import shift.sextiarysector.tileentity.TileEntityGFTank;
import shift.sextiarysector.tileentity.TileEntityGearBox;
import shift.sextiarysector.tileentity.TileEntityGearShaft;
import shift.sextiarysector.tileentity.TileEntityGutter;
import shift.sextiarysector.tileentity.TileEntityHalfGutter;
import shift.sextiarysector.tileentity.TileEntityKnife;
import shift.sextiarysector.tileentity.TileEntityLargeFurnace;
import shift.sextiarysector.tileentity.TileEntityLargeWindmill;
import shift.sextiarysector.tileentity.TileEntityMagicFurnace;
import shift.sextiarysector.tileentity.TileEntityMonitor;
import shift.sextiarysector.tileentity.TileEntityPaddy;
import shift.sextiarysector.tileentity.TileEntityPipe;
import shift.sextiarysector.tileentity.TileEntityPump;
import shift.sextiarysector.tileentity.TileEntitySSChest;
import shift.sextiarysector.tileentity.TileEntitySaw;
import shift.sextiarysector.tileentity.TileEntityShaft;
import shift.sextiarysector.tileentity.TileEntityShippingBox;
import shift.sextiarysector.tileentity.TileEntityShopMonitor;
import shift.sextiarysector.tileentity.TileEntitySimpleMachine;
import shift.sextiarysector.tileentity.TileEntitySmallWaterwheel;
import shift.sextiarysector.tileentity.TileEntitySmallWindmill;
import shift.sextiarysector.tileentity.TileEntitySquare;
import shift.sextiarysector.tileentity.TileEntitySteamMotor;
import shift.sextiarysector.tileentity.TileEntitySuctionMachine;
import shift.sextiarysector.tileentity.TileEntityTank;
import shift.sextiarysector.tileentity.TileEntityTrap;
import shift.sextiarysector.tileentity.TileEntityWaterSupplyMachine;
import shift.sextiarysector.tileentity.TileEntityWindmill;

public class SSBlocks {

    public static String ID = "sextiarysector";

    public static Block LargeFurnace;

    //public static Block craftFurnace;
    //public static Block stoneFrame;

    public static Block fluidFurnace;
    public static Block foodSmokers;
    public static Block magicFurnace;
    public static Block freezer;

    //public static Block bottle;
    public static Block fluidCrafter;

    public static Block woodHopper;

    public static Block square;

    //樋
    public static Block gutter;
    public static Block halfGutter;

    public static Block tank;
    public static Block funnel;
    public static Block copperPipe;

    public static Block blueFire;

    public static Block figure;

    public static Block leafBlock;

    public static Block trap;

    public static Block knife;

    public static Block motor;
    public static Block stickyMotor;

    //素材
    //瓦
    public static Block kawara;

    public static Block animalOil;

    //Bed
    public static Block leafBed;

    //液体
    public static Block drinkingWater;
    public static Block hotSprings;

    public static Block season;

    //GF
    public static Block woodShaft;
    public static Block stoneShaft;
    public static Block steelShaft;
    public static Block ninjaShaft;
    public static Block orichalcumShaft;

    public static Block woodGearBox;
    public static Block stoneGearBox;
    public static Block steelGearBox;
    public static Block ninjaGearBox;
    public static Block orichalcumGearBox;

    public static Block woodGFTank;
    public static Block stoneGFTank;
    public static Block steelGFTank;
    public static Block ninjaGFTank;
    public static Block orichalcumGFTank;

    public static Block woodStoneGearShaft;
    public static Block stoneSteelGearShaft;
    public static Block steelNinjaGearShaft;
    public static Block ninjaOrichalcumGearShaft;

    //発電
    public static Block smallWindmill;
    public static Block windmill;
    public static Block largeWindmill;
    public static Block smallWaterwheel;
    public static Block steamMotor;

    //機械
    //public static Block machineFrame;

    public static Block millstone;
    public static Block loom;

    public static Block sawmill;
    public static Block spinningMachine;
    public static Block extractor;

    public static Block pump;
    public static Block suctionMachine;

    public static Block pulverizer;

    public static Block fan;
    public static Block saw;

    public static Block rollingMachine;
    public static Block manaSqueezer;

    public static Block timeMachine;

    public static Block hole;

    public static Block woodGrate;

    public static Block woodOakPlate;
    public static Block woodBirchPlate;
    public static Block woodSprucePlate;
    public static Block woodJunglePlate;
    public static Block woodAcaciaPlate;
    public static Block woodBigOakPlate;

    //プレート
    public static Block ironPlate;
    public static Block goldPlate;

    public static Block copperPlate;
    public static Block zincPlate;
    public static Block silverPlate;

    public static Block chunkLoader;

    //鉱石
    public static Block blueStoneOre;
    public static Block yellowStoneOre;

    public static Block copperOre;
    public static Block zincOre;
    public static Block silverOre;

    public static Block mithrilOre;
    public static Block orichalcumOre;

    public static Block coalLargeOre;
    public static Block ironLargeOre;
    public static Block goldLargeOre;

    public static Block silverLargeOre;

    //鉱石ブロック
    public static Block copperBlock;
    public static Block zincBlock;
    public static Block silverBlock;

    public static Block steelBlock;
    public static Block brassBlock;
    public static Block ninjaBlock;

    public static Block mithrilBlock;
    public static Block orichalcumBlock;

    //経済
    public static Block shippingBox;
    public static Block creeperChest;

    public static Block monitor;
    public static Block shopMonitor;

    //農業
    public static Block farmland;
    public static Block paddy;
    public static Block wood;
    public static Block crop;

    //農業機械
    public static Block waterSupplyMachine;

    //水産
    public static Block sandpit;

    //輸送
    public static Block boardingBuoy;
    public static Block poweredBuoy;
    public static Block detectorBuoy;

    public static void initBlicks() {

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

        magicFurnace = new BlockMagiFurnace().setBlockName("ss.magic_furnace").setBlockTextureName("sextiarysector:magic/furnace").setCreativeTab(SextiarySectorAPI.TabSSMagic);
        GameRegistry.registerBlock(magicFurnace, "MagicFurnace");
        GameRegistry.registerTileEntity(TileEntityMagicFurnace.class, "MagicFurnace");

        freezer = new BlockFreezer().setBlockName("ss.freezer").setBlockTextureName("sextiarysector:ice/freezer").setCreativeTab(SextiarySectorAPI.TabSSCore);
        GameRegistry.registerBlock(freezer, "Freezer");
        GameRegistry.registerTileEntity(TileEntityFreezer.class, "SSFreezer");

        //bottle = new BlockBottle().setBlockName("ss.bottle").setBlockTextureName("glass");
        //GameRegistry.registerBlock(bottle,ItemBlockBottle.class, "Bottle");
        //GameRegistry.registerTileEntity(TileEntityBlockBottle.class, "Bottle");

        fluidCrafter = new BlockFluidCrafter().setBlockName("ss.fluid_crafter").setBlockTextureName("glass");
        GameRegistry.registerBlock(fluidCrafter, ItemBlockFluidCrafter.class, "FluidCrafter");
        GameRegistry.registerTileEntity(TileEntityFluidCrafter.class, "FluidCrafter");

        hole = new BlockHole().setBlockName("ss.hole").setBlockTextureName("dirt").setCreativeTab(SextiarySectorAPI.TabSSCore);
        GameRegistry.registerBlock(hole, "Hole");

        woodHopper = new BlockWoodHopper().setBlockName("ss.wood_hopper").setCreativeTab(SextiarySectorAPI.TabSSCore);
        GameRegistry.registerBlock(woodHopper, "WoodHopper");

        square = new BlockSquare().setBlockName("ss.square").setBlockTextureName("planks_oak");
        GameRegistry.registerBlock(square, ItemBlockDirection.class, "Square");
        GameRegistry.registerTileEntity(TileEntitySquare.class, "Square");

        gutter = new BlockGutter().setBlockItemTextureName(ID + ":gutter").setBlockName("ss.gutter").setBlockTextureName("planks_oak");
        GameRegistry.registerBlock(gutter, "Gutter");
        GameRegistry.registerTileEntity(TileEntityGutter.class, "Gutter");

        halfGutter = new BlockHalfGutter().setBlockItemTextureName(ID + ":half_gutter").setBlockName("ss.half_gutter").setBlockTextureName("planks_oak");
        GameRegistry.registerBlock(halfGutter, "HalfGutter");
        GameRegistry.registerTileEntity(TileEntityHalfGutter.class, "HalfGutter");

        tank = new BlockTank().setBlockName("ss.tank").setBlockTextureName("glass").setCreativeTab(SextiarySectorAPI.TabSSCore);
        GameRegistry.registerBlock(tank, "Tank");
        GameRegistry.registerTileEntity(TileEntityTank.class, "SSTank");

        funnel = new BlockFunnel().setBlockName("ss.funnel").setBlockTextureName("sextiarysector:funnel").setCreativeTab(SextiarySectorAPI.TabSSCore);
        GameRegistry.registerBlock(funnel, "Funnel");
        GameRegistry.registerTileEntity(TileEntityFunnel.class, "Funnel");

        copperPipe = new BlockPipe().setBlockName("ss.copper_pipe").setBlockTextureName("planks_oak");
        GameRegistry.registerBlock(copperPipe, "CopperPipe");
        GameRegistry.registerTileEntity(TileEntityPipe.class, "SSPipe");

        woodGrate = (new BlockSSPane(ID + ":wood_grate", ID + ":wood_grate", Material.wood, false, 0)).setHardness(0.5F).setBlockName("ss.wood_grate").setCreativeTab(SextiarySectorAPI.TabSSCore);
        GameRegistry.registerBlock(woodGrate, "WoodGrate");

        woodOakPlate = (new BlockSSPane("planks_oak", "planks_oak", Material.wood, false, 1)).setHardness(0.5F).setBlockName("ss.oak_wood_plate").setBlockTextureName(ID + ":plate/wood_oak_plate")
                .setCreativeTab(SextiarySectorAPI.TabSSCore);
        GameRegistry.registerBlock(woodOakPlate, "WoodOakPlate");

        woodBirchPlate = (new BlockSSPane("planks_birch", "planks_birch", Material.wood, false, 1)).setHardness(0.5F).setBlockName("ss.birch_wood_plate").setBlockTextureName(ID + ":plate/wood_birch_plate")
                .setCreativeTab(SextiarySectorAPI.TabSSCore);
        GameRegistry.registerBlock(woodBirchPlate, "WoodBirchPlate");

        woodSprucePlate = (new BlockSSPane("planks_spruce", "planks_spruce", Material.wood, false, 1)).setHardness(0.5F).setBlockName("ss.spruce_wood_plate").setBlockTextureName(ID + ":plate/wood_spruce_plate")
                .setCreativeTab(SextiarySectorAPI.TabSSCore);
        GameRegistry.registerBlock(woodSprucePlate, "WoodSprucePlate");

        woodJunglePlate = (new BlockSSPane("planks_jungle", "planks_jungle", Material.wood, false, 1)).setHardness(0.5F).setBlockName("ss.jungle_wood_plate").setBlockTextureName(ID + ":plate/wood_jungle_plate")
                .setCreativeTab(SextiarySectorAPI.TabSSCore);
        GameRegistry.registerBlock(woodJunglePlate, "WoodJunglePlate");

        woodAcaciaPlate = (new BlockSSPane("planks_acacia", "planks_acacia", Material.wood, false, 1)).setHardness(0.5F).setBlockName("ss.acacia_wood_plate").setBlockTextureName(ID + ":plate/wood_acacia_plate")
                .setCreativeTab(SextiarySectorAPI.TabSSCore);
        GameRegistry.registerBlock(woodAcaciaPlate, "WoodAcaciaPlate");

        woodBigOakPlate = (new BlockSSPane("planks_big_oak", "planks_big_oak", Material.wood, false, 1)).setHardness(0.5F).setBlockName("ss.big_oak_wood_plate").setBlockTextureName(ID + ":plate/wood_big_oak_plate")
                .setCreativeTab(SextiarySectorAPI.TabSSCore);
        GameRegistry.registerBlock(woodBigOakPlate, "WoodBigOakPlate");

        //プレート
        ironPlate = (new BlockSSPane("iron_block", ID + ":iron_block_top", Material.iron, true, 1)).setHardness(0.5F).setBlockName("ss.iron_plate").setBlockTextureName(ID + ":plate/iron_plate")
                .setCreativeTab(SextiarySectorAPI.TabSSMining);
        GameRegistry.registerBlock(ironPlate, "IronPlate");

        goldPlate = (new BlockSSPane("gold_block", ID + ":gold_block_top", Material.iron, true, 1)).setHardness(0.5F).setBlockName("ss.gold_plate").setBlockTextureName(ID + ":plate/gold_plate")
                .setCreativeTab(SextiarySectorAPI.TabSSMining);
        GameRegistry.registerBlock(goldPlate, "GoldPlate");

        copperPlate = (new BlockSSPane(ID + ":ore/copper_block", ID + ":ore/copper_block_top", Material.iron, true, 1)).setHardness(0.5F).setBlockName("ss.copper_plate").setBlockTextureName(ID + ":plate/copper_plate")
                .setCreativeTab(SextiarySectorAPI.TabSSMining);
        GameRegistry.registerBlock(copperPlate, "CopperPlate");
        zincPlate = (new BlockSSPane(ID + ":ore/zinc_block", ID + ":ore/zinc_block_top", Material.iron, true, 1)).setHardness(0.5F).setBlockName("ss.zinc_plate").setBlockTextureName(ID + ":plate/zinc_plate")
                .setCreativeTab(SextiarySectorAPI.TabSSMining);
        GameRegistry.registerBlock(zincPlate, "ZincPlate");
        silverPlate = (new BlockSSPane(ID + ":ore/silver_block", ID + ":ore/silver_block_top", Material.iron, true, 1)).setHardness(0.5F).setBlockName("ss.silver_plate").setBlockTextureName(ID + ":plate/silver_plate")
                .setCreativeTab(SextiarySectorAPI.TabSSMining);
        GameRegistry.registerBlock(silverPlate, "SilverPlate");

        chunkLoader = new BlockChunkLoader().setHardness(1.5F).setBlockName("ss.chunk_loader").setBlockTextureName("sextiarysector:time_loader");
        GameRegistry.registerBlock(chunkLoader, "ChunkLoader");

        blueFire = new BlockBlueFire().setBlockName("ss.blue_fire").setBlockTextureName("sextiarysector:blue_fire");
        GameRegistry.registerBlock(blueFire, "BlueFire");

        figure = new BlockFigure().setBlockName("ss.figure").setBlockTextureName("sextiarysector:figure").setCreativeTab(SextiarySectorAPI.TabSSCore);
        GameRegistry.registerBlock(figure, ItemBlockFigure.class, "Figure");
        GameRegistry.registerTileEntity(TileEntityFigure.class, "SSFigure");

        leafBlock = new BlockLeaf().setBlockName("ss.leaf_block").setBlockTextureName("sextiarysector:leaf_bed_2").setCreativeTab(SextiarySectorAPI.TabSSCore);
        GameRegistry.registerBlock(leafBlock, "LeafBlock");

        trap = new BlockTrap().setBlockName("ss.trap").setBlockTextureName("sextiarysector:wood_grate").setCreativeTab(SextiarySectorAPI.TabSSCore);
        GameRegistry.registerBlock(trap, ItemBlockMeta.class, "Trap");
        GameRegistry.registerTileEntity(TileEntityTrap.class, "SSTrap");

        knife = new BlockKnife().setBlockName("ss.knife").setBlockTextureName("sextiarysector:break");//.setCreativeTab(SextiarySectorAPI.TabSSCore);
        GameRegistry.registerBlock(knife, "Knife");
        GameRegistry.registerTileEntity(TileEntityKnife.class, "SSKnife");

        motor = new BlockMotor(false).setBlockName("ss.motor").setBlockTextureName("sextiarysector:motor").setCreativeTab(SextiarySectorAPI.TabSSCore);
        GameRegistry.registerBlock(motor, "Motor");

        stickyMotor = new BlockMotor(true).setBlockName("ss.sticky_motor").setBlockTextureName("sextiarysector:motor").setCreativeTab(SextiarySectorAPI.TabSSCore);
        GameRegistry.registerBlock(stickyMotor, "StickyMotor");

        kawara = new BlockKawara().setBlockName("ss.kawara").setBlockTextureName("sextiarysector:kawara");//.setCreativeTab(SextiarySectorAPI.TabSSCore);
        GameRegistry.registerBlock(kawara, "KawaraBlock");

        animalOil = new BlockOil().setBlockName("ss.animal_oil").setBlockTextureName("sextiarysector:animal_oil").setCreativeTab(SextiarySectorAPI.TabSSCooking);
        GameRegistry.registerBlock(animalOil, "AnimalOilBlock");

        leafBed = new BlockLeafBed().setBlockName("ss.leaf_bed").setBlockTextureName("sextiarysector:leaf_bed");//.setCreativeTab(SextiarySectorAPI.TabSSCore);
        GameRegistry.registerBlock(leafBed, "LeafBed");

        //液体
        drinkingWater = new BlockSSFluid(SSFluids.springWater).setBlockName("ss.drinking_water");//.setCreativeTab(SextiarySectorAPI.TabSSCore);
        GameRegistry.registerBlock(drinkingWater, ItemBlockSSFluid.class, "DrinkingWater");

        hotSprings = new BlockHotSprings(SSFluids.hotSprings).setBlockName("ss.hot_springs");//.setCreativeTab(SextiarySectorAPI.TabSSCore);
        GameRegistry.registerBlock(hotSprings, ItemBlockSSFluid.class, "HotSprings");

        //season = new BlockSeasonFluid(SSFluids.season).setBlockName("ss.season").setCreativeTab(SextiarySectorAPI.TabSSCore);
        //GameRegistry.registerBlock(season, "Season");

        //鉱石
        blueStoneOre = new BlockPowerStone(2).setBlockName("ss.blue_stone").setBlockTextureName("sextiarysector:ore/bluestone_ore").setCreativeTab(SextiarySectorAPI.TabSSMining);
        GameRegistry.registerBlock(blueStoneOre, "BlueStoneOre");

        yellowStoneOre = new BlockPowerStone(2).setBlockName("ss.yellow_stone").setBlockTextureName("sextiarysector:ore/yellowstone_ore").setCreativeTab(SextiarySectorAPI.TabSSMining);
        GameRegistry.registerBlock(yellowStoneOre, "YellowStoneOre");

        copperOre = new BlockSSOre(null, 1).setBlockName("ss.copper_ore").setBlockTextureName("sextiarysector:ore/copper_ore");
        GameRegistry.registerBlock(copperOre, "CopperOre");

        zincOre = new BlockSSOre(null, 1).setBlockName("ss.zinc_ore").setBlockTextureName("sextiarysector:ore/zinc_ore");
        GameRegistry.registerBlock(zincOre, "ZincOre");

        silverOre = new BlockSSOre(null, 2).setBlockName("ss.silver_ore").setBlockTextureName("sextiarysector:ore/silver_ore");
        GameRegistry.registerBlock(silverOre, "SilverOre");

        mithrilOre = new BlockSSOre(null, 2).setBlockName("ss.mithril_ore").setBlockTextureName("sextiarysector:ore/mithril_ore");
        GameRegistry.registerBlock(mithrilOre, "MithrilOre");

        orichalcumOre = new BlockSSOre(SSItems.orichalcumGem, 3).setBlockName("ss.orichalcum_ore").setBlockTextureName("sextiarysector:ore/orichalcum_ore");
        GameRegistry.registerBlock(orichalcumOre, "OrichalcumOre");

        coalLargeOre = new BlockLargeOre(SSItems.coalDust, Blocks.coal_ore, 1).setBlockName("ss.coal_large_ore").setBlockTextureName("sextiarysector:ore/coal_large_ore");
        GameRegistry.registerBlock(coalLargeOre, "CoalLargeOre");

        ironLargeOre = new BlockLargeOre(SSItems.ironDust, Blocks.iron_ore, 2).setBlockName("ss.iron_large_ore").setBlockTextureName("sextiarysector:ore/iron_large_ore");
        GameRegistry.registerBlock(ironLargeOre, "IronLargeOre");

        goldLargeOre = new BlockLargeOre(SSItems.goldDust, Blocks.gold_ore, 2).setBlockName("ss.gold_large_ore").setBlockTextureName("sextiarysector:ore/gold_large_ore");
        GameRegistry.registerBlock(goldLargeOre, "GoldLargeOre");

        //鉱石ブロック
        copperBlock = new BlockOreBlock().setBlockName("ss.copper_block").setBlockTextureName("sextiarysector:ore/copper_block");
        GameRegistry.registerBlock(copperBlock, "CopperBlock");

        zincBlock = new BlockOreBlock().setBlockName("ss.zinc_block").setBlockTextureName("sextiarysector:ore/zinc_block");
        GameRegistry.registerBlock(zincBlock, "ZincBlock");

        silverBlock = new BlockOreBlock().setBlockName("ss.silver_block").setBlockTextureName("sextiarysector:ore/silver_block");
        GameRegistry.registerBlock(silverBlock, "SilverBlock");

        steelBlock = new BlockOreBlock().setBlockName("ss.steel_block").setBlockTextureName("sextiarysector:ore/steel_block");
        GameRegistry.registerBlock(steelBlock, "SteelBlock");

        brassBlock = new BlockOreBlock().setBlockName("ss.brass_block").setBlockTextureName("sextiarysector:ore/brass_block");
        GameRegistry.registerBlock(brassBlock, "BrassBlock");

        ninjaBlock = new BlockOreBlock().setBlockName("ss.ninja_block").setBlockTextureName("sextiarysector:ore/ninja_block");
        GameRegistry.registerBlock(ninjaBlock, "NinjaBlock");

        mithrilBlock = new BlockOreBlock().setBlockName("ss.mithril_block").setBlockTextureName("sextiarysector:ore/mithril_block");
        GameRegistry.registerBlock(mithrilBlock, "MithrilBlock");

        orichalcumBlock = new BlockOreBlock().setBlockName("ss.orichalcum_block").setBlockTextureName("sextiarysector:ore/orichalcum_block");
        GameRegistry.registerBlock(orichalcumBlock, "OrichalcumBlock");

        //large
        silverLargeOre = new BlockLargeOre(SSItems.silverDust, SSBlocks.silverOre, 2).setBlockName("ss.silver_large_ore").setBlockTextureName("sextiarysector:ore/silver_large_ore");
        GameRegistry.registerBlock(silverLargeOre, "SilverLargeOre");

        //GF
        GameRegistry.registerTileEntity(TileEntityShaft.class, "Shaft");

        woodShaft = new BlockShaft(1).setBlockName("ss.wood_shaft").setBlockTextureName("planks_oak").setStepSound(Block.soundTypeWood);
        GameRegistry.registerBlock(woodShaft, ItemBlockDirection.class, "WoodShaft");

        stoneShaft = new BlockShaft(2).setBlockName("ss.stone_shaft").setBlockTextureName("planks_oak").setStepSound(Block.soundTypeStone);
        GameRegistry.registerBlock(stoneShaft, ItemBlockDirection.class, "StoneShaft");

        steelShaft = new BlockShaft(3).setBlockName("ss.steel_shaft").setBlockTextureName("planks_oak").setStepSound(Block.soundTypeMetal);
        GameRegistry.registerBlock(steelShaft, ItemBlockDirection.class, "SteelShaft");

        ninjaShaft = new BlockShaft(4).setBlockName("ss.ninja_shaft").setBlockTextureName("planks_oak").setStepSound(Block.soundTypeMetal);
        GameRegistry.registerBlock(ninjaShaft, ItemBlockDirection.class, "NinjaShaft");

        orichalcumShaft = new BlockShaft(5).setBlockName("ss.orichalcum_shaft").setBlockTextureName("planks_oak").setStepSound(Block.soundTypeMetal);
        GameRegistry.registerBlock(orichalcumShaft, ItemBlockDirection.class, "OrichalcumShaft");

        GameRegistry.registerTileEntity(TileEntityGearBox.class, "GearBox");
        woodGearBox = new BlockGearBox(Material.wood, 1).setBlockName("ss.wood_gear_box").setBlockTextureName("sextiarysector:machine/wood_gear_box").setStepSound(Block.soundTypeWood);
        GameRegistry.registerBlock(woodGearBox, "WoodGearBox");

        stoneGearBox = new BlockGearBox(Material.rock, 2).setBlockName("ss.stone_gear_box").setBlockTextureName("sextiarysector:machine/stone_gear_box").setStepSound(Block.soundTypeStone);
        GameRegistry.registerBlock(stoneGearBox, "StoneGearBox");

        steelGearBox = new BlockGearBox(Material.iron, 3).setBlockName("ss.steel_gear_box").setBlockTextureName("sextiarysector:machine/steel_gear_box").setStepSound(Block.soundTypeMetal);
        GameRegistry.registerBlock(steelGearBox, "SteelGearBox");

        ninjaGearBox = new BlockGearBox(Material.iron, 4).setBlockName("ss.ninja_gear_box").setBlockTextureName("sextiarysector:machine/ninja_gear_box").setStepSound(Block.soundTypeMetal);
        GameRegistry.registerBlock(ninjaGearBox, "NinjaGearBox");

        orichalcumGearBox = new BlockGearBox(Material.iron, 5).setBlockName("ss.orichalcum_gear_box").setBlockTextureName("sextiarysector:machine/orichalcum_gear_box").setStepSound(Block.soundTypeMetal);
        GameRegistry.registerBlock(orichalcumGearBox, "OrichalcumGearBox");

        GameRegistry.registerTileEntity(TileEntityGFTank.class, "GFTank");
        woodGFTank = new BlockGFTank(Material.wood, 50, 1).setBlockName("ss.wood_gf_tank").setBlockTextureName("sextiarysector:machine/wood_gf_tank").setStepSound(Block.soundTypeWood);
        GameRegistry.registerBlock(woodGFTank, "WoodGFTank");

        stoneGFTank = new BlockGFTank(Material.rock, 51, 2).setBlockName("ss.stone_gf_tank").setBlockTextureName("sextiarysector:machine/stone_gf_tank").setStepSound(Block.soundTypeWood);
        GameRegistry.registerBlock(stoneGFTank, "StoneGFTank");

        steelGFTank = new BlockGFTank(Material.iron, 52, 3).setBlockName("ss.steel_gf_tank").setBlockTextureName("sextiarysector:machine/steel_gf_tank").setStepSound(Block.soundTypeMetal);
        GameRegistry.registerBlock(steelGFTank, "SteelGFTank");

        ninjaGFTank = new BlockGFTank(Material.iron, 53, 4).setBlockName("ss.ninja_gf_tank").setBlockTextureName("sextiarysector:machine/ninja_gf_tank").setStepSound(Block.soundTypeMetal);
        GameRegistry.registerBlock(ninjaGFTank, "NinjaGFTank");

        orichalcumGFTank = new BlockGFTank(Material.iron, 54, 5).setBlockName("ss.orichalcum_gf_tank").setBlockTextureName("sextiarysector:machine/orichalcum_gf_tank").setStepSound(Block.soundTypeMetal);
        GameRegistry.registerBlock(orichalcumGFTank, "OrichalcumGFTank");

        GameRegistry.registerTileEntity(TileEntityGearShaft.class, "GearShaft");
        woodStoneGearShaft = new BlockGearShaft(1).setBlockName("ss.wood_stone_gear_shaft").setBlockTextureName("planks_oak").setStepSound(Block.soundTypeWood);
        GameRegistry.registerBlock(woodStoneGearShaft, ItemBlockGearShaft.class, "WoodStoneGearShaft");

        stoneSteelGearShaft = new BlockGearShaft(2).setBlockName("ss.stone_steel_gear_shaft").setBlockTextureName("planks_oak").setStepSound(Block.soundTypeWood);
        GameRegistry.registerBlock(stoneSteelGearShaft, ItemBlockGearShaft.class, "StoneSteelGearShaft");

        steelNinjaGearShaft = new BlockGearShaft(3).setBlockName("ss.steel_ninja_gear_shaft").setBlockTextureName("planks_oak").setStepSound(Block.soundTypeWood);
        GameRegistry.registerBlock(steelNinjaGearShaft, ItemBlockGearShaft.class, "SteelNinjaGearShaft");

        ninjaOrichalcumGearShaft = new BlockGearShaft(4).setBlockName("ss.ninja_orichalcum_gear_shaft").setBlockTextureName("planks_oak").setStepSound(Block.soundTypeWood);
        GameRegistry.registerBlock(ninjaOrichalcumGearShaft, ItemBlockGearShaft.class, "NinjaOrichalcumGearShaft");

        //発電
        smallWindmill = new BlockSmallWindmill().setBlockName("ss.small_windmill").setBlockTextureName("planks_oak").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
        GameRegistry.registerTileEntity(TileEntitySmallWindmill.class, "SmallWindmill");
        GameRegistry.registerBlock(smallWindmill, "SmallWindmill");

        windmill = new BlockWindmill().setBlockName("ss.windmill").setBlockTextureName("planks_oak").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
        GameRegistry.registerTileEntity(TileEntityWindmill.class, "Windmill");
        GameRegistry.registerBlock(windmill, "Windmill");

        largeWindmill = new BlockLargeWindmill().setBlockName("ss.large_windmill").setBlockTextureName("planks_oak").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
        GameRegistry.registerTileEntity(TileEntityLargeWindmill.class, "LargeWindmill");
        GameRegistry.registerBlock(largeWindmill, "LargeWindmill");

        smallWaterwheel = new BlockSmallWaterwheel().setBlockName("ss.small_waterwheel").setBlockTextureName("planks_oak").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
        GameRegistry.registerTileEntity(TileEntitySmallWaterwheel.class, "SmallWaterwheel");
        GameRegistry.registerBlock(smallWaterwheel, "SmallWaterwheel");

        steamMotor = new BlockSteamMotor().setBlockName("ss.steam_motor").setBlockTextureName("planks_oak").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
        GameRegistry.registerTileEntity(TileEntitySteamMotor.class, "SteamMotor");
        GameRegistry.registerBlock(steamMotor, ItemBlockDirection.class, "SteamMotor");

        //機械
        //machineFrame = new BlockMachineFrame().setBlockName("ss.machine_frame").setBlockTextureName("sextiarysector:machine/machine").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
        //GameRegistry.registerBlock(machineFrame, "MachineFrame");

        GameRegistry.registerTileEntity(TileEntitySimpleMachine.class, "SimpleMachine");
        GameRegistry.registerTileEntity(TileEntityFluidFGFMachineBase.class, "FluidFGFMachine");

        millstone = new BlockSimpleMachine("millstone", 20, SSRecipes.millstone, 1).setBlockName("ss.millstone").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
        GameRegistry.registerBlock(millstone, "Millstone");

        loom = new BlockSimpleMachine("loom", 21, SSRecipes.loom, 1).setBlockName("ss.loom").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
        GameRegistry.registerBlock(loom, "Loom");

        sawmill = new BlockSimpleMachine("sawmill", 25, SSRecipes.sawmill, 2).setBlockName("ss.sawmill").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
        GameRegistry.registerBlock(sawmill, "Sawmill");

        spinningMachine = new BlockSimpleMachine("spinning_machine", 26, SSRecipes.spinning_machine, 2).setBlockName("ss.spinning_machine").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
        GameRegistry.registerBlock(spinningMachine, "SpinningMachine");

        pump = new BlockPump().setBlockTextureName("sextiarysector:machine/pump").setBlockName("ss.pump");
        GameRegistry.registerTileEntity(TileEntityPump.class, "SSPump");
        GameRegistry.registerBlock(pump, "pump");

        suctionMachine = new BlockSuctionMachine().setBlockTextureName("sextiarysector:machine/pump").setBlockName("ss.suction_machine");
        GameRegistry.registerTileEntity(TileEntitySuctionMachine.class, "SSSuctionMachine");
        GameRegistry.registerBlock(suctionMachine, "SuctionMachine");

        pulverizer = new BlockSimpleMachine("pulverizer", 30, SSRecipes.pulverizer, 3).setBlockName("ss.pulverizer").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
        GameRegistry.registerBlock(pulverizer, "Pulverizer");

        extractor = new BlockFluidFGFMachine("extractor", 31, SSRecipes.extractor, 3).setBlockName("ss.extractor").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
        GameRegistry.registerBlock(extractor, "Extractor");

        fan = new BlockFan().setBlockName("ss.fan").setBlockTextureName("stone");
        GameRegistry.registerTileEntity(TileEntityFan.class, "Fan");
        GameRegistry.registerBlock(fan, ItemBlockDirection.class, "Fan");

        saw = new BlockSaw().setBlockName("ss.saw").setBlockTextureName("stone");
        GameRegistry.registerTileEntity(TileEntitySaw.class, "Saw");
        GameRegistry.registerBlock(saw, ItemBlockDirection.class, "Saw");

        rollingMachine = new BlockSimpleMachine("rolling_machine", 35, SSRecipes.rollingMachine, 4).setBlockName("ss.rolling_machine").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
        GameRegistry.registerBlock(rollingMachine, "RollingMachine");

        manaSqueezer = new BlockFluidFGFMachine("mana_squeezer", 36, SSRecipes.manaSqueezer, 4).setBlockName("ss.mana_squeezer").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
        GameRegistry.registerBlock(manaSqueezer, "ManaSqueezer");

        timeMachine = new BlockSimpleMachine("time_machine", 40, SSRecipes.timeMachine, 5).setBlockName("ss.time_machine").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
        GameRegistry.registerBlock(timeMachine, "TimeMachine");

        //経済
        shippingBox = new BlockShippingBox().setBlockName("ss.shipping_box").setBlockTextureName("sextiarysector:shipping_box").setCreativeTab(SextiarySectorAPI.TabSSEconomy);
        GameRegistry.registerBlock(shippingBox, "ShippingBox");
        GameRegistry.registerTileEntity(TileEntityShippingBox.class, "ShippingBox");

        creeperChest = new BlockSSChest(6).setBlockName("ss.creeper_chest").setHardness(2.5F).setStepSound(Block.soundTypeWood).setCreativeTab(SextiarySectorAPI.TabSSEconomy);
        GameRegistry.registerBlock(creeperChest, "CreeperChest");
        GameRegistry.registerTileEntity(TileEntitySSChest.class, "SSChest");

        monitor = new BlockMonitor().setBlockName("ss.monitor").setBlockTextureName("stone");
        GameRegistry.registerBlock(monitor, ItemBlockMonitor.class, "Monitor");
        GameRegistry.registerTileEntity(TileEntityMonitor.class, "SSMonitor");

        shopMonitor = new BlockShopMonitor().setBlockName("ss.monitor").setBlockTextureName("stone");
        GameRegistry.registerBlock(shopMonitor, "ShopMonitor");
        GameRegistry.registerTileEntity(TileEntityShopMonitor.class, "ShopMonitor");

        //農業
        farmland = new BlockFarmland().setBlockName("ss.farmland");
        GameRegistry.registerBlock(farmland, "Farmland");
        GameRegistry.registerTileEntity(TileEntityFarmland.class, "SextiarySector:Farmland");

        paddy = new BlockPaddy().setBlockName("ss.paddy");
        GameRegistry.registerBlock(paddy, "Paddy");
        GameRegistry.registerTileEntity(TileEntityPaddy.class, "SextiarySector:Paddy");

        wood = new BlockWood().setBlockName("ss.wood").setBlockTextureName("sextiarysector:wood");
        GameRegistry.registerBlock(wood, "Wood");
        //GameRegistry.registerTileEntity(TileEntityPaddy.class, "SextiarySector:Paddy");

        crop = new BlockCrop();
        GameRegistry.registerBlock(crop, "Crop");
        GameRegistry.registerTileEntity(TileEntityCrop.class, "SextiarySector:Crop");

        //農業機械
        waterSupplyMachine = new BlockWaterSupplyMachine().setBlockName("ss.water_supply_machine").setBlockTextureName("sextiarysector:machine/water_supply_machine").setCreativeTab(SextiarySectorAPI.TabSSAgriculture);
        GameRegistry.registerBlock(waterSupplyMachine, "WaterSupplyMachine");
        GameRegistry.registerTileEntity(TileEntityWaterSupplyMachine.class, "WaterSupplyMachine");

        //水産
        sandpit = new BlockSandpit().setBlockName("ss.sandpit").setBlockTextureName("sand");
        GameRegistry.registerBlock(sandpit, "Sandpit");

        //輸送
        boardingBuoy = new BlockBoardingBuoy().setBlockName("ss.boarding_buoy").setBlockTextureName("sextiarysector:buoy/boarding_buoy");
        GameRegistry.registerBlock(boardingBuoy, ItemBlockBuoyBase.class, "BoardingBuoy");

        poweredBuoy = new BlockPoweredBuoy().setBlockName("ss.powered_buoy").setBlockTextureName("sextiarysector:buoy/powered_buoy");
        GameRegistry.registerBlock(poweredBuoy, ItemBlockBuoyBase.class, "PoweredBuoy");

        detectorBuoy = new BlockDetectorBuoy().setBlockName("ss.detector_buoy").setBlockTextureName("sextiarysector:buoy/detector_buoy");
        GameRegistry.registerBlock(detectorBuoy, ItemBlockBuoyBase.class, "DetectorBuoy");

        //initVanillaBlock();

    }
}
