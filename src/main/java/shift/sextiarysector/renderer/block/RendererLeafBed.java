package shift.sextiarysector.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.SextiarySector;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RendererLeafBed implements ISimpleBlockRenderingHandler {

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {

    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {

        if (this.getRenderId() != modelId) return false;

        float f = 0.125F;

        float minx = 0;
        float minz = 0;
        float maxx = 1;
        float maxz = 1;

        renderer.setOverrideBlockTexture(Blocks.planks.getIcon(0, 0));

        //右
        if (!this.isSame(world, x, y, z, block, ForgeDirection.getOrientation(5))) {
            maxx = 1 - f;
            renderer.setRenderBounds(14.0D / 16.0D, 0.0D, 0.0D, 1.0D, 2.0D / 16.0D, 1.0D);
            renderer.renderStandardBlock(block, x, y, z);
        }

        //左
        if (!this.isSame(world, x, y, z, block, ForgeDirection.getOrientation(4))) {
            minx = f;
            renderer.setRenderBounds(0.0D, 0.0D, 0.0D, 2.0D / 16.0D, 2.0D / 16.0D, 1.0D);
            renderer.renderStandardBlock(block, x, y, z);
        }

        renderer.clearOverrideBlockTexture();

        renderer.setOverrideBlockTexture(Blocks.planks.getIcon(0, 1));

        //上
        if (!this.isSame(world, x, y, z, block, ForgeDirection.getOrientation(2))) {
            minz = f;
            renderer.setRenderBounds(0.0D, 2.0D / 16.0D, 0.0D, 1.0D, 4.0D / 16.0D, 2.0D / 16.0D);
            renderer.renderStandardBlock(block, x, y, z);
        }

        //下
        if (!this.isSame(world, x, y, z, block, ForgeDirection.getOrientation(3))) {
            maxz = 1 - f;
            renderer.setRenderBounds(0.0D, 2.0D / 16.0D, 14.0D / 16.0D, 1.0D, 4.0D / 16.0D, 1.0D);
            renderer.renderStandardBlock(block, x, y, z);
        }

        //葉っぱ
        renderer.setOverrideBlockTexture(SSBlocks.leafBed.getIcon(0, 0));
        renderer.setRenderBounds(minx, 0.0D, minz, maxx, 1.0D / 16.0D, maxz);
        renderer.renderStandardBlock(block, x, y, z);

        renderer.setOverrideBlockTexture(SSBlocks.leafBed.getIcon(0, 1));
        renderer.setRenderBounds(minx, 1.0D / 16.0D, minz, maxx, 2.0D / 16.0D, maxz);
        renderer.renderStandardBlock(block, x, y, z);

        renderer.setOverrideBlockTexture(SSBlocks.leafBed.getIcon(0, 2));
        renderer.setRenderBounds(minx, 2.0D / 16.0D, minz, maxx, 3.0D / 16.0D, maxz);
        renderer.renderStandardBlock(block, x, y, z);

        renderer.clearOverrideBlockTexture();

        return true;
    }

    protected boolean isSame(IBlockAccess p_149743_1_, int p_149743_2_, int p_149743_3_, int p_149743_4_, Block b, ForgeDirection d) {

        if (p_149743_1_.getBlock(p_149743_2_ + d.offsetX, p_149743_3_ + d.offsetY, p_149743_4_ + d.offsetZ) == b) return true;

        return false;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    @Override
    public int getRenderId() {
        return SextiarySector.proxy.leafBedType;
    }

}
