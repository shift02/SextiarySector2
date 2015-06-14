package shift.sextiarysector.item;

import net.minecraft.item.ItemShears;
import shift.sextiarysector.api.SextiarySectorAPI;

public class ItemSSShears extends ItemShears {

	public ItemSSShears(ToolMaterial m)
	{
		this.setMaxStackSize(1);
		this.setMaxDamage(m.getMaxUses() - 12);
		this.setCreativeTab(SextiarySectorAPI.TabSSCore);
	}

}
