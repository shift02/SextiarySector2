package shift.sextiarysector.item;

import shift.sextiarysector.api.SextiarySectorAPI;

public class ItemCrop extends ItemFoodDrink{

	public ItemCrop(int food, float foodM, int drink, float drinkM,int stamina, float staminaM, boolean p_i45339_3_) {
		super(food, foodM, drink, drinkM, stamina, staminaM, p_i45339_3_);

		this.setCreativeTab(SextiarySectorAPI.TabSSAgriculture);

	}

}
