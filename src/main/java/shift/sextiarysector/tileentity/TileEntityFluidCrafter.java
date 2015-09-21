package shift.sextiarysector.tileentity;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.FluidStack;

public class TileEntityFluidCrafter extends TileEntity {

    public static Random r = new Random();

    public FluidStack f;

    public int x = 0;
    public int y = 0;
    public int z = 0;

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

        x += r.nextInt(2) + 4;
        y += r.nextInt(2) + 4;
        z += r.nextInt(2) + 4;

    }

    private void updateServerEntity() {

    }

    public boolean hasFluid() {
        return f != null;
    }

    public IIcon getFluidIcon() {
        return this.f.getFluid().getIcon(f);
    }

    public int getFluidColor() {
        return this.f.getFluid().getColor(this.f);
    }

    @Override
    public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
        super.readFromNBT(par1nbtTagCompound);
        if (par1nbtTagCompound.hasKey("fluid")) {

            NBTTagCompound nbt = par1nbtTagCompound.getCompoundTag("fluid");

            this.f = FluidStack.loadFluidStackFromNBT(nbt);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
        super.writeToNBT(par1nbtTagCompound);
        if (f != null) {

            NBTTagCompound nbt = new NBTTagCompound();

            f.writeToNBT(nbt);

            par1nbtTagCompound.setTag("fluid", nbt);

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
