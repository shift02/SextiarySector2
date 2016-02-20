package shift.sextiarysector.asm.vanilla;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.ItemStack;
import shift.sextiarysector.SSItems;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.api.equipment.EquipmentType;
import shift.sextiarysector.player.EntityPlayerManager;
import shift.sextiarysector.player.EquipmentStats;

public class EntityPlayerSPMethod {

    public static boolean isSprinting(boolean p_70031_1_) {

        if (p_70031_1_) return false;

        try {

            EntityPlayerSP p = (EntityPlayerSP) SextiarySector.proxy.getClientPlayer();
            if (p.sprintingTicksLeft == 0) return false;
            float f = 0.8f;

            if (p.movementInput.moveForward < f || p.isCollidedHorizontally) return false;

            if (!(p.getFoodStats().getFoodLevel() > 6.0F || p.capabilities.allowFlying)) return true;

        } catch (Exception e) {

        }

        return false;
    }

    public static int isFlag(int i) {

        try {

            EntityPlayerSP p = (EntityPlayerSP) SextiarySector.proxy.getClientPlayer();

            EquipmentStats e = EntityPlayerManager.getEquipmentStats(p);

            for (int j = 0; j < EquipmentType.Unit.getSlots().length; j++) {

                ItemStack item = e.inventory.getStackInSlot(EquipmentType.Unit.getSlots()[j]);

                if (item == null) continue;

                if (item.getItem() == null) continue;

                if (item.getItem() == SSItems.dashUnit) {
                    return 10;
                }

                if (item.getItem() == SSItems.slowlyUnit) {
                    return 0;
                }

            }

            if (EntityPlayerManager.getStaminaStats(p).getStaminaLevel() == 0) return 0;

        } catch (Exception e) {

        }

        return 10;

    }

}
