package shift.sextiarysector.block;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.SSItems;
import shift.sextiarysector.tileentity.TileEntityDirection;
import shift.sextiarysector.tileentity.TileEntityFigure;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFigure extends BlockContainer {

	// private TileEntityFigure tile;

	@SideOnly(Side.CLIENT)
	private IIcon side;

	public BlockFigure() {
		super(Material.wood);
		float f = 1.0f / 16.0f;
		this.setBlockBounds(f * 2, 0, f * 2, f * 14, f * 4, f * 14);
		this.setHardness(0.3f);
		// this.setBlockUnbreakable();
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {

		if (par1 == 0 || par1 == 1) {
			return this.blockIcon;
		} else {
			return this.side;
		}

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		super.registerBlockIcons(par1IconRegister);
		this.side = par1IconRegister.registerIcon(this.getTextureName()
				+ "_side");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getItemIconName() {
		return this.getTextureName();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_) {
		ItemStack itemstack = new ItemStack(p_149666_1_, 1, 0);

		setFigureItem(itemstack, new ItemStack(Blocks.dirt, 1, 0), "creative");

		p_149666_3_.add(itemstack);

		ItemStack itemstack2 = new ItemStack(p_149666_1_, 1, 0);

		setFigureItem(itemstack2, new ItemStack(SSItems.hammer, 1, 0), "creative");

		p_149666_3_.add(itemstack2);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityFigure();
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
			//par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
			tileEntity.direction = ForgeDirection.getOrientation(2);
		}

		if (l == 1) {
			//par1World.setBlockMetadataWithNotify(par2, par3, par4, 5, 2);
			tileEntity.direction = ForgeDirection.getOrientation(5);
		}

		if (l == 2) {
			///par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
			tileEntity.direction = ForgeDirection.getOrientation(3);
		}

		if (l == 3) {
			//par1World.setBlockMetadataWithNotify(par2, par3, par4, 4, 2);
			tileEntity.direction = ForgeDirection.getOrientation(4);
		}

	}

	@Override
	public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_,
			int p_149749_4_, Block p_149749_5_, int p_149749_6_) {
		// ItemInWorldManager a = new ItemInWorldManager();
		// if( ItemInWorldManager.isCreative());
		// tile = (TileEntityFigure) p_149749_1_.getTileEntity(p_149749_2_,
		// p_149749_3_, p_149749_4_);
		// this.dropBlockAsItem(p_149749_1_, p_149749_2_, p_149749_3_,
		// p_149749_4_, 0, 0);
		super.breakBlock(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_,
				p_149749_5_, p_149749_6_);
	}

	@Override
	public void onBlockHarvested(World p_149681_1_, int p_149681_2_, int p_149681_3_, int p_149681_4_, int p_149681_5_, EntityPlayer p_149681_6_) {
		if (!p_149681_6_.capabilities.isCreativeMode) {
			this.dropBlockAsItem(p_149681_1_, p_149681_2_, p_149681_3_, p_149681_4_, p_149681_5_, 0);
		}

		super.onBlockHarvested(p_149681_1_, p_149681_2_, p_149681_3_, p_149681_4_, p_149681_5_, p_149681_6_);
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {

		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

		TileEntity t = world.getTileEntity(x, y, z);

		if (t != null && t instanceof TileEntityFigure) {

			Item item = getItemDropped(metadata, world.rand, fortune);
			ItemStack itemstack = new ItemStack(item, 1);
			setFigureItem(itemstack, ((TileEntityFigure) t).getFigure(), ((TileEntityFigure) t).getEdition());

			ret.add(itemstack);

		}

		return ret;
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {

		ItemStack stack = new ItemStack(this, 1);

		TileEntityFigure tile = (TileEntityFigure) world.getTileEntity(x, y, z);

		if (tile.getFigure() != null) {
			setFigureItem(stack, tile.getFigure(), tile.getEdition());
		}

		return stack;
	}

	public static ItemStack setFigureItem(ItemStack item, ItemStack setItem, String edition) {

		if (setItem == null) return item;

		NBTTagCompound nbt = item.stackTagCompound;

		if (nbt == null) {
			nbt = new NBTTagCompound();
			item.setTagCompound(nbt);
		}

		NBTTagCompound nbttagcompound1 = new NBTTagCompound();
		setItem.writeToNBT(nbttagcompound1);
		nbt.setTag("figure", nbttagcompound1);
		nbt.setString("edition", edition);

		return item;
	}

	public static ItemStack getFigureItem(ItemStack item) {

		NBTTagCompound nbt = item.stackTagCompound;

		if (nbt == null) {
			return null;
		}

		if (nbt.hasKey("figure")) {
			return ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("figure"));
		}

		return null;
	}

	public static String getEdition(ItemStack item) {

		NBTTagCompound nbt = item.stackTagCompound;

		if (nbt == null) {
			return null;
		}

		if (nbt.hasKey("edition")) {
			return nbt.getString("edition");
		}

		return null;
	}

}
