package shift.sextiarysector.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import shift.sextiarysector.api.SextiarySectorAPI;

public class BlockOreBlock extends Block {

	public BlockOreBlock() {
		super(Material.iron);
		this.setHardness(5.0F);
		this.setResistance(10.0F);
		this.setStepSound(soundTypeMetal);
		this.setCreativeTab(SextiarySectorAPI.TabSSMining);
	}

}
