package shift.sextiarysector.recipe;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
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

		p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSItems.emptyBottle, 4),
				new Object[] {
						"x", "x",
						'x', "paneGlassColorless",
				}));

		p_77608_1_.getRecipeList().add(new ShapelessOreRecipe(new ItemStack(SSItems.blueGel, 1),
				new Object[] {
						"dustBluestone",
						"slimeball"
				}));

		p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSItems.smallCloth, 1),
				new Object[] {
						"xx", "xx",
						'x', "craftingString",
				}));

		p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSItems.stringBobbin, 1),
				new Object[] {
						"xxx", "xyx", "xxx",
						'x', Items.string,
						'y', "stickWood",
				}));

		p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSItems.fleshBobbin, 1),
				new Object[] {
						"xxx", "xyx", "xxx",
						'x', SSItems.dryingFlesh,
						'y', "stickWood",
				}));

		p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.woodHopper, 1),
				new Object[] {
						"y y", "yxy", " y ",
						'x', Blocks.chest,
						'y', "plateWood",
				}));

		p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.square, 1),
				new Object[] {
						"x x", "x x", "xxx",
						'x', "plateWood",
				}));

		p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.tank, 1),
				new Object[] {
						"xyx", "y y", "xyx",
						'x', Items.stick,
						'y', "paneGlassColorless",
				}));

		p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.funnel, 1),
				new Object[] {
						"y y", "yxy", " y ",
						'x', SSBlocks.tank,
						'y', "ingotCopper",
				}));

		p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.shippingBox, 1),
				new Object[] {
						"yyy", "yxy", "yyy",
						'x', Blocks.ender_chest,
						'y', "plateWood",
				}));

		p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSItems.rucksack, 1),
				new Object[] {
						"xxx", "x x", "xxx",
						'x', SSItems.cloth,
				}));

		p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSItems.mineboatChest, 1),
				new Object[] {
						"x", "y",
						'x', Blocks.chest,
						'y', Items.boat,
				}));

		p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSItems.mineboatTank, 1),
				new Object[] {
						"x", "y",
						'x', SSBlocks.tank,
						'y', Items.boat,
				}));

		p_77608_1_.getRecipeList().add(new ShapelessOreRecipe(new ItemStack(Items.string, 1),
				new Object[] {
						"craftingSmallCloth",
						"craftingToolKnife"
				}));

		//料理
		p_77608_1_.getRecipeList().add(new ShapelessOreRecipe(new ItemStack(SSItems.riceBall, 1),
				new Object[] {
						"condimentSalt",
						"cookingRice",
						"cookingLaver"
				}));

	}

}
