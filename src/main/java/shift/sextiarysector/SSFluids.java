package shift.sextiarysector;

import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import shift.sextiarysector.event.ClientEventHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SSFluids {

	public static Fluid takumiTea;
	public static Fluid drinkingWater;

	public static Fluid steam;

	public static Fluid iron;
	public static Fluid gold;

	public static void initFluids(){

		takumiTea =  new SSFluid("TakumiTea", 0, 0x006400, 5, 2.0f).setUnlocalizedName("takumi_tea");
		drinkingWater =  new SSFluid("DrinkingWater", 0, 0x87CEFA, 4, 1.0f).setUnlocalizedName("drinking_water");

		steam = new SSFluid("Steam", 1, 0xFFFFFF, 1, 1.0f).setUnlocalizedName("steam");

		iron = new SSFluid("Iron", 2, 0xFF1493, 1, 1.0f).setUnlocalizedName("iron");
		gold = new SSFluid("Gold", 2, 0xFFD700, 1, 1.0f).setUnlocalizedName("gold");

	}

	public static void postFluids(){

		for(int i=1;i<=FluidRegistry.getRegisteredFluids().size();i++){
			FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluid(i), new ItemStack(SSBlocks.fluidCrafter,1,i), new ItemStack(SSBlocks.fluidCrafter,1,0));
		}

		FluidContainerRegistry.registerFluidContainer(FluidRegistry.WATER, new ItemStack(SSItems.waterBottle,1), new ItemStack(SSItems.emptyBottle,1));
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.LAVA, new ItemStack(SSItems.lavaBottle,1), new ItemStack(SSItems.emptyBottle,1));

		FluidContainerRegistry.registerFluidContainer(takumiTea, new ItemStack(SSItems.takumiTeaBottle,1), new ItemStack(SSItems.emptyBottle,1));
		FluidContainerRegistry.registerFluidContainer(drinkingWater, new ItemStack(SSItems.drinkingWaterBottle,1), new ItemStack(SSItems.emptyBottle,1));

	}

	public static class SSFluid extends Fluid {

		private int type;
		private int color;

		public int moisture;
		public float moistureSaturation;

		public SSFluid(String fluidName, int type, int color, int moisture, float moistureSaturation) {
			super(fluidName);
			this.type = type;
			this.color = color;
			this.moisture = moisture;
			this.moistureSaturation = moistureSaturation;
			FluidRegistry.registerFluid(this);
		}

		public String getUnlocalizedName()
	    {
	        return "fluid.ss." + this.unlocalizedName;
	    }

		public int getColor()
	    {
	        return color;
	    }

		@SideOnly(Side.CLIENT)
		public IIcon getStillIcon()
	    {
			switch(type){
				case 0:return ClientEventHandler.waterStill;
				case 1:return ClientEventHandler.portal;
				case 2:return ClientEventHandler.lavaStill;
			}
	        return ClientEventHandler.waterStill;
	    }

		@SideOnly(Side.CLIENT)
	    public IIcon getFlowingIcon()
	    {
			switch(type){
			case 0:return ClientEventHandler.waterFlow;
			case 1:return ClientEventHandler.portal;
			case 2:return ClientEventHandler.lavaFlow;
			}
	        return ClientEventHandler.waterStill;
	    }

	}

}
