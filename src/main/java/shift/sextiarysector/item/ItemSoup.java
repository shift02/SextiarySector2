package shift.sextiarysector.item;

import net.minecraft.init.Items;
import shift.sextiarysector.api.SextiarySectorAPI;

public class ItemSoup extends ItemFoodDrink{

	public ItemSoup(int food, float foodM, int drink, float drinkM,	int stamina, float staminaM, boolean p_i45339_3_) {
		super(food, foodM, drink, drinkM, stamina, staminaM, p_i45339_3_);
		this.setCreativeTab(SextiarySectorAPI.TabSSCooking);
		this.setContainerItem(Items.bowl);
	}

}
