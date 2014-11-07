package shift.sextiarysector.nei;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraftforge.fluids.FluidContainerRegistry;
import shift.sextiarysector.SSRecipes;
import shift.sextiarysector.gui.GuiFluidFurnace;
import shift.sextiarysector.recipe.RecipeSimpleFluid;

public class FluidFurnaceRecipeHandler extends SimpleFluidRecipeHandler{

	@Override
	Class<? extends SimpleFluidRecipeHandler> getHandlerClass() {
		return FluidFurnaceRecipeHandler.class;
	}

	@Override
	String getHandlerName() {
		return "SS_FluidFurnace";
	}

	@Override
	RecipeSimpleFluid getRecipe() {
		return SSRecipes.fluidFurnace;
	}

	@Override
	String getGuiRecipeName() {
		return "ss.fluid_furnace";
	}

	@Override
	public Class<? extends GuiContainer> getGuiClass() {
		return GuiFluidFurnace.class;
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
