package shift.sextiarysector.recipe;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import shift.sextiarysector.SSItems;

public class RecipesPulverizer {

	public static void addRecipes(RecipeSimpleMachine recipe)
    {

		recipe.add(new ItemStack(Blocks.coal_ore,1),new ItemStack(SSItems.coalDust, 2));
		recipe.add(new ItemStack(Blocks.iron_ore,1),new ItemStack(SSItems.ironDust, 2));
		recipe.add(new ItemStack(Blocks.gold_ore,1),new ItemStack(SSItems.goldDust, 2));

    }

}
