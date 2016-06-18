package shift.sextiarysector.plugin;

import java.util.ArrayList;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import shift.sextiarysector.SextiarySector;

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
    public static boolean modMaid;

    public static void initModHelper() {

        modDCsAppleMilk = Loader.isModLoaded("DCsAppleMilk") && modDCsAppleMilk;
        modComputerCraft = Loader.isModLoaded("ComputerCraft") && modComputerCraft;
        modTHKaguya = Loader.isModLoaded("THKaguyaMod") && modTHKaguya;
        modIC2 = Loader.isModLoaded("IC2") && modIC2;
        modTofu = Loader.isModLoaded("TofuCraft") && modTofu;
        modTcon = isTconTab() && modTcon;//Loader.isModLoaded("TConstruct") && modTcon;
        modCleaver = Loader.isModLoaded("schr0.cleaver") && modCleaver;
        modFMP = Loader.isModLoaded("ForgeMultipart") && modFMP;
        modRF = isRF() && modRF;
        modTC = Loader.isModLoaded("Thaumcraft") && modTC;
        modMaid = Loader.isModLoaded("lmmx") && modMaid;

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
                plugins.add(new PluginTconTab());

            } catch (Exception e) {

                SextiarySector.Log.log(Level.WARN, "TConstruct integration was unsuccessful - please contact the author of this mod to let them know that the API may have changed.");
                SextiarySector.Log.catching(e);

            }
        }

        //すちゃー
        if (modCleaver) {

            try {

                SextiarySector.Log.info("Cleaver Plugin is loaded");
                plugins.add(new PluginCleaver());

            } catch (Exception e) {

                SextiarySector.Log.log(Level.WARN, "Cleaver integration was unsuccessful - please contact the author of this mod to let them know that the API may have changed.");
                SextiarySector.Log.catching(e);

            }
        }

        //マルチブロック
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

        if (modMaid) {

            try {

                SextiarySector.Log.info("Maid Plugin is loaded");
                plugins.add(new PluginMaid());

            } catch (Exception e) {

                SextiarySector.Log.log(Level.WARN, "Maid integration was unsuccessful - please contact the author of this mod to let them know that the API may have changed.");
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

    private static boolean isTconTab() {

        Class<?> clazz;
        try {
            clazz = Class.forName("tconstruct.client.tabs.TabRegistry");
        } catch (ClassNotFoundException e) {
            return false;
        } catch (Exception e) {
            return false;
        }

        return true;

    }

}
