package shift.sextiarysector.renderer.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelGFDynamo extends ModelBase
{

	ModelRenderer Base1;
	ModelRenderer Base2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer Shape6;

	public ModelGFDynamo()
	{

		textureWidth = 64;
		textureHeight = 64;

		Base1 = new ModelRenderer(this, 0, 0);
		Base1.addBox(-8F, -8F, -8F, 16, 16, 10);
		Base1.setRotationPoint(0F, 0F, 0F);
		Base1.setTextureSize(64, 64);
		Base1.mirror = true;
		setRotation(Base1, 0F, 0F, 0F);
		Base2 = new ModelRenderer(this, 0, 26);
		Base2.addBox(-3F, -3F, 2F, 6, 6, 6);
		Base2.setRotationPoint(0F, 0F, 0F);
		Base2.setTextureSize(64, 64);
		Base2.mirror = true;
		setRotation(Base2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 0, 38);
		Shape3.addBox(-1F, -7F, -7.5F, 2, 14, 9);
		Shape3.setRotationPoint(0F, 0F, 0F);
		Shape3.setTextureSize(64, 64);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(this, 0, 38);
		Shape4.addBox(-1F, -7F, -7.5F, 2, 14, 9);
		Shape4.setRotationPoint(0F, 0F, 0F);
		Shape4.setTextureSize(64, 64);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0.7853982F);
		Shape5 = new ModelRenderer(this, 0, 38);
		Shape5.addBox(-1F, -7F, -7.5F, 2, 14, 9);
		Shape5.setRotationPoint(0F, 0F, 0F);
		Shape5.setTextureSize(64, 64);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, 1.570796F);
		Shape6 = new ModelRenderer(this, 0, 38);
		Shape6.addBox(-1F, -7F, -7.5F, 2, 14, 9);
		Shape6.setRotationPoint(0F, 0F, 0F);
		Shape6.setTextureSize(64, 64);
		Shape6.mirror = true;
		setRotation(Shape6, 0F, 0F, 2.356194F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		Base1.render(f5);
		Base2.render(f5);
	}

	public void renderShaft(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		Shape3.render(f5);
		Shape4.render(f5);
		Shape5.render(f5);
		Shape6.render(f5);

	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
