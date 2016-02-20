/*
* 作成者: Shift02
* 作成日: 2016/02/15 - 18:05:29
*/
package shift.sextiarysector.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import shift.sextiarysector.api.agriculture.TileFarmland;

public class TileEntityWaterSupplyMachine extends TileEntity implements IFluidHandler {

    public FluidTank tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME * 16);
    protected int lastAmount;

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

        if (getWorldObj().getWorldTime() % 40 != 0) return;
        if (this.tank.getFluidAmount() < 1000) return;

        if (getWorldObj().rand.nextBoolean()) {

            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {

                    if (i == 0 && j == 0) continue;
                    if (this.tank.getFluidAmount() < 1000) break;

                    TileEntity t = getWorldObj().getTileEntity(xCoord + i, yCoord, zCoord + j);

                    if (!(t instanceof TileFarmland)) continue;
                    TileFarmland f = (TileFarmland) t;
                    if (f.addWater(10) > 0) this.tank.drain(1000, true);

                }
            }

        } else {

            for (int i = 1; i > -2; i--) {
                for (int j = 1; j > -2; j--) {

                    if (i == 0 && j == 0) continue;
                    if (this.tank.getFluidAmount() < 1000) break;

                    TileEntity t = getWorldObj().getTileEntity(xCoord + i, yCoord, zCoord + j);

                    if (!(t instanceof TileFarmland)) continue;
                    TileFarmland f = (TileFarmland) t;
                    if (f.addWater(10) > 0) this.tank.drain(1000, true);

                }
            }

        }

    }

    public boolean hasFluidStack() {
        return tank.getFluidAmount() > 0;
    }

    public FluidStack getFluidStack() {
        return tank.getFluid();
    }

    @Override
    public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
        super.readFromNBT(par1nbtTagCompound);
        tank.readFromNBT(par1nbtTagCompound);
        if (par1nbtTagCompound.hasKey("Empty") && tank.getFluidAmount() > 0) this.tank.setFluid(null);
    }

    @Override
    public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
        super.writeToNBT(par1nbtTagCompound);
        tank.writeToNBT(par1nbtTagCompound);
    }

    /* IFluidHandler */
    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {

        if (!this.canFill(from, resource.getFluid())) return 0;

        return tank.fill(resource, doFill);

    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        return null;
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        return null;
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        if (!from.equals(ForgeDirection.UP)) return false;
        if (fluid != null && !fluid.equals(FluidRegistry.WATER)) return false;
        return true;
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return true;
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        return new FluidTankInfo[] { tank.getInfo() };
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        writeToNBT(nbt);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        readFromNBT(pkt.func_148857_g());
        //this.worldObj.func_147479_m(xCoord, yCoord, zCoord);
        this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }

}
