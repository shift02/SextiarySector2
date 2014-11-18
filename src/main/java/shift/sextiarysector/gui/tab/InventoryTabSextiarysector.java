package shift.sextiarysector.gui.tab;

import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import shift.sextiarysector.SextiarySector;
import tconstruct.client.tabs.AbstractTab;
import cpw.mods.fml.client.FMLClientHandler;

public class InventoryTabSextiarysector extends AbstractTab {

	private static Minecraft mc = FMLClientHandler.instance().getClient();

	public InventoryTabSextiarysector() {
		super(0, 0, 0, new ItemStack(Items.chainmail_chestplate));
	}

	@Override
	public void onTabClicked() {

		//mc.thePlayer.sendQueue.addToSendQueue(new C0DPacketCloseWindow(mc.thePlayer.openContainer.windowId));
		mc.thePlayer.openGui(SextiarySector.instance, 200, mc.thePlayer.worldObj, (int)mc.thePlayer.posX, (int)mc.thePlayer.posY, (int)mc.thePlayer.posZ);
		//SextiarySector.proxy.openGUI(200);
	}

	@Override
	public boolean shouldAddToList() {
		return true;
	}

}
