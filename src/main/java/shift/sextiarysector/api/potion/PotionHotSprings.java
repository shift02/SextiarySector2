package shift.sextiarysector.api.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import shift.sextiarysector.api.SextiarySectorAPI;

public class PotionHotSprings extends PotionBase {

	public PotionHotSprings(int id, boolean isBadEffect, int liquidColor, int icon) {
		super(id, isBadEffect, liquidColor, icon);
	}

	public static final ResourceLocation rl = new ResourceLocation("sextiarysector", "textures/guis/inventory_ss_potion.png");

	@Override
	protected ResourceLocation getPotionResource() {
		return rl;
	}

	@Override
	public void performEffect(EntityLivingBase p_76394_1_, int p_76394_2_)
	{

		if (!(p_76394_1_ instanceof EntityPlayer)) return;

		EntityPlayer player = (EntityPlayer) p_76394_1_;

		if (SextiarySectorAPI.getMoistureLevel(player) < 3) return;

		SextiarySectorAPI.addStaminaStats(player, 3, 0.7f);
		SextiarySectorAPI.addMoistureExhaustion(player, 1.9f);
	}

	@Override
	public boolean isReady(int par1, int par2)
	{
		int k = 70 >> par2;
		return k > 0 ? par1 % k == 0 : true;
	}

}
