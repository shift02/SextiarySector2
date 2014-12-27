package shift.sextiarysector.renderer.model;

import net.minecraft.client.model.ModelBoat;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMineboat  extends ModelBoat{

	public ModelRenderer[] boatSides = new ModelRenderer[7];

    public ModelMineboat()
    {
        this.boatSides[0] = new ModelRenderer(this, 0, 8);
        this.boatSides[1] = new ModelRenderer(this, 0, 0);
        this.boatSides[2] = new ModelRenderer(this, 0, 0);
        this.boatSides[3] = new ModelRenderer(this, 0, 0);
        this.boatSides[4] = new ModelRenderer(this, 0, 0);
        this.boatSides[5] = new ModelRenderer(this, 44, 10);
        byte b0 = 24;
        byte b1 = 6;
        byte b2 = 20;
        byte b3 = 4;
        this.boatSides[0].addBox(-b0 / 2, -b2 / 2 + 2, -3.0F, b0, b2 - 4, 4, 0.0F);
        this.boatSides[0].setRotationPoint(0.0F, b3, 0.0F);
        this.boatSides[5].addBox(-b0 / 2 + 1, -b2 / 2 + 1, -1.0F, b0 - 2, b2 - 2, 1, 0.0F);
        this.boatSides[5].setRotationPoint(0.0F, b3, 0.0F);
        this.boatSides[1].addBox(-b0 / 2 + 2, -b1 - 1, -1.0F, b0 - 4, b1, 2, 0.0F);
        this.boatSides[1].setRotationPoint(-b0 / 2 + 1, b3, 0.0F);
        this.boatSides[2].addBox(-b0 / 2 + 2, -b1 - 1, -1.0F, b0 - 4, b1, 2, 0.0F);
        this.boatSides[2].setRotationPoint(b0 / 2 - 1, b3, 0.0F);
        this.boatSides[3].addBox(-b0 / 2 + 2, -b1 - 1, -1.0F, b0 - 4, b1, 2, 0.0F);
        this.boatSides[3].setRotationPoint(0.0F, b3, -b2 / 2 + 1);
        this.boatSides[4].addBox(-b0 / 2 + 2, -b1 - 1, -1.0F, b0 - 4, b1, 2, 0.0F);
        this.boatSides[4].setRotationPoint(0.0F, b3, b2 / 2 - 1);
        this.boatSides[0].rotateAngleX = ((float)Math.PI / 2F);
        this.boatSides[1].rotateAngleY = ((float)Math.PI * 3F / 2F);
        this.boatSides[2].rotateAngleY = ((float)Math.PI / 2F);
        this.boatSides[3].rotateAngleY = (float)Math.PI;
        this.boatSides[5].rotateAngleX = -((float)Math.PI / 2F);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    @Override
	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
    {
    	this.boatSides[5].rotationPointY = 4.0F - par4;

        for (int i = 0; i < 6; ++i)
        {
            this.boatSides[i].render(par7);
        }
    }
}
