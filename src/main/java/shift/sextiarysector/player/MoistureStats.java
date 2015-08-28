package shift.sextiarysector.player;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.EnumDifficulty;
import shift.sextiarysector.Config;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MoistureStats {

	//水分
	/** 水分 */
	private int moistureLevel = 20;
	private final static int MAX_STAMINA_LEVEL = 20;
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
	public void addStats(int par1, float par2)
	{
		this.moistureLevel = Math.min(par1 + this.moistureLevel, 20);
		this.moistureSaturationLevel = Math.min(Math.min(this.moistureSaturationLevel + par2, this.moistureLevel), MAX_PREV_STAMINA_LEVEL);
	}

	/**
	 * Handles the food game logic.
	 */
	public void onUpdate(EntityPlayer par1EntityPlayer)
	{
		EnumDifficulty i = par1EntityPlayer.worldObj.difficultySetting;
		this.prevMoistureLevel = this.moistureLevel;

		if (this.moistureExhaustionLevel > 4.0F)
		{
			this.moistureExhaustionLevel -= 4.0F;

			if (this.moistureSaturationLevel > 0.0F)
			{
				this.moistureSaturationLevel = Math.max(this.moistureSaturationLevel - 1.0F, 0.0F);
			}
			else if (i.getDifficultyId() > 0 || Config.peacefulMoisture)
			{
				this.moistureLevel = Math.max(this.moistureLevel - 1, 0);
			}
		}

		if (par1EntityPlayer.worldObj.getGameRules().getGameRuleBooleanValue("naturalRegeneration") && this.moistureLevel >= 18 && par1EntityPlayer.shouldHeal())
		{
			++this.moistureTimer;

			if (this.moistureTimer >= 160)
			{
				par1EntityPlayer.heal(1.0F);
				this.addExhaustion(3.0F);
				this.moistureTimer = 0;
			}
		}
		else if (this.moistureLevel <= 0)
		{
			++this.moistureTimer;

			if (this.moistureTimer >= 80)
			{
				if (par1EntityPlayer.getHealth() > 10.0F || i.getDifficultyId() >= 3 || par1EntityPlayer.getHealth() > 1.0F && i.getDifficultyId() >= 2)
				{
					//par1EntityPlayer.attackEntityFrom(DamageSource.starve, 1.0F);
				}

				this.moistureTimer = 0;
			}
		}
		else
		{
			this.moistureTimer = 0;
		}
	}

	/**
	 * Reads food stats from an NBT object.
	 */
	public void readNBT(NBTTagCompound par1NBTTagCompound)
	{
		if (par1NBTTagCompound.hasKey("moisturelevel"))
		{
			this.moistureLevel = par1NBTTagCompound.getInteger("moisturelevel");
			this.moistureTimer = par1NBTTagCompound.getInteger("moistureticktimer");
			this.moistureSaturationLevel = par1NBTTagCompound.getFloat("moisturesaturationlevel");
			this.moistureExhaustionLevel = par1NBTTagCompound.getFloat("moistureexhaustionlevel");
		}
	}

	/**
	 * Writes food stats to an NBT object.
	 */
	public void writeNBT(NBTTagCompound par1NBTTagCompound)
	{
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
	public int getMoistureLevel()
	{
		return this.moistureLevel;
	}

	@SideOnly(Side.CLIENT)
	public int getPrevMoistureLevel()
	{
		return this.prevMoistureLevel;
	}

	/**
	 * If foodLevel is not max.
	 */
	public boolean needMoisture()
	{
		return this.moistureLevel < MAX_STAMINA_LEVEL;
	}

	/**
	 * adds input to foodExhaustionLevel to a max of 40
	 */
	public void addExhaustion(float par1)
	{
		this.moistureExhaustionLevel = Math.min(this.moistureExhaustionLevel + par1, 40.0F);
	}

	/**
	 * Get the player's food saturation level.
	 */
	public float getSaturationLevel()
	{
		return this.moistureSaturationLevel;
	}

	@SideOnly(Side.CLIENT)
	public void setMoistureLevel(int par1)
	{
		this.moistureLevel = par1;
	}

	@SideOnly(Side.CLIENT)
	public void setMoistureSaturationLevel(float par1)
	{
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
