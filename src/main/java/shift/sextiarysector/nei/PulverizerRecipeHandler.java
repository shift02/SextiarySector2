package shift.sextiarysector.nei;

import shift.sextiarysector.SSRecipes;
import shift.sextiarysector.gui.GuiPulverizer;
import shift.sextiarysector.gui.GuiSimpleMachine;
import shift.sextiarysector.recipe.RecipeSimpleMachine;

public class PulverizerRecipeHandler extends SimpleMachineRecipeHandler {

    @Override
    public Class<? extends GuiSimpleMachine> getGuiClass() {
        return GuiPulverizer.class;
    }

    @Override
    Class<? extends SimpleMachineRecipeHandler> getHandlerClass() {
        return PulverizerRecipeHandler.class;
    }

    @Override
    String getHandlerName() {
        return "SS_Pulverizer";
    }

    @Override
    RecipeSimpleMachine getRecipe() {
        return SSRecipes.pulverizer;
    }

    @Override
    public String getGuiTexture() {
        return "sextiarysector:textures/guis/machine/pulverizer_nei.png";
    }

    @Override
    String getGuiRecipeName() {
        return "ss.pulverizer";
    }
}
