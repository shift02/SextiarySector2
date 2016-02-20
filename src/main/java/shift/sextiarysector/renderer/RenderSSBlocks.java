package shift.sextiarysector.renderer;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;

public class RenderSSBlocks extends RenderBlocks {

    public boolean renderStandardBlock(Block p_147784_1_, int p_147784_2_, int p_147784_3_, int p_147784_4_, ForgeDirection d) {
        int l = p_147784_1_.colorMultiplier(this.blockAccess, p_147784_2_, p_147784_3_, p_147784_4_);
        float f = (float) (l >> 16 & 255) / 255.0F;
        float f1 = (float) (l >> 8 & 255) / 255.0F;
        float f2 = (float) (l & 255) / 255.0F;

        if (EntityRenderer.anaglyphEnable) {
            float f3 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;
            float f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
            float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
            f = f3;
            f1 = f4;
            f2 = f5;
        }

        return Minecraft.isAmbientOcclusionEnabled() && p_147784_1_.getLightValue() == 0
                ? (this.partialRenderBounds ? this.renderStandardBlockWithAmbientOcclusionPartial(p_147784_1_, p_147784_2_, p_147784_3_, p_147784_4_, f, f1, f2, d)
                        : this.renderStandardBlockWithAmbientOcclusion(p_147784_1_, p_147784_2_, p_147784_3_, p_147784_4_, f, f1, f2))
                : this.renderStandardBlockWithColorMultiplier(p_147784_1_, p_147784_2_, p_147784_3_, p_147784_4_, f, f1, f2);
    }

    public boolean renderStandardBlockWithAmbientOcclusionPartial(Block p_147808_1_, int p_147808_2_, int p_147808_3_, int p_147808_4_, float p_147808_5_, float p_147808_6_, float p_147808_7_, ForgeDirection d) {

        this.enableAO = true;
        boolean flag = false;
        float f3 = 0.0F;
        float f4 = 0.0F;
        float f5 = 0.0F;
        float f6 = 0.0F;
        boolean flag1 = true;
        int l = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_, p_147808_3_, p_147808_4_);
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(983055);

        if (this.getBlockIcon(p_147808_1_).getIconName().equals("grass_top")) {
            flag1 = false;
        } else if (this.hasOverrideBlockTexture()) {
            flag1 = false;
        }

        boolean flag2;
        boolean flag3;
        boolean flag4;
        boolean flag5;
        int i1;
        float f7;

