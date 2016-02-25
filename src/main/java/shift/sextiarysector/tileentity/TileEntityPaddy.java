/*
* 作成者: Shift02
* 作成日: 2016/02/25 - 15:56:37
*/
package shift.sextiarysector.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import shift.sextiarysector.SSCrops;
import shift.sextiarysector.api.agriculture.IFertilizer;
import shift.sextiarysector.api.agriculture.TileFarmland;

public class TileEntityPaddy extends TileEntity implements TileFarmland {

    //肥料
    private IFertilizer fertilizer;

    @Override
    public boolean canCropGrow(int water) {
        return true;
    }

    @Override
    public void doGrow(int water) {

    }

    @Override
    public int getWater() {
        return 10;
    }

    @Override
    public int addWater(int amount) {
        return 0;
    }

    @Override
    public boolean hasWater() {
        return true;
    }

    @Override
    public IFertilizer getFertilizer() {
        return fertilizer;
    }

    @Override
    public void setFertilizer(IFertilizer fertilizer) {
        this.fertilizer = fertilizer;
        this.getWorldObj().markBlockForUpdate(xCoord, yCoord, zCoord);
    }

    //NBT
    @Override
    public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
        super.readFromNBT(par1nbtTagCompound);

        if (par1nbtTagCompound.hasKey("fertilizerName")) {

            this.fertilizer = SSCrops.fertilizerManager.getFertilizer(par1nbtTagCompound.getString("fertilizerName"));

        }

    }

    @Override
    public void writeToNBT(NBTTagCompound par1nbtTagCompound) {

        super.writeToNBT(par1nbtTagCompound);

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
