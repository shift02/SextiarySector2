package shift.sextiarysector.api.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class PlayerEatenEvent extends PlayerEvent {

    public ItemStack food;

    public PlayerEatenEvent(EntityPlayer player, ItemStack food) {
        super(player);
        this.food = food;
    }

}
