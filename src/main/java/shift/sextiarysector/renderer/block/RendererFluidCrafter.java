package shift.sextiarysector.renderer.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fluids.FluidRegistry;

import org.lwjgl.opengl.GL11;

import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.renderer.model.ModelFluidCrafter;
import shift.sextiarysector.tileentity.TileEntityFluidCrafter;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RendererFluidCrafter  extends TileEntitySpecialRenderer  implements ISimpleBlockRenderingHandler {

	public static final ResourceLocation MC_BLOCK_SHEET = new ResourceLocation("textures/atlas/blocks.png");

	private static final ResourceLocation bottleTextures = new ResourceLocation("sextiarysector:textures/models/fluid_crafter.png");

	private final Minecraft  mc = FMLClientHandler.instance().getClient();

	private final RenderBlocks  blockrender = new RenderBlocks();

	public static ModelFluidCrafter modelFluidCrafter = new ModelFluidCrafter();

	public static Random r = new Random();

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {

		GL11.glPushMatrix();

		GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);


		this.bind(MC_BLOCK_SHEET);

        //GL11.glTranslatef((float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F);

		GL11.glRotatef(-(FMLClientHandler.instance().getClient().getMinecraft().getSystemTime()/50)%360, 0, 1, 0);

		if(metadata!=0&&FluidRegistry.getFluid(metadata)!=null){

			IIcon  icon = FluidRegistry.getFluid(metadata).getIcon();

	        setColor3ubFromInt(FluidRegistry.getFluid(metadata).getColor());

	        float i = 0.0625f;

	        GL11.glEnable(GL11.GL_BLEND);
	        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);


	        renderer.renderAllFaces = true;
	        renderer.setOverrideBlockTexture(icon);
	        renderer.setRenderBounds(-3.9f/16.0f, -3.9f/16.0f, -3.9f/16.0f, 3.9f/16.0f, 3.9/16.0f, 3.9f/16.0f);
	        Tessellator tessellator = Tessellator.instance;

	        tessellator.startDrawingQuads();
			tessellator.setNormal(0.0F, -1.0F, 0.0F);
			renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, icon);
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.setNormal(0.0F, 1.0F, 0.0F);
			renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, icon);
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.setNormal(0.0F, 0.0F, -1.0F);
			renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, icon);
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.setNormal(0.0F, 0.0F, 1.0F);
			renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, icon);
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.setNormal(-1.0F, 0.0F, 0.0F);
			renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, icon);
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.setNormal(1.0F, 0.0F, 0.0F);
			renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, icon);
			tessellator.draw();

			renderer.renderAllFaces = false;
			renderer.clearOverrideBlockTexture();

		}



        this.bind(bottleTextures);

        float scale = 0.0625f;
        GL11.glScalef(scale,scale,scale);
        GL11.glRotatef(180, 1, 0, 0);

        GL11.glColor3f(1,1,1);


        modelFluidCrafter.render(null, 0,0,0, 0,0, 1.0f);

        GL11.glPopMatrix();

	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,RenderBlocks renderer) {
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int mate) {
		return true;
	}

	@Override
	public int getRenderId() {
		return SextiarySector.proxy.fluidCrafterType;
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {

		GL11.glPushMatrix();

		GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_LIGHT0);

		GL11.glTranslatef((float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F);

		GL11.glRotatef(((TileEntityFluidCrafter)tileentity).x, 1, 0, 0);
		GL11.glRotatef(((TileEntityFluidCrafter)tileentity).y, 0, 1, 0);
		GL11.glRotatef(((TileEntityFluidCrafter)tileentity).z, 0, 0, 1);

		if(((TileEntityFluidCrafter)tileentity).hasFluid()){

			IIcon  icon = ((TileEntityFluidCrafter)tileentity).getFluidIcon();
			Block block = tileentity.getBlockType();

	        //float i = 0.3125f;

	        this.bindTexture(MC_BLOCK_SHEET);

	        this.setColor3ubFromInt(((TileEntityFluidCrafter)tileentity).getFluidColor());

	        blockrender.blockAccess =tileentity.getWorldObj();

	        blockrender.renderAllFaces = true;
	        blockrender.setOverrideBlockTexture(icon);
	        //blockrender.setRenderBounds(i*2.3, -0.0625*6, i*2.3, 1-i*2.5,0.0625*1.8f, 1-i*2.3);
	        blockrender.setRenderBounds(-3.9f/16.0f, -3.9f/16.0f, -3.9f/16.0f, 3.9f/16.0f, 3.9/16.0f, 3.9f/16.0f);
	        //blockrender.setRenderBounds(i, 0.0625*2, i, 1-i,0.0625*8f, 1-i);
	        //blockrender.renderStandardBlock(Block.blocksList[tileentity.getWorldObj().getBlockId(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord)], tileentity.xCoord, tileentity.yCoord,tileentity.zCoord);


	        Tessellator tessellator = Tessellator.instance;

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
        GL11.glScalef(scale,scale,scale);
        GL11.glRotatef(180, 1, 0, 0);

        GL11.glColor3f(1,1,1);

        modelFluidCrafter.render(null, 0,0,0, 0,0, 1.0f);

        GL11.glPopMatrix();

	}


	private void bind(ResourceLocation res)
    {
        mc.getTextureManager().bindTexture(res);
    }

	public static void setColor3ubFromInt(int color) {

		GL11.glColor3ub((byte) (color >> 16 & 0xFF), (byte) (color >> 8 & 0xFF), (byte) (color & 0xFF));
	}

}
