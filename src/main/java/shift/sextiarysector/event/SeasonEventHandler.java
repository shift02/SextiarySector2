package shift.sextiarysector.event;

import net.minecraft.util.StatCollector;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class SeasonEventHandler {

	//時報とか
	@SubscribeEvent
	public void LivingSleepingEvent(LivingUpdateEvent event)
	{
		/*
		if(event.entityLiving.worldObj.isRemote){
			return ;
		}

		if(!(event.entityLiving instanceof EntityPlayer)){
			return ;
		}

		if(event.entityLiving instanceof EntityPlayerMP){

			EntityPlayer player = (EntityPlayerMP) event.entityLiving;

			if(player.isPlayerFullyAsleep()){

				int y = SextiarySectorAPI.getYear(event.entityLiving.worldObj);
				Season s = SextiarySectorAPI.getSeason(event.entityLiving.worldObj);
				int d = SextiarySectorAPI.getDay(event.entityLiving.worldObj);

				if(d==30)
				{
					s = Season.getNextSeason(s);
					d = 1;

					if(s.ordinal()==0){
						y++;
					}

				}else{
					d++;
				}


				FMLClientHandler.instance().getClient().ingameGUI.getChatGUI().printChatMessage(""+
						" ["+this.getTranslatedName("timesignal")+"] "+
						y+this.getTranslatedName("year")+" "+
						s.getTranslatedName()+this.getTranslatedName("month")+" "+
						d+this.getTranslatedName("day")+" "+
						"6:00:00"
						);

			}

		}*/

	}

	public String getTranslatedName(String name){
		return StatCollector.translateToLocal("tooltip.season." + name);
	}

}
