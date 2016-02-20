package shift.sextiarysector.nei;

import static codechicken.lib.gui.GuiDraw.*;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraftforge.fluids.FluidContainerRegistry;
import shift.sextiarysector.SSRecipes;
import shift.sextiarysector.gui.GuiExtractor;
import shift.sextiarysector.recipe.RecipeSimpleFluid;

public class ExtractorRecipeHandler extends SimpleFluidRecipeHandler {

    @Override
    Class<? extends SimpleFluidRecipeHandler> getHandlerClass() {
        return ExtractorRecipeHandler.class;
    }

    @Override
    String getHandlerName() {
        return "SS_Extractor";
    }

    @Override
    RecipeSimpleFluid getRecipe() {
        return SSRecipes.extractor;
    }

    @Override
    String getGuiRecipeName() {
        return "ss.extractor";
    }

    @Override
    public Class<? extends GuiContainer> getGuiClass() {
        return GuiExtractor.class;
    }

    @Override
    int getMaxFluid() {
        return FluidContainerRegistry.BUCKET_VOLUME * 10;
    }

    @Override
    public String getGuiTexture() {
        return "sextiarysector:textures/guis/machine/extractor_nei.png";
    }

    @Override
    public void drawExtras(int recipe) {
        //drawProgressBar(52, 25, 176, 0, 14, 14, 48, 7);
        drawProgressBar(74, 23, 176, 14, 24, 16, 48, 0);

        if (((SimpleFluidPair) this.arecipes.get(recipe)).fluidStack != null) {
            this.drawFluidTank(102, 4, ((SimpleFluidPair) this.arecipes.get(recipe)).fluidStack, 34, 36);
        }

        changeTexture(getGuiTexture());
        drawTexturedModalRect(103, 6, 176, 31, 32, 36);

    }
}
