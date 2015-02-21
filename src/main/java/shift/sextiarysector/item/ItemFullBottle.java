package shift.sextiarysector.item;

import net.minecraft.item.Item;
import shift.sextiarysector.SSItems;
import shift.sextiarysector.api.SextiarySectorAPI;

public class ItemFullBottle extends Item {

	public ItemFullBottle() {
		this.setCreativeTab(SextiarySectorAPI.TabSSCore);
		this.setContainerItem(SSItems.emptyBottle);
	}

}
