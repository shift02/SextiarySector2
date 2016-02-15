package shift.sextiarysector.api.agriculture;

/**
 * 農地ブロックのTileEntityに実装するinterface
 *
 * @author Shift02
 *
 */
public interface TileFarmland {

    /**
     * 農地の水を使って成長できるか調べる
     * @param water 消費する水の量
     * @return 消費出来るだけ保持しているならtrue
     */
    public boolean canCropGrow(int water);

    /**
     * 実際に作物を成長させた時に呼ぶ
     * @param water 消費した水分の量
     */
    public void doGrow(int water);

    /**
     * 農地が保持している水の量
     * @return 量
     */
    public int getWater();

    /**
     * 農地に水を足す
     * @param amount 増やす量
     * @return 実際に増やせれた量
     */
    public int addWater(int amount);

    /**
     * 農地に撒かれている肥料を取得
     * @return
     */
    public IFertilizer getFertilizer();

    /**
     * 肥料をセット
     * @param fertilizer
     */
    public void setFertilizer(IFertilizer fertilizer);

}
