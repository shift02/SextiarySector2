package shift.sextiarysector.fmp;

import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.api.machine.energy.EnergyStorage;
import shift.sextiarysector.api.machine.energy.IGFEnergyHandler;

public interface IShaft extends IGFEnergyHandler {

	public void setDirection(ForgeDirection d);

	public ForgeDirection getDirection();

	public void setRotateStep(float r);

	public float getRotateStep();

	public EnergyStorage getStorage();

	public IShaft getInTileEntityShaft();

	public IShaft getOutTileEntityShaft();

}
