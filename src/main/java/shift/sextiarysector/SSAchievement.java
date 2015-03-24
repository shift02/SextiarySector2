package shift.sextiarysector;

import java.util.ArrayList;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.oredict.OreDictionary;
import shift.sextiarysector.achievement.AchievementBase;
import shift.sextiarysector.achievement.AchievementCraft;
import shift.sextiarysector.achievement.AchievementCrop;
import shift.sextiarysector.achievement.AchievementFurnace;
import shift.sextiarysector.achievement.AchievementPageAgriculture;
import shift.sextiarysector.achievement.AchievementPageBase;
import shift.sextiarysector.achievement.AchievementPageEconomy;
import shift.sextiarysector.achievement.AchievementPageIndustry;
import shift.sextiarysector.achievement.AchievementPickup;

public class SSAchievement {

	private static ArrayList<Achievement> core = new ArrayList<Achievement>();
	private static ArrayList<Achievement> agriculture = new ArrayList<Achievement>();
	private static ArrayList<Achievement> mining = new ArrayList<Achievement>();
	private static ArrayList<Achievement> industry = new ArrayList<Achievement>();
	private static ArrayList<Achievement> economy = new ArrayList<Achievement>();

	//Core
	public static Achievement moisture;
	public static Achievement bottle;
	public static Achievement drinkingWater;
	public static Achievement craftFurnace;
	public static Achievement fluidFurnace;
	public static Achievement hammer;
	public static Achievement freezer;

	//Agriculture
	public static Achievement seed;
	public static Achievement scoop;
	public static Achievement farmland;
	public static Achievement wateringCan;
	public static Achievement hole;
	public static Achievement paddy;

	public static Achievement turnip;
	public static Achievement ironTurnip;

	public static Achievement cucumber;

	public static Achievement onion;

	public static Achievement tomato;

	public static Achievement corn;
	public static Achievement goldenCorn;

	public static Achievement eggplant;

	public static Achievement sweetPotato;

	public static Achievement greenPepper;

	public static Achievement radish;

	public static Achievement rice;

	public static Achievement shiitake;

	//Mining
	public static Achievement blueStoneDust;
	public static Achievement copperOre;
	public static Achievement silverOre;
	public static Achievement mithrilOre;

	//Industry
	public static Achievement blueStoneSlimeBall;

	//1
	public static Achievement woodGear;
	public static Achievement smallWindmill;
	public static Achievement millstone;
	public static Achievement loom;

	//2
	public static Achievement stoneGear;
	public static Achievement windmill;
	public static Achievement smallWaterwheel;
	public static Achievement sawmill;
	public static Achievement spinningMachine;

	//3
	public static Achievement steelGear;
	public static Achievement steamMotor;
	public static Achievement pulverizer;
	public static Achievement fan;
	public static Achievement saw;

	//4
	public static Achievement ninjaGear;
	public static Achievement rollingMachine;
	public static Achievement manaSqueezer;

	//5
	public static Achievement orichalcumGear;
	public static Achievement timeMachine;

	//Economy
	public static Achievement creeperFirework;
	public static Achievement creeperChest;
	public static Achievement shipping;

