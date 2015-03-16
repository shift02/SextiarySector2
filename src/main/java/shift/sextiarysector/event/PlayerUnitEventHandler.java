package shift.sextiarysector.event;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import shift.sextiarysector.SSItems;
import shift.sextiarysector.player.EntityPlayerManager;
import shift.sextiarysector.player.EquipmentStats;
import shift.sextiarysector.player.EquipmentType;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class PlayerUnitEventHandler {

	@SubscribeEvent
	public void livingDashEvent(LivingUpdateEvent event) {

		//if (event.entityLiving.worldObj.isRemote) {
		//	return;
		//}

		if (!(event.entityLiving instanceof EntityPlayer)) {
			return;
		}

		EntityPlayer player = (EntityPlayer) event.entityLiving;

		if (player.capabilities.isCreativeMode) return;

		if (EntityPlayerManager.getStaminaStats(player).getStaminaLevel() < 5) {

			player.motionX *= 0.8;
			//player.motionY *= 0.5;
			player.motionZ *= 0.8;

		} else if (EntityPlayerManager.getStaminaStats(player).getStaminaLevel() >= 30 && player.isSprinting() && player.motionY == 0 && Math.abs(player.motionX) <= getMaxMove() && Math.abs(player.motionY) <= getMaxMove()) {

			player.motionX *= 1.6;
			//player.motionY *= 1.4;
			player.motionZ *= 1.6;

		}

	}

	public float getMaxMove() {

		return 3.4f;

	}

	//Unit関係のEvent

	@SubscribeEvent
	public void playerAttackEvent(LivingHurtEvent event) {

		if (!(event.source.getEntity() instanceof EntityPlayer)) return;

		EquipmentStats e = EntityPlayerManager.getEquipmentStats((EntityPlayer) event.source.getEntity());

		for (int i = 0; i < EquipmentType.Unit.getSlot().length; i++) {
			ItemStack item = e.inventory.getStackInSlot(EquipmentType.Unit.getSlot()[i]);

			if (item != null && item.getItem() != null && item.getItem() == SSItems.attackUnit) {
				event.ammount++;
			}

		}

	}

	@SubscribeEvent
	public void playeDefenseEvent(LivingHurtEvent event) {

		if (!(event.entity instanceof EntityPlayer)) return;

		EquipmentStats e = EntityPlayerManager.getEquipmentStats((EntityPlayer) event.entity);

		for (int i = 0; i < EquipmentType.Unit.getSlot().length; i++) {
			ItemStack item = e.inventory.getStackInSlot(EquipmentType.Unit.getSlot()[i]);

			if (item != null && item.getItem() != null && item.getItem() == SSItems.defenseUnit) {
				event.ammount--;
			}

		}

		if (event.ammount < 0) {
			event.ammount = 0;
		}

	}

	@SubscribeEvent
	public void playeHarvestCheckEvent(PlayerEvent.HarvestCheck event) {

		if (!(event.entity instanceof EntityPlayer)) return;

		EquipmentStats e = EntityPlayerManager.getEquipmentStats((EntityPlayer) event.entity);

		Block block = event.block;

		for (int i = 0; i < EquipmentType.Unit.getSlot().length; i++) {

			ItemStack item = e.inventory.getStackInSlot(EquipmentType.Unit.getSlot()[i]);

			if (item == null) continue;

			if (item.getItem() == null) continue;

			if (item.getItem() == SSItems.pickaxeUnit) {

				for (int meta = 0; meta < 16; meta++) {
					String tool = block.getHarvestTool(meta);

					if (tool == null) continue;

					if (tool.equals("pickaxe") && block.getHarvestLevel(meta) <= 1) {
						event.success = true;
					}
				}

			}

		}

	}

}
