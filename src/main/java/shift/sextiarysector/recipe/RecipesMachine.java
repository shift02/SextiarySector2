package shift.sextiarysector.recipe;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
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

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSItems.energyReactor, 2),
				new Object[] { "xxx", "xyx","xxx",
			Character.valueOf('y'), "ingotBluestone",
			Character.valueOf('x'), "ingotYellowstone"
			}));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSItems.objectReactor, 2),
				new Object[] { "xxx", "xyx","xxx",
			Character.valueOf('y'), "ingotNinja",
			Character.valueOf('x'), "ingotYellowstone"
			}));

        Object[] material = new Object[]{"plankWood", "stone", "ingotSteel"};
        Item[] gear = new Item[]{SSItems.woodGear, SSItems.stoneGear, SSItems.steelGear};
        String[] gearOre = new String[]{"gearWood", "gearStone", "gearSteel"};
		Item[] unit = new Item[]{SSItems.woodUnitGear, SSItems.stoneUnitGear, SSItems.steelUnitGear};
		Item[] storage = new Item[]{SSItems.woodGFStorage, SSItems.stoneGFStorage, SSItems.steelGFStorage};
		Block[] shaft = new Block[]{SSBlocks.woodShaft, SSBlocks.stoneShaft, SSBlocks.steelShaft};
		Block[] tank = new Block[]{SSBlocks.woodGFTank, SSBlocks.stoneGFTank, SSBlocks.steelGFTank};
		Block[] box = new Block[]{SSBlocks.woodGearBox, SSBlocks.stoneGearBox, SSBlocks.steelGearBox};

        //GF Block
        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.smallWindmill, 1),
				new Object[] { "xzx", "zyz","xzx",
			Character.valueOf('y'), SSItems.blueStoneSlimeBall,
			Character.valueOf('x'), "plankWood",
			Character.valueOf('z'), Blocks.wool
			}));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSItems.canvas, 1),
				new Object[] { "xxy", "xxy","  y",
			Character.valueOf('y'), Blocks.fence,
			Character.valueOf('x'), SSItems.cloth
			}));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.windmill, 1),
				new Object[] { " x ", "xyx"," x ",
			Character.valueOf('y'), SSItems.blueStoneSlimeBall,
			Character.valueOf('x'), SSItems.canvas
			}));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.smallWaterwheel, 1),
				new Object[] { "xxx", "xyx","xxx",
			Character.valueOf('x'), "plateWood",
			Character.valueOf('y'), SSBlocks.steelShaft
			}));

        for(int i=0;i<3;i++){

        	p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(shaft[i], 4),
    				new Object[] { "xzx", "xyx","xzx",
    			Character.valueOf('y'), SSItems.blueStoneDust,
    			Character.valueOf('x'), material[i],
    			Character.valueOf('z'), SSItems.blueStoneSlimeBall
    			}));

            p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(tank[i], 1),
    				new Object[] { "yay", "xzx","yxy",
    			Character.valueOf('y'), material[i],
    			Character.valueOf('x'), storage[i],
    			Character.valueOf('z'), unit[i],
    			Character.valueOf('a'), gearOre[i]
    			}));

            p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(box[i], 1),
    				new Object[] { "yxy", "xzx","yxy",
    			Character.valueOf('y'), material[i],
    			Character.valueOf('x'), gearOre[i],
    			Character.valueOf('z'), unit[i]
    			}));

        }



        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.millstone, 1),
				new Object[] { "yyy", "aza","yxy",
			Character.valueOf('y'), "plankWood",
			Character.valueOf('x'), SSItems.woodGFStorage,
			Character.valueOf('z'), SSItems.woodUnitGear,
			Character.valueOf('a'), "stone"
			}));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.loom, 1),
				new Object[] { "yyy", "aza","yxy",
			Character.valueOf('y'), "plankWood",
			Character.valueOf('x'), SSItems.woodGFStorage,
			Character.valueOf('z'), SSItems.woodUnitGear,
			Character.valueOf('a'), "stickWood"
			}));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.sawmill, 1),
				new Object[] { "yyy", "aza","yxy",
			Character.valueOf('y'), "plankWood",
			Character.valueOf('x'), SSItems.stoneGFStorage,
			Character.valueOf('z'), SSItems.stoneUnitGear,
			Character.valueOf('a'), "ingotIron"
			}));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.pulverizer, 1),
				new Object[] { "yay", "aza","yxy",
			Character.valueOf('y'), "plankWood",
			Character.valueOf('x'), SSItems.steelGFStorage,
			Character.valueOf('z'), SSItems.steelUnitGear,
			Character.valueOf('a'), "gemDiamond"
			}));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.fan, 1),
				new Object[] { "yay", "zxz","zbz",
			Character.valueOf('y'), "ingotBrass",
			Character.valueOf('x'), "plateWood",
			Character.valueOf('z'), "stone",
			Character.valueOf('a'), Blocks.iron_bars,
			Character.valueOf('b'), SSBlocks.steelShaft
			}));

        //GF Item

		for(int i=0;i<3;i++){

			p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(gear[i], 2),
					new Object[] { " x ", "xyx"," x ",
				Character.valueOf('y'), SSItems.blueStoneDust,
				Character.valueOf('x'), material[i]
				}));



	        p_77608_1_.getRecipeList().add(new ShapelessOreRecipe(new ItemStack(unit[i], 1),
					new Object[] {
				SSItems.unit,
				gearOre[i]
				}));

		}


		for(int i=0;i<3;i++){

			 p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(storage[i], 1),
						new Object[] { " y ", "xzx","xzx",
					Character.valueOf('z'), SSItems.blueStoneDust,
					Character.valueOf('x'), material[i],
					Character.valueOf('y'), gearOre[i],
					}));

		}

    }

}
