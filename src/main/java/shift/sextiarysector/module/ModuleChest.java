package shift.sextiarysector.module;

import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import shift.sextiarysector.SSItems;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ModuleChest implements IModule {

	private static ModuleChest instance;

	private ModuleChest() {
	}

	public static ModuleChest getInstance() {
		if (instance == null) {
			instance = new ModuleChest();
		}
		return instance;
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void load(FMLInitializationEvent event) {

		ChestGenHooks pyramidDesertyChest = ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST);
		pyramidDesertyChest.addItem(new WeightedRandomChestContent(new ItemStack(SSItems.attackRustUnit), 1, 1, 1));

		ChestGenHooks pyramidJungleChest = ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST);
		pyramidJungleChest.addItem(new WeightedRandomChestContent(new ItemStack(SSItems.defenseRustUnit), 1, 1, 1));

	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
