package shift.sextiarysector.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraftforge.client.event.GuiOpenEvent;
import shift.sextiarysector.gui.GuiStatsNext;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ClientEventHandler {

    public static Minecraft mc = FMLClientHandler.instance().getClient();

    @SubscribeEvent
    public void onGuiOpenEvent(GuiOpenEvent event) {

    	if(event.gui instanceof GuiStats&&!(event.gui instanceof GuiStatsNext)){

    		/*
    		Class<GuiStats> c = GuiStats.class;

    		Field f1;
    		GuiScreen m = null;
			try {
				f1 = c.getDeclaredField( "parentGui" );
				f1.setAccessible( true );
				m = (GuiScreen) f1.get( event.gui );
			} catch (NoSuchFieldException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			} catch (SecurityException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}*/

				event.gui = new GuiStatsNext(new GuiIngameMenu(),mc.thePlayer.getStatFileWriter());



    		//System.out.println("GuiOpenEvent");
    	}

    }


}
