package shift.sextiarysector.block;

import java.util.List;
import java.util.Random;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockDetectorBuoy extends BlockBuoyBase {

	@SideOnly(Side.CLIENT)
	private IIcon[] iconArray;

	private int ProvidePower;

	public BlockDetectorBuoy() {
		super();
		this.setTickRandomly(true);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public int tickRate(World par1World)
	{
		return 20;
	}

	@Override
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
	{
		if (!par1World.isRemote)
		{
			int l = par1World.getBlockMetadata(par2, par3, par4);

			if ((l & 8) == 0)
			{
				this.setStateIfMineboatInteractsWithRail(par1World, par2, par3, par4, l);
			}
		}
	}

	/*
	@Override
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
	{
		if(par5Entity instanceof EntityBoat){

			par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 1);
			System.out.println("kokodayo2");

		}


	}
	*/

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		if (!par1World.isRemote)
		{
			int l = par1World.getBlockMetadata(par2, par3, par4);

			if (l != 0)
			{
				this.setStateIfMineboatInteractsWithRail(par1World, par2, par3, par4, l);

			}
		}
	}

	private void setStateIfMineboatInteractsWithRail(World par1World, int par2, int par3, int par4, int par5)
	{
		boolean flag = (par5 & 8) != 0;
		boolean flag1 = false;
		float f = -0.125F;
		List list = par1World.getEntitiesWithinAABB(EntityBoat.class, AxisAlignedBB.getBoundingBox(par2 + f, par3, par4 + f, par2 + 1 - f, par3 + 1 - f, par4 + 1 - f));

		if (!list.isEmpty())
		{
			flag1 = true;
		}

		if (flag1 && !flag)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, par5 | 8, 3);
			par1World.notifyBlocksOfNeighborChange(par2, par3, par4, this);
			par1World.notifyBlocksOfNeighborChange(par2, par3 - 1, par4, this);
			par1World.markBlockRangeForRenderUpdate(par2, par3, par4, par2, par3, par4);
		}

		if (!flag1 && flag)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, par5 & 7, 3);
			par1World.notifyBlocksOfNeighborChange(par2, par3, par4, this);
			par1World.notifyBlocksOfNeighborChange(par2, par3 - 1, par4, this);
			par1World.markBlockRangeForRenderUpdate(par2, par3, par4, par2, par3, par4);
		}

		if (flag1)
		{
			par1World.scheduleBlockUpdate(par2, par3, par4, this, this.tickRate(par1World));
		}

		//par1World.setBlock(par2, par3, par4, this);

	}

	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4)
	{
		super.onBlockAdded(par1World, par2, par3, par4);
		this.setStateIfMineboatInteractsWithRail(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4));
	}

	@Override
	public boolean canProvidePower()
	{
		return true;
	}

	@Override
	public int isProvidingWeakPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
	{
		return (par1IBlockAccess.getBlockMetadata(par2, par3, par4) & 8) != 0 ? 15 : 0;
	}

	@Override
	public int isProvidingStrongPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
	{
		return (par1IBlockAccess.getBlockMetadata(par2, par3, par4) & 8) == 0 ? 0 : (par5 == 1 ? 15 : 0);
	}

	/*
	public int isProvidingWeakPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
	{
	    return par1IBlockAccess.getBlockMetadata(par2, par3, par4) >=1 ? 15 : 0 ;
	}

	public int isProvidingStrongPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
	{
		return par1IBlockAccess.getBlockMetadata(par2, par3, par4) >=1 ? 15 : 0 ;
	}
	*/

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		this.iconArray = new IIcon[2];
		this.iconArray[0] = par1IconRegister.registerIcon(this.textureName);
		this.iconArray[1] = par1IconRegister.registerIcon(this.textureName + "_powered");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2)
	{
		return (par2 & 8) != 0 ? this.iconArray[1] : this.iconArray[0];
	}

}
