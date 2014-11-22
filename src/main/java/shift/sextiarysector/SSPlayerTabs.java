package shift.sextiarysector;

import net.minecraft.item.ItemStack;
import shift.sextiarysector.gui.tab.AbstractTab;
import shift.sextiarysector.gui.tab.InventoryTabEquipment;
import shift.sextiarysector.gui.tab.InventoryTabSS;
import shift.sextiarysector.gui.tab.TabManager;
import shift.sextiarysector.player.EquipmentType;

public class SSPlayerTabs {

	public static AbstractTab ss;

	public static AbstractTab craft;

	public static void initRecipes(){

		ss = new InventoryTabSS();
		TabManager.registerTab(ss);

		craft = new InventoryTabEquipment(EquipmentType.Unit, new ItemStack(SSItems.craftUnit));
		TabManager.registerTab(craft);

	}

}
