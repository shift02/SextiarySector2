package shift.sextiarysector.plugin;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import mmmlibx.lib.FileManager;

public class PluginMaid implements IPlugin {

    @Override
    public String getModName() {
        return "Maid";
    }

    @Override
    public void prePlugin(FMLPreInitializationEvent event) {
        FileManager.getModFile("EntityMode", "SextiarySector2");
    }

    @Override
    public void preClientPlugin(FMLPreInitializationEvent event) {
        // TODO 自動生成されたメソッド・スタブ

    }

    @Override
    public void initPlugin(FMLInitializationEvent event) {
        // TODO 自動生成されたメソッド・スタブ

    }

    @Override
    public void postPlugin(FMLPostInitializationEvent event) {
        // TODO 自動生成されたメソッド・スタブ

    }

}
