package shift.sextiarysector.player;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraftforge.common.MinecraftForge;
import shift.sextiarysector.Config;
import shift.sextiarysector.api.event.PlayerStaminaEvent;

public class StaminaStats extends CustomPlayerStats {

    /** The player's stamina level. */
    private int staminaLevel = 100;
    private final static int MAX_STAMINA_LEVEL = 100;

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

    public StaminaStats(EntityPlayer player) {
        super(player);
    }

    /**
     * Args: int staminaLevel, float staminaSaturationModifier
     */
    public void addStats(int par1, float par2) {
        int prevLevel = staminaLevel;
        float prevSaturation = staminaSaturationLevel;

        this.staminaLevel = MathHelper.clamp_int(par1 + this.staminaLevel, 0, MAX_STAMINA_LEVEL);
        this.staminaSaturationLevel = MathHelper.clamp_float(Math.min(this.staminaSaturationLevel + par1 * par2 * 2.0F, this.staminaLevel), 0.0F, MAX_STAMINA_LEVEL / 2);

        PlayerStaminaEvent event = new PlayerStaminaEvent(entityPlayer, staminaLevel, staminaSaturationLevel);

        if (MinecraftForge.EVENT_BUS.post(event)) {
            this.staminaLevel = prevLevel;
            this.staminaSaturationLevel = prevSaturation;
        } else {
            this.staminaLevel = MathHelper.clamp_int(event.newStaminaLevel, 0, MAX_STAMINA_LEVEL);
            this.staminaSaturationLevel = MathHelper.clamp_float(event.newStaminaSaturationLevel, 0.0F, MAX_STAMINA_LEVEL / 2);
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
    @Override
    public void onUpdate() {
        EnumDifficulty i = entityPlayer.worldObj.difficultySetting;
        this.prevStaminaLevel = this.staminaLevel;

        if (this.staminaExhaustionLevel > 4.0F) {
            this.staminaExhaustionLevel -= 4.0F;

            if (this.staminaSaturationLevel > 0.0F) {
                this.staminaSaturationLevel = Math.max(this.staminaSaturationLevel - 1.0F, 0.0F);
            } else if (i.getDifficultyId() > 0 || Config.peacefulStamina) {
                this.staminaLevel = Math.max(this.staminaLevel - 1, 0);
            }
        }

        if (entityPlayer.worldObj.getGameRules().getGameRuleBooleanValue("naturalRegeneration") && this.staminaLevel >= 18 && entityPlayer.shouldHeal()) {
            ++this.staminaTimer;

            if (this.staminaTimer >= 80) {
                entityPlayer.heal(2.0F);
                this.addExhaustion(1.0F);
                this.staminaTimer = 0;
            }
        } else if (this.staminaLevel <= 0) {
            ++this.staminaTimer;

            if (this.staminaTimer >= 80) {
                if (entityPlayer.getHealth() > 10.0F || i.getDifficultyId() >= 3 || entityPlayer.getHealth() > 1.0F && i.getDifficultyId() >= 2) {

                    if (!entityPlayer.isPlayerSleeping()) {
                        //par1EntityPlayer.attackEntityFrom(DamageSource.cactus, 1.0F);
                        entityPlayer.attackEntityFrom(DamageSource.starve, 1.0F);
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
    @Override
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
    @Override
    public void writeNBT(NBTTagCompound par1NBTTagCompound) {
        par1NBTTagCompound.setInteger("staminalevel", this.staminaLevel);
        par1NBTTagCompound.setInteger("staminaticktimer", this.staminaTimer);
        par1NBTTagCompound.setFloat("staminasaturationlevel", this.staminaSaturationLevel);
        par1NBTTagCompound.setFloat("staminaexhaustionlevel", this.staminaExhaustionLevel);
    }

    /**Packetを飛ばす必要があるかどうか*/
    public boolean isPacket() {

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
        return this.staminaLevel < MAX_STAMINA_LEVEL;
    }

    /**
     * adds input to foodExhaustionLevel to a max of 400
     */
    public void addExhaustion(float par1) {
        float max = 400.0F;
        float prev = this.staminaExhaustionLevel;

        this.staminaExhaustionLevel = MathHelper.clamp_float(this.staminaExhaustionLevel + par1, 0.0F, max);

        PlayerStaminaEvent.Exhaustion event = new PlayerStaminaEvent.Exhaustion(entityPlayer, staminaExhaustionLevel);

        if (MinecraftForge.EVENT_BUS.post(event)) {
            this.staminaExhaustionLevel = prev;
        } else {
            this.staminaExhaustionLevel = MathHelper.clamp_float(event.newExhaustionLevel, 0.0F, max);
        }
    }

    /**
     * Get the player's food saturation level.
     */
    public float getSaturationLevel() {
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
