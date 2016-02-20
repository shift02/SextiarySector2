package shift.sextiarysector.block;

import net.minecraft.block.BlockChest;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.tileentity.TileEntitySSChest;

public class BlockSSChest extends BlockChest {

    public BlockSSChest(int p_i45397_1_) {
        super(p_i45397_1_);
    }

    public int getRenderType() {
        return SextiarySector.proxy.chestType;
    }

    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        TileEntitySSChest tileentitychest = new TileEntitySSChest();
        return tileentitychest;
    }

}
