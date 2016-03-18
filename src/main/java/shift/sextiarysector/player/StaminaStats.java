package shift.sextiarysector.player;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraftforge.common.MinecraftForge;
import shift.sextiarysector.SSConfig;
import shift.sextiarysector.api.event.player.PlayerMoistureEvent;
import shift.sextiarysector.api.event.player.PlayerStaminaEvent;

public class StaminaStats {

    /** The player's stamina level. */
    private int staminaLevel = 100;
    private final static int MAX_STAMINA_LEVEL = 100;
    private final static int MAX_PREV_STAMINA_LEVEL = 20;

    /** The player's food saturation. */
    private float staminaSaturationLevel = 5.0F;

    //Packet用
    private int lastStaminaLevel = 100;
    private float lastSaturationLevel = 0;

    /** The player's food exhaustion. */
    private float staminaExhaustionLevel;

    /** The player's food timer value. */
    private int staminaTimer;
    private int prevStaminaLevel = 20;

    /**
     * Args: int staminaLevel, float staminaSaturationModifier
     */
    public void addStats(EntityPlayer entityPlayer, int par1, float par2) {

        if (!SSConfig.statusStamina) return;

        //this.staminaLevel = Math.min(par1 + this.staminaLevel, MAX_STAMINA_LEVEL);
        //this.staminaSaturationLevel = Math.min(Math.min(this.staminaSaturationLevel + par1 * par2 * 2.0F, this.staminaLevel), MAX_STAMINA_LEVEL / 2);

        PlayerMoistureEvent.Add event = new PlayerMoistureEvent.Add(entityPlayer, staminaLevel, staminaSaturationLevel, par1, par2);

        if (MinecraftForge.EVENT_BUS.post(event)) {
            //this.moistureLevel = prevLevel;
            //this.moistureSaturationLevel = prevSaturation;
        } else {
            this.staminaLevel = MathHelper.clamp_int(event.newAddMoistureLevel + this.staminaLevel, 0, MAX_STAMINA_LEVEL);
            this.staminaSaturationLevel = MathHelper.clamp_float(event.newAddMoistureSaturationLevel + this.staminaSaturationLevel, 0.0F, this.staminaLevel / 3.0f);
        }

    }

    /*
    public void addStats(IStaminaFood par1ItemStaminaFood)
    {
        this.addStats(par1ItemStaminaFood.getHealAmount(), par1ItemStaminaFood.getSaturationModifier());
    }*/

    /**
     * Handles the food game logic.
     */
    public void onUpdate(EntityPlayer par1EntityPlayer) {

        if (!SSConfig.statusStamina) return;

        EnumDifficulty i = par1EntityPlayer.worldObj.difficultySetting;
        this.prevStaminaLevel = this.staminaLevel;

        if (this.staminaExhaustionLevel > 4.0F) {
            this.staminaExhaustionLevel -= 4.0F;

            if (this.staminaSaturationLevel > 0.0F) {
                this.staminaSaturationLevel = Math.max(this.staminaSaturationLevel - 1.0F, 0.0F);
            } else if (i.getDifficultyId() > 0 || SSConfig.peacefulStamina) {
                this.staminaLevel = Math.max(this.staminaLevel - 1, 0);
            }
        }

        if (par1EntityPlayer.worldObj.getGameRules().getGameRuleBooleanValue("naturalRegeneration") && this.staminaLevel >= 18 && par1EntityPlayer.shouldHeal()) {
            ++this.staminaTimer;

            if (this.staminaTimer >= 80) {
                par1EntityPlayer.heal(2.0F);
                this.addExhaustion(par1EntityPlayer, 1.0F);
                this.staminaTimer = 0;
            }
        } else if (this.staminaLevel <= 0) {
            ++this.staminaTimer;

            if (this.staminaTimer >= 80) {
                if (par1EntityPlayer.getHealth() > 10.0F || i.getDifficultyId() >= 3 || par1EntityPlayer.getHealth() > 1.0F && i.getDifficultyId() >= 2) {

                    if (!par1EntityPlayer.isPlayerSleeping()) {
                        //par1EntityPlayer.attackEntityFrom(DamageSource.cactus, 1.0F);
                        par1EntityPlayer.attackEntityFrom(DamageSource.starve, 1.0F);
                    }
                    //par1EntityPlayer.attackEntityFrom(DamageSource.starve, 1.0F);
                }

                this.staminaTimer = 0;
            }
        } else {
            this.staminaTimer = 0;
        }
    }

