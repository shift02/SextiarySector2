package shift.sextiarysector.recipe;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import shift.sextiarysector.SSItems;

public class RecipesCore {

	public static void addRecipes(CraftingManager p_77608_1_)
    {

        p_77608_1_.addRecipe(new ItemStack(SSItems.calendar, 1),
        		new Object[] {
        	" y ", "yxy", " y ",
        	'x', Items.redstone,
        	'y', Items.paper,
        	});
    }

}
