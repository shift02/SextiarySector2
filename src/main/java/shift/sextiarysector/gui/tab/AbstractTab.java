package shift.sextiarysector.gui.tab;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class AbstractTab {

	@SideOnly(Side.CLIENT)
	public abstract void onTabClicked ();

	public abstract ItemStack getItemStack();

	@SideOnly(Side.CLIENT)
	public abstract String getTabName();

	@SideOnly(Side.CLIENT)
	public abstract boolean shouldAddToList();

}
