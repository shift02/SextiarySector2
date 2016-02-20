package shift.sextiarysector.event;

import java.util.Random;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import shift.mceconomy2.api.MCEconomyAPI;
import shift.sextiarysector.SSItems;
import shift.sextiarysector.api.equipment.EquipmentType;
import shift.sextiarysector.player.EntityPlayerManager;
import shift.sextiarysector.player.EquipmentStats;

public class PlayerUnitEventHandler {

    protected static Random itemRand = new Random();

    @SubscribeEvent
    public void livingDashEvent(LivingUpdateEvent event) {

        //if (event.entityLiving.worldObj.isRemote) {
        //	return;
        //}

        /*
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
        
        }*/

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

    //jumpユニット
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

    //弓
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void arrowLooseEvent(ArrowLooseEvent event) {

        if (!(event.entity instanceof EntityPlayer)) return;

        EquipmentStats e = EntityPlayerManager.getEquipmentStats((EntityPlayer) event.entity);

        for (int i = 0; i < EquipmentType.Unit.getSlots().length; i++) {

            ItemStack item = e.inventory.getStackInSlot(EquipmentType.Unit.getSlots()[i]);

            if (item == null) continue;

            if (item.getItem() == null) continue;

            if (item.getItem() == SSItems.pullingUnit) {

                event.charge = 30;
                return;

            }

        }

    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onArrowLooseEvent(ArrowLooseEvent event) {

        if (!(event.entity instanceof EntityPlayer)) return;

        EquipmentStats e = EntityPlayerManager.getEquipmentStats((EntityPlayer) event.entity);

        int c = 0;

        for (int i = 0; i < EquipmentType.Unit.getSlots().length; i++) {

            ItemStack item = e.inventory.getStackInSlot(EquipmentType.Unit.getSlots()[i]);

            if (item == null) continue;

            if (item.getItem() == null) continue;

            if (item.getItem() == SSItems.multiSchottUnit) {

                c++;

            }

        }

        if (c == 0) return;

        int j = event.charge;

        for (int i = 0; i <= c; i++) {

            float f = j / 20.0F;
            f = (f * f + f * 2.0F) / 3.0F;

            if (f < 0.1D) {
                return;
            }

            if (f > 1.0F) {
                f = 1.0F;
            }

            EntityArrow entityarrow = new EntityArrow(event.entityPlayer.worldObj, event.entityPlayer, f * 2.0F);
            entityarrow.posX += entityarrow.motionX;
            entityarrow.posY += entityarrow.motionY;
            entityarrow.posZ += entityarrow.motionZ;

            if (f == 1.0F) {
                entityarrow.setIsCritical(true);
            }

            int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, event.bow);

            if (k > 0) {
                entityarrow.setDamage(entityarrow.getDamage() + k * 0.5D + 0.5D);
            }

            int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, event.bow);

            if (l > 0) {
                entityarrow.setKnockbackStrength(l);
            }

            if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, event.bow) > 0) {
                entityarrow.setFire(100);
            }

            //event.bow.damageItem(1, event.entityPlayer);
            //event.entityPlayer.worldObj.playSoundAtEntity(event.entityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

            entityarrow.canBePickedUp = 2;

