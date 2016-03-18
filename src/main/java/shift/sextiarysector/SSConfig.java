package shift.sextiarysector;

import java.io.File;
import java.util.List;

import org.apache.logging.log4j.Level;

import com.google.common.collect.Lists;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.FMLLaunchHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import shift.sextiarysector.plugin.SSPlugins;

public class SSConfig {

    public static Configuration config;

    //General
    public static boolean fastDecayLeaves;
    public static boolean usualHoe;

    public static boolean particleShaft;

    //Potions
    public static int potionBurn;
    public static int potionHotSprings;

    //World
    public static boolean generateCopperOre;
    public static boolean generateZincOre;
    public static boolean generateSilverOre;

    //Status
    public static boolean statusStamina;
    public static boolean statusMoisture;
    public static boolean peacefulStamina;
    public static boolean peacefulMoisture;

    public static final String SS_LANG = "config.ss.";
    public static final String CATEGORY_POTIONS = "potions";
    public static final String CATEGORY_WORLD = "world";
    public static final String CATEGORY_STATUS = "status";
    public static final String CATEGORY_MOD = "mod";

    private static final Side side = FMLLaunchHandler.side();

    public static void initConfig() {

        if (config == null) {

            File file = new File(Loader.instance().getConfigDir(), "SextiarySector2.cfg");
            config = new Configuration(file);

            try {

                config.load();
            }
            catch (Exception e) {

                File dest = new File(file.getParentFile(), file.getName() + ".bak");

                if (dest.exists()) {

                    dest.delete();
                }

                file.renameTo(dest);

                FMLLog.log(Level.ERROR, e, "A critical error occured reading the " + file.getName() + " file, defaults will be used - the invalid file is backed up at " + dest.getName());
            }
        }
    }

