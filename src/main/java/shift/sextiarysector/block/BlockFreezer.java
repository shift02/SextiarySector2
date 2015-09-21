package shift.sextiarysector.block;

import java.util.Random;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import shift.sextiarysector.tileentity.TileEntityFreezer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFreezer extends BlockSimpleFurnace {

    public BlockFreezer() {
        super(6);
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    @Override
    public TileEntity createNewTileEntity(World par1World, int p_149915_2_) {
        return new TileEntityFreezer();
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random p_149734_5_) {

    }
}
