package shift.sextiarysector;

import cpw.mods.fml.common.IFuelHandler;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SSFuels implements IFuelHandler {

    @Override
    public int getBurnTime(ItemStack fuel) {

        if (fuel == null) return 0;

        Item item = fuel.getItem();

        if (item == null) return 0;

        if (item == SSItems.leaf) return 50;
        if (item == Item.getItemFromBlock(SSBlocks.leafBlock)) return 450;
        if (item == SSItems.leafBed) return 1600;
        if (item == SSItems.woodWateringCan) return 200;

        if (item == SSItems.lavaBottle) return 20000;

        if (item == SSItems.animalOil) return 1600;
        if (item == Item.getItemFromBlock(SSBlocks.animalOil)) return 14400;

        return 0;

    }

}
