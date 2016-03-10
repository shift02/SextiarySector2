/*
* 作成者: Shift02
* 作成日: 2016/03/10 - 13:42:38
*/
package shift.sextiarysector.block;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.tileentity.TileEntityDirection;
import shift.sextiarysector.tileentity.TileEntityHalfGutter;

public class BlockHalfGutter extends BlockContainer {

    private String itemIconName;

    public BlockHalfGutter() {
        super(Material.wood);
        this.setHardness(0.8F);
        this.setCreativeTab(SextiarySectorAPI.TabSSCore);
    }

    @Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4) {
        super.onBlockAdded(par1World, par2, par3, par4);

    }

    private void setDefaultDirection(World par1World, int par2, int par3, int par4) {

        if (!par1World.isRemote) {

            Block block = par1World.getBlock(par2, par3, par4 - 1);
            Block block1 = par1World.getBlock(par2, par3, par4 + 1);
            Block block2 = par1World.getBlock(par2 - 1, par3, par4);
            Block block3 = par1World.getBlock(par2 + 1, par3, par4);

            TileEntityDirection tileEntity = (TileEntityDirection) par1World.getTileEntity(par2, par3, par4);

            byte b0 = 3;

            if (block.func_149730_j() && !block1.func_149730_j()) {
                b0 = 3;
            }

            if (block1.func_149730_j() && !block.func_149730_j()) {
                b0 = 2;
            }

            if (block2.func_149730_j() && !block3.func_149730_j()) {
                b0 = 5;
            }

            if (block3.func_149730_j() && !block2.func_149730_j()) {
                b0 = 4;
            }

            //par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 2);
            tileEntity.direction = ForgeDirection.getOrientation(b0).getOpposite();

        }

    }

    @Override
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
        int l = MathHelper.floor_double(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
        TileEntityDirection tileEntity = (TileEntityDirection) par1World.getTileEntity(par2, par3, par4);

        if (l == 0) {
            tileEntity.direction = ForgeDirection.getOrientation(2).getOpposite();
        }

        if (l == 1) {
            tileEntity.direction = ForgeDirection.getOrientation(5).getOpposite();
        }

        if (l == 2) {
            tileEntity.direction = ForgeDirection.getOrientation(3).getOpposite();
        }

        if (l == 3) {
            tileEntity.direction = ForgeDirection.getOrientation(4).getOpposite();
        }

    }

    //ブロック更新時のあたり判定
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {

        double[] i = getBoxFromPool(par1IBlockAccess.getTileEntity(par2, par3, par4).getWorldObj(), par2, par3, par4);

        float minX = (float) i[0];
        float minY = (float) i[1];
        float minZ = (float) i[2];
        float maxX = (float) i[3];
        float maxY = (float) i[4];
        float maxZ = (float) i[5];

        this.setBlockBounds((0.0F + minX), (0.0F + minY), (0.0F + minZ), (0.0F + maxX), (0.0F + maxY), (0.0F + maxZ));
    }

    //当たり判定。サボテンやソウルサンドを参考にすると良い。ココの設定をすると、onEntityCollidedWithBlockが呼ばれるようになる
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
        double[] i = getBoxFromPool(par1World, par2, par3, par4);

        double minX = i[0];
        double minY = i[1];
        double minZ = i[2];
        double maxX = i[3];
        double maxY = i[4];
        double maxZ = i[5];

        return AxisAlignedBB.getBoundingBox(par2 + minX, par3 + minY, par4 + minZ, par2 + maxX, par3 + maxY, par4 + maxZ);
    }

    //ブロックに視点を合わせた時に出てくる黒い線のアレ
    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {

        double[] i = getBoxFromPool(par1World, par2, par3, par4);

        double minX = i[0];
        double minY = i[1];
        double minZ = i[2];
        double maxX = i[3];
        double maxY = i[4];
        double maxZ = i[5];
        GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.4F);

        return AxisAlignedBB.getBoundingBox(par2 + minX, par3 + minY, par4 + minZ, par2 + maxX, par3 + maxY, par4 + maxZ);
    }

    public double[] getBoxFromPool(World par1World, int par2, int par3, int par4) {
        double[] i = new double[6];

        i[0] = 0;//x
        i[1] = 0.25D;//y
        i[2] = 0;//z
        i[3] = 1;//x
        i[4] = 0.75D;//y
        i[5] = 1;//z

        if (!(par1World.getTileEntity(par2, par3, par4) instanceof TileEntityDirection)) {
            i[0] = 0.25f;
            i[1] = 0.25f;
            i[2] = 0.25f;
            return i;
        }

        TileEntityDirection tileEntity = (TileEntityDirection) par1World.getTileEntity(par2, par3, par4);

        if (tileEntity == null) {
            return i;
        }

        ForgeDirection d = tileEntity.getDirection();

        if (d.equals(ForgeDirection.NORTH)) {

            i[0] = 0.25D;
            i[3] = 0.75D;
            i[5] = 0.3125D;

        } else if (d.equals(ForgeDirection.SOUTH)) {

            i[0] = 0.25D;
            i[3] = 0.75D;
            i[2] = 0.6875D;

        } else if (d.equals(ForgeDirection.EAST)) {

            i[2] = 0.25D;
            i[5] = 0.75D;
            i[0] = 0.6875D;

        } else if (d.equals(ForgeDirection.WEST)) {

            i[2] = 0.25D;
            i[5] = 0.75D;
            i[3] = 0.3125D;

        }

        return i;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getItemIconName() {
        return this.itemIconName;
    }

    public Block setBlockItemTextureName(String p_149658_1_) {
        this.itemIconName = p_149658_1_;
        return this;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public int getRenderType() {
        return SextiarySector.proxy.halfGutterType;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityHalfGutter();
    }
}
