package shift.sextiarysector.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

	public int holeType;

	public int bottleType;
	public int squareType;

	public int fluidCrafterType;

	public int woodHopperType;

	public int ShaftRenderType;

	public int GearShaftRenderType;

	public int smallWindMillType;
	public int windMillType;
	public int smallWaterwheel;

	public int fanType;

	public int monitorType;

	public int chestType;

	public int farmlandType;
	public int paddyType;
	public int woodType;


	public EntityPlayer getClientPlayer(){
		return null;
	}

	public void setCustomRenderers() {
	}

	public void registerItemRenderer(Item item) {

	}

	public void openGUI(int id){

	}

	public void registerInventoryTabs()
    {

    }

	public void setPluginCustomRenderers(FMLPreInitializationEvent event)
    {

    }

	public Object getShiftHat(){
		return null;
	}


}
