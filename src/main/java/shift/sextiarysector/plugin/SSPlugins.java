package shift.sextiarysector.plugin;

import org.apache.logging.log4j.Level;

import shift.sextiarysector.Config;
import shift.sextiarysector.SextiarySector;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;

public class SSPlugins {

	public static boolean modDCsAppleMilk;
	public static boolean modComputerCraft;
	public static boolean modTHKaguya;

	public static void initModHelper() {

		modDCsAppleMilk = Loader.isModLoaded("DCsAppleMilk");
		modComputerCraft = Loader.isModLoaded("ComputerCraft");
		modTHKaguya = Loader.isModLoaded("THKaguyaMod");

	}

	public static void initPlugins(FMLInitializationEvent event) {

		if (modDCsAppleMilk && Config.modDCsAppleMilk) {

			try {

				SextiarySector.Log.info("DCsAppleMilk Plugin is loaded");

			} catch (Exception e) {

				SextiarySector.Log.log(Level.WARN, "DCsAppleMilk integration was unsuccessful - please contact the author of this mod to let them know that the API may have changed.");
				SextiarySector.Log.catching(e);

			}
		}

		if (modComputerCraft && Config.modComputerCraft) {

			try {

				PluginComputerCraft.initPlugin();
				SextiarySector.Log.info("ComputerCraft Plugin is loaded");

			} catch (Exception e) {

				SextiarySector.Log.log(Level.WARN, "ComputerCraft integration was unsuccessful - please contact the author of this mod to let them know that the API may have changed.");
				SextiarySector.Log.catching(e);

			}
		}

		System.out.println("AAAAAA"+modTHKaguya+" : "+Config.modTHKaguya);
		if (modTHKaguya && Config.modTHKaguya) {

			try {

				PluginTHKaguya.initPlugin();
				SextiarySector.Log.info("THKaguya Plugin is loaded");

			} catch (Exception e) {

				SextiarySector.Log.log(Level.WARN, "THKaguya integration was unsuccessful - please contact the author of this mod to let them know that the API may have changed.");
				SextiarySector.Log.catching(e);

			}
		}

	}

	public static void postPlugins(FMLPostInitializationEvent event) {

	}

}
