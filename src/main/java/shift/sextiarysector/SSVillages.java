package shift.sextiarysector;

import net.minecraft.world.gen.structure.MapGenStructureIO;
import shift.sextiarysector.world.village.ComponentWindmill;
import shift.sextiarysector.world.village.VillageCreationHandlerWindmill;
import cpw.mods.fml.common.registry.VillagerRegistry;

public class SSVillages {

	public static void initVillages(){

		VillagerRegistry.instance().registerVillageCreationHandler(new VillageCreationHandlerWindmill());
        MapGenStructureIO.registerStructure(ComponentWindmill.class, "VISSSWM");
        MapGenStructureIO.func_143031_a(ComponentWindmill.class, "VISSSWM");

	}

}
