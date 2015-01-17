package shift.sextiarysector.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TileEntitySquare extends TileEntityDirection  implements  IFluidHandler{

	protected FluidTank tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME * 16);

	private int lastFluid;

	@Override
	public void updateEntity() {
		super.updateEntity();
		if (this.worldObj.isRemote) {
			this.updateClientEntity();
		} else {

			this.updateServerEntity();

		}

	}

	public void updateClientEntity() {
	}

	private void updateServerEntity() {

		if((lastFluid)!=(tank.getFluidAmount()/100)){
			this.lastFluid = (tank.getFluidAmount()/100);
			this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}

		if(this.direction.ordinal() != ForgeDirection.UP.ordinal() && this.tank.getFluidAmount() > 0)this.chargeFluid();

	}

	private void chargeFluid(){

		if(this.worldObj.getTileEntity(xCoord + this.direction.offsetX, yCoord  + this.direction.offsetY, zCoord + this.direction.offsetZ) instanceof IFluidHandler){
			IFluidHandler f = (IFluidHandler) this.worldObj.getTileEntity(xCoord + this.direction.offsetX, yCoord  + this.direction.offsetY, zCoord + this.direction.offsetZ);

			if(f.canFill(this.getDirection().getOpposite(), this.tank.getFluid().getFluid())){
				FluidStack fs = this.tank.getFluid().copy();
				if(fs.amount> 500)fs.amount=500;
				int i = f.fill(this.getDirection().getOpposite(), fs, true);
				this.tank.drain(i, true);
				this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			}

		}

	}

	/* IFluidHandler */
    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
    {
    	if(from.ordinal() == this.getDirection().ordinal())return 0;
        return tank.fill(resource, doFill);
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
    {
        if (resource == null || !resource.isFluidEqual(tank.getFluid()))
        {
            return null;
        }
        return tank.drain(resource.amount, doDrain);
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
    {
        return tank.drain(maxDrain, doDrain);
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid)
    {
    	if(from.ordinal() == this.getDirection().ordinal())return false;
        return true;
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid)
    {
        return true;
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from)
    {
        return new FluidTankInfo[] { tank.getInfo() };
    }

    public boolean hasFluid(){
    	return this.tank.getFluidAmount() > 0 ;
    }

    public IIcon getFluidIcon(){
    	return this.tank.getFluid().getFluid().getIcon(this.tank.getFluid());
    }

    public int getFluidColor(){
    	return this.tank.getFluid().getFluid().getColor(this.tank.getFluid());
    }

    public float getFluidHeight(){
		return ((float)this.tank.getFluidAmount()/(float)this.tank.getCapacity())*(11.0f/16.0f);
    }

    public int getFluidID(){
    	return tank.getFluid().fluidID;
    }

    public FluidStack getFluidStack(){
    	return this.tank.getFluid();
    }

    /* NBT */
	@Override
	public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
		super.readFromNBT(par1nbtTagCompound);
		tank.readFromNBT(par1nbtTagCompound);
		if(par1nbtTagCompound.hasKey("Empty") && tank.getFluidAmount() > 0)this.tank.setFluid(null);
	}

	@Override
	public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
		super.writeToNBT(par1nbtTagCompound);
		tank.writeToNBT(par1nbtTagCompound);
	}

}
