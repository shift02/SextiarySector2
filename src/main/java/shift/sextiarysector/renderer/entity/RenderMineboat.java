package shift.sextiarysector.renderer.entity;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderBoat;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import shift.sextiarysector.entity.EntityMineboat;
import shift.sextiarysector.renderer.model.ModelMineboat;

public class RenderMineboat extends RenderBoat{

	private static final ResourceLocation field_110782_f = new ResourceLocation("textures/entity/boat.png");

	protected final RenderBlocks blockrender;

	public RenderMineboat()
    {
        this.shadowSize = 0.5F;
        this.modelBoat = new ModelMineboat();
        //
        this.blockrender = new RenderBlocks();
    }

	public void renderMineboat(EntityMineboat par1EntityBoat, double par2, double par4, double par6, float par8, float par9)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)par2, (float)par4, (float)par6);
        GL11.glRotatef(180.0F - par8, 0.0F, 1.0F, 0.0F);
        float f2 = par1EntityBoat.getTimeSinceHit() - par9;
        float f3 = par1EntityBoat.getDamageTaken() - par9;

        if (f3 < 0.0F)
        {
            f3 = 0.0F;
        }

        if (f2 > 0.0F)
        {
            GL11.glRotatef(MathHelper.sin(f2) * f2 * f3 / 10.0F * par1EntityBoat.getForwardDirection(), 1.0F, 0.0F, 0.0F);
        }

        //kokokara
        int j = 4;//par1EntityBoat.getDisplayTileOffset();
        Block block = par1EntityBoat.getDisplayTile();
        int k = par1EntityBoat.getDisplayTileData();

        if (block != null)
        {
            GL11.glPushMatrix();
            this.bindTexture(TextureMap.locationBlocksTexture);
            float f8 = 1.0f;//0.75F;
            GL11.glScalef(f8, f8, f8);
            GL11.glTranslatef(0.0F, j / 16.0F, 0.0F);
            this.renderBlockInMinecart(par1EntityBoat, par9, block, k);
            GL11.glPopMatrix();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.bindEntityTexture(par1EntityBoat);
        }
        //kokomade

        float f4 = 0.75F;
        GL11.glScalef(f4, f4, f4);
        GL11.glScalef(1.0F / f4, 1.0F / f4, 1.0F / f4);
        this.bindEntityTexture(par1EntityBoat);
        GL11.glScalef(-1.0F, -1.0F, 1.0F);
        this.modelBoat.render(par1EntityBoat, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    }

	//
    protected void renderBlockInMinecart(EntityMineboat par1EntityMineboat, float par2, Block par3Block, int par4)
    {
        float f1 = par1EntityMineboat.getBrightness(par2);
        GL11.glPushMatrix();
        this.blockrender.renderBlockAsItem(par3Block, par4, f1);
        GL11.glPopMatrix();
    }

    @Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderMineboat((EntityMineboat)par1Entity, par2, par4, par6, par8, par9);
    }
}
