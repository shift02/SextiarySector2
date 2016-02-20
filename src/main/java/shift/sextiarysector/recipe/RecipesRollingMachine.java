package shift.sextiarysector.recipe;

import net.minecraft.item.ItemStack;
import shift.sextiarysector.SSBlocks;

public class RecipesRollingMachine {

    public static void addRecipes(RecipeSimpleMachine recipe) {

        recipe.add("ingotIron", new ItemStack(SSBlocks.ironPlate, 1));
        recipe.add("ingotGold", new ItemStack(SSBlocks.goldPlate, 1));

        recipe.add("ingotCopper", new ItemStack(SSBlocks.copperPlate, 1));
        recipe.add("ingotZinc", new ItemStack(SSBlocks.zincPlate, 1));
        recipe.add("ingotSilver", new ItemStack(SSBlocks.silverPlate, 1));

    }

}
