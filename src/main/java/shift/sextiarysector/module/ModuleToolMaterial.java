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

	public static ArmorMaterial copperArmor;

	@Override
	public void preInit(FMLPreInitializationEvent event) {

		copperTool = EnumHelper.addToolMaterial("copper", 2, 200, 4.0F, 1.0F, 10);

		copperArmor = EnumHelper.addArmorMaterial("copper", 2, new int[] { 2, 4, 4, 1 }, 7);

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
