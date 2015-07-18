package shift.sextiarysector.recipe;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.SSItems;
import cpw.mods.fml.common.registry.GameRegistry;

public class RecipesFurnace {

	public static void addRecipes()
	{
		GameRegistry.addSmelting(new ItemStack(SSItems.ironDust), new ItemStack(Items.iron_ingot), 0.1f);
		GameRegistry.addSmelting(new ItemStack(SSItems.goldDust), new ItemStack(Items.gold_ingot), 0.1f);
		GameRegistry.addSmelting(new ItemStack(SSItems.copperDust), new ItemStack(SSItems.copperIngot), 0.1f);
		GameRegistry.addSmelting(new ItemStack(SSItems.zincDust), new ItemStack(SSItems.zincIngot), 0.1f);

		GameRegistry.addSmelting(new ItemStack(SSBlocks.copperOre), new ItemStack(SSItems.copperIngot), 0.1f);
		GameRegistry.addSmelting(new ItemStack(SSBlocks.zincOre), new ItemStack(SSItems.zincIngot), 0.1f);
		GameRegistry.addSmelting(new ItemStack(SSBlocks.silverOre), new ItemStack(SSItems.silverIngot), 0.1f);

		GameRegistry.addSmelting(new ItemStack(SSBlocks.orichalcumOre), new ItemStack(SSItems.orichalcumGem), 0.1f);
		GameRegistry.addSmelting(new ItemStack(SSBlocks.blueStoneOre), new ItemStack(SSItems.blueStoneDust), 0.1f);
		GameRegistry.addSmelting(new ItemStack(SSBlocks.yellowStoneOre), new ItemStack(SSItems.yellowStoneDust), 0.1f);

		GameRegistry.addSmelting(new ItemStack(SSItems.laver), new ItemStack(SSItems.laverRoasted), 0.1f);

		GameRegistry.addSmelting(new ItemStack(SSItems.waterBottle), new ItemStack(SSItems.drinkingWaterBottle), 0.1f);

	}

}
