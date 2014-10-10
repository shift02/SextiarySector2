package shift.sextiarysector.event;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import shift.sextiarysector.api.agriculture.AgricultureAPI;
import shift.sextiarysector.api.agriculture.IFertilizer;
import shift.sextiarysector.gui.GuiStatsNext;
import shift.sextiarysector.module.FertilizerManager;
import shift.sextiarysector.module.SeasonManager;
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

    @SubscribeEvent
    public void onItemTooltipEvent(ItemTooltipEvent event) {


    	ItemStack itemStack = event.itemStack;
    	List<String> toolTip = event.toolTip;

    	if(itemStack.getItem()==Items.clock){
    		toolTip.add(SeasonManager.getInstance().getTime2(this.mc.theWorld));
    	}

    }

    public static IIcon slotGF;

    public static IIcon[] itemGF;

    @SubscribeEvent
	public void PreTextureStitchEvent(TextureStitchEvent.Pre event){

    	//Item
    	if(event.map.getTextureType()==1){


    		slotGF = event.map.registerIcon("sextiarysector:gui/slot_gf");

    		itemGF = new IIcon[2];
    		itemGF[0] = event.map.registerIcon("sextiarysector:damage/damage_0");
    		itemGF[1] = event.map.registerIcon("sextiarysector:damage/damage_1");

    	}

    }

    @SubscribeEvent
	public void rreFertilizerTextureStitchEvent(TextureStitchEvent.Pre event){

    	if(event.map.getTextureType()==0){

    		for(IFertilizer f : ((FertilizerManager)AgricultureAPI.fertilizerManager).fertilizers.values()){
    			f.registerFertilizerIcons(event.map);
    		}

    	}

    }


}
