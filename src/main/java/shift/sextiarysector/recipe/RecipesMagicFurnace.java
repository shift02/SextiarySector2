package shift.sextiarysector.recipe;

import net.minecraft.item.ItemStack;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.SSItems;

public class RecipesMagicFurnace {

	public static void addRecipes(RecipeSimpleMachine recipe)
    {
		recipe.add(new ItemStack(SSBlocks.mithrilOre,1),new ItemStack(SSItems.mithrilIngot, 1));
    }

}
