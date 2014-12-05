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

		//Knife
		Item[] knife = new Item[]{SSItems.woodKnife,SSItems.stoneKnife,SSItems.ironKnife,SSItems.goldKnife,SSItems.diamondKnife};
		for(int i = 0;i<material.length;i++){
			 p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(knife[i], 1),
						new Object[] { " y","x ",
					Character.valueOf('y'), material[i],
					Character.valueOf('x'), "stickWood",
					}));
		}


		Item[] wateringCan = new Item[]{SSItems.woodWateringCan};
		for(int i = 0;i<wateringCan.length;i++){

			 p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(wateringCan[i], 1,(wateringCan[i].getMaxDamage()-1)),
						new Object[] { "yyy", " yy",
					Character.valueOf('y'), material[i],
					}));
		}

    }

}
