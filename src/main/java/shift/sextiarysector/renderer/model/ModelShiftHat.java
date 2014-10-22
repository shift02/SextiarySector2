package shift.sextiarysector.renderer.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

public class ModelShiftHat extends ModelBiped {

	ModelRenderer bipedHat1;
	ModelRenderer bipedHat2;

	public boolean isSneak;

	public ModelShiftHat() {

		this.textureWidth = 64;
		this.textureHeight = 32;

		this.bipedHat1 = new ModelRenderer(this, 0, 0);
		this.bipedHat1.addBox(-3F, -15F, -3F, 6, 6, 6,0.5f);
		this.bipedHat1.setTextureSize(64, 32);
		this.bipedHat1.setRotationPoint( 0F, 0F, 0F);
		this.bipedHat2 = new ModelRenderer(this, 0, 12);
		this.bipedHat2.addBox(-5F, -9F, -5F, 10, 1, 10,0.5f);
		this.bipedHat2.setRotationPoint(0F, 0F, 0F);
		this.bipedHat2.setTextureSize(64, 32);
		this.bipedHat2.setRotationPoint( 0F, 0F, 0F);
	}

	public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_)
    {
        this.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);

        if (this.isChild)
        {
            float f6 = 2.0F;
            GL11.glPushMatrix();
            GL11.glScalef(1.5F / f6, 1.5F / f6, 1.5F / f6);
            GL11.glTranslatef(0.0F, 16.0F * p_78088_7_, 0.0F);
            this.bipedHat1.render(p_78088_7_);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(1.5F / f6, 1.5F / f6, 1.5F / f6);
            GL11.glTranslatef(0.0F, 16.0F * p_78088_7_, 0.0F);
            this.bipedHat2.render(p_78088_7_);
            GL11.glPopMatrix();

        }
        else
        {
            this.bipedHat1.render(p_78088_7_);
            this.bipedHat2.render(p_78088_7_);
        }
    }

	public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
    {
        this.bipedHat1.rotateAngleY = p_78087_4_ / (180F / (float)Math.PI);
        this.bipedHat1.rotateAngleX = p_78087_5_ / (180F / (float)Math.PI);
        this.bipedHat2.rotateAngleY = p_78087_4_ / (180F / (float)Math.PI);
        this.bipedHat2.rotateAngleX = p_78087_5_ / (180F / (float)Math.PI);

        float f6;
        float f7;


        if (this.isSneak)
        {
            this.bipedHat1.rotationPointY = 1.0F;
            this.bipedHat2.rotationPointY = 1.0F;
        }
        else
        {
            this.bipedHat1.rotationPointY = 0.0F;
            this.bipedHat2.rotationPointY = 0.0F;
        }

    }

}
