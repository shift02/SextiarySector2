package shift.sextiarysector.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import shift.sextiarysector.plugin.PluginTofu;
import shift.sextiarysector.renderer.model.ModelMotor;
import shift.sextiarysector.tileentity.TileEntityTofuMotor;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RendererTofuMotor extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler {

    public static final ResourceLocation MC_BLOCK_SHEET = new ResourceLocation("textures/atlas/blocks.png");

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {

        if (modelID != this.getRenderId()) {
            return;
        }

        GL11.glPushMatrix();

        float scale = 0.0625f;
        GL11.glScalef(scale, scale, scale);

        GL11.glRotatef(90, 1, 0, 0);
        GL11.glRotatef(180, 0, 1, 0);

        this.bind(tofuMotorTextures);

        modelElectricMotor.render(null, 0, 0, 0, 0, 0, 1.0f);
        modelElectricMotor.renderShaft(null, 0, 0, 0, 0, 0, 1.0f);

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
        return PluginTofu.tofuMotorType;
    }

    private static final ResourceLocation tofuMotorTextures = new ResourceLocation("sextiarysector:textures/models/tofu_motor.png");

    static public ModelMotor modelElectricMotor = new ModelMotor();

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {

        TileEntityTofuMotor tile = (TileEntityTofuMotor) tileentity;

        //System.out.println("renderTileEntityAt");

        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
        float scale = 0.0625f;
        GL11.glScalef(scale, scale, scale);

        this.bindTexture(tofuMotorTextures);

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
            case SOUTH:
                GL11.glRotatef(180, 0, 1, 0);
                break;
            default:
                break;
        }

        modelElectricMotor.render(null, 0, 0, 0, 0, 0, 1.0f);

        //傾きのスピード
        GL11.glRotatef(tile.getRotateStep(), 0, 0, 1);

        modelElectricMotor.renderShaft(null, 0, 0, 0, 0, 0, 1.0f);

        GL11.glPopMatrix();

    }

    private static void bind(ResourceLocation res) {
        FMLClientHandler.instance().getClient().getTextureManager().bindTexture(res);
    }
}
