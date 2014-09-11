package shift.sextiarysector.recipe;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;


public class RecipesFurnaceCraft {

	public static void addRecipes(FurnaceCraftingManager p_77608_1_)
    {

    }

	public static void addVanillaRecipes()
    {

		for( Map.Entry<ItemStack, ItemStack> e : ((HashMap<ItemStack,ItemStack>)FurnaceRecipes.smelting().getSmeltingList()).entrySet()){

    		FurnaceCraftingManager.getInstance().addShapelessRecipe(e.getValue(), new Object[]{e.getKey()});

    	}

    }

}
