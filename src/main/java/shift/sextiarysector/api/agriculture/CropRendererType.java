package shift.sextiarysector.api.agriculture;

/**
 * 作物の描画タイプ
 * @author Shift02
 *
 */
public enum CropRendererType {

    Normal(6), Cross(1);

    public int id;

    CropRendererType(int renderID) {
        this.id = renderID;
    }

}
