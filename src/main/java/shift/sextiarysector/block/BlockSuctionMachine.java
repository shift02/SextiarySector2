package shift.sextiarysector.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.tileentity.TileEntityDirection;
import shift.sextiarysector.tileentity.TileEntitySuctionMachine;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSuctionMachine extends BlockContainer {

	@SideOnly(Side.CLIENT)
	private IIcon iconTop;
	@SideOnly(Side.CLIENT)
	private IIcon iconUnder;

	public BlockSuctionMachine() {
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
			return this.iconTop;
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
			return this.iconTop;
		} else {
			return this.blockIcon;
		}

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon(this.getTextureName() + "_up");

		this.iconTop = par1IconRegister.registerIcon(this.getTextureName() + "_top");
		this.iconUnder = par1IconRegister.registerIcon(this.getTextureName());

	}

	@Override
	public TileEntity createNewTileEntity(World par1World, int p_149915_2_) {
		return new TileEntitySuctionMachine();
	}

}
