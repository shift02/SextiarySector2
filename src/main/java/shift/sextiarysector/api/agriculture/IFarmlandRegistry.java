package shift.sextiarysector.api.agriculture;

import net.minecraft.block.Block;

public interface IFarmlandRegistry {

	public void registerFarmland(String name, Block block);

	public Block getFarmland(String name);

}
