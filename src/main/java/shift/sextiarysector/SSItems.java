package shift.sextiarysector;

import java.util.ArrayList;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSword;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.item.ItemBottle;
import shift.sextiarysector.item.ItemCalendar;
import shift.sextiarysector.item.ItemCrop;
import shift.sextiarysector.item.ItemDrink;
import shift.sextiarysector.item.ItemFigureBox;
import shift.sextiarysector.item.ItemFoodCrop;
import shift.sextiarysector.item.ItemFoodDrink;
import shift.sextiarysector.item.ItemFullBottle;
import shift.sextiarysector.item.ItemGFContactLenses;
import shift.sextiarysector.item.ItemGearStorage;
import shift.sextiarysector.item.ItemGuiUnit;
import shift.sextiarysector.item.ItemHammer;
import shift.sextiarysector.item.ItemKnife;
import shift.sextiarysector.item.ItemLavaBottle;
import shift.sextiarysector.item.ItemMineboat;
import shift.sextiarysector.item.ItemMineboatTank;
import shift.sextiarysector.item.ItemRucksack;
import shift.sextiarysector.item.ItemSSArmor;
import shift.sextiarysector.item.ItemSSAxe;
import shift.sextiarysector.item.ItemSSPickaxe;
import shift.sextiarysector.item.ItemScoop;
import shift.sextiarysector.item.ItemSeasonStone;
import shift.sextiarysector.item.ItemShiftHat;
import shift.sextiarysector.item.ItemShopRing;
import shift.sextiarysector.item.ItemSimpleBucket;
import shift.sextiarysector.item.ItemSoup;
import shift.sextiarysector.item.ItemUnit;
import shift.sextiarysector.item.ItemWaterBottle;
import shift.sextiarysector.item.ItemWateringCan;
import shift.sextiarysector.module.ModuleToolMaterial;
import cpw.mods.fml.common.registry.GameRegistry;

public class SSItems {

	//Gear
	public static Item unit;

	public static Item woodGear;
	public static Item stoneGear;
	public static Item steelGear;
	public static Item ninjaGear;
	public static Item orichalcumGear;

	public static Item woodUnitGear;
	public static Item stoneUnitGear;
	public static Item steelUnitGear;
	public static Item ninjaUnitGear;
	public static Item orichalcumUnitGear;

	public static Item woodGFStorage;
	public static Item stoneGFStorage;
	public static Item steelGFStorage;
	public static Item ninjaGFStorage;
	public static Item orichalcumGFStorage;

	public static Item hammer;

	public static Item calendar;
	public static Item seasonStone;

	//素材
	public static Item dustWaterLily;

	public static Item stoneDust;

	public static Item blueStoneDust;
	public static Item yellowStoneDust;

	public static Item coalDust;
	public static Item ironDust;
	public static Item goldDust;
	public static Item diamondDust;

	public static Item copperDust;
	public static Item zincDust;

	public static Item mithrilDust;

	public static Item steelIngot;
	public static Item brassIngot;

	public static Item blueStoneIngot;
	public static Item yellowStoneIngot;

	public static Item copperIngot;
	public static Item zincIngot;
	public static Item silverIngot;

	public static Item mithrilIngot;
	public static Item orichalcumGem;

	public static Item ninjaIngot;

	public static Item redGel;
	public static Item blueGel;
	public static Item yellowGel;

	public static Item energyReactor;
	public static Item objectReactor;

	//液体
	public static Item emptyBottle;
	public static Item waterBottle;
	public static Item lavaBottle;
	public static Item sapBottle;

	public static Item steamBucket;
	public static Item ironFluidBucket;
	public static Item goldFluidBucket;

	//unit
	public static Item craftUnit;

	public static Item attackUnit;
	public static Item defenseUnit;
	public static Item attackRustUnit;
	public static Item defenseRustUnit;

	public static Item pickaxeUnit;

	//ring
	public static Item ironRing;
	public static Item creeperRing;

	//魔法
	public static Item magicDust;

	//布
	public static Item stringBobbin;
	public static Item smallCloth;
	public static Item cloth;
	public static Item canvas;
	public static Item dryingFlesh;
	public static Item fleshBobbin;

	//public static Item bottle;

	public static Item figureBox;

	//道具
	public static Item woodScoop;
	public static Item stoneScoop;
	public static Item ironScoop;
	public static Item goldScoop;
	public static Item diamondScoop;

	public static Item woodKnife;
	public static Item stoneKnife;
	public static Item ironKnife;
	public static Item goldKnife;
	public static Item diamondKnife;

	//道具 バニラ
	public static Item copperShovel;
	public static Item copperPickaxe;
	public static Item copperAxe;
	public static Item copperSword;
	public static Item copperHoe;

	//防具
	public static Item copperHelmet;
	public static Item copperChestplate;
	public static Item copperLeggings;
	public static Item copperBoots;

	public static Item woodWateringCan;

	//水産
	public static Item mineboatChest;
	public static Item mineboatTank;

