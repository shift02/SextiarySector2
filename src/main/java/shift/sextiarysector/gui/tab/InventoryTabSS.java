package shift.sextiarysector.gui.tab;

import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import shift.sextiarysector.packet.PacketGuiId;
import shift.sextiarysector.packet.SSPacketHandler;
import cpw.mods.fml.client.FMLClientHandler;

public class InventoryTabSS  extends AbstractTab {

	private static Minecraft mc = FMLClientHandler.instance().getClient();

	@Override
	public void onTabClicked() {
		SSPacketHandler.INSTANCE.sendToServer(new PacketGuiId(200));
		//mc.thePlayer.openGui(SextiarySector.instance, 200, mc.thePlayer.worldObj, (int)mc.thePlayer.posX, (int)mc.thePlayer.posY, (int)mc.thePlayer.posZ);
	}

	@Override
	public ItemStack getItemStack() {
		return new ItemStack(Items.chainmail_chestplate);
	}

	@Override
	public boolean shouldAddToList() {
		return true;
	}

	@Override
	public String getTabName() {
		return "player.tab.equipment";
	}

}
