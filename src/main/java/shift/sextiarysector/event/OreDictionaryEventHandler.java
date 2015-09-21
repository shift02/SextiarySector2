package shift.sextiarysector.event;

import java.util.Locale;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.OreDictionary.OreRegisterEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class OreDictionaryEventHandler {

    @SubscribeEvent
    public void onOreRegisterEvent(OreRegisterEvent event) {

        String ore = event.Name;
        ore = ore.toLowerCase(Locale.ENGLISH);
        ItemStack item = event.Ore;

        if (ore.matches(".*salt.*") && !ore.matches(".*condiment.*")) {

            OreDictionary.registerOre("condimentSalt", item);

        }

        if (ore.matches(".water.*") && !ore.matches(".*fluid.*")) {

            OreDictionary.registerOre("fluidWater", item);

        }

    }

}
