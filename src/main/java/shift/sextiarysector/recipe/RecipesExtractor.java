package shift.sextiarysector.recipe;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import shift.sextiarysector.SSFluids;
import shift.sextiarysector.api.EnumColor;

public class RecipesExtractor {

    public static void addRecipes(RecipeSimpleFluid recipe) {

        //recipe.add("logWood", null, new FluidStack(SSFluids.oxygen, 50));
        recipe.add("treeLeaves", null, new FluidStack(SSFluids.oxygen, 200));
        recipe.add(new ItemStack(Blocks.grass), new ItemStack(Items.clay_ball), new FluidStack(SSFluids.oxygen, 50));

        for (int i = 0; i < SSFluids.color.length; i++) {
            recipe.add("dye" + EnumColor.getColor(i).name(), null, new FluidStack(SSFluids.color[i], 200));
        }

    }

}
