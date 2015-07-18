package shift.sextiarysector.recipe;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import shift.sextiarysector.SSItems;

public class RecipesSpinningMachine {

	public static void addRecipes(RecipeSimpleMachine recipe)
	{

		//
		//recipe.add("plankWood", new ItemStack(SSBlocks.woodPlate, 4));

		recipe.add("clothSilk", new ItemStack(Items.string, 4));
		recipe.add(new ItemStack(Blocks.wool, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.string, 4));
		recipe.add(new ItemStack(SSItems.stringMass, 1), new ItemStack(SSItems.strongString, 2));

	}

}
