package shift.sextiarysector.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import shift.sextiarysector.SextiarySector;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RendererOreStone implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {

		GL11.glPushMatrix();

		Tessellator tessellator = Tessellator.instance;
		renderer.setOverrideBlockTexture(Blocks.stone.getIcon(0, 0));
		renderer.setRenderBounds(0.0001D, 0.0001D, 0.0001D, 0.9999D, 0.9999D, 0.9999D);

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

		GL11.glPushMatrix();

		renderer.clearOverrideBlockTexture();
		renderer.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);

		metadata = 8;

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

		double s = 0.0001D;

		//renderer.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
		//renderer.setRenderBounds(-0.0001D, -0.0001D, -0.0001D, 1.0001D, 1.0001D, 1.0001D);
		//renderer.renderStandardBlock(block, x, y, z);

		//renderer.renderAllFaces = true;

		//renderer.setOverrideBlockTexture(Blocks.stone.getIcon(0, 0));
		//renderer.setRenderBounds(0.0001D, 0.0001D, 0.0001D, 0.9999D, 0.9999D, 0.9999D);
		//renderer.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
		////renderer.renderStandardBlock(block, x, y, z);
		//renderer.renderAllFaces = false;

		//renderer.clearOverrideBlockTexture();

		////int metadata = 8;//world.getBlockMetadata(x, y, z);

		//int mixedBrightness = block.getMixedBrightnessForBlock(world, x, y, z);

		////Tessellator tesselator = Tessellator.instance;

		//tesselator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
		//tesselator.setColorOpaque_F(0.6F, 0.6F, 0.6F);
		//tesselator.setColorOpaque_F(1.0F, 1.0F, 1.0F);

		/*
		if (block.shouldSideBeRendered(world, x, y - 1, z, 0)) {
			tesselator.setBrightness(block.getMixedBrightnessForBlock(world, x, y - 1, z));
			tesselator.setColorOpaque_F(0.45F, 0.45F, 0.45F);
			renderer.renderFaceYNeg(block, x, y, z, renderer.getBlockIconFromSideAndMetadata(block, 0, metadata));
		}

		if (block.shouldSideBeRendered(world, x, y + 1, z, 1)) {
			tesselator.setBrightness(block.getMixedBrightnessForBlock(world, x, y + 1, z));
			tesselator.setColorOpaque_F(0.88F, 0.88F, 0.88F);
			renderer.renderFaceYPos(block, x, y, z, renderer.getBlockIconFromSideAndMetadata(block, 1, metadata));
		}

		if (block.shouldSideBeRendered(world, x, y, z - 1, 2)) {
			tesselator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z - 1));
			tesselator.setColorOpaque_F(0.7F, 0.7F, 0.7F);
			renderer.renderFaceZNeg(block, x, y, z, renderer.getBlockIconFromSideAndMetadata(block, 2, metadata));
		}

		if (block.shouldSideBeRendered(world, x, y, z + 1, 3)) {
			tesselator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z + 1));
			tesselator.setColorOpaque_F(0.7F, 0.7F, 0.7F);
			renderer.renderFaceZPos(block, x, y, z, renderer.getBlockIconFromSideAndMetadata(block, 3, metadata));
		}

		if (block.shouldSideBeRendered(world, x - 1, y, z, 4)) {
			tesselator.setBrightness(block.getMixedBrightnessForBlock(world, x - 1, y, z));
			tesselator.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			renderer.renderFaceXNeg(block, x, y, z, renderer.getBlockIconFromSideAndMetadata(block, 4, metadata));
		}

		if (block.shouldSideBeRendered(world, x + 1, y, z, 5)) {
			tesselator.setBrightness(block.getMixedBrightnessForBlock(world, x + 1, y, z));
			tesselator.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			renderer.renderFaceXPos(block, x, y, z, renderer.getBlockIconFromSideAndMetadata(block, 5, metadata));
		}

		*/

		GL11.glPushMatrix();
		renderer.renderStandardBlock(Blocks.stone, x, y, z);
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		renderer.renderStandardBlock(block, x, y, z);
		GL11.glPopMatrix();

		return true;

	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return SextiarySector.proxy.oreStoneType;
	}
}
