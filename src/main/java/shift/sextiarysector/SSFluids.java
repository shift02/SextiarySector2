package shift.sextiarysector;

import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import shift.sextiarysector.event.ClientEventHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SSFluids {

	public static Fluid takumiTea;

	public static void initFluids(){

		takumiTea =  new SSFluid("takumi_tea", 0x006400, 5, 2.0f);

	}

	public static class SSFluid extends Fluid {

		private int color;

		public int moisture;
		public float moistureSaturation;

		public SSFluid(String fluidName, int color, int moisture, float moistureSaturation) {
			super(fluidName);
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
	        return ClientEventHandler.waterStill;
	    }

		@SideOnly(Side.CLIENT)
	    public IIcon getFlowingIcon()
	    {
	        return ClientEventHandler.waterStill;
	    }

	}

}
