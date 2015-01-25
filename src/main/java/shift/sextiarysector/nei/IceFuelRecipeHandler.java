package shift.sextiarysector.nei;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import shift.sextiarysector.SSRecipes;
import shift.sextiarysector.gui.GuiFreezer;
import shift.sextiarysector.recipe.RecipeSimpleMachine;

public class IceFuelRecipeHandler  extends AbstractFuel2RecipeHandler{

	@Override
	Class<? extends AbstractSimple2RecipeHandler> getFuelHandlerClass() {
		return IceFuelRecipeHandler.class;
	}

	@Override
	RecipeSimpleMachine getRecipe() {
		return SSRecipes.freezer;
	}

	@Override
	String getGuiRecipeName() {
		return "ss.ice_fuel";
	}

	@Override
	public Class<? extends GuiContainer> getGuiClass() {
		return GuiFreezer.class;
	}

	@Override
	String getFuelHandlerName() {
		return "SS_IceFuel";
	}

	@Override
	String getHandlerName() {
		return "SS_Freezer";
	}

	@Override
	int getFuelTime(ItemStack item) {
		return SSRecipes.iceFuel.getResult(item);
	}

	@Override
	public String getGuiTexture() {
		return "sextiarysector:textures/guis/freezer.png";
	}
}
