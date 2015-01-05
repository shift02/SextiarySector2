package shift.sextiarysector.event;

import shift.mceconomy2.api.shop.ShopBuyEvent;
import shift.sextiarysector.SSAchievement;
import shift.sextiarysector.item.ItemBlockCrop;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class AchievementEventHandler {

	@SubscribeEvent
	public void useEvent(ShopBuyEvent event) {
		if(event.buy.getItem() instanceof ItemBlockCrop)event.entityPlayer.addStat(SSAchievement.seed, 1);
	}

}
