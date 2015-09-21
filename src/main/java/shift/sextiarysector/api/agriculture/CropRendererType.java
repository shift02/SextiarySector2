package shift.sextiarysector.api.agriculture;

public enum CropRendererType {

    Normal(6), Close(1);

    public int id;

    CropRendererType(int renderID) {
        this.id = renderID;
    }

}
