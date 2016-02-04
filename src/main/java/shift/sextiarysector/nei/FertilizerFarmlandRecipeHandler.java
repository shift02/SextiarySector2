package shift.sextiarysector.nei;

import java.util.ArrayList;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import shift.sextiarysector.api.agriculture.IFertilizer2;
import shift.sextiarysector.module.FertilizerManager;

public class FertilizerFarmlandRecipeHandler extends FertilizerRecipeHandler {

    public Class<? extends FertilizerRecipeHandler> getHandlerClass() {
        return FertilizerFarmlandRecipeHandler.class;
    }

    public String getHandlerName() {
        return "SS_Fertilizer_Farmland";
    }

    public ArrayList<IFertilizer2> getRecipe() {
        return FertilizerManager.normal;
    }

    public String getGuiRecipeName() {
        return "ss.fertilizer_farmland";
    }

    public Class<? extends GuiContainer> getGuiClass() {
        return null;
    }

    @Override
    public String getRecipeName() {
        return I18n.format("nei." + getGuiRecipeName());
    }

    @Override
    public String getGuiTexture() {
        return "sextiarysector:textures/guis/fertilizer_farmland_nei.png";
    }

}
