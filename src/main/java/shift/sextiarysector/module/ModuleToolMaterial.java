package shift.sextiarysector.module;

import net.minecraft.item.Item.ToolMaterial;
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

	public static ToolMaterial copper;

	@Override
	public void preInit(FMLPreInitializationEvent event) {

		copper = EnumHelper.addToolMaterial("copper", 2, 200, 4.0F, 1.0F, 10);

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
