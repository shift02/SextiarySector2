package shift.sextiarysector.api.recipe;

/**
 * レシピを管理しているClass<br>
 * SextiarySector2の機械にレシピを追加できます。
 *
 * @version 1.0.0
 * @author Shift02
 *
 */
public class RecipeAPI {

	public static INormalRecipe millstone;;
	public static INormalRecipe loom;
	public static INormalRecipe sawmill;
	public static INormalRecipe spinning_machine;
	public static IFluidRecipe extractor;
	public static INormalRecipe pulverizer;
	public static INormalRecipe rollingMachine;
	public static IFluidRecipe manaSqueezer;
	public static INormalRecipe timeMachine;

	/**
	 *
	 * INormalRecipeの使い方
	 *
	 * RecipeAPI.millstone.add(new ItemStack(Blocks.stone, 1), new ItemStack(Blocks.cobblestone, 1));
	 *
	 * これで石から丸石を作るレシピが石臼に追加される
	 *
	 *
	 */

}
