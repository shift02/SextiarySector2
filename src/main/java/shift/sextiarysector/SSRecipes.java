package shift.sextiarysector;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import shift.sextiarysector.api.recipe.RecipeAPI;
import shift.sextiarysector.recipe.FurnaceCraftingManager;
import shift.sextiarysector.recipe.RecipeSimpleFluid;
import shift.sextiarysector.recipe.RecipeSimpleFuel;
import shift.sextiarysector.recipe.RecipeSimpleMachine;
import shift.sextiarysector.recipe.RecipesArmor;
import shift.sextiarysector.recipe.RecipesCore;
import shift.sextiarysector.recipe.RecipesExtractor;
import shift.sextiarysector.recipe.RecipesFluidFurnace;
import shift.sextiarysector.recipe.RecipesFoodSmokers;
import shift.sextiarysector.recipe.RecipesFreezer;
import shift.sextiarysector.recipe.RecipesFurnace;
import shift.sextiarysector.recipe.RecipesFurnaceCraft;
import shift.sextiarysector.recipe.RecipesIceFuel;
import shift.sextiarysector.recipe.RecipesLoom;
import shift.sextiarysector.recipe.RecipesMachine;
import shift.sextiarysector.recipe.RecipesMagicFuel;
import shift.sextiarysector.recipe.RecipesMagicFurnace;
import shift.sextiarysector.recipe.RecipesManaSqueezer;
import shift.sextiarysector.recipe.RecipesMillstone;
import shift.sextiarysector.recipe.RecipesNormalBlock;
import shift.sextiarysector.recipe.RecipesPulverizer;
import shift.sextiarysector.recipe.RecipesRollingMachine;
import shift.sextiarysector.recipe.RecipesSawmill;
import shift.sextiarysector.recipe.RecipesSpinningMachine;
import shift.sextiarysector.recipe.RecipesTimeMachine;
import shift.sextiarysector.recipe.RecipesTool;
import shift.sextiarysector.recipe.RecipesVanilla;

public class SSRecipes {

	public static RecipeSimpleFluid fluidFurnace;
	public static RecipeSimpleFluid foodSmokers;
	public static RecipeSimpleMachine magicFurnace;
	public static RecipeSimpleMachine freezer;

	public static RecipeSimpleFuel magicFuel;
	public static RecipeSimpleFuel iceFuel;

	public static RecipeSimpleMachine millstone;
	public static RecipeSimpleMachine loom;
	public static RecipeSimpleMachine sawmill;
	public static RecipeSimpleMachine spinning_machine;
	public static RecipeSimpleFluid extractor;
	public static RecipeSimpleMachine pulverizer;
	public static RecipeSimpleMachine rollingMachine;
	public static RecipeSimpleFluid manaSqueezer;
	public static RecipeSimpleMachine timeMachine;

	public static void initRecipeLists() {

		fluidFurnace = new RecipeSimpleFluid();
		foodSmokers = new RecipeSimpleFluid();
		magicFurnace = new RecipeSimpleMachine();
		freezer = new RecipeSimpleMachine();

		magicFuel = new RecipeSimpleFuel();
		iceFuel = new RecipeSimpleFuel();

		RecipeAPI.millstone = millstone = new RecipeSimpleMachine();
		RecipeAPI.loom = loom = new RecipeSimpleMachine();
		RecipeAPI.sawmill = sawmill = new RecipeSimpleMachine();
		RecipeAPI.spinning_machine = spinning_machine = new RecipeSimpleMachine();
		RecipeAPI.extractor = extractor = new RecipeSimpleFluid();
		RecipeAPI.pulverizer = pulverizer = new RecipeSimpleMachine();
		RecipeAPI.rollingMachine = rollingMachine = new RecipeSimpleMachine();
		RecipeAPI.manaSqueezer = manaSqueezer = new RecipeSimpleFluid();
		RecipeAPI.timeMachine = timeMachine = new RecipeSimpleMachine();

	}

	public static void initRecipes() {

		CraftingManager m = CraftingManager.getInstance();

		RecipesFurnace.addRecipes();

		RecipesVanilla.addRecipes(m);
		RecipesNormalBlock.addRecipes(m);
		RecipesMachine.addRecipes(m);
		RecipesCore.addRecipes(m);
		RecipesTool.addRecipes(m);
		RecipesArmor.addRecipes(m);

		FurnaceCraftingManager fm = FurnaceCraftingManager.getInstance();

		RecipesFurnaceCraft.addRecipes(fm);

		RecipesFluidFurnace.addRecipes(fluidFurnace);
		RecipesFoodSmokers.addRecipes(foodSmokers);

		RecipesMagicFurnace.addRecipes(magicFurnace);
		RecipesMagicFuel.addRecipes(magicFuel);

		RecipesFreezer.addRecipes(freezer);
		RecipesIceFuel.addRecipes(iceFuel);

		//GF
		RecipesMillstone.addRecipes(millstone);

		RecipesLoom.addRecipes(loom);

		RecipesSawmill.addRecipes(sawmill);

		RecipesSpinningMachine.addRecipes(spinning_machine);

		RecipesExtractor.addRecipes(extractor);

		RecipesPulverizer.addRecipes(pulverizer);

		RecipesRollingMachine.addRecipes(rollingMachine);

		RecipesManaSqueezer.addRecipes(manaSqueezer);

		RecipesTimeMachine.addRecipes(timeMachine);

	}

	public static void deleteVanillaRecipe() {

		CraftingManager m = CraftingManager.getInstance();
		ItemStack wool = new ItemStack(Blocks.wool);

		for (int i = 0; i < m.getRecipeList().size(); i++) {

			IRecipe re = (IRecipe) m.getRecipeList().get(i);
			if (re != null && re.getRecipeOutput() != null && re.getRecipeOutput().isItemEqual(wool) && re instanceof ShapedRecipes && !(re instanceof ShapelessRecipes)) {
				m.getRecipeList().remove(i);
				return;
			}

		}

	}
}
