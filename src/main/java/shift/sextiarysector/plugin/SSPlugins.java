package shift.sextiarysector.plugin;

import org.apache.logging.log4j.Level;

import shift.sextiarysector.Config;
import shift.sextiarysector.SextiarySector;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;

public class SSPlugins {

	public static boolean modDCsAppleMilk;

	public static void initModHelper() {

		modDCsAppleMilk = Loader.isModLoaded("DCsAppleMilk");

	}

	public static void initPlugins(FMLPostInitializationEvent event) {

		//modCoFHCore = Loader.isModLoaded("BuildCraft|Core");

		//event

		//System.out.println("initPlugins"+modCoFHCore);

		if (modDCsAppleMilk && Config.modDCsAppleMilk) {

			try {

				PluginAppleMilk.registeDCsAppleMilk(event);
				SextiarySector.Log.info("DCsAppleMilk is loaded");

			} catch (Exception e) {

				SextiarySector.Log.log(Level.WARN, "DCsAppleMilk integration was unsuccessful - please contact the author of this mod to let them know that the API may have changed.");

				//System.out.println("SextiarySector: CoFHCore integration was unsuccessful - please contact the author of this mod to let them know that the API may have changed.");
			}
		}

	}

}
