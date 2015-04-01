package shift.sextiarysector;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class Config {

	//Potion
	public static int burn;
	public static int hotSprings;

	//Biome
	public static int magicDesert;

	//World
	public static boolean generationCopperOre;
	public static boolean generationZincOre;
	public static boolean generationSilverOre;

	//Player
	public static boolean peacefulMoisture;
	public static boolean peacefulStamina;

	//Mod
	public static boolean modDCsAppleMilk;
	public static boolean modComputerCraft;
	public static boolean modTHKaguya;
	public static boolean modIC2;
	public static boolean modTofu;
	public static boolean modTcon;
	public static boolean modCleaver;
	public static boolean modFMP;
	public static boolean modRF;

	public static void ConfigRead(FMLPreInitializationEvent event) {

		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());

		try {

			cfg.load();
			configForBiome(cfg);
			configForWorld(cfg);
			configForPotion(cfg);

			configForPlayer(cfg);

			configForPlugin(cfg);

		} catch (Exception e) {
			//FMLLog.log(Level.SEVERE, "", e.getMessage());
		} finally {
			cfg.save();
		}

	}

	public static void configForPotion(Configuration cfg) {
		burn = cfg.getInt("BurnID", "potion", 25, 25, 255, "");
		hotSprings = cfg.getInt("HotSpringsID", "potion", 26, 25, 255, "");
	}

	public static void configForBiome(Configuration cfg) {
		//magicDesert = cfg.getInt("MagicDesertID", "biome", 120, 0, 255, "");
	}

	public static void configForWorld(Configuration cfg) {
		generationCopperOre = cfg.getBoolean("GenerationCopperOre", "world", true, "");
		generationZincOre = cfg.getBoolean("GenerationZincOre", "world", true, "");
		generationSilverOre = cfg.getBoolean("GenerationSilverOre", "world", true, "");
	}

	public static void configForPlayer(Configuration cfg) {
		peacefulMoisture = cfg.getBoolean("PeacefulMoisture", "player", false, "");
		peacefulStamina = cfg.getBoolean("PeacefulStamina", "player", false, "");
	}

	public static void configForPlugin(Configuration cfg) {

		modDCsAppleMilk = cfg.getBoolean("AppleMilk", "general", true, "");
		modComputerCraft = cfg.getBoolean("ComputerCraft", "general", true, "");
		modTHKaguya = cfg.getBoolean("THKaguya", "general", true, "");
		modIC2 = cfg.getBoolean("IC2", "general", true, "");
		modTofu = cfg.getBoolean("Tofu", "general", true, "");
		modTcon = cfg.getBoolean("Tcon", "general", true, "");
		modCleaver = cfg.getBoolean("Cleaver", "general", true, "");
		modFMP = cfg.getBoolean("ForgeMultipart", "general", true, "");
		modRF = cfg.getBoolean("RF", "general", true, "");

	}

}
