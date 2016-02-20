package shift.sextiarysector.packet;

import net.minecraft.entity.player.EntityPlayer;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.player.CustomPlayerData;
import shift.sextiarysector.player.EntityPlayerManager;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessagePlayer implements IMessageHandler<PacketPlayerData, IMessage> {

    @Override
    public IMessage onMessage(PacketPlayerData message, MessageContext ctx) {

        EntityPlayer p = SextiarySector.instance.proxy.getClientPlayer();

        CustomPlayerData data = EntityPlayerManager.getCustomPlayerData(p);

        data.loadNBTData(message.getData());

        //System.out.println("AAAAA");

        return null;

    }

}
