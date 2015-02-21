package shift.sextiarysector.recipe;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import shift.sextiarysector.SSItems;

public class RecipesPulverizer {

	public static void addRecipes(RecipeSimpleMachine recipe)
	{

		recipe.add("oreCoal", new ItemStack(SSItems.coalDust, 2));
		recipe.add(new ItemStack(Items.coal, 1, 0), new ItemStack(SSItems.coalDust, 1));
		recipe.add(new ItemStack(Items.coal, 1, 1), new ItemStack(SSItems.coalDust, 1));
		recipe.add("oreIron", new ItemStack(SSItems.ironDust, 2));
		recipe.add("oreGold", new ItemStack(SSItems.goldDust, 2));
		recipe.add("oreCopper", new ItemStack(SSItems.copperDust, 2));
		recipe.add("oreZinc", new ItemStack(SSItems.zincDust, 2));
		recipe.add("gemDiamond", new ItemStack(SSItems.diamondDust, 1));

		recipe.add("oreMithril", new ItemStack(SSItems.mithrilDust, 2));

		recipe.add("ingotCoal", new ItemStack(SSItems.coalDust, 1));
		recipe.add("ingotIron", new ItemStack(SSItems.ironDust, 1));
		recipe.add("ingotGold", new ItemStack(SSItems.goldDust, 1));
		recipe.add("ingotCopper", new ItemStack(SSItems.copperDust, 1));
		recipe.add("ingotZinc", new ItemStack(SSItems.zincDust, 1));

		recipe.add("ingotMithril", new ItemStack(SSItems.mithrilDust, 1));

	}

}
