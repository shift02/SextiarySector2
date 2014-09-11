package shift.sextiarysector;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.common.MinecraftForge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import shift.mceconomy2.gui.HUDMP;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.api.event.VanillaFoodHandler;
import shift.sextiarysector.api.machine.item.GearForceItem;
import shift.sextiarysector.event.ClientEventHandler;
import shift.sextiarysector.event.CommonEventHandler;
import shift.sextiarysector.event.HUDEventHandler;
import shift.sextiarysector.event.PlayerStatusEventHandler;
import shift.sextiarysector.item.GearForceItemManager;
import shift.sextiarysector.module.IModule;
import shift.sextiarysector.module.ModuleAchievement;
import shift.sextiarysector.packet.PacketHandler;
import shift.sextiarysector.player.EntityPlayerManager;
import shift.sextiarysector.proxy.CommonProxy;
import shift.sextiarysector.recipe.FurnaceCraftingManager;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = SextiarySectorAPI.MODID, version = SextiarySector.VERSION)
public class SextiarySector {

	//public static final String MODID = "SextiarySector";
    public static final String VERSION = "1.0.0";

    @Mod.Instance("SextiarySector")
    public static SextiarySector instance;

    @SidedProxy(clientSide = "shift.sextiarysector.proxy.ClientProxy", serverSide = "shift.sextiarysector.proxy.CommonProxy")
	public static CommonProxy proxy;

    public static final Logger Log = LogManager.getLogger(SextiarySectorAPI.MODID);

    public static final ArrayList<IModule> modules = new ArrayList<IModule>();

    @Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{

    	Log.info("PreInit");

    	PacketHandler.init(event);

    	SextiarySector.proxy.setCustomRenderers();

    	MinecraftForge.EVENT_BUS.register(new CommonEventHandler());
    	MinecraftForge.EVENT_BUS.register(EntityPlayerManager.instance);
    	FMLCommonHandler.instance().bus().register(EntityPlayerManager.instance);
    	if(event.getSide().isClient())MinecraftForge.EVENT_BUS.register(new HUDEventHandler());
    	MinecraftForge.EVENT_BUS.register(new PlayerStatusEventHandler());
    	MinecraftForge.EVENT_BUS.register(new VanillaFoodHandler());
    	MinecraftForge.EVENT_BUS.register(new ClientEventHandler());

		GearForceItem.manager = new GearForceItemManager();

    	//Module
    	modules.add(ModuleAchievement.getInstance());

    	for(IModule m : modules){
    		m.preInit(event);
    	}

    	SSRecipes.initRecipeLists();

    	SextiarySectorAPI.playerManager = EntityPlayerManager.instance;

    	SSCreativeTabs.initCreativeTabs();
    	SSBlocks.initBlicks();
    	SSItems.initItems();

    	NetworkRegistry.INSTANCE.registerGuiHandler(this, new SSGuiHandler());

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

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {


    	for( Map.Entry<ItemStack, ItemStack> e : ((HashMap<ItemStack,ItemStack>)FurnaceRecipes.smelting().getSmeltingList()).entrySet()){

    		FurnaceCraftingManager.getInstance().addShapelessRecipe(e.getValue(), new Object[]{e.getKey()});

    	}

    	for(IModule m : modules){
    		m.postInit(event);
    	}

    }



}
