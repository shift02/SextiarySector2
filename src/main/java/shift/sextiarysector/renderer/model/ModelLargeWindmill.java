package shift.sextiarysector.renderer.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLargeWindmill extends ModelBase
{

	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape41;
	ModelRenderer Shape42;
	ModelRenderer Shape51;
	ModelRenderer Shape52;
	ModelRenderer Shape61;
	ModelRenderer Shape71;
	ModelRenderer Shape62;
	ModelRenderer Shape72;

	public ModelLargeWindmill()
	{

		this.textureWidth = 128;
		this.textureHeight = 128;

		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(-2F, -2F, -8.1F, 4, 4, 16);
		Shape1.setRotationPoint(0F, 0F, 0F);
		Shape1.setTextureSize(128, 128);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 0, 20);
		Shape2.addBox(-4F, -4F, 4F, 8, 8, 4);
		Shape2.setRotationPoint(0F, 0F, 0F);
		Shape2.setTextureSize(128, 128);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 42, 1);
		Shape3.addBox(-4F, -4F, -7F, 8, 8, 8);
		Shape3.setRotationPoint(0F, 0F, 0F);
		Shape3.setTextureSize(128, 128);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		Shape41 = new ModelRenderer(this, 0, 32);
		Shape41.addBox(-3F, -54F, -6F, 6, 50, 6);
		Shape41.setRotationPoint(0F, 0F, 0F);
		Shape41.setTextureSize(128, 128);
		Shape41.mirror = true;
		setRotation(Shape41, 0F, 0F, 0F);
		Shape42 = new ModelRenderer(this, 24, 44);
		Shape42.addBox(2F, -50F, -6F, 20, 40, 4);
		Shape42.setRotationPoint(0F, 0F, 0F);
		Shape42.setTextureSize(128, 128);
		Shape42.mirror = true;
		setRotation(Shape42, 0F, -0.3490659F, 0F);
		Shape51 = new ModelRenderer(this, 0, 32);
		Shape51.addBox(-3F, 4F, -6F, 6, 50, 6);
		Shape51.setRotationPoint(0F, 0F, 0F);
		Shape51.setTextureSize(128, 128);
		Shape51.mirror = true;
		setRotation(Shape51, 0F, 0F, 0F);
		Shape52 = new ModelRenderer(this, 24, 44);
		Shape52.addBox(-22F, 10F, -6F, 20, 40, 4);
		Shape52.setRotationPoint(0F, 0F, 0F);
		Shape52.setTextureSize(128, 128);
		Shape52.mirror = true;
		setRotation(Shape52, 0F, 0.3490659F, 0F);
		Shape61 = new ModelRenderer(this, 0, 88);
		Shape61.addBox(4F, -3F, -6F, 50, 6, 6);
		Shape61.setRotationPoint(0F, 0F, 0F);
		Shape61.setTextureSize(128, 128);
		Shape61.mirror = true;
		setRotation(Shape61, 0F, 0F, 0F);
		Shape71 = new ModelRenderer(this, 0, 88);
		Shape71.addBox(-54F, -3F, -6F, 50, 6, 6);
		Shape71.setRotationPoint(0F, 0F, 0F);
		Shape71.setTextureSize(128, 128);
		Shape71.mirror = true;
		setRotation(Shape71, 0F, 0F, 0F);
		Shape62 = new ModelRenderer(this, 0, 100);
		Shape62.addBox(10F, 2F, -6F, 40, 20, 4);
		Shape62.setRotationPoint(0F, 0F, 0F);
		Shape62.setTextureSize(128, 128);
		Shape62.mirror = true;
		setRotation(Shape62, 0.3490659F, 0F, 0F);
		Shape72 = new ModelRenderer(this, 0, 100);
		Shape72.addBox(-50F, -22F, -6F, 40, 20, 4);
		Shape72.setRotationPoint(0F, 0F, 0F);
		Shape72.setTextureSize(128, 128);
		Shape72.mirror = true;
		setRotation(Shape72, -0.3490659F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		Shape3.render(f5);
		Shape41.render(f5);
		Shape42.render(f5);
		Shape51.render(f5);
		Shape52.render(f5);
		Shape61.render(f5);
		Shape71.render(f5);
		Shape62.render(f5);
		Shape72.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void renderinOut(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		Shape1.render(f5);
		Shape2.render(f5);
	}

}
