package shift.sextiarysector.player;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraftforge.common.MinecraftForge;
import shift.sextiarysector.SSConfig;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.api.event.player.PlayerMoistureEvent;

public class MoistureStats {

    //水分
    /** 水分 */
    private int moistureLevel = 20;
    private final static int MAX_MOISTURE_LEVEL = 20;
    private final static int MAX_PREV_STAMINA_LEVEL = 20;

    /** 隠し水分ゲージ */
    private float moistureSaturationLevel = 5.0F;

    //Packet用
    private int lastMoistureLevel = 20;
    private float lastSaturationLevel = 0;

    /** 喉の渇きレベル これが増えると水分ゲージが減る */
    private float moistureExhaustionLevel;

    /** The player's food timer value. */
    private int moistureTimer;
    private int prevMoistureLevel = 20;

    /**
     * Args: int foodLevel, float foodSaturationModifier
     */
    public void addStats(EntityPlayer entityPlayer, int par1, float par2) {

        if (!SSConfig.statusMoisture) return;

        //this.moistureLevel = Math.min(par1 + this.moistureLevel, 20);
        //this.moistureSaturationLevel = Math.min(Math.min(this.moistureSaturationLevel + par2, this.moistureLevel), MAX_PREV_STAMINA_LEVEL);

        PlayerMoistureEvent.Add event = new PlayerMoistureEvent.Add(entityPlayer, moistureLevel, moistureSaturationLevel, par1, par2);

        if (MinecraftForge.EVENT_BUS.post(event)) {
            //this.moistureLevel = prevLevel;
            //this.moistureSaturationLevel = prevSaturation;
        } else {
            this.moistureLevel = MathHelper.clamp_int(event.newAddMoistureLevel + this.moistureLevel, 0, MAX_MOISTURE_LEVEL);
            this.moistureSaturationLevel = MathHelper.clamp_float(event.newAddMoistureSaturationLevel + this.moistureSaturationLevel, 0.0F, this.moistureLevel);
        }

    }

    /**
     * Handles the food game logic.
     */
    public void onUpdate(EntityPlayer par1EntityPlayer) {

        if (!SSConfig.statusMoisture) return;

        EnumDifficulty i = par1EntityPlayer.worldObj.difficultySetting;
        this.prevMoistureLevel = this.moistureLevel;

        if (this.moistureExhaustionLevel > 4.0F) {
            this.moistureExhaustionLevel -= 4.0F;

            if (this.moistureSaturationLevel > 0.0F) {
                this.moistureSaturationLevel = Math.max(this.moistureSaturationLevel - 1.0F, 0.0F);
            } else if (i.getDifficultyId() > 0 || SSConfig.peacefulMoisture) {
                this.moistureLevel = Math.max(this.moistureLevel - 1, 0);
            }
        }

        if (par1EntityPlayer.worldObj.getGameRules().getGameRuleBooleanValue("naturalRegeneration") && this.moistureLevel >= 18 && par1EntityPlayer.shouldHeal()) {

            ++this.moistureTimer;

            if (this.moistureTimer >= 160) {
                //par1EntityPlayer.heal(1.0F);
                this.addExhaustion(par1EntityPlayer, 3.0F);
                this.moistureTimer = 0;
            }

        } else if (this.moistureLevel <= 0) {
            ++this.moistureTimer;

            if (this.moistureTimer >= 80) {
                if (par1EntityPlayer.getHealth() > 10.0F || i.getDifficultyId() >= 3 || par1EntityPlayer.getHealth() > 1.0F && i.getDifficultyId() >= 2) {
                    //par1EntityPlayer.attackEntityFrom(DamageSource.starve, 1.0F);
                    SextiarySectorAPI.addStaminaExhaustion(par1EntityPlayer, 0.5f);
                }

                this.moistureTimer = 0;
            }
        } else {
            this.moistureTimer = 0;
        }
    }

