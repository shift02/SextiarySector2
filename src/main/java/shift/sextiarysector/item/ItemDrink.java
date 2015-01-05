package shift.sextiarysector.item;

import shift.sextiarysector.SSItems;

public class ItemDrink extends ItemFoodDrink{

	public ItemDrink(int food, float foodM, int drink, float drinkM,int stamina, float staminaM, boolean p_i45339_3_) {
		super(food, foodM, drink, drinkM, stamina, staminaM, p_i45339_3_);
		this.setDrink();
		this.setContainerItem(SSItems.emptyBottle);
	}

}
