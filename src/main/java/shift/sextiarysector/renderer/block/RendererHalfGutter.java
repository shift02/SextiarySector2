/*
* 作成者: Shift02
* 作成日: 2016/03/10 - 13:41:06
*/
package shift.sextiarysector.renderer.block;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.tileentity.TileEntityGutter.FluidTankDirection;
import shift.sextiarysector.tileentity.TileEntityHalfGutter;

public class RendererHalfGutter extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler {

    public static final ResourceLocation MC_BLOCK_SHEET = new ResourceLocation("textures/atlas/blocks.png");

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {

        if (this.getRenderId() != modelId) return false;

        TileEntityHalfGutter t = (TileEntityHalfGutter) world.getTileEntity(x, y, z);
        ForgeDirection d = t.getDirection();

        renderer.renderAllFaces = true;

        //木
        if (d.equals(ForgeDirection.NORTH)) {
            this.renderWorldBlockFromNORTH(world, x, y, z, block, modelId, renderer);
        } else if (d.equals(ForgeDirection.SOUTH)) {
            this.renderWorldBlockFromSOUTH(world, x, y, z, block, modelId, renderer);
        } else if (d.equals(ForgeDirection.EAST)) {
            this.renderWorldBlockFromEAST(world, x, y, z, block, modelId, renderer);
        } else if (d.equals(ForgeDirection.WEST)) {
            this.renderWorldBlockFromWEST(world, x, y, z, block, modelId, renderer);
        }

        renderer.renderAllFaces = false;

        return true;
    }

    //-z
    public void renderWorldBlockFromNORTH(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {

        //底
        renderer.setRenderBounds(0.25D, 0.25D, 0.0D, 0.75, 0.3125D, 0.3125D);
        renderer.renderStandardBlock(block, x, y, z);

        //サイド
        renderer.setRenderBounds(0.25D, 0.3125D, 0.0D, 0.3125D, 0.75D, 0.3125D);
        renderer.renderStandardBlock(block, x, y, z);

        renderer.setRenderBounds(0.6875D, 0.3125D, 0.0D, 0.75D, 0.75D, 0.3125D);
        renderer.renderStandardBlock(block, x, y, z);

    }

    //+z
    public void renderWorldBlockFromSOUTH(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {

        //底
        renderer.setRenderBounds(0.25D, 0.25D, 0.6875D, 0.75, 0.3125D, 1.0D);
        renderer.renderStandardBlock(block, x, y, z);

        //サイド
        renderer.setRenderBounds(0.25D, 0.3125D, 0.6875D, 0.3125D, 0.75D, 1.0D);
        renderer.renderStandardBlock(block, x, y, z);

        renderer.setRenderBounds(0.6875D, 0.3125D, 0.6875D, 0.75D, 0.75D, 1.0D);
        renderer.renderStandardBlock(block, x, y, z);

    }

    //+x
    public void renderWorldBlockFromEAST(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {

        //底
        renderer.setRenderBounds(0.6875D, 0.25D, 0.25D, 1.00D, 0.3125, 0.75D);
        renderer.renderStandardBlock(block, x, y, z);

        //サイド
        renderer.setRenderBounds(0.6875D, 0.3125D, 0.25D, 1.0D, 0.75D, 0.3125D);
        renderer.renderStandardBlock(block, x, y, z);

        renderer.setRenderBounds(0.6875D, 0.3125D, 0.6875D, 1.0D, 0.75D, 0.75D);
        renderer.renderStandardBlock(block, x, y, z);

    }

    //-x
    public void renderWorldBlockFromWEST(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {

        //底
        renderer.setRenderBounds(0.0D, 0.25D, 0.25D, 0.3125D, 0.3125, 0.75D);
        renderer.renderStandardBlock(block, x, y, z);

        //サイド
        renderer.setRenderBounds(0.0D, 0.3125D, 0.25D, 0.3125D, 0.75D, 0.3125D);
        renderer.renderStandardBlock(block, x, y, z);

        renderer.setRenderBounds(0.0D, 0.3125D, 0.6875D, 0.3125D, 0.75D, 0.75D);
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

        TileEntityHalfGutter t = (TileEntityHalfGutter) tileEntity;

        ForgeDirection d = t.getDirection();

        //水真ん中
        if (t.getTank(ForgeDirection.UNKNOWN).getClientFluidAmount() > 0) {

            GL11.glPushMatrix();
            renderer.setRenderBounds(
                    0.3125D, 0.3120D + (0.0625) * (6.0f * t.getTank(ForgeDirection.UNKNOWN).getClientFluidAmount() / t.getTank(ForgeDirection.UNKNOWN).getCapacity()), 0.3125D,
                    0.6875D, 0.3125D + (0.0625) * (6.0f * t.getTank(ForgeDirection.UNKNOWN).getClientFluidAmount() / t.getTank(ForgeDirection.UNKNOWN).getCapacity()), 0.6875D);
            this.setFluidColor(t.getTank(ForgeDirection.UNKNOWN));
            this.renderFluid(block, t.getTank(ForgeDirection.UNKNOWN).getClientFluid().getFluid().getIcon(t.getTank(ForgeDirection.UNKNOWN).getFluid()));
            GL11.glPopMatrix();

            GL11.glPushMatrix();
            renderer.setRenderBounds(
                    0.3125D, 0.000D, 0.3125D,
                    0.6875D, 0.3120D + (0.0625) * (6.0f * t.getTank(ForgeDirection.UNKNOWN).getClientFluidAmount() / t.getTank(ForgeDirection.UNKNOWN).getCapacity()), 0.6875D);
            this.setFluidColor(t.getTank(ForgeDirection.UNKNOWN));
            this.renderFluid(block, t.getTank(ForgeDirection.UNKNOWN).getClientFluid().getFluid().getFlowingIcon());
            GL11.glPopMatrix();

            for (int i = 1; i < t.getDownClient(); i++) {
                GL11.glPushMatrix();
                GL11.glTranslatef(0.0f, -1 * i, 0.0f);
                //System.out.println("AA");
                renderer.setRenderBounds(
                        0.3125D, 0.000D, 0.3125D,
                        0.6875D, 1.000D, 0.6875D);
                this.setFluidColor(t.getTank(ForgeDirection.UNKNOWN));
                this.renderFluid(block, t.getTank(ForgeDirection.UNKNOWN).getClientFluid().getFluid().getFlowingIcon());
                GL11.glPopMatrix();
            }

        }

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

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        // TODO 自動生成されたメソッド・スタブ
        return false;
    }

    public static void setColor3ub(int color) {

        GL11.glColor3ub((byte) (color >> 16 & 0xFF), (byte) (color >> 8 & 0xFF), (byte) (color & 0xFF));
    }

    @Override
    public int getRenderId() {
        return SextiarySector.proxy.halfGutterType;
    }

}
