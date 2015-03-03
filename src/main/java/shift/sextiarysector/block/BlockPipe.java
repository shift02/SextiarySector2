package shift.sextiarysector.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.IFluidHandler;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.tileentity.TileEntityPipe;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPipe extends BlockContainer {

	public double one = 1.0d / 16.0d;

	public BlockPipe() {
		super(Material.iron);
		this.setHardness(0.8F);
		//this.setBlockBounds(0.0625f, 0.0f, 0.0625f, 0.9375f, 0.875f, 0.9375f);
		this.setCreativeTab(SextiarySectorAPI.TabSSCore);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityPipe();
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		float[] defaultSize = new float[] { (float) (one * 4), (float) (one * 12), (float) (one * 4), (float) (one * 12), (float) (one * 4), (float) (one * 12) };

		for (ForgeDirection d : ForgeDirection.VALID_DIRECTIONS) {

			float setX = 0.5f;
			float setY = 0.5f;
			float setZ = 0.5f;

			if (d.offsetX != 0 && this.canConnect(par1IBlockAccess, par2, par3, par4, d)) {
				defaultSize[d.ordinal()] = (float) (setX + 0.5 * d.offsetX);
			}

			if (d.offsetY != 0 && this.canConnect(par1IBlockAccess, par2, par3, par4, d)) {
				defaultSize[d.ordinal()] = (float) (setY + 0.5 * d.offsetY);
			}

			if (d.offsetZ != 0 && this.canConnect(par1IBlockAccess, par2, par3, par4, d)) {
				defaultSize[d.ordinal()] = (float) (setZ + 0.5 * d.offsetZ);
			}

		}

		this.setBlockBounds(0 + defaultSize[4], 0 + defaultSize[0], 0 + defaultSize[2], 0 + defaultSize[5], 0 + defaultSize[1], 0 + defaultSize[3]);
	}

	//当たり判定。サボテンやソウルサンドを参考にすると良い。ココの設定をすると、onEntityCollidedWithBlockが呼ばれるようになる
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{

		double[] defaultSize = new double[] { one * 4, one * 12, one * 4, one * 12, one * 4, one * 12 };

		for (ForgeDirection d : ForgeDirection.VALID_DIRECTIONS) {

			double setX = 0.5d;
			double setY = 0.5d;
			double setZ = 0.5d;

			if (d.offsetX != 0 && this.canConnect(par1World, par2, par3, par4, d)) {
				defaultSize[d.ordinal()] = setX + 0.5 * d.offsetX;
			}

			if (d.offsetY != 0 && this.canConnect(par1World, par2, par3, par4, d)) {
				defaultSize[d.ordinal()] = setY + 0.5 * d.offsetY;
			}

			if (d.offsetZ != 0 && this.canConnect(par1World, par2, par3, par4, d)) {
				defaultSize[d.ordinal()] = setZ + 0.5 * d.offsetZ;
			}

		}

		return AxisAlignedBB.getBoundingBox(par2 + defaultSize[4], par3 + defaultSize[0], par4 + defaultSize[2], par2 + defaultSize[5], par3 + defaultSize[1], par4 + defaultSize[3]);
	}

	//ブロックに視点を合わせた時に出てくる黒い線のアレ
	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{

		double[] defaultSize = new double[] { one * 4, one * 12, one * 4, one * 12, one * 4, one * 12 };

		for (ForgeDirection d : ForgeDirection.VALID_DIRECTIONS) {

			double setX = 0.5d;
			double setY = 0.5d;
			double setZ = 0.5d;

			if (d.offsetX != 0 && this.canConnect(par1World, par2, par3, par4, d)) {
				defaultSize[d.ordinal()] = setX + 0.5 * d.offsetX;
			}

			if (d.offsetY != 0 && this.canConnect(par1World, par2, par3, par4, d)) {
				defaultSize[d.ordinal()] = setY + 0.5 * d.offsetY;
			}

			if (d.offsetZ != 0 && this.canConnect(par1World, par2, par3, par4, d)) {
				defaultSize[d.ordinal()] = setZ + 0.5 * d.offsetZ;
			}

		}

		return AxisAlignedBB.getBoundingBox(par2 + defaultSize[4], par3 + defaultSize[0], par4 + defaultSize[2], par2 + defaultSize[5], par3 + defaultSize[1], par4 + defaultSize[3]);
	}

	public boolean canConnect(IBlockAccess world, int x, int y, int z, ForgeDirection d) {

		TileEntity t = world.getTileEntity(x + d.offsetX, y + d.offsetY, z + d.offsetZ);

		if (t != null && t instanceof IFluidHandler) return true;

		return false;
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
		return SextiarySector.proxy.pipeType;
	}
}
