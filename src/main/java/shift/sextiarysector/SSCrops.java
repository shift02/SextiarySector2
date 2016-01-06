package shift.sextiarysector;

import shift.sextiarysector.agriculture.CropBase;
import shift.sextiarysector.agriculture.CropManager;
import shift.sextiarysector.agriculture.CropReHarvest;
import shift.sextiarysector.agriculture.CropTest;
import shift.sextiarysector.agriculture.FarmlandRegistry;
import shift.sextiarysector.api.agriculture.AgricultureAPI;
import shift.sextiarysector.api.agriculture.CropWither;
import shift.sextiarysector.api.agriculture.ICrop;
import shift.sextiarysector.api.season.Season;

public class SSCrops {

    public static CropManager cropManager;
    public static FarmlandRegistry farmlandRegistry;

    public static ICrop test;
    public static ICrop wither;

    public static ICrop wheat;

    public static CropBase turnip;
    public static CropBase cucumber;
    public static CropBase ironTurnip;

    public static CropBase onion;
    public static CropBase tomato;
    public static CropBase corn;
    public static CropBase goldenCorn;

    public static CropBase eggplant;
    public static CropBase sweetPotato;
    public static CropBase greenPepper;

    public static CropBase radish;

    public static CropBase rice;

    public static CropBase redMushroom;
    public static CropBase shiitake;

    public static void preInit() {

        cropManager = new CropManager();
        AgricultureAPI.cropManager = cropManager;

        farmlandRegistry = new FarmlandRegistry();
        AgricultureAPI.farmlandRegistry = farmlandRegistry;

    }

    public static void initCrops() {

        //農地登録
        AgricultureAPI.registerFarmland(AgricultureAPI.FARMLAND, SSBlocks.farmland);

        //作物の登録
        test = new CropTest();
        cropManager.registerCrop(test);

        wither = new CropWither();
        cropManager.registerCrop(wither);

        //バニラ
        //wheat = new CropVanilla("wheat", Items.wheat_seeds, Blocks.wheat, new Season[] { Season.AUTUMN, Season.WINTER, Season.SPRING }, new int[] { 1, 2, 3, 4, 5, 6, 7 });
        //cropManager.registerCrop(wheat);

        //SS2

        //春
        turnip = new CropBase("turnip", SSItems.turnip, new Season[] { Season.SPRING }, new int[] { 2, 3, 4 });
        cropManager.registerCrop(turnip);
        SSItems.seeds.addSeed("turnip", turnip);

        cucumber = new CropReHarvest("cucumber", SSItems.cucumber, new Season[] { Season.SPRING }, new int[] { 2, 4, 10, 3 });
        cropManager.registerCrop(cucumber);
        SSItems.seeds.addSeed("cucumber", cucumber);

        ironTurnip = new CropBase("iron_turnip", SSItems.ironTurnip, new Season[] { Season.SPRING }, new int[] { 4, 8, 12 });
        cropManager.registerCrop(ironTurnip);
        SSItems.seeds.addSeed("iron_turnip", ironTurnip);

        //夏

    }

}
