package shift.sextiarysector.api.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;

public class PotionBurn extends PotionBase {

	public PotionBurn(int id, boolean isBadEffect, int liquidColor, int icon) {
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

		if (p_76394_1_.getHealth() > 1.0F)
		{
			p_76394_1_.attackEntityFrom(DamageSource.onFire, 1.0F);
		}

	}

	@Override
	public boolean isReady(int par1, int par2)
	{
		int k = 30 >> par2;
		return k > 0 ? par1 % k == 0 : true;
	}
}