	public static Item laver;

	//野菜
	public static ArrayList<Item> farmlandCrops = new ArrayList<Item>();
	public static ArrayList<Item> paddyCrops = new ArrayList<Item>();
	public static ArrayList<Item> woodCrops = new ArrayList<Item>();

	public static Item turnip;
	public static Item cucumber;

	public static Item ironTurnip;

	public static Item onion;
	public static Item tomato;
	public static Item corn;

	public static Item goldenCorn;

	public static Item eggplant;
	public static Item sweetPotato;
	public static Item greenPepper;

	public static Item bluePotato;

	public static Item radish;

	public static Item rice;

	public static Item shiitake;

	//料理
	public static Item whiteRice;

	public static Item salt;
	public static Item curryPowder;

	public static Item laverRoasted;

	public static Item chickenSmoked;
	public static Item porkchopSmoked;
	public static Item beefSmoked;

	public static Item riceBall;
	public static Item curryRice;

	public static Item carrotSoup;
	public static Item cornSoup;
	public static Item eggSoup;
	public static Item mushroomStew;
	public static Item onionSoup;
	public static Item enderSoup;
	public static Item tomatoSoup;

	public static Item chocolate;

	//飲み物
	//public static Item drinkingWaterSmallBottle;
	public static Item drinkingWaterBottle;
	public static Item takumiTeaBottle;

	//装備
	public static Item shiftHat;

	public static Item rucksack;
	public static Item gfContactLenses;

