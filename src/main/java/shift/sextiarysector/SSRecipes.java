package shift.sextiarysector;

import net.minecraft.item.crafting.CraftingManager;
import shift.sextiarysector.recipe.FurnaceCraftingManager;
import shift.sextiarysector.recipe.RecipeSimpleMachine;
import shift.sextiarysector.recipe.RecipesCore;
import shift.sextiarysector.recipe.RecipesFurnaceCraft;
import shift.sextiarysector.recipe.RecipesLoom;
import shift.sextiarysector.recipe.RecipesMachine;
import shift.sextiarysector.recipe.RecipesMillstone;
import shift.sextiarysector.recipe.RecipesNormalBlock;
import shift.sextiarysector.recipe.RecipesSawmill;
import shift.sextiarysector.recipe.RecipesTool;

public class SSRecipes {

	public static RecipeSimpleMachine millstone;
	public static RecipeSimpleMachine loom;
	public static RecipeSimpleMachine sawmill;

	public static void initRecipeLists(){

		millstone = new RecipeSimpleMachine();
		loom = new RecipeSimpleMachine();
		sawmill = new RecipeSimpleMachine();

	}

	public static void initRecipes(){

		CraftingManager m = CraftingManager.getInstance();


		RecipesNormalBlock.addRecipes(m);
		RecipesMachine.addRecipes(m);
		RecipesCore.addRecipes(m);
		RecipesTool.addRecipes(m);

		FurnaceCraftingManager fm = FurnaceCraftingManager.getInstance();

		RecipesFurnaceCraft.addRecipes(fm);

		//GF
		RecipesMillstone.addRecipes(millstone);

		RecipesLoom.addRecipes(loom);

		RecipesSawmill.addRecipes(sawmill);

	}

}
