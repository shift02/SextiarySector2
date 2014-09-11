package shift.sextiarysector.item;

import net.minecraft.item.Item;
import shift.sextiarysector.api.machine.item.ItemGearForce;

public class ItemGearStorage extends ItemGearForce{

	public ItemGearStorage(int power, int maxSpeed, int slot) {
		super(power, maxSpeed, slot);
	}

	public Item setTextureName(String p_111206_1_)
    {
        this.iconString = p_111206_1_;
        return this;
    }

}
