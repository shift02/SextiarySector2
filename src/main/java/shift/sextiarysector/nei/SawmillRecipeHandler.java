package shift.sextiarysector.nei;

import shift.sextiarysector.SSRecipes;
import shift.sextiarysector.gui.GuiSawmill;
import shift.sextiarysector.gui.GuiSimpleMachine;
import shift.sextiarysector.recipe.RecipeSimpleMachine;

public class SawmillRecipeHandler extends SimpleMachineRecipeHandler {

    @Override
    public Class<? extends GuiSimpleMachine> getGuiClass() {
        return GuiSawmill.class;
    }

    @Override
    Class<? extends SimpleMachineRecipeHandler> getHandlerClass() {
        return SawmillRecipeHandler.class;
    }

    @Override
    String getHandlerName() {
        return "SS_Sawmill";
    }

    @Override
    RecipeSimpleMachine getRecipe() {
        return SSRecipes.sawmill;
    }

    @Override
    public String getGuiTexture() {
        return "sextiarysector:textures/guis/machine/sawmill_nei.png";
    }

    @Override
    String getGuiRecipeName() {
        return "ss.sawmill";
    }

}
