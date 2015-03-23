package shift.sextiarysector.renderer.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelGearShaftUp extends ModelBase {

	ModelRenderer b;
	ModelRenderer out;
	ModelRenderer in;

	ModelRenderer d1;
	ModelRenderer d2;
	ModelRenderer d3;
	ModelRenderer d4;

	ModelRenderer d5;
	ModelRenderer d6;
	ModelRenderer d7;
	ModelRenderer d8;

	ModelRenderer u1;

	public ModelGearShaftUp() {
		textureWidth = 64;
		textureHeight = 32;

		b = new ModelRenderer(this, 0, 0);
		b.addBox(-1F, -1F, -7F, 2, 2, 14);
		b.setRotationPoint(0F, 0F, 0F);
		b.setTextureSize(64, 32);
		b.mirror = true;
		out = new ModelRenderer(this, 33, 0);
		out.addBox(-4F, -4F, -8F, 8, 8, 4);
		out.setRotationPoint(0F, 0F, 0F);
		out.setTextureSize(64, 32);
		out.mirror = true;
		in = new ModelRenderer(this, 33, 13);
		in.addBox(-4F, -4F, 4F, 8, 8, 4);
		in.setRotationPoint(0F, 0F, 0F);
		in.setTextureSize(64, 32);
		in.mirror = true;

		d1 = new ModelRenderer(this, 0, 0);
		d1.addBox(-1F, -7F, 1F, 2, 2, 2);
		d1.setRotationPoint(0F, 0F, 0F);
		d1.setTextureSize(64, 32);
		d1.mirror = true;
		d2 = new ModelRenderer(this, 0, 0);
		d2.addBox(-7F, -1F, 1F, 2, 2, 2);
		d2.setRotationPoint(0F, 0F, 0F);
		d2.setTextureSize(64, 32);
		d2.mirror = true;
		d3 = new ModelRenderer(this, 0, 0);
		d3.addBox(5F, -1F, 1F, 2, 2, 2);
		d3.setRotationPoint(0F, 0F, 0F);
		d3.setTextureSize(64, 32);
		d3.mirror = true;
		d4 = new ModelRenderer(this, 0, 0);
		d4.addBox(-1F, 5F, 1F, 2, 2, 2);
		d4.setRotationPoint(0F, 0F, 0F);
		d4.setTextureSize(64, 32);
		d4.mirror = true;

		d5 = new ModelRenderer(this, 0, 0);
		d5.addBox(-1F, -7F, 0F, 2, 2, 2);
		d5.setRotationPoint(0F, 0F, 0F);
		d5.setTextureSize(64, 32);
		d5.mirror = true;
		this.setRotation(d5, 0, 0, 0.90F);
		d6 = new ModelRenderer(this, 0, 0);
		d6.addBox(-7F, -1F, 0F, 2, 2, 2);
		d6.setRotationPoint(0F, 0F, 0F);
		d6.setTextureSize(64, 32);
		d6.mirror = true;
		this.setRotation(d6, 0, 0, 0.90F);
		d7 = new ModelRenderer(this, 0, 0);
		d7.addBox(5F, -1F, 0F, 2, 2, 2);
		d7.setRotationPoint(0F, 0F, 0F);
		d7.setTextureSize(64, 32);
		d7.mirror = true;
		this.setRotation(d7, 0, 0, 0.90F);
		d8 = new ModelRenderer(this, 0, 0);
		d8.addBox(-1F, 5F, 0F, 2, 2, 2);
		d8.setRotationPoint(0F, 0F, 0F);
		d8.setTextureSize(64, 32);
		d8.mirror = true;
		this.setRotation(d8, 0, 0, 0.90F);

		u1 = new ModelRenderer(this, 0, 4);
		u1.addBox(-1F, -7F, -3F, 2, 2, 2);
		u1.setRotationPoint(0F, 0F, 0F);
		u1.setTextureSize(64, 32);
		u1.mirror = true;
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		b.render(f5);
		out.render(f5);
		in.render(f5);
	}

	public void renderDown(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		d1.render(f5);
		d2.render(f5);
		d3.render(f5);
		d4.render(f5);

		d5.render(f5);
		d6.render(f5);
		d7.render(f5);
		d8.render(f5);
	}

	public void renderUp(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		u1.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
