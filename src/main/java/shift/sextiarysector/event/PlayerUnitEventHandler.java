package shift.sextiarysector.event;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import shift.mceconomy2.api.MCEconomyAPI;
import shift.sextiarysector.SSItems;
import shift.sextiarysector.api.equipment.EquipmentType;
import shift.sextiarysector.player.EntityPlayerManager;
import shift.sextiarysector.player.EquipmentStats;
import cpw.mods.fml.common.eventhandler.EventPriority;
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

		if (!player.onGround) return;

		if (EntityPlayerManager.getStaminaStats(player).getStaminaLevel() < 5) {

			player.motionX *= 0.7;
			//player.motionY *= 0.5;
			player.motionZ *= 0.7;
			//player.capabilities.setPlayerWalkSpeed(0.08f);

		} else if (EntityPlayerManager.getStaminaStats(player).getStaminaLevel() >= 30 && player.isSprinting() && Math.abs(player.motionX) <= getMaxMove() && Math.abs(player.motionZ) <= getMaxMove()) {

			player.motionX *= 1.1;
			//player.motionY *= 1.4;
			player.motionZ *= 1.1;
			//player.capabilities.setPlayerWalkSpeed(0.16f);

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

		for (int i = 0; i < EquipmentType.Unit.getSlots().length; i++) {
			ItemStack item = e.inventory.getStackInSlot(EquipmentType.Unit.getSlots()[i]);

			if (item != null && item.getItem() != null && item.getItem() == SSItems.attackUnit) {
				event.ammount++;
			}

		}

	}

	@SubscribeEvent
	public void playeDefenseEvent(LivingHurtEvent event) {

		if (!(event.entity instanceof EntityPlayer)) return;

		EquipmentStats e = EntityPlayerManager.getEquipmentStats((EntityPlayer) event.entity);

		for (int i = 0; i < EquipmentType.Unit.getSlots().length; i++) {
			ItemStack item = e.inventory.getStackInSlot(EquipmentType.Unit.getSlots()[i]);

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

		for (int i = 0; i < EquipmentType.Unit.getSlots().length; i++) {

			ItemStack item = e.inventory.getStackInSlot(EquipmentType.Unit.getSlots()[i]);

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

	//ダッシュユニット
	@SubscribeEvent
	public void onLivingJump(LivingJumpEvent event) {

		if (!(event.entityLiving instanceof EntityPlayer)) {
			return;
		}

		//if (event.entityLiving.worldObj.isRemote) {
		//	return;
		//}

		EntityPlayer player = (EntityPlayer) event.entityLiving;

		EquipmentStats e = EntityPlayerManager.getEquipmentStats((EntityPlayer) event.entity);

		double size = 0;

		for (int i = 0; i < EquipmentType.Unit.getSlots().length; i++) {

			ItemStack item = e.inventory.getStackInSlot(EquipmentType.Unit.getSlots()[i]);

			if (item == null) continue;

			if (item.getItem() == null) continue;

			if (item.getItem() == SSItems.jumpUnit) {
				size += 0.1;
			}

		}

		//System.out.println(size);
		player.motionY += size;

	}

	//リング
	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onPlayerCloneEvent(net.minecraftforge.event.entity.player.PlayerEvent.Clone event)
	{

		if (!event.wasDeath) return;

		if (!(event.original instanceof EntityPlayer)) return;

		EquipmentStats e = EntityPlayerManager.getEquipmentStats(event.original);

		int mp = (int) (MCEconomyAPI.getPlayerMP(event.original) / 4.0f);
		int xp = (int) (event.original.experienceLevel / 4.0f);

		for (int i = 0; i < EquipmentType.Ring.getSlots().length; i++) {

			ItemStack item = e.inventory.getStackInSlot(EquipmentType.Ring.getSlots()[i]);

			if (item != null && item.getItem() == SSItems.mpRing) {

				int reduce = MCEconomyAPI.reducePlayerMP(event.original, mp, false);
				MCEconomyAPI.addPlayerMP(event.entityPlayer, reduce, false);
				e.inventory.setInventorySlotContents(EquipmentType.Ring.getSlots()[i], null);

			} else if (item != null && item.getItem() == SSItems.xpRing) {

				event.entityPlayer.experienceLevel += xp;
				event.original.experienceLevel -= xp;
				e.inventory.setInventorySlotContents(EquipmentType.Ring.getSlots()[i], null);

			}

		}

	}

}
