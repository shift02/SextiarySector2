package shift.sextiarysector.nei;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import shift.sextiarysector.SSRecipes;
import shift.sextiarysector.gui.GuiFreezer;
import shift.sextiarysector.recipe.RecipeSimpleMachine;

public class FreezerRecipeHandler extends AbstractSimple2RecipeHandler {

    @Override
    Class<? extends AbstractSimple2RecipeHandler> getHandlerClass() {
        return FreezerRecipeHandler.class;
    }

    @Override
    String getHandlerName() {
        return "SS_Freezer";
    }

    @Override
    RecipeSimpleMachine getRecipe() {
        return SSRecipes.freezer;
    }

    @Override
    String getGuiRecipeName() {
        return "ss.freezer";
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
    int getFuelTime(ItemStack item) {
        return SSRecipes.iceFuel.getResult(item);
    }

    @Override
    public String getGuiTexture() {
        return "sextiarysector:textures/guis/freezer.png";
    }

}
