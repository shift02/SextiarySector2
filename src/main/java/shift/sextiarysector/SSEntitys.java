package shift.sextiarysector;

import shift.sextiarysector.entity.EntityMineboatChest;
import shift.sextiarysector.entity.EntityMineboatTank;
import cpw.mods.fml.common.registry.EntityRegistry;

public class SSEntitys {

	public static void initEntity() {

		EntityRegistry.registerModEntity(EntityMineboatChest.class, "MineboatChest", 0, SextiarySector.instance, 250, 5, true);
		EntityRegistry.registerModEntity(EntityMineboatTank.class, "EntityMineboatTank", 1, SextiarySector.instance, 250, 5, true);

	}

}
