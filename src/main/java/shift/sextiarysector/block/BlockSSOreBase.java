package shift.sextiarysector.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.api.SextiarySectorAPI;

public class BlockSSOreBase extends Block {

	public IIcon stone;

	public BlockSSOreBase(int level) {
		super(Material.rock);
		this.setHarvestLevel("pickaxe", level);
		this.setStepSound(Block.soundTypeStone);
		this.setCreativeTab(SextiarySectorAPI.TabSSMining);
		this.useNeighborBrightness = true;
		this.setLightOpacity(255);
	}

	@Override
	public int getRenderType()
	{
		return SextiarySector.proxy.oreStoneType;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public boolean isNormalCube(IBlockAccess world, int x, int y, int z)
	{
		return true;//getMaterial().isOpaque() && renderAsNormalBlock() && !canProvidePower();
	}

	/*
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int p_149691_1_, int p_149691_2_)
	{
		if (p_149691_2_ == 8) {
			return this.blockIcon;
		}

		return this.stone;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister p_149651_1_)
	{
		this.blockIcon = p_149651_1_.registerIcon(this.getTextureName());
		this.stone = p_149651_1_.registerIcon("stone");
	}*/

}
