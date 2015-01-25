package shift.sextiarysector.nei;

import java.util.ArrayList;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import shift.sextiarysector.api.agriculture.IFertilizer;
import shift.sextiarysector.module.FertilizerManager;

public class FertilizerPaddyRecipeHandler  extends FertilizerRecipeHandler{

	public Class<? extends FertilizerRecipeHandler> getHandlerClass(){
		return FertilizerPaddyRecipeHandler.class;
	}
	public String getHandlerName(){
		return "SS_Fertilizer_Paddy";
	}
	public ArrayList<IFertilizer> getRecipe(){
		return FertilizerManager.paddy;
	}
	public String getGuiRecipeName(){
		return "ss.fertilizer_paddy";
	}

	public Class<? extends GuiContainer> getGuiClass(){
		return null;
	}

	@Override
	public String getRecipeName() {
		return I18n.format("nei."+getGuiRecipeName());
	}

	@Override
	public String getGuiTexture() {
		return "sextiarysector:textures/guis/fertilizer_paddy_nei.png";
	}
}
