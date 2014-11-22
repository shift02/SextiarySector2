package shift.sextiarysector.nei;

import net.minecraft.item.ItemStack;
import shift.sextiarysector.SSRecipes;
import shift.sextiarysector.gui.GuiMagicFurnace;
import shift.sextiarysector.recipe.RecipeSimpleMachine;

public class MagicFuelRecipeHandler extends AbstractFuelRecipeHandler{

	@Override
	Class<? extends AbstractSimpleRecipeHandler> getFuelHandlerClass() {
		return MagicFuelRecipeHandler.class;
	}

	@Override
	RecipeSimpleMachine getRecipe() {
		return SSRecipes.magicFurnace;
	}

	@Override
	String getGuiRecipeName() {
		return "ss.magic_fuel";
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
	String getHandlerName() {
		return "SS_MagicFurnace";
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
