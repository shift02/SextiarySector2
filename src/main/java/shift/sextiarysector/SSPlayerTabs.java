package shift.sextiarysector;

import net.minecraft.item.ItemStack;
import shift.sextiarysector.api.equipment.EquipmentType;
import shift.sextiarysector.gui.tab.AbstractTab;
import shift.sextiarysector.gui.tab.InventoryTabEquipment;
import shift.sextiarysector.gui.tab.InventoryTabSS;
import shift.sextiarysector.gui.tab.TabManager;

public class SSPlayerTabs {

    public static AbstractTab ss;

    public static AbstractTab craft;

    public static AbstractTab rucksack;

    public static AbstractTab quiver;

    public static AbstractTab creeperShop;

    public static void initRecipes() {

        ss = new InventoryTabSS();
        TabManager.registerTab(ss);

        craft = new InventoryTabEquipment(EquipmentType.Unit, new ItemStack(SSItems.craftUnit));
        TabManager.registerTab(craft);

        rucksack = new InventoryTabEquipment(EquipmentType.Bag, new ItemStack(SSItems.rucksack));
        TabManager.registerTab(rucksack);

        quiver = new InventoryTabEquipment(EquipmentType.Bag, new ItemStack(SSItems.quiver));
        TabManager.registerTab(quiver);

        //creeperShop = new InventoryTabEquipment(EquipmentType.Other, new ItemStack(SSItems.creeperRing));
        //TabManager.registerTab(creeperShop);
        //((ItemShopRing) SSItems.creeperRing).setTab(creeperShop);

    }

}
