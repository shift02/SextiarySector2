package shift.sextiarysector.api.agriculture;

import net.minecraftforge.fluids.IFluidHandler;

public interface IFarmland extends IFluidHandler {

    public boolean canGrowth();

    public void growth();

}
