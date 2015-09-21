package shift.sextiarysector.tileentity;

import java.util.ArrayList;
import java.util.UUID;

import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
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
import shift.mceconomy2.api.MCEconomyAPI;
import shift.sextiarysector.api.season.SeasonAPI;
import shift.sextiarysector.container.ItemBox;
import shift.sextiarysector.module.ModuleStatistics;

public class TileEntityShippingBox extends TileEntity implements IInventory, IFluidHandler {

    public UUID player = null;
    public double mp = 0;
    public double lastmp = 0;
    protected ItemBox items = new ItemBox("Base", 1);
    public FluidTank tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME * 8);

    public void setPlayer(EntityPlayer p) {

        player = p.getGameProfile().getId();

    }

    @Override
    public void updateEntity() {

        if (!this.worldObj.isRemote) {
            this.updateServerEntity();
        }

    }

    public void updateServerEntity() {

        if (SeasonAPI.getHour(getWorldObj(), 1) != 7 || SeasonAPI.getMinute(getWorldObj()) != 0) return;

        if (lastmp > 0) {

            mp += lastmp;

            ItemStack p_77648_1_ = this.getFireworks();

            EntityFireworkRocket entityfireworkrocket = new EntityFireworkRocket(worldObj, xCoord + 0.5f, yCoord + 1.2f, zCoord + 0.5f, p_77648_1_);
            getWorldObj().spawnEntityInWorld(entityfireworkrocket);

            if (lastmp > 1000) {

                ItemStack p_77648_2_ = this.getFireworks();

                EntityFireworkRocket entityfireworkrocket2 = new EntityFireworkRocket(worldObj, xCoord + 0.5f, yCoord + 1.7f, zCoord + 0.5f, p_77648_2_);
                getWorldObj().spawnEntityInWorld(entityfireworkrocket2);

            }

            if (lastmp > 10000) {

                for (int i = 0; i < 4; i++) {

                    ItemStack p_77648_3_ = this.getFireworks();

                    EntityFireworkRocket entityfireworkrocket3 = new EntityFireworkRocket(worldObj, xCoord + this.worldObj.rand.nextFloat(), yCoord + 1.7f, zCoord + this.worldObj.rand.nextFloat(), p_77648_3_);
                    getWorldObj().spawnEntityInWorld(entityfireworkrocket3);

                }

            }

            lastmp = 0;

        }

    }

    private ItemStack getFireworks() {

        ItemStack p_77648_1_ = new ItemStack(Items.fireworks);

        NBTTagCompound nbttagcompound = new NBTTagCompound();//Explosion

        ArrayList arraylist = new ArrayList();
        arraylist.add(Integer.valueOf(ItemDye.field_150922_c[2]));
        int[] aint1 = new int[arraylist.size()];

        for (int l2 = 0; l2 < aint1.length; ++l2) {
            aint1[l2] = ((Integer) arraylist.get(l2)).intValue();
        }

        if (SeasonAPI.getHour(getWorldObj(), 0) == 7) {
            nbttagcompound.setBoolean("Flicker", true);
        } else {
            nbttagcompound.setBoolean("Trail", true);
        }

        nbttagcompound.setIntArray("Colors", aint1);
        nbttagcompound.setByte("Type", (byte) 3);

        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        NBTTagCompound nbttagcompound2 = new NBTTagCompound();
        NBTTagList nbttaglist = new NBTTagList();

        nbttaglist.appendTag(nbttagcompound);

        nbttagcompound1.setTag("Explosions", nbttaglist);
        nbttagcompound1.setByte("Flight", (byte) 3);
        nbttagcompound2.setTag("Fireworks", nbttagcompound1);

        p_77648_1_.setTagCompound(nbttagcompound2);

        return p_77648_1_;

    }

    //IInventory関係
    @Override
    public int getSizeInventory() {
        return items.getSizeInventory();
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return items.getStackInSlot(i);
    }

    @Override
    public ItemStack decrStackSize(int i, int j) {
        return items.decrStackSize(i, j);
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        return items.getStackInSlotOnClosing(i);
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack) {
        //items.setInventorySlotContents(i, itemstack);

        if (i != 0 || itemstack == null) return;

        int mp = 0;

        mp = MCEconomyAPI.getPurchase(itemstack) * itemstack.stackSize;

        if (mp > 0 && player != null && this.worldObj.func_152378_a(player) != null) {
            this.worldObj.func_152378_a(player).addStat(ModuleStatistics.objectSellStats[Item.getIdFromItem(itemstack.getItem())], itemstack.stackSize);
        }

        this.lastmp += mp;

    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return items.getInventoryStackLimit();
    }

    @Override
    public void markDirty() {
        super.markDirty();
        items.onInventoryChanged();
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : entityplayer.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory() {
    }

    @Override
    public void closeInventory() {
    }

    @Override
    public String getInventoryName() {
        return "ss.shipping_box";
    }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack itemstack) {

        if (itemstack == null) return false;

        return MCEconomyAPI.hasPurchase(itemstack);

    }

    /* IFluidHandler */
    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        //return tank.fill(resource, doFill);

        double mp = 0;

        if (!MCEconomyAPI.hasFluidPurchase(resource.getFluid())) return 0;

        mp = MCEconomyAPI.getFluidPurchase(resource.getFluid()) * resource.amount;

        if (mp > 0 && doFill) {
            this.lastmp += mp;
        }

        return resource.amount;

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
    public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
        super.readFromNBT(par1nbtTagCompound);
        this.items.readFromNBT(par1nbtTagCompound);
        tank.readFromNBT(par1nbtTagCompound);
        if (par1nbtTagCompound.hasKey("Empty") && tank.getFluidAmount() > 0) this.tank.setFluid(null);
        this.mp = par1nbtTagCompound.getDouble("mp");
        this.lastmp = par1nbtTagCompound.getDouble("lastmp");
        if (par1nbtTagCompound.hasKey("owner")) {
            long i = par1nbtTagCompound.getLong("uuid1");
            long j = par1nbtTagCompound.getLong("uuid2");
            this.player = new UUID(i, j);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
        super.writeToNBT(par1nbtTagCompound);
        this.items.writeToNBT(par1nbtTagCompound);
        tank.writeToNBT(par1nbtTagCompound);
        par1nbtTagCompound.setDouble("mp", this.mp);
        par1nbtTagCompound.setDouble("lastmp", this.lastmp);
        if (this.player != null) {
            par1nbtTagCompound.setBoolean("owner", true);

            par1nbtTagCompound.setLong("uuid1", this.player.getMostSignificantBits());
            par1nbtTagCompound.setLong("uuid2", this.player.getLeastSignificantBits());

        }

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
