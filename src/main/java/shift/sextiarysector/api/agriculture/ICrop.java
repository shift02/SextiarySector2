package shift.sextiarysector.api.agriculture;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

/**
 * 作物のクラスに実装するinterface <br>
 *
 * {@link AgricultureAPI#registerCrop(ICrop)}で登録する
 *
 * @author Shift02
 *
 */
public interface ICrop {

    /**
     * 他の作物と被らないオリジナルの名前 <br>
     * 作物を登録する時に被っているとエラーになります。
     * @return
     */
    String getName();

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
     * 毎tick呼ばれる
     * @param crop 作物本体
     * @param farmland 耕地
     */
    void update(TileCrop crop, TileFarmland farmland);

    /**
     * プレイヤーに作物がクリックされた時に呼ばれる <br>
     * 収穫処理などを書く
     * @param crop
     * @param player
     */
    boolean click(TileCrop crop, EntityPlayer player);

    /**
     * 作物が引数の農地で育つことができるかを返す<br>
     * @param name 農地の名前 SS2で追加されたものはAgricultureAPIで定数で宣言されている
     * @param farmland 農地 (水田や木の場合もある)
     * @return falseだと設置できない
     */
    boolean canBlockStay(String name, TileFarmland farmland);

    /**
     * trueだと枯れる
     * @param crop
     * @return
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

    @SideOnly(Side.CLIENT)
    void registerCropIcons(IIconRegister register);

    @SideOnly(Side.CLIENT)
    public IIcon getCropIcon(TileCrop crop);

    @SideOnly(Side.CLIENT)
    public CropRendererType getRenderType();

}
