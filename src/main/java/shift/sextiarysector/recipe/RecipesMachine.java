package shift.sextiarysector.recipe;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.SSItems;

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

        //GF Block
        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.smallWindmill, 1),
				new Object[] { "xzx", "zyz","xzx",
			Character.valueOf('y'), SSItems.blueStoneSlimeBall,
			Character.valueOf('x'), "plankWood",
			Character.valueOf('z'), Blocks.wool
			}));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.woodShaft, 4),
				new Object[] { "xzx", "xyx","xzx",
			Character.valueOf('y'), SSItems.blueStoneDust,
			Character.valueOf('x'), "plankWood",
			Character.valueOf('z'), SSItems.blueStoneSlimeBall
			}));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.woodGFTank, 1),
				new Object[] { "yay", "xzx","yxy",
			Character.valueOf('y'), "plankWood",
			Character.valueOf('x'), SSItems.woodGFStorage,
			Character.valueOf('z'), SSItems.woodUnitGear,
			Character.valueOf('a'), SSItems.woodGear
			}));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.woodGearBox, 1),
				new Object[] { "yxy", "xzx","yxy",
			Character.valueOf('y'), "plankWood",
			Character.valueOf('x'), SSItems.woodGear,
			Character.valueOf('z'), SSItems.woodUnitGear
			}));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.millstone, 1),
				new Object[] { "yyy", "aza","yxy",
			Character.valueOf('y'), "plankWood",
			Character.valueOf('x'), SSItems.woodGFStorage,
			Character.valueOf('z'), SSItems.woodUnitGear,
			Character.valueOf('a'), "stone"
			}));

        //GF Item
        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSItems.woodGear, 2),
				new Object[] { " x ", "xyx"," x ",
			Character.valueOf('y'), SSItems.blueStoneDust,
			Character.valueOf('x'), "plankWood"
			}));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSItems.woodGFStorage, 1),
				new Object[] { " y ", "xzx","xzx",
			Character.valueOf('z'), SSItems.blueStoneDust,
			Character.valueOf('x'), "plankWood",
			Character.valueOf('y'), SSItems.woodGear,
			}));

        p_77608_1_.getRecipeList().add(new ShapelessOreRecipe(new ItemStack(SSItems.woodUnitGear, 1),
				new Object[] {
			SSItems.unit,
			SSItems.woodGear
			}));

    }

}
