package shift.sextiarysector.nei;

import shift.sextiarysector.SSRecipes;
import shift.sextiarysector.gui.GuiLoom;
import shift.sextiarysector.gui.GuiSimpleMachine;
import shift.sextiarysector.recipe.RecipeSimpleMachine;

public class LoomRecipeHandler extends SimpleMachineRecipeHandler {

    @Override
    public Class<? extends GuiSimpleMachine> getGuiClass() {
        return GuiLoom.class;
    }

    @Override
    Class<? extends SimpleMachineRecipeHandler> getHandlerClass() {
        return LoomRecipeHandler.class;
    }

    @Override
    String getHandlerName() {
        return "SS_Loom";
    }

    @Override
    RecipeSimpleMachine getRecipe() {
        return SSRecipes.loom;
    }

    @Override
    public String getGuiTexture() {
        return "sextiarysector:textures/guis/machine/loom_nei.png";
    }

    @Override
    String getGuiRecipeName() {
        return "ss.loom";
    }

}
