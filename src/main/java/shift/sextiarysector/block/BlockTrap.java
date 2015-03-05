package shift.sextiarysector.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import shift.sextiarysector.tileentity.TileEntityTrap;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTrap extends BlockContainer {

	public static double one = 1.0d / 64.0d;
	private IIcon grass;

	public BlockTrap() {
		super(Material.wood);
		this.setTickRandomly(true);
	}

	@Override
	public void updateTick(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_)
	{
		if (p_149674_1_.getBlockMetadata(p_149674_2_, p_149674_3_, p_149674_4_) == 0) return;

		p_149674_1_.scheduleBlockUpdate(p_149674_2_, p_149674_3_, p_149674_4_, this, this.tickRate(p_149674_1_) + p_149674_5_.nextInt(100));

		//ModuleTrap.spawnTrap(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_);
	}

	@Override
	public int tickRate(World p_149738_1_)
	{
		return 200;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2)
	{

		if (par2 == 1) {
			return this.grass;
		}

		return this.blockIcon;

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		super.registerBlockIcons(par1IconRegister);
		this.grass = par1IconRegister.registerIcon("sextiarysector:trap");
	}

	@Override
	public void addCollisionBoxesToList(World p_149743_1_, int x, int y, int z, AxisAlignedBB p_149743_5_, List p_149743_6_, Entity p_149743_7_)
	{
		AxisAlignedBB axisalignedbb1 = AxisAlignedBB.getBoundingBox(x + this.minX, y + this.minY, z + this.minZ, x + this.maxX, y + this.one, z + this.maxZ);

		if (axisalignedbb1 != null && p_149743_5_.intersectsWith(axisalignedbb1))
		{
			p_149743_6_.add(axisalignedbb1);
		}

		AxisAlignedBB axisalignedbb2 = AxisAlignedBB.getBoundingBox(x + this.minX, y + this.maxY - this.one, z + this.minZ, x + this.maxX, y + this.maxY, z + this.maxZ);

		if (axisalignedbb2 != null && p_149743_5_.intersectsWith(axisalignedbb2))
		{
			p_149743_6_.add(axisalignedbb2);
		}

		AxisAlignedBB axisalignedbb3 = AxisAlignedBB.getBoundingBox(x + this.minX, y + this.minY, z + this.minZ, x + this.one, y + this.maxY, z + this.maxZ);

		if (axisalignedbb3 != null && p_149743_5_.intersectsWith(axisalignedbb3))
		{
			p_149743_6_.add(axisalignedbb3);
		}

		AxisAlignedBB axisalignedbb4 = AxisAlignedBB.getBoundingBox(x + this.maxX - this.one, y + this.minY, z + this.minZ, x + this.maxX, y + this.maxY, z + this.maxZ);

		if (axisalignedbb4 != null && p_149743_5_.intersectsWith(axisalignedbb4))
		{
			p_149743_6_.add(axisalignedbb4);
		}

		AxisAlignedBB axisalignedbb5 = AxisAlignedBB.getBoundingBox(x + this.minX, y + this.minY, z + this.minZ, x + this.maxX, y + this.maxY, z + this.one);

		if (axisalignedbb5 != null && p_149743_5_.intersectsWith(axisalignedbb5))
		{
			p_149743_6_.add(axisalignedbb5);
		}

		AxisAlignedBB axisalignedbb6 = AxisAlignedBB.getBoundingBox(x + this.minX, y + this.minY, z + this.maxZ - this.one, x + this.maxX, y + this.maxY, z + this.maxZ);

		if (axisalignedbb6 != null && p_149743_5_.intersectsWith(axisalignedbb6))
		{
			p_149743_6_.add(axisalignedbb6);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass()
	{
		return 0;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_) {

		p_149666_3_.add(new ItemStack(p_149666_1_, 1, 0));
		p_149666_3_.add(new ItemStack(p_149666_1_, 1, 1));

	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityTrap();
	}

}
