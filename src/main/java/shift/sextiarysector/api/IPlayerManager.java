package shift.sextiarysector.api;

import net.minecraft.entity.player.EntityPlayer;

public interface IPlayerManager {

    public void addMoistureStats(EntityPlayer entityPlayer, int par1, float par2);

    public int getMoistureLevel(EntityPlayer entityPlayer);

    public void addMoistureExhaustion(EntityPlayer entityPlayer, float par1);

    public void addStaminaStats(EntityPlayer entityPlayer, int par1, float par2);

    public int getStaminaLevel(EntityPlayer entityPlayer);

    public void addStaminaExhaustion(EntityPlayer entityPlayer, float par1);

}
