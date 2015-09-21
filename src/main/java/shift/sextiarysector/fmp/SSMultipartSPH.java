package shift.sextiarysector.fmp;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.INetHandlerPlayServer;
import shift.sextiarysector.SextiarySector;
import codechicken.lib.packet.PacketCustom;
import codechicken.lib.packet.PacketCustom.IServerPacketHandler;

public class SSMultipartSPH implements IServerPacketHandler {
    public static Object channel = SextiarySector.instance;

    @Override
    public void handlePacket(PacketCustom packet, EntityPlayerMP sender, INetHandlerPlayServer netHandler) {
        switch (packet.getType()) {
            case 1:
                ShaftEventHandler.place(sender, sender.worldObj);
                break;
        }
    }

}