    /**
     * Reads food stats from an NBT object.
     */
    public void readNBT(NBTTagCompound par1NBTTagCompound) {
        if (par1NBTTagCompound.hasKey("moisturelevel")) {
            this.moistureLevel = par1NBTTagCompound.getInteger("moisturelevel");
            this.moistureTimer = par1NBTTagCompound.getInteger("moistureticktimer");
            this.moistureSaturationLevel = par1NBTTagCompound.getFloat("moisturesaturationlevel");
            this.moistureExhaustionLevel = par1NBTTagCompound.getFloat("moistureexhaustionlevel");
        }
    }

    /**
     * Writes food stats to an NBT object.
     */
    public void writeNBT(NBTTagCompound par1NBTTagCompound) {
        par1NBTTagCompound.setInteger("moisturelevel", this.moistureLevel);
        par1NBTTagCompound.setInteger("moistureticktimer", this.moistureTimer);
        par1NBTTagCompound.setFloat("moisturesaturationlevel", this.moistureSaturationLevel);
        par1NBTTagCompound.setFloat("moistureexhaustionlevel", this.moistureExhaustionLevel);
    }

    public boolean isPacket() {

        if (!SSConfig.statusMoisture) return false;

        boolean flag = false;

        if (this.moistureLevel != this.lastMoistureLevel) {
            flag = true;
            this.lastMoistureLevel = this.moistureLevel;
        }

        if (this.moistureSaturationLevel != this.lastSaturationLevel) {

            flag = true;
            this.lastSaturationLevel = this.moistureSaturationLevel;

        }

        return flag;

    }

    /**
     * Get the player's moisture level.
     */
    public int getMoistureLevel() {

        if (!SSConfig.statusMoisture) return MAX_MOISTURE_LEVEL;

        return this.moistureLevel;
    }

    @SideOnly(Side.CLIENT)
    public int getPrevMoistureLevel() {
        return this.prevMoistureLevel;
    }

    /**
     * If foodLevel is not max.
     */
    public boolean needMoisture() {

        if (!SSConfig.statusMoisture) return false;

        return this.moistureLevel < MAX_MOISTURE_LEVEL;
    }

    /**
     * adds input to foodExhaustionLevel to a max of 40
     */
    public void addExhaustion(EntityPlayer entityPlayer, float par1) {

        if (!SSConfig.statusMoisture) return;

        PlayerMoistureEvent.Exhaustion event = new PlayerMoistureEvent.Exhaustion(entityPlayer, moistureLevel, moistureExhaustionLevel, par1);

        if (MinecraftForge.EVENT_BUS.post(event)) {
            //this.moistureLevel = prevLevel;
            //this.moistureSaturationLevel = prevSaturation;
        } else {
            this.moistureExhaustionLevel = MathHelper.clamp_float(event.newExhaustionLevel + this.moistureExhaustionLevel, 0, 40.0F);
        }

    }

    /**
     * Get the player's food saturation level.
     */
    public float getSaturationLevel() {

        if (!SSConfig.statusMoisture) return MAX_MOISTURE_LEVEL;

        return this.moistureSaturationLevel;
    }

    @SideOnly(Side.CLIENT)
    public void setMoistureLevel(int par1) {
        this.moistureLevel = par1;
    }

    @SideOnly(Side.CLIENT)
    public void setMoistureSaturationLevel(float par1) {
        this.moistureSaturationLevel = par1;
    }

    @SideOnly(Side.SERVER)
    public int getLastMoistureLevel() {
        return lastMoistureLevel;
    }

    @SideOnly(Side.SERVER)
    public void setLastMoistureLevel(int lastMoistureLevel) {
        this.lastMoistureLevel = lastMoistureLevel;
    }

    @SideOnly(Side.SERVER)
    public float getLastExhaustionLevel() {
        return lastSaturationLevel;
    }

    @SideOnly(Side.SERVER)
    public void setLastExhaustionLevel(float lastExhaustionLevel) {
        this.lastSaturationLevel = lastExhaustionLevel;
    }

}
