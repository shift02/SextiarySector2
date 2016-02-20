package shift.sextiarysector.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.module.FertilizerManager;
import shift.sextiarysector.tileentity.TileEntityWood;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RendererWood implements ISimpleBlockRenderingHandler {

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {

        GL11.glPushMatrix();

        Tessellator tessellator = Tessellator.instance;
        renderer.setRenderBounds(2.0D / 16.0D, 0.0D, 2.0D / 16.0D, 14.0D / 16.0D, 15.0D / 16.0D, 14.0D / 16.0D);

        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, -1.0F, 0.0F);
        renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 0, metadata));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 1, metadata));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, -1.0F);
        renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 2, metadata));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 3, metadata));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(-1.0F, 0.0F, 0.0F);
        renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 4, metadata));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 5, metadata));
        tessellator.draw();
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);

        GL11.glPopMatrix();

    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {

        if (this.getRenderId() != modelId) return false;

        renderer.setRenderBounds(2.0D / 16.0D, 0.0D, 2.0D / 16.0D, 14.0D / 16.0D, 15.0D / 16.0D, 14.0D / 16.0D);
        renderer.renderStandardBlock(block, x, y, z);

        TileEntityWood t = (TileEntityWood) world.getTileEntity(x, y, z);

        if (t != null && t.getFertilizer() != null) {

            if (FertilizerManager.getFertilizer(t.getFertilizer()) != null) {
                renderer.setOverrideBlockTexture(FertilizerManager.getFertilizerIcon(t.getFertilizer()).getFertilizerIcon());
            } else {
                t.setFertilizer(null);
            }

            renderer.setRenderBounds(2.0D / 16.0D, 1 - 0.01f, 2.0D / 16.0D, 14.0D / 16.0D, 15.0D / 16.0D + 0.009f, 14.0D / 16.0D);
            renderer.renderStandardBlock(block, x, y, z);
        }

        renderer.clearOverrideBlockTexture();

        return true;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    @Override
    public int getRenderId() {
        return SextiarySector.proxy.woodType;
    }

}
