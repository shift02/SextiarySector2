package shift.sextiarysector.item;

import net.minecraft.world.World;
import shift.sextiarysector.entity.EntityMineboat;
import shift.sextiarysector.entity.EntityMineboatTank;

public class ItemMineboatTank extends ItemMineboat {

	@Override
	public EntityMineboat createMineboat(World par1World, double par2, double par4, double par6) {
		return new EntityMineboatTank(par1World, par2 + 0.5F, par4 + 1.0F, par6 + 0.5F);

	}

}
