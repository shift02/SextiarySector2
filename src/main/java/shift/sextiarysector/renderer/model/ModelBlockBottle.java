package shift.sextiarysector.renderer.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBlockBottle extends ModelBase {
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape1;

    public ModelBlockBottle() {
        textureWidth = 64;
        textureHeight = 64;

        Shape2 = new ModelRenderer(this, 0, 0);
        Shape2.addBox(-5.5F, -8F, -5.5F, 11, 2, 11);
        Shape2.setRotationPoint(0F, 0F, 0F);
        Shape2.setTextureSize(64, 64);
        Shape2.mirror = true;
        Shape3 = new ModelRenderer(this, 0, 14);
        Shape3.addBox(-2F, -6F, -2F, 4, 5, 4);
        Shape3.setRotationPoint(0F, 0F, 0F);
        Shape3.setTextureSize(64, 64);
        Shape3.mirror = true;
        Shape4 = new ModelRenderer(this, 0, 23);
        Shape4.addBox(-6F, -3F, -6F, 12, 11, 12);
        Shape4.setRotationPoint(0F, 0F, 0F);
        Shape4.setTextureSize(64, 64);
        Shape4.mirror = true;
        Shape1 = new ModelRenderer(this, 0, 46);
        Shape1.addBox(-3F, -5F, -3F, 6, 2, 6);
        Shape1.setRotationPoint(0F, 0F, 0F);
        Shape1.setTextureSize(64, 64);
        Shape1.mirror = true;
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        Shape2.render(f5);
        Shape3.render(f5);
        Shape4.render(f5);
        Shape1.render(f5);
    }

}
