package shift.sextiarysector.plugin;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public interface IPlugin {

	public String getModName();

	public void prePlugin(FMLPreInitializationEvent event);

	public void initPlugin(FMLInitializationEvent event);

	public void postPlugin(FMLPostInitializationEvent event);

}
