package shift.sextiarysector.renderer.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelWindmill extends ModelBase {

	ModelRenderer Shape1;
	ModelRenderer out;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape5_1;
	ModelRenderer Shape5_2;
	ModelRenderer Shape5_3;
	ModelRenderer Shape5_4;
	ModelRenderer Shape6_1;
	ModelRenderer Shape6_2;
	ModelRenderer Shape7_1;
	ModelRenderer Shape7_2;

	public ModelWindmill() {

		textureWidth = 128;
		textureHeight = 64;

		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(-2F, -2F, -8F, 4, 4, 15);
		Shape1.setRotationPoint(0F, 0F, 0F);
		Shape1.setTextureSize(128, 64);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		out = new ModelRenderer(this, 0, 20);
		out.addBox(-4F, -4F, 4F, 8, 8, 4);
		out.setRotationPoint(0F, 0F, 0F);
		out.setTextureSize(128, 64);
		out.mirror = true;
		setRotation(out, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 0, 33);
		Shape2.addBox(-3F, -3F, -6F, 6, 6, 2);
		Shape2.setRotationPoint(0F, 0F, 0F);
		Shape2.setTextureSize(128, 64);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 0, 33);
		Shape3.addBox(-3F, -3F, -2F, 6, 6, 2);
		Shape3.setRotationPoint(0F, 0F, 0F);
		Shape3.setTextureSize(128, 64);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		Shape5_1 = new ModelRenderer(this, 40, 0);
		Shape5_1.addBox(-1.5F, -39F, -1.5F, 3, 36, 1);
		Shape5_1.setRotationPoint(0F, 0F, 0F);
		Shape5_1.setTextureSize(128, 64);
		Shape5_1.mirror = true;
		setRotation(Shape5_1, 0F, 0F, 0F);
		Shape5_2 = new ModelRenderer(this, 40, 0);
		Shape5_2.addBox(-1.5F, 3F, -1.5F, 3, 36, 1);
		Shape5_2.setRotationPoint(0F, 0F, 0F);
		Shape5_2.setTextureSize(128, 64);
		Shape5_2.mirror = true;
		setRotation(Shape5_2, 0F, 0F, 0F);
		Shape5_3 = new ModelRenderer(this, 40, 0);
		Shape5_3.addBox(-1.5F, 3F, -5.5F, 3, 36, 1);
		Shape5_3.setRotationPoint(0F, 0F, 0F);
		Shape5_3.setTextureSize(128, 64);
		Shape5_3.mirror = true;
		setRotation(Shape5_3, 0F, 0F, 1.570796F);
		Shape5_4 = new ModelRenderer(this, 40, 0);
		Shape5_4.addBox(-1.5F, 3F, -5.5F, 3, 36, 1);
		Shape5_4.setRotationPoint(0F, 0F, 0F);
		Shape5_4.setTextureSize(128, 64);
		Shape5_4.mirror = true;
		setRotation(Shape5_4, 0F, 0F, -1.570796F);
		Shape6_1 = new ModelRenderer(this, 50, 0);
		Shape6_1.addBox(1F, -38F, -2F, 10, 34, 1);
		Shape6_1.setRotationPoint(0F, 0F, 0F);
		Shape6_1.setTextureSize(128, 64);
		Shape6_1.mirror = true;
		setRotation(Shape6_1, 0F, -0.3490659F, 0F);
		Shape6_2 = new ModelRenderer(this, 50, 0);
		Shape6_2.addBox(-11F, 4F, -2F, 10, 34, 1);
		Shape6_2.setRotationPoint(0F, 0F, 0F);
		Shape6_2.setTextureSize(128, 64);
		Shape6_2.mirror = true;
		setRotation(Shape6_2, 0F, 0.3490659F, 0F);
		Shape7_1 = new ModelRenderer(this, 0, 43);
		Shape7_1.addBox(4F, 0F, -6F, 34, 10, 1);
		Shape7_1.setRotationPoint(0F, 0F, 0F);
		Shape7_1.setTextureSize(128, 64);
		Shape7_1.mirror = true;
		setRotation(Shape7_1, 0.3490659F, 0F, 0F);
		Shape7_2 = new ModelRenderer(this, 0, 43);
		Shape7_2.addBox(-38F, -9.5F, -5.5F, 34, 10, 1);
		Shape7_2.setRotationPoint(0F, 0F, 0F);
		Shape7_2.setTextureSize(128, 64);
		Shape7_2.mirror = true;
		setRotation(Shape7_2, -0.3490659F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3,float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape5_1.render(f5);
		Shape5_2.render(f5);
		Shape5_3.render(f5);
		Shape5_4.render(f5);
		Shape6_1.render(f5);
		Shape6_2.render(f5);
		Shape7_1.render(f5);
		Shape7_2.render(f5);
	}

	public void renderinOut(Entity entity, float f, float f1, float f2,	float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		out.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
