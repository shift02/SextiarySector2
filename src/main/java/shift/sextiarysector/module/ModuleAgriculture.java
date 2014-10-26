package shift.sextiarysector.module;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import shift.sextiarysector.SSItems;
import shift.sextiarysector.api.agriculture.AgricultureAPI;
import shift.sextiarysector.api.agriculture.SimpleFertilizer;
import shift.sextiarysector.item.ItemCrop;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ModuleAgriculture implements IModule {

	private static ModuleAgriculture instance;

	private ModuleAgriculture() {
	}

	public static ModuleAgriculture getInstance() {
		if(instance==null){
			instance = new ModuleAgriculture();
		}
		return instance;
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {

		AgricultureAPI.fertilizerManager = new FertilizerManager();

	}

	@Override
	public void load(FMLInitializationEvent event) {

		String t = "sextiarysector:fertilizer/";

		for(ItemCrop item :ItemCrop.crops){
			AgricultureAPI.fertilizerManager.registerFertilizer(new SimpleFertilizer("bone",t+"bone",new ItemStack(Items.dye, 1, 15), new ItemStack(item), new ItemStack(item,1,1)));
		}

		AgricultureAPI.fertilizerManager.registerFertilizer(new SimpleFertilizer("stone",t+"stone",new ItemStack(SSItems.stoneDust), new ItemStack(SSItems.corn), new ItemStack(SSItems.goldenCorn)));
		AgricultureAPI.fertilizerManager.registerFertilizer(new SimpleFertilizer("stone",t+"stone",new ItemStack(SSItems.stoneDust), new ItemStack(SSItems.turnip), new ItemStack(SSItems.ironTurnip)));

		//AgricultureAPI.fertilizerManager.registerFertilizer(new SimpleFertilizer("bone",t+"bone",new ItemStack(Items.dye, 4, 15), new ItemStack(SSItems.cucumber), new ItemStack(SSItems.tomato)));

	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {

	}

}
