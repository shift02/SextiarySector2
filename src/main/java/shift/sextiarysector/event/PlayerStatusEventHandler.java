package shift.sextiarysector.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import shift.sextiarysector.SSPotions;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.api.event.PlayerEatenEvent;
import shift.sextiarysector.module.ModuleHotSprings;
import shift.sextiarysector.player.EntityPlayerManager;
import shift.sextiarysector.player.MoistureStats;
import shift.sextiarysector.player.StaminaStats;

/***
 *
 * プレイヤー関係のEvent
 *
 * @author Shift02
 * @SpecialThanks Furia
 */
public class PlayerStatusEventHandler {

    /** 食べ物を食べた時の動作 */
    @SubscribeEvent
    public void onPlayerUseItemEvent(PlayerUseItemEvent.Finish event) {

        ItemStack item = event.item;
        EntityPlayer player = (EntityPlayer) event.entity;

        MinecraftForge.EVENT_BUS.post(new PlayerEatenEvent(player, item));

    }

    /***
     * 空腹時のダメージを無効
     */

    //    @SubscribeEvent
    //    public void onPlayerUseItemEvent(LivingAttackEvent event) {
    //
    //        if (!(event.entityLiving instanceof EntityPlayer)) return;
    //
    //        if (event.source == DamageSource.starve) {
    //            event.setCanceled(true);
    //        }
    //
    //    }

    @SubscribeEvent
    public void onPlayerUseItemEvent(LivingUpdateEvent event) {

    }

    /**
     * 水分関係
     */

    // ブロック破壊
    @SubscribeEvent
    public void onBlockBreakEvent(BreakEvent event) {

        if (event.world.isRemote) {
            return;
        }

        EntityPlayer player = event.getPlayer();

        float i = 0.025f;

        if (BiomeDictionary.isBiomeOfType(event.world.getBiomeGenForCoords(event.x, event.y), Type.SANDY)) {
            i *= 4;
        }
        if (event.world.getBlock(event.x, event.y, event.z).getMaterial() == Material.sand) {
            i *= 2;
        }

        SextiarySectorAPI.addMoistureExhaustion(player, i);

    }

    // ダメージ
    @SubscribeEvent
    public void onLivingHurtEvent(LivingHurtEvent event) {

        if (event.entityLiving.worldObj.isRemote || !(event.entityLiving instanceof EntityPlayer)) {
            return;
        }

        EntityPlayer player = (EntityPlayer) event.entityLiving;

        SextiarySectorAPI.addMoistureExhaustion(player, event.ammount * 0.2f);

    }

    //攻撃
    @SubscribeEvent
    public void onAttackEntityEvent(AttackEntityEvent event) {

        if (event.entityLiving.worldObj.isRemote || !(event.entityLiving instanceof EntityPlayer)) {
            return;
        }

        EntityPlayer player = (EntityPlayer) event.entityLiving;

        SextiarySectorAPI.addMoistureExhaustion(player, 0.23f);

    }

    //ジャンプ
    @SubscribeEvent
    public void onLivingJump2(LivingJumpEvent event) {

        if (!(event.entityLiving instanceof EntityPlayer)) {
            return;
        }

        if (event.entityLiving.worldObj.isRemote) {
            return;
        }

        EntityPlayer player = (EntityPlayer) event.entityLiving;

        float i = 1;

        if (BiomeDictionary.isBiomeOfType(player.worldObj.getBiomeGenForCoords((int) player.posX, (int) player.posY), Type.DESERT)) {
            i = 2;
        }

        if (player.isSprinting()) {
            SextiarySectorAPI.addMoistureExhaustion(player, 0.1f * i);
        } else {
            SextiarySectorAPI.addMoistureExhaustion(player, 0.05f * i);
        }

    }

    /**
     * スタミナ関係
     */

    // ブロック破壊
    @SubscribeEvent
    public void onBlockBreakEventS(BreakEvent event) {

        if (event.world.isRemote) {
            return;
        }

        EntityPlayer player = event.getPlayer();

        float i = 0.65f;

        if (BiomeDictionary
                .isBiomeOfType(
                        event.world.getBiomeGenForCoords(event.x, event.y),
                        Type.DESERT)) {
            i *= 4;
        }
        if (event.world.getBlock(event.x, event.y, event.z).getMaterial() == Material.sand) {
            i *= 2;
        }

        SextiarySectorAPI.addStaminaExhaustion(player, i);

    }

