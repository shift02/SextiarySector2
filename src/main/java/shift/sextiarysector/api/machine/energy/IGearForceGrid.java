package shift.sextiarysector.api.machine.energy;

import net.minecraftforge.common.util.ForgeDirection;

public interface IGearForceGrid {

	boolean canIn(ForgeDirection from);

	boolean canOut(ForgeDirection from);

}
