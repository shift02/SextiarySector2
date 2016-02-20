package shift.sextiarysector.renderer.entity;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import shift.sextiarysector.entity.EntityMineboat;
import shift.sextiarysector.entity.EntityMineboatTank;

public class RenderMineboatTank extends RenderMineboat {

    @Override
    public void renderMineboat(EntityMineboat par1EntityBoat, double par2, double par4, double par6, float par8, float par9) {
        super.renderMineboat(par1EntityBoat, par2, par4, par6, par8, par9);

        GL11.glPushMatrix();
        GL11.glTranslatef((float) par2, (float) par4, (float) par6);
        GL11.glRotatef(180.0F - par8, 0.0F, 1.0F, 0.0F);
        float f2 = par1EntityBoat.getTimeSinceHit() - par9;
        float f3 = par1EntityBoat.getDamageTaken() - par9;

        if (f3 < 0.0F) {
            f3 = 0.0F;
        }

        if (f2 > 0.0F) {
            GL11.glRotatef(MathHelper.sin(f2) * f2 * f3 / 10.0F * par1EntityBoat.getForwardDirection(), 1.0F, 0.0F, 0.0F);
        }

        float f8 = 1.0f;
        GL11.glScalef(f8, f8, f8);
        GL11.glTranslatef(0.0F, 4 / 16.0F, 0.0F);

        if (((EntityMineboatTank) par1EntityBoat).hasFluidStack()) {

            EntityMineboatTank boat = (EntityMineboatTank) par1EntityBoat;

            IIcon icon = boat.getFluidStack().getFluid().getIcon(boat.getFluidStack());
            icon = blockrender.getIconSafe(icon);

            float i = 0.4375f;

            float f1 = boat.getRendererAmount();
            if (f1 >= 0.99) f1 = 0.98f;

            this.bindTexture(TextureMap.locationBlocksTexture);

            setColor3ub(boat.getFluidStack().getFluid().getColor());

            blockrender.blockAccess = boat.worldObj;

            blockrender.renderAllFaces = true;
            blockrender.setOverrideBlockTexture(icon);
            //blockrender.setRenderBounds(i*2.3, -0.0625*6, i*2.3, 1-i*2.5,0.0625*1.8f, 1-i*2.3);
            blockrender.setRenderBounds(-i + 0.01f, -0.49f, -i + 0.01f, i - 0.01f, f1 - 0.49f, i - 0.01f);
            //blockrender.setRenderBounds(i, 0.0625*2, i, 1-i,0.0625*8f, 1-i);
            //blockrender.renderStandardBlock(Block.blocksList[tileentity.getWorldObj().getBlockId(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord)], tileentity.xCoord, tileentity.yCoord,tileentity.zCoord);

            Tessellator tessellator = Tessellator.instance;

            Block block = Blocks.air;//boat.worldObj.getBlock(boat.posX, boat.posY, boat.posZ);

            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, -1.0F, 0.0F);
            blockrender.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, icon);
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, 1.0F, 0.0F);
            blockrender.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, icon);
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, 0.0F, -1.0F);
            blockrender.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, icon);
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, 0.0F, 1.0F);
            blockrender.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, icon);
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(-1.0F, 0.0F, 0.0F);
            blockrender.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, icon);
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(1.0F, 0.0F, 0.0F);
            blockrender.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, icon);
            tessellator.draw();

            blockrender.renderAllFaces = false;
            blockrender.clearOverrideBlockTexture();

        }

        /*
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
        }*/

        GL11.glPopMatrix();

    }

    public static void setColor3ub(int color) {

        GL11.glColor3ub((byte) (color >> 16 & 0xFF), (byte) (color >> 8 & 0xFF), (byte) (color & 0xFF));
    }
}