	public static void initAchievements() {

		moisture = new AchievementBase("moisture", 3, -1, new ItemStack(Items.water_bucket), (Achievement) null, core).initIndependentStat().registerStat();
		bottle = new AchievementCraft("bottle", 1, 1, new ItemStack(SSItems.emptyBottle), AchievementList.buildWorkBench, core).registerStat();
		drinkingWater = new AchievementFurnace("drinking_water", 3, 1, new ItemStack(SSItems.drinkingWaterBottle), bottle, core).registerStat();
		craftFurnace = new AchievementCraft("craft_furnace", -2, -1, new ItemStack(SSBlocks.LargeFurnace), AchievementList.buildWorkBench, core).registerStat();
		hammer = new AchievementFurnace("hammer", -4, -1, new ItemStack(SSItems.hammer), craftFurnace, core).registerStat();
		fluidFurnace = new AchievementFurnace("fluid_furnace", -2, 1, new ItemStack(SSBlocks.fluidFurnace), craftFurnace, core).registerStat();
		freezer = new AchievementFurnace("freezer", 0, -3, new ItemStack(SSBlocks.freezer), craftFurnace, core).registerStat();

		AchievementPage.registerAchievementPage(new AchievementPageBase("achievement.ss.core", core));

		//農業
		seed = new AchievementBase("seed", -8, -2, new ItemStack(SSBlocks.tomato), (Achievement) null, agriculture).initIndependentStat().registerStat();
		scoop = new AchievementCraft("scoop", -6, -3, new ItemStack(SSItems.woodScoop), AchievementList.buildWorkBench, agriculture).registerStat();
		farmland = new AchievementBase("farmland", -4, -4, new ItemStack(Items.stone_hoe), scoop, agriculture).registerStat();
		wateringCan = new AchievementCraft("watering_can", 1, -2, new ItemStack(SSItems.woodWateringCan, 1, OreDictionary.WILDCARD_VALUE), AchievementList.buildWorkBench, agriculture).registerStat();
		hole = new AchievementBase("hole", -4, -2, new ItemStack(SSItems.ironScoop), scoop, agriculture).registerStat();
		paddy = new AchievementBase("paddy", -2, -2, new ItemStack(Items.water_bucket), hole, agriculture).registerStat();

		int spring = 1;
		turnip = new AchievementCrop("turnip", -7, spring, new ItemStack(SSItems.turnip), SSBlocks.turnip, seed, agriculture).registerStat();
		ironTurnip = new AchievementPickup("iron_turnip", -5, spring - 1, new ItemStack(SSItems.ironTurnip), turnip, agriculture).registerStat();

		cucumber = new AchievementCrop("cucumber", -7, spring + 4, new ItemStack(SSItems.cucumber), SSBlocks.cucumber, seed, agriculture).registerStat();

		int summer = 10;
		onion = new AchievementCrop("onion", -7, summer, new ItemStack(SSItems.onion), SSBlocks.onion, seed, agriculture).registerStat();

		tomato = new AchievementCrop("tomato", -7, summer + 4, new ItemStack(SSItems.tomato), SSBlocks.tomato, seed, agriculture).registerStat();

		corn = new AchievementCrop("corn", -7, summer + 8, new ItemStack(SSItems.corn), SSBlocks.corn, seed, agriculture).registerStat();
		goldenCorn = new AchievementPickup("golden_corn", -5, summer + 7, new ItemStack(SSItems.goldenCorn), corn, agriculture).registerStat();

		int autumn = 23;
		eggplant = new AchievementCrop("eggplant", -7, autumn, new ItemStack(SSItems.eggplant), SSBlocks.eggplant, seed, agriculture).registerStat();

		sweetPotato = new AchievementCrop("sweet_potato", -7, autumn + 4, new ItemStack(SSItems.sweetPotato), SSBlocks.sweetPotato, seed, agriculture).registerStat();

		greenPepper = new AchievementCrop("green_pepper", -7, autumn + 8, new ItemStack(SSItems.greenPepper), SSBlocks.greenPepper, seed, agriculture).registerStat();

		int winter = 36;
		radish = new AchievementCrop("radish", -7, winter, new ItemStack(SSItems.radish), SSBlocks.radish, seed, agriculture).registerStat();

		int spring2 = 41;
		rice = new AchievementCrop("rice", -7, spring2, new ItemStack(SSItems.rice), SSBlocks.rice, seed, agriculture).registerStat();

		int spring3 = 46;
		shiitake = new AchievementCrop("shiitake", -7, spring3, new ItemStack(SSItems.shiitake), SSBlocks.shiitake, seed, agriculture).registerStat();

		AchievementPage.registerAchievementPage(new AchievementPageAgriculture("achievement.ss.agriculture", agriculture));

		//鉱業

		blueStoneDust = new AchievementPickup("bluestone_dust", 0, 0, new ItemStack(SSItems.blueStoneDust), AchievementList.buildPickaxe, mining).registerStat();

		copperOre = new AchievementPickup("copper_ore", 2, 0, new ItemStack(SSBlocks.copperOre), AchievementList.buildPickaxe, mining).registerStat();

		silverOre = new AchievementPickup("silver_ore", 4, 2, new ItemStack(SSBlocks.silverOre), AchievementList.buildPickaxe, mining).registerStat();

		mithrilOre = new AchievementPickup("mithril_ore", -2, -1, new ItemStack(SSBlocks.mithrilOre), AchievementList.buildPickaxe, mining).registerStat();

		AchievementPage.registerAchievementPage(new AchievementPageBase("achievement.ss.mining", mining));

		//工業

		blueStoneSlimeBall = new AchievementFurnace("blue_gel", -4, -1, new ItemStack(SSItems.blueGel), blueStoneDust, industry).registerStat();

		woodGear = new AchievementCraft("wood_gear", -2, 0, new ItemStack(SSItems.woodGear), blueStoneSlimeBall, industry).registerStat();
		smallWindmill = new AchievementCraft("small_windmill", -1, -2, new ItemStack(SSBlocks.smallWindmill), woodGear, industry).registerStat();
		millstone = new AchievementCraft("millstone", -1, 2, new ItemStack(SSBlocks.millstone), woodGear, industry).registerStat();
		loom = new AchievementCraft("loom", -1, 3, new ItemStack(SSBlocks.loom), woodGear, industry).registerStat();

		stoneGear = new AchievementCraft("stone_gear", 1, 0, new ItemStack(SSItems.stoneGear), woodGear, industry).registerStat();
		windmill = new AchievementCraft("windmill", 2, -2, new ItemStack(SSBlocks.windmill), stoneGear, industry).registerStat();
		smallWaterwheel = new AchievementCraft("small_waterwheel", 2, -3, new ItemStack(SSBlocks.smallWaterwheel), stoneGear, industry).registerStat();
		sawmill = new AchievementCraft("sawmill", 2, 2, new ItemStack(SSBlocks.sawmill), stoneGear, industry).registerStat();
		spinningMachine = new AchievementCraft("spinning_machine", 2, 3, new ItemStack(SSBlocks.spinningMachine), stoneGear, industry).registerStat();

		steelGear = new AchievementCraft("steel_gear", 4, 0, new ItemStack(SSItems.steelGear), stoneGear, industry).registerStat();
		steamMotor = new AchievementCraft("steam_motor", 5, -2, new ItemStack(SSBlocks.steamMotor), steelGear, industry).registerStat();
		pulverizer = new AchievementCraft("pulverizer", 5, 2, new ItemStack(SSBlocks.pulverizer), steelGear, industry).registerStat();
		fan = new AchievementCraft("fan", 5, 4, new ItemStack(SSBlocks.fan), steelGear, industry).registerStat();
		saw = new AchievementCraft("saw", 5, 5, new ItemStack(SSBlocks.saw), steelGear, industry).registerStat();

		ninjaGear = new AchievementCraft("ninja_gear", 7, 0, new ItemStack(SSItems.ninjaGear), steelGear, industry).registerStat();
		rollingMachine = new AchievementCraft("rolling_machine", 8, 2, new ItemStack(SSBlocks.rollingMachine), ninjaGear, industry).registerStat();
		manaSqueezer = new AchievementCraft("mana_squeezer", 8, 3, new ItemStack(SSBlocks.manaSqueezer), ninjaGear, industry).registerStat();

		orichalcumGear = new AchievementCraft("orichalcum_gear", 10, 0, new ItemStack(SSItems.orichalcumGear), ninjaGear, industry).registerStat();
		timeMachine = new AchievementCraft("time_machine", 11, 2, new ItemStack(SSBlocks.timeMachine), orichalcumGear, industry).registerStat();

		AchievementPage.registerAchievementPage(new AchievementPageIndustry("achievement.ss.industry", industry));

		//経済
		creeperFirework = new AchievementBase("creeper_firework", 0, 0, new ItemStack(Items.fireworks), (Achievement) null, economy).initIndependentStat().registerStat();
		creeperChest = new AchievementBase("creeper_chest", 1, -2, new ItemStack(SSBlocks.creeperChest), creeperFirework, economy).registerStat();
		shipping = new AchievementBase("shipping", 3, -2, new ItemStack(SSBlocks.shippingBox), creeperChest, economy).registerStat();

		AchievementPage.registerAchievementPage(new AchievementPageEconomy("achievement.ss.economy", economy));

	}

}
