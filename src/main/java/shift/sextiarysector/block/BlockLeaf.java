package shift.sextiarysector.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockLeaf extends Block {

	public BlockLeaf() {
		super(Material.leaves);
		this.setHardness(0.2F);
		this.setLightOpacity(1);
		this.setStepSound(soundTypeGrass);
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

}
