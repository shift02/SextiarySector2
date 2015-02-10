package shift.sextiarysector.renderer.block;

import static net.minecraftforge.client.IItemRenderer.ItemRenderType.*;
import static net.minecraftforge.client.IItemRenderer.ItemRendererHelper.*;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

import org.lwjgl.opengl.GL11;

import shift.sextiarysector.tileentity.TileEntityFigure;

public class RendererFigure  extends TileEntitySpecialRenderer {

	private static RenderItem renderer;
	private static EntityItem entityItem;
	static {
		renderer = new RenderItem();
		renderer.setRenderManager(RenderManager.instance);
		entityItem = new EntityItem(null);
		entityItem.hoverStart = 0;
	}

	@Override
	public void renderTileEntityAt(TileEntity p_147500_1_, double x,double y, double z, float p_147500_8_) {

		GL11.glPushMatrix();
		GL11.glTranslatef((float)x + 0.5F, (float)y + 0.3F, (float)z + 0.5F);
        //float scale = 0.0625f;
        //GL11.glScalef(scale,scale,scale);

        TileEntityFigure t = (TileEntityFigure)p_147500_1_;



        if(t.getFigure() != null){

        	GL11.glColor4f(1, 1, 1, 1);
        	switch(t.direction){
            case WEST:
                GL11.glRotatef(90, 0, 1, 0);
                break;
            case EAST:
                GL11.glRotatef(90, 0, -1, 0);
                break;
            case SOUTH:
                GL11.glRotatef(180, 0, 1, 0);
                break;
            //case NORTH:
                //GL11.glRotatef(180, 0, -1, 0);
                //break;
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

	public void renderFigure(ItemStack itemstack)
    {

        entityItem.setEntityItemStack(itemstack);

        if(isLarge(itemstack)){

        	double d = 2;
            if(isLarge(itemstack))GL11.glScaled(d, d, d);

            renderer.doRender(entityItem, 0, 0.06, 0, 0, 0);

        }else{

            renderer.doRender(entityItem, 0,  0.03, 0, 0, 0);

        }

    }

	private boolean isLarge(ItemStack itemstack){

		IItemRenderer customItemRenderer = MinecraftForgeClient.getItemRenderer(itemstack, ENTITY);

        if (itemstack.getItemSpriteNumber() == 0 && itemstack.getItem() instanceof ItemBlock && RenderBlocks.renderItemIn3d(Block.getBlockFromItem(itemstack.getItem()).getRenderType()))
        {

            int renderType = Block.getBlockFromItem(itemstack.getItem()).getRenderType();
            return !(renderType == 1 || renderType == 19 || renderType == 12 || renderType == 2);

        }

        else if(customItemRenderer != null && customItemRenderer.shouldUseRenderHelper(ENTITY, itemstack, BLOCK_3D))
        {

        	return true;

        }


		return false;

	}

}
