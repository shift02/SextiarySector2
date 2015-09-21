package shift.sextiarysector.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import shift.sextiarysector.SSBlocks;

public class EntityMineboatTank extends EntityMineboat implements IFluidHandler {

    protected FluidTank tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME * 32);
    private int lastID = 0;
    private int lastAmount = 0;

    public EntityMineboatTank(World par1World) {
        super(par1World);

    }

    public EntityMineboatTank(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
    }

    @Override
    protected void entityInit() {

        super.entityInit();
        this.dataWatcher.addObject(23, new Integer(0));
        this.dataWatcher.addObject(24, new Integer(0));

    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.worldObj.isRemote) {
            this.updateClientEntity();
        } else {

            this.updateServerEntity();

        }

    }

    public void updateClientEntity() {

        if (this.lastID != this.getFluidID()) {

            if (this.getFluidID() == 0) {
                this.tank.setFluid(null);
            } else {
                this.tank.setFluid(new FluidStack(this.getFluidID(), this.getFluidAmount()));
            }

            this.lastID = this.getFluidID();
            this.lastAmount = this.getFluidAmount();

        }

        if (this.lastAmount != this.getFluidAmount()) {
            this.tank.getFluid().amount = this.getFluidAmount();

            this.lastID = this.getFluidID();
            this.lastAmount = this.getFluidAmount();

        }

    }

    private void updateServerEntity() {

        if (this.lastAmount != this.tank.getFluidAmount()) {

            this.dataWatcher.updateObject(24, this.tank.getFluidAmount());

            if (this.tank.getFluidAmount() == 0) {
                this.dataWatcher.updateObject(23, 0);
            } else {
                this.dataWatcher.updateObject(23, this.tank.getFluid().getFluid().getID());
            }

            this.lastAmount = this.tank.getFluidAmount();

        }

    }

    private int getFluidID() {
        return this.dataWatcher.getWatchableObjectInt(23);
    }

    private int getFluidAmount() {
        return this.dataWatcher.getWatchableObjectInt(24);
    }

    public float getRendererAmount() {
        return (float) this.tank.getFluidAmount() / (float) this.tank.getCapacity();
    }

    public boolean hasFluidStack() {
        return tank.getFluidAmount() > 0;
    }

    public FluidStack getFluidStack() {
        return tank.getFluid();
    }

    @Override
    public Block getDefaultDisplayTile() {
        return SSBlocks.tank;
    }

    @Override
    public void killMineBoat(DamageSource par1DamageSource) {
        super.killMineBoat(par1DamageSource);
        this.func_145778_a(Item.getItemFromBlock(SSBlocks.tank), 1, 0.0F);
    }

    @Override
    //ここに重要な事がある。
    public boolean interactFirst(EntityPlayer par1EntityPlayer) {
        return true;
    }

    /* IFluidHandler */
    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        return tank.fill(resource, doFill);
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

    //NBT
    @Override
    protected void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        tank.writeToNBT(par1NBTTagCompound);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        tank.readFromNBT(par1NBTTagCompound);
        if (par1NBTTagCompound.hasKey("Empty") && tank.getFluidAmount() > 0) this.tank.setFluid(null);
    }

}
