package shift.sextiarysector.recipe;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import shift.sextiarysector.SSItems;
import cpw.mods.fml.common.registry.GameRegistry;

public class RecipesFurnace {

	public static void addRecipes()
    {
		GameRegistry.addSmelting(new ItemStack(SSItems.ironDust),new ItemStack(Items.iron_ingot),0.1f);
		GameRegistry.addSmelting(new ItemStack(SSItems.goldDust),new ItemStack(Items.gold_ingot),0.1f);

		GameRegistry.addSmelting(new ItemStack(SSItems.laver),new ItemStack(SSItems.laverRoasted),0.1f);

    }

}
