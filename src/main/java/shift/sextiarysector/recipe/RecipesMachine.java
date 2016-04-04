package shift.sextiarysector.recipe;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.SSItems;

public class RecipesMachine {

    public static void addRecipes(CraftingManager p_77608_1_) {

        p_77608_1_.addRecipe(new ItemStack(SSBlocks.LargeFurnace, 1),
                new Object[] {
                        "xyx", "xax", "xzx",
                        'x', Blocks.stone,
                        'y', Blocks.crafting_table,
                        'z', Blocks.chest,
                        'a', Blocks.furnace
                });

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSItems.craftReactor, 1),
                new Object[] { "xyx", "yzy", "xyx",
                        Character.valueOf('y'), "ingotSilver",
                        Character.valueOf('x'), "nuggetGold",
                        Character.valueOf('z'), "gelRedstone"
                }));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSItems.energyReactor, 1),
                new Object[] { "xyx", "yzy", "xyx",
                        Character.valueOf('y'), "ingotSilver",
                        Character.valueOf('x'), "nuggetCopper",
                        Character.valueOf('z'), "gelBluestone"
                }));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSItems.objectReactor, 1),
                new Object[] { "xyx", "yzy", "xyx",
                        Character.valueOf('y'), "ingotSilver",
                        Character.valueOf('x'), "nuggetNinja",
                        Character.valueOf('z'), "gelYellowstone"
                }));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSItems.emptyBottle, 1),
                new Object[] { "x",
                        Character.valueOf('x'), new ItemStack(SSItems.colorSpray, 1, OreDictionary.WILDCARD_VALUE)
                }));

        Object[] material = new Object[] { "plankWood", "stone", "ingotSteel", "ingotNinja", "gemOrichalcum" };
        Item[] gear = new Item[] { SSItems.woodGear, SSItems.stoneGear, SSItems.steelGear, SSItems.ninjaGear, SSItems.orichalcumGear };
        String[] gearOre = new String[] { "gearWood", "gearStone", "gearSteel", "gearNinja", "gearOrichalcum" };
        //Item[] unit = new Item[] { SSItems.woodUnitGear, SSItems.stoneUnitGear, SSItems.steelUnitGear, SSItems.ninjaUnitGear, SSItems.orichalcumUnitGear };
        Item[] storage = new Item[] { SSItems.woodGFStorage, SSItems.stoneGFStorage, SSItems.steelGFStorage, SSItems.ninjaGFStorage, SSItems.orichalcumGFStorage };
        Block[] shaft = new Block[] { SSBlocks.woodShaft, SSBlocks.stoneShaft, SSBlocks.steelShaft, SSBlocks.ninjaShaft, SSBlocks.orichalcumShaft };
        Block[] tank = new Block[] { SSBlocks.woodGFTank, SSBlocks.stoneGFTank, SSBlocks.steelGFTank, SSBlocks.ninjaGFTank, SSBlocks.orichalcumGFTank };
        Block[] box = new Block[] { SSBlocks.woodGearBox, SSBlocks.stoneGearBox, SSBlocks.steelGearBox, SSBlocks.ninjaGearBox, SSBlocks.orichalcumGearBox };

        //GF Block
        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.smallWindmill, 1),
                new Object[] { "xzx", "zyz", "xzx",
                        Character.valueOf('y'), SSBlocks.woodShaft,
                        Character.valueOf('x'), "plankWood",
                        Character.valueOf('z'), "blockWool"
                }));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSItems.canvas, 1),
                new Object[] { "xxy", "xxy", "  y",
                        Character.valueOf('y'), Blocks.fence,
                        Character.valueOf('x'), "itemCloth"
                }));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.windmill, 1),
                new Object[] { " x ", "xyx", " x ",
                        Character.valueOf('y'), SSBlocks.stoneShaft,
                        Character.valueOf('x'), SSItems.canvas
                }));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.smallWaterwheel, 1),
                new Object[] { "xxx", "xyx", "xxx",
                        Character.valueOf('x'), "plateWood",
                        Character.valueOf('y'), SSBlocks.stoneShaft
                }));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.steamMotor, 1),
                new Object[] { "xzx", "xyx", "pbp",
                        Character.valueOf('x'), "paneGlassColorless",
                        Character.valueOf('y'), SSItems.energyReactor,
                        Character.valueOf('z'), SSBlocks.steelShaft,
                        Character.valueOf('b'), new ItemStack(SSItems.dustWaterLily, 1, 0),
                        Character.valueOf('p'), "ingotSteel"
                }));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSItems.strongCanvas, 1),
                new Object[] { "xxy", "xxy", "  y",
                        Character.valueOf('y'), "plankWood",
                        Character.valueOf('x'), SSItems.strongCloth
                }));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.largeWindmill, 1),
                new Object[] { " x ", "xyx", " x ",
                        Character.valueOf('y'), SSBlocks.steelShaft,
                        Character.valueOf('x'), SSItems.strongCanvas
                }));

        for (int i = 0; i < 5; i++) {

            p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(shaft[i], 4),
                    new Object[] { "xzx", "xyx", "xzx",
                            Character.valueOf('y'), "dustBluestone",
                            Character.valueOf('x'), material[i],
                            Character.valueOf('z'), "gelBluestone"
                    }));

            p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(tank[i], 1),
                    new Object[] { "yay", "xzx", "yxy",
                            Character.valueOf('y'), material[i],
                            Character.valueOf('x'), storage[i],
                            Character.valueOf('z'), "gelBluestone", //unit[i],
                            Character.valueOf('a'), gearOre[i]
                    }));

            p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(box[i], 1),
                    new Object[] { "yxy", "xzx", "yxy",
                            Character.valueOf('y'), material[i],
                            Character.valueOf('x'), gearOre[i],
                            Character.valueOf('z'), "gelBluestone",//unit[i]
                    }));

        }

        Block[] gearShaft = new Block[] { SSBlocks.woodStoneGearShaft, SSBlocks.stoneSteelGearShaft, SSBlocks.steelNinjaGearShaft, SSBlocks.ninjaOrichalcumGearShaft };

        for (int i = 0; i < 4; i++) {

            p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(gearShaft[i], 1, 1),
                    new Object[] { "y", "z", "x",
                            Character.valueOf('y'), shaft[i],
                            Character.valueOf('x'), shaft[i + 1],
                            Character.valueOf('z'), SSItems.energyReactor
                    }));

            p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(gearShaft[i], 1, 0),
                    new Object[] { "y", "z", "x",
                            Character.valueOf('y'), shaft[i + 1],
                            Character.valueOf('x'), shaft[i],
                            Character.valueOf('z'), SSItems.energyReactor
                    }));

        }

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.millstone, 1),
                new Object[] { "yay", "aza", "yxy",
                        Character.valueOf('y'), "gearWood",
                        Character.valueOf('x'), SSItems.woodGFStorage,
                        Character.valueOf('z'), SSBlocks.motor,
                        Character.valueOf('a'), "stone"
                }));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.loom, 1),
                new Object[] { "y y", "aza", "yxy",
                        Character.valueOf('y'), "gearWood",
                        Character.valueOf('x'), SSItems.woodGFStorage,
                        Character.valueOf('z'), SSBlocks.motor,
                        Character.valueOf('a'), "stickWood"
                }));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.sawmill, 1),
                new Object[] { "y y", "aza", "yxy",
                        Character.valueOf('y'), "gearStone",
                        Character.valueOf('x'), SSItems.stoneGFStorage,
                        Character.valueOf('z'), SSBlocks.motor,
                        Character.valueOf('a'), "ingotZinc"
                }));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.spinningMachine, 1),
                new Object[] { "yby", "aza", "yxy",
                        Character.valueOf('y'), "gearStone",
                        Character.valueOf('x'), SSItems.stoneGFStorage,
                        Character.valueOf('z'), SSBlocks.motor,
                        Character.valueOf('a'), "ingotSilver",
                        Character.valueOf('b'), "stickWood"
                }));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.pump, 1),
                new Object[] { "yxy", "azx", "yxy",
                        Character.valueOf('x'), "plateWood",
                        Character.valueOf('y'), "gearStone",
                        Character.valueOf('z'), SSBlocks.tank,
                        Character.valueOf('a'), SSBlocks.woodGrate,
                }));

        //水補給機
        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.waterSupplyMachine, 1),
                new Object[] { "yxy", "aza", "yxy",
                        Character.valueOf('x'), "plateWood",
                        Character.valueOf('y'), "plankWood",
                        Character.valueOf('z'), SSBlocks.tank,
                        Character.valueOf('a'), SSBlocks.woodGrate,
                }));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.suctionMachine, 1),
                new Object[] { "yay", "xzx", "yay",
                        Character.valueOf('x'), "plateWood",
                        Character.valueOf('y'), "gearStone",
                        Character.valueOf('z'), SSBlocks.tank,
                        Character.valueOf('a'), Blocks.iron_bars
                }));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.pulverizer, 1),
                new Object[] { "yay", "aza", "yxy",
                        Character.valueOf('y'), "gearSteel",
                        Character.valueOf('x'), SSItems.steelGFStorage,
                        Character.valueOf('z'), SSBlocks.motor,
                        Character.valueOf('a'), "gemDiamond"
                }));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.extractor, 1),
                new Object[] { "yby", "aza", "yxy",
                        Character.valueOf('y'), "gearSteel",
                        Character.valueOf('x'), SSItems.steelGFStorage,
                        Character.valueOf('z'), SSBlocks.motor,
                        Character.valueOf('a'), "containerBottle",
                        Character.valueOf('b'), SSBlocks.tank
                }));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.fan, 1),
                new Object[] { "yay", "zxz", "zbz",
                        Character.valueOf('y'), "ingotBrass",
                        Character.valueOf('x'), "plateWood",
                        Character.valueOf('z'), "stone",
                        Character.valueOf('a'), Blocks.iron_bars,
                        Character.valueOf('b'), SSBlocks.steelShaft
                }));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.saw, 1),
                new Object[] { "yay", "zxz", "zbz",
                        Character.valueOf('y'), "ingotBrass",
                        Character.valueOf('x'), "plateIron",
                        Character.valueOf('z'), "stone",
                        Character.valueOf('a'), "dustDiamond",
                        Character.valueOf('b'), SSBlocks.steelShaft
                }));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.rollingMachine, 1),
                new Object[] { "yay", "aza", "yxy",
                        Character.valueOf('y'), "gearNinja",
                        Character.valueOf('x'), SSItems.ninjaGFStorage,
                        Character.valueOf('z'), SSBlocks.motor,
                        Character.valueOf('a'), "ingotIron"
                }));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(SSBlocks.timeMachine, 1),
                new Object[] { "yby", "aza", "yxy",
                        Character.valueOf('y'), "gearOrichalcum",
                        Character.valueOf('x'), SSItems.orichalcumGFStorage,
                        Character.valueOf('z'), SSBlocks.motor,
                        Character.valueOf('a'), SSBlocks.chunkLoader,
                        Character.valueOf('b'), "ingotMithril"
                }));

        //GF Item

        for (int i = 0; i < 5; i++) {

            p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(gear[i], 2),
                    new Object[] { " x ", "xyx", " x ",
                            Character.valueOf('y'), "dustBluestone",
                            Character.valueOf('x'), material[i]
                    }));

            //                    p_77608_1_.getRecipeList().add(new ShapelessOreRecipe(new ItemStack(unit[i], 1),
            //                            new Object[] {
            //                                    SSItems.unit,
            //                                    gearOre[i]
            //                            }));

        }

        for (int i = 0; i < 5; i++) {

            p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(storage[i], 1),
                    new Object[] { " y ", "xzx", "xzx",
                            Character.valueOf('z'), "dustBluestone",
                            Character.valueOf('x'), material[i],
                            Character.valueOf('y'), gearOre[i],
                    }));

        }

    }

}
