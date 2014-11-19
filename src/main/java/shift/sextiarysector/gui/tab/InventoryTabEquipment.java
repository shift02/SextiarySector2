package shift.sextiarysector.gui.tab;

import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import shift.sextiarysector.SextiarySector;
import cpw.mods.fml.client.FMLClientHandler;

public class InventoryTabEquipment extends AbstractTab {

	private static Minecraft mc = FMLClientHandler.instance().getClient();

	@Override
	public void onTabClicked() {
		mc.thePlayer.openGui(SextiarySector.instance, 200, mc.thePlayer.worldObj, (int)mc.thePlayer.posX, (int)mc.thePlayer.posY, (int)mc.thePlayer.posZ);
	}

	@Override
	public ItemStack getItemStack() {
		return new ItemStack(Items.chainmail_chestplate);
	}

	@Override
	public boolean shouldAddToList() {
		return true;
	}

}
