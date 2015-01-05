package shift.sextiarysector;

import shift.sextiarysector.entity.EntityMineboatChest;
import cpw.mods.fml.common.registry.EntityRegistry;

public class SSEntitys {

	public static void initEntity(){

		 EntityRegistry.registerModEntity(EntityMineboatChest.class, "MineboatChest", 0, SextiarySector.instance, 250, 5, true);

	}

}
