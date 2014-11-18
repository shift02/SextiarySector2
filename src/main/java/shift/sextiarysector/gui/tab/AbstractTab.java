package shift.sextiarysector.gui.tab;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class AbstractTab {


	@SideOnly(Side.CLIENT)
	public abstract boolean shouldAddToList(EntityPlayer player);

}
