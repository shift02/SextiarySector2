package shift.sextiarysector.nei;

import net.minecraft.item.ItemStack;
import shift.sextiarysector.SSRecipes;
import shift.sextiarysector.gui.GuiMagicFurnace;
import shift.sextiarysector.recipe.RecipeSimpleMachine;

public class MagicFurnaceRecipeHandler extends AbstractSimpleRecipeHandler{

	@Override
	Class<? extends AbstractSimpleRecipeHandler> getHandlerClass() {
		return MagicFurnaceRecipeHandler.class;
	}

	@Override
	String getHandlerName() {
		return "SS_MagicFurnace";
	}


	@Override
	RecipeSimpleMachine getRecipe() {
		return SSRecipes.magicFurnace;
	}

	@Override
	String getGuiRecipeName() {
		return "ss.magic_furnace";
	}

	@Override
	public Class<? extends GuiMagicFurnace> getGuiClass() {
		return GuiMagicFurnace.class;
	}

	@Override
	String getFuelHandlerName() {
		return "SS_MagicFuel";
	}

	@Override
	int getFuelTime(ItemStack item) {
		return SSRecipes.magicFuel.getResult(item);
	}

	@Override
	public String getGuiTexture() {
		return "sextiarysector:textures/guis/magic_furnace.png";
	}

}
