package shift.sextiarysector.recipe;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import shift.sextiarysector.SSBlocks;

public class RecipesSawmill {

	public static void addRecipes(RecipeSimpleMachine recipe)
    {

		//
		recipe.add("plankWood", new ItemStack(SSBlocks.woodPlate, 4));

		for(int i=0;i<4;i++){
			recipe.add(new ItemStack(Blocks.log,1,i),new ItemStack(Blocks.planks, 6,i));
		}
		recipe.add(new ItemStack(Blocks.log2,1,0),new ItemStack(Blocks.planks, 6,4));
		recipe.add(new ItemStack(Blocks.log2,1,1),new ItemStack(Blocks.planks, 6,5));
		recipe.add(new ItemStack(Items.wooden_door,1),new ItemStack(Items.stick, 16));
		recipe.add(new ItemStack(Items.bow,1),new ItemStack(Items.string, 3));


    }

}
