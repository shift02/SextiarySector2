/*
* 作成者: Shift02
* 作成日: 2016/03/07 - 15:25:35
*/
package shift.sextiarysector.renderer.block;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.tileentity.TileEntityGutter;

public class RendererGutter implements ISimpleBlockRenderingHandler {

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {

    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {

        if (this.getRenderId() != modelId) return false;

        TileEntityGutter t = (TileEntityGutter) world.getTileEntity(x, y, z);
        ForgeDirection d = t.getDirection();

        //柱
        if (y > 0) {
            Block underBlock = world.getBlock(x, y - 1, z);
            if (underBlock.isSideSolid(world, x, y, z, ForgeDirection.UP) || this.canPlaceTorchOnTop(underBlock)) {
                renderer.setRenderBounds(0.375, 0.0D, 0.375, 0.625D, 0.25D, 0.625D);
                renderer.renderStandardBlock(block, x, y, z);
            }
        }

        if (d.equals(ForgeDirection.NORTH) || d.equals(ForgeDirection.SOUTH)) {
            this.renderWorldBlockFromZ(world, x, y, z, block, modelId, renderer);
        } else if (d.equals(ForgeDirection.EAST) || d.equals(ForgeDirection.WEST)) {
            this.renderWorldBlockFromX(world, x, y, z, block, modelId, renderer);
        }

        return true;

    }

    public void renderWorldBlockFromZ(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {

        //底
        renderer.setRenderBounds(0.25D, 0.25D, 0.0D, 0.75, 0.3125D, 1.0D);
        renderer.renderStandardBlock(block, x, y, z);

        //サイド
        renderer.setRenderBounds(0.25D, 0.3125D, 0.0D, 0.3125D, 0.75D, 1.0D);
        renderer.renderStandardBlock(block, x, y, z);

        renderer.setRenderBounds(0.6875D, 0.3125D, 0.0D, 0.75D, 0.75D, 1.0D);
        renderer.renderStandardBlock(block, x, y, z);

    }

    public void renderWorldBlockFromX(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {

        //底
        renderer.setRenderBounds(0.0D, 0.25D, 0.25D, 1.00D, 0.3125, 0.75D);
        renderer.renderStandardBlock(block, x, y, z);

        //サイド
        renderer.setRenderBounds(0.0D, 0.3125D, 0.25D, 1.0D, 0.75D, 0.3125D);
        renderer.renderStandardBlock(block, x, y, z);

        renderer.setRenderBounds(0.0D, 0.3125D, 0.6875D, 1.0D, 0.75D, 0.75D);
        renderer.renderStandardBlock(block, x, y, z);

    }

    public boolean canPlaceTorchOnTop(Block block) {
        return block == Blocks.fence || block == Blocks.nether_brick_fence || block == Blocks.glass || block == Blocks.cobblestone_wall;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        // TODO 自動生成されたメソッド・スタブ
        return false;
    }

    @Override
    public int getRenderId() {
        return SextiarySector.proxy.gutterType;
    }

}
