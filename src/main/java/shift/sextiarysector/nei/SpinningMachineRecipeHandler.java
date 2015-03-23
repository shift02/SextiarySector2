package shift.sextiarysector.nei;

import shift.sextiarysector.SSRecipes;
import shift.sextiarysector.gui.GuiSimpleMachine;
import shift.sextiarysector.gui.GuiSpinningMachine;
import shift.sextiarysector.recipe.RecipeSimpleMachine;

public class SpinningMachineRecipeHandler extends SimpleMachineRecipeHandler {

	@Override
	public Class<? extends GuiSimpleMachine> getGuiClass()
	{
		return GuiSpinningMachine.class;
	}

	@Override
	Class<? extends SimpleMachineRecipeHandler> getHandlerClass() {
		return SpinningMachineRecipeHandler.class;
	}

	@Override
	String getHandlerName() {
		return "SS_SpinningMachine";
	}

	@Override
	RecipeSimpleMachine getRecipe() {
		return SSRecipes.spinning_machine;
	}

	@Override
	public String getGuiTexture() {
		return "sextiarysector:textures/guis/machine/spinning_machine_nei.png";
	}

	@Override
	String getGuiRecipeName() {
		return "ss.spinning_machine";
	}
}
