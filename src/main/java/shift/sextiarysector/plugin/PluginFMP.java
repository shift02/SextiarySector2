package shift.sextiarysector.plugin;

import shift.sextiarysector.fmp.PartRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class PluginFMP implements IPlugin {

	@Override
	public String getModName() {
		return "ForgeMultipart";
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

		PartRegistry.init();

	}

}
