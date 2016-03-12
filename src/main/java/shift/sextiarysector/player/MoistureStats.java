package shift.sextiarysector.player;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraftforge.common.MinecraftForge;
import shift.sextiarysector.Config;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.api.event.PlayerMoistureEvent;

public class MoistureStats extends CustomPlayerStats {

    //水分
    /** 水分 */
    private int moistureLevel = 20;
    private final static int MAX_MOISTURE_LEVEL = 20;

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

    public MoistureStats(EntityPlayer player) {
        super(player);
    }

    /**
     * Args: int foodLevel, float foodSaturationModifier
     */
    public void addStats(int par1, float par2) {
        int prevLevel = moistureLevel;
        float prevSaturation = moistureSaturationLevel;

        this.moistureLevel = MathHelper.clamp_int(par1 + this.moistureLevel, 0, MAX_MOISTURE_LEVEL);
        this.moistureSaturationLevel = MathHelper.clamp_float(Math.min(this.moistureSaturationLevel + par2, this.moistureLevel), 0.0F, MAX_MOISTURE_LEVEL);

        PlayerMoistureEvent event = new PlayerMoistureEvent(entityPlayer, moistureLevel, moistureSaturationLevel);

        if (MinecraftForge.EVENT_BUS.post(event)) {
            this.moistureLevel = prevLevel;
            this.moistureSaturationLevel = prevSaturation;
        } else {
            this.moistureLevel = MathHelper.clamp_int(event.newMoistureLevel, 0, MAX_MOISTURE_LEVEL);
            this.moistureSaturationLevel = MathHelper.clamp_float(event.newMoistureSaturationLevel, 0.0F, MAX_MOISTURE_LEVEL);
        }
    }

    /**
     * Handles the food game logic.
     */
    @Override
    public void onUpdate() {
        EnumDifficulty i = entityPlayer.worldObj.difficultySetting;
        this.prevMoistureLevel = this.moistureLevel;

        if (this.moistureExhaustionLevel > 4.0F) {
            this.moistureExhaustionLevel -= 4.0F;

            if (this.moistureSaturationLevel > 0.0F) {
                this.moistureSaturationLevel = Math.max(this.moistureSaturationLevel - 1.0F, 0.0F);
            } else if (i.getDifficultyId() > 0 || Config.peacefulMoisture) {
                this.moistureLevel = Math.max(this.moistureLevel - 1, 0);
            }
        }

        if (entityPlayer.worldObj.getGameRules().getGameRuleBooleanValue("naturalRegeneration") && this.moistureLevel >= 18 && entityPlayer.shouldHeal()) {

            ++this.moistureTimer;

            if (this.moistureTimer >= 160) {
                //par1EntityPlayer.heal(1.0F);
                this.addExhaustion(3.0F);
                this.moistureTimer = 0;
            }

        } else if (this.moistureLevel <= 0) {
            ++this.moistureTimer;

            if (this.moistureTimer >= 80) {
                if (entityPlayer.getHealth() > 10.0F || i.getDifficultyId() >= 3 || entityPlayer.getHealth() > 1.0F && i.getDifficultyId() >= 2) {
                    //par1EntityPlayer.attackEntityFrom(DamageSource.starve, 1.0F);
                    SextiarySectorAPI.addStaminaExhaustion(entityPlayer, 0.5f);
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
    @Override
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
    @Override
    public void writeNBT(NBTTagCompound par1NBTTagCompound) {
        par1NBTTagCompound.setInteger("moisturelevel", this.moistureLevel);
        par1NBTTagCompound.setInteger("moistureticktimer", this.moistureTimer);
        par1NBTTagCompound.setFloat("moisturesaturationlevel", this.moistureSaturationLevel);
        par1NBTTagCompound.setFloat("moistureexhaustionlevel", this.moistureExhaustionLevel);
    }

    public boolean isPacket() {

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
        return this.moistureLevel < MAX_MOISTURE_LEVEL;
    }

    /**
     * adds input to foodExhaustionLevel to a max of 40
     */
    public void addExhaustion(float par1) {
        float max = 40.0F;
        float prev = this.moistureExhaustionLevel;

        this.moistureExhaustionLevel = MathHelper.clamp_float(this.moistureExhaustionLevel + par1, 0.0F, max);

        PlayerMoistureEvent.Exhaustion event = new PlayerMoistureEvent.Exhaustion(entityPlayer, moistureExhaustionLevel);

        if (MinecraftForge.EVENT_BUS.post(event)) {
            this.moistureExhaustionLevel = prev;
        } else {
            this.moistureExhaustionLevel = MathHelper.clamp_float(event.newExhaustionLevel, 0.0F, max);
        }
    }

    /**
     * Get the player's food saturation level.
     */
    public float getSaturationLevel() {
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
