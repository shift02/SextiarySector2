package shift.sextiarysector.renderer.block;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import shift.sextiarysector.tileentity.TileEntityKnife;

public class RendererKnife extends TileEntitySpecialRenderer {

    private static RenderItem renderer;
    private static EntityItem entityItem;

    static {
        renderer = new RenderItem();
        renderer.setRenderManager(RenderManager.instance);
        entityItem = new EntityItem(null);
        entityItem.hoverStart = 0;
    }

    @Override
    public void renderTileEntityAt(TileEntity p_147500_1_, double x, double y, double z, float p_147500_8_) {

        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 0.3F, (float) z + 0.5F);
        float scale = 1.5f;//0.0625f;
        GL11.glScalef(scale, scale, scale);

        TileEntityKnife t = (TileEntityKnife) p_147500_1_;

        if (t.getKnife() != null) {

            GL11.glColor4f(1, 1, 1, 1);
            switch (t.direction) {
                case WEST:
                    //GL11.glRotatef(90, 0, 1, 0);

                    break;
                case EAST:
                    //GL11.glRotatef(90, 0, -1, 0);
                    GL11.glRotatef(180, 0, 1, 0);
                    break;
                case SOUTH:
                    //GL11.glRotatef(180, 0, 1, 0);
                    GL11.glRotatef(90, 0, 1, 0);
                    break;
                case NORTH:
                    //GL11.glRotatef(360, 0, -1, 0);
                    GL11.glRotatef(90, 0, -1, 0);
                    break;
                default:
                    break;
            }

            GL11.glTranslatef(0.22f, 0.0f, 0.0f);

            this.renderFigure(t.getKnife());

        }

        GL11.glPopMatrix();

    }

    public void renderFigure(ItemStack itemstack) {

        GL11.glColor4f(1, 1, 1, 1);
        entityItem.setEntityItemStack(itemstack);

        renderer.renderInFrame = true;
        renderer.doRender(entityItem, 0, 0.03, 0, 0, 0);
        renderer.renderInFrame = false;

    }

}
