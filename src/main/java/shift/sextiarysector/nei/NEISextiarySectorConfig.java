package shift.sextiarysector.nei;

import shift.sextiarysector.gui.GuiFluidFurnace;
import shift.sextiarysector.gui.GuiLargeFurnace;
import shift.sextiarysector.gui.GuiLoom;
import shift.sextiarysector.gui.GuiMagicFurnace;
import shift.sextiarysector.gui.GuiMillstone;
import shift.sextiarysector.gui.GuiSawmill;
import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;

public class NEISextiarySectorConfig implements IConfigureNEI {

	@Override
	public void loadConfig() {


		API.registerRecipeHandler(new MillstoneRecipeHandler());
		API.registerUsageHandler(new MillstoneRecipeHandler());

		API.registerRecipeHandler(new LoomRecipeHandler());
		API.registerUsageHandler(new LoomRecipeHandler());

		API.registerRecipeHandler(new SawmillRecipeHandler());
		API.registerUsageHandler(new SawmillRecipeHandler());

		/*
		API.registerRecipeHandler(new LoomRecipeHandler());
		API.registerUsageHandler(new LoomRecipeHandler());

		API.registerRecipeHandler(new CentrifugalSeparatorRecipeHandler());
		API.registerUsageHandler(new CentrifugalSeparatorRecipeHandler());
		*/

		API.registerRecipeHandler(new FurnaceShapedRecipeHandler());
		API.registerUsageHandler(new FurnaceShapedRecipeHandler());
		API.registerRecipeHandler(new FurnaceShapelessRecipeHandler());
		API.registerUsageHandler(new FurnaceShapelessRecipeHandler());

		API.registerRecipeHandler(new FluidFurnaceRecipeHandler());
		API.registerUsageHandler(new FluidFurnaceRecipeHandler());

		API.registerRecipeHandler(new MagicFurnaceRecipeHandler());
		API.registerUsageHandler(new MagicFurnaceRecipeHandler());
		API.registerRecipeHandler(new MagicFuelRecipeHandler());
		API.registerUsageHandler(new MagicFuelRecipeHandler());


		API.registerRecipeHandler(new FertilizerRecipeHandler());
		API.registerUsageHandler(new FertilizerRecipeHandler());

		/*
		API.registerRecipeHandler(new BrewingRecipeHandler());
		API.registerUsageHandler(new BrewingRecipeHandler());

		API.registerRecipeHandler(new ShapedOreRecipeHandler());
		API.registerUsageHandler(new ShapedOreRecipeHandler());
		API.registerRecipeHandler(new ShapelessOreRecipeHandler());
		API.registerUsageHandler(new ShapelessOreRecipeHandler());
		*/

		//API.registerGuiOverlay(GuiMillstone.class, "SextiarySectorMillstone");
		//API.registerGuiOverlay(GuiLoom.class, "SextiarySectorLoom");
		API.registerGuiOverlay(GuiLargeFurnace.class, "furnaceCrafting");

		API.registerGuiOverlay(GuiFluidFurnace.class, "SS_FluidFurnace");
		API.registerGuiOverlay(GuiMagicFurnace.class, "SS_MagicFurnace");

		API.registerGuiOverlay(GuiMillstone.class, "SS_Millstone");
		API.registerGuiOverlay(GuiLoom.class, "SS_Loom");
		API.registerGuiOverlay(GuiSawmill.class, "SS_Sawmill");
		//API.registerGuiOverlay(GuiBrewingStand.class, "SextiarySectorBrewing");

	}

	@Override
	public String getName() {
		return "Sextiary Sector NEI Plugin";
	}

	@Override
	public String getVersion() {
		return "0.0.3";
	}

}
