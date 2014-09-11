package shift.sextiarysector.renderer.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelsmallWindMill extends ModelBase
{
  //fields
    ModelRenderer shaft;
    ModelRenderer out;
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;

  public ModelsmallWindMill()
  {
    textureWidth = 64;
    textureHeight = 64;

      shaft = new ModelRenderer(this, 0, 0);
      shaft.addBox(-2F, -2F, -8F, 4, 4, 16);
      shaft.setRotationPoint(0F, 0F, 0F);
      shaft.setTextureSize(64, 64);
      shaft.mirror = true;
      setRotation(shaft, 0F, 0F, 0F);
      out = new ModelRenderer(this, 40, 0);
      out.addBox(-4F, -4F, 4.1F, 8, 8, 4);
      out.setRotationPoint(0F, 0F, 0F);
      out.setTextureSize(64, 64);
      out.mirror = true;
      setRotation(out, 0F, 0F, 0F);
      Shape1 = new ModelRenderer(this, 0, 20);
      Shape1.addBox(-1F, 2F, -6F, 2, 20, 1);
      Shape1.setRotationPoint(0F, 0F, 0F);
      Shape1.setTextureSize(64, 64);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 0, 20);
      Shape2.addBox(-1F, -22F, -6F, 2, 20, 1);
      Shape2.setRotationPoint(0F, 0F, 0F);
      Shape2.setTextureSize(64, 64);
      Shape2.mirror = true;
      setRotation(Shape2, 0F, 0F, 0F);
      Shape3 = new ModelRenderer(this, 0, 41);
      Shape3.addBox(2F, -1F, -3F, 20, 2, 1);
      Shape3.setRotationPoint(0F, 0F, 0F);
      Shape3.setTextureSize(64, 64);
      Shape3.mirror = true;
      setRotation(Shape3, 0F, 0F, 0F);
      Shape4 = new ModelRenderer(this, 0, 41);
      Shape4.addBox(-22F, -1F, -3F, 20, 2, 1);
      Shape4.setRotationPoint(0F, 0F, 0F);
      Shape4.setTextureSize(64, 64);
      Shape4.mirror = true;
      setRotation(Shape4, 0F, 0F, 0F);
      Shape5 = new ModelRenderer(this, 7, 21);
      Shape5.addBox(-1F, -21F, -6F, 6, 18, 1);
      Shape5.setRotationPoint(0F, 0F, 0F);
      Shape5.setTextureSize(64, 64);
      Shape5.mirror = true;
      setRotation(Shape5, 0F, -0.3490659F, 0F);
      Shape6 = new ModelRenderer(this, 7, 21);
      Shape6.addBox(-5F, 3F, -6F, 6, 18, 1);
      Shape6.setRotationPoint(0F, 0F, 0F);
      Shape6.setTextureSize(64, 64);
      Shape6.mirror = true;
      setRotation(Shape6, 0F, 0.3490659F, 0F);
      Shape7 = new ModelRenderer(this, 23, 21);
      Shape7.addBox(3F, 0F, -3F, 18, 6, 1);
      Shape7.setRotationPoint(0F, 0F, 0F);
      Shape7.setTextureSize(64, 64);
      Shape7.mirror = true;
      setRotation(Shape7, 0.3490659F, 0F, 0F);
      Shape8 = new ModelRenderer(this, 23, 21);
      Shape8.addBox(-21F, -6F, -3F, 18, 6, 1);
      Shape8.setRotationPoint(0F, 0F, 0F);
      Shape8.setTextureSize(64, 64);
      Shape8.mirror = true;
      setRotation(Shape8, -0.3490659F, 0F, 0F);
  }

  @Override
public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5,entity);
    shaft.render(f5);

    Shape1.render(f5);
    Shape2.render(f5);
    Shape3.render(f5);
    Shape4.render(f5);
    Shape5.render(f5);
    Shape6.render(f5);
    Shape7.render(f5);
    Shape8.render(f5);
  }

  public void renderinOut(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
	  super.render(entity, f, f1, f2, f3, f4, f5);
	    setRotationAngles(f, f1, f2, f3, f4, f5,entity);
	    out.render(f5);
    }

  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }

  @Override
public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5,Entity entity )
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}