    // ダメージ
    @SubscribeEvent
    public void onLivingHurtEvent2(LivingHurtEvent event) {

        if (event.entityLiving.worldObj.isRemote || !(event.entityLiving instanceof EntityPlayer)) {
            return;
        }

        EntityPlayer player = (EntityPlayer) event.entityLiving;

        SextiarySectorAPI.addStaminaExhaustion(player, event.ammount * 1.2f);

    }

    // ジャンプ
    @SubscribeEvent
    public void onLivingJump(LivingJumpEvent event) {

        if (!(event.entityLiving instanceof EntityPlayer)) {
            return;
        }

        if (event.entityLiving.worldObj.isRemote) {
            return;
        }

        EntityPlayer player = (EntityPlayer) event.entityLiving;

        float i = 1;

        if (BiomeDictionary.isBiomeOfType(player.worldObj.getBiomeGenForCoords((int) player.posX, (int) player.posY), Type.DESERT)) {
            i = 2;
        }

        if (player.isSprinting()) {
            SextiarySectorAPI.addStaminaExhaustion(player, 2.2f * i);
        } else {
            SextiarySectorAPI.addStaminaExhaustion(player, 0.5f * i);
        }

    }

    //攻撃
    @SubscribeEvent
    public void onAttackEntityEvent2(AttackEntityEvent event) {

        if (event.entityLiving.worldObj.isRemote || !(event.entityLiving instanceof EntityPlayer)) {
            return;
        }

        EntityPlayer player = (EntityPlayer) event.entityLiving;

        SextiarySectorAPI.addStaminaExhaustion(player, 1.4f);

    }

    //ベットで回復
    @SubscribeEvent
    public void LivingSleepingEvent(PlayerWakeUpEvent event) {

        if (event.entityPlayer.worldObj.isRemote) {
            return;
        }

        if (!(event.entityPlayer instanceof EntityPlayer)) {
            return;
        }

        if (event.updateWorld) return;

        if (!event.entityLiving.worldObj.isRemote) {

            EntityPlayer player = event.entityPlayer;

            if (EntityPlayerManager.getMoistureStats(player).getMoistureLevel() > 4 && player.getFoodStats().getFoodLevel() > 4) {
                EntityPlayerManager.getStaminaStats(player).addStats(player, 100, 20.0f);
            } else {
                EntityPlayerManager.getStaminaStats(player).addStats(player, 40, 0.0f);
            }
            player.getFoodStats().addExhaustion(19.3f);
            SextiarySectorAPI.addMoistureExhaustion(player, 20.3f);

        }

    }

    //Special Thanks ,Furia
    @SubscribeEvent
    public void playerUpdateEvent(TickEvent.PlayerTickEvent event) {
        this.playerNoMoveEvent(event.player);
        this.playerSittingEvent(event.player);
        this.playerSpaEvent(event.player);
    }

    private void playerNoMoveEvent(EntityPlayer player) {

        if (player.worldObj.getTotalWorldTime() % 120 != 0) return;

        if (player.worldObj.rand.nextBoolean()) return;

        if (player.isSneaking()) return;

        StaminaStats stats = EntityPlayerManager.getStaminaStats(player);
        if (stats == null) return;

        if (!stats.needStamina()) return;

        MoistureStats moistStats = EntityPlayerManager.getMoistureStats(player);
        if (moistStats == null) return;

        if (moistStats.getMoistureLevel() < 1) return;

        if ((int) player.lastTickPosX == (int) player.posX
                && (int) player.lastTickPosY == (int) player.posY
                && (int) player.lastTickPosZ == (int) player.posZ
                && player.motionX == 0
                && player.motionY == 0
                && player.motionZ == 0) {

            if (!player.worldObj.isRemote) {
                stats.addStats(player, 1, 0.1f);
                moistStats.addExhaustion(player, 0.05f);
                player.addExhaustion(0.1f);
            }
            generateRandomParticles(player, "happyVillager");

        }
    }

