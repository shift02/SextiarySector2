package shift.sextiarysector.renderer.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSmallWaterwheel extends ModelBase
{
  //fields
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape2_1;
    ModelRenderer Shape2_2;
    ModelRenderer Shape2_3;
    ModelRenderer Shape3;

  public ModelSmallWaterwheel()
  {
    textureWidth = 64;
    textureHeight = 64;

      Shape1 = new ModelRenderer(this, 18, 0);
      Shape1.addBox(-2F, -2F, -8F, 4, 4, 16);
      Shape1.setRotationPoint(0F, 0F, 0F);
      Shape1.setTextureSize(64, 64);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 0, 0);
      Shape2.addBox(-1F, -24F, -6F, 2, 48, 7);
      Shape2.setRotationPoint(0F, 0F, 0F);
      Shape2.setTextureSize(64, 64);
      Shape2.mirror = true;
      setRotation(Shape2, 0F, 0F, 0F);
      Shape2_1 = new ModelRenderer(this, 0, 0);
      Shape2_1.addBox(-1F, -24F, -6F, 2, 48, 7);
      Shape2_1.setRotationPoint(0F, 0F, 0F);
      Shape2_1.setTextureSize(64, 64);
      Shape2_1.mirror = true;
      setRotation(Shape2_1, 0F, 0F, 0.7853982F);
      Shape2_2 = new ModelRenderer(this, 0, 0);
      Shape2_2.addBox(-1F, -24F, -6F, 2, 48, 7);
      Shape2_2.setRotationPoint(0F, 0F, 0F);
      Shape2_2.setTextureSize(64, 64);
      Shape2_2.mirror = true;
      setRotation(Shape2_2, 0F, 0F, -0.7853982F);
      Shape2_3 = new ModelRenderer(this, 0, 0);
      Shape2_3.addBox(-1F, -24F, -6F, 2, 48, 7);
      Shape2_3.setRotationPoint(0F, 0F, 0F);
      Shape2_3.setTextureSize(64, 64);
      Shape2_3.mirror = true;
      setRotation(Shape2_3, 0F, 0F, 1.570796F);
      Shape3 = new ModelRenderer(this, 18, 20);
      Shape3.addBox(-4F, -4F, 4.11F, 8, 8, 4);
      Shape3.setRotationPoint(0F, 0F, 0F);
      Shape3.setTextureSize(64, 64);
      Shape3.mirror = true;
      setRotation(Shape3, 0F, 0F, 0F);
  }

  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    Shape2.render(f5);
    Shape2_1.render(f5);
    Shape2_2.render(f5);
    Shape2_3.render(f5);
    Shape3.render(f5);
  }

  public void renderinOut(Entity entity, float f, float f1, float f2,float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Shape1.render(f5);
	}

  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }


}
