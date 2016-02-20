package shift.sextiarysector.api.gearforce.tileentity;

import net.minecraftforge.common.util.ForgeDirection;

public interface IGearForceGrid {

    boolean canIn(ForgeDirection from);

    boolean canOut(ForgeDirection from);

}
