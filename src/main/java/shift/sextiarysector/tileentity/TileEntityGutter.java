/*
* 作成者: Shift02
* 作成日: 2016/03/07 - 15:21:04
*/
package shift.sextiarysector.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TileEntityGutter extends TileEntityDirection implements IFluidHandler {

    protected FluidTankDirection[] tanks = new FluidTankDirection[6];

    private int MAX_VOLUME = 600;
    private int MAX_MOVE_VOLUME = 100;

    private int count = 0;

    private ForgeDirection centralMove = ForgeDirection.UNKNOWN;

    private int upHalfGutter;

    /** 流れの向き */
    //private ForgeDirection fluidDirection = ForgeDirection.UNKNOWN;

    public TileEntityGutter() {

        tanks[0] = new FluidTankDirection(MAX_VOLUME, ForgeDirection.UP);
        tanks[1] = new FluidTankDirection(MAX_VOLUME, ForgeDirection.UNKNOWN);
        tanks[2] = new FluidTankDirection(MAX_VOLUME, ForgeDirection.EAST);
        tanks[3] = new FluidTankDirection(MAX_VOLUME, ForgeDirection.WEST);
        tanks[4] = new FluidTankDirection(MAX_VOLUME, ForgeDirection.NORTH);
        tanks[5] = new FluidTankDirection(MAX_VOLUME, ForgeDirection.SOUTH);

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

    }

    private void updateServerEntity() {

        this.moveFluid();

        boolean f = false;
        if (this.upHalfGutter > 0) {
            this.upHalfGutter--;
            if (this.upHalfGutter == 0) f = true;
        }

        for (int i = 0; i < this.tanks.length; i++) {
            if (this.tanks[i].canRendererUpdate()) f = true;
        }
        if (f) this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

    }

    public void moveFluid() {

        if (this.getWorldObj().getWorldTime() % 4 == 0) {

            if (!centralMove.equals(ForgeDirection.UNKNOWN)) {
                this.getTank(centralMove).setFluidDirection(centralMove);
                this.moveFluidD(this.getTank(centralMove));
                centralMove = ForgeDirection.UNKNOWN;
            }

        } else if (this.getWorldObj().getWorldTime() % 4 == 2) {

            this.moveFluidC(this.getTank(ForgeDirection.UNKNOWN));

            //ランダム性を出す
            if (this.getWorldObj().rand.nextBoolean()) {
                this.moveUPFluid();
            } else {
                this.moveDownFluid();
            }

            this.moveFluidUP(this.getTank(ForgeDirection.UP));

        }

    }

    private void moveUPFluid() {

        for (int i = 0; i < this.tanks.length; i++) {

            if (this.tanks[i].getSide().equals(ForgeDirection.UP)) continue;

            if (this.tanks[i].getSide().equals(ForgeDirection.UNKNOWN)) continue;

            if (centralMove.equals(this.tanks[i].getSide())) continue;

            this.moveFluidD(this.tanks[i]);

        }

    }

    private void moveDownFluid() {

        for (int i = this.tanks.length - 1; i >= 0; i--) {

            if (this.tanks[i].getSide().equals(ForgeDirection.UP)) continue;

            if (this.tanks[i].getSide().equals(ForgeDirection.UNKNOWN)) continue;

            if (centralMove.equals(this.tanks[i].getSide())) continue;

            this.moveFluidD(this.tanks[i]);

        }

    }

    public void moveFluidUP(FluidTankDirection tank) {

        if (tank.getFluidAmount() == 0) return;

        FluidStack fs = tank.getFluid().copy();
        if (fs.amount > MAX_MOVE_VOLUME) fs.amount = MAX_MOVE_VOLUME;
        int i = this.getTank(ForgeDirection.UNKNOWN).fill(fs, true);
        tank.drain(i, true);

    }

    //中心
    public void moveFluidC(FluidTankDirection tank) {

        if (tank.getFluidAmount() == 0) return;

        ForgeDirection d = ForgeDirection.UNKNOWN;

        d = tank.fluidDirection;

        //たまに液体の方向性にする
        if (d.equals(ForgeDirection.UNKNOWN)) {

            if (this.getTank(getDirection()).getFluidAmount() > this.getTank(getDirection().getOpposite()).getFluidAmount()) {
                d = getDirection().getOpposite();

            }

            if (this.getTank(getDirection()).getFluidAmount() < this.getTank(getDirection().getOpposite()).getFluidAmount()) {
                d = getDirection();
            }

        }

        if (!d.equals(ForgeDirection.UNKNOWN)) {

            FluidStack fs = tank.getFluid().copy();
            if (fs.amount > MAX_MOVE_VOLUME) fs.amount = MAX_MOVE_VOLUME;
            int i = this.getTank(d).fill(fs, true);
            if (i > 0) {
                this.getTank(d).setFluidDirection(d);
                centralMove = d;
            }
            tank.drain(i, true);

            //System.out.println("AAAA");

        } else if (this.getTank(getDirection()).getFluidAmount() <= tank.getFluidAmount() && this.getTank(getDirection().getOpposite()).getFluidAmount() <= tank.getFluidAmount()) {

            int total = this.getTank(getDirection()).getFluidAmount() + tank.getFluidAmount() + this.getTank(getDirection().getOpposite()).getFluidAmount();
            int total_3 = total / 3;

            //半分こ
            FluidStack fs1 = tank.getFluid().copy();
            fs1.amount = total_3 - this.getTank(getDirection()).getFluidAmount();
            if (fs1.amount > MAX_MOVE_VOLUME / 2) fs1.amount = MAX_MOVE_VOLUME / 2;
            int i = this.getTank(getDirection()).fill(fs1, true);
            if (i > 0) {
                this.getTank(getDirection()).setFluidDirection(getDirection());
            }
            tank.drain(i, true);

            FluidStack fs2 = tank.getFluid().copy();
            fs2.amount = total_3 - this.getTank(getDirection().getOpposite()).getFluidAmount();
            if (fs2.amount > MAX_MOVE_VOLUME / 2) fs2.amount = MAX_MOVE_VOLUME / 2;
            int i2 = this.getTank(getDirection().getOpposite()).fill(fs2, true);
            if (i2 > 0) {
                this.getTank(getDirection().getOpposite()).setFluidDirection(getDirection().getOpposite());
            }
            tank.drain(i2, true);

        }

        tank.fluidDirection = ForgeDirection.UNKNOWN;

    }

    //side
    public void moveFluidD(FluidTankDirection tank) {

        if (tank.getFluidAmount() == 0) return;

        FluidTankDirection c = this.getTank(ForgeDirection.UNKNOWN);

        //外へ
        if (c.getFluidAmount() > tank.getFluidAmount() || (c.getFluidAmount() >= tank.getFluidAmount() && this.getWorldObj().rand.nextInt(2) == 0) || tank.getSide().equals(tank.fluidDirection)) {

            tank.fluidDirection = ForgeDirection.UNKNOWN;

            if (this.worldObj.getTileEntity(xCoord + tank.getSide().offsetX, yCoord + tank.getSide().offsetY, zCoord + tank.getSide().offsetZ) instanceof IFluidHandler) {

                IFluidHandler f = (IFluidHandler) this.worldObj.getTileEntity(xCoord + tank.getSide().offsetX, yCoord + tank.getSide().offsetY, zCoord + tank.getSide().offsetZ);

                if (f.canFill(tank.getSide().getOpposite(), tank.getFluid().getFluid())) {

                    FluidStack fs = tank.getFluid().copy();
                    if (fs.amount > MAX_MOVE_VOLUME) fs.amount = MAX_MOVE_VOLUME;
                    int i = f.fill(tank.getSide().getOpposite(), fs, true);
                    tank.drain(i, true);

                    if (i == 0) {
                        //外に行けない
                        if (tank.canRegurgitation()) {
                            tank.fluidDirection = tank.getSide().getOpposite();
                            //c.setFluidDirection(tank.getSide().getOpposite());
                            //this.getTank(tank.getSide().getOpposite()).setFluidDirection(tank.getSide().getOpposite());
                        }
                    } else {
                        tank.fluidDirection = tank.getSide();
                    }
                    //this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

                }

            } else {
                //外に行けない
                if (tank.canRegurgitation()) {
                    tank.fluidDirection = tank.getSide().getOpposite();
                    //c.setFluidDirection(tank.getSide().getOpposite());
                    //this.getTank(tank.getSide().getOpposite()).setFluidDirection(tank.getSide().getOpposite());
                }

            }

        } else if (c.getFluidAmount() < tank.getFluidAmount() || tank.getSide().getOpposite().equals(tank.fluidDirection)) {

            //中
            FluidStack fs = tank.getFluid().copy();
            if (fs.amount > MAX_MOVE_VOLUME) fs.amount = MAX_MOVE_VOLUME;
            int i = c.fill(fs, true);
            if (i > 0) {
                c.setFluidDirection(tank.getSide().getOpposite());
            }
            tank.drain(i, true);

        }

    }

    //流体
    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {

        if (from.equals(ForgeDirection.DOWN)) return 0;

        if (from.equals(ForgeDirection.UP)) return this.getTank(from).fill(resource, doFill);

        if (!(from.equals(this.direction) || from.getOpposite().equals(this.direction))) return 0;

        int i = this.getTank(from).fill(resource, doFill);

        if (i > 0) {
            this.getTank(from).setFluidDirection(from.getOpposite());
        }

        return i;

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

        if (from.equals(ForgeDirection.UP)) return true;

        if (from.equals(getDirection()) || from.equals(getDirection().getOpposite())) return true;

        return false;

    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return false;
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {

        if (this.getTank(from) == null) return null;

        return new FluidTankInfo[] { getTank(from).getInfo() };
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

        this.upHalfGutter = par1nbtTagCompound.getInteger("upHalfGutter");

    }

    @Override
    public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
        super.writeToNBT(par1nbtTagCompound);

        for (int i = 0; i < this.tanks.length; i++) {

            NBTTagCompound nbt = new NBTTagCompound();
            tanks[i].writeToNBT(nbt);
            par1nbtTagCompound.setTag("tanks" + i, nbt);
        }

        par1nbtTagCompound.setInteger("upHalfGutter", this.upHalfGutter);

    }

    public FluidTankDirection getTank(ForgeDirection direction) {
        for (int i = 0; i < this.tanks.length; i++) {
            if (this.tanks[i].getSide().equals(direction)) return this.tanks[i];
        }

        return null;
    }

    public int getUPHalfGutter() {
        return this.upHalfGutter;
    }

    public void setUPHalfGutter() {

        int i = this.upHalfGutter;

        this.upHalfGutter = 20;

        if (i == 0) this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }

    static public class FluidTankDirection extends FluidTank {

        ForgeDirection side;
        int lastAmount = -1;

        /** 流れの向き */
        private ForgeDirection fluidDirection = ForgeDirection.UNKNOWN;

        private int regurgitation = 0;

        private int clientFluidAmount = 0;
        private FluidStack clientF;

        public FluidTankDirection(int capacity, ForgeDirection side) {
            super(capacity);
            this.side = side;
        }

        public ForgeDirection getSide() {
            return side;
        }

        public void setSide(ForgeDirection side) {
            this.side = side;
        }

        public boolean canRegurgitation() {

            this.regurgitation++;

            if (this.regurgitation > 4) {
                this.regurgitation = 0;
                return true;
            }

            return false;

        }

        public boolean canRendererUpdate() {

            if ((lastAmount / 10 != (this.getFluidAmount() / 10)) || (lastAmount != 0 && this.getFluidAmount() == 0)) {
                this.lastAmount = this.getFluidAmount();
                return true;
            }

            return false;

        }

        private int downCount = 0;

        public void updateRenderer() {

            int i = clientFluidAmount;

            if (this.clientFluidAmount > this.getFluidAmount()) {

                downCount++;

                if (downCount > 4) {
                    downCount = 0;
                    if (this.clientFluidAmount > this.getFluidAmount() + 10) {
                        this.clientFluidAmount -= 10;
                    } else {
                        this.clientFluidAmount--;
                    }
                }

            }

            if (this.clientFluidAmount < this.getFluidAmount()) {

                downCount = 0;

                if (this.clientFluidAmount + 10 < this.getFluidAmount()) {
                    this.clientFluidAmount += 10;
                } else if (this.clientFluidAmount + 2 < this.getFluidAmount()) {
                    this.clientFluidAmount += 2;
                } else {
                    this.clientFluidAmount++;
                }
            }

            if (i == 0 && clientFluidAmount > 0) {
                clientF = this.getFluid();
            }

            if (i > 0 && clientFluidAmount == 0) {
                clientF = null;
            }

        }

        public int getClientFluidAmount() {
            return clientFluidAmount;
        }

        public FluidStack getClientFluid() {
            return clientF;

        }

        public ForgeDirection getFluidDirection() {
            return fluidDirection;
        }

        public void setFluidDirection(ForgeDirection from) {

            if (fluidDirection.equals(ForgeDirection.UNKNOWN) || fluidDirection.equals(from)) {
                fluidDirection = from;
            } else {
                fluidDirection = ForgeDirection.UNKNOWN;
            }

        }

    }

}
