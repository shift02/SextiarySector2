package shift.sextiarysector.recipe;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class RecipesIceFuel {

    public static void addRecipes(RecipeSimpleFuel recipe) {
        recipe.add(new ItemStack(Items.snowball, 1), 200);
        recipe.add(new ItemStack(Blocks.ice, 1), 1600);
    }

}
