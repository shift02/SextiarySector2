package shift.sextiarysector.renderer.block;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.tileentity.TileEntityFarmland;

public class RendererFarmland implements ISimpleBlockRenderingHandler {

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {

    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {

        if (this.getRenderId() != modelId) return false;
        //renderer.field_152631_f = true;
        //IIcon i = renderer.overrideBlockTexture;
        //renderer.renderStandardBlock(Blocks.stone, x, y, z);
        renderer.renderStandardBlock(block, x, y, z);
        //renderer.setOverrideBlockTexture(i);

        renderer.renderAllFaces = true;

        float minx = 0.1875f, minz = 0.1875f;
        float maxx = 1 - 0.1875f, maxz = 1 - 0.1875f;

        if (this.isSame(world, x, y, z, block, ForgeDirection.getOrientation(4))) {
            minx = 0;
        }
        if (this.isSame(world, x, y, z, block, ForgeDirection.getOrientation(2))) {
            minz = 0;
        }
        if (this.isSame(world, x, y, z, block, ForgeDirection.getOrientation(5))) {
            maxx = 1;
        }
        if (this.isSame(world, x, y, z, block, ForgeDirection.getOrientation(3))) {
            maxz = 1;
        }
        renderer.setRenderBounds(minx, (1.0f / 16.0f) * 14, minz, maxx, 1 - 0.01f, maxz);
        renderer.renderStandardBlock(block, x, y, z);

        TileEntityFarmland t = (TileEntityFarmland) world.getTileEntity(x, y, z);

        if (t != null && t.getFertilizer() != null) {

            IIcon icon = renderer.overrideBlockTexture;
            renderer.setOverrideBlockTexture(t.getFertilizer().getFertilizerIcon());

            renderer.setRenderBounds(minx, 1 - 0.01f, minz, maxx, 1 - 0.009f, maxz);
            renderer.renderStandardBlock(block, x, y, z);

            renderer.setOverrideBlockTexture(icon);
        }

        //renderer.clearOverrideBlockTexture();

        renderer.renderAllFaces = false;
        //renderer.field_152631_f = false;

        return true;
    }

    private boolean isSame(IBlockAccess p_149743_1_, int p_149743_2_, int p_149743_3_, int p_149743_4_, Block b, ForgeDirection d) {

        if (p_149743_1_.getBlock(p_149743_2_ + d.offsetX, p_149743_3_ + d.offsetY, p_149743_4_ + d.offsetZ) == b) return true;

        return false;
    }

    public void renderTopBlock(Block p_147784_1_, int p_147784_2_, int p_147784_3_, int p_147784_4_, RenderBlocks renderer) {
        int l = p_147784_1_.colorMultiplier(renderer.blockAccess, p_147784_2_, p_147784_3_, p_147784_4_);
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

        renderFertilizer(p_147784_1_, p_147784_2_, p_147784_3_, p_147784_4_, f, f1, f2, renderer);

    }

    public void renderFertilizer(Block p_147808_1_, int p_147808_2_, int p_147808_3_, int p_147808_4_, float p_147808_5_, float p_147808_6_, float p_147808_7_, RenderBlocks renderer) {

        renderer.enableAO = true;
        boolean flag = false;
        float f3 = 0.0F;
        float f4 = 0.0F;
        float f5 = 0.0F;
        float f6 = 0.0F;
        boolean flag1 = true;
        int l = p_147808_1_.getMixedBrightnessForBlock(renderer.blockAccess, p_147808_2_, p_147808_3_, p_147808_4_);
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(983055);

        if (renderer.getBlockIcon(p_147808_1_).getIconName().equals("grass_top")) {
            flag1 = false;
        } else if (renderer.hasOverrideBlockTexture()) {
            flag1 = false;
        }

        boolean flag2;
        boolean flag3;
        boolean flag4;
        boolean flag5;
        int i1;
        float f7;

        if (p_147808_1_.shouldSideBeRendered(renderer.blockAccess, p_147808_2_, p_147808_3_ + 1, p_147808_4_, 1)) {
            if (renderer.renderMaxY >= 1.0D) {
                ++p_147808_3_;
            }

            renderer.aoBrightnessXYNP = p_147808_1_.getMixedBrightnessForBlock(renderer.blockAccess, p_147808_2_ - 1, p_147808_3_, p_147808_4_);
            renderer.aoBrightnessXYPP = p_147808_1_.getMixedBrightnessForBlock(renderer.blockAccess, p_147808_2_ + 1, p_147808_3_, p_147808_4_);
            renderer.aoBrightnessYZPN = p_147808_1_.getMixedBrightnessForBlock(renderer.blockAccess, p_147808_2_, p_147808_3_, p_147808_4_ - 1);
            renderer.aoBrightnessYZPP = p_147808_1_.getMixedBrightnessForBlock(renderer.blockAccess, p_147808_2_, p_147808_3_, p_147808_4_ + 1);
            renderer.aoLightValueScratchXYNP = renderer.blockAccess.getBlock(p_147808_2_ - 1, p_147808_3_, p_147808_4_).getAmbientOcclusionLightValue();
            renderer.aoLightValueScratchXYPP = renderer.blockAccess.getBlock(p_147808_2_ + 1, p_147808_3_, p_147808_4_).getAmbientOcclusionLightValue();
            renderer.aoLightValueScratchYZPN = renderer.blockAccess.getBlock(p_147808_2_, p_147808_3_, p_147808_4_ - 1).getAmbientOcclusionLightValue();
            renderer.aoLightValueScratchYZPP = renderer.blockAccess.getBlock(p_147808_2_, p_147808_3_, p_147808_4_ + 1).getAmbientOcclusionLightValue();
            flag2 = renderer.blockAccess.getBlock(p_147808_2_ + 1, p_147808_3_ + 1, p_147808_4_).getCanBlockGrass();
            flag3 = renderer.blockAccess.getBlock(p_147808_2_ - 1, p_147808_3_ + 1, p_147808_4_).getCanBlockGrass();
            flag4 = renderer.blockAccess.getBlock(p_147808_2_, p_147808_3_ + 1, p_147808_4_ + 1).getCanBlockGrass();
            flag5 = renderer.blockAccess.getBlock(p_147808_2_, p_147808_3_ + 1, p_147808_4_ - 1).getCanBlockGrass();

            if (!flag5 && !flag3) {
                renderer.aoLightValueScratchXYZNPN = renderer.aoLightValueScratchXYNP;
                renderer.aoBrightnessXYZNPN = renderer.aoBrightnessXYNP;
            } else {
                renderer.aoLightValueScratchXYZNPN = renderer.blockAccess.getBlock(p_147808_2_ - 1, p_147808_3_, p_147808_4_ - 1).getAmbientOcclusionLightValue();
                renderer.aoBrightnessXYZNPN = p_147808_1_.getMixedBrightnessForBlock(renderer.blockAccess, p_147808_2_ - 1, p_147808_3_, p_147808_4_ - 1);
            }

            if (!flag5 && !flag2) {
                renderer.aoLightValueScratchXYZPPN = renderer.aoLightValueScratchXYPP;
                renderer.aoBrightnessXYZPPN = renderer.aoBrightnessXYPP;
            } else {
                renderer.aoLightValueScratchXYZPPN = renderer.blockAccess.getBlock(p_147808_2_ + 1, p_147808_3_, p_147808_4_ - 1).getAmbientOcclusionLightValue();
                renderer.aoBrightnessXYZPPN = p_147808_1_.getMixedBrightnessForBlock(renderer.blockAccess, p_147808_2_ + 1, p_147808_3_, p_147808_4_ - 1);
            }

            if (!flag4 && !flag3) {
                renderer.aoLightValueScratchXYZNPP = renderer.aoLightValueScratchXYNP;
                renderer.aoBrightnessXYZNPP = renderer.aoBrightnessXYNP;
            } else {
                renderer.aoLightValueScratchXYZNPP = renderer.blockAccess.getBlock(p_147808_2_ - 1, p_147808_3_, p_147808_4_ + 1).getAmbientOcclusionLightValue();
                renderer.aoBrightnessXYZNPP = p_147808_1_.getMixedBrightnessForBlock(renderer.blockAccess, p_147808_2_ - 1, p_147808_3_, p_147808_4_ + 1);
            }

            if (!flag4 && !flag2) {
                renderer.aoLightValueScratchXYZPPP = renderer.aoLightValueScratchXYPP;
                renderer.aoBrightnessXYZPPP = renderer.aoBrightnessXYPP;
            } else {
                renderer.aoLightValueScratchXYZPPP = renderer.blockAccess.getBlock(p_147808_2_ + 1, p_147808_3_, p_147808_4_ + 1).getAmbientOcclusionLightValue();
                renderer.aoBrightnessXYZPPP = p_147808_1_.getMixedBrightnessForBlock(renderer.blockAccess, p_147808_2_ + 1, p_147808_3_, p_147808_4_ + 1);
            }

            if (renderer.renderMaxY >= 1.0D) {
                --p_147808_3_;
            }

            i1 = l;

            if (renderer.renderMaxY >= 1.0D || !renderer.blockAccess.getBlock(p_147808_2_, p_147808_3_ + 1, p_147808_4_).isOpaqueCube()) {
                i1 = p_147808_1_.getMixedBrightnessForBlock(renderer.blockAccess, p_147808_2_, p_147808_3_ + 1, p_147808_4_);
            }

            f7 = renderer.blockAccess.getBlock(p_147808_2_, p_147808_3_ + 1, p_147808_4_).getAmbientOcclusionLightValue();
            f6 = (renderer.aoLightValueScratchXYZNPP + renderer.aoLightValueScratchXYNP + renderer.aoLightValueScratchYZPP + f7) / 4.0F;
            f3 = (renderer.aoLightValueScratchYZPP + f7 + renderer.aoLightValueScratchXYZPPP + renderer.aoLightValueScratchXYPP) / 4.0F;
            f4 = (f7 + renderer.aoLightValueScratchYZPN + renderer.aoLightValueScratchXYPP + renderer.aoLightValueScratchXYZPPN) / 4.0F;
            f5 = (renderer.aoLightValueScratchXYNP + renderer.aoLightValueScratchXYZNPN + f7 + renderer.aoLightValueScratchYZPN) / 4.0F;
            renderer.brightnessTopRight = renderer.getAoBrightness(renderer.aoBrightnessXYZNPP, renderer.aoBrightnessXYNP, renderer.aoBrightnessYZPP, i1);
            renderer.brightnessTopLeft = renderer.getAoBrightness(renderer.aoBrightnessYZPP, renderer.aoBrightnessXYZPPP, renderer.aoBrightnessXYPP, i1);
            renderer.brightnessBottomLeft = renderer.getAoBrightness(renderer.aoBrightnessYZPN, renderer.aoBrightnessXYPP, renderer.aoBrightnessXYZPPN, i1);
            renderer.brightnessBottomRight = renderer.getAoBrightness(renderer.aoBrightnessXYNP, renderer.aoBrightnessXYZNPN, renderer.aoBrightnessYZPN, i1);
            renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = p_147808_5_;
            renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = p_147808_6_;
            renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = p_147808_7_;
            renderer.colorRedTopLeft *= f3;
            renderer.colorGreenTopLeft *= f3;
            renderer.colorBlueTopLeft *= f3;
            renderer.colorRedBottomLeft *= f4;
            renderer.colorGreenBottomLeft *= f4;
            renderer.colorBlueBottomLeft *= f4;
            renderer.colorRedBottomRight *= f5;
            renderer.colorGreenBottomRight *= f5;
            renderer.colorBlueBottomRight *= f5;
            renderer.colorRedTopRight *= f6;
            renderer.colorGreenTopRight *= f6;
            renderer.colorBlueTopRight *= f6;
            renderer.renderFaceYPos(p_147808_1_, p_147808_2_, p_147808_3_, p_147808_4_, renderer.getBlockIcon(p_147808_1_, renderer.blockAccess, p_147808_2_, p_147808_3_, p_147808_4_, 1));
            flag = true;
        }

        renderer.enableAO = false;

    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    @Override
    public int getRenderId() {
        return SextiarySector.proxy.farmlandType;
    }

}
