package shift.sextiarysector.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.renderer.model.ModelGearShaftDown;
import shift.sextiarysector.renderer.model.ModelGearShaftUp;
import shift.sextiarysector.tileentity.TileEntityGearShaft;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RendererGearShaft extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler {

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

        ModelGearShaftUp m;
        if(metadata==0){
        	m = this.modelShaftUp;
        }else{
        	m = this.modelShaftDown;
        }



        if(block == SSBlocks.woodStoneGearShaft){
        	this.bind(woodShaftTextures);
        }else if(block == SSBlocks.stoneSteelGearShaft){
        	this.bind(stoneShaftTextures);
        }else if(block == SSBlocks.steelNinjaGearShaft){
        	this.bind(steelShaftTextures);
        }else if(block == SSBlocks.ninjaOrichalcumGearShaft){
        	this.bind(ninjaShaftTextures);
        }

        //this.bind(woodShaftTextures);

        m.render(null, 0,0,0, 0,0, 1.0f);

        GL11.glRotatef(-(FMLClientHandler.instance().getClient().getMinecraft().getSystemTime()/20)%360, 0, 0, 1);

        m.renderUp(null, 0,0,0, 0,0, 1.0f);

        GL11.glRotatef((FMLClientHandler.instance().getClient().getMinecraft().getSystemTime()/20)%360, 0, 0, 1);
        GL11.glRotatef(-(FMLClientHandler.instance().getClient().getMinecraft().getSystemTime()/80)%360, 0, 0, 1);

        m.renderDown(null, 0,0,0, 0,0, 1.0f);

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
		return SextiarySector.instance.proxy.GearShaftRenderType;
	}

	private static final ResourceLocation woodShaftTextures = new ResourceLocation("sextiarysector:textures/models/wood_gear_shaft.png");
	private static final ResourceLocation stoneShaftTextures = new ResourceLocation("sextiarysector:textures/models/stone_gear_shaft.png");
	private static final ResourceLocation steelShaftTextures = new ResourceLocation("sextiarysector:textures/models/steel_gear_shaft.png");
	private static final ResourceLocation ninjaShaftTextures = new ResourceLocation("sextiarysector:textures/models/ninja_gear_shaft.png");

	static public ModelGearShaftUp modelShaftUp = new ModelGearShaftUp();
	static public ModelGearShaftDown modelShaftDown = new ModelGearShaftDown();

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {

		TileEntityGearShaft tile = (TileEntityGearShaft)tileentity;

		//System.out.println("renderTileEntityAt");

		GL11.glPushMatrix();
        GL11.glTranslatef((float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F);
        float scale = 0.0625f;
        GL11.glScalef(scale,scale,scale);

        switch(tile.getMaxPowerStored(ForgeDirection.UP)){
    	case 1:this.bindTexture(woodShaftTextures);break;
    	case 2:this.bindTexture(stoneShaftTextures);break;
    	case 3:this.bindTexture(steelShaftTextures);break;
    	case 4:this.bindTexture(ninjaShaftTextures);break;
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

        ModelGearShaftUp m;
        if(tileentity.getBlockMetadata()==0){
        	m = this.modelShaftUp;
        }else{
        	m = this.modelShaftDown;
        }

        m.render(null, 0,0,0, 0,0, 1.0f);

      //傾きのスピード
        GL11.glRotatef(tile.rotateDownStep, 0, 0, 1);

        m.renderDown(null, 0,0,0, 0,0, 1.0f);

      //傾きのスピード
        GL11.glRotatef(-tile.rotateDownStep, 0, 0, 1);
        GL11.glRotatef(tile.rotateUpStep, 0, 0, 1);

        m.renderUp(null, 0,0,0, 0,0, 1.0f);

        GL11.glPopMatrix();

	}


	private static void bind(ResourceLocation res)
    {
        FMLClientHandler.instance().getClient().getTextureManager().bindTexture(res);
    }

}
