package shift.sextiarysector.recipe;

import net.minecraft.item.ItemStack;
import shift.sextiarysector.SSItems;

public class RecipesPulverizer {

	public static void addRecipes(RecipeSimpleMachine recipe)
    {

		recipe.add("oreCoal",new ItemStack(SSItems.coalDust, 2));
		recipe.add("oreIron",new ItemStack(SSItems.ironDust, 2));
		recipe.add("oreGold",new ItemStack(SSItems.goldDust, 2));
		recipe.add("gemDiamond",new ItemStack(SSItems.diamondDust, 1));

		recipe.add("oreMithril",new ItemStack(SSItems.mithrilDust, 2));

    }

}
