package shift.sextiarysector.recipe;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import shift.sextiarysector.SSItems;


public class RecipesFurnaceCraft {

	public static void addRecipes(FurnaceCraftingManager p_77608_1_)
    {

		//スライム
		p_77608_1_.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.slime_ball, 2),
				new Object[] {
			SSItems.dustWaterLily,
			"craftingSugar",
			Items.water_bucket,
			}));

		p_77608_1_.addRecipe(new ShapelessOreRecipe(new ItemStack(SSItems.blueStoneSlimeBall, 1),
				new Object[] {
			SSItems.blueStoneDust,
			Items.slime_ball
			}));

		p_77608_1_.addRecipe(new ShapedOreRecipe(new ItemStack(SSItems.unit, 1),
				new Object[] { "xxx", "xyx","xxx",
			Character.valueOf('y'), SSItems.blueStoneSlimeBall,
			Character.valueOf('x'), "cobblestone",
			}));

    }

	public static void addVanillaRecipes()
    {

		for( Map.Entry<ItemStack, ItemStack> e : ((HashMap<ItemStack,ItemStack>)FurnaceRecipes.smelting().getSmeltingList()).entrySet()){

    		FurnaceCraftingManager.getInstance().addShapelessRecipe(e.getValue(), new Object[]{e.getKey()});

    	}

    }

}
