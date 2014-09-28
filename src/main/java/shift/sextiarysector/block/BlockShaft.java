package shift.sextiarysector.block;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.tileentity.TileEntityShaft;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockShaft  extends BlockDirection{


	public BlockShaft() {
		super(Material.wood);
		this.setHardness(0.8F);
		this.setCreativeTab(SextiarySectorAPI.TabSSMachine);
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
	public int getRenderType()
    {
    	return SextiarySector.proxy.ShaftRenderType;
    }

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{

		double[] i = getBoxFromPool(par1IBlockAccess.getTileEntity(par2, par3, par4).getWorldObj(), par2, par3, par4);

		float x = (float)i[0] ;
		float y = (float)i[1] ;
		float z = (float)i[2] ;

		this.setBlockBounds((0.0F+x),(0.0F+y), (0.0F+z), (1.0F-x), (1.0F-y), (1.0F-z));
	}

	//当たり判定。サボテンやソウルサンドを参考にすると良い。ココの設定をすると、onEntityCollidedWithBlockが呼ばれるようになる
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		double[] i = getBoxFromPool(par1World, par2, par3, par4);

		double x = i[0] ;
		double y = i[1] ;
		double z = i[2] ;

		return AxisAlignedBB.getBoundingBox(par2+x, par3+y, par4+z, (par2 + 1)-x, (par3 + 1)-y, (par4 + 1)-z);
	}

	//ブロックに視点を合わせた時に出てくる黒い線のアレ
	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		double[] i = getBoxFromPool(par1World, par2, par3, par4);

		double x = i[0] ;
		double y = i[1] ;
		double z = i[2] ;
		GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.4F);

		return AxisAlignedBB.getBoundingBox(par2+x, par3+y, par4+z, (par2 + 1)-x, (par3 + 1)-y, (par4 + 1)-z);
	}

	public double[] getBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		double[] i = new double[3];

		i[0]=0;//x
		i[1]=0;//y
		i[2]=0;//z

		TileEntityShaft tileEntity = (TileEntityShaft)par1World.getTileEntity(par2, par3, par4);

		if(tileEntity==null){
			return i ;
		}

		ForgeDirection d = tileEntity.direction;

		if(d.ordinal()==0||d.ordinal()==1){
			i[0]=0.3125;
			i[2]=0.3125;
		}
		if(d.ordinal()==2||d.ordinal()==3){
			i[0]=0.3125;
			i[1]=0.3125;
		}
		if(d.ordinal()==4||d.ordinal()==5){
			i[1]=0.3125;
			i[2]=0.3125;
		}

		return i ;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int p_149915_2_) {
		return new TileEntityShaft();
	}


}
