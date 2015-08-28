package shift.sextiarysector.block;

import java.math.BigDecimal;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import shift.sextiarysector.SSItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBoardingBuoy extends BlockPoweredBuoy {

	//private final boolean powered;

	private IIcon[] blockIcons_a;

	public BlockBoardingBuoy() {
		super();
		//this.powered = par2;

	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z)
	{
		if (world.getBlockMetadata(x, y, z) >= 8) {
			return (int) (15.0F * 1.0f);
		}
		return 0;
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{

		if (par5EntityPlayer.getCurrentEquippedItem() != null && par5EntityPlayer.getCurrentEquippedItem().getItem() == SSItems.ironSpanner) {
			int i = par1World.getBlockMetadata(par2, par3, par4);

			if (this.ispowered(par1World, par2, par3, par4)) {

				if (i < 15) {
					i += 1;
				} else {
					i = 8;
				}

			} else {

				if (i < 7) {
					i += 1;
				} else {
					i = 0;
				}

			}

			par1World.setBlockMetadataWithNotify(par2, par3, par4, i, 1);
			par1World.markBlockForUpdate(par2, par3, par4);
			return true;
		}
		return false;

	}

	@Override
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
	{
		if (par1World.getBlockMetadata(par2, par3, par4) < 8) {
			if (par5Entity instanceof EntityBoat) {

				double mX = par5Entity.motionX;
				double mZ = par5Entity.motionZ;

				BigDecimal bdX = new BigDecimal(mX);
				BigDecimal bdZ = new BigDecimal(mZ);

				//mX = Math.ceil(mX);
				//mZ = Math.ceil(mZ);

				BigDecimal bdX2 = bdX.setScale(1, BigDecimal.ROUND_HALF_UP);
				BigDecimal bdZ2 = bdZ.setScale(1, BigDecimal.ROUND_HALF_UP);

				mX = bdX2.doubleValue();
				mZ = bdZ2.doubleValue();

				if (mX > 0) {
					mX = mX - 0.1;
				}
				if (mX < 0) {
					mX = mX + 0.1;
				}

				if (mZ > 0) {
					mZ = mZ - 0.1;
				}
				if (mZ < 0) {
					mZ = mZ + 0.1;
				}

				par5Entity.motionX = mX;
				par5Entity.motionZ = mZ;
				//par5Entity.motionY = 0;

				par5Entity.prevPosX = par2;
				//par5Entity.prevPosY = par3;
				par5Entity.prevPosZ = par4;

				return;
			}

		}

		if (par5Entity instanceof EntityBoat) {

			int x = 0;
			int z = 0;

			int y = 0;

			int i = par1World.getBlockMetadata(par2, par3, par4) - 8;

			switch (i) {

			/*
			case 0 : z = -1; break;
			case 1 : x = 1; break;
			case 2 : z = 1; break;
			case 3 : x = -1; break;

			case 4 : x = -1; z = -1 ; break;
			case 5 : x = 1;z = -1 ; break;
			case 6 : x = 1;z = 1 ; break;
			case 7 : x = -1; z = 1 ; break;
			*/

			case 0:
				z = -1;
				break;
			case 2:
				x = 1;
				break;
			case 4:
				z = 1;
				break;
			case 6:
				x = -1;
				break;

			case 1:
				x = 1;
				z = -1;
				break;
			case 3:
				x = 1;
				z = 1;
				break;
			case 5:
				x = -1;
				z = 1;
				break;
			case 7:
				x = -1;
				z = -1;
				break;

			//case 9 : y = 1;break;

			}

			par5Entity.motionX += x;
			par5Entity.motionZ += z;
			par5Entity.motionY += y;
			//par5Entity.motionY += 1;
			//par5Entity.motionZ += 1;
			//moveFlying(1, 1, 1);
			//par5Entity.setPositionAndRotation2(2,2,2,1,1,1);
		}

	}

	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4)
	{
		if (!par1World.isRemote)
		{
			if (this.ispowered(par1World, par2, par3, par4) && !par1World.isBlockIndirectlyGettingPowered(par2, par3, par4))
			{
				par1World.scheduleBlockUpdate(par2, par3, par4, this, 4);
			}
			else if (!this.ispowered(par1World, par2, par3, par4) && par1World.isBlockIndirectlyGettingPowered(par2, par3, par4))
			{
				int i = par1World.getBlockMetadata(par2, par3, par4);
				if (i <= 7) {
					par1World.setBlock(par2, par3, par4, this, i + 8, 2);
				}

			}
		}
	}

	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5)
	{
		if (!par1World.isRemote)
		{
			if (this.ispowered(par1World, par2, par3, par4) && !par1World.isBlockIndirectlyGettingPowered(par2, par3, par4))
			{
				par1World.scheduleBlockUpdate(par2, par3, par4, this, 4);
			}
			else if (!this.ispowered(par1World, par2, par3, par4) && par1World.isBlockIndirectlyGettingPowered(par2, par3, par4))
			{
				int i = par1World.getBlockMetadata(par2, par3, par4);
				if (i <= 7) {
					par1World.setBlock(par2, par3, par4, this, i + 8, 2);
				}
			}
		}
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		if (!par1World.isRemote && this.ispowered(par1World, par2, par3, par4) && !par1World.isBlockIndirectlyGettingPowered(par2, par3, par4))
		{
			int i = par1World.getBlockMetadata(par2, par3, par4);
			if (i > 7) {
				par1World.setBlock(par2, par3, par4, this, i - 8, 2);
			}
		}
	}

	public boolean ispowered(World par1World, int par2, int par3, int par4)
	{
		return par1World.getBlockMetadata(par2, par3, par4) > 7;
	}

	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
	{
		return Item.getItemFromBlock(this);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int par1, int par2)
	{
		if (par2 > 7) {
			return blockIcons_a[par2 - 8];
		}
		return blockIcons[par2];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		blockIcons = new IIcon[8];
		blockIcons_a = new IIcon[8];

		for (int i = 0; i < 8; i++) {
			blockIcons[i] = par1IconRegister.registerIcon(this.getTextureName() + "_" + i);

		}

		for (int i = 0; i < 8; i++) {
			blockIcons_a[i] = par1IconRegister.registerIcon(this.getTextureName() + "_" + i + "_active");

		}

		this.blockIcon = blockIcons[0];//par1IconRegister.registerIcon(this.getTextureName());

	}

}
