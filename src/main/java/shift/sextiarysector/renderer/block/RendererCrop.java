package shift.sextiarysector.renderer.block;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.api.agriculture.CropRendererType;
import shift.sextiarysector.tileentity.TileEntityCrop;

public class RendererCrop implements ISimpleBlockRenderingHandler {

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {

    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {

        TileEntityCrop tCrop = (TileEntityCrop) world.getTileEntity(x, y, z);

        if (tCrop.getCrop() == null) return this.renderCrossedSquares(block, x, y, z, renderer);

        if (tCrop.getCrop().getRenderType() == CropRendererType.Normal) {
            return this.renderBlockCrops(block, x, y, z, renderer);
        } else {
            return this.renderCrossedSquares(block, x, y, z, renderer);
        }

    }

    public boolean renderBlockCrops(Block p_147796_1_, int x, int y, int z, RenderBlocks renderer) {
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(p_147796_1_.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z));
        tessellator.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        this.renderBlockCropsImpl(p_147796_1_, renderer.blockAccess.getBlockMetadata(x, y, z), x, y, z, x, y - 0.0625F, z, renderer);
        return true;
    }

    public void renderBlockCropsImpl(Block p_147795_1_, int p_147795_2_, int x, int y, int z, double p_147795_3_, double p_147795_5_, double p_147795_7_, RenderBlocks renderer) {
        Tessellator tessellator = Tessellator.instance;

        IIcon iicon = renderer.getBlockIcon(p_147795_1_, renderer.blockAccess, x, y, z, 0);
        //IIcon iicon = renderer.getBlockIconFromSideAndMetadata(p_147795_1_, 0, p_147795_2_);

        if (renderer.hasOverrideBlockTexture()) {
            iicon = renderer.overrideBlockTexture;
        }

        double d3 = iicon.getMinU();
        double d4 = iicon.getMinV();
        double d5 = iicon.getMaxU();
        double d6 = iicon.getMaxV();
        double d7 = p_147795_3_ + 0.5D - 0.25D;
        double d8 = p_147795_3_ + 0.5D + 0.25D;
        double d9 = p_147795_7_ + 0.5D - 0.5D;
        double d10 = p_147795_7_ + 0.5D + 0.5D;
        tessellator.addVertexWithUV(d7, p_147795_5_ + 1.0D, d9, d3, d4);
        tessellator.addVertexWithUV(d7, p_147795_5_ + 0.0D, d9, d3, d6);
        tessellator.addVertexWithUV(d7, p_147795_5_ + 0.0D, d10, d5, d6);
        tessellator.addVertexWithUV(d7, p_147795_5_ + 1.0D, d10, d5, d4);
        tessellator.addVertexWithUV(d7, p_147795_5_ + 1.0D, d10, d3, d4);
        tessellator.addVertexWithUV(d7, p_147795_5_ + 0.0D, d10, d3, d6);
        tessellator.addVertexWithUV(d7, p_147795_5_ + 0.0D, d9, d5, d6);
        tessellator.addVertexWithUV(d7, p_147795_5_ + 1.0D, d9, d5, d4);
        tessellator.addVertexWithUV(d8, p_147795_5_ + 1.0D, d10, d3, d4);
        tessellator.addVertexWithUV(d8, p_147795_5_ + 0.0D, d10, d3, d6);
        tessellator.addVertexWithUV(d8, p_147795_5_ + 0.0D, d9, d5, d6);
        tessellator.addVertexWithUV(d8, p_147795_5_ + 1.0D, d9, d5, d4);
        tessellator.addVertexWithUV(d8, p_147795_5_ + 1.0D, d9, d3, d4);
        tessellator.addVertexWithUV(d8, p_147795_5_ + 0.0D, d9, d3, d6);
        tessellator.addVertexWithUV(d8, p_147795_5_ + 0.0D, d10, d5, d6);
        tessellator.addVertexWithUV(d8, p_147795_5_ + 1.0D, d10, d5, d4);
        d7 = p_147795_3_ + 0.5D - 0.5D;
        d8 = p_147795_3_ + 0.5D + 0.5D;
        d9 = p_147795_7_ + 0.5D - 0.25D;
        d10 = p_147795_7_ + 0.5D + 0.25D;
        tessellator.addVertexWithUV(d7, p_147795_5_ + 1.0D, d9, d3, d4);
        tessellator.addVertexWithUV(d7, p_147795_5_ + 0.0D, d9, d3, d6);
        tessellator.addVertexWithUV(d8, p_147795_5_ + 0.0D, d9, d5, d6);
        tessellator.addVertexWithUV(d8, p_147795_5_ + 1.0D, d9, d5, d4);
        tessellator.addVertexWithUV(d8, p_147795_5_ + 1.0D, d9, d3, d4);
        tessellator.addVertexWithUV(d8, p_147795_5_ + 0.0D, d9, d3, d6);
        tessellator.addVertexWithUV(d7, p_147795_5_ + 0.0D, d9, d5, d6);
        tessellator.addVertexWithUV(d7, p_147795_5_ + 1.0D, d9, d5, d4);
        tessellator.addVertexWithUV(d8, p_147795_5_ + 1.0D, d10, d3, d4);
        tessellator.addVertexWithUV(d8, p_147795_5_ + 0.0D, d10, d3, d6);
        tessellator.addVertexWithUV(d7, p_147795_5_ + 0.0D, d10, d5, d6);
        tessellator.addVertexWithUV(d7, p_147795_5_ + 1.0D, d10, d5, d4);
        tessellator.addVertexWithUV(d7, p_147795_5_ + 1.0D, d10, d3, d4);
        tessellator.addVertexWithUV(d7, p_147795_5_ + 0.0D, d10, d3, d6);
        tessellator.addVertexWithUV(d8, p_147795_5_ + 0.0D, d10, d5, d6);
        tessellator.addVertexWithUV(d8, p_147795_5_ + 1.0D, d10, d5, d4);
    }

