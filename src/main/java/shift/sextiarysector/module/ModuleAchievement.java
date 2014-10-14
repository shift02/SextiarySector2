package shift.sextiarysector.module;

import java.util.ArrayList;
import java.util.Iterator;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatCrafting;
import net.minecraft.stats.StatList;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.AchievementPage;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.SextiarySector;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ModuleAchievement implements IModule {


	private static ModuleAchievement instance;

	public static StatBase[] objectSellStats = new StatBase[32000];

	private ModuleAchievement() {
	}

	public static ModuleAchievement getInstance() {
		if(instance==null){
			instance = new ModuleAchievement();
		}
		return instance;
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {

		initEconomy();

	}


	private ArrayList<Achievement> economy = new ArrayList<Achievement>();

	public static Achievement creeperFirework;

	public static Achievement creeperChest;

	public static Achievement shipping;

	private void initEconomy() {

		creeperFirework = new SSAchievement("creeper_firework", 0, 0, new ItemStack(Items.fireworks), (Achievement)null,economy).initIndependentStat().registerStat();
		creeperChest = new SSAchievement("creeper_chest", 1, -2, new ItemStack(SSBlocks.creeperChest), creeperFirework,economy).registerStat();
		shipping = new SSAchievement("shipping", 3, -2, new ItemStack(SSBlocks.shippingBox), creeperChest,economy).registerStat();

		AchievementPage.registerAchievementPage(new AchievementPage("achievement.ss.economy", economy.toArray(new Achievement[0])){
			public String getName()
		    {
		        return StatCollector.translateToLocal(super.getName());
		    }
		});

	}

	private static class SSAchievement extends Achievement{

		public SSAchievement(String p_i45302_1_,int p_i45302_3_, int p_i45302_4_, ItemStack p_i45302_5_,Achievement p_i45302_6_,ArrayList<Achievement>a) {
			super("achievement.ss"+p_i45302_1_, p_i45302_1_, p_i45302_3_, p_i45302_4_, p_i45302_5_,p_i45302_6_);
			a.add(this);
		}

	}

	@Override
	public void load(FMLInitializationEvent event) {

	}
	@Override
	public void postInit(FMLPostInitializationEvent event) {

		initStats();

	}

	private static void initStats()
    {
        Iterator iterator = Item.itemRegistry.iterator();

        while (iterator.hasNext())
        {

        	try{

        		Item item = (Item)iterator.next();

                if (item != null)
                {
                    int i = Item.getIdFromItem(item);

                    objectSellStats[i] = (new StatCrafting("stat.sellItem." + i, new ChatComponentTranslation("stat.sellItem", new Object[] {(new ItemStack(item)).func_151000_E()}), item)).registerStat();

                    if (!(item instanceof ItemBlock))
                    {
        				StatList.itemStats.add((StatCrafting)objectSellStats[i]);
                    }

                }


        	}catch (NullPointerException e){
        		SextiarySector.Log.catching(e);
            }catch (ArrayIndexOutOfBoundsException e){
            	SextiarySector.Log.catching(e);
            }

        }

        replaceAllSimilarBlocks(objectSellStats);
    }

	private static void replaceAllSimilarBlocks(StatBase[] p_75924_0_)
    {
        func_151180_a(p_75924_0_, Blocks.water, Blocks.flowing_water);
        func_151180_a(p_75924_0_, Blocks.lava, Blocks.flowing_lava);
        func_151180_a(p_75924_0_, Blocks.lit_pumpkin, Blocks.pumpkin);
        func_151180_a(p_75924_0_, Blocks.lit_furnace, Blocks.furnace);
        func_151180_a(p_75924_0_, Blocks.lit_redstone_ore, Blocks.redstone_ore);
        func_151180_a(p_75924_0_, Blocks.powered_repeater, Blocks.unpowered_repeater);
        func_151180_a(p_75924_0_, Blocks.powered_comparator, Blocks.unpowered_comparator);
        func_151180_a(p_75924_0_, Blocks.redstone_torch, Blocks.unlit_redstone_torch);
        func_151180_a(p_75924_0_, Blocks.lit_redstone_lamp, Blocks.redstone_lamp);
        func_151180_a(p_75924_0_, Blocks.red_mushroom, Blocks.brown_mushroom);
        func_151180_a(p_75924_0_, Blocks.double_stone_slab, Blocks.stone_slab);
        func_151180_a(p_75924_0_, Blocks.double_wooden_slab, Blocks.wooden_slab);
        func_151180_a(p_75924_0_, Blocks.grass, Blocks.dirt);
        func_151180_a(p_75924_0_, Blocks.farmland, Blocks.dirt);
    }

    private static void func_151180_a(StatBase[] p_151180_0_, Block p_151180_1_, Block p_151180_2_)
    {
        int i = Block.getIdFromBlock(p_151180_1_);
        int j = Block.getIdFromBlock(p_151180_2_);

        if (p_151180_0_[i] != null && p_151180_0_[j] == null)
        {
            p_151180_0_[j] = p_151180_0_[i];
        }
        else
        {
        	StatList.allStats.remove(p_151180_0_[i]);
        	StatList.objectMineStats.remove(p_151180_0_[i]);
            StatList.generalStats.remove(p_151180_0_[i]);
            p_151180_0_[i] = p_151180_0_[j];
        }
    }


}