    public static void syncConfig() {

        initConfig();

        String category = Configuration.CATEGORY_GENERAL;
        Property prop;
        List<String> propOrder = Lists.newArrayList();

        //Fast Decay Leaves - 葉の高速消滅
        prop = config.get(category, "fastDecayLeaves", true);
        prop.setLanguageKey(SS_LANG  + category + "." + prop.getName());
        prop.comment = StatCollector.translateToLocal(prop.getLanguageKey() + ".tooltip");
        prop.comment += " [default: " + prop.getDefault() + "]";
        propOrder.add(prop.getName());
        fastDecayLeaves = prop.getBoolean(fastDecayLeaves);

        //Usual Hoe - 通常のクワ
        prop = config.get(category, "usualHoe", false);
        prop.setLanguageKey(SS_LANG  + category + "." + prop.getName());
        prop.comment = StatCollector.translateToLocal(prop.getLanguageKey() + ".tooltip");
        prop.comment += " [default: " + prop.getDefault() + "]";
        propOrder.add(prop.getName());
        usualHoe = prop.getBoolean(usualHoe);

        if (side.isClient()) {

            //Shaft Particle - シャフトのパーティクル
            prop = config.get(category, "particleShaft", true);
            prop.setLanguageKey(SS_LANG  + category + "." + prop.getName());
            prop.comment = StatCollector.translateToLocal(prop.getLanguageKey() + ".tooltip");
            prop.comment += " [default: " + prop.getDefault() + "]";
            propOrder.add(prop.getName());
            particleShaft = prop.getBoolean(particleShaft);
        }

        config.setCategoryLanguageKey(category, SS_LANG + category);
        config.setCategoryPropertyOrder(category, propOrder);

        category = CATEGORY_POTIONS;
        propOrder = Lists.newArrayList();

        //PotionID: Burn - 火傷
        prop = config.get(category, "burn", 25);
        prop.setMinValue(25).setMaxValue(255).setRequiresMcRestart(true);
        prop.setLanguageKey(SS_LANG  + category + "." + prop.getName());
        prop.comment = StatCollector.translateToLocal(prop.getLanguageKey() + ".tooltip");
        prop.comment += " [range: " + prop.getMinValue() + " ~ " + prop.getMaxValue() + ", default: " + prop.getDefault() + "]";
        propOrder.add(prop.getName());
        potionBurn = MathHelper.clamp_int(prop.getInt(potionBurn), Integer.parseInt(prop.getMinValue()), Integer.parseInt(prop.getMaxValue()));

        //PotionID: Hot Springs - 温泉
        prop = config.get(category, "hotSprings", 26);
        prop.setMinValue(25).setMaxValue(255).setRequiresMcRestart(true);
        prop.setLanguageKey(SS_LANG  + category + "." + prop.getName());
        prop.comment = StatCollector.translateToLocal(prop.getLanguageKey() + ".tooltip");
        prop.comment += " [range: " + prop.getMinValue() + " ~ " + prop.getMaxValue() + ", default: " + prop.getDefault() + "]";
        propOrder.add(prop.getName());
        potionHotSprings = MathHelper.clamp_int(prop.getInt(potionHotSprings), Integer.parseInt(prop.getMinValue()), Integer.parseInt(prop.getMaxValue()));

        config.setCategoryRequiresMcRestart(category, true);
        config.setCategoryLanguageKey(category, SS_LANG + category);
        config.setCategoryPropertyOrder(category, propOrder);

        category = CATEGORY_WORLD;
        propOrder = Lists.newArrayList();

        //Generate Copper Ore - 銅鉱石の生成
        prop = config.get(category, "generateCopperOre", true);
        prop.setLanguageKey(SS_LANG  + category + "." + prop.getName());
        prop.comment = StatCollector.translateToLocal(prop.getLanguageKey() + ".tooltip");
        prop.comment += " [default: " + prop.getDefault() + "]";
        propOrder.add(prop.getName());
        generateCopperOre = prop.getBoolean(generateCopperOre);

        //Generate Zinc Ore - 亜鉛鉱石の生成
        prop = config.get(category, "generateZincOre", true);
        prop.setLanguageKey(SS_LANG  + category + "." + prop.getName());
        prop.comment = StatCollector.translateToLocal(prop.getLanguageKey() + ".tooltip");
        prop.comment += " [default: " + prop.getDefault() + "]";
        propOrder.add(prop.getName());
        generateZincOre = prop.getBoolean(generateZincOre);

        //Generate Silver Ore - 銀鉱石の生成
        prop = config.get(category, "generateSilverOre", true);
        prop.setLanguageKey(SS_LANG  + category + "." + prop.getName());
        prop.comment = StatCollector.translateToLocal(prop.getLanguageKey() + ".tooltip");
        prop.comment += " [default: " + prop.getDefault() + "]";
        propOrder.add(prop.getName());
        generateSilverOre = prop.getBoolean(generateSilverOre);

        config.setCategoryLanguageKey(category, SS_LANG + category);
        config.setCategoryPropertyOrder(category, propOrder);

        category = CATEGORY_STATUS;
        propOrder = Lists.newArrayList();

        //Stamina Status - スタミナのステータス
        prop = config.get(category, "statusStamina", true);
        prop.setLanguageKey(SS_LANG  + category + "." + prop.getName());
        prop.comment = StatCollector.translateToLocal(prop.getLanguageKey() + ".tooltip");
        prop.comment += " [default: " + prop.getDefault() + "]";
        propOrder.add(prop.getName());
        statusStamina = prop.getBoolean(statusStamina);

        //Moisture Status - 水分のステータス
        prop = config.get(category, "statusMoisture", true);
        prop.setLanguageKey(SS_LANG  + category + "." + prop.getName());
        prop.comment = StatCollector.translateToLocal(prop.getLanguageKey() + ".tooltip");
        prop.comment += " [default: " + prop.getDefault() + "]";
        propOrder.add(prop.getName());
        statusMoisture = prop.getBoolean(statusMoisture);

        //Peaceful Stamina - ピースフルのスタミナ
        prop = config.get(category, "peacefulStamina", false);
        prop.setLanguageKey(SS_LANG  + category + "." + prop.getName());
        prop.comment = StatCollector.translateToLocal(prop.getLanguageKey() + ".tooltip");
        prop.comment += " [default: " + prop.getDefault() + "]";
        propOrder.add(prop.getName());
        peacefulStamina = prop.getBoolean(peacefulStamina);

        //Peaceful Moisture - ピースフルの水分
        prop = config.get(category, "peacefulMoisture", false);
        prop.setLanguageKey(SS_LANG  + category + "." + prop.getName());
        prop.comment = StatCollector.translateToLocal(prop.getLanguageKey() + ".tooltip");
        prop.comment += " [default: " + prop.getDefault() + "]";
        propOrder.add(prop.getName());
        peacefulMoisture = prop.getBoolean(peacefulMoisture);

        config.setCategoryLanguageKey(category, SS_LANG + category);
        config.setCategoryPropertyOrder(category, propOrder);

        category = CATEGORY_MOD;
        propOrder = Lists.newArrayList();
        String key = "Compatibility with %s";

        prop = config.get(category, "AppleMilkTea", true);
        prop.comment = String.format(key, prop.getName());
        prop.comment += " [default: " + prop.getDefault() + "]";
        propOrder.add(prop.getName());
        SSPlugins.modDCsAppleMilk = prop.getBoolean(SSPlugins.modDCsAppleMilk);

        prop = config.get(category, "ComputerCraft", true);
        prop.comment = String.format(key, prop.getName());
        prop.comment += " [default: " + prop.getDefault() + "]";
        propOrder.add(prop.getName());
        SSPlugins.modComputerCraft = prop.getBoolean(SSPlugins.modComputerCraft);

        prop = config.get(category, "THKaguya", true);
        prop.comment = String.format(key, prop.getName());
        prop.comment += " [default: " + prop.getDefault() + "]";
        propOrder.add(prop.getName());
        SSPlugins.modTHKaguya = prop.getBoolean(SSPlugins.modTHKaguya);

        prop = config.get(category, "IC2", true);
        prop.comment = String.format(key, prop.getName());
        prop.comment += " [default: " + prop.getDefault() + "]";
        propOrder.add(prop.getName());
        SSPlugins.modIC2 = prop.getBoolean(SSPlugins.modIC2);

        prop = config.get(category, "TofuCraft", true);
        prop.comment = String.format(key, prop.getName());
        prop.comment += " [default: " + prop.getDefault() + "]";
        propOrder.add(prop.getName());
        SSPlugins.modTofu = prop.getBoolean(SSPlugins.modTofu);

        prop = config.get(category, "TinkersConstruct", true);
        prop.comment = String.format(key, prop.getName());
        prop.comment += " [default: " + prop.getDefault() + "]";
        propOrder.add(prop.getName());
        SSPlugins.modTcon = prop.getBoolean(SSPlugins.modTcon);

        prop = config.get(category, "Cleaver", true);
        prop.comment = String.format(key, prop.getName());
        prop.comment += " [default: " + prop.getDefault() + "]";
        propOrder.add(prop.getName());
        SSPlugins.modCleaver = prop.getBoolean(SSPlugins.modCleaver);

        prop = config.get(category, "ForgeMultipart", true);
        prop.comment = String.format(key, prop.getName());
        prop.comment += " [default: " + prop.getDefault() + "]";
        propOrder.add(prop.getName());
        SSPlugins.modFMP = prop.getBoolean(SSPlugins.modFMP);

        prop = config.get(category, "RF", true);
        prop.comment = String.format(key, prop.getName());
        prop.comment += " [default: " + prop.getDefault() + "]";
        propOrder.add(prop.getName());
        SSPlugins.modRF = prop.getBoolean(SSPlugins.modRF);

        prop = config.get(category, "Thaumcraft", true);
        prop.comment = String.format(key, prop.getName());
        prop.comment += " [default: " + prop.getDefault() + "]";
        propOrder.add(prop.getName());
        SSPlugins.modTC = prop.getBoolean(SSPlugins.modTC);

        prop = config.get(category, "littleMaidMobX", true);
        prop.comment = String.format(key, prop.getName());
        prop.comment += " [default: " + prop.getDefault() + "]";
        propOrder.add(prop.getName());
        SSPlugins.modMaid = prop.getBoolean(SSPlugins.modMaid);

        config.getCategory(category).setShowInGui(false);
        config.setCategoryPropertyOrder(category, propOrder);

        if (config.hasChanged()) {

            config.save();
        }
    }
}