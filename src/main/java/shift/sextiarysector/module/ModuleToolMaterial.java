package shift.sextiarysector.module;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ModuleToolMaterial implements IModule {

    private static ModuleToolMaterial instance;

    private ModuleToolMaterial() {
    }

    public static ModuleToolMaterial getInstance() {
        if (instance == null) {
            instance = new ModuleToolMaterial();
        }
        return instance;
    }

    public static ToolMaterial copperTool;
    public static ToolMaterial brassTool;
    public static ToolMaterial ninjaTool;

    public static ArmorMaterial copperArmor;
    public static ArmorMaterial ninjaArmor;

    @Override
    public void preInit(FMLPreInitializationEvent event) {

        //レベル 耐久 スピード Power Enchant
        copperTool = EnumHelper.addToolMaterial("copper", 2, 200, 4.0F, 1.0F, 10);
        brassTool = EnumHelper.addToolMaterial("brass", 2, 660, 7.0F, 3.0F, 8);
        ninjaTool = EnumHelper.addToolMaterial("ninja", 4, 1172, 12.5F, 5.0F, 19);

        //耐久 それぞれの防御力 Enchant
        copperArmor = EnumHelper.addArmorMaterial("copper", 13, new int[] { 2, 6, 4, 2 }, 7);
        ninjaArmor = EnumHelper.addArmorMaterial("ninja", 29, new int[] { 4, 9, 6, 4 }, 22);

    }

    @Override
    public void load(FMLInitializationEvent event) {
        // TODO 自動生成されたメソッド・スタブ

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        // TODO 自動生成されたメソッド・スタブ

    }

}
