package shift.sextiarysector;

import java.util.Locale;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;
import shift.sextiarysector.api.EnumColor;
import shift.sextiarysector.event.ClientEventHandler;
import shift.sextiarysector.fluid.FluidColor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SSFluids {

	public static Fluid takumiTea;
	public static Fluid drinkingWater;
	public static Fluid springWater;
	public static Fluid hotSprings;

	public static Fluid steam;
	public static Fluid mana;
	public static Fluid oxygen;
	public static Fluid season;

	public static Fluid sap;

	public static Fluid iron;
	public static Fluid gold;

	public static Fluid[] color;

	public static void initFluids() {

		takumiTea = registerFluid("TakumiTea", 0, 0x006400, 5, 2.0f).setUnlocalizedName("takumi_tea");
		drinkingWater = registerFluid("DrinkingWater", 0, 0x87CEFA, 4, 1.0f).setUnlocalizedName("drinking_water");
		springWater = registerFluid("SpringWater", 0, 0x87CEFA, 4, 1.0f).setUnlocalizedName("spring_water");
		hotSprings = registerFluid("HotSprings", 0, 0xFFFFFF, 4, 1.0f).setUnlocalizedName("hot_springs");

		steam = registerFluid("Steam", 1, 0xFFFFFF, 1, 1.0f).setUnlocalizedName("steam").setGaseous(true);
		mana = registerFluid("Mana", 1, 0x7CF4E4, 1, 1.0f).setUnlocalizedName("mana").setGaseous(true);
		oxygen = registerFluid("Oxygen", 0, 0xFFFFFF, 1, 1.0f).setUnlocalizedName("oxygen").setGaseous(true);
		//season = new SSFluid("season", 1, 0xFFFFFF, 1, 1.0f).setUnlocalizedName("season").setDensity(-10).setGaseous(true);

		sap = registerFluid("Sap", 2, 0xFF7F50, 1, 1.0f).setUnlocalizedName("sap");

		iron = registerFluid("Iron", 2, 0xFF1493, 1, 1.0f).setUnlocalizedName("iron");
		gold = registerFluid("Gold", 2, 0xFFD700, 1, 1.0f).setUnlocalizedName("gold");

		color = new Fluid[16];
		for (int i = 0; i < color.length; i++) {
			color[i] = new FluidColor("ss_" + EnumColor.getColor(i).name(), EnumColor.getColor(i)).setUnlocalizedName(EnumColor.getColor(i).name().toLowerCase(Locale.ENGLISH));
		}

	}

	public static void postFluids() {

		for (int i = 1; i <= FluidRegistry.getRegisteredFluids().size(); i++) {
			FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluid(i), new ItemStack(SSBlocks.fluidCrafter, 1, i), new ItemStack(SSBlocks.fluidCrafter, 1, 0));
		}

		FluidContainerRegistry.registerFluidContainer(FluidRegistry.WATER, new ItemStack(SSItems.waterBottle, 1), FluidContainerRegistry.EMPTY_BOTTLE);
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.LAVA, new ItemStack(SSItems.lavaBottle, 1), FluidContainerRegistry.EMPTY_BOTTLE);

		//FluidContainerRegistry.registerFluidContainer(takumiTea, new ItemStack(SSItems.takumiTeaBottle, 1), new ItemStack(SSItems.emptyBottle, 1));
		//FluidContainerRegistry.registerFluidContainer(drinkingWater, new ItemStack(SSItems.drinkingWaterBottle, 1), new ItemStack(SSItems.emptyBottle, 1));
		//FluidContainerRegistry.registerFluidContainer(sap, new ItemStack(SSItems.sapBottle, 1), new ItemStack(SSItems.emptyBottle, 1));

		FluidContainerRegistry.registerFluidContainer(takumiTea, new ItemStack(SSItems.takumiTeaBottle, 1), FluidContainerRegistry.EMPTY_BOTTLE);
		FluidContainerRegistry.registerFluidContainer(drinkingWater, new ItemStack(SSItems.drinkingWaterBottle, 1), FluidContainerRegistry.EMPTY_BOTTLE);
		FluidContainerRegistry.registerFluidContainer(sap, new ItemStack(SSItems.sapBottle, 1), FluidContainerRegistry.EMPTY_BOTTLE);

		FluidContainerRegistry.registerFluidContainer(steam, new ItemStack(SSItems.steamBucket, 1), new ItemStack(Items.bucket, 1));
		FluidContainerRegistry.registerFluidContainer(iron, new ItemStack(SSItems.ironFluidBucket, 1), new ItemStack(Items.bucket, 1));
		FluidContainerRegistry.registerFluidContainer(gold, new ItemStack(SSItems.goldFluidBucket, 1), new ItemStack(Items.bucket, 1));

		for (int i = 0; i < color.length; i++) {

			ItemStack item = new ItemStack(SSItems.colorSpray, 1, i);
			((IFluidContainerItem) item.getItem()).fill(item, new FluidStack(color[i], 1000), true);
			FluidContainerRegistry.registerFluidContainer(color[i], item, new ItemStack(SSItems.emptyBottle));
		}

	}

	public static Fluid registerFluid(String fluidName, int type, int color, int moisture, float moistureSaturation) {

		Fluid f = new SSFluid(fluidName, type, color, moisture, moistureSaturation);
		boolean t = FluidRegistry.registerFluid(f);

		if (!t) {

			if (FluidRegistry.getFluid(fluidName) != null) {
				f = FluidRegistry.getFluid(fluidName);
			}

		}

		return f;

	}

	public static class SSFluid extends Fluid {

		private final int type;
		private final int color;

		public int moisture;
		public float moistureSaturation;

		public SSFluid(String fluidName, int type, int color, int moisture, float moistureSaturation) {
			super(fluidName);
			this.type = type;
			this.color = color;
			this.moisture = moisture;
			this.moistureSaturation = moistureSaturation;
		}

		@Override
		public String getUnlocalizedName()
		{
			return "fluid.ss." + this.unlocalizedName;
		}

		@Override
		public int getColor()
		{
			return color;
		}

		@Override
		@SideOnly(Side.CLIENT)
		public IIcon getStillIcon()
		{
			switch (type) {
			case 0:
				return ClientEventHandler.waterStill;
			case 1:
				return ClientEventHandler.portal;
			case 2:
				return ClientEventHandler.lavaStill;
			}
			return ClientEventHandler.waterStill;
		}

		@Override
		@SideOnly(Side.CLIENT)
		public IIcon getFlowingIcon()
		{
			switch (type) {
			case 0:
				return ClientEventHandler.waterFlow;
			case 1:
				return ClientEventHandler.portal;
			case 2:
				return ClientEventHandler.lavaFlow;
			}
			return ClientEventHandler.waterStill;
		}

	}

}
