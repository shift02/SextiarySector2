package shift.sextiarysector;

import java.util.ArrayList;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import shift.sextiarysector.agriculture.CropBase;
import shift.sextiarysector.agriculture.CropManager;
import shift.sextiarysector.agriculture.CropPaddy;
import shift.sextiarysector.agriculture.CropReHarvest;
import shift.sextiarysector.agriculture.CropTest;
import shift.sextiarysector.agriculture.FarmlandRegistry;
import shift.sextiarysector.agriculture.FertilizerManager;
import shift.sextiarysector.agriculture.MutationRegistry;
import shift.sextiarysector.api.agriculture.AgricultureAPI;
import shift.sextiarysector.api.agriculture.CropVanilla;
import shift.sextiarysector.api.agriculture.CropWither;
import shift.sextiarysector.api.agriculture.FertilizerBase;
import shift.sextiarysector.api.agriculture.ICrop;
import shift.sextiarysector.api.agriculture.IFertilizer;
import shift.sextiarysector.api.agriculture.MutationNormal;
import shift.sextiarysector.api.season.Season;

public class SSCrops {

    public static CropManager cropManager;
    public static FarmlandRegistry farmlandRegistry;
    public static FertilizerManager fertilizerManager;
    public static MutationRegistry mutationRegistry;

    //肥料
    public static IFertilizer normal;

    public static ICrop test;
    public static ICrop wither;

    public static ICrop wheat;
    public static ICrop carrot;
    public static ICrop potato;

    //春
    public static CropBase turnip;
    public static CropBase cucumber;
    public static CropBase ironTurnip;

    //夏
    public static CropBase onion;
    public static CropBase tomato;
    public static CropBase corn;
    public static CropBase goldenCorn;

    //秋
    public static CropBase eggplant;
    public static CropBase sweetPotato;
    public static CropBase greenPepper;

    //冬
    public static CropBase radish;

    //穀物
    public static CropBase rice;

    //キノコ
    public static CropBase redMushroom;
    public static CropBase shiitake;

    //突然変異

    /** 骨粉で変異する作物のリスト */
    public static ArrayList<Item> boneMealCrops = new ArrayList<Item>();

    public static void preInit() {

        cropManager = new CropManager();
        AgricultureAPI.cropManager = cropManager;

        farmlandRegistry = new FarmlandRegistry();
        AgricultureAPI.farmlandRegistry = farmlandRegistry;

        fertilizerManager = new FertilizerManager();
        AgricultureAPI.fertilizerManager = fertilizerManager;

        mutationRegistry = new MutationRegistry();
        AgricultureAPI.mutationRegistry = mutationRegistry;

    }

    public static void initCrops() {

        //農地登録
        AgricultureAPI.registerFarmland(AgricultureAPI.FARMLAND, SSBlocks.farmland);

        //肥料の登録
        //TODO
        normal = new FertilizerBase("normal", new ItemStack(Items.dye, 1, 15)).setIconName("sextiarysector:fertilizer/bone");
        AgricultureAPI.normal = normal;
        AgricultureAPI.registerFertilizer(normal);

        //作物の登録
        test = new CropTest();
        cropManager.registerCrop(test);

        wither = new CropWither();
        cropManager.registerCrop(wither);

        //バニラ
        wheat = new CropVanilla("wheat", Items.wheat_seeds, Blocks.wheat, new Season[] { Season.AUTUMN, Season.WINTER, Season.SPRING }, new int[] { 8, 13, 25, 32, 40, 50, 62 });
        cropManager.registerCrop(wheat);

        carrot = new CropVanilla("carrot", Items.carrot, Blocks.carrots, new Season[] { Season.AUTUMN }, new int[] { 2, 4, 7 });
        cropManager.registerCrop(carrot);

        potato = new CropVanilla("potato", Items.potato, Blocks.potatoes, new Season[] { Season.SPRING }, new int[] { 2, 5, 7 });
        cropManager.registerCrop(potato);

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
        onion = new CropBase("onion", SSItems.onion, new Season[] { Season.SUMMER }, new int[] { 2, 4, 8 });
        cropManager.registerCrop(onion);
        SSItems.seeds.addSeed("onion", onion);

        tomato = new CropReHarvest("tomato", SSItems.tomato, new Season[] { Season.SUMMER }, new int[] { 4, 10, 14, 2 });
        cropManager.registerCrop(tomato);
        SSItems.seeds.addSeed("tomato", tomato);

        corn = new CropBase("corn", SSItems.corn, new Season[] { Season.SUMMER }, new int[] { 4, 8, 12 });
        cropManager.registerCrop(corn);
        SSItems.seeds.addSeed("corn", corn);

        goldenCorn = new CropBase("golden_corn", SSItems.goldenCorn, new Season[] { Season.SUMMER }, new int[] { 4, 8, 12 });
        cropManager.registerCrop(goldenCorn);
        SSItems.seeds.addSeed("golden_corn", goldenCorn);

        //秋
        eggplant = new CropReHarvest("eggplant", SSItems.eggplant, new Season[] { Season.AUTUMN }, new int[] { 3, 7, 12, 3 });
        cropManager.registerCrop(eggplant);
        SSItems.seeds.addSeed("eggplant", eggplant);

        sweetPotato = new CropReHarvest("sweet_potato", SSItems.sweetPotato, new Season[] { Season.AUTUMN }, new int[] { 2, 4, 6, 2 });
        cropManager.registerCrop(sweetPotato);
        SSItems.seeds.addSeed("sweet_potato", sweetPotato);

        greenPepper = new CropReHarvest("green_pepper", SSItems.greenPepper, new Season[] { Season.AUTUMN }, new int[] { 4, 8, 14, 3 });
        cropManager.registerCrop(greenPepper);
        SSItems.seeds.addSeed("green_pepper", greenPepper);

        //冬
        radish = new CropBase("radish", SSItems.radish, new Season[] { Season.WINTER }, new int[] { 2, 4, 5 });
        cropManager.registerCrop(radish);
        SSItems.seeds.addSeed("radish", radish);

        //穀物
        rice = new CropPaddy("rice", SSItems.rice, new Season[] { Season.SPRING, Season.SUMMER, Season.AUTUMN }, new int[] { 28, 46, 68 });
        cropManager.registerCrop(rice);
        SSItems.seeds.addSeed("rice", rice);

        //キノコ

        //突然変異

        //普通の肥料
        for (Item item : boneMealCrops) {
            mutationRegistry.registeMutation(new MutationNormal(new ItemStack(item, 1, 0), new ItemStack(item, 1, 1)) {

                @Override
                public int getProbability() {
                    return 0;
                }

            });
        }

    }

}
