package shift.sextiarysector.item;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import shift.sextiarysector.api.SextiarySectorAPI;

public class ItemFullBottle extends Item {

    public ItemFullBottle() {
        this.setCreativeTab(SextiarySectorAPI.TabSSCore);
        this.setContainerItem(Items.glass_bottle);
        this.setMaxStackSize(1);
    }

}
