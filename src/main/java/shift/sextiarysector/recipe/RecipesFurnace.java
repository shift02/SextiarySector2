package shift.sextiarysector.recipe;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.SSItems;

public class RecipesFurnace {

    public static void addRecipes() {
        GameRegistry.addSmelting(new ItemStack(SSItems.ironDust), new ItemStack(Items.iron_ingot), 0.1f);
        GameRegistry.addSmelting(new ItemStack(SSItems.goldDust), new ItemStack(Items.gold_ingot), 0.1f);
        GameRegistry.addSmelting(new ItemStack(SSItems.copperDust), new ItemStack(SSItems.copperIngot), 0.1f);
        GameRegistry.addSmelting(new ItemStack(SSItems.zincDust), new ItemStack(SSItems.zincIngot), 0.1f);
        GameRegistry.addSmelting(new ItemStack(SSItems.silverDust), new ItemStack(SSItems.silverIngot), 0.1f);

        GameRegistry.addSmelting(new ItemStack(SSBlocks.copperOre), new ItemStack(SSItems.copperIngot), 0.1f);
        GameRegistry.addSmelting(new ItemStack(SSBlocks.zincOre), new ItemStack(SSItems.zincIngot), 0.1f);
        GameRegistry.addSmelting(new ItemStack(SSBlocks.silverOre), new ItemStack(SSItems.silverIngot), 0.1f);

        GameRegistry.addSmelting(new ItemStack(SSBlocks.orichalcumOre), new ItemStack(SSItems.orichalcumGem), 0.1f);
        GameRegistry.addSmelting(new ItemStack(SSBlocks.blueStoneOre), new ItemStack(SSItems.blueStoneDust), 0.1f);
        GameRegistry.addSmelting(new ItemStack(SSBlocks.yellowStoneOre), new ItemStack(SSItems.yellowStoneDust), 0.1f);

        GameRegistry.addSmelting(new ItemStack(SSItems.laver), new ItemStack(SSItems.laverRoasted), 0.1f);

        GameRegistry.addSmelting(new ItemStack(Items.potionitem, 1, 0), new ItemStack(SSItems.drinkingWaterBottle), 0.1f);

        GameRegistry.addSmelting(new ItemStack(SSBlocks.leafBlock), new ItemStack(Items.dye, 1, 2), 0.1f);

        GameRegistry.addSmelting(new ItemStack(Blocks.tallgrass, 1, 1), new ItemStack(SSItems.ash, 1), 0.1f);
        GameRegistry.addSmelting(new ItemStack(Blocks.tallgrass, 1, 2), new ItemStack(SSItems.ash, 1), 0.1f);

    }

}
