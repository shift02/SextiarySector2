package shift.sextiarysector.module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.World;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.api.IFigureManager;
import shift.sextiarysector.api.SextiarySectorAPI;

public class ModuleFigure implements IModule, IFigureManager {

    private static ModuleFigure instance;

    private ModuleFigure() {
    }

    public static ModuleFigure getInstance() {
        if (instance == null) {
            instance = new ModuleFigure();
        }
        return instance;
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {

        SextiarySectorAPI.figureManager = this.getInstance();

    }

    @Override
    public void load(FMLInitializationEvent event) {

        //フィギュア初心者
        addFigure(SextiarySectorAPI.FIGURE_BEGINNER, new ItemStack(Blocks.dirt), 50);
        addFigure(SextiarySectorAPI.FIGURE_BEGINNER, new ItemStack(Blocks.clay), 40);
        addFigure(SextiarySectorAPI.FIGURE_BEGINNER, new ItemStack(Blocks.fence), 22);
        addFigure(SextiarySectorAPI.FIGURE_BEGINNER, new ItemStack(Blocks.log), 30);
        addFigure(SextiarySectorAPI.FIGURE_BEGINNER, new ItemStack(Blocks.iron_ore), 16);

        addFigure(SextiarySectorAPI.FIGURE_BEGINNER, new ItemStack(Items.stick), 50);
        addFigure(SextiarySectorAPI.FIGURE_BEGINNER, new ItemStack(Items.flint), 40);
        addFigure(SextiarySectorAPI.FIGURE_BEGINNER, new ItemStack(Items.fish), 26);
        addFigure(SextiarySectorAPI.FIGURE_BEGINNER, new ItemStack(Items.book), 34);
        addFigure(SextiarySectorAPI.FIGURE_BEGINNER, new ItemStack(Items.bone), 18);
        addFigure(SextiarySectorAPI.FIGURE_BEGINNER, new ItemStack(Items.cake), 2);

        //鉱石シリーズ
        addFigure(SextiarySectorAPI.ORE_FESTIVAL, new ItemStack(Blocks.stone), 50);
        addFigure(SextiarySectorAPI.ORE_FESTIVAL, new ItemStack(Blocks.cobblestone), 40);
        addFigure(SextiarySectorAPI.ORE_FESTIVAL, new ItemStack(Blocks.coal_ore), 30);
        addFigure(SextiarySectorAPI.ORE_FESTIVAL, new ItemStack(Blocks.iron_ore), 20);
        addFigure(SextiarySectorAPI.ORE_FESTIVAL, new ItemStack(Blocks.gold_ore), 16);
        addFigure(SextiarySectorAPI.ORE_FESTIVAL, new ItemStack(Blocks.lapis_ore), 10);
        addFigure(SextiarySectorAPI.ORE_FESTIVAL, new ItemStack(Blocks.redstone_ore), 8);
        addFigure(SextiarySectorAPI.ORE_FESTIVAL, new ItemStack(Blocks.diamond_ore), 2);

        //MOD
        addFigure(SextiarySectorAPI.ORE_FESTIVAL, new ItemStack(SSBlocks.zincOre), 10);
        addFigure(SextiarySectorAPI.ORE_FESTIVAL, new ItemStack(SSBlocks.copperOre), 20);
        addFigure(SextiarySectorAPI.ORE_FESTIVAL, new ItemStack(SSBlocks.silverOre), 14);
        addFigure(SextiarySectorAPI.ORE_FESTIVAL, new ItemStack(SSBlocks.blueStoneOre), 8);
        addFigure(SextiarySectorAPI.ORE_FESTIVAL, new ItemStack(SSBlocks.yellowStoneOre), 8);
        addFigure(SextiarySectorAPI.ORE_FESTIVAL, new ItemStack(SSBlocks.mithrilOre), 5);
        addFigure(SextiarySectorAPI.ORE_FESTIVAL, new ItemStack(SSBlocks.orichalcumOre), 1);

        //魔法のカボチャ
        addFigure(SextiarySectorAPI.MAGIC_PUMPKIN, new ItemStack(Blocks.pumpkin), 20);
        addFigure(SextiarySectorAPI.MAGIC_PUMPKIN, new ItemStack(Blocks.lit_pumpkin), 8);
        addFigure(SextiarySectorAPI.MAGIC_PUMPKIN, new ItemStack(Blocks.enchanting_table), 3);
        addFigure(SextiarySectorAPI.MAGIC_PUMPKIN, new ItemStack(Blocks.bookshelf), 5);
        addFigure(SextiarySectorAPI.MAGIC_PUMPKIN, new ItemStack(Blocks.torch), 5);
        //addFigure(SextiarySectorAPI.MAGIC_PUMPKIN, new ItemStack(Blocks.flower_pot), 5);
        addFigure(SextiarySectorAPI.MAGIC_PUMPKIN, new ItemStack(Blocks.obsidian), 2);
        addFigure(SextiarySectorAPI.MAGIC_PUMPKIN, new ItemStack(Blocks.beacon), 1);
        addFigure(SextiarySectorAPI.MAGIC_PUMPKIN, new ItemStack(Items.redstone), 5);
        addFigure(SextiarySectorAPI.MAGIC_PUMPKIN, new ItemStack(Items.blaze_rod), 3);
        addFigure(SextiarySectorAPI.MAGIC_PUMPKIN, new ItemStack(Items.golden_apple), 2);
        addFigure(SextiarySectorAPI.MAGIC_PUMPKIN, new ItemStack(Items.ender_pearl), 3);
        addFigure(SextiarySectorAPI.MAGIC_PUMPKIN, new ItemStack(Items.ender_eye), 2);
        addFigure(SextiarySectorAPI.MAGIC_PUMPKIN, new ItemStack(Items.bone), 2);
        addFigure(SextiarySectorAPI.MAGIC_PUMPKIN, new ItemStack(Items.potionitem), 8);
        addFigure(SextiarySectorAPI.MAGIC_PUMPKIN, new ItemStack(Items.blaze_powder), 2);
        addFigure(SextiarySectorAPI.MAGIC_PUMPKIN, new ItemStack(Items.book), 2);
        addFigure(SextiarySectorAPI.MAGIC_PUMPKIN, new ItemStack(Items.cake), 1);
        addFigure(SextiarySectorAPI.MAGIC_PUMPKIN, new ItemStack(Items.name_tag), 2);
        addFigure(SextiarySectorAPI.MAGIC_PUMPKIN, new ItemStack(Items.nether_star), 2);
        addFigure(SextiarySectorAPI.MAGIC_PUMPKIN, new ItemStack(Items.quartz), 2);

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {

    }

    public static void addFigure(String edition, ItemStack seed, int weight) {
        addFigure(edition, new FigureEntry(seed, weight));
    }

    public static void addFigure(String edition, FigureEntry shellEntry) {
        if (!figures.containsKey(edition)) {
            figures.put(edition, new ArrayList<FigureEntry>());
        }
        figures.get(edition).add(shellEntry);
    }

    public static class FigureEntry extends WeightedRandom.Item {
        public final ItemStack figure;
        private float damage;
        private boolean enchant;

        public FigureEntry(ItemStack seed, int weight) {
            super(weight);
            this.figure = seed;
        }

        public ItemStack addCustom(Random p_150708_1_) {
            ItemStack itemstack = this.figure.copy();

            if (this.damage > 0.0F) {
                int i = (int) (this.damage * this.figure.getMaxDamage());
                int j = itemstack.getMaxDamage() - p_150708_1_.nextInt(p_150708_1_.nextInt(i) + 1);

                if (j > i) {
                    j = i;
                }

                if (j < 1) {
                    j = 1;
                }

                itemstack.setItemDamage(j);
            }

            if (this.enchant) {
                EnchantmentHelper.addRandomEnchantment(p_150708_1_, itemstack, 30);
            }

            return itemstack;
        }

        public FigureEntry setDamage(float p_150709_1_) {
            this.damage = p_150709_1_;
            return this;
        }

        public FigureEntry setEnchant() {
            this.enchant = true;
            return this;
        }

    }

    public static final HashMap<String, List<FigureEntry>> figures = new HashMap<String, List<FigureEntry>>();

    public static ItemStack getFigure(World world, String edition) {

        if (!figures.containsKey(edition)) return null;

        FigureEntry entry = (FigureEntry) WeightedRandom.getRandomItem(world.rand, figures.get(edition));
        if (entry == null || entry.figure == null) {
            return null;
        }

        return entry.addCustom(world.rand);

    }

}
