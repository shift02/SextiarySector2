package shift.sextiarysector.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import shift.sextiarysector.SSCrops;
import shift.sextiarysector.api.agriculture.ICrop;
import shift.sextiarysector.api.agriculture.TileCrop;
import shift.sextiarysector.api.season.SeasonAPI;

public class TileEntityCrop extends TileEntity implements TileCrop {

    private ICrop crop;

    private NBTTagCompound customData;

    /**
     * 成長判定に使う時間
     */
    private int lastMinutes = -1;

    /**
     * 最後に成長した日
     */
    private int lastDay = 0;

    //成長何日目か
    private int day = 0;

    @Override
    public void updateEntity() {

        super.updateEntity();

        if (this.worldObj.isRemote) {

            this.updateClientEntity();

        } else {

            this.updateServerEntity();

        }

        TileEntityFarmland farmland = this.getFarmland();
        if (this.crop != null && farmland != null) this.crop.update(this, farmland);

    }

    public void updateClientEntity() {
    }

    private void updateServerEntity() {

        //一日一回発動する成長判定
        if (lastMinutes == SeasonAPI.getMinute(getWorldObj()) && lastDay != SeasonAPI.getDay(getWorldObj())) {
            this.updateCrop();
        }

        //初期値を設定
        if (lastMinutes == -1) {
            lastMinutes = SeasonAPI.getMinute(getWorldObj());
        }

    }

    private void updateCrop() {

        if (this.crop == null) return;

        if (!this.crop.canGrow(this, null)) return;

        TileEntityFarmland farmland = this.getFarmland();

        if (this.crop.canWither(this, farmland)) this.doWither();

        if (farmland == null) return;

        int water = this.crop.getConsumptionMoisture(this, farmland);

        if (!farmland.canCropGrow(water)) return;

        this.lastDay = SeasonAPI.getDay(getWorldObj());

        //成長
        this.day++;
        farmland.doGrow(water);

        this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

    }

    private TileEntityFarmland getFarmland() {

        TileEntity t = this.getWorldObj().getTileEntity(xCoord, yCoord - 1, zCoord);

        if (!(t instanceof TileEntityFarmland)) return null;

        return (TileEntityFarmland) t;
    }

    public ICrop getCrop() {
        return this.crop;
    }

    public void setCrop(ICrop crop) {
        this.crop = crop;
    }

    //TileCrop
    @Override
    public int getDay() {
        return this.day;
    }

    @Override
    public void doGrowth(int day) {

        if (day < 0) return;

        this.day += day;
        this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

    }

    @Override
    public void doDecline(int day) {

        int de = Math.max(0, this.day - day);

        this.day = de;
        this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

    }

    @Override
    public void doWither() {

        this.crop = SSCrops.wither;
        this.day = 0;
        this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

    }

    @Override
    public World getWorld() {
        return this.getWorldObj();
    }

    @Override
    public int getX() {
        return this.xCoord;
    }

    @Override
    public int getY() {
        return this.yCoord;
    }

    @Override
    public int getZ() {
        return this.zCoord;
    }

    @Override
    public NBTTagCompound getExtendedCropProperties() {

        if (this.customData == null) this.customData = new NBTTagCompound();

        return this.customData;
    }

    //NBT
    @Override
    public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
        super.readFromNBT(par1nbtTagCompound);

        if (par1nbtTagCompound.hasKey("cropName")) {

            this.crop = SSCrops.cropManager.getCrop(par1nbtTagCompound.getString("cropName"));

        }

        if (par1nbtTagCompound.hasKey("customData")) {
            this.customData = par1nbtTagCompound.getCompoundTag("customData");
        }

        this.lastMinutes = par1nbtTagCompound.getInteger("lastminutes");
        this.lastDay = par1nbtTagCompound.getInteger("lastday");

        this.day = par1nbtTagCompound.getInteger("day");

    }

    @Override
    public void writeToNBT(NBTTagCompound par1nbtTagCompound) {

        super.writeToNBT(par1nbtTagCompound);
        if (crop != null) par1nbtTagCompound.setString("cropName", crop.getName());
        if (this.customData != null) par1nbtTagCompound.setTag("customData", this.customData);

        par1nbtTagCompound.setInteger("lastminutes", this.lastMinutes);
        par1nbtTagCompound.setInteger("lastday", this.lastDay);

        par1nbtTagCompound.setInteger("day", this.day);

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
