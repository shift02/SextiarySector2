package shift.sextiarysector.api.shop;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public interface IShopMemory {

    public ResourceLocation getMonitorResource();

    public int getShopID(World world);

}
