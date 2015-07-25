package shift.sextiarysector.plugin;

import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PluginTC implements IPlugin {

	@Override
	public String getModName() {
		return null;
	}

	@Override
	public void prePlugin(FMLPreInitializationEvent event) {

	}

	@Override
	public void preClientPlugin(FMLPreInitializationEvent event) {

	}

	@Override
	public void initPlugin(FMLInitializationEvent event) {

	}

	@Override
	public void postPlugin(FMLPostInitializationEvent event) {

	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void blockHighlight(DrawBlockHighlightEvent event)
	{

	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void blockHighlightPost(DrawBlockHighlightEvent event)
	{

	}

}
