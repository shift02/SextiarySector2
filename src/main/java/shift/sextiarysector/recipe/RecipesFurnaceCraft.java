package shift.sextiarysector.recipe;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.SSItems;

public class RecipesFurnaceCraft {

    public static void addRecipes(FurnaceCraftingManager p_77608_1_) {

        //飲み物
        //p_77608_1_.addRecipe(new ShapelessOreRecipe(new ItemStack(SSItems.drinkingWaterBottle, 1),
        //		new Object[] {
        //	new ItemStack(Items.potionitem),
        //	}));

        //スライム
        p_77608_1_.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.slime_ball, 1),
                new Object[] {
                        SSItems.dustWaterLily,
                        "condimentSugar",
                        "fluidWater",
                }));

        p_77608_1_.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.slime_ball, 1),
                new Object[] {
                        "dyeGreen",
                        "fluidSap",
                }));

        //石鹸
        p_77608_1_.addRecipe(new ShapelessOreRecipe(new ItemStack(SSItems.soap, 1),
                new Object[] {
                        "itemAsh",
                        SSItems.animalOil,
                        SSItems.animalOil
                }));

        /*
        p_77608_1_.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.slime_ball, 2),
        		new Object[] {
        				"condimentSugar",
        				"condimentSugar",
        				"condimentSalt",
        				"fluidSap",
        		}));
        
        p_77608_1_.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.slime_ball, 2),
        		new Object[] {
        				"algaLaver",
        				"algaLaver",
        				"condimentSalt",
        				"fluidWater",
        		}));
        */

        //p_77608_1_.addRecipe(new ShapelessOreRecipe(new ItemStack(SSItems.drinkingWaterBottle, 2),
        //		new Object[] {
        //				SSItems.emptyBottle,
        //				SSItems.emptyBottle,
        //				SSItems.waterBottle,
        //		}));

        p_77608_1_.addRecipe(new ShapelessOreRecipe(new ItemStack(SSItems.chocolate, 1),
                new Object[] {
                        "condimentSugar",
                        "condimentSugar",
                        "condimentSugar",
                        "condimentCocoa",
                }));

        p_77608_1_.addRecipe(new ShapelessOreRecipe(new ItemStack(SSItems.redGel, 2),
                new Object[] {
                        "dustRedstone",
                        "slimeball"
                }));

        p_77608_1_.addRecipe(new ShapelessOreRecipe(new ItemStack(SSItems.blueGel, 2),
                new Object[] {
                        "dustBluestone",
                        "slimeball"
                }));

        p_77608_1_.addRecipe(new ShapelessOreRecipe(new ItemStack(SSItems.yellowGel, 2),
                new Object[] {
                        "dustYellowstone",
                        "slimeball"
                }));

        p_77608_1_.addRecipe(new ShapelessOreRecipe(new ItemStack(SSItems.steelIngot, 1),
                new Object[] {
                        "dustCoal",
                        "dustIron"
                }));

        p_77608_1_.addRecipe(new ShapelessOreRecipe(new ItemStack(SSItems.brassIngot, 2),
                new Object[] {
                        "dustCopper",
                        "dustCopper",
                        "dustZinc"
                }));

        p_77608_1_.addRecipe(new ShapelessOreRecipe(new ItemStack(SSItems.ninjaIngot, 1),
                new Object[] {
                        "dustMithril",
                        "dustDiamond"
                }));

        //瓦
        p_77608_1_.addRecipe(new ShapelessOreRecipe(new ItemStack(SSItems.kawara, 1),
                new Object[] {
                        Items.clay_ball,
                        SSItems.glaze
                }));

        /*
        p_77608_1_.addRecipe(new ShapedOreRecipe(new ItemStack(SSItems.unit, 1),
        		new Object[] { "xxx", "xyx", "xxx",
        				Character.valueOf('y'), SSItems.blueGel,
        				Character.valueOf('x'), "cobblestone",
        		}));
        		*/

        //p_77608_1_.addRecipe(new ShapedOreRecipe(new ItemStack(SSBlocks.machineFrame, 1),
        //        new Object[] { "xxx", "xyx", "xxx",
        //                Character.valueOf('y'), "gelBluestone",
        //                Character.valueOf('x'), "cobblestone",
        //        }));

        //        p_77608_1_.addRecipe(new ShapedOreRecipe(new ItemStack(SSItems.emptyBottle, 8),
        //                new Object[] {
        //                        "x", "x",
        //                        'x', "paneGlassColorless",
        //                }));

        p_77608_1_.addRecipe(new ShapedOreRecipe(new ItemStack(SSItems.orichalcumGem, 1),
                new Object[] {
                        "xyb", "aza", "byx",
                        'x', "ingotBrass",
                        'y', "ingotSilver",
                        'z', "craftingMagic",
                        'a', "ingotMithril",
                        'b', "ingotGold"
                }));

        //Hammer
        //p_77608_1_.addRecipe(new ShapedOreRecipe(new ItemStack(SSItems.ironSpanner, 1),
        //		new Object[] { "xxx", " y ", " y ",
        //				Character.valueOf('x'), "ingotIron",
        //				Character.valueOf('y'), "stickWood",
        //		}));

        //鉄のリング
        p_77608_1_.addRecipe(new ShapedOreRecipe(new ItemStack(SSItems.ironRing, 1),
                new Object[] { " x ", "x x", " x ",
                        Character.valueOf('x'), "ingotIron",
                }));

        //クリーパーリング
        //p_77608_1_.addRecipe(new ShapelessOreRecipe(new ItemStack(SSItems.creeperRing, 1),
        //         new Object[] {
        //                "ringIron",
        //                SSItems.objectReactor,
        //               BlockMonitor.getMonitor(MonitorType.creeper)
        //       }));

        //MPRing
        p_77608_1_.addRecipe(new ShapelessOreRecipe(new ItemStack(SSItems.mpRing, 1),
                new Object[] {
                        "ringIron",
                        SSItems.magicDust,
                        "dustGold"
                }));

        //XPRing
        p_77608_1_.addRecipe(new ShapelessOreRecipe(new ItemStack(SSItems.xpRing, 1),
                new Object[] {
                        "ringIron",
                        SSItems.magicDust,
                        new ItemStack(Items.dye, 1, 4)
                }));

        //液体カマド
        p_77608_1_.addRecipe(new ShapedOreRecipe(new ItemStack(SSBlocks.fluidFurnace, 1),
                new Object[] { "xxx", "xyx", "xxx",
                        Character.valueOf('x'), "cobblestone",
                        Character.valueOf('y'), Blocks.glass_pane,
                }));

        //魔法カマド
        p_77608_1_.addRecipe(new ShapedOreRecipe(new ItemStack(SSBlocks.magicFurnace, 1),
                new Object[] { "xxx", "xyx", "xxx",
                        Character.valueOf('x'), "cobblestone",
                        Character.valueOf('y'), SSItems.magicDust,
                }));

        //フリーザー
        p_77608_1_.addRecipe(new ShapelessOreRecipe(new ItemStack(SSBlocks.freezer, 1),
                new Object[] {
                        SSItems.objectReactor,
                        Blocks.ice,
                        Blocks.furnace,
                }));

        //乾燥機
        p_77608_1_.addRecipe(new ShapedOreRecipe(new ItemStack(SSBlocks.foodSmokers, 1),
                new Object[] { "xxx", "xyx", "xxx",
                        Character.valueOf('x'), "ingotSteel",
                        Character.valueOf('y'), Blocks.glass_pane,
                }));

        //time
        p_77608_1_.addRecipe(new ShapedOreRecipe(new ItemStack(SSBlocks.chunkLoader, 1),
                new Object[] { "xyx", "yzy", "xyx",
                        Character.valueOf('x'), "ingotIron",
                        Character.valueOf('y'), Blocks.obsidian,
                        Character.valueOf('z'), Items.clock,
                }));

        //パイプ
        p_77608_1_.addRecipe(new ShapedOreRecipe(new ItemStack(SSBlocks.copperPipe, 4),
                new Object[] {
                        "x", "x", "x",
                        'x', "ingotCopper",
                }));

        //料理
        p_77608_1_.addRecipe(new ShapelessOreRecipe(new ItemStack(SSItems.curryRice, 1),
                new Object[] {
                        "containerWoodBowl",
                        "condimentCurryPowder",
                        "cookingRice",
                        "cropPotato",
                        "cropCarrot"
                }));

        //スープ
        p_77608_1_.addRecipe(new ShapelessOreRecipe(new ItemStack(SSItems.carrotSoup, 1),
                new Object[] {
                        "containerWoodBowl",
                        "fluidWater",
                        "cropCarrot"
                }));

        p_77608_1_.addRecipe(new ShapelessOreRecipe(new ItemStack(SSItems.cornSoup, 1),
                new Object[] {
                        "containerWoodBowl",
                        "fluidWater",
                        "cropCorn"
                }));

        p_77608_1_.addRecipe(new ShapelessOreRecipe(new ItemStack(SSItems.eggSoup, 1),
                new Object[] {
                        "containerWoodBowl",
                        "fluidWater",
                        "eggChicken"
                }));

        p_77608_1_.addRecipe(new ShapelessOreRecipe(new ItemStack(SSItems.onionSoup, 1),
                new Object[] {
                        "containerWoodBowl",
                        "fluidWater",
                        "cropOnion"
                }));

        p_77608_1_.addRecipe(new ShapelessOreRecipe(new ItemStack(SSItems.tomatoSoup, 1),
                new Object[] {
                        "containerWoodBowl",
                        "fluidWater",
                        "cropTomato"
                }));

        //輸送系
        p_77608_1_.addRecipe(new ShapedOreRecipe(new ItemStack(SSBlocks.poweredBuoy, 4),
                new Object[] { "xyx", "yzy", "xyx",
                        Character.valueOf('x'), "ingotIron",
                        Character.valueOf('y'), SSItems.dustWaterLily,
                        Character.valueOf('z'), Items.redstone,
                }));

        p_77608_1_.addRecipe(new ShapedOreRecipe(new ItemStack(SSBlocks.boardingBuoy, 4),
                new Object[] { "xyx", "yzy", "xyx",
                        Character.valueOf('x'), "ingotGold",
                        Character.valueOf('y'), SSItems.dustWaterLily,
                        Character.valueOf('z'), Items.redstone,
                }));

        p_77608_1_.addRecipe(new ShapedOreRecipe(new ItemStack(SSBlocks.detectorBuoy, 4),
                new Object[] { "xyx", "yzy", "xyx",
                        Character.valueOf('z'), "ingotIron",
                        Character.valueOf('x'), SSItems.dustWaterLily,
                        Character.valueOf('y'), Items.redstone,
                }));

    }

    public static void addVanillaRecipes() {

        for (Map.Entry<ItemStack, ItemStack> e : ((HashMap<ItemStack, ItemStack>) FurnaceRecipes.smelting().getSmeltingList()).entrySet()) {

            if (e.getValue().getItem().hasContainerItem(e.getValue().copy()) && e.getKey().getItem().hasContainerItem(e.getKey().copy())) {
                if (checkItem(e.getValue().getItem().getContainerItem(e.getValue().copy()), e.getKey().getItem().getContainerItem(e.getKey().copy())))
                    continue;
            }

            FurnaceCraftingManager.getInstance().addShapelessRecipe(e.getValue(), new Object[] { e.getKey() });

        }

    }

    private static boolean checkItem(ItemStack p_151397_1_, ItemStack p_151397_2_) {
        return p_151397_2_.getItem() == p_151397_1_.getItem() && (p_151397_2_.getItemDamage() == 32767 || p_151397_2_.getItemDamage() == p_151397_1_.getItemDamage());
    }

}
