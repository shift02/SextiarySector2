package shift.sextiarysector.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import shift.sextiarysector.SSCrops;
import shift.sextiarysector.api.agriculture.IFertilizer;
import shift.sextiarysector.api.agriculture.TileFarmland;

public class TileEntityFarmland extends TileEntity implements TileFarmland {

    //水
    //protected FluidTank water = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME);
    protected int water;
    private final int MAX_WATER = 10;

    //肥料
    private IFertilizer fertilizer;

    @Override
    public void updateEntity() {

        if (!this.worldObj.isRemote) {
            this.updateServerEntity();
        }

    }

    public void updateServerEntity() {

        if (this.getBlockMetadata() == 0 && water > 5) {
            this.worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 4);
            this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        }

        if (this.getBlockMetadata() == 1 && water <= 5) {
            this.worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 4);
            this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        }

    }

    @Override
    public boolean canCropGrow(int water) {

        if (this.water < water) return false;

        return true;

    }

    @Override
    public void doGrow(int water) {

        this.water -= water;

    }

    @Override
    public int getWater() {
        return this.water;
    }

    @Override
    public int addWater(int amount) {

        int add = this.water;

        this.water = Math.min(water + amount, MAX_WATER);

        return this.water - add;

    }

    public IFertilizer getFertilizer() {
        return fertilizer;
    }

    public void setFertilizer(IFertilizer fertilizer) {
        this.fertilizer = fertilizer;
        this.getWorldObj().markBlockForUpdate(xCoord, yCoord, zCoord);
    }

    //NBT
    @Override
    public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
        super.readFromNBT(par1nbtTagCompound);

        this.water = par1nbtTagCompound.getInteger("water");

        if (par1nbtTagCompound.hasKey("fertilizerName")) {

            this.fertilizer = SSCrops.fertilizerManager.getFertilizer(par1nbtTagCompound.getString("fertilizerName"));

        }

    }

    @Override
    public void writeToNBT(NBTTagCompound par1nbtTagCompound) {

        super.writeToNBT(par1nbtTagCompound);
        par1nbtTagCompound.setInteger("water", this.water);

        if (fertilizer != null) par1nbtTagCompound.setString("fertilizerName", fertilizer.getName());

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
        this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }

}
