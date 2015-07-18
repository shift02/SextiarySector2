package shift.sextiarysector.module;

import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomFishable;
import net.minecraftforge.common.FishingHooks;
import shift.sextiarysector.SSItems;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ModuleFishing implements IModule {

	private static ModuleFishing instance;

	private ModuleFishing() {
	}

	public static ModuleFishing getInstance() {
		if (instance == null) {
			instance = new ModuleFishing();
		}
		return instance;
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void load(FMLInitializationEvent event) {

		FishingHooks.addTreasure(new WeightedRandomFishable(new ItemStack(SSItems.jumpUnit), 1));

	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
