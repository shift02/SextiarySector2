package shift.sextiarysector.plugin;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface IPlugin {

	public String getModName();

	public void prePlugin(FMLPreInitializationEvent event);

	@SideOnly(Side.CLIENT)
	public void preClientPlugin(FMLPreInitializationEvent event);

	public void initPlugin(FMLInitializationEvent event);

	public void postPlugin(FMLPostInitializationEvent event);

}
