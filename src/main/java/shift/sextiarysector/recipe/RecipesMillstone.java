package shift.sextiarysector.recipe;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import shift.sextiarysector.SSItems;

public class RecipesMillstone {

	public static void addRecipes(RecipeSimpleMachine recipe)
	{

		//バニラ
		recipe.add(new ItemStack(Blocks.red_flower, 1), new ItemStack(Items.dye, 4, 1));
		recipe.add(new ItemStack(Blocks.yellow_flower, 1), new ItemStack(Items.dye, 4, 11));
		recipe.add(new ItemStack(Blocks.gravel, 1), new ItemStack(Items.flint, 4));
		recipe.add(new ItemStack(Items.reeds, 1), new ItemStack(Items.sugar, 4));
		recipe.add(new ItemStack(Items.bone, 1), new ItemStack(Items.dye, 4, 15));

		//Mod
		recipe.add(new ItemStack(Blocks.waterlily, 1), new ItemStack(SSItems.dustWaterLily, 4));

		recipe.add("cropRice", new ItemStack(SSItems.whiteRice, 2));

	}

}
