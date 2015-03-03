package shift.sextiarysector;

import net.minecraftforge.common.MinecraftForge;
import shift.sextiarysector.api.event.VanillaFoodHandler;
import shift.sextiarysector.event.AchievementEventHandler;
import shift.sextiarysector.event.ClientEventHandler;
import shift.sextiarysector.event.CommonEventHandler;
import shift.sextiarysector.event.HUDEventHandler;
import shift.sextiarysector.event.OreDictionaryEventHandler;
import shift.sextiarysector.event.PlayerStatusEventHandler;
import shift.sextiarysector.event.PlayerUnitEventHandler;
import shift.sextiarysector.event.WorldEventHandler;
import shift.sextiarysector.gui.tab.TabManager;
import shift.sextiarysector.player.EntityPlayerManager;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class SSEvents {

	public static void preInit(FMLPreInitializationEvent event) {

		MinecraftForge.EVENT_BUS.register(new CommonEventHandler());

		MinecraftForge.EVENT_BUS.register(EntityPlayerManager.instance);
		FMLCommonHandler.instance().bus().register(EntityPlayerManager.instance);

		if (event.getSide().isClient()) {
			MinecraftForge.EVENT_BUS.register(new HUDEventHandler());
		}

		MinecraftForge.EVENT_BUS.register(new PlayerStatusEventHandler());
		FMLCommonHandler.instance().bus().register(new PlayerStatusEventHandler());
		MinecraftForge.EVENT_BUS.register(new PlayerUnitEventHandler());
		MinecraftForge.EVENT_BUS.register(new VanillaFoodHandler());

		MinecraftForge.EVENT_BUS.register(new OreDictionaryEventHandler());

		if (event.getSide().isClient()) {
			MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
		}

		if (event.getSide().isClient()) {
			MinecraftForge.EVENT_BUS.register(new TabManager());
		}

		MinecraftForge.EVENT_BUS.register(new WorldEventHandler());
		MinecraftForge.ORE_GEN_BUS.register(new WorldEventHandler());

		MinecraftForge.EVENT_BUS.register(new AchievementEventHandler());

		//MinecraftForge.EVENT_BUS.register(new SantaEventHandler());

	}

}
