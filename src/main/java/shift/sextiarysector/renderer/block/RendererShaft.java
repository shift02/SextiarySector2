package shift.sextiarysector.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.renderer.model.ModelShaft;
import shift.sextiarysector.tileentity.TileEntityShaft;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RendererShaft extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler {

	public static final ResourceLocation MC_BLOCK_SHEET = new ResourceLocation("textures/atlas/blocks.png");


	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {

		if(modelID!=this.getRenderId()){
			return ;
		}

		GL11.glPushMatrix();

        float scale = 0.0625f;
        GL11.glScalef(scale,scale,scale);

        GL11.glRotatef(90, 1, 0, 0);

        if(block == SSBlocks.woodShaft){
        	this.bind(woodShaftTextures);
        }else if(block == SSBlocks.stoneShaft){
        	this.bind(stoneShaftTextures);
        }

        modelShaft.render(null, 0,0,0, 0,0, 1.0f);
        modelShaft.renderIn(null, 0,0,0, 0,0, 1.0f);
        modelShaft.renderOut(null, 0,0,0, 0,0, 1.0f);

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
		return SextiarySector.instance.proxy.ShaftRenderType;
	}

	private static final ResourceLocation woodShaftTextures = new ResourceLocation("sextiarysector:textures/models/wood_shaft.png");
	private static final ResourceLocation stoneShaftTextures = new ResourceLocation("sextiarysector:textures/models/stone_shaft.png");

	static public ModelShaft modelShaft = new ModelShaft();

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {

		TileEntityShaft tile = (TileEntityShaft)tileentity;

		//System.out.println("renderTileEntityAt");

		GL11.glPushMatrix();
        GL11.glTranslatef((float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F);
        float scale = 0.0625f;
        GL11.glScalef(scale,scale,scale);

        switch(tile.getStorage().getMaxPowerStored()){
        	case 1:this.bindTexture(woodShaftTextures);break;
        	case 2:this.bindTexture(stoneShaftTextures);break;
        }


        switch(tile.direction){
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

        if(!this.isIn(tileentity)){
        	modelShaft.renderIn(null, 0,0,0, 0,0, 1.0f);
        }

        if(!this.isOut(tileentity)){
        	modelShaft.renderOut(null, 0,0,0, 0,0, 1.0f);
        }

        //傾きのスピード
        GL11.glRotatef(tile.getRotateStep(), 0, 0, 1);

        modelShaft.render(null, 0,0,0, 0,0, 1.0f);

        GL11.glPopMatrix();

	}


	private static void bind(ResourceLocation res)
    {
        FMLClientHandler.instance().getClient().getTextureManager().bindTexture(res);
    }


	public boolean isOut(TileEntity tileentity){

		TileEntityShaft tile = (TileEntityShaft) tileentity;

		int x = tile.xCoord + tile.direction.offsetX;
		int y = tile.yCoord + tile.direction.offsetY;
		int z = tile.zCoord + tile.direction.offsetZ;

		TileEntity tile2 = tile.getWorldObj().getTileEntity(x, y, z);

		if(tile2 instanceof TileEntityShaft && ((TileEntityShaft) tile2).getOutDirection().ordinal()==tile.getOutDirection().ordinal()){
			return true;
		}else{
			return false;
		}

	}

	public boolean isIn(TileEntity tileentity){

		TileEntityShaft tile = (TileEntityShaft) tileentity;

		int x = tile.xCoord - tile.direction.offsetX;
		int y = tile.yCoord - tile.direction.offsetY;
		int z = tile.zCoord - tile.direction.offsetZ;

		TileEntity tile2 = tile.getWorldObj().getTileEntity(x, y, z);

		if(tile2 instanceof TileEntityShaft && ((TileEntityShaft) tile2).getOutDirection().ordinal()==tile.getOutDirection().ordinal()){
			return true;
		}else{
			return false;
		}

	}



	/*
	public static boolean isOut(TileEntity tileentity){

		TileEntityDirection tile = (TileEntityDirection)tileentity;

		int x = tileentity.xCoord + tile.getDirection().offsetX;
		int y = tileentity.yCoord + tile.getDirection().offsetY;
		int z = tileentity.zCoord + tile.getDirection().offsetZ;

		TileEntity tile2 = tileentity.getWorldObj().getTileEntity(x, y, z);

		if(tile2 instanceof IAxle && ((IAxle) tile2).getDirection().ordinal()==tile.getDirection().ordinal()){
			return true;
		}else{
			return false;
		}

	}

	public static boolean isIn(TileEntity tileentity){

		TileEntityDirection tile = (TileEntityDirection)tileentity;

		int x = tileentity.xCoord - tile.getDirection().offsetX;
		int y = tileentity.yCoord - tile.getDirection().offsetY;
		int z = tileentity.zCoord - tile.getDirection().offsetZ;

		TileEntity tile2 = tileentity.getWorldObj().getTileEntity(x, y, z);

		if(tile2 instanceof IAxle && ((IAxle) tile2).getDirection().ordinal()==tile.getDirection().ordinal()){
			return true;
		}else{
			return false;
		}

	}*/

}
