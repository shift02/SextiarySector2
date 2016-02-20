package shift.sextiarysector.api.agriculture;

public enum CropRendererType {

    Normal(6), Cross(1);

    public int id;

    CropRendererType(int renderID) {
        this.id = renderID;
    }

}
