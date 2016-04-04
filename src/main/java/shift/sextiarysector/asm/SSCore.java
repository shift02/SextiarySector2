package shift.sextiarysector.asm;

import java.io.File;
import java.util.Map;

import cpw.mods.fml.relauncher.IFMLCallHook;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

public class SSCore implements IFMLLoadingPlugin, IFMLCallHook {

    static File location;

    @Override
    public String[] getASMTransformerClass() {
        return new String[] {
                "shift.sextiarysector.asm.TransformerBottle",
                "shift.sextiarysector.asm.TransformerEntityPlayerSP",
                "shift.sextiarysector.asm.TransformerFoodStats",
                "shift.sextiarysector.asm.TransformerRenderPlayer" };
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;//DepLoader.class.getName();
    }

    @Override
    public void injectData(Map<String, Object> data) {
        if (data.containsKey("coremodLocation")) {
            location = (File) data.get("coremodLocation");
        }

        if (location == null) {

            location = new File((File) data.get("mcLocation"), "../bin");

            location.mkdir();

        }
    }

    @Override
    public Void call() {
        //DepLoader.load();

        return null;
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }

}
