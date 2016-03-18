package shift.sextiarysector.module;

import java.util.ArrayList;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import shift.mceconomy2.api.MCEconomyAPI;
import shift.mceconomy2.api.shop.IProduct;
import shift.mceconomy2.api.shop.IShop;
import shift.mceconomy2.api.shop.ProductBase;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.SSItems;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.api.season.Season;
import shift.sextiarysector.api.season.SeasonAPI;
import shift.sextiarysector.item.ItemFigureBox;
import shift.sextiarysector.item.ItemSeed;

public class ModuleShop implements IModule {

    private static ModuleShop instance = new ModuleShop();

    public static ShopSeasonBase creeper;
    public static ShopSeasonBase skeleton;

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

        //クリーパー
        creeper = new ShopSeasonBase("shop.ss.creeper");

        creeper.addProduct(new ProductBase(new ItemStack(SSItems.blueStoneDust, 2), 320));
        creeper.addProduct(new ProductBase(new ItemStack(SSItems.yellowStoneDust, 2), 320));

        creeper.addProduct(new ProductBase(new ItemStack(SSItems.takumiTeaBottle, 2), 216));

        creeper.addProduct(new ProductBase(new ItemStack(SSItems.seasonStone, 1), 3000));

        creeper.addProduct(new ProductBase(((ItemFigureBox) SSItems.figureBox).getEditionFigureBox(SextiarySectorAPI.FIGURE_BEGINNER), 300));
        creeper.addProduct(new ProductBase(((ItemFigureBox) SSItems.figureBox).getEditionFigureBox(SextiarySectorAPI.ORE_FESTIVAL), 500));

        //秋
        creeper.addProduct(Season.AUTUMN, new ProductBase(((ItemFigureBox) SSItems.figureBox).getEditionFigureBox(SextiarySectorAPI.MAGIC_PUMPKIN), 500));

        creeper.addProduct(new ProductBase(new ItemStack(SSBlocks.woodStoneGearShaft, 1, 0), 260));
        creeper.addProduct(new ProductBase(new ItemStack(SSBlocks.woodStoneGearShaft, 1, 1), 260));

        creeper.addProduct(new ProductBase(new ItemStack(SSItems.gfContactLenses, 1), 300));

        creeper.addProduct(new ProductBase(new ItemStack(SSItems.craftUnit, 1), 5300));
        creeper.addProduct(new ProductBase(new ItemStack(SSItems.pickaxeUnit, 1), 6800));

        creeper.addProduct(new ProductBase(new ItemStack(SSBlocks.shopMonitor, 1), 14000));

        creeper.addProduct(new ProductBase(new ItemStack(SSBlocks.wood, 1), 180));

        //春
        creeper.addProduct(Season.SPRING, new ProductBase(ItemSeed.getSeedItemStack("turnip", 8), 180));
        creeper.addProduct(Season.SPRING, new ProductBase(ItemSeed.getSeedItemStack("cucumber", 8), 360));

        creeper.addProduct(Season.SPRING, new ProductBase(ItemSeed.getSeedItemStack("rice", 8), 280));
        creeper.addProduct(Season.SPRING, new ProductBase(ItemSeed.getSeedItemStack("shiitake", 8), 210));

        creeper.addProduct(Season.SPRING, new ProductBase(new ItemStack(SSItems.mithrilIngot, 1), 1400));

        creeper.addProduct(Season.SPRING, new ProductBase(new ItemStack(SSItems.skeletonMemory, 1), 1800));

        creeper.addProduct(Season.SPRING, new ProductBase(new ItemStack(SSItems.dashUnit, 1), 8200));

        //夏
        creeper.addProduct(Season.SUMMER, new ProductBase(ItemSeed.getSeedItemStack("onion", 8), 180));
        creeper.addProduct(Season.SUMMER, new ProductBase(ItemSeed.getSeedItemStack("tomato", 8), 190));
        creeper.addProduct(Season.SUMMER, new ProductBase(ItemSeed.getSeedItemStack("corn", 8), 740));

