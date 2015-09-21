package shift.sextiarysector.renderer.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSaw extends ModelBase {
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;

    public ModelSaw() {
        textureWidth = 64;
        textureHeight = 64;

        Shape1 = new ModelRenderer(this, 0, 0);
        Shape1.addBox(-8F, -8F, -4F, 16, 16, 12);
        Shape1.setRotationPoint(0F, 0F, 0F);
        Shape1.setTextureSize(64, 64);
        Shape1.mirror = true;
        setRotation(Shape1, 0F, 0F, 0F);
        Shape2 = new ModelRenderer(this, 0, 28);
        Shape2.addBox(-5.5F, -1F, -5.5F, 11, 2, 11);
        Shape2.setRotationPoint(0F, 0F, 0F);
        Shape2.setTextureSize(64, 64);
        Shape2.mirror = true;
        setRotation(Shape2, 0F, 0F, 0F);
        Shape3 = new ModelRenderer(this, 0, 28);
        Shape3.addBox(-5.5F, -1.1F, -5.5F, 11, 2, 11);
        Shape3.setRotationPoint(0F, 0F, 0F);
        Shape3.setTextureSize(64, 64);
        Shape3.mirror = true;
        setRotation(Shape3, 0F, 0.5235988F, 0F);
        Shape4 = new ModelRenderer(this, 0, 28);
        Shape4.addBox(-5.5F, -0.9F, -5.5F, 11, 2, 11);
        Shape4.setRotationPoint(0F, 0F, 0F);
        Shape4.setTextureSize(64, 64);
        Shape4.mirror = true;
        setRotation(Shape4, 0F, 1.047198F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        Shape1.render(f5);
    }

    public void renderBlade(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        Shape2.render(f5);
        Shape3.render(f5);
        Shape4.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

}
