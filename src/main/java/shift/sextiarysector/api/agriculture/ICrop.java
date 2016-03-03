package shift.sextiarysector.api.agriculture;

import java.util.ArrayList;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

/**
 * 作物のクラスに実装するinterface <br>
 * 通常は {@link CropAbstract}を使う<br>
 * <br>
 * {@link AgricultureAPI#registerCrop(ICrop)}で登録する
 *
 * @see CropAbstract
 * @author Shift02
 *
 */
public interface ICrop {

    /*
     *--------------------------------------
     *  基礎の処理
     *--------------------------------------
     */
    /**
     * 他の作物と被らないオリジナルの名前 <br>
     * 作物を登録する時に被っているとエラーになります。
     * @return
     */
    String getName();

    /**
     * 収穫できるまでの日数 <br>
     * 生育期間
     * @return 収穫できない作物の場合は-1を返す
     */
    public int getGrowingPeriod();

    /**
     * プレイヤーが農地を右クリックした時に呼ばれる<br>
     * 手に持っていたアイテムがこの作物データの種ならtrueを返す
     *
     * @param seed プレイヤーが持っている種らしきアイテム
     * @param player 種を持っているプレイヤー
     *
     * @return 種ならtrue <br> trueを返すと植える
     */
    boolean isSeed(ItemStack seed, EntityPlayer player);

    /**
     * 作物が引数の農地で育つことができるかを返す<br>
     * @param name 農地の名前 SS2で追加されたものはAgricultureAPIで定数で宣言されている
     * @param farmland 農地 (水田や木の場合もある)
     * @return falseだと設置できない
     */
    boolean canBlockStay(String name, TileFarmland farmland);

    /**
     * 作物がこの環境(季節やバイオーム)で育つことができるか
     * @param crop 作物
     * @param farmland 耕地
     * @return trueだと枯れる
     */
    boolean canWither(TileCrop crop, TileFarmland farmland);

    /**
     * デフォルトの成長判定 trueだと耕地の水分を消費して成長する
     * @param crop 作物
     * @param farmland 耕地
     * @return 成長できるどうか
     */
    boolean canGrow(TileCrop crop, TileFarmland farmland);

    /**
     * 作物が成長するときに消費する水分の量を返す
     * @param crop 作物
     * @param farmland 耕地
     * @return 消費する量
     */
    int getConsumptionMoisture(TileCrop crop, TileFarmland farmland);

    /**
     * 作物が収穫できるか返す
     * @return 収穫できる場合はtrue
     */
    boolean canHarvest(TileCrop crop, TileFarmland farmland);

    /**
     * 作物の収穫処理
     * @param crop 作物
     * @param farmland 農地
     * @return 収穫できた作物
     */
    ArrayList<ItemStack> harvest(TileCrop crop, TileFarmland farmland);

    /*
     *--------------------------------------
     *  Blockからの処理
     *--------------------------------------
     */
    /**
     * プレイヤーに作物がクリックされた時に呼ばれる <br>
     * {@link #harvest(TileCrop crop, TileFarmland farmland) } を呼んだりする
     * @param crop
     * @param farmland TODO
     * @param player
     */
    boolean click(TileCrop crop, TileFarmland farmland, EntityPlayer player);

    /**
     * 作物の硬さを返す<br>
     * 作物Blockを破壊する時の時間に関係
     * @param crop 作物
     * @param farmland 農地
     * @return 硬さ
     */
    float getHardness(TileCrop crop, TileFarmland farmland);

    /**
     * 作物の明るさを返す<br>
     * 輝くダイコンとか作成できる
     * @param crop 作物
     * @param farmland 農地
     * @return 0 ~ 15
     */
    int getLightValue(TileCrop crop, TileFarmland farmland);

    /**
     * Entityが作物に接触した時に呼ばれる
     * @param crop 作物
     * @param farmland 農地
     * @param entity 接触したEntity
     */
    void onEntityCollidedWithCrop(TileCrop crop, TileFarmland farmland, Entity entity);

    /**
     * 作物に触れたEntityを燃やすかどうか
     * @param crop 作物
     * @param farmland 農地
     * @return trueだと火がつく
     */
    boolean isBurning(TileCrop crop, TileFarmland farmland);

    /**
     * 作物をエンチャント時のレベル上げ用本棚の代用にする
     * @param crop 作物
     * @param farmland 農地
     * @return エンチャントテーブルに供給する魔力量
     */
    float getEnchantPowerBonus(TileCrop crop, TileFarmland farmland);

    /*
     *--------------------------------------
     *  TileEntityからの処理
     *--------------------------------------
     */
    /**
     * 毎tick呼ばれる
     * @param crop 作物本体
     * @param farmland 耕地
     */
    void update(TileCrop crop, TileFarmland farmland);

    /*
     *--------------------------------------
     *  Client側の処理
     *--------------------------------------
     */
    /**
     * 作物のIIconを登録するメソッド
     * @param register
     */
    @SideOnly(Side.CLIENT)
    void registerCropIcons(IIconRegister register);

    /**
     * 作物の成長度合いによってIIconを返す
     * @param crop 作物
     * @return 作物のIIcon
     */
    @SideOnly(Side.CLIENT)
    public IIcon getCropIcon(TileCrop crop);

    /**
     * 作物の描画タイプを返す
     * @see CropRendererType
     * @return 描画タイプ
     */
    @SideOnly(Side.CLIENT)
    public CropRendererType getRenderType();

}
