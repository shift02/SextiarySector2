package shift.sextiarysector.fmp;

import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.api.gearforce.tileentity.EnergyStorage;
import shift.sextiarysector.api.gearforce.tileentity.IGearForceHandler;

public interface IShaft extends IGearForceHandler {

    public void setDirection(ForgeDirection d);

    public ForgeDirection getDirection();

    public void setRotateStep(float r);

    public float getRotateStep();

    public EnergyStorage getStorage();

    public IShaft getInTileEntityShaft();

    public IShaft getOutTileEntityShaft();

}
