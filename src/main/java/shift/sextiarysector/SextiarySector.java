package shift.sextiarysector;



import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import shift.mceconomy2.gui.HUDMP;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.api.machine.item.GearForceItem;
import shift.sextiarysector.item.GearForceItemManager;
import shift.sextiarysector.module.IModule;
import shift.sextiarysector.module.ModuleAchievement;
import shift.sextiarysector.module.ModuleAgriculture;
import shift.sextiarysector.module.ModuleChunkLoader;
import shift.sextiarysector.module.ModuleSeason;
import shift.sextiarysector.packet.PacketHandler;
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

@Mod(modid = SextiarySectorAPI.MODID, version = SextiarySector.VERSION, dependencies = SextiarySector.DEPENDENCY)
public class SextiarySector {

	//public static final String MODID = "SextiarySector";
    public static final String VERSION = "2.0.6";

    @Mod.Instance("SextiarySector")
    public static SextiarySector instance;

    public static final String DEPENDENCY = "";//"required-after:mceconomy2";

    @SidedProxy(clientSide = "shift.sextiarysector.proxy.ClientProxy", serverSide = "shift.sextiarysector.proxy.CommonProxy")
	public static CommonProxy proxy;

    public static final Logger Log = LogManager.getLogger(SextiarySectorAPI.MODID);

    public static final ArrayList<IModule> modules = new ArrayList<IModule>();

    @Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{

    	Log.info("PreInit");

    	Config.ConfigRead(event);

    	PacketHandler.init(event);

    	SextiarySector.proxy.setCustomRenderers();

    	SSEvents.preInit(event);

		GearForceItem.manager = new GearForceItemManager();

    	//Module
    	modules.add(ModuleAchievement.getInstance());
    	modules.add(ModuleChunkLoader.getInstance());
    	modules.add(ModuleSeason.getInstance());
    	modules.add(ModuleAgriculture.getInstance());

    	SSRecipes.initRecipeLists();

    	SextiarySectorAPI.playerManager = EntityPlayerManager.instance;

    	SSCreativeTabs.initCreativeTabs();
    	SSFluids.initFluids();
    	SSItems.initItems();
    	SSBlocks.initBlicks();

    	NetworkRegistry.INSTANCE.registerGuiHandler(this, new SSGuiHandler());

    	SSOreDictionary.init();

    	for(IModule m : modules){
    		m.preInit(event);
    	}

	}

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {


    	if(event.getSide().isClient()){
			HUDMP.left_height += 10;
		}

    	SSRecipes.initRecipes();


    	for(IModule m : modules){
    		m.load(event);
    	}

    	SSVillages.initVillages();

    	SSPlugins.initModHelper();

    	SSPlugins.initPlugins(event);

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {

    	RecipesFurnaceCraft.addVanillaRecipes();
    	SSFluids.postFluids();
    	SSShops.initShops();
    	SSShops.initPurchase();

    	for(IModule m : modules){
    		m.postInit(event);
    	}

    	SSPlugins.postPlugins(event);

    }



}
