package shift.sextiarysector.recipe;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class RecipesFreezer {

    public static void addRecipes(RecipeSimpleMachine recipe) {

        recipe.add("fluidWater", new ItemStack(Blocks.ice));

        recipe.add("fluidIron", new ItemStack(Items.iron_ingot));
        recipe.add("fluidGold", new ItemStack(Items.gold_ingot));

    }

}
