package shift.sextiarysector.renderer.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPipe extends ModelBase
{

	ModelRenderer Core;
	ModelRenderer Pipe01;
	ModelRenderer Pipe02;
	ModelRenderer Pipe11;
	ModelRenderer Pipe12;
	ModelRenderer Pipe21;
	ModelRenderer Pipe22;
	ModelRenderer Pipe31;
	ModelRenderer Pipe32;
	ModelRenderer Pipe41;
	ModelRenderer Pipe42;
	ModelRenderer Pipe51;
	ModelRenderer Pipe52;

	public ModelPipe()
	{
		textureWidth = 64;
		textureHeight = 64;

		Core = new ModelRenderer(this, 0, 0);
		Core.addBox(-3F, -3F, -3F, 6, 6, 6);
		Core.setRotationPoint(0F, 0F, 0F);
		Core.setTextureSize(64, 64);
		Core.mirror = true;
		setRotation(Core, 0F, 0F, 0F);
		Pipe11 = new ModelRenderer(this, 0, 12);
		Pipe11.addBox(-3F, 3F, -3F, 6, 5, 6);
		Pipe11.setRotationPoint(0F, 0F, 0F);
		Pipe11.setTextureSize(64, 64);
		Pipe11.mirror = true;
		setRotation(Pipe11, 0F, 0F, 0F);
		Pipe12 = new ModelRenderer(this, 0, 23);
		Pipe12.addBox(-4F, 6F, -4F, 8, 2, 8);
		Pipe12.setRotationPoint(0F, 0F, 0F);
		Pipe12.setTextureSize(64, 64);
		Pipe12.mirror = true;
		setRotation(Pipe12, 0F, 0F, 0F);
		Pipe01 = new ModelRenderer(this, 0, 12);
		Pipe01.addBox(-3F, -8F, -3F, 6, 5, 6);
		Pipe01.setRotationPoint(0F, 0F, 0F);
		Pipe01.setTextureSize(64, 64);
		Pipe01.mirror = true;
		setRotation(Pipe01, 0F, 0F, 0F);
		Pipe02 = new ModelRenderer(this, 0, 23);
		Pipe02.addBox(-4F, -8F, -4F, 8, 2, 8);
		Pipe02.setRotationPoint(0F, 0F, 0F);
		Pipe02.setTextureSize(64, 64);
		Pipe02.mirror = true;
		setRotation(Pipe02, 0F, 0F, 0F);
		Pipe51 = new ModelRenderer(this, 0, 33);
		Pipe51.addBox(3F, -3F, -3F, 5, 6, 6);
		Pipe51.setRotationPoint(0F, 0F, 0F);
		Pipe51.setTextureSize(64, 64);
		Pipe51.mirror = true;
		setRotation(Pipe51, 0F, 0F, 0F);
		Pipe52 = new ModelRenderer(this, 0, 45);
		Pipe52.addBox(6F, -4F, -4F, 2, 8, 8);
		Pipe52.setRotationPoint(0F, 0F, 0F);
		Pipe52.setTextureSize(64, 64);
		Pipe52.mirror = true;
		setRotation(Pipe52, 0F, 0F, 0F);
		Pipe41 = new ModelRenderer(this, 0, 33);
		Pipe41.addBox(-8F, -3F, -3F, 5, 6, 6);
		Pipe41.setRotationPoint(0F, 0F, 0F);
		Pipe41.setTextureSize(64, 64);
		Pipe41.mirror = true;
		setRotation(Pipe41, 0F, 0F, 0F);
		Pipe42 = new ModelRenderer(this, 0, 45);
		Pipe42.addBox(-8F, -4F, -4F, 2, 8, 8);
		Pipe42.setRotationPoint(0F, 0F, 0F);
		Pipe42.setTextureSize(64, 64);
		Pipe42.mirror = true;
		setRotation(Pipe42, 0F, 0F, 0F);
		Pipe21 = new ModelRenderer(this, 32, 0);
		Pipe21.addBox(-3F, -3F, -8F, 6, 6, 5);
		Pipe21.setRotationPoint(0F, 0F, 0F);
		Pipe21.setTextureSize(64, 64);
		Pipe21.mirror = true;
		setRotation(Pipe21, 0F, 0F, 0F);
		Pipe22 = new ModelRenderer(this, 32, 11);
		Pipe22.addBox(-4F, -4F, -8F, 8, 8, 2);
		Pipe22.setRotationPoint(0F, 0F, 0F);
		Pipe22.setTextureSize(64, 64);
		Pipe22.mirror = true;
		setRotation(Pipe22, 0F, 0F, 0F);
		Pipe31 = new ModelRenderer(this, 32, 0);
		Pipe31.addBox(-3F, -3F, 3F, 6, 6, 5);
		Pipe31.setRotationPoint(0F, 0F, 0F);
		Pipe31.setTextureSize(64, 64);
		Pipe31.mirror = true;
		setRotation(Pipe31, 0F, 0F, 0F);
		Pipe32 = new ModelRenderer(this, 32, 11);
		Pipe32.addBox(-4F, -4F, 6F, 8, 8, 2);
		Pipe32.setRotationPoint(0F, 0F, 0F);
		Pipe32.setTextureSize(64, 64);
		Pipe32.mirror = true;
		setRotation(Pipe32, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		Core.render(f5);
	}

	public void renderPipe(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, int direction, boolean isStraight)
	{

		if (direction == 0) {

			Pipe01.render(f5);
			if (!isStraight) Pipe02.render(f5);

		} else if (direction == 1) {

			Pipe11.render(f5);
			if (!isStraight) Pipe12.render(f5);

		} else if (direction == 2) {

			Pipe21.render(f5);
			if (!isStraight) Pipe22.render(f5);

		} else if (direction == 3) {

			Pipe31.render(f5);
			if (!isStraight) Pipe32.render(f5);

		} else if (direction == 4) {

			Pipe41.render(f5);
			if (!isStraight) Pipe42.render(f5);

		} else if (direction == 5) {

			Pipe51.render(f5);
			if (!isStraight) Pipe52.render(f5);

		}

	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
