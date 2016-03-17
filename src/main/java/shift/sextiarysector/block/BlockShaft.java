package shift.sextiarysector.block;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.SSConfig;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.fmp.IShaft;
import shift.sextiarysector.tileentity.TileEntityShaft;

public class BlockShaft extends BlockDirection {

    private int type;

    public BlockShaft(int type) {
        super(Material.wood);
        this.setType(type);
        this.setHardness(0.8F);
        this.setCreativeTab(SextiarySectorAPI.TabSSIndustry);

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
        return SextiarySector.proxy.ShaftRenderType;
    }

    /*
    public int damageDropped(int p_149692_1_)
    {
        return p_149692_1_;
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_)
    {
    	for(int i=0;i<5;i++){
    		p_149666_3_.add(new ItemStack(p_149666_1_,1,i));
    	}
    }*/

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {

        double[] i = getBoxFromPool(par1IBlockAccess.getTileEntity(par2, par3, par4).getWorldObj(), par2, par3, par4);

        float x = (float) i[0];
        float y = (float) i[1];
        float z = (float) i[2];

        this.setBlockBounds(0.0F + x, 0.0F + y, 0.0F + z, 1.0F - x, 1.0F - y, 1.0F - z);
    }

    //当たり判定。サボテンやソウルサンドを参考にすると良い。ココの設定をすると、onEntityCollidedWithBlockが呼ばれるようになる
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
        double[] i = getBoxFromPool(par1World, par2, par3, par4);

        double x = i[0];
        double y = i[1];
        double z = i[2];

        return AxisAlignedBB.getBoundingBox(par2 + x, par3 + y, par4 + z, par2 + 1 - x, par3 + 1 - y, par4 + 1 - z);
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

        return AxisAlignedBB.getBoundingBox(par2 + x, par3 + y, par4 + z, par2 + 1 - x, par3 + 1 - y, par4 + 1 - z);
    }

    public double[] getBoxFromPool(World par1World, int par2, int par3, int par4) {
        double[] i = new double[3];

        i[0] = 0;//x
        i[1] = 0;//y
        i[2] = 0;//z

        if (!(par1World.getTileEntity(par2, par3, par4) instanceof IShaft)) {
            i[0] = 0.25f;
            i[1] = 0.25f;
            i[2] = 0.25f;
            return i;
        }

        IShaft tileEntity = (IShaft) par1World.getTileEntity(par2, par3, par4);

        if (tileEntity == null) {
            return i;
        }

        ForgeDirection d = tileEntity.getDirection();

        if (d.ordinal() == 0 || d.ordinal() == 1) {
            i[0] = 0.25;
            i[2] = 0.25;
        }
        if (d.ordinal() == 2 || d.ordinal() == 3) {
            i[0] = 0.25;
            i[1] = 0.25;
        }
        if (d.ordinal() == 4 || d.ordinal() == 5) {
            i[1] = 0.25;
            i[2] = 0.25;
        }

        return i;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int p_149915_2_) {
        return new TileEntityShaft(this.getType());
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {

        if (!SSConfig.particleShaft) return;

        TileEntityShaft tileEntity = (TileEntityShaft) par1World.getTileEntity(par2, par3, par4);

        if (tileEntity.lastSpeed != 0) {
            sparkle(par1World, par2, par3, par4);
        }
    }

    public static void sparkle(World par1World, int par2, int par3, int par4) {
        Random random = par1World.rand;
        double d0 = 0.0625D;

        for (int l = 0; l < 3; ++l) {

            double d1 = par2 + random.nextFloat() / 2.0f;
            double d2 = par3 + random.nextFloat() / 2.0f;
            double d3 = par4 + random.nextFloat() / 2.0f;

            if (l == 0 && !par1World.getBlock(par2, par3 + 1, par4).isOpaqueCube()) {
                d2 = par3 + 1 + d0;
            }

            if (l == 1 && !par1World.getBlock(par2, par3 - 1, par4).isOpaqueCube()) {
                d2 = par3 + 0 - d0;
            }

            if (l == 2 && !par1World.getBlock(par2, par3, par4 + 1).isOpaqueCube()) {
                d3 = par4 + 1 + d0;
            }

            if (l == 3 && !par1World.getBlock(par2, par3, par4 - 1).isOpaqueCube()) {
                d3 = par4 + 0 - d0;
            }

            if (l == 4 && !par1World.getBlock(par2 + 1, par3, par4).isOpaqueCube()) {
                d1 = par2 + 1 + d0;
            }

            if (l == 5 && !par1World.getBlock(par2 - 1, par3, par4).isOpaqueCube()) {
                d1 = par2 + 0 - d0;
            }

            if (d1 < par2 || d1 > par2 + 1 || d2 < 0.0D || d2 > par3 + 1 || d3 < par4 || d3 > par4 + 1) {

                par1World.spawnParticle("reddust", d1, d2, d3, -0.3D, 0.0D, 1.0D);

            }

        }
    }

}
