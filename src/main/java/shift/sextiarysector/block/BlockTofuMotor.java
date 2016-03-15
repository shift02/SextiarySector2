package shift.sextiarysector.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import shift.sextiarysector.plugin.PluginTofu;
import shift.sextiarysector.tileentity.TileEntityTofuMotor;

public class BlockTofuMotor extends BlockEnergyMotor {

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityTofuMotor();
    }

    @Override
    public int getRenderType() {
        return PluginTofu.tofuMotorType;
    }
}
