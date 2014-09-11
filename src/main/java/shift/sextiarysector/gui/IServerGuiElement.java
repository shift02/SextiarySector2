package shift.sextiarysector.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public interface IServerGuiElement {

	public Object getServerGuiElement(int ID, EntityPlayer player, World world,int x, int y, int z);

}
