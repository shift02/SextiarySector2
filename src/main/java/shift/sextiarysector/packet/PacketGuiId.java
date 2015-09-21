package shift.sextiarysector.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class PacketGuiId implements IMessage {

    private NBTTagCompound data;

    public PacketGuiId() {

    }

    public PacketGuiId(int id) {

        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("gui", id);
        this.setData(nbt);

    }

    @Override
    public void fromBytes(ByteBuf buf) {
        setData(ByteBufUtils.readTag(buf));
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, getData());
    }

    public NBTTagCompound getData() {
        return data;
    }

    public void setData(NBTTagCompound data) {
        this.data = data;
    }

}
