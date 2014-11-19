package shift.sextiarysector.gui.tab;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class AbstractTab {

	public abstract void onTabClicked ();

	public abstract ItemStack getItemStack();

	public abstract boolean shouldAddToList();

}
