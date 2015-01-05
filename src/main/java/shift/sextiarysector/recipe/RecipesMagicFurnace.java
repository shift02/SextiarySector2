package shift.sextiarysector.recipe;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.SSItems;

public class RecipesMagicFurnace {

	public static void addRecipes(RecipeSimpleMachine recipe)
    {
		recipe.add(new ItemStack(SSBlocks.mithrilOre,1),new ItemStack(SSItems.mithrilIngot, 1));
		recipe.add(new ItemStack(Items.slime_ball,1),new ItemStack(Items.ender_pearl, 1));
		recipe.add(new ItemStack(Items.gunpowder,1),new ItemStack(Items.blaze_powder, 1));

		recipe.add("dustBluestone",new ItemStack(SSItems.blueStoneIngot));
		recipe.add("dustYellowstone",new ItemStack(SSItems.yellowStoneIngot));

    }

}
