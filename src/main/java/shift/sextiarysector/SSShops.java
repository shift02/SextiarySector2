package shift.sextiarysector;

import java.util.ArrayList;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import shift.mceconomy2.api.MCEconomyAPI;
import shift.mceconomy2.api.shop.IProductItem;
import shift.mceconomy2.api.shop.IProductList;
import shift.mceconomy2.api.shop.ProductItem;
import shift.sextiarysector.block.BlockMonitor.MonitorType;
import shift.sextiarysector.item.ItemFigureBox;
import shift.sextiarysector.item.ItemSeed;

public class SSShops {

    public static SSProductList[] creepers;

    public static void initShops() {

        creepers = new SSProductList[4];

        for (int i = 0; i < 4; i++) {

            creepers[i] = new SSProductList("shop.ss.creeper");
            creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.blueStoneDust, 2), 320));
            creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.yellowStoneDust, 2), 320));

            //creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.brassIngot, 2), 230));

            creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.takumiTeaBottle, 2), 216));

            creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.seasonStone, 1), 3000));

            creepers[i].addItemProduct(new ProductItem(((ItemFigureBox) SSItems.figureBox).getEditionFigureBox("figure_beginner"), 300));
            creepers[i].addItemProduct(new ProductItem(((ItemFigureBox) SSItems.figureBox).getEditionFigureBox("ore_festival"), 500));

            creepers[i].addItemProduct(new ProductItem(new ItemStack(SSBlocks.woodStoneGearShaft, 1, 0), 260));
            creepers[i].addItemProduct(new ProductItem(new ItemStack(SSBlocks.woodStoneGearShaft, 1, 1), 260));

            creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.gfContactLenses, 1), 300));

            //creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.attackUnit, 1), 2400));
            //creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.defenseUnit, 1), 3600));
            creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.craftUnit, 1), 5300));
            creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.pickaxeUnit, 1), 6800));

            creepers[i].addItemProduct(new ProductItem(new ItemStack(SSBlocks.wood, 1), 180));

            if (i == 0) {
                creepers[i].addItemProduct(new ProductItem(ItemSeed.getSeedItemStack("turnip", 8), 180));
                creepers[i].addItemProduct(new ProductItem(ItemSeed.getSeedItemStack("cucumber", 8), 360));

                creepers[i].addItemProduct(new ProductItem(ItemSeed.getSeedItemStack("rice", 8), 280));
                creepers[i].addItemProduct(new ProductItem(ItemSeed.getSeedItemStack("shiitake", 8), 210));

                creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.mithrilIngot, 1), 1400));
            }

            if (i == 1) {
                creepers[i].addItemProduct(new ProductItem(ItemSeed.getSeedItemStack("onion", 8), 180));
                creepers[i].addItemProduct(new ProductItem(ItemSeed.getSeedItemStack("tomato", 8), 190));
                creepers[i].addItemProduct(new ProductItem(ItemSeed.getSeedItemStack("corn", 8), 740));

                creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.magicDust, 4), 500));
                creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.waterContactLenses, 1), 700));
            }

            if (i == 2) {
                creepers[i].addItemProduct(new ProductItem(ItemSeed.getSeedItemStack("eggplant", 8), 290));
                creepers[i].addItemProduct(new ProductItem(ItemSeed.getSeedItemStack("sweet_potato", 8), 110));
                creepers[i].addItemProduct(new ProductItem(ItemSeed.getSeedItemStack("green_pepper", 8), 320));

                creepers[i].addItemProduct(new ProductItem(new ItemStack(SSBlocks.fluidCrafter, 4, 0), 580));

            }

            if (i == 3) {
                creepers[i].addItemProduct(new ProductItem(ItemSeed.getSeedItemStack("radish", 8), 170));

                creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.stoneDust, 4), 500));
            }

            creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.curryPowder, 4), 200));

            creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.orichalcumGem, 1), 9999));

            //creepers[i].addItemProduct(new ProductItem(BlockMonitor.getMonitor(MonitorType.creeper), 100000));

            creepers[i].addItemProduct(new ProductItem(new ItemStack(SSItems.creeperMemory, 1), 1));

        }

        MonitorType.creeper.setList(creepers);
        //SSProductList[] creepersR = new SSProductList[4];
        //for (int i = 0; i < 4; i++) {
        //	creepersR[i] = creepers[i].copySSProductList();
        //}
        //((ItemShopRing) SSItems.creeperRing).setList(creepersR);
        ///((ItemShopRing) SSItems.creeperRing).setList(creepers);

        SSProductList robot = new SSProductList("shop.ss.robot");
        MonitorType.robot.setList(robot);

        robot.addItemProduct(new ProductItem(new ItemStack(Blocks.lava, 1), 250));

    }

    public static class SSProductList implements IProductList {

        public ArrayList<IProductItem> productItemList = new ArrayList<IProductItem>();
        String name;
        public int id;

        public SSProductList(String name) {
            this.name = name;
            this.id = MCEconomyAPI.registerProductList(this);
        }

        @Override
        public String getProductListName() {
            return this.name;
        }

        public SSProductList copySSProductList() {

            SSProductList c = new SSProductList(this.name);
            c.productItemList = this.getProductList();

            return c;
        }

        @Override
        public void addItemProduct(IProductItem item) {
            productItemList.add(item);
        }

        @Override
        public int getItemProductSize() {
            return productItemList.size();
        }

        @Override
        public ArrayList<IProductItem> getProductList() {
            return productItemList;
        }

    }

    public static void initPurchase() {

        MCEconomyAPI.addPurchaseItem(new ItemStack(Items.skull, 1, OreDictionary.WILDCARD_VALUE), 25);

        MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.turnip), 30);
        MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.cucumber), 35);
        MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.ironTurnip), 300);

        MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.turnip, 1, 1), 300);
        MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.cucumber, 1, 1), 350);
        MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.ironTurnip, 1, 1), 3000);

        MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.onion), 38);
        MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.tomato), 16);
        MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.corn), 84);
        MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.goldenCorn), 800);

        MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.onion, 1, 1), 380);
        MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.tomato, 1, 1), 160);
        MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.corn, 1, 1), 840);
        MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.goldenCorn, 1, 1), 8000);

        MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.eggplant), 32);
        MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.sweetPotato), 20);
        MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.greenPepper), 22);

        MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.eggplant, 1, 1), 320);
        MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.sweetPotato, 1, 1), 200);
        MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.greenPepper, 1, 1), 220);

        MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.radish), 24);

        MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.radish, 1, 1), 240);

        MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.rice), 84);

        MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.rice, 1, 1), 840);

        MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.shiitake), 31);

        MCEconomyAPI.addPurchaseItem(new ItemStack(SSItems.shiitake, 1, 1), 310);

    }

}
