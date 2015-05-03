package shift.sextiarysector.fluid;

import shift.sextiarysector.SSFluids.SSFluid;
import shift.sextiarysector.api.EnumColor;

public class FluidColor extends SSFluid {

	public EnumColor color;

	public FluidColor(String fluidName, EnumColor color) {
		super(fluidName, 0, 0, 0, 0);
		this.color = color;
	}

	@Override
	public int getColor()
	{
		return color.color;
	}

}
