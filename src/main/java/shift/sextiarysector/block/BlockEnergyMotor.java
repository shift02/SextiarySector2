package shift.sextiarysector.block;

import net.minecraft.block.material.Material;

public abstract class BlockEnergyMotor extends BlockDirection {

    public BlockEnergyMotor() {
        super(Material.iron);
        this.setHardness(0.8F);
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

}
