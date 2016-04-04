package shift.sextiarysector.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import shift.sextiarysector.plugin.PluginIC2;
import shift.sextiarysector.tileentity.TileEntityElectricMotor;

public class BlockElectricMotor extends BlockEnergyMotor {

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityElectricMotor();
    }

    @Override
    public int getRenderType() {
        return PluginIC2.electricMotorType;
    }

}
