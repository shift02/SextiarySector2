package shift.sextiarysector.item;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import shift.sextiarysector.api.SextiarySectorAPI;

public class ItemSimpleBucket extends Item {

	public ItemSimpleBucket() {
		this.maxStackSize = 1;
		this.setCreativeTab(SextiarySectorAPI.TabSSFluid);
		this.setContainerItem(Items.bucket);
	}

}
