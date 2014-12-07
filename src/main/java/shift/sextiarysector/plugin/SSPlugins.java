package shift.sextiarysector.plugin;

import java.util.ArrayList;

import org.apache.logging.log4j.Level;

import shift.sextiarysector.Config;
import shift.sextiarysector.SextiarySector;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class SSPlugins {

	public static ArrayList<IPlugin> plugins = new ArrayList<IPlugin>();

	public static boolean modDCsAppleMilk;
	public static boolean modComputerCraft;
	public static boolean modTHKaguya;

	public static void initModHelper() {

		modDCsAppleMilk = Loader.isModLoaded("DCsAppleMilk") && Config.modDCsAppleMilk;
		modComputerCraft = Loader.isModLoaded("ComputerCraft") && Config.modComputerCraft;
		modTHKaguya = Loader.isModLoaded("THKaguyaMod")  && Config.modTHKaguya;

		if (modDCsAppleMilk) {

			try {

				SextiarySector.Log.info("DCsAppleMilk Plugin is loaded");
				plugins.add(new PluginAppleMilk());

			} catch (Exception e) {

				SextiarySector.Log.log(Level.WARN, "DCsAppleMilk integration was unsuccessful - please contact the author of this mod to let them know that the API may have changed.");
				SextiarySector.Log.catching(e);

			}
		}

		if (modComputerCraft) {

			try {

				SextiarySector.Log.info("ComputerCraft Plugin is loaded");
				plugins.add(new PluginComputerCraft());

			} catch (Exception e) {

				SextiarySector.Log.log(Level.WARN, "ComputerCraft integration was unsuccessful - please contact the author of this mod to let them know that the API may have changed.");
				SextiarySector.Log.catching(e);

			}
		}

		if (modTHKaguya) {

			try {

				SextiarySector.Log.info("THKaguya Plugin is loaded");
				plugins.add(new PluginTHKaguya());

			} catch (Exception e) {

				SextiarySector.Log.log(Level.WARN, "THKaguya integration was unsuccessful - please contact the author of this mod to let them know that the API may have changed.");
				SextiarySector.Log.catching(e);

			}
		}

	}

	public static void prePlugins(FMLPreInitializationEvent event) {

		for(IPlugin p : plugins)
		{
			try {

				SextiarySector.Log.info(p.getModName()+" Plugin is pre init");
				p.prePlugin(event);

			} catch (Exception e) {

				SextiarySector.Log.log(Level.WARN, p.getModName() +" integration was unsuccessful - please contact the author of this mod to let them know that the API may have changed.");
				SextiarySector.Log.catching(e);

			}
		}

	}

	public static void initPlugins(FMLInitializationEvent event) {

		for(IPlugin p : plugins)
		{
			try {

				SextiarySector.Log.info(p.getModName()+" Plugin is init");
				p.initPlugin(event);

			} catch (Exception e) {

				SextiarySector.Log.log(Level.WARN, p.getModName() +" integration was unsuccessful - please contact the author of this mod to let them know that the API may have changed.");
				SextiarySector.Log.catching(e);

			}
		}

	}

	public static void postPlugins(FMLPostInitializationEvent event) {

		for(IPlugin p : plugins)
		{
			try {

				SextiarySector.Log.info(p.getModName()+" Plugin is post init");
				p.postPlugin(event);

			} catch (Exception e) {

				SextiarySector.Log.log(Level.WARN, p.getModName() +" integration was unsuccessful - please contact the author of this mod to let them know that the API may have changed.");
				SextiarySector.Log.catching(e);

			}
		}

	}

}