        creeper.addProduct(Season.SUMMER, new ProductBase(new ItemStack(SSItems.magicDust, 4), 500));
        creeper.addProduct(Season.SUMMER, new ProductBase(new ItemStack(SSItems.waterContactLenses, 1), 700));

        creeper.addProduct(Season.SUMMER, new ProductBase(new ItemStack(SSItems.slowlyUnit, 1), 3000));

        //秋
        creeper.addProduct(Season.AUTUMN, new ProductBase(ItemSeed.getSeedItemStack("eggplant", 8), 290));
        creeper.addProduct(Season.AUTUMN, new ProductBase(ItemSeed.getSeedItemStack("sweet_potato", 8), 110));
        creeper.addProduct(Season.AUTUMN, new ProductBase(ItemSeed.getSeedItemStack("green_pepper", 8), 320));

        creeper.addProduct(Season.AUTUMN, new ProductBase(new ItemStack(SSBlocks.fluidCrafter, 4, 0), 580));

        //冬
        creeper.addProduct(Season.WINTER, new ProductBase(ItemSeed.getSeedItemStack("radish", 8), 170));

        creeper.addProduct(Season.WINTER, new ProductBase(new ItemStack(SSItems.stoneDust, 4), 500));

        //その他
        creeper.addProduct(new ProductBase(new ItemStack(SSItems.curryPowder, 4), 200));

        creeper.addProduct(new ProductBase(new ItemStack(SSItems.orichalcumGem, 1), 9999));

        SSItems.creeperMemory.setShopData(creeper);

        //スケルトン
        skeleton = new ShopSeasonBase("shop.ss.skeleton");

        skeleton.addProduct(new ProductBase(new ItemStack(Items.iron_axe, 1), 2600));
        skeleton.addProduct(new ProductBase(new ItemStack(Items.iron_hoe, 1), 2400));
        skeleton.addProduct(new ProductBase(new ItemStack(Items.iron_pickaxe, 1), 2600));
        skeleton.addProduct(new ProductBase(new ItemStack(Items.iron_shovel, 1), 2200));
        skeleton.addProduct(new ProductBase(new ItemStack(Items.iron_sword, 1), 2400));

        skeleton.addProduct(new ProductBase(new ItemStack(Items.arrow, 16), 800));
        skeleton.addProduct(new ProductBase(new ItemStack(Items.bow, 1), 2100));

        skeleton.addProduct(new ProductBase(new ItemStack(Items.iron_helmet, 1), 3000));
        skeleton.addProduct(new ProductBase(new ItemStack(Items.iron_chestplate, 1), 3600));
        skeleton.addProduct(new ProductBase(new ItemStack(Items.iron_leggings, 1), 3400));
        skeleton.addProduct(new ProductBase(new ItemStack(Items.iron_boots, 1), 2800));

        //春
        skeleton.addProduct(Season.SPRING, new ProductBase(new ItemStack(SSItems.pullingUnit, 1), 9500));

        //秋
        skeleton.addProduct(Season.AUTUMN, new ProductBase(new ItemStack(SSItems.multiSchottUnit, 1), 12700));

        SSItems.skeletonMemory.setShopData(skeleton);

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {

    }

    static public class ShopSeasonBase implements IShop {

        private ArrayList<IProduct>[] productList;
        private String name;

        private int id;

        public ShopSeasonBase(String name) {

            this.name = name;
            this.productList = new ArrayList[4];
            for (int i = 0; i < this.productList.length; i++) {
                this.productList[i] = new ArrayList<IProduct>();
            }

            this.id = MCEconomyAPI.registerShop(this);

        }

        public int getID() {
            return this.id;
        }

        @Override
        public String getShopName(World world, EntityPlayer player) {
            return this.name;
        }

        @Override
        public void addProduct(IProduct product) {
            for (int i = 0; i < this.productList.length; i++) {
                this.productList[i].add(product);
            }
        }

        public void addProduct(Season season, IProduct product) {
            this.productList[season.ordinal()].add(product);
        }

        @Override
        public ArrayList<IProduct> getProductList(World world, EntityPlayer player) {

            if (world == null) return this.productList[0];

            return this.productList[SeasonAPI.getSeason(world).ordinal()];

        }

    }

}
