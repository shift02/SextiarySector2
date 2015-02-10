package shift.sextiarysector.module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.World;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ModuleFigure implements IModule {

	private static ModuleFigure instance;

	private ModuleFigure() {
	}

	public static ModuleFigure getInstance() {
		if(instance==null){
			instance = new ModuleFigure();
		}
		return instance;
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {

	}

	@Override
	public void load(FMLInitializationEvent event) {

		addFigure("figure_beginner", new ItemStack(Blocks.dirt), 50);
		addFigure("figure_beginner", new ItemStack(Blocks.clay), 40);
		addFigure("figure_beginner", new ItemStack(Blocks.fence), 22);
		addFigure("figure_beginner", new ItemStack(Blocks.log), 30);
		addFigure("figure_beginner", new ItemStack(Blocks.iron_ore), 16);

		addFigure("figure_beginner", new ItemStack(Items.stick), 50);
		addFigure("figure_beginner", new ItemStack(Items.flint), 40);
		addFigure("figure_beginner", new ItemStack(Items.fish), 26);
		addFigure("figure_beginner", new ItemStack(Items.book), 34);
		addFigure("figure_beginner", new ItemStack(Items.bone), 18);
		addFigure("figure_beginner", new ItemStack(Items.cake), 2);

	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {

	}

	public static void addFigure(String edition, ItemStack seed, int weight)
    {
		addFigure(edition, new FigureEntry(seed, weight));
    }

	public static void addFigure(String edition, FigureEntry shellEntry){
		if(!figures.containsKey(edition)){
			figures.put(edition, new ArrayList<FigureEntry>());
		}
		figures.get(edition).add(shellEntry);
	}

	public static class FigureEntry extends WeightedRandom.Item
    {
        public final ItemStack figure;
        private float damage;
        private boolean enchant;

        public FigureEntry(ItemStack seed, int weight)
        {
            super(weight);
            this.figure = seed;
        }

        public ItemStack addCustom(Random p_150708_1_)
        {
            ItemStack itemstack = this.figure.copy();

            if (this.damage > 0.0F)
            {
                int i = (int)(this.damage * (float)this.figure.getMaxDamage());
                int j = itemstack.getMaxDamage() - p_150708_1_.nextInt(p_150708_1_.nextInt(i) + 1);

                if (j > i)
                {
                    j = i;
                }

                if (j < 1)
                {
                    j = 1;
                }

                itemstack.setItemDamage(j);
            }

            if (this.enchant)
            {
                EnchantmentHelper.addRandomEnchantment(p_150708_1_, itemstack, 30);
            }

            return itemstack;
        }

        public FigureEntry setDamage(float p_150709_1_)
        {
            this.damage = p_150709_1_;
            return this;
        }

        public FigureEntry setEnchant()
        {
            this.enchant = true;
            return this;
        }

    }
	public static final HashMap<String,List<FigureEntry>> figures = new HashMap<String,List<FigureEntry>>();

    public static ItemStack getFigure(World world, String edition)
    {

    	if(!figures.containsKey(edition))return null;

    	FigureEntry entry = (FigureEntry)WeightedRandom.getRandomItem(world.rand, figures.get(edition));
        if (entry == null || entry.figure == null)
        {
            return null;
        }

        return entry.addCustom(world.rand);

    }

}
