package shift.sextiarysector.api.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import shift.sextiarysector.api.IDrink;
import shift.sextiarysector.api.SextiarySectorAPI;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class VanillaFoodHandler {

	/**バニラの食べ物を食べた時の動作*/
	@SubscribeEvent
    public void onPlayerEatenEvent(PlayerEatenEvent event) {

		ItemStack food = event.food;

		EntityPlayer player = (EntityPlayer) event.entity;

		//水入り瓶
		if(food.getItem() == Items.potionitem){

			SextiarySectorAPI.playerManager.addMoistureStats(player, 3, 0);
			player.addPotionEffect(new PotionEffect(Potion.hunger.getId(),600,0));
		}

		//きのこシチュー
		if(food.getItem() == Items.mushroom_stew){

			SextiarySectorAPI.playerManager.addMoistureStats(player, 2, 2);
		}

		//鳥
		if(food.getItem() == Items.chicken){

			SextiarySectorAPI.playerManager.addMoistureStats(player, 1, 0);
		}

		if(food.getItem() == Items.cooked_chicken){

			SextiarySectorAPI.playerManager.addMoistureExhaustion(player, 9.2f);
			SextiarySectorAPI.playerManager.addStaminaStats(player, 4, 2);
		}

		//豚
		if(food.getItem() == Items.porkchop){

			SextiarySectorAPI.playerManager.addMoistureStats(player, 1, 0);
		}

		if(food.getItem() == Items.cooked_porkchop){

			SextiarySectorAPI.playerManager.addMoistureExhaustion(player, 12.2f);
			SextiarySectorAPI.playerManager.addStaminaStats(player, 5, 1);
		}

		//牛
		if(food.getItem() == Items.beef){

			SextiarySectorAPI.playerManager.addMoistureStats(player, 1, 0);
		}

		if(food.getItem() == Items.cooked_beef){

			SextiarySectorAPI.playerManager.addMoistureExhaustion(player, 16.2f);
			SextiarySectorAPI.playerManager.addStaminaStats(player, 7, 4);
		}

		//魚
		if(food.getItem() == Items.fish){

			SextiarySectorAPI.playerManager.addMoistureStats(player, 4, 2);
			player.addPotionEffect(new PotionEffect(Potion.hunger.getId(),600,0));

		}

		if(food.getItem() == Items.cooked_fished){

			SextiarySectorAPI.playerManager.addMoistureExhaustion(player, 6.2f);
			SextiarySectorAPI.playerManager.addStaminaStats(player, 6, 2);
		}

		//リンゴ
		if(food.getItem() == Items.apple){

			SextiarySectorAPI.playerManager.addMoistureStats(player, 1,1.0f);

		}

		//スイカ
		if(food.getItem() == Items.melon){

			SextiarySectorAPI.playerManager.addMoistureStats(player, 3,1.0f);

		}

		//パン
		if(food.getItem() == Items.bread){

			SextiarySectorAPI.playerManager.addMoistureExhaustion(player, 6.8f);
			SextiarySectorAPI.playerManager.addStaminaStats(player, 4, 4);
		}

		//牛乳
		if(food.getItem() == Items.milk_bucket){

			SextiarySectorAPI.playerManager.addMoistureStats(player, 4, 4);

		}

		if(food.getItem() instanceof IDrink){
			SextiarySectorAPI.playerManager.addMoistureStats(player, ((IDrink)food.getItem()).getMoisture(food), ((IDrink)food.getItem()).getMoistureSaturation(food));
		}

	}

}
