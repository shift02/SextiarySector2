/*
* 作成者: Shift02
* 作成日: 2016/03/28 - 11:21:28
*/
package shift.sextiarysector.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockOil extends Block {

    public BlockOil() {
        super(Material.clay);
    }

    @Override
    public boolean isFireSource(World world, int x, int y, int z, ForgeDirection side) {
        return true;
    }

}
