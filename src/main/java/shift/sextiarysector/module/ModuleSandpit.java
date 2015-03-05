package shift.sextiarysector.module;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import shift.sextiarysector.SSItems;
import shift.sextiarysector.block.BlockSandpit;
import shift.sextiarysector.block.BlockSandpit.ShellEntry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ModuleSandpit implements IModule {

	private static ModuleSandpit instance;

	private ModuleSandpit() {
	}

	public static ModuleSandpit getInstance() {
		if (instance == null) {
			instance = new ModuleSandpit();
		}
		return instance;
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {

	}

	@Override
	public void load(FMLInitializationEvent event) {

	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {

		BlockSandpit.addShell(new ItemStack(Items.stick), 45);

		BlockSandpit.addShell(new ItemStack(Items.glass_bottle), 14);

		BlockSandpit.addShell(new ShellEntry(new ItemStack(Items.book), 10).setEnchant());

		BlockSandpit.addShell(new ItemStack(SSItems.laver), 26);

		BlockSandpit.addShell(new ItemStack(Items.redstone), 8);
		BlockSandpit.addShell(new ItemStack(SSItems.blueStoneDust), 8);
		BlockSandpit.addShell(new ItemStack(SSItems.yellowStoneDust), 8);

		BlockSandpit.addShell(new ItemStack(SSItems.mithrilDust), 3);
		BlockSandpit.addShell(new ItemStack(SSItems.orichalcumGem), 1);

	}

}
