package shift.sextiarysector.packet;

import net.minecraft.entity.player.EntityPlayerMP;
import shift.sextiarysector.SextiarySector;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageGuiId implements IMessageHandler<PacketGuiId, IMessage> {

    @Override
    public IMessage onMessage(PacketGuiId message, MessageContext ctx) {

        EntityPlayerMP p = ctx.getServerHandler().playerEntity;

        //CustomPlayerData data = EntityPlayerManager.getCustomPlayerData(p);

        //data.loadNBTData(message.getData());

        //System.out.println("AAAAA" + message.getData().getInteger("gui"));

        int i = message.getData().getInteger("gui");

        p.openGui(SextiarySector.instance, i, p.worldObj, (int) p.posX, (int) p.posY, (int) p.posZ);

        return null;

    }
}
