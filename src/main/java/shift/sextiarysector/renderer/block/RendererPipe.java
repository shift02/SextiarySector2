package shift.sextiarysector.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.api.EnumColor;
import shift.sextiarysector.block.BlockPipe;
import shift.sextiarysector.renderer.model.ModelPipe;
import shift.sextiarysector.tileentity.TileEntityPipe;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RendererPipe extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler {

    public static final ResourceLocation MC_BLOCK_SHEET = new ResourceLocation("textures/atlas/blocks.png");

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {

        if (modelId != this.getRenderId()) {
            return;
        }

        GL11.glPushMatrix();

        float scale = 0.0625f;
        GL11.glScalef(scale, scale, scale);

        GL11.glRotatef(180, 0, 1, 0);

        this.bind(this.pipeTextures);

        modelPipe.render(null, 0, 0, 0, 0, 0, 1.0f);
        modelPipe.renderPipe(null, 0, 0, 0, 0, 0, 1.0f, 0, false);
        modelPipe.renderPipe(null, 0, 0, 0, 0, 0, 1.0f, 1, false);

        GL11.glPopMatrix();

        this.bind(MC_BLOCK_SHEET);

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
        return SextiarySector.proxy.pipeType;
    }

    private static final ResourceLocation pipeTextures = new ResourceLocation("sextiarysector:textures/models/copper_pipe.png");
    private static final ResourceLocation pipeColorTextures = new ResourceLocation("sextiarysector:textures/models/copper_pipe_color.png");

    static public ModelPipe modelPipe = new ModelPipe();

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x1, double y1, double z1, float p_147500_8_) {

        TileEntityPipe tile = (TileEntityPipe) tileentity;

        GL11.glPushMatrix();
        GL11.glTranslated(x1 + 0.5F, y1 + 0.5F, z1 + 0.5F);
        float scale = 0.0623f;
        GL11.glScalef(scale, scale, scale);
        GL11.glDisable(GL11.GL_LIGHTING);

        this.bindTexture(pipeTextures);

        modelPipe.render(null, 0, 0, 0, 0, 0, 1.0f);

        for (ForgeDirection d : ForgeDirection.VALID_DIRECTIONS) {

            if (BlockPipe.canConnect(tile.getWorldObj(), tile.xCoord, tile.yCoord, tile.zCoord, d)) {
                modelPipe.renderPipe(null, 0, 0, 0, 0, 0, 1.0f, d.ordinal(), false);
            }

            /*
            int x = tile.xCoord + d.offsetX;
            int y = tile.yCoord + d.offsetY;
            int z = tile.zCoord + d.offsetZ;
            
            TileEntity t = tileentity.getWorldObj().getTileEntity(x, y, z);
            
            if (t instanceof IFluidHandler) {
            	modelPipe.renderPipe(null, 0, 0, 0, 0, 0, 1.0f, d.ordinal(), false);
            }*/

        }

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();

        if (tile.color.ordinal() != EnumColor.Unknown.ordinal()) {

            GL11.glPushMatrix();
            GL11.glTranslated(x1 + 0.5F, y1 + 0.5F, z1 + 0.5F);
            float scale2 = 0.0624f;
            GL11.glScalef(scale2, scale2, scale2);
            GL11.glDisable(GL11.GL_LIGHTING);

            this.bindTexture(pipeColorTextures);
            byte color[] = tile.color.getGLColor3ub();
            GL11.glColor3ub(color[0], color[1], color[2]);

            modelPipe.render(null, 0, 0, 0, 0, 0, 1.0f);

            for (ForgeDirection d : ForgeDirection.VALID_DIRECTIONS) {

                if (BlockPipe.canConnect(tile.getWorldObj(), tile.xCoord, tile.yCoord, tile.zCoord, d)) {
                    modelPipe.renderPipe(null, 0, 0, 0, 0, 0, 1.0f, d.ordinal(), true);
                }

                /*
                int x = tile.xCoord + d.offsetX;
                int y = tile.yCoord + d.offsetY;
                int z = tile.zCoord + d.offsetZ;
                
                TileEntity t = tileentity.getWorldObj().getTileEntity(x, y, z);
                
                if (t instanceof IFluidHandler) {
                
                }*/

            }

            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glPopMatrix();
        }

    }

    private static void bind(ResourceLocation res) {
        FMLClientHandler.instance().getClient().getTextureManager().bindTexture(res);
    }

}
