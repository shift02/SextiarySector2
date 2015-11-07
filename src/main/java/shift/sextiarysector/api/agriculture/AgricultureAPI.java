package shift.sextiarysector.api.agriculture;

import net.minecraft.block.Block;

public class AgricultureAPI {

    /** 普通の農地 */
    public static TileFarmland farmland;

    public static ICropManager cropManager;

    public static IFertilizerManager fertilizerManager;
    public static IFarmlandRegistry farmlandRegistry;

    public static void registerCrop(ICrop crop) {
        cropManager.registerCrop(crop);
    }

    public static void registerFarmland(String name, Block block) {
        farmlandRegistry.registerFarmland(name, block);
    }

    public Block getFarmland(String name) {
        return farmlandRegistry.getFarmland(name);
    }

}
