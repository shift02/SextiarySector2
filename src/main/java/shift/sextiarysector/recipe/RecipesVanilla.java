package shift.sextiarysector.recipe;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class RecipesVanilla {

    public static void addRecipes(CraftingManager p_77608_1_) {

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(Items.painting, 1),
                new Object[] { "xxx", "xyx", "xxx",
                        Character.valueOf('x'), "stickWood",
                        Character.valueOf('y'), "massString"
                }));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(Items.bed, 1),
                new Object[] { "yyy", "xxx",
                        Character.valueOf('x'), "plankWood",
                        Character.valueOf('y'), "massString"
                }));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(Items.fishing_rod, 1),
                new Object[] { "  x", " xy", "x z",
                        Character.valueOf('x'), "stickWood",
                        Character.valueOf('y'), new ItemStack(Items.string),
                        Character.valueOf('z'), "nuggetIron"
                }));

        p_77608_1_.getRecipeList().add(new ShapedOreRecipe(new ItemStack(Items.fishing_rod, 1),
                new Object[] { "  x", " xy", "x z",
                        Character.valueOf('x'), "stickWood",
                        Character.valueOf('y'), new ItemStack(Items.string),
                        Character.valueOf('z'), "nuggetZinc"
                }));

    }

}
