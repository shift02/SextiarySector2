package shift.sextiarysector.renderer.block;

import static net.minecraftforge.client.IItemRenderer.ItemRenderType.*;
import static net.minecraftforge.client.IItemRenderer.ItemRendererHelper.*;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.block.BlockFigure;
import shift.sextiarysector.tileentity.TileEntityFigure;

public class RendererFigure extends TileEntitySpecialRenderer implements IItemRenderer {

    public static final ResourceLocation MC_BLOCK_SHEET = new ResourceLocation("textures/atlas/blocks.png");

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
        //float scale = 0.0625f;
        //GL11.glScalef(scale,scale,scale);

        TileEntityFigure t = (TileEntityFigure) p_147500_1_;

        if (t.getFigure() != null) {

            GL11.glColor4f(1, 1, 1, 1);
            switch (t.direction) {
                case WEST:
                    GL11.glRotatef(90, 0, 1, 0);
                    break;
                case EAST:
                    GL11.glRotatef(90, 0, -1, 0);
                    break;
                case SOUTH:
                    GL11.glRotatef(180, 0, 1, 0);
                    break;
                case NORTH:
                    GL11.glRotatef(360, 0, -1, 0);
                    break;
                default:
                    break;
            }
            //EntityItem entityitem = new EntityItem(t.getWorldObj(), t.xCoord, t.yCoord, t.zCoord, t.getFigure());
            this.renderFigure(t.getFigure());
            //renderer.doRender(entityitem, x, y, z, 0, 0);
            //RenderManager.instance.renderEntitySimple(entityitem, 1.0f);
            //System.out.println("AAAACC");
        }

        GL11.glPopMatrix();

    }

    public void renderFigure(ItemStack itemstack) {

        try {

            GL11.glColor4f(1, 1, 1, 1);
            entityItem.setEntityItemStack(itemstack);

            if (isLarge(itemstack)) {

                double d = 2;
                if (isLarge(itemstack)) GL11.glScaled(d, d, d);

                renderer.renderInFrame = true;
                renderer.doRender(entityItem, 0, 0.06, 0, 0, 0);
                renderer.renderInFrame = false;

            } else {

                renderer.renderInFrame = true;
                renderer.doRender(entityItem, 0, 0.03, 0, 0, 0);
                renderer.renderInFrame = false;

            }

        } catch (NullPointerException e) {

        }

    }

    private boolean isLarge(ItemStack itemstack) {

        IItemRenderer customItemRenderer = MinecraftForgeClient.getItemRenderer(itemstack, ENTITY);

        if (itemstack.getItemSpriteNumber() == 0 && itemstack.getItem() instanceof ItemBlock && RenderBlocks.renderItemIn3d(Block.getBlockFromItem(itemstack.getItem()).getRenderType())) {

            int renderType = Block.getBlockFromItem(itemstack.getItem()).getRenderType();
            return !(renderType == 1 || renderType == 19 || renderType == 12 || renderType == 2);

        }

        else if (customItemRenderer != null && customItemRenderer.shouldUseRenderHelper(ENTITY, itemstack, BLOCK_3D)) {

            return true;

        }

        return false;

    }

    //Item
    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        switch (type) {
            case ENTITY:
            case EQUIPPED:
            case EQUIPPED_FIRST_PERSON:
            case INVENTORY:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        switch (type) {
            case ENTITY: {
                return (helper == ItemRendererHelper.ENTITY_BOBBING ||
                        helper == ItemRendererHelper.ENTITY_ROTATION || helper == ItemRendererHelper.BLOCK_3D);
            }
            case EQUIPPED: {
                return (helper == ItemRendererHelper.BLOCK_3D || helper == ItemRendererHelper.EQUIPPED_BLOCK);
            }
            case EQUIPPED_FIRST_PERSON: {
                return (helper == ItemRendererHelper.EQUIPPED_BLOCK);
            }
            case INVENTORY: {
                return (helper == ItemRendererHelper.INVENTORY_BLOCK);
            }
            default: {
                return false;
            }
        }
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

        GL11.glPushMatrix();

        boolean r = true;

        try {

            switch (type) {
                case EQUIPPED:
                case EQUIPPED_FIRST_PERSON: {
                    GL11.glTranslatef(0.5F, 0.5F, 0.5F);
                    break;
                }
                case ENTITY:
                case INVENTORY: {
                    GL11.glTranslatef(0.0F, -0.2F, 0.0F);
                    break;
                }
                default:
                    break;
            }

            GL11.glRotatef(90, 0, 1, 0);

            ItemStack figure = BlockFigure.getFigureItem(item);

            if (figure != null) {

                float scale = 4.0f / 5.0f;
                GL11.glScalef(scale, scale, scale);

                this.renderFigure(figure);
                //GL11.glColor4f(2.0F, 2.0F, 2.0F, 0.75F);
                //GL11.glDisable(GL12.GL_RESCALE_NORMAL);
                //GL11.glDisable(GL11.GL_LIGHTING);
                //GL11.glDisable(GL11.GL_DEPTH_TEST);
                //RenderHelper.disableStandardItemLighting();
                //GL11.glDisable(GL11.GL_LIGHTING);
                //GL11.glDisable(GL11.GL_LIGHT0);
                //GL11.glDisable(GL11.GL_LIGHT1);
                //GL11.glDisable(GL11.GL_COLOR_MATERIAL);
                //GL11.glDisable(GL11.GL_BLEND);
                //GL11.glEnable(GL11.GL_LIGHTING);

            }
        } catch (NullPointerException e) {
            SextiarySector.Log.catching(e);
            r = false;
            float scale = 2.0f;
            //GL11.glScalef(scale, scale, scale);
            //RenderHelper.disableStandardItemLighting();
            //GL11.glEnable(GL11.GL_BLEND);
        }

        GL11.glPopMatrix();

        if (r) {
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            float scale = 2.0f;
            //GL11.glScalef(scale, scale, scale);
            //GL11.glEnable(GL11.GL_LIGHTING);
            //GL11.glEnable(GL11.GL_DEPTH_TEST);
            //RenderHelper.enableStandardItemLighting();
            //RenderHelper.disableStandardItemLighting();
            //RenderHelper.enableGUIStandardItemLighting();
        }

        GL11.glPushMatrix();

        switch (type) {
            case EQUIPPED:
            case EQUIPPED_FIRST_PERSON: {
                break;
            }
            case ENTITY:
            case INVENTORY: {
                GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
                break;
            }
            default:
                break;
        }

        this.bind(MC_BLOCK_SHEET);

        Tessellator tessellator = Tessellator.instance;
        RenderBlocks renderer = (RenderBlocks) data[0];
        Block block = Block.getBlockFromItem(item.getItem());
        int metadata = item.getItemDamage();
        float f = 1.0f / 16.0f;
        renderer.setRenderBounds(f * 2, 0, f * 2, f * 14, f * 4, f * 14);

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

        GL11.glPopMatrix();

        //RenderHelper.enableGUIStandardItemLighting();

    }

    private static void bind(ResourceLocation res) {
        FMLClientHandler.instance().getClient().getTextureManager().bindTexture(res);
    }

}
