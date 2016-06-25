package shift.sextiarysector.packet;

import java.util.List;
import java.util.UUID;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.player.CustomPlayerData;
import shift.sextiarysector.player.EntityPlayerManager;

public class MessagePlayer implements IMessageHandler<PacketPlayerData, IMessage> {

    @Override
    public IMessage onMessage(PacketPlayerData message, MessageContext ctx) {

        EntityPlayer p = this.getPlayer(message);

        if (p == null) return null;

        CustomPlayerData data = EntityPlayerManager.getCustomPlayerData(p);

        data.loadNBTData(message.getData());

        //System.out.println("AAAAA");

        return null;

    }

    public EntityPlayer getPlayer(PacketPlayerData message) {

        NBTTagCompound nbt = message.getData();

        if (nbt.hasKey("uuid")) {

            UUID uuid = UUID.fromString(nbt.getString("uuid"));

            //自分自身は更新しない
            if (uuid.equals(SextiarySector.instance.proxy.getClientPlayer().getUniqueID())) return null;

            List<EntityPlayer> player = SextiarySector.instance.proxy.getClientPlayer().worldObj.playerEntities;

            for (EntityPlayer p : player) {
                if (p.getUniqueID().equals(uuid)) {

                    return p;

                }
            }

        } else {
            return SextiarySector.instance.proxy.getClientPlayer();
        }

        return null;

    }

}
