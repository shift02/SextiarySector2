package shift.sextiarysector.module;

import shift.sextiarysector.api.season.SeasonAPI;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ModuleSeason implements IModule {

    private static ModuleSeason instance = new ModuleSeason();

    private ModuleSeason() {
    }

    public static ModuleSeason getInstance() {
        return instance;
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {

        SeasonAPI.SeasonManager = SeasonManager.getInstance();
        //MinecraftForge.EVENT_BUS.register(new SeasonEventHandler());

    }

    @Override
    public void load(FMLInitializationEvent event) {
        // TODO 自動生成されたメソッド・スタブ

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        // TODO 自動生成されたメソッド・スタブ

    }

}
