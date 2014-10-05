package shift.sextiarysector.renderer.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMonitor extends ModelBase
{

	ModelRenderer Shape1;
	ModelRenderer Shape2;
    ModelRenderer Shape3;

    public ModelMonitor()
    {
    	textureWidth = 64;
    	textureHeight = 64;

    	Shape1 = new ModelRenderer(this, 0, 0);
    	Shape1.addBox(-6F, 7F, -6F, 12, 1, 12);
    	Shape1.setRotationPoint(0F, 0F, 0F);
    	Shape1.setTextureSize(64, 64);
    	Shape1.mirror = true;
    	setRotation(Shape1, 0F, 0F, 0F);
    	Shape2 = new ModelRenderer(this, 0, 18);
    	Shape2.addBox(-2F, -1F, -2F, 4, 8, 4);
    	Shape2.setRotationPoint(0F, 0F, 0F);
    	Shape2.setTextureSize(64, 64);
    	Shape2.mirror = true;
    	setRotation(Shape2, 0F, 0F, 0F);
    	Shape3 = new ModelRenderer(this, 0, 33);
    	Shape3.addBox(-7F, -2.5F, -7F, 14, 3, 14);
    	Shape3.setRotationPoint(0F, 0F, 0F);
    	Shape3.setTextureSize(64, 64);
    	Shape3.mirror = true;
    	setRotation(Shape3, 0.9599311F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {

    	super.render(entity, f, f1, f2, f3, f4, f5);
    	Shape1.render(f5);
    	Shape2.render(f5);
    	Shape3.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
    	model.rotateAngleX = x;
    	model.rotateAngleY = y;
    	model.rotateAngleZ = z;
    }

}
