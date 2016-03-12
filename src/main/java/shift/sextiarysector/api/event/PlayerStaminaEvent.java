package shift.sextiarysector.api.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;

@Cancelable
public class PlayerStaminaEvent extends PlayerEvent {

    public final int staminaLevel;
    public final float staminaSaturationLevel;

    public int newStaminaLevel;
    public float newStaminaSaturationLevel;

    public PlayerStaminaEvent(EntityPlayer player, int level, float saturation) {
        super(player);
        this.staminaLevel = level;
        this.staminaSaturationLevel = saturation;
        this.newStaminaLevel = staminaLevel;
        this.newStaminaSaturationLevel = staminaSaturationLevel;
    }

    @Cancelable
    public static class Exhaustion extends PlayerEvent {

        public final float exhaustionLevel;

        public float newExhaustionLevel;

        public Exhaustion(EntityPlayer player, float level) {
            super(player);
            this.exhaustionLevel = level;
            this.newExhaustionLevel = exhaustionLevel;
        }
    }
}