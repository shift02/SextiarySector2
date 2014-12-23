package shift.sextiarysector.renderer.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMotor extends ModelBase
{
  //fields
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;

  public ModelMotor()
  {
    textureWidth = 64;
    textureHeight = 64;

      Shape1 = new ModelRenderer(this, 0, 40);
      Shape1.addBox(-2F, -2F, -7F, 4, 4, 14);
      Shape1.setRotationPoint(0F, 0F, 0F);
      Shape1.setTextureSize(64, 64);
      Shape1.mirror = true;
      Shape2 = new ModelRenderer(this, 0, 0);
      Shape2.addBox(-8F, -8F, -8F, 16, 16, 6);
      Shape2.setRotationPoint(0F, 0F, 0F);
      Shape2.setTextureSize(64, 64);
      Shape2.mirror = true;
      Shape3 = new ModelRenderer(this, 0, 22);
      Shape3.addBox(-4F, -4F, -2F, 8, 8, 10);
      Shape3.setRotationPoint(0F, 0F, 0F);
      Shape3.setTextureSize(64, 64);
      Shape3.mirror = true;
  }

  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);

    Shape2.render(f5);
    Shape3.render(f5);
  }

  public void renderShaft(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
	  Shape1.render(f5);
  }

}
