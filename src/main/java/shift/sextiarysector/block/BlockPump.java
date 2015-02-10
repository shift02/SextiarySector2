package shift.sextiarysector.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.tileentity.TileEntityDirection;
import shift.sextiarysector.tileentity.TileEntityPump;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPump extends BlockContainer {

	@SideOnly(Side.CLIENT)
	private IIcon iconTop;
	@SideOnly(Side.CLIENT)
	private IIcon iconUnder;
	@SideOnly(Side.CLIENT)
	private IIcon iconFront;

	public BlockPump() {
		super(Material.wood);
		this.setHardness(1.0F);
		this.setStepSound(soundTypeStone);
		this.setCreativeTab(SextiarySectorAPI.TabSSIndustry);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess p_149673_1_, int x, int y, int z, int side) {

		int d = ((TileEntityDirection) p_149673_1_.getTileEntity(x, y, z)).direction.ordinal();

		if (side == 1) {
			return this.iconTop;
		} else if (side == 0) {
			return this.iconUnder;
		} else if (side == d) {
			return this.iconFront;
		} else {
			return this.blockIcon;
		}

	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int par1, int par2) {

		if (par1 == 1) {
			return this.iconTop;
		} else if (par1 == 0) {
			return this.iconUnder;
		} else if (par1 == 3) {
			return this.iconFront;
		} else {
			return this.blockIcon;
		}

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon(this.getTextureName());

		this.iconTop = par1IconRegister.registerIcon(this.getTextureName()+ "_top");
		this.iconUnder = par1IconRegister.registerIcon(this.getTextureName());

		this.iconFront = par1IconRegister.registerIcon(this.getTextureName()+ "_front");

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

			TileEntityDirection tileEntity = (TileEntityDirection) par1World
					.getTileEntity(par2, par3, par4);

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

			// par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 2);
			tileEntity.direction = ForgeDirection.getOrientation(b0);
		}
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
		int l = MathHelper.floor_double(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		TileEntityDirection tileEntity = (TileEntityDirection) par1World.getTileEntity(par2, par3, par4);

		if (l == 0) {
			// par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
			tileEntity.direction = ForgeDirection.VALID_DIRECTIONS[2];
		}

		if (l == 1) {
			// par1World.setBlockMetadataWithNotify(par2, par3, par4, 5, 2);
			tileEntity.direction = ForgeDirection.VALID_DIRECTIONS[5];
		}

		if (l == 2) {
			// par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
			tileEntity.direction = ForgeDirection.VALID_DIRECTIONS[3];
		}

		if (l == 3) {
			// par1World.setBlockMetadataWithNotify(par2, par3, par4, 4, 2);
			tileEntity.direction = ForgeDirection.VALID_DIRECTIONS[4];
		}

	}

	@Override
	public TileEntity createNewTileEntity(World par1World, int p_149915_2_) {
		return new TileEntityPump();
	}

}
