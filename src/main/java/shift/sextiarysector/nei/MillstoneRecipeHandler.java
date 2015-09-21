package shift.sextiarysector.nei;

import shift.sextiarysector.SSRecipes;
import shift.sextiarysector.gui.GuiMillstone;
import shift.sextiarysector.gui.GuiSimpleMachine;
import shift.sextiarysector.recipe.RecipeSimpleMachine;

public class MillstoneRecipeHandler extends SimpleMachineRecipeHandler {

    @Override
    public Class<? extends GuiSimpleMachine> getGuiClass() {
        return GuiMillstone.class;
    }

    @Override
    Class<? extends SimpleMachineRecipeHandler> getHandlerClass() {
        return MillstoneRecipeHandler.class;
    }

    @Override
    String getHandlerName() {
        return "SS_Millstone";
    }

    @Override
    RecipeSimpleMachine getRecipe() {
        return SSRecipes.millstone;
    }

    @Override
    public String getGuiTexture() {
        return "sextiarysector:textures/guis/machine/millstone_nei.png";
    }

    @Override
    String getGuiRecipeName() {
        return "ss.millstone";
    }

}
