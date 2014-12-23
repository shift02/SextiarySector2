package shift.sextiarysector.nei;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraftforge.fluids.FluidContainerRegistry;
import shift.sextiarysector.SSRecipes;
import shift.sextiarysector.gui.GuiFoodSmokers;
import shift.sextiarysector.recipe.RecipeSimpleFluid;

public class FoodSmokersRecipeHandler  extends SimpleFluidRecipeHandler{

	@Override
	Class<? extends SimpleFluidRecipeHandler> getHandlerClass() {
		return FoodSmokersRecipeHandler.class;
	}

	@Override
	String getHandlerName() {
		return "SS_FoodSmokers";
	}

	@Override
	RecipeSimpleFluid getRecipe() {
		return SSRecipes.foodSmokers;
	}

	@Override
	String getGuiRecipeName() {
		return "ss.food_smokers";
	}

	@Override
	public Class<? extends GuiContainer> getGuiClass() {
		return GuiFoodSmokers.class;
	}

	@Override
	int getMaxFluid() {
		return FluidContainerRegistry.BUCKET_VOLUME * 10;
	}

	@Override
	public String getGuiTexture() {
		return "sextiarysector:textures/guis/fluid_furnace_nei.png";
	}
}
