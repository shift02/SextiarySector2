package shift.sextiarysector.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import shift.sextiarysector.SSMaterials;
import shift.sextiarysector.plugin.PluginRF;
import shift.sextiarysector.tileentity.TileEntityGFDynamo;

public class BlockGFDynamo extends BlockDirection {

	public BlockGFDynamo() {
		super(SSMaterials.machine);
		this.setHardness(3.0F);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityGFDynamo();
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
		return PluginRF.gfDynamoType;
	}

}
