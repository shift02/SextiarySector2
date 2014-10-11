package shift.sextiarysector.recipe;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;
import shift.sextiarysector.SSItems;

public class RecipesTool {

	public static void addRecipes(CraftingManager p_77608_1_)
    {

		//Scoop
		Object[] material = new Object[]{"plankWood","cobblestone","ingotIron","ingotGold","gemDiamond"};
		Item[] scoop = new Item[]{SSItems.woodScoop,SSItems.stoneScoop,SSItems.ironScoop,SSItems.goldScoop,SSItems.diamondScoop};
		for(int i = 0;i<material.length;i++){
			 p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(scoop[i], 1),
						new Object[] { "y", "x",
					Character.valueOf('y'), material[i],
					Character.valueOf('x'), "stickWood",
					}));
		}

    }

}
