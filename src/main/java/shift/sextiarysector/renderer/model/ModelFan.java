package shift.sextiarysector.renderer.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFan extends ModelBase {
    //fields
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;

    public ModelFan() {
        textureWidth = 64;
        textureHeight = 64;

        Shape1 = new ModelRenderer(this, 0, 0);
        Shape1.addBox(-8F, -8F, -8F, 16, 16, 12);
        Shape1.setRotationPoint(0F, 0F, 0F);
        Shape1.setTextureSize(64, 64);
        Shape1.mirror = true;
        setRotation(Shape1, 0F, 0F, 0F);
        Shape2 = new ModelRenderer(this, 0, 48);
        Shape2.addBox(-2F, -2F, 4.5F, 4, 4, 3);
        Shape2.setRotationPoint(0F, 0F, 0F);
        Shape2.setTextureSize(64, 64);
        Shape2.mirror = true;
        setRotation(Shape2, 0F, 0F, 0F);
        Shape3 = new ModelRenderer(this, 0, 55);
        Shape3.addBox(-2F, -6F, 5.5F, 2, 4, 1);
        Shape3.setRotationPoint(0F, 0F, 0F);
        Shape3.setTextureSize(64, 64);
        Shape3.mirror = true;
        setRotation(Shape3, 0F, 0.1745329F, 0F);
        Shape4 = new ModelRenderer(this, 0, 55);
        Shape4.addBox(0F, 2F, 5.5F, 2, 4, 1);
        Shape4.setRotationPoint(0F, 0F, 0F);
        Shape4.setTextureSize(64, 64);
        Shape4.mirror = true;
        setRotation(Shape4, 0F, -0.1745329F, 0F);
        Shape5 = new ModelRenderer(this, 6, 55);
        Shape5.addBox(2F, -2F, 5.5F, 4, 2, 1);
        Shape5.setRotationPoint(0F, 0F, 0F);
        Shape5.setTextureSize(64, 64);
        Shape5.mirror = true;
        setRotation(Shape5, -0.1745329F, 0F, 0F);
        Shape6 = new ModelRenderer(this, 6, 55);
        Shape6.addBox(-6F, 0F, 5.5F, 4, 2, 1);
        Shape6.setRotationPoint(0F, 0F, 0F);
        Shape6.setTextureSize(64, 64);
        Shape6.mirror = true;
        setRotation(Shape6, 0.1745329F, 0F, 0F);
        Shape7 = new ModelRenderer(this, 0, 28);
        Shape7.addBox(-8F, -8F, 4F, 16, 16, 4);
        Shape7.setRotationPoint(0F, 0F, 0F);
        Shape7.setTextureSize(64, 64);
        Shape7.mirror = true;
        setRotation(Shape7, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        Shape1.render(f5);
        Shape7.render(f5);
    }

    public void renderFan(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        Shape2.render(f5);
        Shape3.render(f5);
        Shape4.render(f5);
        Shape5.render(f5);
        Shape6.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

}
