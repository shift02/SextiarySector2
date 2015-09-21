package shift.sextiarysector.api.agriculture;

import net.minecraft.block.Block;

public class AgricultureAPI {

    public static IFertilizerManager fertilizerManager;
    public static IFarmlandRegistry farmlandRegistry;

    public static void registerFarmland(String name, Block block) {
        farmlandRegistry.registerFarmland(name, block);
    }

    public Block getFarmland(String name) {
        return farmlandRegistry.getFarmland(name);
    }

}
