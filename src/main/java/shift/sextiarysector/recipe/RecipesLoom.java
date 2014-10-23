package shift.sextiarysector.recipe;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class RecipesLoom {

	public static void addRecipes(RecipeSimpleMachine recipe)
    {

		//バニラ
		recipe.add(new ItemStack(Blocks.bedrock,1),new ItemStack(Items.dye, 4, 1));


    }

}
