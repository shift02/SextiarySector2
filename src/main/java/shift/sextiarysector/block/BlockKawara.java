/*
* 作成者: Shift02
* 作成日: 2016/03/23 - 15:58:20
*/
package shift.sextiarysector.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import shift.sextiarysector.api.SextiarySectorAPI;

public class BlockKawara extends Block {

    public BlockKawara() {
        super(Material.rock);

        this.setCreativeTab(SextiarySectorAPI.TabSSCore);
    }

}
