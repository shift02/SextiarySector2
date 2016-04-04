/*
* 作成者: Shift02
* 作成日: 2016/03/14 - 14:31:10
*/
package shift.sextiarysector.api.event.player;

import cpw.mods.fml.common.eventhandler.Cancelable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class PlayerStaminaEvent extends PlayerEvent {

    public final int staminaLevel;
    public final float staminaSaturationLevel;

    public PlayerStaminaEvent(EntityPlayer player, int level, float saturation) {
        super(player);
        this.staminaLevel = level;
        this.staminaSaturationLevel = saturation;
    }

    @Cancelable
    public static class Add extends PlayerStaminaEvent {

        public final int addStaminaLevel;
        public final float addStaminaSaturationLevel;

        public int newAddMoistureLevel;
        public float newAddMoistureSaturationLevel;

        public Add(EntityPlayer player, int level, float saturation, int addStaminaLevel, float addStaminaSaturationLevel) {
            super(player, level, saturation);
            this.addStaminaLevel = addStaminaLevel;
            this.addStaminaSaturationLevel = addStaminaSaturationLevel;
            this.newAddMoistureLevel = addStaminaLevel;
            this.newAddMoistureSaturationLevel = addStaminaSaturationLevel;
        }
    }

    @Cancelable
    public static class Exhaustion extends PlayerStaminaEvent {

        public final float exhaustionLevel;

        public float newExhaustionLevel;

        public Exhaustion(EntityPlayer player, int level, float saturation, float exhaustionLevel) {
            super(player, level, saturation);
            this.exhaustionLevel = level;
            this.newExhaustionLevel = exhaustionLevel;
        }
    }

}