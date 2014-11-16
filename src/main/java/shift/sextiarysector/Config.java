package shift.sextiarysector;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class Config {

	//Biome
	public static int magicDesert;

	//Mod
	public static boolean modDCsAppleMilk;
	public static boolean modComputerCraft;
	public static boolean modTHKaguya;

	public static void ConfigRead(FMLPreInitializationEvent event) {

		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());

		try {

			cfg.load();
			configForBiome(cfg);

			configForPlugin(cfg);

		} catch (Exception e) {
			//FMLLog.log(Level.SEVERE, "", e.getMessage());
		} finally {
			cfg.save();
		}

	}

	public static void configForBiome(Configuration cfg) {
		magicDesert = cfg.getInt("MagicDesertID", "biome", 120, 0, 255, "");
	}

	public static void configForPlugin(Configuration cfg) {

		modDCsAppleMilk = cfg.getBoolean("AppleMilk", "general", true, "");
		modComputerCraft = cfg.getBoolean("ComputerCraft", "general", true, "");
		modTHKaguya = cfg.getBoolean("THKaguya", "general", true, "");

	}

}
