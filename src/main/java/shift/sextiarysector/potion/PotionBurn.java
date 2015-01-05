package shift.sextiarysector.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;

public class PotionBurn extends PotionSS{

	public PotionBurn(int p_i1573_1_, boolean p_i1573_2_, int p_i1573_3_,int icon) {
		super(p_i1573_1_, p_i1573_2_, p_i1573_3_, icon);
	}

	public void performEffect(EntityLivingBase p_76394_1_, int p_76394_2_)
    {
		if (p_76394_1_.getHealth() > 1.0F)
        {
            p_76394_1_.attackEntityFrom(DamageSource.onFire, 1.0F);
        }
    }

	public boolean isReady(int p_76397_1_, int amplifier)
    {
		return p_76397_1_ % (5 - amplifier) == 0;
    }

}
