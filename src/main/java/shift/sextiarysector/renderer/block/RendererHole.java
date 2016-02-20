package shift.sextiarysector.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.renderer.RenderSSBlocks;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RendererHole implements ISimpleBlockRenderingHandler {

    public static RenderSSBlocks renderer = new RenderSSBlocks();

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {

        if (this.getRenderId() != modelId) return false;

        this.renderer.blockAccess = world;

        this.renderer.renderAllFaces = true;
        this.renderer.field_152631_f = true;
        this.renderer.setRenderBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.625f, 1.0F);
        this.renderer.renderStandardBlock(block, x, y, z);

        //Tessellator tessellator = Tessellator.instance;
        //tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y+1, z));

        //renderer.setOverrideBlockTexture(Blocks.dirt.getIcon(0, 0));

        float f = 0.125F;

        float minx = 0;
        float minz = 0;
        float maxx = 1;
        float maxz = 1;

        if (!this.isSame(world, x, y, z, block, ForgeDirection.getOrientation(4))) {
            //tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y+1, z));
            this.renderer.setRenderBounds(0.0f, 0.0F, 0.0F, f, 1.0F, 1.0F);
            this.renderer.renderStandardBlock(block, x, y, z, ForgeDirection.getOrientation(4));
            minx = f;
        }

        if (!this.isSame(world, x, y, z, block, ForgeDirection.getOrientation(2))) {
            //tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
            this.renderer.setRenderBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
            this.renderer.renderStandardBlock(block, x, y, z, ForgeDirection.getOrientation(2));
            //this.renderStandardBlock(block, world, x, y, z, renderer);
            minz = f;
        }

        if (!this.isSame(world, x, y, z, block, ForgeDirection.getOrientation(5))) {
            //tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
            this.renderer.setRenderBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            this.renderer.renderStandardBlock(block, x, y, z, ForgeDirection.getOrientation(5));
            //this.renderStandardBlock(block, world, x, y, z, renderer);
            maxx = 1 - f;
        }

        if (!this.isSame(world, x, y, z, block, ForgeDirection.getOrientation(3))) {
            //tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
            this.renderer.setRenderBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
            this.renderer.renderStandardBlock(block, x, y, z, ForgeDirection.getOrientation(3));
            //this.renderStandardBlock(block, world, x, y, z, renderer);
            maxz = 1 - f;
        }

        //Tessellator tessellator = Tessellator.instance;
        //tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));

        //IIcon iicon = BlockLiquid.getLiquidIcon("water_still");
        //this.renderer.renderFaceYPos(block, (double)x, (double)((float)y - 1.0F + (15f/16f)), (double)z, iicon);

        this.renderer.field_152631_f = false;
        this.renderer.renderAllFaces = false;

        /*
        renderer.renderStandardBlock(block, x, y, z);
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
        int l = block.colorMultiplier(world, x, y, z);
        f = (float)(l >> 16 & 255) / 255.0F;
        float f1 = (float)(l >> 8 & 255) / 255.0F;
        float f2 = (float)(l & 255) / 255.0F;
        float f4;
        
        if (EntityRenderer.anaglyphEnable)
        {
            float f3 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;
            f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
            float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
            f = f3;
            f1 = f4;
            f2 = f5;
        }
        
        tessellator.setColorOpaque_F(f, f1, f2);
        IIcon iicon1 = block.getBlockTextureFromSide(2);
        f4 = 0.125F;
        renderer.renderFaceXPos(block, (double)((float)x - 1.0F + f4), (double)y, (double)z, iicon1);
        renderer.renderFaceXNeg(block, (double)((float)x + 1.0F - f4), (double)y, (double)z, iicon1);
        renderer.renderFaceZPos(block, (double)x, (double)y, (double)((float)z - 1.0F + f4), iicon1);
        renderer.renderFaceZNeg(block, (double)x, (double)y, (double)((float)z + 1.0F - f4), iicon1);
        IIcon iicon2 = block.getBlockTextureFromSide(2);
        renderer.renderFaceYPos(block, (double)x, (double)((float)y - 1.0F + 0.25F), (double)z, iicon2);
        renderer.renderFaceYNeg(block, (double)x, (double)((float)y + 1.0F - 0.75F), (double)z, iicon2);
        */

        return true;

    }

    protected boolean isSame(IBlockAccess p_149743_1_, int p_149743_2_, int p_149743_3_, int p_149743_4_, Block b, ForgeDirection d) {

        if (p_149743_1_.getBlock(p_149743_2_ + d.offsetX, p_149743_3_ + d.offsetY, p_149743_4_ + d.offsetZ) == b) return true;

        return false;
    }

    public boolean renderStandardBlock(Block p_147784_1_, IBlockAccess world, int p_147784_2_, int p_147784_3_, int p_147784_4_, RenderBlocks renderer) {
        int l = p_147784_1_.colorMultiplier(world, p_147784_2_, p_147784_3_, p_147784_4_);
        float f = (l >> 16 & 255) / 255.0F;
        float f1 = (l >> 8 & 255) / 255.0F;
        float f2 = (l & 255) / 255.0F;

        if (EntityRenderer.anaglyphEnable) {
            float f3 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;
            float f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
            float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
            f = f3;
            f1 = f4;
            f2 = f5;
        }

        return Minecraft.isAmbientOcclusionEnabled() && p_147784_1_.getLightValue() == 0
                ? (renderer.partialRenderBounds ? renderer.renderStandardBlockWithAmbientOcclusionPartial(p_147784_1_, p_147784_2_, p_147784_3_, p_147784_4_, f, f1, f2) : this
                        .renderStandardBlockWithColorMultiplier(p_147784_1_, world, p_147784_2_, p_147784_3_, p_147784_4_, f, f1, f2, renderer))
                : this.renderStandardBlockWithColorMultiplier(p_147784_1_, world, p_147784_2_, p_147784_3_, p_147784_4_, f, f1, f2,
                        renderer);

        //return this.renderStandardBlockWithColorMultiplier(p_147784_1_, world, p_147784_2_, p_147784_3_, p_147784_4_,  f, f1, f2, renderer);
    }

    public boolean renderStandardBlockWithColorMultiplier(Block p_147736_1_, IBlockAccess world, int p_147736_2_, int p_147736_3_, int p_147736_4_, float p_147736_5_, float p_147736_6_, float p_147736_7_,
            RenderBlocks renderer) {
        //System.out.println("AAA");
        renderer.enableAO = false;
        Tessellator tessellator = Tessellator.instance;
        boolean flag = false;
        float f3 = 0.5F;
        float f4 = 1.0F;
        float f5 = 0.8F;
        float f6 = 0.6F;
        float f7 = f4 * p_147736_5_;
        float f8 = f4 * p_147736_6_;
        float f9 = f4 * p_147736_7_;
        float f10 = f3;
        float f11 = f5;
        float f12 = f6;
        float f13 = f3;
        float f14 = f5;
        float f15 = f6;
        float f16 = f3;
        float f17 = f5;
        float f18 = f6;

        IBlockAccess blockAccess = world;

        if (p_147736_1_ != Blocks.grass) {
            f10 = f3 * p_147736_5_;
            f11 = f5 * p_147736_5_;
            f12 = f6 * p_147736_5_;
            f13 = f3 * p_147736_6_;
            f14 = f5 * p_147736_6_;
            f15 = f6 * p_147736_6_;
            f16 = f3 * p_147736_7_;
            f17 = f5 * p_147736_7_;
            f18 = f6 * p_147736_7_;
        }

        int l = p_147736_1_.getMixedBrightnessForBlock(blockAccess, p_147736_2_, p_147736_3_, p_147736_4_);
        tessellator.setBrightness(l);

        if (renderer.renderAllFaces || p_147736_1_.shouldSideBeRendered(blockAccess, p_147736_2_, p_147736_3_ - 1, p_147736_4_, 0)) {
            //tessellator.setBrightness(renderer.renderMinY > 0.0D ? l : p_147736_1_.getMixedBrightnessForBlock(blockAccess, p_147736_2_, p_147736_3_ - 1, p_147736_4_));
            tessellator.setColorOpaque_F(f10, f13, f16);
            renderer.renderFaceYNeg(p_147736_1_, p_147736_2_, p_147736_3_, p_147736_4_, renderer.getBlockIcon(p_147736_1_, blockAccess, p_147736_2_, p_147736_3_, p_147736_4_, 0));
            flag = true;
        }

        if (renderer.renderAllFaces || p_147736_1_.shouldSideBeRendered(blockAccess, p_147736_2_, p_147736_3_ + 1, p_147736_4_, 1)) {
            //tessellator.setBrightness(renderer.renderMaxY < 1.0D ? l : p_147736_1_.getMixedBrightnessForBlock(blockAccess, p_147736_2_, p_147736_3_ + 1, p_147736_4_));
            tessellator.setColorOpaque_F(f7, f8, f9);
            renderer.renderFaceYPos(p_147736_1_, p_147736_2_, p_147736_3_, p_147736_4_, renderer.getBlockIcon(p_147736_1_, blockAccess, p_147736_2_, p_147736_3_, p_147736_4_, 1));
            flag = true;
        }

        IIcon iicon;

        if (renderer.renderAllFaces || p_147736_1_.shouldSideBeRendered(blockAccess, p_147736_2_, p_147736_3_, p_147736_4_ - 1, 2)) {
            //tessellator.setBrightness(renderer.renderMinZ > 0.0D ? l : p_147736_1_.getMixedBrightnessForBlock(blockAccess, p_147736_2_, p_147736_3_, p_147736_4_ - 1));
            tessellator.setColorOpaque_F(f11, f14, f17);
            iicon = renderer.getBlockIcon(p_147736_1_, renderer.blockAccess, p_147736_2_, p_147736_3_, p_147736_4_, 2);
            renderer.renderFaceZNeg(p_147736_1_, p_147736_2_, p_147736_3_, p_147736_4_, iicon);

            flag = true;
        }

        if (renderer.renderAllFaces || p_147736_1_.shouldSideBeRendered(renderer.blockAccess, p_147736_2_, p_147736_3_, p_147736_4_ + 1, 3)) {
            //tessellator.setBrightness(renderer.renderMaxZ < 1.0D ? l : p_147736_1_.getMixedBrightnessForBlock(blockAccess, p_147736_2_, p_147736_3_, p_147736_4_ + 1));
            tessellator.setColorOpaque_F(f11, f14, f17);
            iicon = renderer.getBlockIcon(p_147736_1_, blockAccess, p_147736_2_, p_147736_3_, p_147736_4_, 3);
            renderer.renderFaceZPos(p_147736_1_, p_147736_2_, p_147736_3_, p_147736_4_, iicon);

            flag = true;
        }

        if (renderer.renderAllFaces || p_147736_1_.shouldSideBeRendered(blockAccess, p_147736_2_ - 1, p_147736_3_, p_147736_4_, 4)) {
            //tessellator.setBrightness(renderer.renderMinX > 0.0D ? l : p_147736_1_.getMixedBrightnessForBlock(blockAccess, p_147736_2_ - 1, p_147736_3_, p_147736_4_));
            tessellator.setColorOpaque_F(f12, f15, f18);
            iicon = renderer.getBlockIcon(p_147736_1_, blockAccess, p_147736_2_, p_147736_3_, p_147736_4_, 4);
            renderer.renderFaceXNeg(p_147736_1_, p_147736_2_, p_147736_3_, p_147736_4_, iicon);

            flag = true;
        }

        if (renderer.renderAllFaces || p_147736_1_.shouldSideBeRendered(blockAccess, p_147736_2_ + 1, p_147736_3_, p_147736_4_, 5)) {
            //tessellator.setBrightness(renderer.renderMaxX < 1.0D ? l : p_147736_1_.getMixedBrightnessForBlock(blockAccess, p_147736_2_ + 1, p_147736_3_, p_147736_4_));
            tessellator.setColorOpaque_F(f12, f15, f18);
            iicon = renderer.getBlockIcon(p_147736_1_, blockAccess, p_147736_2_, p_147736_3_, p_147736_4_, 5);
            renderer.renderFaceXPos(p_147736_1_, p_147736_2_, p_147736_3_, p_147736_4_, iicon);

            flag = true;
        }

        return flag;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    @Override
    public int getRenderId() {
        return SextiarySector.proxy.holeType;
    }

}
