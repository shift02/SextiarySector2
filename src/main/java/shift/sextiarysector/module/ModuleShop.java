package shift.sextiarysector.module;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import shift.mceconomy2.api.shop.ProductItem;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.SSItems;
import shift.sextiarysector.api.shop.ProductListBase;
import shift.sextiarysector.item.ItemFigureBox;

public class ModuleShop implements IModule {

    private static ModuleShop instance = new ModuleShop();

    public static ProductListBase[] creepers;
    public static ProductListBase[] skeleton;

    private ModuleShop() {
    }

    public static ModuleShop getInstance() {
        return instance;
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {

    }

    @Override
    public void load(FMLInitializationEvent event) {

        creepers = new ProductListBase[4];

        for (int i = 0; i < 4; i++) {

            creepers[i] = new ProductListBase("shop.ss.creeper");
            creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.blueStoneDust, 2), 320));
            creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.yellowStoneDust, 2), 320));

            creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.takumiTeaBottle, 2), 216));

            creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.seasonStone, 1), 3000));

            creepers[i].addItemProduct(new ProductItem(((ItemFigureBox) SSItems.figureBox).getEditionFigureBox("figure_beginner"), 300));
            creepers[i].addItemProduct(new ProductItem(((ItemFigureBox) SSItems.figureBox).getEditionFigureBox("ore_festival"), 500));

            creepers[i].addItemProduct(new ProductItem(new ItemStack(SSBlocks.woodStoneGearShaft, 1, 0), 260));
            creepers[i].addItemProduct(new ProductItem(new ItemStack(SSBlocks.woodStoneGearShaft, 1, 1), 260));

            creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.gfContactLenses, 1), 300));

            creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.craftUnit, 1), 5300));
            creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.pickaxeUnit, 1), 6800));

            creepers[i].addItemProduct(new ProductItem(new ItemStack(SSBlocks.shopMonitor, 1), 14000));

            creepers[i].addItemProduct(new ProductItem(new ItemStack(SSBlocks.wood, 1), 180));

            if (i == 0) {
                creepers[i].addItemProduct(new ProductItem(new ItemStack(SSBlocks.turnip, 8), 180));
                creepers[i].addItemProduct(new ProductItem(new ItemStack(SSBlocks.cucumber, 8), 360));

                creepers[i].addItemProduct(new ProductItem(new ItemStack(SSBlocks.rice, 8), 280));
                creepers[i].addItemProduct(new ProductItem(new ItemStack(SSBlocks.shiitake, 8), 210));

                creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.mithrilIngot, 1), 1400));

                creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.skeletonMemory, 1), 1800));

                creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.dashUnit, 1), 8200));

            }

            if (i == 1) {
                creepers[i].addItemProduct(new ProductItem(new ItemStack(SSBlocks.onion, 8), 180));
                creepers[i].addItemProduct(new ProductItem(new ItemStack(SSBlocks.tomato, 8), 190));
                creepers[i].addItemProduct(new ProductItem(new ItemStack(SSBlocks.corn, 8), 740));

                creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.magicDust, 4), 500));
                creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.waterContactLenses, 1), 700));

                creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.slowlyUnit, 1), 3000));

            }

            if (i == 2) {
                creepers[i].addItemProduct(new ProductItem(new ItemStack(SSBlocks.eggplant, 8), 290));
                creepers[i].addItemProduct(new ProductItem(new ItemStack(SSBlocks.sweetPotato, 8), 110));
                creepers[i].addItemProduct(new ProductItem(new ItemStack(SSBlocks.greenPepper, 8), 320));

                creepers[i].addItemProduct(new ProductItem(new ItemStack(SSBlocks.fluidCrafter, 4, 0), 580));

            }

            if (i == 3) {
                creepers[i].addItemProduct(new ProductItem(new ItemStack(SSBlocks.radish, 8), 170));

                creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.stoneDust, 4), 500));
            }

            creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.curryPowder, 4), 200));

            creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.orichalcumGem, 1), 9999));

        }

        SSItems.creeperMemory.setList(creepers);

        skeleton = new ProductListBase[4];

        for (int i = 0; i < 4; i++) {

            skeleton[i] = new ProductListBase("shop.ss.skeleton");

            skeleton[i].addItemProduct(new ProductItem(new ItemStack(Items.iron_axe, 1), 2600));
            skeleton[i].addItemProduct(new ProductItem(new ItemStack(Items.iron_hoe, 1), 2400));
            skeleton[i].addItemProduct(new ProductItem(new ItemStack(Items.iron_pickaxe, 1), 2600));
            skeleton[i].addItemProduct(new ProductItem(new ItemStack(Items.iron_shovel, 1), 2200));
            skeleton[i].addItemProduct(new ProductItem(new ItemStack(Items.iron_sword, 1), 2400));

            skeleton[i].addItemProduct(new ProductItem(new ItemStack(Items.arrow, 16), 800));
            skeleton[i].addItemProduct(new ProductItem(new ItemStack(Items.bow, 1), 2100));

            skeleton[i].addItemProduct(new ProductItem(new ItemStack(Items.iron_helmet, 1), 3000));
            skeleton[i].addItemProduct(new ProductItem(new ItemStack(Items.iron_chestplate, 1), 3600));
            skeleton[i].addItemProduct(new ProductItem(new ItemStack(Items.iron_leggings, 1), 3400));
            skeleton[i].addItemProduct(new ProductItem(new ItemStack(Items.iron_boots, 1), 2800));

            if (i == 0) {

                skeleton[i].addItemProduct(new ProductItem(new ItemStack(SSItems.pullingUnit, 1), 9500));

            }

            if (i == 2) {

                skeleton[i].addItemProduct(new ProductItem(new ItemStack(SSItems.multiSchottUnit, 1), 12700));

            }

        }

        SSItems.skeletonMemory.setList(skeleton);

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {

    }

}