            if (!event.entityPlayer.worldObj.isRemote) {
                event.entityPlayer.worldObj.spawnEntityInWorld(entityarrow);
            }

        }

    }

    //どこでも睡眠
    /*
    @SubscribeEvent
    public void onSleep(PlayerInteractEvent event) {
    
    	if (event.action != Action.RIGHT_CLICK_BLOCK) return;
    
    	System.out.println("aaa");
    	if (!event.entityPlayer.isSneaking()) return;
    
    	System.out.println("baaa");
    
    	if (event.entityPlayer.getCurrentEquippedItem() != null) return;
    
    	System.out.println("caaa");
    
    	if (event.world.isRemote) return;
    
    	EquipmentStats e = EntityPlayerManager.getEquipmentStats(event.entityPlayer);
    
    	for (int i = 0; i < EquipmentType.Unit.getSlots().length; i++) {
    
    		ItemStack item = e.inventory.getStackInSlot(EquipmentType.Unit.getSlots()[i]);
    
    		if (item == null) continue;
    
    		if (item.getItem() == null) continue;
    
    		if (item.getItem() == SSItems.bedMonsterUnit) {
    			System.out.println("daaa");
    			event.entityPlayer.sleepInBedAt(event.x, event.y + 1, event.z);
    			return;
    
    		}
    
    	}
    
    	//double y = event.world.getBlock(event.x, event.y, event.z).getBlockBoundsMaxY();
    
    }*/

    public boolean checkBed(World w, int x, int y, int z) {

        return false;
    }

    //睡眠ユニット
    /*
    @SubscribeEvent
    public void onSleep(PlayerSleepInBedEvent event) {
    
    	EntityPlayer player = (EntityPlayer) event.entityLiving;
    
    	NBTTagCompound nbt = player.getEntityData();
    	NBTTagCompound ssnbt;
    	if (!nbt.hasKey("ss2")) {
    
    		ssnbt = new NBTTagCompound();
    		nbt.setTag("ss2", ssnbt);
    
    	} else {
    		ssnbt = nbt.getCompoundTag("ss2");
    	}
    
    	if (ssnbt.hasKey("bed")) {
    		ssnbt.removeTag("bed");
    		player.worldObj.isRemote = false;
    		return;
    	}
    
    	EquipmentStats e = EntityPlayerManager.getEquipmentStats((EntityPlayer) event.entity);
    
    	for (int i = 0; i < EquipmentType.Unit.getSlots().length; i++) {
    
    		ItemStack item = e.inventory.getStackInSlot(EquipmentType.Unit.getSlots()[i]);
    
    		if (item == null) continue;
    
    		if (item.getItem() == null) continue;
    
    		if (item.getItem() == SSItems.bedMonsterUnit) {
    
    			if (!player.worldObj.isRemote)
    			{
    				if (player.isPlayerSleeping() || !player.isEntityAlive())
    				{
    					return;
    				}
    
    				if (!player.worldObj.provider.isSurfaceWorld())
    				{
    					return;
    				}
    
    				if (player.worldObj.isDaytime())
    				{
    					return;
    				}
    
    				if (Math.abs(player.posX - event.x) > 3.0D || Math.abs(player.posY - event.y) > 2.0D || Math.abs(player.posZ - event.z) > 3.0D)
    				{
    					return;
    				}
    
    				event.result = EntityPlayer.EnumStatus.OK;
    
    				if (player.isRiding())
    				{
    					player.mountEntity((Entity) null);
    				}
    
    				player.sleepInBedAt(event.x, event.y, event.z);
    
    				player.worldObj.updateAllPlayersSleepingFlag();
    
    				return;
    
    			} else {
    				return;
    			}
    
    		}
    
    	}
    
    }*/

    protected void setSize(EntityPlayer p, float p_70105_1_, float p_70105_2_) {
        float f2;

        if (p_70105_1_ != p.width || p_70105_2_ != p.height) {
            f2 = p.width;
            p.width = p_70105_1_;
            p.height = p_70105_2_;
            p.boundingBox.maxX = p.boundingBox.minX + p.width;
            p.boundingBox.maxZ = p.boundingBox.minZ + p.width;
            p.boundingBox.maxY = p.boundingBox.minY + p.height;

            if (p.width > f2 && !p.worldObj.isRemote) {
                p.moveEntity(f2 - p.width, 0.0D, f2 - p.width);
            }
        }

        f2 = p_70105_1_ % 2.0F;

        if (f2 < 0.375D) {
            p.myEntitySize = Entity.EnumEntitySize.SIZE_1;
        } else if (f2 < 0.75D) {
            p.myEntitySize = Entity.EnumEntitySize.SIZE_2;
        } else if (f2 < 1.0D) {
            p.myEntitySize = Entity.EnumEntitySize.SIZE_3;
        } else if (f2 < 1.375D) {
            p.myEntitySize = Entity.EnumEntitySize.SIZE_4;
        } else if (f2 < 1.75D) {
            p.myEntitySize = Entity.EnumEntitySize.SIZE_5;
        } else {
            p.myEntitySize = Entity.EnumEntitySize.SIZE_6;
        }
    }

    //リング
    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onPlayerCloneEvent(net.minecraftforge.event.entity.player.PlayerEvent.Clone event) {

        if (!event.wasDeath) return;

        if (!(event.original instanceof EntityPlayer)) return;

        EquipmentStats e = EntityPlayerManager.getEquipmentStats(event.original);

        int mp = (int) (MCEconomyAPI.getPlayerMP(event.original) / 4.0f);
        int xp = (int) (event.original.experienceLevel / 4.0f);

        for (int i = 0; i < EquipmentType.Other.getSlots().length; i++) {

            ItemStack item = e.inventory.getStackInSlot(EquipmentType.Other.getSlots()[i]);

            if (item != null && item.getItem() == SSItems.mpRing) {

                int reduce = MCEconomyAPI.reducePlayerMP(event.original, mp, false);
                MCEconomyAPI.addPlayerMP(event.entityPlayer, reduce, false);
                e.inventory.setInventorySlotContents(EquipmentType.Other.getSlots()[i], null);

            } else if (item != null && item.getItem() == SSItems.xpRing) {

                event.entityPlayer.experienceLevel += xp;
                event.original.experienceLevel -= xp;
                e.inventory.setInventorySlotContents(EquipmentType.Other.getSlots()[i], null);

            }

        }

    }

}
