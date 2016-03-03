package shift.sextiarysector.api.shop;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

/**
 * 新しいShopを追加するためのインターフェース<br>
 * Itemに実装
 * @author Shift02
 *
 */
public interface IShopMemory {

    public ResourceLocation getMonitorResource();

    public int getShopID(World world, EntityPlayer player);

}
