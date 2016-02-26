package shift.sextiarysector.api.agriculture;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

/**
 * 農業関係の処理にアクセスするできるクラス
 * @author Shift02
 */
public class AgricultureAPI {

    /** 普通の農地 */
    public static final String FARMLAND = "farmland";

    /** 水田 */
    public static final String PADDY = "paddy";

    /** 木(キノコ用) */
    public static final String WOOD = "wood";

    public static ICropManager cropManager;

    public static IFarmlandRegistry farmlandRegistry;

    public static IFertilizerManager fertilizerManager;

    public static IMutationRegistry mutationRegistry;

    public static IFertilizerManager2 fertilizerManager2;

    //肥料

    /** 普通の肥料(大きさ作物が収穫できる) */
    public static IFertilizer normal;

    /** 石の肥料(鉱石作物が収穫できる) */
    public static IFertilizer stone;

    /**
     * 作物のデータを登録
     * @param crop 登録する作物
     */
    public static void registerCrop(ICrop crop) {
        cropManager.registerCrop(crop);
    }

    /**
     * @deprecated 上級者向け <br>
     * 農地の登録<br>
     * {@link TileFarmland}を実装したTileEntityをcreateNewTileEntity()で返すブロックを登録してください
     * @param name 農地の名前
     * @param block 農地
     */
    @Deprecated
    public static void registerFarmland(String name, Block block) {
        farmlandRegistry.registerFarmland(name, block);
    }

    /**
     * 名前から農地を取得する
     * @param name
     * @return
     */
    public static Block getFarmland(String name) {
        return farmlandRegistry.getFarmland(name);
    }

    /**
     * 肥料の登録
     * @param fertilizer 登録する肥料
     */
    public static void registerFertilizer(IFertilizer fertilizer) {
        fertilizerManager.registerFertilizer(fertilizer);
    }

    /**
     * 突然変異後のアイテムの取得 <br>
     * ICropのメソッド内で使用する
     * @param fertilizer 肥料
     * @param crop 変異前の作物
     * @return 変異後の作物
     */
    public static ItemStack getMutationItem(IFertilizer fertilizer, ItemStack crop) {
        return mutationRegistry.getMutationItem(fertilizer, crop);
    }

}
