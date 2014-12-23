package shift.sextiarysector.block;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.tileentity.TileEntityFan;

public class BlockFan  extends BlockDirection{

	public BlockFan() {
		super(Material.iron);
		this.setBlockTextureName("planks_oak");
		this.setHardness(1.0F);
		this.setStepSound(soundTypeStone);
		this.setCreativeTab(SextiarySectorAPI.TabSSMachine);
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
    	return SextiarySector.proxy.fanType;
    }

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityFan();
	}

}
