package shift.sextiarysector.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import shift.sextiarysector.tileentity.TileEntityMagicFurnace;

public class BlockMagiFurnace extends BlockSimpleFurnace {

    public BlockMagiFurnace() {
        super(90);
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    @Override
    public TileEntity createNewTileEntity(World par1World, int p_149915_2_) {
        return new TileEntityMagicFurnace();
    }

}
