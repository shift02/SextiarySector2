package shift.sextiarysector;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import shift.mceconomy2.gui.HUDMP;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.api.machine.item.GearForceItem;
import shift.sextiarysector.item.GearForceItemManager;
import shift.sextiarysector.module.IModule;
import shift.sextiarysector.module.ModuleAgriculture;
import shift.sextiarysector.module.ModuleChest;
import shift.sextiarysector.module.ModuleChunkLoader;
import shift.sextiarysector.module.ModuleFigure;
import shift.sextiarysector.module.ModuleHotSprings;
import shift.sextiarysector.module.ModuleSandpit;
import shift.sextiarysector.module.ModuleSeason;
import shift.sextiarysector.module.ModuleStatistics;
import shift.sextiarysector.module.ModuleToolMaterial;
import shift.sextiarysector.module.ModuleTrap;
import shift.sextiarysector.packet.SSPacketHandler;
import shift.sextiarysector.player.EntityPlayerManager;
import shift.sextiarysector.plugin.SSPlugins;
import shift.sextiarysector.proxy.CommonProxy;
import shift.sextiarysector.recipe.RecipesFurnaceCraft;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = SextiarySector.MODID, version = SextiarySector.VERSION, dependencies = SextiarySector.DEPENDENCY)
public class SextiarySector {

	public static final String MODID = "SextiarySector";
	public static final String VERSION = "2.1.8";

	@Mod.Instance("SextiarySector")
	public static SextiarySector instance;

	public static final String DEPENDENCY = "";//"required-after:mceconomy2";

	@SidedProxy(modId = MODID, clientSide = "shift.sextiarysector.proxy.ClientProxy", serverSide = "shift.sextiarysector.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static final Logger Log = LogManager.getLogger(SextiarySectorAPI.MODID);

	public static final ArrayList<IModule> modules = new ArrayList<IModule>();

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{

		Log.info("PreInit");

		Config.ConfigRead(event);

		SSPacketHandler.init(event);

		SextiarySector.proxy.setCustomRenderers();

		SSEvents.preInit(event);

		GearForceItem.manager = new GearForceItemManager();

		//Module
		modules.add(ModuleStatistics.getInstance());
		modules.add(ModuleChest.getInstance());
		modules.add(ModuleChunkLoader.getInstance());
		modules.add(ModuleSeason.getInstance());
		modules.add(ModuleAgriculture.getInstance());
		modules.add(ModuleTrap.getInstance());
		modules.add(ModuleSandpit.getInstance());
		modules.add(ModuleHotSprings.getInstance());
		modules.add(ModuleFigure.getInstance());
		modules.add(ModuleToolMaterial.getInstance());

		for (IModule m : modules) {
			m.preInit(event);
		}

		SSRecipes.deleteVanillaRecipe();
		SSRecipes.initRecipeLists();

		SextiarySectorAPI.playerManager = EntityPlayerManager.instance;

		SSCreativeTabs.initCreativeTabs();
		SSFluids.initFluids();
		SSPotions.initPotions();
		SSMaterials.preInitMaterial();
		SSItems.initItems();
		SSBlocks.initBlicks();
		SSEntitys.initEntity();

		NetworkRegistry.INSTANCE.registerGuiHandler(this, new SSGuiHandler());

		SSOreDictionary.init();

		/*
		GameRegistry.registerTileEntity(TileEmptyCauldron.class, "SSCauldron");
		Iterable<Class<?>> cc = new ArrayList();
		((ArrayList) cc).add(ITileEntityProvider.class);
		ExtendedClassSupport.loadAndGenerateNewExtendedClass(BlockCauldron.class, Block.class, BlockSSCauldron.class, ITileEntityProvider.class, cc);
		*/
		SSPlugins.initModHelper();

		proxy.setPluginCustomRenderers(event);

		SSPlugins.prePlugins(event);

		SSAchievement.initAchievements();

	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{

		SSWorld.init(event);

		if (event.getSide().isClient()) {
			HUDMP.left_height += 10;
		}

		SSRecipes.initRecipes();

		for (IModule m : modules) {
			m.load(event);
		}

		SSVillages.initVillages();

		if (event.getSide().isClient()) SSPlayerTabs.initRecipes();

		//SSPlugins.initModHelper();

		SSPlugins.initPlugins(event);

	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{

		SextiarySector.proxy.registerInventoryTabs();
		RecipesFurnaceCraft.addVanillaRecipes();
		SSFluids.postFluids();
		SSShops.initShops();
		SSShops.initPurchase();

		for (IModule m : modules) {
			m.postInit(event);
		}

		SSPlugins.postPlugins(event);

	}

}