    public boolean renderCrossedSquares(Block p_147746_1_, int p_147746_2_, int p_147746_3_, int p_147746_4_, RenderBlocks renderer) {
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(p_147746_1_.getMixedBrightnessForBlock(renderer.blockAccess, p_147746_2_, p_147746_3_, p_147746_4_));
        int l = p_147746_1_.colorMultiplier(renderer.blockAccess, p_147746_2_, p_147746_3_, p_147746_4_);
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

        tessellator.setColorOpaque_F(f, f1, f2);
        double d1 = p_147746_2_;
        double d2 = p_147746_3_;
        double d0 = p_147746_4_;
        long i1;

        if (p_147746_1_ == Blocks.tallgrass) {
            i1 = p_147746_2_ * 3129871 ^ p_147746_4_ * 116129781L ^ p_147746_3_;
            i1 = i1 * i1 * 42317861L + i1 * 11L;
            d1 += ((i1 >> 16 & 15L) / 15.0F - 0.5D) * 0.5D;
            d2 += ((i1 >> 20 & 15L) / 15.0F - 1.0D) * 0.2D;
            d0 += ((i1 >> 24 & 15L) / 15.0F - 0.5D) * 0.5D;
        } else if (p_147746_1_ == Blocks.red_flower || p_147746_1_ == Blocks.yellow_flower) {
            i1 = p_147746_2_ * 3129871 ^ p_147746_4_ * 116129781L ^ p_147746_3_;
            i1 = i1 * i1 * 42317861L + i1 * 11L;
            d1 += ((i1 >> 16 & 15L) / 15.0F - 0.5D) * 0.3D;
            d0 += ((i1 >> 24 & 15L) / 15.0F - 0.5D) * 0.3D;
        }

        IIcon iicon = renderer.getBlockIcon(p_147746_1_, renderer.blockAccess, p_147746_2_, p_147746_3_, p_147746_4_, 0);
        renderer.drawCrossedSquares(iicon, d1, d2, d0, 1.0F);
        return true;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    @Override
    public int getRenderId() {
        return SextiarySector.proxy.cropType;
    }

}
