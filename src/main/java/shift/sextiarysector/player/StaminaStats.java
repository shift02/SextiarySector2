package shift.sextiarysector.player;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import shift.sextiarysector.Config;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class StaminaStats {

	/** The player's stamina level. */
    private int staminaLevel = 100;
    private final static int MAX_STAMINA_LEVEL = 100;
	private final static int MAX_PREV_STAMINA_LEVEL = 20;

    /** The player's food saturation. */
    private float staminaSaturationLevel = 5.0F;

    //Packet用
    private int lastStaminaLevel = 100 ;
    private float lastSaturationLevel = 0;

    /** The player's food exhaustion. */
    private float staminaExhaustionLevel;

    /** The player's food timer value. */
    private int staminaTimer;
    private int prevStaminaLevel = 20;

    /**
     * Args: int staminaLevel, float staminaSaturationModifier
     */
    public void addStats(int par1, float par2)
    {
        this.staminaLevel = Math.min(par1 + this.staminaLevel, MAX_STAMINA_LEVEL);
        this.staminaSaturationLevel = Math.min(this.staminaSaturationLevel + par1 * par2 * 2.0F, this.staminaLevel);
    }

    /*
    public void addStats(IStaminaFood par1ItemStaminaFood)
    {
        this.addStats(par1ItemStaminaFood.getHealAmount(), par1ItemStaminaFood.getSaturationModifier());
    }*/

    /**
     * Handles the food game logic.
     */
    public void onUpdate(EntityPlayer par1EntityPlayer)
    {
        EnumDifficulty i = par1EntityPlayer.worldObj.difficultySetting;
        this.prevStaminaLevel = this.staminaLevel;

        if (this.staminaExhaustionLevel > 4.0F)
        {
            this.staminaExhaustionLevel -= 4.0F;

            if (this.staminaSaturationLevel > 0.0F)
            {
                this.staminaSaturationLevel = Math.max(this.staminaSaturationLevel - 1.0F, 0.0F);
            }
            else if (i.getDifficultyId() > 0 || Config.peacefulStamina)
            {
                this.staminaLevel = Math.max(this.staminaLevel - 1, 0);
            }
        }

        if (par1EntityPlayer.worldObj.getGameRules().getGameRuleBooleanValue("naturalRegeneration") && this.staminaLevel >= 18 && par1EntityPlayer.shouldHeal())
        {
            ++this.staminaTimer;

            if (this.staminaTimer >= 160)
            {
                par1EntityPlayer.heal(1.0F);
                this.addExhaustion(3.0F);
                this.staminaTimer = 0;
            }
        }
        else if (this.staminaLevel <= 0)
        {
            ++this.staminaTimer;

            if (this.staminaTimer >= 80)
            {
                if (par1EntityPlayer.getHealth() > 10.0F || i.getDifficultyId() >= 3 || par1EntityPlayer.getHealth() > 1.0F && i.getDifficultyId() >= 2)
                {
                	if(!par1EntityPlayer.isPlayerSleeping()){
                		par1EntityPlayer.attackEntityFrom(DamageSource.starve, 1.0F);
                	}
                    //par1EntityPlayer.attackEntityFrom(DamageSource.starve, 1.0F);
                }

                this.staminaTimer = 0;
            }
        }
        else
        {
            this.staminaTimer = 0;
        }
    }

    /**
     * Reads food stats from an NBT object.
     */
    public void readNBT(NBTTagCompound par1NBTTagCompound)
    {
        if (par1NBTTagCompound.hasKey("staminalevel"))
        {
            this.staminaLevel = par1NBTTagCompound.getInteger("staminalevel");
            this.staminaTimer = par1NBTTagCompound.getInteger("staminaticktimer");
            this.staminaSaturationLevel = par1NBTTagCompound.getFloat("staminasaturationlevel");
            this.staminaExhaustionLevel = par1NBTTagCompound.getFloat("staminaexhaustionlevel");
        }
    }

    /**
     * Writes food stats to an NBT object.
     */
    public void writeNBT(NBTTagCompound par1NBTTagCompound)
    {
        par1NBTTagCompound.setInteger("staminalevel", this.staminaLevel);
        par1NBTTagCompound.setInteger("staminaticktimer", this.staminaTimer);
        par1NBTTagCompound.setFloat("staminasaturationlevel", this.staminaSaturationLevel);
        par1NBTTagCompound.setFloat("staminaexhaustionlevel", this.staminaExhaustionLevel);
    }

    /**Packetを飛ばす必要があるかどうか*/
    public boolean isPacket(){

    	boolean flag = false;

    	if(this.staminaLevel!=this.lastStaminaLevel){
    		flag= true;
    		this.lastStaminaLevel=this.staminaLevel;
    	}

    	if(this.staminaSaturationLevel!=this.lastSaturationLevel){

    		flag=true;
    		this.lastSaturationLevel=this.staminaSaturationLevel;

    	}

		return flag;

    }

    /**
     * Get the player's food level.
     */
    public int getStaminaLevel()
    {
        return this.staminaLevel;
    }

    @SideOnly(Side.CLIENT)
    public int getPrevStaminaLevel()
    {
        return this.prevStaminaLevel;
    }

    /**
     * If foodLevel is not max.
     */
    public boolean needStamina()
    {
        return this.staminaLevel < MAX_STAMINA_LEVEL;
    }

    /**
     * adds input to foodExhaustionLevel to a max of 400
     */
    public void addExhaustion(float par1)
    {
        this.staminaExhaustionLevel = Math.min(this.staminaExhaustionLevel + par1, 400.0F);
    }

    /**
     * Get the player's food saturation level.
     */
    public float getSaturationLevel()
    {
        return this.staminaSaturationLevel;
    }

    @SideOnly(Side.CLIENT)
    public void setStaminaLevel(int par1)
    {
        this.staminaLevel = par1;
    }

    @SideOnly(Side.CLIENT)
    public void setStaminaSaturationLevel(float par1)
    {
        this.staminaSaturationLevel = par1;
    }

}
