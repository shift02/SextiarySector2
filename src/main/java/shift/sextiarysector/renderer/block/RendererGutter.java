/*
* 作成者: Shift02
* 作成日: 2016/03/07 - 15:25:35
*/
package shift.sextiarysector.renderer.block;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.tileentity.TileEntityGutter;
import shift.sextiarysector.tileentity.TileEntityGutter.FluidTankDirection;

public class RendererGutter extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler {

    public static final ResourceLocation MC_BLOCK_SHEET = new ResourceLocation("textures/atlas/blocks.png");

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {

    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {

        if (this.getRenderId() != modelId) return false;

        TileEntityGutter t = (TileEntityGutter) world.getTileEntity(x, y, z);
        ForgeDirection d = t.getDirection();

        renderer.renderAllFaces = true;

        //柱
        if (y > 0) {
            Block underBlock = world.getBlock(x, y - 1, z);
            if (underBlock.isSideSolid(world, x, y, z, ForgeDirection.UP) || this.canPlaceTorchOnTop(underBlock)) {

                renderer.setRenderBounds(0.375, 0.0D, 0.375, 0.625D, 0.25D, 0.625D);
                renderer.renderStandardBlock(block, x, y, z);
            }
        }

        //木
        if (d.equals(ForgeDirection.NORTH) || d.equals(ForgeDirection.SOUTH)) {
            this.renderWorldBlockFromZ(world, x, y, z, block, modelId, renderer);
        } else if (d.equals(ForgeDirection.EAST) || d.equals(ForgeDirection.WEST)) {
            this.renderWorldBlockFromX(world, x, y, z, block, modelId, renderer);
        }

        renderer.renderAllFaces = false;

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

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float p_147500_8_) {

        GL11.glPushMatrix();

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_LIGHT0);

        GL11.glTranslatef((float) x, (float) y, (float) z);

        this.bindTexture(MC_BLOCK_SHEET);

        RenderBlocks renderer = RenderBlocks.getInstance();

        Block block = tileEntity.getWorldObj().getBlock(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);

        TileEntityGutter t = (TileEntityGutter) tileEntity;

        //水真ん中
        if (t.getTank(ForgeDirection.UNKNOWN).getClientFluidAmount() > 0) {
            GL11.glPushMatrix();
            this.setFluidBounds(0.3125D - 0.00001D, 0.3125D - 0.00001D, 0.6875D + 0.00001D, 0.6875D + 0.00001D, t.getTank(ForgeDirection.UNKNOWN));
            this.setFluidColor(t.getTank(ForgeDirection.UNKNOWN));
            this.renderFluid(block, t.getTank(ForgeDirection.UNKNOWN).getClientFluid().getFluid().getIcon(t.getTank(ForgeDirection.UNKNOWN).getFluid()));
            GL11.glPopMatrix();
        }

        //上
        if (t.getTank(ForgeDirection.UP).getClientFluidAmount() > 0) {
            GL11.glPushMatrix();

            double s = (0.0625) * (3.0D * t.getTank(ForgeDirection.UP).getClientFluidAmount() / t.getTank(ForgeDirection.UP).getCapacity());
            if (t.getUPHalfGutter() > 0) {
                s = 0.1875D;
            }
            renderer.setRenderBounds(0.5D - s, 0.3125D, 0.5D - s, 0.5D + s, 1.00D, 0.5D + s);
            this.setFluidColor(t.getTank(ForgeDirection.UP));
            this.renderFluid(block, t.getTank(ForgeDirection.UP).getClientFluid().getFluid().getFlowingIcon());
            GL11.glPopMatrix();
        }

        ForgeDirection d = t.getDirection();

        if (d.equals(ForgeDirection.NORTH) || d.equals(ForgeDirection.SOUTH)) {

            if (t.getTank(ForgeDirection.NORTH).getClientFluidAmount() > 0) {
                GL11.glPushMatrix();
                this.setFluidBounds(0.3125D, 0.00D, 0.6875D, 0.3125D, t.getTank(ForgeDirection.NORTH));
                this.setFluidColor(t.getTank(ForgeDirection.NORTH));
                this.renderFluid(block, t.getTank(ForgeDirection.NORTH).getClientFluid().getFluid().getIcon(t.getTank(ForgeDirection.NORTH).getFluid()));
                GL11.glPopMatrix();
            }

            if (t.getTank(ForgeDirection.SOUTH).getClientFluidAmount() > 0) {
                GL11.glPushMatrix();
                this.setFluidBounds(0.3125D, 0.6875D, 0.6875D, 1.00D, t.getTank(ForgeDirection.SOUTH));
                this.setFluidColor(t.getTank(ForgeDirection.SOUTH));
                this.renderFluid(block, t.getTank(ForgeDirection.SOUTH).getClientFluid().getFluid().getIcon(t.getTank(ForgeDirection.SOUTH).getFluid()));
                GL11.glPopMatrix();
            }

        } else if (d.equals(ForgeDirection.EAST) || d.equals(ForgeDirection.WEST)) {

            if (t.getTank(ForgeDirection.WEST).getClientFluidAmount() > 0) {
                GL11.glPushMatrix();
                this.setFluidBounds(0.00D, 0.3125D, 0.3125D, 0.6875D, t.getTank(ForgeDirection.WEST));
                this.setFluidColor(t.getTank(ForgeDirection.WEST));
                this.renderFluid(block, t.getTank(ForgeDirection.WEST).getClientFluid().getFluid().getIcon(t.getTank(ForgeDirection.WEST).getFluid()));
                GL11.glPopMatrix();
            }

            if (t.getTank(ForgeDirection.EAST).getClientFluidAmount() > 0) {
                GL11.glPushMatrix();
                this.setFluidBounds(0.6875D, 0.3125D, 1.00D, 0.6875D, t.getTank(ForgeDirection.EAST));
                this.setFluidColor(t.getTank(ForgeDirection.EAST));
                this.renderFluid(block, t.getTank(ForgeDirection.EAST).getClientFluid().getFluid().getIcon(t.getTank(ForgeDirection.EAST).getFluid()));
                GL11.glPopMatrix();
            }

        }

        GL11.glPopMatrix();

    }

    public void setFluidColor(FluidTankDirection tank) {
        this.setColor3ub(tank.getClientFluid().getFluid().getColor());
    }

    public void setFluidBounds(double minX, double minZ, double maxX, double maxZ, FluidTankDirection tank) {

        RenderBlocks renderer = RenderBlocks.getInstance();
        renderer.setRenderBounds(minX, 0.3125D, minZ, maxX, 0.3125D + (0.0625) * (6.0f * tank.getClientFluidAmount() / tank.getCapacity()), maxZ);

    }

    public void renderFluid(Block block, IIcon fIcon) {

        Tessellator tessellator = Tessellator.instance;

        RenderBlocks renderer = RenderBlocks.getInstance();

        IIcon icon = renderer.getIconSafe(fIcon);

        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, -1.0F, 0.0F);
        renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, icon);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, icon);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, -1.0F);
        renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, icon);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, icon);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(-1.0F, 0.0F, 0.0F);
        renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, icon);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, icon);
        tessellator.draw();

    }

    public boolean canPlaceTorchOnTop(Block block) {
        return block == Blocks.fence || block == Blocks.nether_brick_fence || block == Blocks.glass || block == Blocks.cobblestone_wall;
    }

    public static void setColor3ub(int color) {

        GL11.glColor3ub((byte) (color >> 16 & 0xFF), (byte) (color >> 8 & 0xFF), (byte) (color & 0xFF));
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
