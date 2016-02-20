package shift.sextiarysector.module;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import shift.sextiarysector.SSBlocks;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ModuleHotSprings implements IModule {

    private static ModuleHotSprings instance;

    private ModuleHotSprings() {
    }

    public static ModuleHotSprings getInstance() {
        if (instance == null) {
            instance = new ModuleHotSprings();
        }
        return instance;
    }

    public static List<ItemStack> hotSpringsBlocks = new ArrayList<ItemStack>();

    @Override
    public void preInit(FMLPreInitializationEvent event) {

    }

    @Override
    public void load(FMLInitializationEvent event) {

        registerBlock(SSBlocks.hotSprings);

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {

    }

    public static void registerBlock(Block block) {

        if (block == null) return;
        registerBlock(new ItemStack(block, 1, OreDictionary.WILDCARD_VALUE));

    }

    public static void registerBlock(ItemStack stack) {

        if (stack == null) return;
        hotSpringsBlocks.add(stack);

    }

    public static boolean isHotSprings(Block block, int meta) {

        if (block == null) return false;

        for (ItemStack stack : hotSpringsBlocks) {
            if (checkItem(block, meta, stack)) return true;
        }
        return false;

    }

    private static boolean checkItem(Block block, int meta, ItemStack p_151397_2_) {
        return p_151397_2_.getItem() == Item.getItemFromBlock(block) && (p_151397_2_.getItemDamage() == 32767 || p_151397_2_.getItemDamage() == meta);
    }
}
