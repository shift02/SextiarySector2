package shift.sextiarysector;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class Config {


	//Mod
	public static boolean modDCsAppleMilk;
	public static boolean modComputerCraft;

	public static void ConfigRead(FMLPreInitializationEvent event) {

		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());

		try {

			cfg.load();

			ConfigForMCEconomy(cfg);

		} catch (Exception e) {
			//FMLLog.log(Level.SEVERE, "", e.getMessage());
		} finally {
			cfg.save();
		}

	}

	public static void ConfigForMCEconomy(Configuration cfg) {

		modDCsAppleMilk = cfg.getBoolean("mod_DCsAppleMilk", "general", true, "");
		modComputerCraft = cfg.getBoolean("ComputerCraft", "general", true, "");

	}

}
