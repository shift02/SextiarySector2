package shift.sextiarysector.recipe;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;
import shift.sextiarysector.SSBlocks;
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

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSItems.stringBobbin, 1),
        		new Object[] {
        	"xxx", "xyx", "xxx",
        	'x', Items.string,
        	'y', "stickWood",
        	}));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.woodHopper, 1),
        		new Object[] {
        	"y y", "yxy", " y ",
        	'x', Blocks.chest,
        	'y', "plateWood",
        	}));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSItems.mineboatChest, 1),
        		new Object[] {
        	"x", "y",
        	'x', Blocks.chest,
        	'y', Items.boat,
        	}));

    }

}
