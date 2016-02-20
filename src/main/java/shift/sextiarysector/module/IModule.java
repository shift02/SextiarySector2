package shift.sextiarysector.module;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public interface IModule {

    public void preInit(FMLPreInitializationEvent event);

    public void load(FMLInitializationEvent event);

    public void postInit(FMLPostInitializationEvent event);

}
