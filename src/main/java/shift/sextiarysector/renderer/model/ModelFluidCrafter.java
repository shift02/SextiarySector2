package shift.sextiarysector.renderer.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFluidCrafter extends ModelBase {
    ModelRenderer Shape1;

    public ModelFluidCrafter() {
        textureWidth = 64;
        textureHeight = 32;

        Shape1 = new ModelRenderer(this, 0, 0);
        Shape1.addBox(-4F, -4F, -4F, 8, 8, 8);
        Shape1.setRotationPoint(0F, 0F, 0F);
        Shape1.setTextureSize(64, 32);
        Shape1.mirror = true;

    }

    public void render(Entity entity, float f, float f1, float f2, float f3,
            float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        Shape1.render(f5);
    }

}
