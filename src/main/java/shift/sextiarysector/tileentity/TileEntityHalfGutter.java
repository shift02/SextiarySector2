/*
* 作成者: Shift02
* 作成日: 2016/03/10 - 13:41:42
*/
package shift.sextiarysector.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import shift.sextiarysector.tileentity.TileEntityGutter.FluidTankDirection;

public class TileEntityHalfGutter extends TileEntityDirection implements IFluidHandler {

    protected FluidTankDirection[] tanks = new FluidTankDirection[5];

    private int MAX_VOLUME = 600;
    private int MAX_MOVE_VOLUME = 100;

    private int downClient = 0;
    private int downClient2 = 0;

    public TileEntityHalfGutter() {

        tanks[0] = new FluidTankDirection(MAX_VOLUME, ForgeDirection.UNKNOWN);
        tanks[1] = new FluidTankDirection(MAX_VOLUME, ForgeDirection.EAST);
        tanks[2] = new FluidTankDirection(MAX_VOLUME, ForgeDirection.WEST);
        tanks[3] = new FluidTankDirection(MAX_VOLUME, ForgeDirection.NORTH);
        tanks[4] = new FluidTankDirection(MAX_VOLUME, ForgeDirection.SOUTH);

    }

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

        for (int i = 0; i < this.tanks.length; i++) {
            this.tanks[i].updateRenderer();
        }

        updateDown();

    }

    private void updateServerEntity() {

        this.moveFluid();

        boolean f = false;
        for (int i = 0; i < this.tanks.length; i++) {
            if (this.tanks[i].canRendererUpdate()) f = true;
        }
        if (f) this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

    }

    public void moveFluid() {

        if (this.getWorldObj().getWorldTime() % 4 == 2) {

            moveFluidC(this.getTank(ForgeDirection.UNKNOWN));

            moveUPFluid();

        }

    }

    private void moveUPFluid() {

        for (int i = 0; i < this.tanks.length; i++) {

            if (this.tanks[i].getSide().equals(ForgeDirection.UP)) continue;

            if (this.tanks[i].getSide().equals(ForgeDirection.UNKNOWN)) continue;

            this.moveFluidD(this.tanks[i]);

        }

    }

    //中心
    public void moveFluidC(FluidTankDirection tank) {

        if (tank.getFluidAmount() == 0) return;

        int down = getDownFluidHandler();
        if (down == 0) return;

        downClient = 0;

        if (this.worldObj.getTileEntity(xCoord, yCoord - down, zCoord) instanceof IFluidHandler) {

            IFluidHandler f = (IFluidHandler) this.worldObj.getTileEntity(xCoord, yCoord - down, zCoord);

            if (f.canFill(ForgeDirection.UP, tank.getFluid().getFluid())) {

                FluidStack fs = tank.getFluid().copy();
                if (fs.amount > MAX_MOVE_VOLUME) fs.amount = MAX_MOVE_VOLUME;
                int i = f.fill(ForgeDirection.UP, fs, true);
                tank.drain(i, true);

                if (i > 0 || tank.getFluidAmount() > 0) {

                    downClient = down;

                    if (this.worldObj.getTileEntity(xCoord, yCoord - down, zCoord) instanceof TileEntityGutter) {
                        ((TileEntityGutter) this.worldObj.getTileEntity(xCoord, yCoord - down, zCoord)).setUPHalfGutter();
                    }

                }

            }

        }

    }

    private int getDownFluidHandler() {

        if (this.yCoord == 0) return 0;

        int d = 1;

        for (int i = 1; i <= 20; i++) {
            if (!this.getWorldObj().isAirBlock(xCoord, yCoord - i, zCoord)) {
                d = i;
                break;
            }
        }

        return d;

    }

    public int getDownClient() {
        return downClient2;
    }

    private int downCount;

    private void updateDown() {

        if (this.downClient > this.downClient2) {

            downCount++;

            if (downCount > 1) {
                downCount = 0;
                this.downClient2++;
            }

        }

        if (this.downClient < this.downClient2) {

            downCount++;

            if (downCount > 5) {
                downCount = 0;
                this.downClient2--;
            }

        }

    }

    //side
    public void moveFluidD(FluidTankDirection tank) {

        if (tank.getFluidAmount() == 0) return;

        FluidTankDirection c = this.getTank(ForgeDirection.UNKNOWN);

        //中
        FluidStack fs = tank.getFluid().copy();
        if (fs.amount > MAX_MOVE_VOLUME) fs.amount = MAX_MOVE_VOLUME;
        int i = c.fill(fs, true);

        tank.drain(i, true);

    }

    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {

        if (this.getTank(from) == null) return 0;

        if (from.equals(ForgeDirection.UNKNOWN)) return 0;

        if (!from.equals(getDirection())) return 0;

        return this.getTank(from).fill(resource, doFill);
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

        return from.equals(getDirection());

    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return false;
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {

        if (this.getTank(from) == null) return null;

        return new FluidTankInfo[] { this.getTank(from).getInfo() };
    }

    //NBT
    @Override
    public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
        super.readFromNBT(par1nbtTagCompound);

        for (int i = 0; i < this.tanks.length; i++) {

            if (!par1nbtTagCompound.hasKey("tanks" + i)) continue;
            NBTTagCompound nbt = (NBTTagCompound) par1nbtTagCompound.getTag("tanks" + i);
            tanks[i].readFromNBT(nbt);

        }

        downClient = par1nbtTagCompound.getInteger("downClient");

    }

    @Override
    public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
        super.writeToNBT(par1nbtTagCompound);

        for (int i = 0; i < this.tanks.length; i++) {

            NBTTagCompound nbt = new NBTTagCompound();
            tanks[i].writeToNBT(nbt);
            par1nbtTagCompound.setTag("tanks" + i, nbt);
        }

        par1nbtTagCompound.setInteger("downClient", downClient);

    }

    public FluidTankDirection getTank(ForgeDirection direction) {
        for (int i = 0; i < this.tanks.length; i++) {
            if (this.tanks[i].getSide().equals(direction)) return this.tanks[i];
        }

        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        AxisAlignedBB bb = INFINITE_EXTENT_AABB;

        bb = AxisAlignedBB.getBoundingBox(xCoord, yCoord - this.getDownClient(), zCoord, xCoord + 1, yCoord + 1, zCoord + 1);

        return bb;
    }

}
