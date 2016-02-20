package shift.sextiarysector.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.renderer.model.ModelSquare;
import shift.sextiarysector.tileentity.TileEntitySquare;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RendererSquare extends TileEntitySpecialRenderer implements
        ISimpleBlockRenderingHandler {

    private static final ResourceLocation squareTextures = new ResourceLocation("sextiarysector:textures/models/square.png");
    private static final ResourceLocation squareUunderTextures = new ResourceLocation("sextiarysector:textures/models/square_under.png");
    static public ModelSquare modelSquare = new ModelSquare();

    private final RenderBlocks blockrender = new RenderBlocks();

    public static final ResourceLocation MC_BLOCK_SHEET = new ResourceLocation("textures/atlas/blocks.png");

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {

        if (modelID != this.getRenderId()) {
            return;
        }

        GL11.glPushMatrix();

        float scale = 0.0625f / 1.0f;
        GL11.glScalef(scale, scale, scale);

        GL11.glRotatef(180, 1, 0, 0);

        GL11.glRotatef(90, 0, -1, 0);
        this.bind(squareTextures);

        modelSquare.render(null, 0, 0, 0, 0, 0, 1.0f);

        GL11.glPopMatrix();

        this.bind(MC_BLOCK_SHEET);

    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        return false;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    @Override
    public int getRenderId() {
        return SextiarySector.proxy.squareType;
    }

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {

        TileEntitySquare tile = (TileEntitySquare) tileentity;

        GL11.glPushMatrix();

        GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);

        float scale = 0.0625f;
        // float scale = 0.125f;
        GL11.glScalef(scale, scale, scale);
        GL11.glRotatef(180, 1, 0, 0);
        switch (tile.direction) {
            case WEST:
                GL11.glRotatef(90, 0, 1, 0);
                break;
            case EAST:
                GL11.glRotatef(90, 0, -1, 0);
                break;
            case NORTH:
                GL11.glRotatef(180, 0, 1, 0);
            default:
                break;
        }

        if (tile.direction.ordinal() == ForgeDirection.UP.ordinal()) {
            this.bindTexture(squareTextures);
        } else if (tile.direction.ordinal() == ForgeDirection.DOWN.ordinal()) {
            this.bindTexture(squareUunderTextures);
        } else {
            this.bindTexture(squareTextures);
            modelSquare.renderConnection(null, 0, 0, 0, 0, 0, 1.0f);
        }

        modelSquare.render(null, 0, 0, 0, 0, 0, 1.0f);

        GL11.glPopMatrix();

        GL11.glPushMatrix();

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_LIGHT0);

        //GL11.glDisable(GL11.GL_DEPTH_TEST);

        GL11.glColor3f(1, 1, 1);

        GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);

        // float scale = 0.125f;

        if (tile.hasFluid()) {

            IIcon icon = tile.getFluidIcon();
            float fluidHeight = tile.getFluidHeight();
            Block block = tile.getBlockType();

            //float i = 0.3125f;

            this.bindTexture(MC_BLOCK_SHEET);

            this.setColor3ubFromInt(tile.getFluidColor());

            blockrender.blockAccess = tileentity.getWorldObj();

            blockrender.renderAllFaces = true;
            blockrender.setOverrideBlockTexture(icon);
            blockrender.setRenderBounds(-5.99f / 16.0f, -5.99f / 16.0f, -5.99f / 16.0f, 6.0f / 16.0f, fluidHeight - 6.0f / 16.0f, 6.0f / 16.0f);

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

        //GL11.glEnable(GL11.GL_DEPTH_TEST);

        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LIGHTING);

        GL11.glColor3f(1, 1, 1);

        GL11.glPopMatrix();

    }

    private static void bind(ResourceLocation res) {
        FMLClientHandler.instance().getClient().getTextureManager().bindTexture(res);
    }

    public static void setColor3ubFromInt(int color) {

        GL11.glColor3ub((byte) (color >> 16 & 0xFF), (byte) (color >> 8 & 0xFF), (byte) (color & 0xFF));
    }
}
