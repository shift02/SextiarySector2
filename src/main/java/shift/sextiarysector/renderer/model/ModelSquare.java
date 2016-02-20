package shift.sextiarysector.renderer.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSquare extends ModelBase {

    ModelRenderer side1;
    ModelRenderer side2;
    ModelRenderer side3;
    ModelRenderer side4;
    ModelRenderer under;
    ModelRenderer connection;

    public ModelSquare() {

        textureWidth = 64;
        textureHeight = 64;

        side1 = new ModelRenderer(this, 0, 0);
        side1.addBox(-7F, -6F, 5F, 12, 14, 2);
        side1.setRotationPoint(0F, 0F, 0F);
        side1.setTextureSize(64, 64);
        side1.mirror = true;
        setRotation(side1, 0F, 0F, 0F);
        side2 = new ModelRenderer(this, 0, 16);
        side2.addBox(5F, -6F, -5F, 2, 14, 12);
        side2.setRotationPoint(0F, 0F, 0F);
        side2.setTextureSize(64, 64);
        side2.mirror = true;
        setRotation(side2, 0F, 0F, 0F);
        side3 = new ModelRenderer(this, 28, 0);
        side3.addBox(-5F, -6F, -7F, 12, 14, 2);
        side3.setRotationPoint(0F, 0F, 0F);
        side3.setTextureSize(64, 64);
        side3.mirror = true;
        setRotation(side3, 0F, 0F, 0F);
        side4 = new ModelRenderer(this, 28, 16);
        side4.addBox(-7F, -6F, -7F, 2, 14, 12);
        side4.setRotationPoint(0F, 0F, 0F);
        side4.setTextureSize(64, 64);
        side4.mirror = true;
        setRotation(side4, 0F, 0F, 0F);
        under = new ModelRenderer(this, 0, 52);
        under.addBox(-5F, 6F, -5F, 10, 2, 10);
        under.setRotationPoint(0F, 0F, 0F);
        under.setTextureSize(64, 64);
        under.mirror = true;
        setRotation(under, 0F, 0F, 0F);
        connection = new ModelRenderer(this, 31, 49);
        connection.addBox(-4F, -4F, -8F, 8, 8, 4);
        connection.setRotationPoint(0F, 0F, 0F);
        connection.setTextureSize(64, 64);
        connection.mirror = true;
        setRotation(connection, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        side1.render(f5);
        side2.render(f5);
        side3.render(f5);
        side4.render(f5);
        under.render(f5);
    }

    public void renderConnection(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        connection.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

}
