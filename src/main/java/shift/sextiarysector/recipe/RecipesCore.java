package shift.sextiarysector.recipe;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.SSItems;
import shift.sextiarysector.block.BlockMonitor;
import shift.sextiarysector.block.BlockMonitor.MonitorType;

public class RecipesCore {

    public static void addRecipes(CraftingManager p_77608_1_) {

        p_77608_1_.addRecipe(new ItemStack(SSItems.calendar, 1),
                new Object[] {
                        " y ", "yxy", " y ",
                        'x', Items.redstone,
                        'y', Items.paper,
                });

        //        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSItems.emptyBottle, 4),
        //                new Object[] {
        //                        "x", "x",
        //                        'x', "paneGlassColorless",
        //                }));

        p_77608_1_.getRecipeList().add(new ShapelessOreRecipe(new ItemStack(SSItems.redGel, 1),
                new Object[] {
                        "dustRedstone",
                        "slimeball"
                }));

        p_77608_1_.getRecipeList().add(new ShapelessOreRecipe(new ItemStack(SSItems.blueGel, 1),
                new Object[] {
                        "dustBluestone",
                        "slimeball"
                }));

        p_77608_1_.getRecipeList().add(new ShapelessOreRecipe(new ItemStack(SSItems.yellowGel, 1),
                new Object[] {
                        "dustYellowstone",
                        "slimeball"
                }));

        //鉱石 インゴット--ブロック
        Object[] oIngot = new Object[] { "ingotCopper", "ingotZinc", "ingotSilver", "ingotSteel", "ingotBrass", "ingotNinja", "ingotMithril", "gemOrichalcum" };
        Block[] block = new Block[] { SSBlocks.copperBlock, SSBlocks.zincBlock, SSBlocks.silverBlock, SSBlocks.steelBlock, SSBlocks.brassBlock,
                SSBlocks.ninjaBlock, SSBlocks.mithrilBlock, SSBlocks.orichalcumBlock };
        for (int i = 0; i < oIngot.length; i++) {
            p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(block[i], 1),
                    new Object[] { "xxx", "xxx", "xxx",
                            Character.valueOf('x'), oIngot[i],
                    }));
        }

        Object[] oBlock = new Object[] { "blockCopper", "blockZinc", "blockSilver", "blockSteel", "blockBrass", "blockNinja", "blockMithril",
                "blockOrichalcum" };
        Item[] ingot = new Item[] { SSItems.copperIngot, SSItems.zincIngot, SSItems.silverIngot, SSItems.steelIngot, SSItems.brassIngot, SSItems.ninjaIngot,
                SSItems.mithrilIngot, SSItems.orichalcumGem };
        for (int i = 0; i < oBlock.length; i++) {
            p_77608_1_.getRecipeList().add(new ShapelessOreRecipe(new ItemStack(ingot[i], 9),
                    new Object[] {
                            oBlock[i]
                    }));
        }

        //鉱石  ナゲット--インゴット
        Object[] oNugget = new Object[] { "nuggetIron", "nuggetCopper", "nuggetZinc", "nuggetSilver", "nuggetSteel", "nuggetNinja" };
        ingot = new Item[] { Items.iron_ingot, SSItems.copperIngot, SSItems.zincIngot, SSItems.silverIngot, SSItems.steelIngot, SSItems.ninjaIngot };
        for (int i = 0; i < oNugget.length; i++) {
            p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(ingot[i], 1),
                    new Object[] { "xxx", "xxx", "xxx",
                            Character.valueOf('x'), oNugget[i],
                    }));
        }

        oIngot = new Object[] { "ingotIron", "ingotCopper", "ingotZinc", "ingotSilver", "ingotSteel", "ingotNinja" };
        Item[] nugget = new Item[] { SSItems.ironNugget, SSItems.copperNugget, SSItems.zincNugget, SSItems.silverNugget, SSItems.steelNugget,
                SSItems.ninjaNugget };
        for (int i = 0; i < oIngot.length; i++) {
            p_77608_1_.getRecipeList().add(new ShapelessOreRecipe(new ItemStack(nugget[i], 9),
                    new Object[] {
                            oIngot[i]
                    }));
        }

        //その他
        oNugget = new Object[] { "nuggetObsidian" };
        Block[] other = new Block[] { Blocks.obsidian };
        for (int i = 0; i < oNugget.length; i++) {
            p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(other[i], 1),
                    new Object[] { "xxx", "xxx", "xxx",
                            Character.valueOf('x'), oNugget[i],
                    }));
        }

        /*
        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSItems.smallCloth, 1),
        		new Object[] {
        				"xx", "xx",
        				'x', "craftingString",
        		}));*/

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.leafBlock, 1),
                new Object[] {
                        "xxx", "xxx", "xxx",
                        'x', SSItems.leaf
                }));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSItems.leafBed, 1),
                new Object[] {
                        "xxx", "yyy",
                        'x', SSBlocks.leafBlock,
                        'y', "plankWood",
                }));

        p_77608_1_.getRecipeList().add(new ShapelessOreRecipe(new ItemStack(SSItems.glaze, 8),
                new Object[] {
                        "slimeball",
                        "dustIron",
                        "dustAsh"
                }));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.kawara, 1),
                new Object[] {
                        "xx", "xx",
                        'x', SSItems.kawara
                }));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.animalOil, 1),
                new Object[] {
                        "xxx", "xxx", "xxx",
                        'x', SSItems.animalOil
                }));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.motor, 1),
                new Object[] {
                        "xxx", "azy", "yyy",
                        'x', "stone",
                        'y', "plankWood",
                        'z', "ingotIron",
                        'a', "dustBluestone"
                }));

        p_77608_1_.getRecipeList().add(new ShapelessOreRecipe(new ItemStack(SSBlocks.stickyMotor, 1),
                new Object[] {
                        SSBlocks.motor,
                        "slimeball"
                }));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSItems.silkBobbin, 1),
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

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSItems.stringMass, 1),
                new Object[] {
                        "xxx", "xxx", "xxx",
                        'x', Items.string,
                }));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSItems.strongStringBobbin, 1),
                new Object[] {
                        "xxx", "xyx", "xxx",
                        'x', SSItems.strongString,
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

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.gutter, 4),
                new Object[] {
                        "xxx",
                        'x', "plateWood"
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
                        'x', SSItems.silkCloth
                }));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSItems.quiver, 1),
                new Object[] {
                        "xy ", "xy ", " y ",
                        'x', SSItems.strongString,
                        'y', Items.leather
                }));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSItems.oxygenTank, 1),
                new Object[] {
                        "x x", "xyx", "x x",
                        'x', SSItems.strongString,
                        'y', SSBlocks.tank,
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

        p_77608_1_.getRecipeList().add(new ShapelessOreRecipe(new ItemStack(SSBlocks.shopMonitor, 1),
                new Object[] {
                        BlockMonitor.getMonitor(MonitorType.creeper)
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
