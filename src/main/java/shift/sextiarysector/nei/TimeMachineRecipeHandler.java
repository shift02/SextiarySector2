package shift.sextiarysector.nei;

import shift.sextiarysector.SSRecipes;
import shift.sextiarysector.gui.GuiSimpleMachine;
import shift.sextiarysector.gui.GuiTimeMachine;
import shift.sextiarysector.recipe.RecipeSimpleMachine;

public class TimeMachineRecipeHandler extends SimpleMachineRecipeHandler {

    @Override
    public Class<? extends GuiSimpleMachine> getGuiClass() {
        return GuiTimeMachine.class;
    }

    @Override
    Class<? extends SimpleMachineRecipeHandler> getHandlerClass() {
        return TimeMachineRecipeHandler.class;
    }

    @Override
    String getHandlerName() {
        return "SS_TimeMachine";
    }

    @Override
    RecipeSimpleMachine getRecipe() {
        return SSRecipes.timeMachine;
    }

    @Override
    public String getGuiTexture() {
        return "sextiarysector:textures/guis/machine/time_machine_nei.png";
    }

    @Override
    String getGuiRecipeName() {
        return "ss.time_machine";
    }
}
