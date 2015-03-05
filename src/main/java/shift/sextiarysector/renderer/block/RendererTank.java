package shift.sextiarysector.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.renderer.model.ModelTank;
import shift.sextiarysector.tileentity.TileEntityTank;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RendererTank extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler {

	public static final ResourceLocation MC_BLOCK_SHEET = new ResourceLocation("textures/atlas/blocks.png");

	private static final ResourceLocation bottleTextures = new ResourceLocation("sextiarysector:textures/models/tank.png");

	private final Minecraft mc = FMLClientHandler.instance().getClient();

	private final RenderBlocks blockrender = new RenderBlocks();

	static public ModelTank modelTank = new ModelTank();

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {

		GL11.glPushMatrix();

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		this.bind(bottleTextures);

		float scale = 0.0625f;
		GL11.glScalef(scale, scale, scale);
		GL11.glRotatef(180, 1, 0, 0);

		GL11.glColor3f(1, 1, 1);

		modelTank.render(null, 0, 0, 0, 0, 0, 1.0f);

		GL11.glPopMatrix();

	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return SextiarySector.proxy.tankType;
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {

		GL11.glPushMatrix();

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_LIGHT0);
		//GL11.glDisable(GL11.GL_CULL_FACE);

		GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);

		if (((TileEntityTank) tileentity).hasFluidStack()) {

			//System.out.println("renderTileEntityAt");
			IIcon icon = ((TileEntityTank) tileentity).getFluidStack().getFluid().getIcon(((TileEntityTank) tileentity).getFluidStack());
			icon = blockrender.getIconSafe(icon);

			float i = 0.4375f;

			float f1 = ((TileEntityTank) tileentity).getRendererAmount();
			if (f1 >= 0.99) f1 = 0.98f;

			this.bindTexture(MC_BLOCK_SHEET);

			setColor3ub(((TileEntityTank) tileentity).getFluidStack().getFluid().getColor());

			blockrender.blockAccess = tileentity.getWorldObj();

			blockrender.renderAllFaces = true;
			blockrender.setOverrideBlockTexture(icon);
			//blockrender.setRenderBounds(i*2.3, -0.0625*6, i*2.3, 1-i*2.5,0.0625*1.8f, 1-i*2.3);
			blockrender.setRenderBounds(-i + 0.01f, -0.49f, -i + 0.01f, i - 0.01f, f1 - 0.49f, i - 0.01f);
			//blockrender.setRenderBounds(i, 0.0625*2, i, 1-i,0.0625*8f, 1-i);
			//blockrender.renderStandardBlock(Block.blocksList[tileentity.getWorldObj().getBlockId(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord)], tileentity.xCoord, tileentity.yCoord,tileentity.zCoord);

			Tessellator tessellator = Tessellator.instance;

			Block block = tileentity.getWorldObj().getBlock(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord);

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

		this.bindTexture(bottleTextures);

		float scale = 0.0625f;
		GL11.glScalef(scale, scale, scale);
		GL11.glRotatef(180, 1, 0, 0);

		GL11.glColor3f(1, 1, 1);

		modelTank.render(null, 0, 0, 0, 0, 0, 1.0f);

		//GL11.glEnable(GL11.GL_CULL_FACE);

		GL11.glPopMatrix();

	}

	private void bind(ResourceLocation res)
	{
		mc.getTextureManager().bindTexture(res);
	}

	public static void setColor3ub(int color) {

		GL11.glColor3ub((byte) (color >> 16 & 0xFF), (byte) (color >> 8 & 0xFF), (byte) (color & 0xFF));
	}

}
