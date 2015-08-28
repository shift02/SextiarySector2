package shift.sextiarysector.block;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.module.FertilizerManager;
import shift.sextiarysector.tileentity.TileEntityPaddy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPaddy extends BlockContainer {

	public BlockPaddy() {
		super(Material.grass);
		this.setHardness(0.4f);
		this.setLightOpacity(255);
		this.setStepSound(soundTypeGrass);
		this.useNeighborBrightness = true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int p_149691_1_, int p_149691_2_)
	{
		return Blocks.dirt.getIcon(p_149691_1_, p_149691_2_);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass()
	{
		return 1;
	}

	@Override
	public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
	{

		if (FertilizerManager.getFertilizer(p_149727_5_.getCurrentEquippedItem()) != null && par1World.isAirBlock(x, y + 1, z)) {

			return this.setFertilizer(par1World, x, y, z, p_149727_5_);

		}

		return false;

	}

	private boolean setFertilizer(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer) {

		TileEntityPaddy t = (TileEntityPaddy) par1World.getTileEntity(x, y, z);

		if (t.getFertilizer() != null) {
			return false;
		}

		t.setFertilizer(FertilizerManager.getFertilizer(par5EntityPlayer.getCurrentEquippedItem()).getFertilizer());

		if (!par5EntityPlayer.capabilities.isCreativeMode && !par1World.isRemote)
		{
			--par5EntityPlayer.getCurrentEquippedItem().stackSize;
		}

		par1World.markBlockForUpdate(x, y, z);

		return true;

	}

	//当たり判定
	@Override
	public void addCollisionBoxesToList(World p_149743_1_, int p_149743_2_, int p_149743_3_, int p_149743_4_, AxisAlignedBB p_149743_5_, List p_149743_6_, Entity p_149743_7_)
	{
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.625f, 1.0F);
		super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
		float f = 0.125F;

		if (!this.isSame(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, ForgeDirection.getOrientation(4))) {
			this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
			super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
		}

		if (!this.isSame(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, ForgeDirection.getOrientation(2))) {
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
			super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
		}

		if (!this.isSame(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, ForgeDirection.getOrientation(5))) {
			this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
		}

		if (!this.isSame(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, ForgeDirection.getOrientation(3))) {
			this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
			super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
		}

		this.setBlockBoundsForItemRender();

	}

	private boolean isSame(World p_149743_1_, int p_149743_2_, int p_149743_3_, int p_149743_4_, ForgeDirection d) {

		if (p_149743_1_.getBlock(p_149743_2_ + d.offsetX, p_149743_3_ + d.offsetY, p_149743_4_ + d.offsetZ) == this) return true;

		return false;
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
	{
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

		ret.add(new ItemStack(Blocks.dirt, 1));

		return ret;
	}

	//ブロックの線
	@Override
	public void setBlockBoundsForItemRender()
	{
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public int getRenderType()
	{
		return SextiarySector.proxy.paddyType;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityPaddy();
	}

}