	public static void initItems() {

		unit = new Item().setUnlocalizedName("ss.unit").setTextureName("sextiarysector:machine/unit").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
		GameRegistry.registerItem(unit, "Unit");

		woodGear = new Item().setUnlocalizedName("ss.wood_gear").setTextureName("sextiarysector:machine/wood_gear").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
		GameRegistry.registerItem(woodGear, "WoodGear");

		stoneGear = new Item().setUnlocalizedName("ss.stone_gear").setTextureName("sextiarysector:machine/stone_gear").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
		GameRegistry.registerItem(stoneGear, "StoneGear");

		steelGear = new Item().setUnlocalizedName("ss.steel_gear").setTextureName("sextiarysector:machine/steel_gear").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
		GameRegistry.registerItem(steelGear, "SteelGear");

		ninjaGear = new Item().setUnlocalizedName("ss.ninja_gear").setTextureName("sextiarysector:machine/ninja_gear").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
		GameRegistry.registerItem(ninjaGear, "NinjaGear");

		orichalcumGear = new Item().setUnlocalizedName("ss.orichalcum_gear").setTextureName("sextiarysector:machine/orichalcum_gear").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
		GameRegistry.registerItem(orichalcumGear, "OrichalcumGear");

		woodUnitGear = new Item().setUnlocalizedName("ss.wood_unit_gear").setTextureName("sextiarysector:machine/wood_unit_gear").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
		GameRegistry.registerItem(woodUnitGear, "WoodUnitGear");

		stoneUnitGear = new Item().setUnlocalizedName("ss.stone_unit_gear").setTextureName("sextiarysector:machine/stone_unit_gear").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
		GameRegistry.registerItem(stoneUnitGear, "StoneUnitGear");

		steelUnitGear = new Item().setUnlocalizedName("ss.steel_unit_gear").setTextureName("sextiarysector:machine/steel_unit_gear").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
		GameRegistry.registerItem(steelUnitGear, "steelUnitGear");

		ninjaUnitGear = new Item().setUnlocalizedName("ss.ninja_unit_gear").setTextureName("sextiarysector:machine/ninja_unit_gear").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
		GameRegistry.registerItem(ninjaUnitGear, "NinjaUnitGear");

		orichalcumUnitGear = new Item().setUnlocalizedName("ss.orichalcum_unit_gear").setTextureName("sextiarysector:machine/orichalcum_unit_gear").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
		GameRegistry.registerItem(orichalcumUnitGear, "OrichalcumUnitGear");

		woodGFStorage = new ItemGearStorage(1, 10000, 1).setUnlocalizedName("ss.wood_gf_storage").setTextureName("sextiarysector:gearforce/wood_gear_storage").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
		GameRegistry.registerItem(woodGFStorage, "WoodGFStorage");

		stoneGFStorage = new ItemGearStorage(2, 10000, 2).setUnlocalizedName("ss.stone_gf_storage").setTextureName("sextiarysector:gearforce/stone_gear_storage").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
		GameRegistry.registerItem(stoneGFStorage, "StoneGFStorage");

		steelGFStorage = new ItemGearStorage(3, 10000, 3).setUnlocalizedName("ss.steel_gf_storage").setTextureName("sextiarysector:gearforce/steel_gear_storage").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
		GameRegistry.registerItem(steelGFStorage, "SteelGFStorage");

		ninjaGFStorage = new ItemGearStorage(4, 10000, 4).setUnlocalizedName("ss.ninja_gf_storage").setTextureName("sextiarysector:gearforce/ninja_gear_storage").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
		GameRegistry.registerItem(ninjaGFStorage, "NinjaGFStorage");

		orichalcumGFStorage = new ItemGearStorage(5, 10000, 5).setUnlocalizedName("ss.orichalcum_gf_storage").setTextureName("sextiarysector:gearforce/orichalcum_gear_storage").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
		GameRegistry.registerItem(orichalcumGFStorage, "OrichalcumGFStorage");

		hammer = new ItemHammer().setUnlocalizedName("ss.hammer").setTextureName("sextiarysector:machine/hammer").setCreativeTab(SextiarySectorAPI.TabSSIndustry);
		GameRegistry.registerItem(hammer, "Hammer");

		calendar = new ItemCalendar().setUnlocalizedName("ss.calendar").setTextureName("sextiarysector:calendar").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(calendar, "Calendar");

		seasonStone = new ItemSeasonStone().setUnlocalizedName("ss.season_stone").setTextureName("sextiarysector:season_stone").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(seasonStone, "SeasonStone");

		//素材
		dustWaterLily = new Item().setUnlocalizedName("ss.dust_waterlily").setTextureName("sextiarysector:dust/waterlily_dust").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(dustWaterLily, "DustWaterLily");

		stoneDust = new Item().setUnlocalizedName("ss.stone_dust").setTextureName("sextiarysector:dust/stone_dust").setCreativeTab(SextiarySectorAPI.TabSSMining);
		GameRegistry.registerItem(stoneDust, "StoneDust");

		blueStoneDust = new Item().setUnlocalizedName("ss.dust_blue_stone").setTextureName("sextiarysector:dust/bluestone_dust").setCreativeTab(SextiarySectorAPI.TabSSMining);
		GameRegistry.registerItem(blueStoneDust, "DustBlueStone");

		yellowStoneDust = new Item().setUnlocalizedName("ss.dust_yellow_stone").setTextureName("sextiarysector:dust/yellowstone_dust").setCreativeTab(SextiarySectorAPI.TabSSMining);
		GameRegistry.registerItem(yellowStoneDust, "DustYellowStone");

		coalDust = new Item().setUnlocalizedName("ss.coal_dust").setTextureName("sextiarysector:dust/coal_dust").setCreativeTab(SextiarySectorAPI.TabSSMining);
		GameRegistry.registerItem(coalDust, "CoalDust");

		ironDust = new Item().setUnlocalizedName("ss.iron_dust").setTextureName("sextiarysector:dust/iron_dust").setCreativeTab(SextiarySectorAPI.TabSSMining);
		GameRegistry.registerItem(ironDust, "IronDust");

		goldDust = new Item().setUnlocalizedName("ss.gold_dust").setTextureName("sextiarysector:dust/gold_dust").setCreativeTab(SextiarySectorAPI.TabSSMining);
		GameRegistry.registerItem(goldDust, "GoldDust");

		diamondDust = new Item().setUnlocalizedName("ss.diamond_dust").setTextureName("sextiarysector:dust/diamond_dust").setCreativeTab(SextiarySectorAPI.TabSSMining);
		GameRegistry.registerItem(diamondDust, "DiamondDust");

		copperDust = new Item().setUnlocalizedName("ss.copper_dust").setTextureName("sextiarysector:dust/copper_dust").setCreativeTab(SextiarySectorAPI.TabSSMining);
		GameRegistry.registerItem(copperDust, "CopperDust");

		zincDust = new Item().setUnlocalizedName("ss.zinc_dust").setTextureName("sextiarysector:dust/zinc_dust").setCreativeTab(SextiarySectorAPI.TabSSMining);
		GameRegistry.registerItem(zincDust, "ZincDust");

		mithrilDust = new Item().setUnlocalizedName("ss.mithril_dust").setTextureName("sextiarysector:dust/mithril_dust").setCreativeTab(SextiarySectorAPI.TabSSMining);
		GameRegistry.registerItem(mithrilDust, "MithrilDust");

		steelIngot = new Item().setUnlocalizedName("ss.steel_ingot").setTextureName("sextiarysector:ingot/steel_ingot").setCreativeTab(SextiarySectorAPI.TabSSMining);
		GameRegistry.registerItem(steelIngot, "SteelIngot");

		brassIngot = new Item().setUnlocalizedName("ss.brass_ingot").setTextureName("sextiarysector:ingot/brass_ingot").setCreativeTab(SextiarySectorAPI.TabSSMining);
		GameRegistry.registerItem(brassIngot, "BrassIngot");

		blueStoneIngot = new Item().setUnlocalizedName("ss.bluestone_ingot").setTextureName("sextiarysector:ingot/bluestone_ingot").setCreativeTab(SextiarySectorAPI.TabSSMining);
		GameRegistry.registerItem(blueStoneIngot, "BlueStoneIngot");

		yellowStoneIngot = new Item().setUnlocalizedName("ss.yellowstone_ingot").setTextureName("sextiarysector:ingot/yellowstone_ingot").setCreativeTab(SextiarySectorAPI.TabSSMining);
		GameRegistry.registerItem(yellowStoneIngot, "YellowStoneIngot");

		copperIngot = new Item().setUnlocalizedName("ss.copper_ingot").setTextureName("sextiarysector:ingot/copper_ingot").setCreativeTab(SextiarySectorAPI.TabSSMining);
		GameRegistry.registerItem(copperIngot, "CopperIngot");

		zincIngot = new Item().setUnlocalizedName("ss.zinc_ingot").setTextureName("sextiarysector:ingot/zinc_ingot").setCreativeTab(SextiarySectorAPI.TabSSMining);
		GameRegistry.registerItem(zincIngot, "ZincIngot");

		silverIngot = new Item().setUnlocalizedName("ss.silver_ingot").setTextureName("sextiarysector:ingot/silver_ingot").setCreativeTab(SextiarySectorAPI.TabSSMining);
		GameRegistry.registerItem(silverIngot, "SilverIngot");

		mithrilIngot = new Item().setUnlocalizedName("ss.mithril_ingot").setTextureName("sextiarysector:ingot/mithril_ingot").setCreativeTab(SextiarySectorAPI.TabSSMining);
		GameRegistry.registerItem(mithrilIngot, "MithrilIngot");

		orichalcumGem = new Item().setUnlocalizedName("ss.orichalcum_gem").setTextureName("sextiarysector:gem/orichalcum_gem").setCreativeTab(SextiarySectorAPI.TabSSMining);
		GameRegistry.registerItem(orichalcumGem, "OrichalcumGem");

		ninjaIngot = new Item().setUnlocalizedName("ss.ninja_ingot").setTextureName("sextiarysector:ingot/ninja_ingot").setCreativeTab(SextiarySectorAPI.TabSSMining);
		GameRegistry.registerItem(ninjaIngot, "NinjaIngot");

		redGel = new Item().setUnlocalizedName("ss.red_gel").setTextureName("sextiarysector:gel/red_gel").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(redGel, "RedGel");

		blueGel = new Item().setUnlocalizedName("ss.blue_gel").setTextureName("sextiarysector:gel/blue_gel").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(blueGel, "BlueGel");

		yellowGel = new Item().setUnlocalizedName("ss.yellow_gel").setTextureName("sextiarysector:gel/yellow_gel").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(yellowGel, "YellowGel");

		energyReactor = new Item().setUnlocalizedName("ss.energy_reactor").setTextureName("sextiarysector:energy_reactor").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(energyReactor, "EnergyReactor");

		objectReactor = new Item().setUnlocalizedName("ss.object_reactor").setTextureName("sextiarysector:object_reactor").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(objectReactor, "ObjectReactor");

		//液体
		//ボトル
		emptyBottle = new ItemBottle().setUnlocalizedName("ss.empty_bottle").setTextureName("sextiarysector:fluid/empty_bottle");
		GameRegistry.registerItem(emptyBottle, "EmptyBottle");

		waterBottle = new ItemWaterBottle().setUnlocalizedName("ss.water_bottle").setTextureName("sextiarysector:fluid/water_bottle");
		GameRegistry.registerItem(waterBottle, "WaterBottle");

		lavaBottle = new ItemLavaBottle().setUnlocalizedName("ss.lava_bottle").setTextureName("sextiarysector:fluid/lava_bottle");
		GameRegistry.registerItem(lavaBottle, "LavaBottle");

		sapBottle = new ItemFullBottle().setUnlocalizedName("ss.sap_bottle").setTextureName("sextiarysector:fluid/sap_bottle");
		GameRegistry.registerItem(sapBottle, "SapBottle");

		//バケツ
		steamBucket = new ItemSimpleBucket().setUnlocalizedName("ss.steam_bucket").setTextureName("sextiarysector:fluid/steam_bucket");
		GameRegistry.registerItem(steamBucket, "SteamBucket");

		ironFluidBucket = new ItemSimpleBucket().setUnlocalizedName("ss.iron_fluid_bucket").setTextureName("sextiarysector:fluid/iron_fluid_bucket");
		GameRegistry.registerItem(ironFluidBucket, "IronFluidBucket");

		goldFluidBucket = new ItemSimpleBucket().setUnlocalizedName("ss.gold_fluid_bucket").setTextureName("sextiarysector:fluid/gold_fluid_bucket");
		GameRegistry.registerItem(goldFluidBucket, "GoldFluidBucket");

		//Unit
		craftUnit = new ItemGuiUnit(201).setUnlocalizedName("ss.craft_unit").setTextureName("sextiarysector:unit/craft_unit").setCreativeTab(SextiarySectorAPI.TabSSPlayer);
		GameRegistry.registerItem(craftUnit, "CraftUnit");

		attackUnit = new ItemUnit().setUnlocalizedName("ss.attack_unit").setTextureName("sextiarysector:unit/attack_unit").setCreativeTab(SextiarySectorAPI.TabSSPlayer);
		GameRegistry.registerItem(attackUnit, "AttackUnit");

		defenseUnit = new ItemUnit().setUnlocalizedName("ss.defense_unit").setTextureName("sextiarysector:unit/defense_unit").setCreativeTab(SextiarySectorAPI.TabSSPlayer);
		GameRegistry.registerItem(defenseUnit, "DefenseUnit");

		attackRustUnit = new ItemUnit().setUnlocalizedName("ss.attack_rust_unit").setTextureName("sextiarysector:unit/attack_rust_unit").setCreativeTab(SextiarySectorAPI.TabSSPlayer);
		GameRegistry.registerItem(attackRustUnit, "AttackRustUnit");

		defenseRustUnit = new ItemUnit().setUnlocalizedName("ss.defense_rust_unit").setTextureName("sextiarysector:unit/defense_rust_unit").setCreativeTab(SextiarySectorAPI.TabSSPlayer);
		GameRegistry.registerItem(defenseRustUnit, "DefenseRustUnit");

		pickaxeUnit = new ItemUnit().setUnlocalizedName("ss.pickaxe_unit").setTextureName("sextiarysector:unit/pickaxe_unit").setCreativeTab(SextiarySectorAPI.TabSSPlayer);
		GameRegistry.registerItem(pickaxeUnit, "PickaxeUnit");

		//Ring
		ironRing = new Item().setUnlocalizedName("ss.iron_ring").setTextureName("sextiarysector:ring/iron_ring").setCreativeTab(SextiarySectorAPI.TabSSPlayer);
		GameRegistry.registerItem(ironRing, "IronRing");

		creeperRing = new ItemShopRing().setUnlocalizedName("ss.creeper_ring").setTextureName("sextiarysector:ring/creeper_ring");
		GameRegistry.registerItem(creeperRing, "CreeperRing");

		//魔法
		magicDust = new Item().setUnlocalizedName("ss.magic_dust").setTextureName("sextiarysector:dust/magic_dust").setCreativeTab(SextiarySectorAPI.TabSSMagic);
		GameRegistry.registerItem(magicDust, "MagicDust");

		//布
		stringBobbin = new Item().setUnlocalizedName("ss.string_bobbin").setTextureName("sextiarysector:loom/string_bobbin").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(stringBobbin, "StringBobbin");

		smallCloth = new Item().setUnlocalizedName("ss.small_cloth").setTextureName("sextiarysector:loom/small_cloth").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(smallCloth, "SmallCloth");

		cloth = new Item().setUnlocalizedName("ss.cloth").setTextureName("sextiarysector:loom/cloth").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(cloth, "Cloth");

		canvas = new Item().setUnlocalizedName("ss.canvas").setTextureName("sextiarysector:loom/canvas").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(canvas, "Canvas");

		dryingFlesh = new Item().setUnlocalizedName("ss.drying_flesh").setTextureName("sextiarysector:loom/drying_flesh").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(dryingFlesh, "DryingFlesh");

		fleshBobbin = new Item().setUnlocalizedName("ss.flesh_bobbin").setTextureName("sextiarysector:loom/flesh_bobbin").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(fleshBobbin, "FleshBobbin");

		//bottle  = new ItemBlockBottle().setUnlocalizedName("ss.bottle").setTextureName("sextiarysector:drink/empty_bottle");
		//GameRegistry.registerItem(bottle, "Bottle");

		figureBox = new ItemFigureBox().setUnlocalizedName("ss.figure_box").setTextureName("sextiarysector:figure_box").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(figureBox, "FigureBox");

		//道具
		woodScoop = new ItemScoop(ToolMaterial.WOOD).setUnlocalizedName("ss.wood_scoop").setTextureName("wood_scoop");
		stoneScoop = new ItemScoop(ToolMaterial.STONE).setUnlocalizedName("ss.stone_scoop").setTextureName("stone_scoop");
		ironScoop = new ItemScoop(ToolMaterial.IRON).setUnlocalizedName("ss.iron_scoop").setTextureName("iron_scoop");
		goldScoop = new ItemScoop(ToolMaterial.GOLD).setUnlocalizedName("ss.gold_scoop").setTextureName("gold_scoop");
		diamondScoop = new ItemScoop(ToolMaterial.EMERALD).setUnlocalizedName("ss.diamond_scoop").setTextureName("diamond_scoop");
		GameRegistry.registerItem(woodScoop, "WoodScoop");
		GameRegistry.registerItem(stoneScoop, "StoneScoop");
		GameRegistry.registerItem(ironScoop, "IronScoop");
		GameRegistry.registerItem(goldScoop, "GoldScoop");
		GameRegistry.registerItem(diamondScoop, "DiamondScoop");

		woodKnife = new ItemKnife(ToolMaterial.WOOD).setUnlocalizedName("ss.wood_knife").setTextureName("wood_knife");
		stoneKnife = new ItemKnife(ToolMaterial.STONE).setUnlocalizedName("ss.stone_knife").setTextureName("stone_knife");
		ironKnife = new ItemKnife(ToolMaterial.IRON).setUnlocalizedName("ss.iron_knife").setTextureName("iron_knife");
		goldKnife = new ItemKnife(ToolMaterial.GOLD).setUnlocalizedName("ss.gold_knife").setTextureName("gold_knife");
		diamondKnife = new ItemKnife(ToolMaterial.EMERALD).setUnlocalizedName("ss.diamond_knife").setTextureName("diamond_knife");
		GameRegistry.registerItem(woodKnife, "WoodKnife");
		GameRegistry.registerItem(stoneKnife, "StoneKnife");
		GameRegistry.registerItem(ironKnife, "IronKnife");
		GameRegistry.registerItem(goldKnife, "GoldKnife");
		GameRegistry.registerItem(diamondKnife, "DiamondKnife");

		//バニラ
		copperShovel = new ItemSpade(ModuleToolMaterial.copperTool).setUnlocalizedName("ss.copper_shovel").setTextureName("sextiarysector:tool/copper_shovel").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(copperShovel, "CopperShovel");
		copperPickaxe = new ItemSSPickaxe(ModuleToolMaterial.copperTool).setUnlocalizedName("ss.copper_pickaxe").setTextureName("sextiarysector:tool/copper_pickaxe").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(copperPickaxe, "CopperPickaxe");
		copperAxe = new ItemSSAxe(ModuleToolMaterial.copperTool).setUnlocalizedName("ss.copper_axe").setTextureName("sextiarysector:tool/copper_axe").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(copperAxe, "CopperAxe");
		copperSword = new ItemSword(ModuleToolMaterial.copperTool).setUnlocalizedName("ss.copper_sword").setTextureName("sextiarysector:tool/copper_sword").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(copperSword, "CopperSword");
		copperHoe = new ItemHoe(ModuleToolMaterial.copperTool).setUnlocalizedName("ss.copper_hoe").setTextureName("sextiarysector:tool/copper_hoe").setCreativeTab(SextiarySectorAPI.TabSSCore);
		GameRegistry.registerItem(copperHoe, "CopperHoe");
		//Item i = Items.iron_axe;

		//防具
		copperHelmet = new ItemSSArmor(ModuleToolMaterial.copperArmor, 0, 0).setUnlocalizedName("ss.copper_helmet").setTextureName("sextiarysector:armor/copper_helmet");
		GameRegistry.registerItem(copperHelmet, "CopperHelmet");

		copperChestplate = new ItemSSArmor(ModuleToolMaterial.copperArmor, 0, 1).setUnlocalizedName("ss.copper_chestplate").setTextureName("sextiarysector:armor/copper_chestplate");
		GameRegistry.registerItem(copperChestplate, "CopperChestplate");

		copperLeggings = new ItemSSArmor(ModuleToolMaterial.copperArmor, 0, 2).setUnlocalizedName("ss.copper_leggings").setTextureName("sextiarysector:armor/copper_leggings");
		GameRegistry.registerItem(copperLeggings, "CopperLeggings");

		copperBoots = new ItemSSArmor(ModuleToolMaterial.copperArmor, 0, 3).setUnlocalizedName("ss.copper_boots").setTextureName("sextiarysector:armor/copper_boots");
		GameRegistry.registerItem(copperBoots, "CopperBoots");

		//水やり
		woodWateringCan = new ItemWateringCan(ToolMaterial.WOOD).setUnlocalizedName("ss.wood_watering_can").setTextureName("wood_watering_can");
		GameRegistry.registerItem(woodWateringCan, "WoodWateringCan");

		//水産
		mineboatChest = new ItemMineboat().setUnlocalizedName("ss.mineboat_chest").setTextureName("sextiarysector:mineboat_chest");
		GameRegistry.registerItem(mineboatChest, "MineboatChest");

		mineboatTank = new ItemMineboatTank().setUnlocalizedName("ss.mineboat_tank").setTextureName("sextiarysector:mineboat_tank");
		GameRegistry.registerItem(mineboatTank, "MineboatTank");

		laver = new Item().setUnlocalizedName("ss.laver").setTextureName("sextiarysector:food/fish/laver").setCreativeTab(SextiarySectorAPI.TabSSFishery);
		GameRegistry.registerItem(laver, "Laver");

		//野菜
		turnip = new ItemFoodCrop(farmlandCrops, 3, 1, 1, 4, 0, 0, false).setUnlocalizedName("ss.turnip").setTextureName("sextiarysector:food/vegetable/turnip");
		GameRegistry.registerItem(turnip, "Turnip");

		cucumber = new ItemFoodCrop(farmlandCrops, 1, 1, 3, 4, 0, 2, false).setUnlocalizedName("ss.cucumber").setTextureName("sextiarysector:food/vegetable/cucumber");
		GameRegistry.registerItem(cucumber, "Cucumber");

		ironTurnip = new ItemFoodCrop(farmlandCrops, 0, 1, 0, 0, 0, 0, false).setUnlocalizedName("ss.iron_turnip").setTextureName("sextiarysector:food/vegetable/iron_turnip");
		GameRegistry.registerItem(ironTurnip, "IronTurnip");

		onion = new ItemFoodCrop(farmlandCrops, 2, 1, 1, 0, 0, 0, false).setUnlocalizedName("ss.onion").setTextureName("sextiarysector:food/vegetable/onion");
		GameRegistry.registerItem(onion, "Onion");

		tomato = new ItemFoodCrop(farmlandCrops, 1, 1, 4, 5, 0, 0, false).setUnlocalizedName("ss.tomato").setTextureName("sextiarysector:food/vegetable/tomato");
		GameRegistry.registerItem(tomato, "Tomato");

		corn = new ItemFoodCrop(farmlandCrops, 0, 1, 1, 6, 4, 2, false).setUnlocalizedName("ss.corn").setTextureName("sextiarysector:food/vegetable/corn");
		GameRegistry.registerItem(corn, "corn");

		goldenCorn = new ItemFoodCrop(farmlandCrops, 0, 2, 0, 0, 0, 0, false).setUnlocalizedName("ss.golden_corn").setTextureName("sextiarysector:food/vegetable/golden_corn");
		GameRegistry.registerItem(goldenCorn, "GoldCorn");

		eggplant = new ItemFoodCrop(farmlandCrops, 1, 1, 4, 2, 0, 0, false).setUnlocalizedName("ss.eggplant").setTextureName("sextiarysector:food/vegetable/eggplant");
		GameRegistry.registerItem(eggplant, "Eggplant");

		sweetPotato = new ItemFoodCrop(farmlandCrops, 4, 1, 0, 0, 6, 0, false).setUnlocalizedName("ss.sweet_potato").setTextureName("sextiarysector:food/vegetable/sweet_potato");
		GameRegistry.registerItem(sweetPotato, "SweetPotato");

		greenPepper = new ItemFoodCrop(farmlandCrops, 2, 1, 2, 1, 0, 0, false).setUnlocalizedName("ss.green_pepper").setTextureName("sextiarysector:food/vegetable/green_pepper");
		GameRegistry.registerItem(greenPepper, "GreenPepper");

		bluePotato = new ItemFoodCrop(paddyCrops, 1, 0, 5, 8, 2, 0, false).setUnlocalizedName("ss.blue_potato").setTextureName("sextiarysector:food/vegetable/blue_potato");
		GameRegistry.registerItem(bluePotato, "BluePotato");

		radish = new ItemFoodCrop(farmlandCrops, 3, 1, 2, 1, 0, 0, false).setUnlocalizedName("ss.radish").setTextureName("sextiarysector:food/vegetable/radish");
		GameRegistry.registerItem(radish, "Radish");

		rice = new ItemCrop(paddyCrops).setUnlocalizedName("ss.rice").setTextureName("sextiarysector:food/grain/rice").setCreativeTab(SextiarySectorAPI.TabSSAgriculture);
		GameRegistry.registerItem(rice, "Rice");

		shiitake = new ItemFoodCrop(woodCrops, 2, 1, 0, 0, 2, 0, false).setUnlocalizedName("ss.shiitake").setTextureName("sextiarysector:food/mushroom/shiitake");
		GameRegistry.registerItem(shiitake, "Shiitake");

		//料理
		whiteRice = new Item().setUnlocalizedName("ss.white_rice").setTextureName("sextiarysector:food/grain/white_rice").setCreativeTab(SextiarySectorAPI.TabSSCooking);
		GameRegistry.registerItem(whiteRice, "WhiteRice");

		salt = new Item().setUnlocalizedName("ss.salt").setTextureName("sextiarysector:food/condiment/salt").setCreativeTab(SextiarySectorAPI.TabSSCooking);
		GameRegistry.registerItem(salt, "Salt");

		curryPowder = new Item().setUnlocalizedName("ss.curry_powder").setTextureName("sextiarysector:food/condiment/curry_powder").setCreativeTab(SextiarySectorAPI.TabSSCooking);
		GameRegistry.registerItem(curryPowder, "CurryPowder");

		laverRoasted = new ItemFoodDrink(3, 1.2f, 0, 0, 2, 0, false).setUnlocalizedName("ss.laver_roasted").setTextureName("sextiarysector:food/fish/laver_roasted").setCreativeTab(SextiarySectorAPI.TabSSCooking);
		GameRegistry.registerItem(laverRoasted, "LaverRoasted");

		chickenSmoked = new ItemFoodDrink(2, 4.6f, 0, 0, 4, 2.0f, false).setUnlocalizedName("ss.chicken_smoked").setTextureName("sextiarysector:food/meat/chicken_smoked").setCreativeTab(SextiarySectorAPI.TabSSCooking);
		GameRegistry.registerItem(chickenSmoked, "ChickenSmoked");

		porkchopSmoked = new ItemFoodDrink(3, 7.6f, 0, 0, 4, 2.0f, false).setUnlocalizedName("ss.porkchop_smoked").setTextureName("sextiarysector:food/meat/porkchop_smoked").setCreativeTab(SextiarySectorAPI.TabSSCooking);
		GameRegistry.registerItem(porkchopSmoked, "PorkchopSmoked");

		beefSmoked = new ItemFoodDrink(3, 7.6f, 0, 0, 4, 2.0f, false).setUnlocalizedName("ss.beef_smoked").setTextureName("sextiarysector:food/meat/beef_smoked").setCreativeTab(SextiarySectorAPI.TabSSCooking);
		GameRegistry.registerItem(beefSmoked, "BeefSmoked");

		riceBall = new ItemFoodDrink(4, 0.6f, 0, 0, 4, 6.0f, false).setUnlocalizedName("ss.rice_ball").setTextureName("sextiarysector:food/rice/rice_ball").setCreativeTab(SextiarySectorAPI.TabSSCooking);
		GameRegistry.registerItem(riceBall, "RiceBall");

		curryRice = new ItemFoodDrink(9, 12.6f, 0, 0, 15, 2.0f, false).setUnlocalizedName("ss.curry_rice").setTextureName("sextiarysector:food/rice/curry_rice").setContainerItem(Items.bowl).setCreativeTab(SextiarySectorAPI.TabSSCooking);
		GameRegistry.registerItem(curryRice, "CurryRice");

		carrotSoup = new ItemSoup(2, 0.6f, 4, 1, 0, 0.0f, false).setUnlocalizedName("ss.carrot_soup").setTextureName("sextiarysector:food/soup/carrot_soup");
		GameRegistry.registerItem(carrotSoup, "CarrotSoup");

		cornSoup = new ItemSoup(1, 0.6f, 5, 1, 0, 0.0f, false).setUnlocalizedName("ss.corn_soup").setTextureName("sextiarysector:food/soup/corn_soup");
		GameRegistry.registerItem(cornSoup, "CornSoup");

		eggSoup = new ItemSoup(3, 0.6f, 4, 2, 0, 0.0f, false).setUnlocalizedName("ss.egg_soup").setTextureName("sextiarysector:food/soup/egg_soup");
		GameRegistry.registerItem(eggSoup, "EggSoup");

		onionSoup = new ItemSoup(1, 0.6f, 5, 1, 0, 0.0f, false).setUnlocalizedName("ss.onion_soup").setTextureName("sextiarysector:food/soup/onion_soup");
		GameRegistry.registerItem(onionSoup, "OnionSoup");

		enderSoup = new ItemSoup(0, 0.6f, 4, 1, 20, 0.0f, false).setUnlocalizedName("ss.ender_soup").setTextureName("sextiarysector:food/soup/ender_soup");
		GameRegistry.registerItem(enderSoup, "EnderSoup");

		tomatoSoup = new ItemSoup(1, 0.6f, 3, 4.0f, 0, 0.0f, false).setUnlocalizedName("ss.tomato_soup").setTextureName("sextiarysector:food/soup/tomato_soup");
		GameRegistry.registerItem(tomatoSoup, "TomatoSoup");

		chocolate = new ItemFoodDrink(2, 1.2f, 0, 0, 6, 4.0f, false).setUnlocalizedName("ss.chocolate").setTextureName("sextiarysector:food/dessert/chocolate").setCreativeTab(SextiarySectorAPI.TabSSCooking);
		GameRegistry.registerItem(chocolate, "Chocolate");

		//飲み物
		drinkingWaterBottle = new ItemDrink(0, 2.5f, 3, 6.5f, 0, 0, false).setUnlocalizedName("ss.drinking_water_bottle").setTextureName("sextiarysector:fluid/drinking_water_bottle");
		GameRegistry.registerItem(drinkingWaterBottle, "DrinkingWaterBottle");

		takumiTeaBottle = new ItemDrink(0, 0.0f, 6, 9.5f, 0, 0, false).setUnlocalizedName("ss.takumi_tea_bottle").setTextureName("sextiarysector:fluid/takumi_tea_bottle");
		GameRegistry.registerItem(takumiTeaBottle, "TAKUMITeaBottle");

		//装備
		shiftHat = new ItemShiftHat().setUnlocalizedName("ss.shift_hat").setTextureName("sextiarysector:shift_hat");
		GameRegistry.registerItem(shiftHat, "ShiftHat");

		rucksack = new ItemRucksack().setUnlocalizedName("ss.rucksack").setTextureName("sextiarysector:rucksack");
		GameRegistry.registerItem(rucksack, "Rucksack");

		gfContactLenses = new ItemGFContactLenses().setUnlocalizedName("ss.gf_contact_lenses").setTextureName("sextiarysector:face/gf_contact_lenses");
		GameRegistry.registerItem(gfContactLenses, "GFContactLenses");

	}

}
