package shift.sextiarysector.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.module.FertilizerManager;
import shift.sextiarysector.tileentity.TileEntityPaddy;

public class RendererPaddy extends RendererHole{

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,Block block, int modelId, RenderBlocks renderer) {

		super.renderWorldBlock(world, x, y, z, block, modelId, renderer);

		this.renderer.renderAllFaces = true;
		this.renderer.field_152631_f = true;


		float f = 0.125F;

		float minx = 0;
		float minz = 0;
		float maxx = 1;
		float maxz = 1;


		if(!this.isSame(world, x, y, z,block, ForgeDirection.getOrientation(4))){
			minx = f;
        }

        if(!this.isSame(world, x, y, z,block, ForgeDirection.getOrientation(2))){
        	minz = f;
        }

        if(!this.isSame(world, x, y, z,block, ForgeDirection.getOrientation(5))){
        	maxx = 1-f;
        }

        if(!this.isSame(world, x, y, z,block, ForgeDirection.getOrientation(3))){
        	maxz = 1-f;
        }

        //水の描写
        //renderer.setRenderBounds(minx, 0.3125F, minz, maxx , 0.9375F, maxz);
        renderer.setRenderBounds(minx, 0.9375F-0.001f, minz, maxx , 0.9375F, maxz);
        renderer.setOverrideBlockTexture(Blocks.water.getIcon(0, 0));
        renderer.renderStandardBlock(block, x, y, z);
        renderer.clearOverrideBlockTexture();

        this.renderer.clearOverrideBlockTexture();
        //

        TileEntityPaddy t = (TileEntityPaddy) world.getTileEntity(x, y, z);

		if(t!=null&&t.getFertilizer()!=null){

			if(FertilizerManager.getFertilizer(t.getFertilizer())!=null){
				renderer.setOverrideBlockTexture(FertilizerManager.getFertilizerIcon(t.getFertilizer()).getFertilizerIcon());
			}else{
				t.setFertilizer(null);
			}

			renderer.setRenderBounds(minx, 1 - 0.01f, minz, maxx , 1 - 0.009f, maxz);
			renderer.renderStandardBlock(block, x, y, z);
		}

		renderer.clearOverrideBlockTexture();

		this.renderer.field_152631_f = false;
        this.renderer.renderAllFaces = false;

		return true;

	}

	@Override
	public int getRenderId() {
		return SextiarySector.proxy.paddyType;
	}

}
