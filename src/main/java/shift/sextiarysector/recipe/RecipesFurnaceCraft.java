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
import shift.sextiarysector.block.BlockMonitor;
import shift.sextiarysector.block.BlockMonitor.MonitorType;


public class RecipesFurnaceCraft {

	public static void addRecipes(FurnaceCraftingManager p_77608_1_)
    {

		//飲み物
		//p_77608_1_.addRecipe(new ShapelessOreRecipe(new ItemStack(SSItems.drinkingWaterBottle, 1),
		//		new Object[] {
		//	new ItemStack(Items.potionitem),
		//	}));

		//スライム
		p_77608_1_.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.slime_ball, 2),
				new Object[] {
			SSItems.dustWaterLily,
			"condimentSugar",
			Items.water_bucket,
			}));

		p_77608_1_.addRecipe(new ShapelessOreRecipe(new ItemStack(SSItems.drinkingWaterBottle, 2),
				new Object[] {
			SSItems.emptyBottle,
			SSItems.emptyBottle,
			SSItems.waterBottle,
			}));

		p_77608_1_.addRecipe(new ShapelessOreRecipe(new ItemStack(SSItems.blueStoneSlimeBall, 1),
				new Object[] {
			"dustBluestone",
			Items.slime_ball
			}));

		p_77608_1_.addRecipe(new ShapelessOreRecipe(new ItemStack(SSItems.steelIngot, 1),
				new Object[] {
			"dustCoal",
			"dustIron"
			}));

		p_77608_1_.addRecipe(new ShapelessOreRecipe(new ItemStack(SSItems.ninjaIngot, 1),
				new Object[] {
			"dustMithril",
			"dustDiamond"
			}));

		p_77608_1_.addRecipe(new ShapedOreRecipe(new ItemStack(SSItems.unit, 1),
				new Object[] { "xxx", "xyx","xxx",
			Character.valueOf('y'), SSItems.blueStoneSlimeBall,
			Character.valueOf('x'), "cobblestone",
			}));

		p_77608_1_.addRecipe(new ShapedOreRecipe(new ItemStack(SSItems.emptyBottle, 8),
        		new Object[] {
        	"x", "x",
        	'x', "paneGlassColorless",
			}));

		//Hammer
        p_77608_1_.addRecipe(new ShapedOreRecipe(new ItemStack(SSItems.hammer, 1),
				new Object[] { "xxx", " y "," y ",
			Character.valueOf('x'), "ingotIron",
			Character.valueOf('y'), "stickWood",
			}));

        //鉄のリング
        p_77608_1_.addRecipe(new ShapedOreRecipe(new ItemStack(SSItems.ironRing, 1),
				new Object[] { " x ", "x x"," x ",
			Character.valueOf('x'), "ingotIron",
			}));

        //クリーパーリング
        p_77608_1_.addRecipe(new ShapelessOreRecipe(new ItemStack(SSItems.creeperRing, 1),
				new Object[] {
        	"ringIron",
			SSItems.objectReactor,
			BlockMonitor.getMonitor(MonitorType.creeper)
			}));



        //液体カマド
        p_77608_1_.addRecipe(new ShapedOreRecipe(new ItemStack(SSBlocks.fluidFurnace, 1),
				new Object[] { "xxx", "xyx","xxx",
			Character.valueOf('x'), "cobblestone",
			Character.valueOf('y'), Blocks.glass_pane,
			}));

        //魔法カマド
        p_77608_1_.addRecipe(new ShapedOreRecipe(new ItemStack(SSBlocks.magicFurnace, 1),
				new Object[] { "xxx", "xyx","xxx",
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
				new Object[] { "xxx", "xyx","xxx",
			Character.valueOf('x'), "ingotSteel",
			Character.valueOf('y'), Blocks.glass_pane,
			}));

        //time
        p_77608_1_.addRecipe(new ShapedOreRecipe(new ItemStack(SSBlocks.chunkLoader, 1),
				new Object[] { "xyx", "yzy","xyx",
			Character.valueOf('x'), "ingotIron",
			Character.valueOf('y'), Blocks.obsidian,
			Character.valueOf('z'), Items.clock,
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

    }

	public static void addVanillaRecipes()
    {

		for( Map.Entry<ItemStack, ItemStack> e : ((HashMap<ItemStack,ItemStack>)FurnaceRecipes.smelting().getSmeltingList()).entrySet()){

			if(e.getValue().getItem().hasContainerItem(e.getValue().copy()) && e.getKey().getItem().hasContainerItem(e.getKey().copy()) ){
				if(checkItem(e.getValue().getItem().getContainerItem(e.getValue().copy()), e.getKey().getItem().getContainerItem(e.getKey().copy())))continue;
			}

    		FurnaceCraftingManager.getInstance().addShapelessRecipe(e.getValue(), new Object[]{e.getKey()});

    	}

    }

	private static boolean checkItem(ItemStack p_151397_1_, ItemStack p_151397_2_)
    {
        return p_151397_2_.getItem() == p_151397_1_.getItem() && (p_151397_2_.getItemDamage() == 32767 || p_151397_2_.getItemDamage() == p_151397_1_.getItemDamage());
    }

}
