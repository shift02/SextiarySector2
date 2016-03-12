package shift.sextiarysector.api.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;

@Cancelable
public class PlayerMoistureEvent extends PlayerEvent {

    public final int moistureLevel;
    public final float moistureSaturationLevel;

    public int newMoistureLevel;
    public float newMoistureSaturationLevel;

    public PlayerMoistureEvent(EntityPlayer player, int level, float saturation) {
        super(player);
        this.moistureLevel = level;
        this.moistureSaturationLevel = saturation;
        this.newMoistureLevel = moistureLevel;
        this.newMoistureSaturationLevel = moistureSaturationLevel;
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