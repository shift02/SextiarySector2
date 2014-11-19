package shift.sextiarysector.gui.tab;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class InventoryTabVanilla extends AbstractTab {

	@Override
	public ItemStack getItemStack() {
		return new ItemStack(Blocks.crafting_table);
	}

	@Override
	public boolean shouldAddToList() {
		return true;
	}

	@Override
	public void onTabClicked() {
		TabManager.openInventoryGui();
	}

}
