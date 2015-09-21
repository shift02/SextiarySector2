package shift.sextiarysector.api.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fluids.FluidStack;

public class BlockBottleEvent extends PlayerEvent {

    public FluidStack fluid;

    public BlockBottleEvent(EntityPlayer player, FluidStack fluid) {
        super(player);
        this.fluid = fluid;
    }

}