    /**
     * Reads food stats from an NBT object.
     */
    public void readNBT(NBTTagCompound par1NBTTagCompound) {
        if (par1NBTTagCompound.hasKey("staminalevel")) {
            this.staminaLevel = par1NBTTagCompound.getInteger("staminalevel");
            this.staminaTimer = par1NBTTagCompound.getInteger("staminaticktimer");
            this.staminaSaturationLevel = par1NBTTagCompound.getFloat("staminasaturationlevel");
            this.staminaExhaustionLevel = par1NBTTagCompound.getFloat("staminaexhaustionlevel");
        }
    }

    /**
     * Writes food stats to an NBT object.
     */
    public void writeNBT(NBTTagCompound par1NBTTagCompound) {
        par1NBTTagCompound.setInteger("staminalevel", this.staminaLevel);
        par1NBTTagCompound.setInteger("staminaticktimer", this.staminaTimer);
        par1NBTTagCompound.setFloat("staminasaturationlevel", this.staminaSaturationLevel);
        par1NBTTagCompound.setFloat("staminaexhaustionlevel", this.staminaExhaustionLevel);
    }

    /**Packetを飛ばす必要があるかどうか*/
    public boolean isPacket() {

        if (!SSConfig.statusStamina) return false;

        boolean flag = false;

        if (this.staminaLevel != this.lastStaminaLevel) {
            flag = true;
            this.lastStaminaLevel = this.staminaLevel;
        }

        if (this.staminaSaturationLevel != this.lastSaturationLevel) {

            flag = true;
            this.lastSaturationLevel = this.staminaSaturationLevel;

        }

        return flag;

    }

    /**
     * Get the player's food level.
     */
    public int getStaminaLevel() {

        if (!SSConfig.statusStamina) return MAX_STAMINA_LEVEL;

        return this.staminaLevel;
    }

    @SideOnly(Side.CLIENT)
    public int getPrevStaminaLevel() {
        return this.prevStaminaLevel;
    }

    /**
     * If foodLevel is not max.
     */
    public boolean needStamina() {

        if (!SSConfig.statusStamina) return false;

        return this.staminaLevel < MAX_STAMINA_LEVEL;
    }

    /**
     * adds input to foodExhaustionLevel to a max of 400
     */
    public void addExhaustion(EntityPlayer entityPlayer, float par1) {

        if (!SSConfig.statusStamina) return;

        PlayerStaminaEvent.Exhaustion event = new PlayerStaminaEvent.Exhaustion(entityPlayer, staminaLevel, staminaExhaustionLevel, par1);

        if (MinecraftForge.EVENT_BUS.post(event)) {
            //this.moistureLevel = prevLevel;
            //this.moistureSaturationLevel = prevSaturation;
        } else {
            this.staminaExhaustionLevel = MathHelper.clamp_float(event.newExhaustionLevel + this.staminaExhaustionLevel, 0, 400.0F);
        }

    }

    /**
     * Get the player's food saturation level.
     */
    public float getSaturationLevel() {

        if (!SSConfig.statusStamina) return MAX_STAMINA_LEVEL / 3.0F;

        return this.staminaSaturationLevel;
    }

    @SideOnly(Side.CLIENT)
    public void setStaminaLevel(int par1) {
        this.staminaLevel = par1;
    }

    @SideOnly(Side.CLIENT)
    public void setStaminaSaturationLevel(float par1) {
        this.staminaSaturationLevel = par1;
    }

}
