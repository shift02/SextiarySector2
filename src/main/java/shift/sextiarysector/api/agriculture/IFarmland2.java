package shift.sextiarysector.api.agriculture;

import net.minecraftforge.fluids.IFluidHandler;

public interface IFarmland2 extends IFluidHandler {

    public boolean canGrowth();

    public void growth();

}
