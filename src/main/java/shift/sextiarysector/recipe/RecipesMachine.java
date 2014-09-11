package shift.sextiarysector.recipe;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import shift.sextiarysector.SSBlocks;

public class RecipesMachine {

	public static void addRecipes(CraftingManager p_77608_1_)
    {

        p_77608_1_.addRecipe(new ItemStack(SSBlocks.LargeFurnace, 1),
        		new Object[] {
        	"xyx", "xax", "xzx",
        	'x', Blocks.stone,
        	'y', Blocks.crafting_table,
        	'z', Blocks.chest,
        	'a', Blocks.furnace
        	});

    }

}
