package shift.sextiarysector.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import shift.sextiarysector.SSItems;
import shift.sextiarysector.api.SextiarySectorAPI;

public class ItemWaterBottle extends ItemFoodDrink{

	public ItemWaterBottle() {
		super(0, 0, 3, 0, 0, 0, false);
		this.setContainerItem(SSItems.emptyBottle);
		this.setDrink();
		this.setCreativeTab(SextiarySectorAPI.TabSSFluid);
	}

	protected void onFoodEaten(ItemStack p_77849_1_, World p_77849_2_, EntityPlayer player)
    {
		player.addExhaustion(4.5f);
		player.addPotionEffect(new PotionEffect(Potion.hunger.getId(),30,0));

    }

}
