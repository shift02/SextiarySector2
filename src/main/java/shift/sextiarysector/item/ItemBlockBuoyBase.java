package shift.sextiarysector.item;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;

public class ItemBlockBuoyBase extends ItemBlock {

	public ItemBlockBuoyBase(Block par1) {
		super(par1);

	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, true);

		if (movingobjectposition == null)
		{
			return par1ItemStack;
		}
		else
		{
			if (movingobjectposition.typeOfHit == MovingObjectType.BLOCK)
			{
				int i = movingobjectposition.blockX;
				int j = movingobjectposition.blockY;
				int k = movingobjectposition.blockZ;

				if (!par2World.canMineBlock(par3EntityPlayer, i, j, k))
				{
					return par1ItemStack;
				}

				if (!par3EntityPlayer.canPlayerEdit(i, j, k, movingobjectposition.sideHit, par1ItemStack))
				{
					return par1ItemStack;
				}

				if (par2World.getBlock(i, j, k).getMaterial() == Material.water && par2World.getBlockMetadata(i, j, k) == 0 && par2World.isAirBlock(i, j + 1, k))
				{

					int l = MathHelper.floor_double(par3EntityPlayer.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
					int d = 0;
					if (l == 0) {
						d = 4;
					} else if (l == 1) {
						d = 6;
					} else if (l == 2) {
						d = 0;
					} else if (l == 3) {
						d = 2;
					}

					par2World.setBlock(i, j + 1, k, this.field_150939_a);
					par2World.setBlockMetadataWithNotify(i, j + 1, k, d, 2);

					if (!par3EntityPlayer.capabilities.isCreativeMode)
					{
						--par1ItemStack.stackSize;
					}
				}
			}

			return par1ItemStack;
		}
	}

	/*
	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	{
		int i1 = par3World.getBlock(par4, par5, par6);

		if (i1 == Block.snow.blockID && (par3World.getBlockMetadata(par4, par5, par6) & 7) < 1)
		{
			par7 = 1;
		}
		else if (i1 != Block.vine.blockID && i1 != Block.tallGrass.blockID && i1 != Block.deadBush.blockID
				&& (Block.blocksList[i1] == null || !Block.blocksList[i1].isBlockReplaceable(par3World, par4, par5, par6)))
		{
			if (par7 == 0)
			{
				--par5;
			}

			if (par7 == 1)
			{
				++par5;
			}

			if (par7 == 2)
			{
				--par6;
			}

			if (par7 == 3)
			{
				++par6;
			}

			if (par7 == 4)
			{
				--par4;
			}

			if (par7 == 5)
			{
				++par4;
			}
		}

		if (par1ItemStack.stackSize == 0)
		{
			return false;
		}
		else if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack))
		{
			return false;
		}
		else if (par5 == 255 && Block.blocksList[this.blockID].blockMaterial.isSolid())
		{
			return false;
		}
		else if (par3World.canPlaceEntityOnSide(this.blockID, par4, par5, par6, false, par7, par2EntityPlayer, par1ItemStack))
		{
			Block block = Block.blocksList[this.blockID];
			int j1 = this.getMetadata(par1ItemStack.getItemDamage());
			int k1 = Block.blocksList[this.blockID].onBlockPlaced(par3World, par4, par5, par6, par7, par8, par9, par10, j1);

			if (placeBlockAt(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10, k1))
			{
				par3World.playSoundEffect(par4 + 0.5F, par5 + 0.5F, par6 + 0.5F, block.stepSound.getPlaceSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
				--par1ItemStack.stackSize;
			}

			return true;
		}
		else
		{
			return false;
		}
	}*/

}
