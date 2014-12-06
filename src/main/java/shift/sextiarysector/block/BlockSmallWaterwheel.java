package shift.sextiarysector.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.tileentity.TileEntityDirection;
import shift.sextiarysector.tileentity.TileEntitySmallWaterwheel;

public class BlockSmallWaterwheel extends BlockContainer{

	public BlockSmallWaterwheel() {
		super(Material.wood);
		this.setHardness(0.8F);
	}

	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4)
	{
		super.onBlockAdded(par1World, par2, par3, par4);
		this.setDefaultDirection(par1World, par2, par3, par4);
	}

	private void setDefaultDirection(World par1World, int par2, int par3, int par4)
	{
		if (!par1World.isRemote)
		{
			Block block = par1World.getBlock(par2, par3, par4 - 1);
            Block block1 = par1World.getBlock(par2, par3, par4 + 1);
            Block block2 = par1World.getBlock(par2 - 1, par3, par4);
            Block block3 = par1World.getBlock(par2 + 1, par3, par4);

            byte b0 = 3;

            if (block.func_149730_j() && !block1.func_149730_j())
            {
                b0 = 3;
            }

            if (block1.func_149730_j() && !block.func_149730_j())
            {
                b0 = 2;
            }

            if (block2.func_149730_j() && !block3.func_149730_j())
            {
                b0 = 5;
            }

            if (block3.func_149730_j() && !block2.func_149730_j())
            {
                b0 = 4;
            }

			((TileEntityDirection) par1World.getTileEntity(par2, par3, par4)).direction = ForgeDirection
					.getOrientation(b0);

			//par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 2);
		}
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase,
			ItemStack par6ItemStack)
	{
		int l = MathHelper.floor_double(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

		if (l == 0)
		{
			//par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
			((TileEntityDirection) par1World.getTileEntity(par2, par3, par4)).direction = ForgeDirection
					.getOrientation(2);
		}

		if (l == 1)
		{
			//par1World.setBlockMetadataWithNotify(par2, par3, par4, 5, 2);
			((TileEntityDirection) par1World.getTileEntity(par2, par3, par4)).direction = ForgeDirection
					.getOrientation(5);

		}

		if (l == 2)
		{
			//par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
			((TileEntityDirection) par1World.getTileEntity(par2, par3, par4)).direction = ForgeDirection
					.getOrientation(3);

		}

		if (l == 3)
		{
			//par1World.setBlockMetadataWithNotify(par2, par3, par4, 4, 2);
			((TileEntityDirection) par1World.getTileEntity(par2, par3, par4)).direction = ForgeDirection
					.getOrientation(4);

		}

		par1World.markBlockForUpdate(par2, par3, par4);

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
    	return SextiarySector.proxy.smallWaterwheel;
    }

	@Override
	public TileEntity createNewTileEntity(World world, int p_149915_2_) {
		return new TileEntitySmallWaterwheel();
	}
}