    private void playerSittingEvent(EntityPlayer player) {

        if (player.worldObj.getTotalWorldTime() % 70 != 0)
            return;

        if (!player.isRiding()) return;

        StaminaStats stats = EntityPlayerManager.getStaminaStats(player);
        if (stats == null) return;

        if (!stats.needStamina()) return;

        MoistureStats moistStats = EntityPlayerManager.getMoistureStats(player);
        if (moistStats == null) return;

        if (moistStats.getMoistureLevel() < 10)
            return;

        if (player.lastTickPosX == player.posX
                && player.lastTickPosY == player.posY
                && player.lastTickPosZ == player.posZ
                && player.motionX == 0
                && player.motionY == 0
                && player.motionZ == 0) {

            stats.addStats(player, 1, 0.0f);
            moistStats.addExhaustion(player, 1.4f);
            player.addExhaustion(0.05f);
            this.generateRandomParticles(player, "happyVillager");
        }

    }

    private void playerSpaEvent(EntityPlayer player) {

        if (player.worldObj.getTotalWorldTime() % 200 != 0)
            return;

        StaminaStats stats = EntityPlayerManager.getStaminaStats(player);
        if (stats == null)
            return;

        if (!stats.needStamina())
            return;

        //spaEffect
        if (!player.isInWater())
            return;

        int i = MathHelper.floor_double(player.posX);
        int j = MathHelper.floor_double(player.boundingBox.minY);
        int k = MathHelper.floor_double(player.posZ);

        for (int offset = 0; offset <= 1; offset++) {
            Block spaBlock = player.worldObj.getBlock(i, j + offset, k);
            int meta = player.worldObj.getBlockMetadata(i, j + offset, k);
            if (spaBlock == null) continue;

            if (ModuleHotSprings.isHotSprings(spaBlock, meta)) {
                player.addPotionEffect(new PotionEffect(SSPotions.hotSprings.getId(), 400, 2));
                generateRandomParticles(player, "happyVillager");
                break;
            }
        }
    }

    private void generateRandomParticles(EntityPlayer player, String particleName) {
        for (int i = 0; i < 5; ++i) {
            double d0 = player.getRNG().nextGaussian() * 0.02D;
            double d1 = player.getRNG().nextGaussian() * 0.02D;
            double d2 = player.getRNG().nextGaussian() * 0.02D;
            player.worldObj.spawnParticle(particleName,
                    player.posX + player.getRNG().nextFloat() * player.width * 2.0F - player.width,
                    player.posY + 0.2D + player.getRNG().nextFloat() * player.height,
                    player.posZ + player.getRNG().nextFloat() * player.width * 2.0F - player.width,
                    d0, d1, d2);
        }
    }

    /** スタミナのボーナス */
    /*
    @SubscribeEvent
    public void livingDashEvent(LivingUpdateEvent event) {
    
    	//if (event.entityLiving.worldObj.isRemote) {
    	//	return;
    	//}
    
    	if (!(event.entityLiving instanceof EntityPlayer)) {
    		return;
    	}
    
    	EntityPlayer player = (EntityPlayer) event.entityLiving;
    
    	if(EntityPlayerManager.getStaminaStats(player).getStaminaLevel()<20){
    
    		player.motionX *= 0.5;
    		//player.motionY *= 0.5;
    		player.motionZ *= 0.5;
    
    	}else if(player.isSprinting() && !player.isAirBorne){
    
    		player.motionX *= 1.4;
    		//player.motionY *= 1.4;
    		player.motionZ *= 1.4;
    
    	}
    
    
    
    
    }*/

    // ベットで回復
    /*@SubscribeEvent
    public void LivingSleepingEvent(LivingUpdateEvent event) {
    	if (event.entityLiving.worldObj.isRemote) {
    		return;
    	}
    
    	if (!(event.entityLiving instanceof EntityPlayer)) {
    		return;
    	}
    
    	if (!event.entityLiving.worldObj.isRemote) {
    
    		EntityPlayer player = (EntityPlayer) event.entityLiving;
    
    		if (player.isPlayerFullyAsleep()) {
    
    			if (EntityPlayerManager.getMoistureStats(player).getMoistureLevel() > 4&& player.getFoodStats().getFoodLevel() > 4) {
    				EntityPlayerManager.getStaminaStats(player).addStats(100, 20.0f);
    			} else {
    				EntityPlayerManager.getStaminaStats(player).addStats(40, 0.0f);
    			}
    			player.getFoodStats().addExhaustion(21.0f);
    			SextiarySectorAPI.addMoistureExhaustion(player, 21.0f);
    
    			// System.out.println("LivingSleepingEvent");
    		}
    
    	}
    
    }*/

}