        if (this.renderAllFaces || p_147808_1_.shouldSideBeRendered(this.blockAccess, p_147808_2_, p_147808_3_ - 1, p_147808_4_, 0)) {
            if (this.renderMinY <= 0.0D) {
                --p_147808_3_;
            }

            this.aoBrightnessXYNN = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_ - 1, p_147808_3_, p_147808_4_);
            this.aoBrightnessYZNN = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_, p_147808_3_, p_147808_4_ - 1);
            this.aoBrightnessYZNP = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_, p_147808_3_, p_147808_4_ + 1);
            this.aoBrightnessXYPN = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_ + 1, p_147808_3_, p_147808_4_);
            this.aoLightValueScratchXYNN = this.blockAccess.getBlock(p_147808_2_ - 1, p_147808_3_, p_147808_4_).getAmbientOcclusionLightValue();
            this.aoLightValueScratchYZNN = this.blockAccess.getBlock(p_147808_2_, p_147808_3_, p_147808_4_ - 1).getAmbientOcclusionLightValue();
            this.aoLightValueScratchYZNP = this.blockAccess.getBlock(p_147808_2_, p_147808_3_, p_147808_4_ + 1).getAmbientOcclusionLightValue();
            this.aoLightValueScratchXYPN = this.blockAccess.getBlock(p_147808_2_ + 1, p_147808_3_, p_147808_4_).getAmbientOcclusionLightValue();
            flag2 = this.blockAccess.getBlock(p_147808_2_ + 1, p_147808_3_ - 1, p_147808_4_).getCanBlockGrass();
            flag3 = this.blockAccess.getBlock(p_147808_2_ - 1, p_147808_3_ - 1, p_147808_4_).getCanBlockGrass();
            flag4 = this.blockAccess.getBlock(p_147808_2_, p_147808_3_ - 1, p_147808_4_ + 1).getCanBlockGrass();
            flag5 = this.blockAccess.getBlock(p_147808_2_, p_147808_3_ - 1, p_147808_4_ - 1).getCanBlockGrass();

            if (!flag5 && !flag3) {
                this.aoLightValueScratchXYZNNN = this.aoLightValueScratchXYNN;
                this.aoBrightnessXYZNNN = this.aoBrightnessXYNN;
            } else {
                this.aoLightValueScratchXYZNNN = this.blockAccess.getBlock(p_147808_2_ - 1, p_147808_3_, p_147808_4_ - 1).getAmbientOcclusionLightValue();
                this.aoBrightnessXYZNNN = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_ - 1, p_147808_3_, p_147808_4_ - 1);
            }

            if (!flag4 && !flag3) {
                this.aoLightValueScratchXYZNNP = this.aoLightValueScratchXYNN;
                this.aoBrightnessXYZNNP = this.aoBrightnessXYNN;
            } else {
                this.aoLightValueScratchXYZNNP = this.blockAccess.getBlock(p_147808_2_ - 1, p_147808_3_, p_147808_4_ + 1).getAmbientOcclusionLightValue();
                this.aoBrightnessXYZNNP = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_ - 1, p_147808_3_, p_147808_4_ + 1);
            }

            if (!flag5 && !flag2) {
                this.aoLightValueScratchXYZPNN = this.aoLightValueScratchXYPN;
                this.aoBrightnessXYZPNN = this.aoBrightnessXYPN;
            } else {
                this.aoLightValueScratchXYZPNN = this.blockAccess.getBlock(p_147808_2_ + 1, p_147808_3_, p_147808_4_ - 1).getAmbientOcclusionLightValue();
                this.aoBrightnessXYZPNN = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_ + 1, p_147808_3_, p_147808_4_ - 1);
            }

            if (!flag4 && !flag2) {
                this.aoLightValueScratchXYZPNP = this.aoLightValueScratchXYPN;
                this.aoBrightnessXYZPNP = this.aoBrightnessXYPN;
            } else {
                this.aoLightValueScratchXYZPNP = this.blockAccess.getBlock(p_147808_2_ + 1, p_147808_3_, p_147808_4_ + 1).getAmbientOcclusionLightValue();
                this.aoBrightnessXYZPNP = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_ + 1, p_147808_3_, p_147808_4_ + 1);
            }

            if (this.renderMinY <= 0.0D) {
                ++p_147808_3_;
            }

            i1 = l;

            if (this.renderMinY <= 0.0D || !this.blockAccess.getBlock(p_147808_2_, p_147808_3_ - 1, p_147808_4_).isOpaqueCube()) {
                i1 = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_, p_147808_3_ - 1, p_147808_4_);
            }

            f7 = this.blockAccess.getBlock(p_147808_2_, p_147808_3_ - 1, p_147808_4_).getAmbientOcclusionLightValue();
            f3 = (this.aoLightValueScratchXYZNNP + this.aoLightValueScratchXYNN + this.aoLightValueScratchYZNP + f7) / 4.0F;
            f6 = (this.aoLightValueScratchYZNP + f7 + this.aoLightValueScratchXYZPNP + this.aoLightValueScratchXYPN) / 4.0F;
            f5 = (f7 + this.aoLightValueScratchYZNN + this.aoLightValueScratchXYPN + this.aoLightValueScratchXYZPNN) / 4.0F;
            f4 = (this.aoLightValueScratchXYNN + this.aoLightValueScratchXYZNNN + f7 + this.aoLightValueScratchYZNN) / 4.0F;
            this.brightnessTopLeft = this.getAoBrightness(this.aoBrightnessXYZNNP, this.aoBrightnessXYNN, this.aoBrightnessYZNP, i1);
            this.brightnessTopRight = this.getAoBrightness(this.aoBrightnessYZNP, this.aoBrightnessXYZPNP, this.aoBrightnessXYPN, i1);
            this.brightnessBottomRight = this.getAoBrightness(this.aoBrightnessYZNN, this.aoBrightnessXYPN, this.aoBrightnessXYZPNN, i1);
            this.brightnessBottomLeft = this.getAoBrightness(this.aoBrightnessXYNN, this.aoBrightnessXYZNNN, this.aoBrightnessYZNN, i1);

            if (flag1) {
                this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = p_147808_5_ * 0.5F;
                this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = p_147808_6_ * 0.5F;
                this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = p_147808_7_ * 0.5F;
            } else {
                this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = 0.5F;
                this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = 0.5F;
                this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = 0.5F;
            }

            this.colorRedTopLeft *= f3;
            this.colorGreenTopLeft *= f3;
            this.colorBlueTopLeft *= f3;
            this.colorRedBottomLeft *= f4;
            this.colorGreenBottomLeft *= f4;
            this.colorBlueBottomLeft *= f4;
            this.colorRedBottomRight *= f5;
            this.colorGreenBottomRight *= f5;
            this.colorBlueBottomRight *= f5;
            this.colorRedTopRight *= f6;
            this.colorGreenTopRight *= f6;
            this.colorBlueTopRight *= f6;
            this.renderFaceYNeg(p_147808_1_, (double) p_147808_2_, (double) p_147808_3_, (double) p_147808_4_, this.getBlockIcon(p_147808_1_, this.blockAccess, p_147808_2_, p_147808_3_, p_147808_4_, 0));
            flag = true;
        }

        if (this.renderAllFaces || p_147808_1_.shouldSideBeRendered(this.blockAccess, p_147808_2_, p_147808_3_ + 1, p_147808_4_, 1)) {
            if (this.renderMaxY >= 1.0D) {
                ++p_147808_3_;
            }

            int up = 0;
            if (d.ordinal() == ForgeDirection.UP.ordinal()) up = 1;

            this.aoBrightnessXYNP = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_ - 1, p_147808_3_ + up, p_147808_4_);
            this.aoBrightnessXYPP = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_ + 1, p_147808_3_ + up, p_147808_4_);
            this.aoBrightnessYZPN = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_, p_147808_3_ + up, p_147808_4_ - 1);
            this.aoBrightnessYZPP = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_, p_147808_3_ + up, p_147808_4_ + 1);
            this.aoLightValueScratchXYNP = this.blockAccess.getBlock(p_147808_2_ - 1, p_147808_3_ + up, p_147808_4_).getAmbientOcclusionLightValue();
            this.aoLightValueScratchXYPP = this.blockAccess.getBlock(p_147808_2_ + 1, p_147808_3_ + up, p_147808_4_).getAmbientOcclusionLightValue();
            this.aoLightValueScratchYZPN = this.blockAccess.getBlock(p_147808_2_, p_147808_3_ + up, p_147808_4_ - 1).getAmbientOcclusionLightValue();
            this.aoLightValueScratchYZPP = this.blockAccess.getBlock(p_147808_2_, p_147808_3_ + up, p_147808_4_ + 1).getAmbientOcclusionLightValue();
            flag2 = this.blockAccess.getBlock(p_147808_2_ + 1, p_147808_3_ + 1, p_147808_4_).getCanBlockGrass();
            flag3 = this.blockAccess.getBlock(p_147808_2_ - 1, p_147808_3_ + 1, p_147808_4_).getCanBlockGrass();
            flag4 = this.blockAccess.getBlock(p_147808_2_, p_147808_3_ + 1, p_147808_4_ + 1).getCanBlockGrass();
            flag5 = this.blockAccess.getBlock(p_147808_2_, p_147808_3_ + 1, p_147808_4_ - 1).getCanBlockGrass();

            if (!flag5 && !flag3) {
                this.aoLightValueScratchXYZNPN = this.aoLightValueScratchXYNP;
                this.aoBrightnessXYZNPN = this.aoBrightnessXYNP;
            } else {
                this.aoLightValueScratchXYZNPN = this.blockAccess.getBlock(p_147808_2_ - 1, p_147808_3_ + up, p_147808_4_ - 1).getAmbientOcclusionLightValue();
                this.aoBrightnessXYZNPN = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_ - 1, p_147808_3_ + up, p_147808_4_ - 1);
            }

            if (!flag5 && !flag2) {
                this.aoLightValueScratchXYZPPN = this.aoLightValueScratchXYPP;
                this.aoBrightnessXYZPPN = this.aoBrightnessXYPP;
            } else {
                this.aoLightValueScratchXYZPPN = this.blockAccess.getBlock(p_147808_2_ + 1, p_147808_3_ + up, p_147808_4_ - 1).getAmbientOcclusionLightValue();
                this.aoBrightnessXYZPPN = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_ + 1, p_147808_3_ + up, p_147808_4_ - 1);
            }

            if (!flag4 && !flag3) {
                this.aoLightValueScratchXYZNPP = this.aoLightValueScratchXYNP;
                this.aoBrightnessXYZNPP = this.aoBrightnessXYNP;
            } else {
                this.aoLightValueScratchXYZNPP = this.blockAccess.getBlock(p_147808_2_ - 1, p_147808_3_ + up, p_147808_4_ + 1).getAmbientOcclusionLightValue();
                this.aoBrightnessXYZNPP = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_ - 1, p_147808_3_ + up, p_147808_4_ + 1);
            }

            if (!flag4 && !flag2) {
                this.aoLightValueScratchXYZPPP = this.aoLightValueScratchXYPP;
                this.aoBrightnessXYZPPP = this.aoBrightnessXYPP;
            } else {
                this.aoLightValueScratchXYZPPP = this.blockAccess.getBlock(p_147808_2_ + 1, p_147808_3_ + up, p_147808_4_ + 1).getAmbientOcclusionLightValue();
                this.aoBrightnessXYZPPP = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_ + 1, p_147808_3_ + up, p_147808_4_ + 1);
            }

            if (this.renderMaxY >= 1.0D) {
                --p_147808_3_;
            }

            i1 = l;

            if (this.renderMaxY >= 1.0D || !this.blockAccess.getBlock(p_147808_2_, p_147808_3_ + 1, p_147808_4_).isOpaqueCube()) {
                i1 = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_, p_147808_3_ + 1, p_147808_4_);
            }
            if (d.ordinal() == ForgeDirection.UP.ordinal()) {
                //i1 = (int) (p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_, p_147808_3_ + 1, p_147808_4_)/1.4);
                //System.out.println("AAA : " + this.aoBrightnessXYZNPP);
                //this.aoBrightnessXYZNPN = i1;
                //this.aoBrightnessXYZPPN = i1;
                //this.aoBrightnessXYZNPP = i1;
                //this.aoBrightnessXYZPPP = i1;
            }

            f7 = this.blockAccess.getBlock(p_147808_2_, p_147808_3_ + 1, p_147808_4_).getAmbientOcclusionLightValue();
            f6 = (this.aoLightValueScratchXYZNPP + this.aoLightValueScratchXYNP + this.aoLightValueScratchYZPP + f7) / 4.0F;
            f3 = (this.aoLightValueScratchYZPP + f7 + this.aoLightValueScratchXYZPPP + this.aoLightValueScratchXYPP) / 4.0F;
            f4 = (f7 + this.aoLightValueScratchYZPN + this.aoLightValueScratchXYPP + this.aoLightValueScratchXYZPPN) / 4.0F;
            f5 = (this.aoLightValueScratchXYNP + this.aoLightValueScratchXYZNPN + f7 + this.aoLightValueScratchYZPN) / 4.0F;
            this.brightnessTopRight = this.getAoBrightness(this.aoBrightnessXYZNPP, this.aoBrightnessXYNP, this.aoBrightnessYZPP, i1);
            this.brightnessTopLeft = this.getAoBrightness(this.aoBrightnessYZPP, this.aoBrightnessXYZPPP, this.aoBrightnessXYPP, i1);
            this.brightnessBottomLeft = this.getAoBrightness(this.aoBrightnessYZPN, this.aoBrightnessXYPP, this.aoBrightnessXYZPPN, i1);
            this.brightnessBottomRight = this.getAoBrightness(this.aoBrightnessXYNP, this.aoBrightnessXYZNPN, this.aoBrightnessYZPN, i1);
            this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = p_147808_5_;
            this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = p_147808_6_;
            this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = p_147808_7_;
            this.colorRedTopLeft *= f3;
            this.colorGreenTopLeft *= f3;
            this.colorBlueTopLeft *= f3;
            this.colorRedBottomLeft *= f4;
            this.colorGreenBottomLeft *= f4;
            this.colorBlueBottomLeft *= f4;
            this.colorRedBottomRight *= f5;
            this.colorGreenBottomRight *= f5;
            this.colorBlueBottomRight *= f5;
            this.colorRedTopRight *= f6;
            this.colorGreenTopRight *= f6;
            this.colorBlueTopRight *= f6;
            this.renderFaceYPos(p_147808_1_, (double) p_147808_2_, (double) p_147808_3_, (double) p_147808_4_, this.getBlockIcon(p_147808_1_, this.blockAccess, p_147808_2_, p_147808_3_, p_147808_4_, 1));
            flag = true;
        }

        float f8;
        float f9;
        float f10;
        float f11;
        int j1;
        int k1;
        int l1;
        int i2;
        IIcon iicon;

        if (this.renderAllFaces || p_147808_1_.shouldSideBeRendered(this.blockAccess, p_147808_2_, p_147808_3_, p_147808_4_ - 1, 2)) {
            if (this.renderMinZ <= 0.0D) {
                --p_147808_4_;
            }

            this.aoLightValueScratchXZNN = this.blockAccess.getBlock(p_147808_2_ - 1, p_147808_3_, p_147808_4_).getAmbientOcclusionLightValue();
            this.aoLightValueScratchYZNN = this.blockAccess.getBlock(p_147808_2_, p_147808_3_ - 1, p_147808_4_).getAmbientOcclusionLightValue();
            this.aoLightValueScratchYZPN = this.blockAccess.getBlock(p_147808_2_, p_147808_3_ + 1, p_147808_4_).getAmbientOcclusionLightValue();
            this.aoLightValueScratchXZPN = this.blockAccess.getBlock(p_147808_2_ + 1, p_147808_3_, p_147808_4_).getAmbientOcclusionLightValue();
            this.aoBrightnessXZNN = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_ - 1, p_147808_3_, p_147808_4_);
            this.aoBrightnessYZNN = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_, p_147808_3_ - 1, p_147808_4_);
            this.aoBrightnessYZPN = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_, p_147808_3_ + 1, p_147808_4_);
            this.aoBrightnessXZPN = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_ + 1, p_147808_3_, p_147808_4_);
            flag2 = this.blockAccess.getBlock(p_147808_2_ + 1, p_147808_3_, p_147808_4_ - 1).getCanBlockGrass();
            flag3 = this.blockAccess.getBlock(p_147808_2_ - 1, p_147808_3_, p_147808_4_ - 1).getCanBlockGrass();
            flag4 = this.blockAccess.getBlock(p_147808_2_, p_147808_3_ + 1, p_147808_4_ - 1).getCanBlockGrass();
            flag5 = this.blockAccess.getBlock(p_147808_2_, p_147808_3_ - 1, p_147808_4_ - 1).getCanBlockGrass();

            if (!flag3 && !flag5) {
                this.aoLightValueScratchXYZNNN = this.aoLightValueScratchXZNN;
                this.aoBrightnessXYZNNN = this.aoBrightnessXZNN;
            } else {
                this.aoLightValueScratchXYZNNN = this.blockAccess.getBlock(p_147808_2_ - 1, p_147808_3_ - 1, p_147808_4_).getAmbientOcclusionLightValue();
                this.aoBrightnessXYZNNN = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_ - 1, p_147808_3_ - 1, p_147808_4_);
            }

            if (!flag3 && !flag4) {
                this.aoLightValueScratchXYZNPN = this.aoLightValueScratchXZNN;
                this.aoBrightnessXYZNPN = this.aoBrightnessXZNN;
            } else {
                this.aoLightValueScratchXYZNPN = this.blockAccess.getBlock(p_147808_2_ - 1, p_147808_3_ + 1, p_147808_4_).getAmbientOcclusionLightValue();
                this.aoBrightnessXYZNPN = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_ - 1, p_147808_3_ + 1, p_147808_4_);
            }

            if (!flag2 && !flag5) {
                this.aoLightValueScratchXYZPNN = this.aoLightValueScratchXZPN;
                this.aoBrightnessXYZPNN = this.aoBrightnessXZPN;
            } else {
                this.aoLightValueScratchXYZPNN = this.blockAccess.getBlock(p_147808_2_ + 1, p_147808_3_ - 1, p_147808_4_).getAmbientOcclusionLightValue();
                this.aoBrightnessXYZPNN = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_ + 1, p_147808_3_ - 1, p_147808_4_);
            }

            if (!flag2 && !flag4) {
                this.aoLightValueScratchXYZPPN = this.aoLightValueScratchXZPN;
                this.aoBrightnessXYZPPN = this.aoBrightnessXZPN;
            } else {
                this.aoLightValueScratchXYZPPN = this.blockAccess.getBlock(p_147808_2_ + 1, p_147808_3_ + 1, p_147808_4_).getAmbientOcclusionLightValue();
                this.aoBrightnessXYZPPN = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_ + 1, p_147808_3_ + 1, p_147808_4_);
            }

            if (this.renderMinZ <= 0.0D) {
                ++p_147808_4_;
            }

            i1 = l;

            if (this.renderMinZ <= 0.0D || !this.blockAccess.getBlock(p_147808_2_, p_147808_3_, p_147808_4_ - 1).isOpaqueCube()) {
                i1 = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_, p_147808_3_, p_147808_4_ - 1);
                //i1 = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_, p_147808_3_ + 1, p_147808_4_);

            }
            if (d.ordinal() != ForgeDirection.NORTH.ordinal()) {
                i1 = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_, p_147808_3_ + 1, p_147808_4_);
            }

            f7 = this.blockAccess.getBlock(p_147808_2_, p_147808_3_, p_147808_4_ - 1).getAmbientOcclusionLightValue();
            f7 = this.blockAccess.getBlock(p_147808_2_, p_147808_3_ + 1, p_147808_4_).getAmbientOcclusionLightValue();
            f8 = (this.aoLightValueScratchXZNN + this.aoLightValueScratchXYZNPN + f7 + this.aoLightValueScratchYZPN) / 4.0F;
            f9 = (f7 + this.aoLightValueScratchYZPN + this.aoLightValueScratchXZPN + this.aoLightValueScratchXYZPPN) / 4.0F;
            f10 = (this.aoLightValueScratchYZNN + f7 + this.aoLightValueScratchXYZPNN + this.aoLightValueScratchXZPN) / 4.0F;
            f11 = (this.aoLightValueScratchXYZNNN + this.aoLightValueScratchXZNN + this.aoLightValueScratchYZNN + f7) / 4.0F;
            f3 = (float) ((double) f8 * this.renderMaxY * (1.0D - this.renderMinX) + (double) f9 * this.renderMaxY * this.renderMinX + (double) f10 * (1.0D - this.renderMaxY) * this.renderMinX
                    + (double) f11 * (1.0D - this.renderMaxY) * (1.0D - this.renderMinX));
            f4 = (float) ((double) f8 * this.renderMaxY * (1.0D - this.renderMaxX) + (double) f9 * this.renderMaxY * this.renderMaxX + (double) f10 * (1.0D - this.renderMaxY) * this.renderMaxX
                    + (double) f11 * (1.0D - this.renderMaxY) * (1.0D - this.renderMaxX));
            f5 = (float) ((double) f8 * this.renderMinY * (1.0D - this.renderMaxX) + (double) f9 * this.renderMinY * this.renderMaxX + (double) f10 * (1.0D - this.renderMinY) * this.renderMaxX
                    + (double) f11 * (1.0D - this.renderMinY) * (1.0D - this.renderMaxX));
            f6 = (float) ((double) f8 * this.renderMinY * (1.0D - this.renderMinX) + (double) f9 * this.renderMinY * this.renderMinX + (double) f10 * (1.0D - this.renderMinY) * this.renderMinX
                    + (double) f11 * (1.0D - this.renderMinY) * (1.0D - this.renderMinX));
            j1 = this.getAoBrightness(this.aoBrightnessXZNN, this.aoBrightnessXYZNPN, this.aoBrightnessYZPN, i1);
            k1 = this.getAoBrightness(this.aoBrightnessYZPN, this.aoBrightnessXZPN, this.aoBrightnessXYZPPN, i1);
            l1 = this.getAoBrightness(this.aoBrightnessYZNN, this.aoBrightnessXYZPNN, this.aoBrightnessXZPN, i1);
            i2 = this.getAoBrightness(this.aoBrightnessXYZNNN, this.aoBrightnessXZNN, this.aoBrightnessYZNN, i1);
            this.brightnessTopLeft = this.mixAoBrightness(j1, k1, l1, i2, this.renderMaxY * (1.0D - this.renderMinX), this.renderMaxY * this.renderMinX, (1.0D - this.renderMaxY) * this.renderMinX,
                    (1.0D - this.renderMaxY) * (1.0D - this.renderMinX));
            this.brightnessBottomLeft = this.mixAoBrightness(j1, k1, l1, i2, this.renderMaxY * (1.0D - this.renderMaxX), this.renderMaxY * this.renderMaxX, (1.0D - this.renderMaxY) * this.renderMaxX,
                    (1.0D - this.renderMaxY) * (1.0D - this.renderMaxX));
            this.brightnessBottomRight = this.mixAoBrightness(j1, k1, l1, i2, this.renderMinY * (1.0D - this.renderMaxX), this.renderMinY * this.renderMaxX, (1.0D - this.renderMinY) * this.renderMaxX,
                    (1.0D - this.renderMinY) * (1.0D - this.renderMaxX));
            this.brightnessTopRight = this.mixAoBrightness(j1, k1, l1, i2, this.renderMinY * (1.0D - this.renderMinX), this.renderMinY * this.renderMinX, (1.0D - this.renderMinY) * this.renderMinX,
                    (1.0D - this.renderMinY) * (1.0D - this.renderMinX));

            if (flag1) {
                this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = p_147808_5_ * 0.8F;
                this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = p_147808_6_ * 0.8F;
                this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = p_147808_7_ * 0.8F;
            } else {
                this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = 0.8F;
                this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = 0.8F;
                this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = 0.8F;
            }

            this.colorRedTopLeft *= f3;
            this.colorGreenTopLeft *= f3;
            this.colorBlueTopLeft *= f3;
            this.colorRedBottomLeft *= f4;
            this.colorGreenBottomLeft *= f4;
            this.colorBlueBottomLeft *= f4;
            this.colorRedBottomRight *= f5;
            this.colorGreenBottomRight *= f5;
            this.colorBlueBottomRight *= f5;
            this.colorRedTopRight *= f6;
            this.colorGreenTopRight *= f6;
            this.colorBlueTopRight *= f6;
            iicon = this.getBlockIcon(p_147808_1_, this.blockAccess, p_147808_2_, p_147808_3_, p_147808_4_, 2);
            this.renderFaceZNeg(p_147808_1_, (double) p_147808_2_, (double) p_147808_3_, (double) p_147808_4_, iicon);

            if (fancyGrass && iicon.getIconName().equals("grass_side") && !this.hasOverrideBlockTexture()) {
                this.colorRedTopLeft *= p_147808_5_;
                this.colorRedBottomLeft *= p_147808_5_;
                this.colorRedBottomRight *= p_147808_5_;
                this.colorRedTopRight *= p_147808_5_;
                this.colorGreenTopLeft *= p_147808_6_;
                this.colorGreenBottomLeft *= p_147808_6_;
                this.colorGreenBottomRight *= p_147808_6_;
                this.colorGreenTopRight *= p_147808_6_;
                this.colorBlueTopLeft *= p_147808_7_;
                this.colorBlueBottomLeft *= p_147808_7_;
                this.colorBlueBottomRight *= p_147808_7_;
                this.colorBlueTopRight *= p_147808_7_;
                this.renderFaceZNeg(p_147808_1_, (double) p_147808_2_, (double) p_147808_3_, (double) p_147808_4_, BlockGrass.getIconSideOverlay());
            }

            flag = true;
        }

        if (this.renderAllFaces || p_147808_1_.shouldSideBeRendered(this.blockAccess, p_147808_2_, p_147808_3_, p_147808_4_ + 1, 3)) {
            if (this.renderMaxZ >= 1.0D) {
                ++p_147808_4_;
            }

            this.aoLightValueScratchXZNP = this.blockAccess.getBlock(p_147808_2_ - 1, p_147808_3_, p_147808_4_).getAmbientOcclusionLightValue();
            this.aoLightValueScratchXZPP = this.blockAccess.getBlock(p_147808_2_ + 1, p_147808_3_, p_147808_4_).getAmbientOcclusionLightValue();
            this.aoLightValueScratchYZNP = this.blockAccess.getBlock(p_147808_2_, p_147808_3_ - 1, p_147808_4_).getAmbientOcclusionLightValue();
            this.aoLightValueScratchYZPP = this.blockAccess.getBlock(p_147808_2_, p_147808_3_ + 1, p_147808_4_).getAmbientOcclusionLightValue();
            this.aoBrightnessXZNP = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_ - 1, p_147808_3_, p_147808_4_);
            this.aoBrightnessXZPP = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_ + 1, p_147808_3_, p_147808_4_);
            this.aoBrightnessYZNP = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_, p_147808_3_ - 1, p_147808_4_);
            this.aoBrightnessYZPP = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_, p_147808_3_ + 1, p_147808_4_);
            flag2 = this.blockAccess.getBlock(p_147808_2_ + 1, p_147808_3_, p_147808_4_ + 1).getCanBlockGrass();
            flag3 = this.blockAccess.getBlock(p_147808_2_ - 1, p_147808_3_, p_147808_4_ + 1).getCanBlockGrass();
            flag4 = this.blockAccess.getBlock(p_147808_2_, p_147808_3_ + 1, p_147808_4_ + 1).getCanBlockGrass();
            flag5 = this.blockAccess.getBlock(p_147808_2_, p_147808_3_ - 1, p_147808_4_ + 1).getCanBlockGrass();

            if (!flag3 && !flag5) {
                this.aoLightValueScratchXYZNNP = this.aoLightValueScratchXZNP;
                this.aoBrightnessXYZNNP = this.aoBrightnessXZNP;
            } else {
                this.aoLightValueScratchXYZNNP = this.blockAccess.getBlock(p_147808_2_ - 1, p_147808_3_ - 1, p_147808_4_).getAmbientOcclusionLightValue();
                this.aoBrightnessXYZNNP = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_ - 1, p_147808_3_ - 1, p_147808_4_);
            }

            if (!flag3 && !flag4) {
                this.aoLightValueScratchXYZNPP = this.aoLightValueScratchXZNP;
                this.aoBrightnessXYZNPP = this.aoBrightnessXZNP;
            } else {
                this.aoLightValueScratchXYZNPP = this.blockAccess.getBlock(p_147808_2_ - 1, p_147808_3_ + 1, p_147808_4_).getAmbientOcclusionLightValue();
                this.aoBrightnessXYZNPP = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_ - 1, p_147808_3_ + 1, p_147808_4_);
            }

            if (!flag2 && !flag5) {
                this.aoLightValueScratchXYZPNP = this.aoLightValueScratchXZPP;
                this.aoBrightnessXYZPNP = this.aoBrightnessXZPP;
            } else {
                this.aoLightValueScratchXYZPNP = this.blockAccess.getBlock(p_147808_2_ + 1, p_147808_3_ - 1, p_147808_4_).getAmbientOcclusionLightValue();
                this.aoBrightnessXYZPNP = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_ + 1, p_147808_3_ - 1, p_147808_4_);
            }

            if (!flag2 && !flag4) {
                this.aoLightValueScratchXYZPPP = this.aoLightValueScratchXZPP;
                this.aoBrightnessXYZPPP = this.aoBrightnessXZPP;
            } else {
                this.aoLightValueScratchXYZPPP = this.blockAccess.getBlock(p_147808_2_ + 1, p_147808_3_ + 1, p_147808_4_).getAmbientOcclusionLightValue();
                this.aoBrightnessXYZPPP = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_ + 1, p_147808_3_ + 1, p_147808_4_);
            }

            if (this.renderMaxZ >= 1.0D) {
                --p_147808_4_;
            }

            i1 = l;

            if (this.renderMaxZ >= 1.0D || !this.blockAccess.getBlock(p_147808_2_, p_147808_3_, p_147808_4_ + 1).isOpaqueCube()) {
                i1 = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_, p_147808_3_, p_147808_4_ + 1);
                //i1 = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_, p_147808_3_ + 1, p_147808_4_);
            }
            if (d.ordinal() != ForgeDirection.SOUTH.ordinal()) {
                i1 = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_, p_147808_3_ + 1, p_147808_4_);
            }

            f7 = this.blockAccess.getBlock(p_147808_2_, p_147808_3_, p_147808_4_ + 1).getAmbientOcclusionLightValue();
            f8 = (this.aoLightValueScratchXZNP + this.aoLightValueScratchXYZNPP + f7 + this.aoLightValueScratchYZPP) / 4.0F;
            f9 = (f7 + this.aoLightValueScratchYZPP + this.aoLightValueScratchXZPP + this.aoLightValueScratchXYZPPP) / 4.0F;
            f10 = (this.aoLightValueScratchYZNP + f7 + this.aoLightValueScratchXYZPNP + this.aoLightValueScratchXZPP) / 4.0F;
            f11 = (this.aoLightValueScratchXYZNNP + this.aoLightValueScratchXZNP + this.aoLightValueScratchYZNP + f7) / 4.0F;
            f3 = (float) ((double) f8 * this.renderMaxY * (1.0D - this.renderMinX) + (double) f9 * this.renderMaxY * this.renderMinX + (double) f10 * (1.0D - this.renderMaxY) * this.renderMinX
                    + (double) f11 * (1.0D - this.renderMaxY) * (1.0D - this.renderMinX));
            f4 = (float) ((double) f8 * this.renderMinY * (1.0D - this.renderMinX) + (double) f9 * this.renderMinY * this.renderMinX + (double) f10 * (1.0D - this.renderMinY) * this.renderMinX
                    + (double) f11 * (1.0D - this.renderMinY) * (1.0D - this.renderMinX));
            f5 = (float) ((double) f8 * this.renderMinY * (1.0D - this.renderMaxX) + (double) f9 * this.renderMinY * this.renderMaxX + (double) f10 * (1.0D - this.renderMinY) * this.renderMaxX
                    + (double) f11 * (1.0D - this.renderMinY) * (1.0D - this.renderMaxX));
            f6 = (float) ((double) f8 * this.renderMaxY * (1.0D - this.renderMaxX) + (double) f9 * this.renderMaxY * this.renderMaxX + (double) f10 * (1.0D - this.renderMaxY) * this.renderMaxX
                    + (double) f11 * (1.0D - this.renderMaxY) * (1.0D - this.renderMaxX));
            j1 = this.getAoBrightness(this.aoBrightnessXZNP, this.aoBrightnessXYZNPP, this.aoBrightnessYZPP, i1);
            k1 = this.getAoBrightness(this.aoBrightnessYZPP, this.aoBrightnessXZPP, this.aoBrightnessXYZPPP, i1);
            l1 = this.getAoBrightness(this.aoBrightnessYZNP, this.aoBrightnessXYZPNP, this.aoBrightnessXZPP, i1);
            i2 = this.getAoBrightness(this.aoBrightnessXYZNNP, this.aoBrightnessXZNP, this.aoBrightnessYZNP, i1);
            this.brightnessTopLeft = this.mixAoBrightness(j1, i2, l1, k1, this.renderMaxY * (1.0D - this.renderMinX), (1.0D - this.renderMaxY) * (1.0D - this.renderMinX), (1.0D - this.renderMaxY) * this.renderMinX,
                    this.renderMaxY * this.renderMinX);
            this.brightnessBottomLeft = this.mixAoBrightness(j1, i2, l1, k1, this.renderMinY * (1.0D - this.renderMinX), (1.0D - this.renderMinY) * (1.0D - this.renderMinX), (1.0D - this.renderMinY) * this.renderMinX,
                    this.renderMinY * this.renderMinX);
            this.brightnessBottomRight = this.mixAoBrightness(j1, i2, l1, k1, this.renderMinY * (1.0D - this.renderMaxX), (1.0D - this.renderMinY) * (1.0D - this.renderMaxX), (1.0D - this.renderMinY) * this.renderMaxX,
                    this.renderMinY * this.renderMaxX);
            this.brightnessTopRight = this.mixAoBrightness(j1, i2, l1, k1, this.renderMaxY * (1.0D - this.renderMaxX), (1.0D - this.renderMaxY) * (1.0D - this.renderMaxX), (1.0D - this.renderMaxY) * this.renderMaxX,
                    this.renderMaxY * this.renderMaxX);

            if (flag1) {
                this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = p_147808_5_ * 0.8F;
                this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = p_147808_6_ * 0.8F;
                this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = p_147808_7_ * 0.8F;
            } else {
                this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = 0.8F;
                this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = 0.8F;
                this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = 0.8F;
            }

            this.colorRedTopLeft *= f3;
            this.colorGreenTopLeft *= f3;
            this.colorBlueTopLeft *= f3;
            this.colorRedBottomLeft *= f4;
            this.colorGreenBottomLeft *= f4;
            this.colorBlueBottomLeft *= f4;
            this.colorRedBottomRight *= f5;
            this.colorGreenBottomRight *= f5;
            this.colorBlueBottomRight *= f5;
            this.colorRedTopRight *= f6;
            this.colorGreenTopRight *= f6;
            this.colorBlueTopRight *= f6;
            iicon = this.getBlockIcon(p_147808_1_, this.blockAccess, p_147808_2_, p_147808_3_, p_147808_4_, 3);
            this.renderFaceZPos(p_147808_1_, (double) p_147808_2_, (double) p_147808_3_, (double) p_147808_4_, iicon);

            if (fancyGrass && iicon.getIconName().equals("grass_side") && !this.hasOverrideBlockTexture()) {
                this.colorRedTopLeft *= p_147808_5_;
                this.colorRedBottomLeft *= p_147808_5_;
                this.colorRedBottomRight *= p_147808_5_;
                this.colorRedTopRight *= p_147808_5_;
                this.colorGreenTopLeft *= p_147808_6_;
                this.colorGreenBottomLeft *= p_147808_6_;
                this.colorGreenBottomRight *= p_147808_6_;
                this.colorGreenTopRight *= p_147808_6_;
                this.colorBlueTopLeft *= p_147808_7_;
                this.colorBlueBottomLeft *= p_147808_7_;
                this.colorBlueBottomRight *= p_147808_7_;
                this.colorBlueTopRight *= p_147808_7_;
                this.renderFaceZPos(p_147808_1_, (double) p_147808_2_, (double) p_147808_3_, (double) p_147808_4_, BlockGrass.getIconSideOverlay());
            }

            flag = true;
        }

        if (this.renderAllFaces || p_147808_1_.shouldSideBeRendered(this.blockAccess, p_147808_2_ - 1, p_147808_3_, p_147808_4_, 4)) {
            if (this.renderMinX <= 0.0D) {
                --p_147808_2_;
            }

            this.aoLightValueScratchXYNN = this.blockAccess.getBlock(p_147808_2_, p_147808_3_ - 1, p_147808_4_).getAmbientOcclusionLightValue();
            this.aoLightValueScratchXZNN = this.blockAccess.getBlock(p_147808_2_, p_147808_3_, p_147808_4_ - 1).getAmbientOcclusionLightValue();
            this.aoLightValueScratchXZNP = this.blockAccess.getBlock(p_147808_2_, p_147808_3_, p_147808_4_ + 1).getAmbientOcclusionLightValue();
            this.aoLightValueScratchXYNP = this.blockAccess.getBlock(p_147808_2_, p_147808_3_ + 1, p_147808_4_).getAmbientOcclusionLightValue();
            this.aoBrightnessXYNN = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_, p_147808_3_ - 1, p_147808_4_);
            this.aoBrightnessXZNN = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_, p_147808_3_, p_147808_4_ - 1);
            this.aoBrightnessXZNP = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_, p_147808_3_, p_147808_4_ + 1);
            this.aoBrightnessXYNP = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_, p_147808_3_ + 1, p_147808_4_);
            flag2 = this.blockAccess.getBlock(p_147808_2_ - 1, p_147808_3_ + 1, p_147808_4_).getCanBlockGrass();
            flag3 = this.blockAccess.getBlock(p_147808_2_ - 1, p_147808_3_ - 1, p_147808_4_).getCanBlockGrass();
            flag4 = this.blockAccess.getBlock(p_147808_2_ - 1, p_147808_3_, p_147808_4_ - 1).getCanBlockGrass();
            flag5 = this.blockAccess.getBlock(p_147808_2_ - 1, p_147808_3_, p_147808_4_ + 1).getCanBlockGrass();

            if (!flag4 && !flag3) {
                this.aoLightValueScratchXYZNNN = this.aoLightValueScratchXZNN;
                this.aoBrightnessXYZNNN = this.aoBrightnessXZNN;
            } else {
                this.aoLightValueScratchXYZNNN = this.blockAccess.getBlock(p_147808_2_, p_147808_3_ - 1, p_147808_4_ - 1).getAmbientOcclusionLightValue();
                this.aoBrightnessXYZNNN = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_, p_147808_3_ - 1, p_147808_4_ - 1);
            }

            if (!flag5 && !flag3) {
                this.aoLightValueScratchXYZNNP = this.aoLightValueScratchXZNP;
                this.aoBrightnessXYZNNP = this.aoBrightnessXZNP;
            } else {
                this.aoLightValueScratchXYZNNP = this.blockAccess.getBlock(p_147808_2_, p_147808_3_ - 1, p_147808_4_ + 1).getAmbientOcclusionLightValue();
                this.aoBrightnessXYZNNP = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_, p_147808_3_ - 1, p_147808_4_ + 1);
            }

            if (!flag4 && !flag2) {
                this.aoLightValueScratchXYZNPN = this.aoLightValueScratchXZNN;
                this.aoBrightnessXYZNPN = this.aoBrightnessXZNN;
            } else {
                this.aoLightValueScratchXYZNPN = this.blockAccess.getBlock(p_147808_2_, p_147808_3_ + 1, p_147808_4_ - 1).getAmbientOcclusionLightValue();
                this.aoBrightnessXYZNPN = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_, p_147808_3_ + 1, p_147808_4_ - 1);
            }

            if (!flag5 && !flag2) {
                this.aoLightValueScratchXYZNPP = this.aoLightValueScratchXZNP;
                this.aoBrightnessXYZNPP = this.aoBrightnessXZNP;
            } else {
                this.aoLightValueScratchXYZNPP = this.blockAccess.getBlock(p_147808_2_, p_147808_3_ + 1, p_147808_4_ + 1).getAmbientOcclusionLightValue();
                this.aoBrightnessXYZNPP = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_, p_147808_3_ + 1, p_147808_4_ + 1);
            }

            if (this.renderMinX <= 0.0D) {
                ++p_147808_2_;
            }

            i1 = l;

            if (this.renderMinX <= 0.0D || !this.blockAccess.getBlock(p_147808_2_ - 1, p_147808_3_, p_147808_4_).isOpaqueCube()) {
                i1 = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_ - 1, p_147808_3_, p_147808_4_);
                //i1 = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_, p_147808_3_ + 1, p_147808_4_);
            }
            if (d.ordinal() != ForgeDirection.WEST.ordinal()) {
                i1 = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_, p_147808_3_ + 1, p_147808_4_);
            }

            f7 = this.blockAccess.getBlock(p_147808_2_ - 1, p_147808_3_, p_147808_4_).getAmbientOcclusionLightValue();
            f8 = (this.aoLightValueScratchXYNN + this.aoLightValueScratchXYZNNP + f7 + this.aoLightValueScratchXZNP) / 4.0F;
            f9 = (f7 + this.aoLightValueScratchXZNP + this.aoLightValueScratchXYNP + this.aoLightValueScratchXYZNPP) / 4.0F;
            f10 = (this.aoLightValueScratchXZNN + f7 + this.aoLightValueScratchXYZNPN + this.aoLightValueScratchXYNP) / 4.0F;
            f11 = (this.aoLightValueScratchXYZNNN + this.aoLightValueScratchXYNN + this.aoLightValueScratchXZNN + f7) / 4.0F;
            f3 = (float) ((double) f9 * this.renderMaxY * this.renderMaxZ + (double) f10 * this.renderMaxY * (1.0D - this.renderMaxZ) + (double) f11 * (1.0D - this.renderMaxY) * (1.0D - this.renderMaxZ)
                    + (double) f8 * (1.0D - this.renderMaxY) * this.renderMaxZ);
            f4 = (float) ((double) f9 * this.renderMaxY * this.renderMinZ + (double) f10 * this.renderMaxY * (1.0D - this.renderMinZ) + (double) f11 * (1.0D - this.renderMaxY) * (1.0D - this.renderMinZ)
                    + (double) f8 * (1.0D - this.renderMaxY) * this.renderMinZ);
            f5 = (float) ((double) f9 * this.renderMinY * this.renderMinZ + (double) f10 * this.renderMinY * (1.0D - this.renderMinZ) + (double) f11 * (1.0D - this.renderMinY) * (1.0D - this.renderMinZ)
                    + (double) f8 * (1.0D - this.renderMinY) * this.renderMinZ);
            f6 = (float) ((double) f9 * this.renderMinY * this.renderMaxZ + (double) f10 * this.renderMinY * (1.0D - this.renderMaxZ) + (double) f11 * (1.0D - this.renderMinY) * (1.0D - this.renderMaxZ)
                    + (double) f8 * (1.0D - this.renderMinY) * this.renderMaxZ);
            j1 = this.getAoBrightness(this.aoBrightnessXYNN, this.aoBrightnessXYZNNP, this.aoBrightnessXZNP, i1);
            k1 = this.getAoBrightness(this.aoBrightnessXZNP, this.aoBrightnessXYNP, this.aoBrightnessXYZNPP, i1);
            l1 = this.getAoBrightness(this.aoBrightnessXZNN, this.aoBrightnessXYZNPN, this.aoBrightnessXYNP, i1);
            i2 = this.getAoBrightness(this.aoBrightnessXYZNNN, this.aoBrightnessXYNN, this.aoBrightnessXZNN, i1);
            this.brightnessTopLeft = this.mixAoBrightness(k1, l1, i2, j1, this.renderMaxY * this.renderMaxZ, this.renderMaxY * (1.0D - this.renderMaxZ), (1.0D - this.renderMaxY) * (1.0D - this.renderMaxZ),
                    (1.0D - this.renderMaxY) * this.renderMaxZ);
            this.brightnessBottomLeft = this.mixAoBrightness(k1, l1, i2, j1, this.renderMaxY * this.renderMinZ, this.renderMaxY * (1.0D - this.renderMinZ), (1.0D - this.renderMaxY) * (1.0D - this.renderMinZ),
                    (1.0D - this.renderMaxY) * this.renderMinZ);
            this.brightnessBottomRight = this.mixAoBrightness(k1, l1, i2, j1, this.renderMinY * this.renderMinZ, this.renderMinY * (1.0D - this.renderMinZ), (1.0D - this.renderMinY) * (1.0D - this.renderMinZ),
                    (1.0D - this.renderMinY) * this.renderMinZ);
            this.brightnessTopRight = this.mixAoBrightness(k1, l1, i2, j1, this.renderMinY * this.renderMaxZ, this.renderMinY * (1.0D - this.renderMaxZ), (1.0D - this.renderMinY) * (1.0D - this.renderMaxZ),
                    (1.0D - this.renderMinY) * this.renderMaxZ);

            if (flag1) {
                this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = p_147808_5_ * 0.6F;
                this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = p_147808_6_ * 0.6F;
                this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = p_147808_7_ * 0.6F;
            } else {
                this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = 0.6F;
                this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = 0.6F;
                this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = 0.6F;
            }

            this.colorRedTopLeft *= f3;
            this.colorGreenTopLeft *= f3;
            this.colorBlueTopLeft *= f3;
            this.colorRedBottomLeft *= f4;
            this.colorGreenBottomLeft *= f4;
            this.colorBlueBottomLeft *= f4;
            this.colorRedBottomRight *= f5;
            this.colorGreenBottomRight *= f5;
            this.colorBlueBottomRight *= f5;
            this.colorRedTopRight *= f6;
            this.colorGreenTopRight *= f6;
            this.colorBlueTopRight *= f6;
            iicon = this.getBlockIcon(p_147808_1_, this.blockAccess, p_147808_2_, p_147808_3_, p_147808_4_, 4);
            this.renderFaceXNeg(p_147808_1_, (double) p_147808_2_, (double) p_147808_3_, (double) p_147808_4_, iicon);

            if (fancyGrass && iicon.getIconName().equals("grass_side") && !this.hasOverrideBlockTexture()) {
                this.colorRedTopLeft *= p_147808_5_;
                this.colorRedBottomLeft *= p_147808_5_;
                this.colorRedBottomRight *= p_147808_5_;
                this.colorRedTopRight *= p_147808_5_;
                this.colorGreenTopLeft *= p_147808_6_;
                this.colorGreenBottomLeft *= p_147808_6_;
                this.colorGreenBottomRight *= p_147808_6_;
                this.colorGreenTopRight *= p_147808_6_;
                this.colorBlueTopLeft *= p_147808_7_;
                this.colorBlueBottomLeft *= p_147808_7_;
                this.colorBlueBottomRight *= p_147808_7_;
                this.colorBlueTopRight *= p_147808_7_;
                this.renderFaceXNeg(p_147808_1_, (double) p_147808_2_, (double) p_147808_3_, (double) p_147808_4_, BlockGrass.getIconSideOverlay());
            }

            flag = true;
        }

        if (this.renderAllFaces || p_147808_1_.shouldSideBeRendered(this.blockAccess, p_147808_2_ + 1, p_147808_3_, p_147808_4_, 5)) {
            if (this.renderMaxX >= 1.0D) {
                ++p_147808_2_;
            }

            this.aoLightValueScratchXYPN = this.blockAccess.getBlock(p_147808_2_, p_147808_3_ - 1, p_147808_4_).getAmbientOcclusionLightValue();
            this.aoLightValueScratchXZPN = this.blockAccess.getBlock(p_147808_2_, p_147808_3_, p_147808_4_ - 1).getAmbientOcclusionLightValue();
            this.aoLightValueScratchXZPP = this.blockAccess.getBlock(p_147808_2_, p_147808_3_, p_147808_4_ + 1).getAmbientOcclusionLightValue();
            this.aoLightValueScratchXYPP = this.blockAccess.getBlock(p_147808_2_, p_147808_3_ + 1, p_147808_4_).getAmbientOcclusionLightValue();
            this.aoBrightnessXYPN = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_, p_147808_3_ - 1, p_147808_4_);
            this.aoBrightnessXZPN = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_, p_147808_3_, p_147808_4_ - 1);
            this.aoBrightnessXZPP = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_, p_147808_3_, p_147808_4_ + 1);
            this.aoBrightnessXYPP = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_, p_147808_3_ + 1, p_147808_4_);
            flag2 = this.blockAccess.getBlock(p_147808_2_ + 1, p_147808_3_ + 1, p_147808_4_).getCanBlockGrass();
            flag3 = this.blockAccess.getBlock(p_147808_2_ + 1, p_147808_3_ - 1, p_147808_4_).getCanBlockGrass();
            flag4 = this.blockAccess.getBlock(p_147808_2_ + 1, p_147808_3_, p_147808_4_ + 1).getCanBlockGrass();
            flag5 = this.blockAccess.getBlock(p_147808_2_ + 1, p_147808_3_, p_147808_4_ - 1).getCanBlockGrass();

            if (!flag3 && !flag5) {
                this.aoLightValueScratchXYZPNN = this.aoLightValueScratchXZPN;
                this.aoBrightnessXYZPNN = this.aoBrightnessXZPN;
            } else {
                this.aoLightValueScratchXYZPNN = this.blockAccess.getBlock(p_147808_2_, p_147808_3_ - 1, p_147808_4_ - 1).getAmbientOcclusionLightValue();
                this.aoBrightnessXYZPNN = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_, p_147808_3_ - 1, p_147808_4_ - 1);
            }

            if (!flag3 && !flag4) {
                this.aoLightValueScratchXYZPNP = this.aoLightValueScratchXZPP;
                this.aoBrightnessXYZPNP = this.aoBrightnessXZPP;
            } else {
                this.aoLightValueScratchXYZPNP = this.blockAccess.getBlock(p_147808_2_, p_147808_3_ - 1, p_147808_4_ + 1).getAmbientOcclusionLightValue();
                this.aoBrightnessXYZPNP = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_, p_147808_3_ - 1, p_147808_4_ + 1);
            }

            if (!flag2 && !flag5) {
                this.aoLightValueScratchXYZPPN = this.aoLightValueScratchXZPN;
                this.aoBrightnessXYZPPN = this.aoBrightnessXZPN;
            } else {
                this.aoLightValueScratchXYZPPN = this.blockAccess.getBlock(p_147808_2_, p_147808_3_ + 1, p_147808_4_ - 1).getAmbientOcclusionLightValue();
                this.aoBrightnessXYZPPN = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_, p_147808_3_ + 1, p_147808_4_ - 1);
            }

            if (!flag2 && !flag4) {
                this.aoLightValueScratchXYZPPP = this.aoLightValueScratchXZPP;
                this.aoBrightnessXYZPPP = this.aoBrightnessXZPP;
            } else {
                this.aoLightValueScratchXYZPPP = this.blockAccess.getBlock(p_147808_2_, p_147808_3_ + 1, p_147808_4_ + 1).getAmbientOcclusionLightValue();
                this.aoBrightnessXYZPPP = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_, p_147808_3_ + 1, p_147808_4_ + 1);
            }

            if (this.renderMaxX >= 1.0D) {
                --p_147808_2_;
            }

            i1 = l;

            if (this.renderMaxX >= 1.0D || !this.blockAccess.getBlock(p_147808_2_ + 1, p_147808_3_, p_147808_4_).isOpaqueCube()) {
                i1 = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_ + 1, p_147808_3_, p_147808_4_);
                //i1 = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_, p_147808_3_ + 1, p_147808_4_);
            }
            if (d.ordinal() != ForgeDirection.EAST.ordinal()) {
                i1 = p_147808_1_.getMixedBrightnessForBlock(this.blockAccess, p_147808_2_, p_147808_3_ + 1, p_147808_4_);
            }

            f7 = this.blockAccess.getBlock(p_147808_2_ + 1, p_147808_3_, p_147808_4_).getAmbientOcclusionLightValue();
            f8 = (this.aoLightValueScratchXYPN + this.aoLightValueScratchXYZPNP + f7 + this.aoLightValueScratchXZPP) / 4.0F;
            f9 = (this.aoLightValueScratchXYZPNN + this.aoLightValueScratchXYPN + this.aoLightValueScratchXZPN + f7) / 4.0F;
            f10 = (this.aoLightValueScratchXZPN + f7 + this.aoLightValueScratchXYZPPN + this.aoLightValueScratchXYPP) / 4.0F;
            f11 = (f7 + this.aoLightValueScratchXZPP + this.aoLightValueScratchXYPP + this.aoLightValueScratchXYZPPP) / 4.0F;
            f3 = (float) ((double) f8 * (1.0D - this.renderMinY) * this.renderMaxZ + (double) f9 * (1.0D - this.renderMinY) * (1.0D - this.renderMaxZ) + (double) f10 * this.renderMinY * (1.0D - this.renderMaxZ)
                    + (double) f11 * this.renderMinY * this.renderMaxZ);
            f4 = (float) ((double) f8 * (1.0D - this.renderMinY) * this.renderMinZ + (double) f9 * (1.0D - this.renderMinY) * (1.0D - this.renderMinZ) + (double) f10 * this.renderMinY * (1.0D - this.renderMinZ)
                    + (double) f11 * this.renderMinY * this.renderMinZ);
            f5 = (float) ((double) f8 * (1.0D - this.renderMaxY) * this.renderMinZ + (double) f9 * (1.0D - this.renderMaxY) * (1.0D - this.renderMinZ) + (double) f10 * this.renderMaxY * (1.0D - this.renderMinZ)
                    + (double) f11 * this.renderMaxY * this.renderMinZ);
            f6 = (float) ((double) f8 * (1.0D - this.renderMaxY) * this.renderMaxZ + (double) f9 * (1.0D - this.renderMaxY) * (1.0D - this.renderMaxZ) + (double) f10 * this.renderMaxY * (1.0D - this.renderMaxZ)
                    + (double) f11 * this.renderMaxY * this.renderMaxZ);
            j1 = this.getAoBrightness(this.aoBrightnessXYPN, this.aoBrightnessXYZPNP, this.aoBrightnessXZPP, i1);
            k1 = this.getAoBrightness(this.aoBrightnessXZPP, this.aoBrightnessXYPP, this.aoBrightnessXYZPPP, i1);
            l1 = this.getAoBrightness(this.aoBrightnessXZPN, this.aoBrightnessXYZPPN, this.aoBrightnessXYPP, i1);
            i2 = this.getAoBrightness(this.aoBrightnessXYZPNN, this.aoBrightnessXYPN, this.aoBrightnessXZPN, i1);
            this.brightnessTopLeft = this.mixAoBrightness(j1, i2, l1, k1, (1.0D - this.renderMinY) * this.renderMaxZ, (1.0D - this.renderMinY) * (1.0D - this.renderMaxZ), this.renderMinY * (1.0D - this.renderMaxZ),
                    this.renderMinY * this.renderMaxZ);
            this.brightnessBottomLeft = this.mixAoBrightness(j1, i2, l1, k1, (1.0D - this.renderMinY) * this.renderMinZ, (1.0D - this.renderMinY) * (1.0D - this.renderMinZ), this.renderMinY * (1.0D - this.renderMinZ),
                    this.renderMinY * this.renderMinZ);
            this.brightnessBottomRight = this.mixAoBrightness(j1, i2, l1, k1, (1.0D - this.renderMaxY) * this.renderMinZ, (1.0D - this.renderMaxY) * (1.0D - this.renderMinZ), this.renderMaxY * (1.0D - this.renderMinZ),
                    this.renderMaxY * this.renderMinZ);
            this.brightnessTopRight = this.mixAoBrightness(j1, i2, l1, k1, (1.0D - this.renderMaxY) * this.renderMaxZ, (1.0D - this.renderMaxY) * (1.0D - this.renderMaxZ), this.renderMaxY * (1.0D - this.renderMaxZ),
                    this.renderMaxY * this.renderMaxZ);

            if (flag1) {
                this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = p_147808_5_ * 0.6F;
                this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = p_147808_6_ * 0.6F;
                this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = p_147808_7_ * 0.6F;
            } else {
                this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = 0.6F;
                this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = 0.6F;
                this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = 0.6F;
            }

            this.colorRedTopLeft *= f3;
            this.colorGreenTopLeft *= f3;
            this.colorBlueTopLeft *= f3;
            this.colorRedBottomLeft *= f4;
            this.colorGreenBottomLeft *= f4;
            this.colorBlueBottomLeft *= f4;
            this.colorRedBottomRight *= f5;
            this.colorGreenBottomRight *= f5;
            this.colorBlueBottomRight *= f5;
            this.colorRedTopRight *= f6;
            this.colorGreenTopRight *= f6;
            this.colorBlueTopRight *= f6;
            iicon = this.getBlockIcon(p_147808_1_, this.blockAccess, p_147808_2_, p_147808_3_, p_147808_4_, 5);
            this.renderFaceXPos(p_147808_1_, (double) p_147808_2_, (double) p_147808_3_, (double) p_147808_4_, iicon);

            if (fancyGrass && iicon.getIconName().equals("grass_side") && !this.hasOverrideBlockTexture()) {
                this.colorRedTopLeft *= p_147808_5_;
                this.colorRedBottomLeft *= p_147808_5_;
                this.colorRedBottomRight *= p_147808_5_;
                this.colorRedTopRight *= p_147808_5_;
                this.colorGreenTopLeft *= p_147808_6_;
                this.colorGreenBottomLeft *= p_147808_6_;
                this.colorGreenBottomRight *= p_147808_6_;
                this.colorGreenTopRight *= p_147808_6_;
                this.colorBlueTopLeft *= p_147808_7_;
                this.colorBlueBottomLeft *= p_147808_7_;
                this.colorBlueBottomRight *= p_147808_7_;
                this.colorBlueTopRight *= p_147808_7_;
                this.renderFaceXPos(p_147808_1_, (double) p_147808_2_, (double) p_147808_3_, (double) p_147808_4_, BlockGrass.getIconSideOverlay());
            }

            flag = true;
        }

        this.enableAO = false;
        return flag;

    }

}
