package shift.sextiarysector.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import shift.sextiarysector.SextiarySector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockLeafBed extends Block {

	@SideOnly(Side.CLIENT)
	protected IIcon[] blockIcons;

	public BlockLeafBed() {
		super(Material.wood);
		//this.setLightOpacity(255);
		this.setLightOpacity(1);
		this.setStepSound(soundTypeGrass);
		this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 4.0f / 16.0f, 1.0f);
		this.setHardness(1.2f);
		this.setStepSound(soundTypeWood);
		this.useNeighborBrightness = true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister p_149651_1_)
	{
		this.blockIcon = p_149651_1_.registerIcon(this.getTextureName() + "_0");

		blockIcons = new IIcon[3];

		for (int i = 0; i < blockIcons.length; i++) {
			blockIcons[i] = p_149651_1_.registerIcon(this.getTextureName() + "_" + i);
		}

	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int p_149691_1_, int p_149691_2_)
	{
		return this.blockIcons[p_149691_2_];
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
		return SextiarySector.proxy.leafBedType;
	}

}
