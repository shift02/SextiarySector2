package shift.sextiarysector.recipe;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import shift.sextiarysector.SSItems;

public class RecipesLoom {

    public static void addRecipes(RecipeSimpleMachine recipe) {

        //風車
        recipe.add(new ItemStack(SSItems.silkBobbin, 1), new ItemStack(SSItems.silkCloth, 1));
        recipe.add(new ItemStack(SSItems.strongStringBobbin, 1), new ItemStack(SSItems.strongCloth, 1));
        recipe.add(new ItemStack(SSItems.fleshBobbin, 1), new ItemStack(Items.leather, 1));
        recipe.add(new ItemStack(Items.reeds, 1), new ItemStack(Items.paper, 2));
        //recipe.add(new ItemStack(Items,1),new ItemStack(SSItems.cloth, 1));

    }

}
