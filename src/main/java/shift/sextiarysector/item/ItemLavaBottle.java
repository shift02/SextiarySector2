package shift.sextiarysector.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import shift.sextiarysector.SSItems;
import shift.sextiarysector.SSPotions;
import shift.sextiarysector.api.SextiarySectorAPI;

public class ItemLavaBottle extends ItemFoodDrink {

	public ItemLavaBottle() {
		super(0, 0, 0, 0, 1, 0, false);
		this.setContainerItem(Items.glass_bottle);
		this.setDrink();
		this.setMaxStackSize(1);
		this.setAlwaysEdible();

		this.setCreativeTab(SextiarySectorAPI.TabSSFluid);
	}

	@Override
	protected void onFoodEaten(ItemStack p_77849_1_, World p_77849_2_, EntityPlayer player)
	{

		player.addExhaustion(7.8f);
		player.addPotionEffect(new PotionEffect(SSPotions.burn.getId(), 360, 2));

		if (!player.inventory.addItemStackToInventory(new ItemStack(SSItems.obsidianNugget)))
		{
			player.dropPlayerItemWithRandomChoice(new ItemStack(SSItems.obsidianNugget, 1, 0), false);
		}

	}
}
