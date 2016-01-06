package shift.sextiarysector.api.agriculture;

import net.minecraft.block.Block;

public class AgricultureAPI {

    /** 普通の農地 */
    public static final String FARMLAND = "farmland";

    public static ICropManager cropManager;

    public static IFertilizerManager fertilizerManager;
    public static IFarmlandRegistry farmlandRegistry;

    /**
     * 作物のデータを登録
     * @param crop 登録する作物
     */
    public static void registerCrop(ICrop crop) {
        cropManager.registerCrop(crop);
    }

    /**
     * 農地の登録<br>
     * {@link TileFarmland}を実装したTileEntityをcreateNewTileEntity()で返すブロックを登録してください
     * @param name 農地の名前
     * @param block 農地
     */
    public static void registerFarmland(String name, Block block) {
        farmlandRegistry.registerFarmland(name, block);
    }

    /**
     * 名前から農地を取得する
     * @param name
     * @return
     */
    public Block getFarmland(String name) {
        return farmlandRegistry.getFarmland(name);
    }

}
