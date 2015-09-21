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
    public static boolean modIC2;
    public static boolean modTofu;
    public static boolean modTcon;
    public static boolean modCleaver;
    public static boolean modFMP;
    public static boolean modRF;
    public static boolean modTC;

    public static void initModHelper() {

        modDCsAppleMilk = Loader.isModLoaded("DCsAppleMilk") && Config.modDCsAppleMilk;
        modComputerCraft = Loader.isModLoaded("ComputerCraft") && Config.modComputerCraft;
        modTHKaguya = Loader.isModLoaded("THKaguyaMod") && Config.modTHKaguya;
        modIC2 = Loader.isModLoaded("IC2") && Config.modIC2;
        modTofu = Loader.isModLoaded("TofuCraft") && Config.modTofu;
        modTcon = Loader.isModLoaded("TConstruct") && Config.modTcon;
        modCleaver = Loader.isModLoaded("schr0.cleaver") && Config.modCleaver;
        modFMP = Loader.isModLoaded("ForgeMultipart") && Config.modFMP;
        modRF = isRF() && Config.modRF;
        modTC = Loader.isModLoaded("Thaumcraft") && Config.modTC;

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

        if (modIC2) {

            try {

                SextiarySector.Log.info("IC2 Plugin is loaded");
                plugins.add(new PluginIC2());

            } catch (Exception e) {

                SextiarySector.Log.log(Level.WARN, "IC2 integration was unsuccessful - please contact the author of this mod to let them know that the API may have changed.");
                SextiarySector.Log.catching(e);

            }
        }

        if (modTofu) {

            try {

                SextiarySector.Log.info("TofuCraft Plugin is loaded");
                plugins.add(new PluginTofu());

            } catch (Exception e) {

                SextiarySector.Log.log(Level.WARN, "TofuCraft integration was unsuccessful - please contact the author of this mod to let them know that the API may have changed.");
                SextiarySector.Log.catching(e);

            }
        }

        if (modTcon) {

            try {

                SextiarySector.Log.info("TConstruct Plugin is loaded");
                plugins.add(new PluginTcon());

            } catch (Exception e) {

                SextiarySector.Log.log(Level.WARN, "TConstruct integration was unsuccessful - please contact the author of this mod to let them know that the API may have changed.");
                SextiarySector.Log.catching(e);

            }
        }

        if (modCleaver) {

            try {

                SextiarySector.Log.info("Cleaver Plugin is loaded");
                plugins.add(new PluginCleaver());

            } catch (Exception e) {

                SextiarySector.Log.log(Level.WARN, "Cleaver integration was unsuccessful - please contact the author of this mod to let them know that the API may have changed.");
                SextiarySector.Log.catching(e);

            }
        }

        if (modFMP) {

            try {

                SextiarySector.Log.info("ForgeMultipart Plugin is loaded");
                plugins.add(new PluginFMP());

            } catch (Exception e) {

                SextiarySector.Log.log(Level.WARN, "ForgeMultipart integration was unsuccessful - please contact the author of this mod to let them know that the API may have changed.");
                SextiarySector.Log.catching(e);

            }
        }

        if (modRF) {

            try {

                SextiarySector.Log.info("RF Plugin is loaded");
                plugins.add(new PluginRF());

            } catch (Exception e) {

                SextiarySector.Log.log(Level.WARN, "RF integration was unsuccessful - please contact the author of this mod to let them know that the API may have changed.");
                SextiarySector.Log.catching(e);

            }
        }

        if (modTC) {

            try {

                SextiarySector.Log.info("Thaumcraft Plugin is loaded");
                plugins.add(new PluginTC());

            } catch (Exception e) {

                SextiarySector.Log.log(Level.WARN, "Thaumcraft integration was unsuccessful - please contact the author of this mod to let them know that the API may have changed.");
                SextiarySector.Log.catching(e);

            }
        }

    }

    public static void prePlugins(FMLPreInitializationEvent event) {

        for (IPlugin p : plugins) {

            SextiarySector.Log.info(p.getModName() + " Plugin is pre init");
            try {

                p.prePlugin(event);
                SextiarySector.Log.info(p.getModName() + " Plugin was successful pre initialization");

            } catch (Exception e) {

                SextiarySector.Log.log(Level.WARN, p.getModName() + " integration was unsuccessful - please contact the author of this mod to let them know that the API may have changed.");
                SextiarySector.Log.catching(e);

            }
        }

    }

    public static void initPlugins(FMLInitializationEvent event) {

        for (IPlugin p : plugins) {
            SextiarySector.Log.info(p.getModName() + " Plugin is init");
            try {

                p.initPlugin(event);
                SextiarySector.Log.info(p.getModName() + " Plugin was successful initialization");

            } catch (Exception e) {

                SextiarySector.Log.log(Level.WARN, p.getModName() + " integration was unsuccessful - please contact the author of this mod to let them know that the API may have changed.");
                SextiarySector.Log.catching(e);

            }
        }

    }

    public static void postPlugins(FMLPostInitializationEvent event) {

        for (IPlugin p : plugins) {
            SextiarySector.Log.info(p.getModName() + " Plugin is post init");
            try {

                p.postPlugin(event);
                SextiarySector.Log.info(p.getModName() + " Plugin was successful post initialization");

            } catch (Exception e) {

                SextiarySector.Log.log(Level.WARN, p.getModName() + " integration was unsuccessful - please contact the author of this mod to let them know that the API may have changed.");
                SextiarySector.Log.catching(e);

            }
        }

    }

    private static boolean isRF() {

        Class<?> clazz;
        try {
            clazz = Class.forName("cofh.api.energy.IEnergyHandler");
        } catch (ClassNotFoundException e) {
            return false;
        } catch (Exception e) {
            return false;
        }

        return true;

    }

}
