package shift.sextiarysector.api.agriculture;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface ICrop {

    String getName();

    boolean isSeed(ItemStack seed);

    void update(TileCrop crop);

    void click(TileCrop crop, EntityPlayer player);

    boolean canBlockStay(TileFarmland farmland);

}
