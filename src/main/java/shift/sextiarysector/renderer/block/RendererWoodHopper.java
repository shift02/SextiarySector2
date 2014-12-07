package shift.sextiarysector.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHopper;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.block.BlockWoodHopper;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RendererWoodHopper  implements ISimpleBlockRenderingHandler{

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId,RenderBlocks renderer) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,Block block, int modelId, RenderBlocks renderer) {
		return renderBlockHopper(world,(BlockWoodHopper) block, x, y, z, renderer);
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return false;
	}

	@Override
	public int getRenderId() {
		return SextiarySector.proxy.woodHopperType;
	}


	public boolean renderBlockHopper(IBlockAccess world,BlockWoodHopper p_147803_1_, int p_147803_2_, int p_147803_3_, int p_147803_4_, RenderBlocks renderer)
    {
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(p_147803_1_.getMixedBrightnessForBlock((IBlockAccess) world, p_147803_2_, p_147803_3_, p_147803_4_));
        int l = p_147803_1_.colorMultiplier((IBlockAccess) world, p_147803_2_, p_147803_3_, p_147803_4_);
        float f = (float)(l >> 16 & 255) / 255.0F;
        float f1 = (float)(l >> 8 & 255) / 255.0F;
        float f2 = (float)(l & 255) / 255.0F;

        if (EntityRenderer.anaglyphEnable)
        {
            float f3 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;
            float f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
            float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
            f = f3;
            f1 = f4;
            f2 = f5;
        }

        tessellator.setColorOpaque_F(f, f1, f2);
        return renderBlockHopperMetadata(world,p_147803_1_, p_147803_2_, p_147803_3_, p_147803_4_, world.getBlockMetadata(p_147803_2_, p_147803_3_, p_147803_4_), false, renderer);
    }

    public boolean renderBlockHopperMetadata(IBlockAccess world, BlockWoodHopper p_147799_1_, int p_147799_2_, int p_147799_3_, int p_147799_4_, int p_147799_5_, boolean p_147799_6_, RenderBlocks renderer)
    {
        Tessellator tessellator = Tessellator.instance;
        int i1 = BlockHopper.getDirectionFromMetadata(p_147799_5_);
        double d0 = 0.625D;
        renderer.setRenderBounds(0.0D, d0, 0.0D, 1.0D, 1.0D, 1.0D);

        if (p_147799_6_)
        {
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, -1.0F, 0.0F);
            renderer.renderFaceYNeg(p_147799_1_, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(p_147799_1_, 0, p_147799_5_));
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, 1.0F, 0.0F);
            renderer.renderFaceYPos(p_147799_1_, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(p_147799_1_, 1, p_147799_5_));
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, 0.0F, -1.0F);
            renderer.renderFaceZNeg(p_147799_1_, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(p_147799_1_, 2, p_147799_5_));
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, 0.0F, 1.0F);
            renderer.renderFaceZPos(p_147799_1_, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(p_147799_1_, 3, p_147799_5_));
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(-1.0F, 0.0F, 0.0F);
            renderer.renderFaceXNeg(p_147799_1_, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(p_147799_1_, 4, p_147799_5_));
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(1.0F, 0.0F, 0.0F);
            renderer.renderFaceXPos(p_147799_1_, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(p_147799_1_, 5, p_147799_5_));
            tessellator.draw();
        }
        else
        {
        	renderer.renderStandardBlock(p_147799_1_, p_147799_2_, p_147799_3_, p_147799_4_);
        }

        float f1;

        if (!p_147799_6_)
        {
            tessellator.setBrightness(p_147799_1_.getMixedBrightnessForBlock(world, p_147799_2_, p_147799_3_, p_147799_4_));
            int j1 = p_147799_1_.colorMultiplier(world, p_147799_2_, p_147799_3_, p_147799_4_);
            float f = (float)(j1 >> 16 & 255) / 255.0F;
            f1 = (float)(j1 >> 8 & 255) / 255.0F;
            float f2 = (float)(j1 & 255) / 255.0F;

            if (EntityRenderer.anaglyphEnable)
            {
                float f3 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;
                float f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
                float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
                f = f3;
                f1 = f4;
                f2 = f5;
            }

            tessellator.setColorOpaque_F(f, f1, f2);
        }

        IIcon iicon = p_147799_1_.getHopperIcon2("hopper_outside");
        IIcon iicon1 = p_147799_1_.getHopperIcon2("hopper_inside");
        f1 = 0.125F;

        if (p_147799_6_)
        {
            tessellator.startDrawingQuads();
            tessellator.setNormal(1.0F, 0.0F, 0.0F);
            renderer.renderFaceXPos(p_147799_1_, (double)(-1.0F + f1), 0.0D, 0.0D, iicon);
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(-1.0F, 0.0F, 0.0F);
            renderer.renderFaceXNeg(p_147799_1_, (double)(1.0F - f1), 0.0D, 0.0D, iicon);
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, 0.0F, 1.0F);
            renderer.renderFaceZPos(p_147799_1_, 0.0D, 0.0D, (double)(-1.0F + f1), iicon);
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, 0.0F, -1.0F);
            renderer.renderFaceZNeg(p_147799_1_, 0.0D, 0.0D, (double)(1.0F - f1), iicon);
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, 1.0F, 0.0F);
            renderer.renderFaceYPos(p_147799_1_, 0.0D, -1.0D + d0, 0.0D, iicon1);
            tessellator.draw();
        }
        else
        {
        	renderer.renderFaceXPos(p_147799_1_, (double)((float)p_147799_2_ - 1.0F + f1), (double)p_147799_3_, (double)p_147799_4_, iicon);
        	renderer.renderFaceXNeg(p_147799_1_, (double)((float)p_147799_2_ + 1.0F - f1), (double)p_147799_3_, (double)p_147799_4_, iicon);
        	renderer.renderFaceZPos(p_147799_1_, (double)p_147799_2_, (double)p_147799_3_, (double)((float)p_147799_4_ - 1.0F + f1), iicon);
        	renderer.renderFaceZNeg(p_147799_1_, (double)p_147799_2_, (double)p_147799_3_, (double)((float)p_147799_4_ + 1.0F - f1), iicon);
        	renderer.renderFaceYPos(p_147799_1_, (double)p_147799_2_, (double)((float)p_147799_3_ - 1.0F) + d0, (double)p_147799_4_, iicon1);
        }

        renderer.setOverrideBlockTexture(iicon);
        double d3 = 0.25D;
        double d4 = 0.25D;
        renderer.setRenderBounds(d3, d4, d3, 1.0D - d3, d0 - 0.002D, 1.0D - d3);

        if (p_147799_6_)
        {
            tessellator.startDrawingQuads();
            tessellator.setNormal(1.0F, 0.0F, 0.0F);
            renderer.renderFaceXPos(p_147799_1_, 0.0D, 0.0D, 0.0D, iicon);
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(-1.0F, 0.0F, 0.0F);
            renderer.renderFaceXNeg(p_147799_1_, 0.0D, 0.0D, 0.0D, iicon);
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, 0.0F, 1.0F);
            renderer.renderFaceZPos(p_147799_1_, 0.0D, 0.0D, 0.0D, iicon);
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, 0.0F, -1.0F);
            renderer.renderFaceZNeg(p_147799_1_, 0.0D, 0.0D, 0.0D, iicon);
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, 1.0F, 0.0F);
            renderer.renderFaceYPos(p_147799_1_, 0.0D, 0.0D, 0.0D, iicon);
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, -1.0F, 0.0F);
            renderer.renderFaceYNeg(p_147799_1_, 0.0D, 0.0D, 0.0D, iicon);
            tessellator.draw();
        }
        else
        {
        	renderer.renderStandardBlock(p_147799_1_, p_147799_2_, p_147799_3_, p_147799_4_);
        }

        if (!p_147799_6_)
        {
            double d1 = 0.375D;
            double d2 = 0.25D;
            renderer.setOverrideBlockTexture(iicon);

            if (i1 == 0)
            {
            	renderer.setRenderBounds(d1, 0.0D, d1, 1.0D - d1, 0.25D, 1.0D - d1);
                renderer.renderStandardBlock(p_147799_1_, p_147799_2_, p_147799_3_, p_147799_4_);
            }

            if (i1 == 2)
            {
            	renderer.setRenderBounds(d1, d4, 0.0D, 1.0D - d1, d4 + d2, d3);
            	renderer.renderStandardBlock(p_147799_1_, p_147799_2_, p_147799_3_, p_147799_4_);
            }

            if (i1 == 3)
            {
            	renderer.setRenderBounds(d1, d4, 1.0D - d3, 1.0D - d1, d4 + d2, 1.0D);
            	renderer.renderStandardBlock(p_147799_1_, p_147799_2_, p_147799_3_, p_147799_4_);
            }

            if (i1 == 4)
            {
            	renderer.setRenderBounds(0.0D, d4, d1, d3, d4 + d2, 1.0D - d1);
            	renderer.renderStandardBlock(p_147799_1_, p_147799_2_, p_147799_3_, p_147799_4_);
            }

            if (i1 == 5)
            {
            	renderer.setRenderBounds(1.0D - d3, d4, d1, 1.0D, d4 + d2, 1.0D - d1);
            	renderer.renderStandardBlock(p_147799_1_, p_147799_2_, p_147799_3_, p_147799_4_);
            }
        }

        renderer.clearOverrideBlockTexture();
        return true;
    }

}
