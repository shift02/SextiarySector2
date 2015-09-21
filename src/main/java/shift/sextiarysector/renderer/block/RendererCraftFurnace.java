package shift.sextiarysector.renderer.block;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import shift.sextiarysector.renderer.model.ModelCraftFurnace;
import shift.sextiarysector.tileentity.TileEntityCraftFurnace;

public class RendererCraftFurnace extends TileEntitySpecialRenderer {

    private static final ResourceLocation craftFurnaceTextures = new ResourceLocation("sextiarysector:textures/models/craft_furnace.png");

    static public ModelCraftFurnace modelCraftFurnace = new ModelCraftFurnace();

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float p_147500_8_) {

        TileEntityCraftFurnace tile = (TileEntityCraftFurnace) tileentity;

        if (!tile.isLarge()) return;

        //System.out.println("renderTileEntityAt");

        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
        float scale = 0.0625f + 0.0001f;
        GL11.glScalef(scale, scale, scale);

        this.bindTexture(craftFurnaceTextures);

        switch (tile.direction) {
            case WEST:
                GL11.glRotatef(90, 0, 1, 0);
                break;
            case EAST:
                GL11.glRotatef(90, 0, -1, 0);
                break;
            case SOUTH:
                GL11.glRotatef(180, 1, 0, 0);
                GL11.glRotatef(180, 0, 1, 0);
                break;
            case NORTH:
                GL11.glRotatef(180, 1, 0, 0);
                break;
            default:
                break;
        }

        modelCraftFurnace.render(null, 0, 0, 0, 0, 0, 1.0f);

        GL11.glPopMatrix();

    }

}
