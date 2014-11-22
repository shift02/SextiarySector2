package shift.sextiarysector;

import static net.minecraftforge.common.BiomeDictionary.Type.*;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import shift.sextiarysector.world.biome.BiomeGenMagicDesert;
import cpw.mods.fml.common.event.FMLInitializationEvent;

public class SSWorld {

	 public static BiomeGenBase magicDesert;

    public static void init(FMLInitializationEvent event) {

    	magicDesert = (new BiomeGenMagicDesert(Config.magicDesert)).setColor(0xff0000).setBiomeName("MagicDesert");


    	BiomeDictionary.registerBiomeType(magicDesert,              HOT,      DRY,        SANDY     ,MAGICAL             );

    }

}
