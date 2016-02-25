package shift.sextiarysector.recipe;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import shift.sextiarysector.SSItems;
import shift.sextiarysector.item.ItemSeed;

public class RecipesTimeMachine {

    public static void addRecipes(RecipeSimpleMachine recipe) {

        recipe.add(new ItemStack(SSItems.turnip), ItemSeed.getSeedItemStack("turnip", 2));
        recipe.add(new ItemStack(SSItems.cucumber), ItemSeed.getSeedItemStack("cucumber", 2));
        recipe.add(new ItemStack(SSItems.ironTurnip), ItemSeed.getSeedItemStack("iron_turnip", 2));

        recipe.add(new ItemStack(SSItems.onion), ItemSeed.getSeedItemStack("onion", 2));
        recipe.add(new ItemStack(SSItems.tomato), ItemSeed.getSeedItemStack("tomato", 2));
        recipe.add(new ItemStack(SSItems.corn), ItemSeed.getSeedItemStack("corn", 2));
        recipe.add(new ItemStack(SSItems.goldenCorn), ItemSeed.getSeedItemStack("golden_corn", 2));

        recipe.add(new ItemStack(SSItems.eggplant), ItemSeed.getSeedItemStack("eggplant", 2));
        recipe.add(new ItemStack(SSItems.sweetPotato), ItemSeed.getSeedItemStack("sweet_potato", 2));
        recipe.add(new ItemStack(SSItems.greenPepper), ItemSeed.getSeedItemStack("green_pepper", 2));

        recipe.add(new ItemStack(SSItems.radish), ItemSeed.getSeedItemStack("radish", 2));

        recipe.add(new ItemStack(SSItems.rice), ItemSeed.getSeedItemStack("rice", 2));

        recipe.add(new ItemStack(Blocks.red_mushroom), ItemSeed.getSeedItemStack("mushroom_red", 2));

        recipe.add(new ItemStack(SSItems.attackRustUnit), new ItemStack(SSItems.attackUnit, 1));
        recipe.add(new ItemStack(SSItems.defenseRustUnit), new ItemStack(SSItems.defenseUnit, 1));

    }

}
