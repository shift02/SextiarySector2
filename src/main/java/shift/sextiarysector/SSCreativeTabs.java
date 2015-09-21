package shift.sextiarysector;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import shift.sextiarysector.api.SextiarySectorAPI;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SSCreativeTabs {

    public static void initCreativeTabs() {

        SextiarySectorAPI.TabSSCore = new CreativeTabSSCore();
        SextiarySectorAPI.TabSSFluid = new CreativeTabSSFluid();
        SextiarySectorAPI.TabSSPlayer = new CreativeTabSSPlayer();
        SextiarySectorAPI.TabSSAgriculture = new CreativeTabSSAgriculture();
        SextiarySectorAPI.TabSSFishery = new CreativeTabSSFishery();
        SextiarySectorAPI.TabSSMining = new CreativeTabSSMining();
        SextiarySectorAPI.TabSSIndustry = new CreativeTabSSIndustry();
        SextiarySectorAPI.TabSSCooking = new CreativeTabSSCooking();
        SextiarySectorAPI.TabSSTransport = new CreativeTabSSTransport();
        SextiarySectorAPI.TabSSEconomy = new CreativeTabSSEconomy();
        SextiarySectorAPI.TabSSMagic = new CreativeTabSSMagic();

    }

    private static class CreativeTabSSCore extends CreativeTabs {

        public CreativeTabSSCore() {
            super("ss.core");
        }

        @Override
        public Item getTabIconItem() {
            return null;//SSBlocks.LargeFurnace.g;
        }

        @Override
        @SideOnly(Side.CLIENT)
        public ItemStack getIconItemStack() {
            return new ItemStack(SSBlocks.LargeFurnace, 1);
        }

    }

    private static class CreativeTabSSFluid extends CreativeTabs {

        public CreativeTabSSFluid() {
            super("ss.fluid");
        }

        @Override
        public Item getTabIconItem() {
            return null;//SSBlocks.LargeFurnace.g;
        }

        @Override
        @SideOnly(Side.CLIENT)
        public ItemStack getIconItemStack() {
            return new ItemStack(SSItems.lavaBottle, 1);
        }

    }

    private static class CreativeTabSSPlayer extends CreativeTabs {

        public CreativeTabSSPlayer() {
            super("ss.player");
        }

        @Override
        public Item getTabIconItem() {
            return null;//SSBlocks.LargeFurnace.g;
        }

        @Override
        @SideOnly(Side.CLIENT)
        public ItemStack getIconItemStack() {
            return new ItemStack(SSItems.ironRing, 1);
        }

    }

    private static class CreativeTabSSAgriculture extends CreativeTabs {

        public CreativeTabSSAgriculture() {
            super("ss.agriculture");
        }

        @Override
        public Item getTabIconItem() {
            return null;//SSBlocks.LargeFurnace.g;
        }

        @Override
        @SideOnly(Side.CLIENT)
        public ItemStack getIconItemStack() {
            return new ItemStack(SSBlocks.turnip, 1);
        }

    }

    private static class CreativeTabSSFishery extends CreativeTabs {

        public CreativeTabSSFishery() {
            super("ss.fishery");
        }

        @Override
        public Item getTabIconItem() {
            return null;//SSBlocks.LargeFurnace.g;
        }

        @Override
        @SideOnly(Side.CLIENT)
        public ItemStack getIconItemStack() {
            return new ItemStack(SSItems.laver, 1);
        }

    }

    private static class CreativeTabSSMining extends CreativeTabs {

        public CreativeTabSSMining() {
            super("ss.mining");
        }

        @Override
        public Item getTabIconItem() {
            return null;//SSBlocks.LargeFurnace.g;
        }

        @Override
        @SideOnly(Side.CLIENT)
        public ItemStack getIconItemStack() {
            return new ItemStack(SSBlocks.ironLargeOre, 1);
        }

    }

    private static class CreativeTabSSIndustry extends CreativeTabs {

        public CreativeTabSSIndustry() {
            super("ss.industry");
        }

        @Override
        public Item getTabIconItem() {
            return null;//SSBlocks.LargeFurnace.g;
        }

        @Override
        @SideOnly(Side.CLIENT)
        public ItemStack getIconItemStack() {
            return new ItemStack(SSBlocks.woodShaft, 1);
        }

    }

    private static class CreativeTabSSCooking extends CreativeTabs {

        public CreativeTabSSCooking() {
            super("ss.cooking");
        }

        @Override
        public Item getTabIconItem() {
            return null;//SSBlocks.LargeFurnace.g;
        }

        @Override
        @SideOnly(Side.CLIENT)
        public ItemStack getIconItemStack() {
            return new ItemStack(SSItems.ironKnife, 1);
        }

    }

    private static class CreativeTabSSTransport extends CreativeTabs {

        public CreativeTabSSTransport() {
            super("ss.transport");
        }

        @Override
        public Item getTabIconItem() {
            return null;//SSBlocks.LargeFurnace.g;
        }

        @Override
        @SideOnly(Side.CLIENT)
        public ItemStack getIconItemStack() {
            return new ItemStack(SSItems.mineboatChest, 1);
        }

    }

    private static class CreativeTabSSEconomy extends CreativeTabs {

        public CreativeTabSSEconomy() {
            super("ss.economy");
        }

        @Override
        public Item getTabIconItem() {
            return null;//SSBlocks.LargeFurnace.g;
        }

        @Override
        @SideOnly(Side.CLIENT)
        public ItemStack getIconItemStack() {
            return new ItemStack(SSBlocks.creeperChest, 1);
        }

    }

    private static class CreativeTabSSMagic extends CreativeTabs {

        public CreativeTabSSMagic() {
            super("ss.magic");
        }

        @Override
        public Item getTabIconItem() {
            return null;//SSBlocks.LargeFurnace.g;
        }

        @Override
        @SideOnly(Side.CLIENT)
        public ItemStack getIconItemStack() {
            return new ItemStack(SSItems.magicDust, 1);
        }

    }

}
