package shift.sextiarysector.recipe;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import shift.sextiarysector.SSBlocks;

public class RecipesNormalBlock {

	public static void addRecipes(CraftingManager p_77608_1_)
    {

        p_77608_1_.addRecipe(new ItemStack(SSBlocks.woodGrate, 2),
        		new Object[] {
        	"xxx", "xxx", "xxx",
        	'x', Items.stick,
        	});

    }

}
