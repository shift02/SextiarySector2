package shift.sextiarysector.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.tileentity.TileEntityPipe;

public class BlockPipe extends BlockContainer {

	public BlockPipe() {
		super(Material.iron);
		this.setHardness(0.8F);
		//this.setBlockBounds(0.0625f, 0.0f, 0.0625f, 0.9375f, 0.875f, 0.9375f);
		this.setCreativeTab(SextiarySectorAPI.TabSSCore);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityPipe();
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public int getRenderType()
	{
		return SextiarySector.proxy.pipeType;
	}
}
