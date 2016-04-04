/*
* 作成者: Shift02
* 作成日: 2016/03/07 - 15:19:01
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
import shift.sextiarysector.tileentity.TileEntityGutter;

public class BlockGutter extends BlockContainer {

    private String itemIconName;

    public BlockGutter() {
        super(Material.wood);
        this.setHardness(0.8F);
        this.setCreativeTab(SextiarySectorAPI.TabSSCore);
    }

    //    @Override
    //    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
    //
    //        TileEntityGutter t = (TileEntityGutter) world.getTileEntity(x, y, z);
    //
    //        if (world.isRemote) return false;
    //
    //        System.out.println("EAST +x: " + t.getTank(ForgeDirection.EAST).getFluidAmount());
    //        System.out.println("WEST -x: " + t.getTank(ForgeDirection.WEST).getFluidAmount());
    //        System.out.println("SOUTH: +Z " + t.getTank(ForgeDirection.SOUTH).getFluidAmount());
    //        System.out.println("NORTH: -Z " + t.getTank(ForgeDirection.NORTH).getFluidAmount());
    //        System.out.println("UNKNOWN: " + t.getTank(ForgeDirection.UNKNOWN).getFluidAmount());
    //
    //        System.out.println("WEST: " + t.getTank(ForgeDirection.WEST).getFluidDirection());
    //        System.out.println("UNKNOWN: " + t.getTank(ForgeDirection.UNKNOWN).getFluidDirection());
    //        System.out.println("EAST: " + t.getTank(ForgeDirection.EAST).getFluidDirection());
    //
    //        return false;
    //    }

    //ブロック更新時のあたり判定
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {

        double[] i = getBoxFromPool(par1IBlockAccess.getTileEntity(par2, par3, par4).getWorldObj(), par2, par3, par4);

        float x = (float) i[0];
        float y = (float) i[1];
        float z = (float) i[2];

        this.setBlockBounds((0.0F + x), (0.0F + y), (0.0F + z), (1.0F - x), (1.0F - y), (1.0F - z));
    }

    //当たり判定。サボテンやソウルサンドを参考にすると良い。ココの設定をすると、onEntityCollidedWithBlockが呼ばれるようになる
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
        double[] i = getBoxFromPool(par1World, par2, par3, par4);

        double x = i[0];
        double y = i[1];
        double z = i[2];

        return AxisAlignedBB.getBoundingBox(par2 + x, par3 + y, par4 + z, (par2 + 1) - x, (par3 + 1) - y, (par4 + 1) - z);
    }

    //ブロックに視点を合わせた時に出てくる黒い線のアレ
    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
        double[] i = getBoxFromPool(par1World, par2, par3, par4);

        double x = i[0];
        double y = i[1];
        double z = i[2];
        GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.4F);

        return AxisAlignedBB.getBoundingBox(par2 + x, par3 + y, par4 + z, (par2 + 1) - x, (par3 + 1) - y, (par4 + 1) - z);
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z) {

        TileEntityGutter tileEntity = (TileEntityGutter) world.getTileEntity(x, y, z);

        if (tileEntity.getTank(ForgeDirection.UNKNOWN).getFluidAmount() == 0) return 0;

        try {

            return tileEntity.getTank(ForgeDirection.UNKNOWN).getFluid().getFluid().getLuminosity((World) world, x, y, z);

        } catch (Exception e) {

            try {

                return tileEntity.getTank(ForgeDirection.UNKNOWN).getFluid().getFluid().getLuminosity();

            } catch (Exception e2) {
            }

        }

        return 0;

    }

    public double[] getBoxFromPool(World par1World, int par2, int par3, int par4) {
        double[] i = new double[3];

        i[0] = 0;//x
        i[1] = 0;//y
        i[2] = 0;//z

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

        if (d.equals(ForgeDirection.NORTH) || d.equals(ForgeDirection.SOUTH)) {
            i[0] = 0.25;
            i[1] = 0.25;
        }
        if (d.equals(ForgeDirection.WEST) || d.equals(ForgeDirection.EAST)) {
            i[1] = 0.25;
            i[2] = 0.25;
        }

        return i;
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

            par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 2);
            tileEntity.direction = ForgeDirection.getOrientation(b0);

        }

    }

    @Override
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
        int l = MathHelper.floor_double(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
        TileEntityDirection tileEntity = (TileEntityDirection) par1World.getTileEntity(par2, par3, par4);

        if (l == 0) {
            tileEntity.direction = ForgeDirection.getOrientation(2);
        }

        if (l == 1) {
            tileEntity.direction = ForgeDirection.getOrientation(5);
        }

        if (l == 2) {
            tileEntity.direction = ForgeDirection.getOrientation(3);
        }

        if (l == 3) {
            tileEntity.direction = ForgeDirection.getOrientation(4);
        }

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
        return SextiarySector.proxy.gutterType;
    }
    //
    //    @Override
    //    public boolean rotateBlock(World worldObj, int x, int y, int z, ForgeDirection axis) {
    //
    //        if (worldObj.isRemote) {
    //            return false;
    //        }
    //
    //        TileEntityDirection tileEntity = (TileEntityDirection) worldObj.getTileEntity(x, y, z);
    //
    //        if (axis == UP || axis == DOWN) {
    //
    //            ForgeDirection d = tileEntity.getDirection().getRotation(axis);
    //            tileEntity.direction = d;
    //            worldObj.ma
    //
    //
    //            return true;
    //
    //        } else {
    //            return false;
    //        }
    //
    //    }
    //
    //    @Override
    //    public ForgeDirection[] getValidRotations(World worldObj, int x, int y, int z) {
    //        return new ForgeDirection[] { UP, DOWN };
    //    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityGutter();
    }

}
