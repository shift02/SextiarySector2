package shift.sextiarysector.renderer.block;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.api.shop.IShopMemory;
import shift.sextiarysector.renderer.model.ModelMonitor;
import shift.sextiarysector.tileentity.TileEntityShopMonitor;

public class RendererShopMonitor extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler {

    private static final ResourceLocation monitorTextures = new ResourceLocation("sextiarysector:textures/models/monitor.png");
    static public ModelBase modelMonitor = new ModelMonitor();
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
        this.bind(monitorTextures);

        modelMonitor.render(null, 0, 0, 0, 0, 0, 1.0f);

        GL11.glPopMatrix();

        this.bind(MC_BLOCK_SHEET);

    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
            RenderBlocks renderer) {
        return false;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    @Override
    public int getRenderId() {
        return SextiarySector.proxy.shopMonitorType;
    }

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {

        TileEntityShopMonitor tile = (TileEntityShopMonitor) tileentity;

        //System.out.println("renderTileEntityAt");

        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
        float scale = 0.0625f;
        //float scale =  0.125f;
        GL11.glScalef(scale, scale, scale);
        GL11.glRotatef(180, 1, 0, 0);
        switch (tile.direction) {
            case UP:
                GL11.glRotatef(90, 1, 0, 0);
                break;
            case DOWN:
                GL11.glRotatef(90, -1, 0, 0);
                break;
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

        if (!tile.on) {
            this.bindTexture(monitorTextures);
        } else {
            if (tile.getMemory() != null) {

                if (tile.getMemory().getItem() instanceof IShopMemory) {
                    IShopMemory memory = (IShopMemory) tile.getMemory().getItem();

                    this.bindTexture(memory.getMonitorResource());
                } else {
                    this.bindTexture(monitorTextures);
                }

            } else {
                this.bindTexture(monitorTextures);
            }

        }

        modelMonitor.render(null, 0, 0, 0, 0, 0, 1.0f);

        GL11.glPopMatrix();

    }

    private static void bind(ResourceLocation res) {
        FMLClientHandler.instance().getClient().getTextureManager().bindTexture(res);
    }

}
