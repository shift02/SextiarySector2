package shift.sextiarysector.nei;

import shift.sextiarysector.SSRecipes;
import shift.sextiarysector.gui.GuiRollingMachine;
import shift.sextiarysector.gui.GuiSimpleMachine;
import shift.sextiarysector.recipe.RecipeSimpleMachine;

public class RollingMachineRecipeHandler extends SimpleMachineRecipeHandler {

    @Override
    public Class<? extends GuiSimpleMachine> getGuiClass() {
        return GuiRollingMachine.class;
    }

    @Override
    Class<? extends SimpleMachineRecipeHandler> getHandlerClass() {
        return RollingMachineRecipeHandler.class;
    }

    @Override
    String getHandlerName() {
        return "SS_RollingMachine";
    }

    @Override
    RecipeSimpleMachine getRecipe() {
        return SSRecipes.rollingMachine;
    }

    @Override
    public String getGuiTexture() {
        return "sextiarysector:textures/guis/machine/rolling_machine_nei.png";
    }

    @Override
    String getGuiRecipeName() {
        return "ss.rolling_machine";
    }
}
