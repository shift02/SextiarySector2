package shift.sextiarysector.recipe;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.SSItems;

public class RecipesTimeMachine {

    public static void addRecipes(RecipeSimpleMachine recipe) {

        recipe.add(new ItemStack(SSItems.turnip), new ItemStack(SSBlocks.turnip, 1));
        recipe.add(new ItemStack(SSItems.cucumber), new ItemStack(SSBlocks.cucumber, 1));
        recipe.add(new ItemStack(SSItems.ironTurnip), new ItemStack(SSBlocks.ironTurnip, 1));

        recipe.add(new ItemStack(SSItems.onion), new ItemStack(SSBlocks.onion, 1));
        recipe.add(new ItemStack(SSItems.tomato), new ItemStack(SSBlocks.tomato, 1));
        recipe.add(new ItemStack(SSItems.corn), new ItemStack(SSBlocks.corn, 1));
        recipe.add(new ItemStack(SSItems.goldenCorn), new ItemStack(SSBlocks.goldenCorn, 1));

        recipe.add(new ItemStack(SSItems.eggplant), new ItemStack(SSBlocks.eggplant, 1));
        recipe.add(new ItemStack(SSItems.sweetPotato), new ItemStack(SSBlocks.sweetPotato, 1));
        recipe.add(new ItemStack(SSItems.greenPepper), new ItemStack(SSBlocks.greenPepper, 1));

        recipe.add(new ItemStack(SSItems.radish), new ItemStack(SSBlocks.radish, 1));

        recipe.add(new ItemStack(SSItems.rice), new ItemStack(SSBlocks.rice, 1));

        recipe.add(new ItemStack(Blocks.red_mushroom), new ItemStack(SSBlocks.redMushroom, 1));

        recipe.add(new ItemStack(SSItems.attackRustUnit), new ItemStack(SSItems.attackUnit, 1));
        recipe.add(new ItemStack(SSItems.defenseRustUnit), new ItemStack(SSItems.defenseUnit, 1));

    }

}
