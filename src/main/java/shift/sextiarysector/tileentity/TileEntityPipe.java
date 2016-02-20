package shift.sextiarysector.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import shift.sextiarysector.api.EnumColor;
import shift.sextiarysector.block.BlockPipe;

public class TileEntityPipe extends TileEntity implements IFluidHandler {

    protected FluidTank tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME * 16);
    private final boolean isIN[] = { false, false, false, false, false, false };
    public EnumColor color = EnumColor.Unknown;

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

    public void updateServerEntity() {

        if (this.tank.getFluidAmount() > 0 && getConnectSize() > 0) this.addFluid();
        this.clearIN();

    }

    private void addFluid() {

        int add = Math.min(50, this.tank.getFluidAmount() / getConnectSize());
        int doAdd = 0;

        for (ForgeDirection d : ForgeDirection.VALID_DIRECTIONS) {

            if (!BlockPipe.canConnect(getWorldObj(), xCoord, yCoord, zCoord, d)) continue;

            TileEntity t = this.worldObj.getTileEntity(xCoord + d.offsetX, yCoord + d.offsetY, zCoord + d.offsetZ);

            if (!isIN[d.ordinal()] && t instanceof IFluidHandler) {

                FluidStack f = this.tank.getFluid().copy();
                f.amount = add;

                int i = ((IFluidHandler) t).fill(d.getOpposite(), f, true);
                if (i > 0) doAdd += i;

            }

        }

        this.tank.drain(doAdd, true);

    }

    private void clearIN() {

        for (int i = 0; i < isIN.length; i++) {
            isIN[i] = false;
        }

    }

    private int getConnectSize() {

        int i = 0;

        for (ForgeDirection d : ForgeDirection.VALID_DIRECTIONS) {

            if (!BlockPipe.canConnect(getWorldObj(), xCoord, yCoord, zCoord, d)) continue;

            TileEntity t = this.worldObj.getTileEntity(xCoord + d.offsetX, yCoord + d.offsetY, zCoord + d.offsetZ);

            if (!isIN[d.ordinal()] && t instanceof IFluidHandler && ((IFluidHandler) t).fill(d.getOpposite(), tank.getFluid(), false) > 0) {
                i++;
            }

        }

        return i;

    }

    private int getIsIN() {

        int i = 0;

        for (boolean b : isIN) {
            if (b) i++;
        }

        return i;

    }

    public void getFluidFromSuctionMachine(ForgeDirection direction) {

        int i = 0;

        for (ForgeDirection d : ForgeDirection.VALID_DIRECTIONS) {

            if (!BlockPipe.canConnect(getWorldObj(), xCoord, yCoord, zCoord, d)) continue;

            TileEntity t = this.worldObj.getTileEntity(xCoord + d.offsetX, yCoord + d.offsetY, zCoord + d.offsetZ);

            if (t instanceof IFluidHandler) {
                i++;
            }

        }

        if (i > 2) return;

        for (ForgeDirection d : ForgeDirection.VALID_DIRECTIONS) {

            if (direction.equals(d)) continue;

            int x = this.xCoord + d.offsetX;
            int y = this.yCoord + d.offsetY;
            int z = this.zCoord + d.offsetZ;

            TileEntity t = this.worldObj.getTileEntity(x, y, z);

            if (t instanceof TileEntityPipe) {
                ((TileEntityPipe) t).getFluidFromSuctionMachine(d.getOpposite());
                break;
            } else if (t instanceof IFluidHandler) {
                this.updateServerInWorkEntity(d);
            }

        }

    }

    public void updateServerInWorkEntity(ForgeDirection direction) {

        int x = this.xCoord + direction.offsetX;
        int y = this.yCoord + direction.offsetY;
        int z = this.zCoord + direction.offsetZ;

        if (this.worldObj.getTileEntity(x, y, z) instanceof IFluidHandler) {

            IFluidHandler t = (IFluidHandler) this.worldObj.getTileEntity(x, y, z);

            if (this.tank.getFluid() == null) {

                if (t.getTankInfo(direction.getOpposite()) != null && t.getTankInfo(direction.getOpposite())[0].fluid != null
                        && t.canDrain(direction.getOpposite(), t.getTankInfo(direction.getOpposite())[0].fluid.getFluid())) {
                    FluidStack fs = t.drain(direction.getOpposite(), FluidContainerRegistry.BUCKET_VOLUME, true);
                    this.fill(direction, fs, true);
                }

            } else if (t.canDrain(direction.getOpposite(), this.tank.getFluid().getFluid())) {
                FluidStack fs = t.drain(direction.getOpposite(), FluidContainerRegistry.BUCKET_VOLUME - this.tank.getFluidAmount(), true);
                this.fill(direction, fs, true);
            }

        }

    }

    /* IFluidHandler */
    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        int i = tank.fill(resource, doFill);

        if (i > 0 && doFill) {
            this.isIN[from.ordinal()] = true;
        }

        return i;
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        if (resource == null || !resource.isFluidEqual(tank.getFluid())) {
            return null;
        }
        return tank.drain(resource.amount, doDrain);
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        return tank.drain(maxDrain, doDrain);
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
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

    /* NBT */
    @Override
    public void readFromNBT(NBTTagCompound par1nbtTagCompound) {

        super.readFromNBT(par1nbtTagCompound);
        tank.readFromNBT(par1nbtTagCompound);
        if (par1nbtTagCompound.hasKey("Empty") && tank.getFluidAmount() > 0) this.tank.setFluid(null);
        color = EnumColor.getColor(par1nbtTagCompound.getInteger("color"));

    }

    @Override
    public void writeToNBT(NBTTagCompound par1nbtTagCompound) {

        super.writeToNBT(par1nbtTagCompound);
        tank.writeToNBT(par1nbtTagCompound);
        par1nbtTagCompound.setInteger("color", color.ordinal());

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

    public boolean isStraight() {

        int i = 0;
        int x = this.xCoord;
        int y = this.yCoord;
        int z = this.zCoord;

        if (this.worldObj.getBlock(x + 1, y, z) == this.getBlockType() && this.worldObj.getBlock(x - 1, y, z) == this.getBlockType()) i++;
        if (this.worldObj.getBlock(x, y + 1, z) == this.getBlockType() && this.worldObj.getBlock(x, y - 1, z) == this.getBlockType()) i++;
        if (this.worldObj.getBlock(x, y, z + 1) == this.getBlockType() && this.worldObj.getBlock(x, y, z - 1) == this.getBlockType()) i++;

        return i == 1;
    }

}
