package shift.sextiarysector.world.biome;

import java.awt.Color;

import net.minecraft.world.biome.BiomeGenDesert;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BiomeGenMagicDesert extends  BiomeGenDesert{

	public BiomeGenMagicDesert(int p_i1977_1_) {
		super(p_i1977_1_);
	}

	@SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float p_76731_1_)
    {
        p_76731_1_ /= 3.0F;

        if (p_76731_1_ < -1.0F)
        {
            p_76731_1_ = -1.0F;
        }

        if (p_76731_1_ > 1.0F)
        {
            p_76731_1_ = 1.0F;
        }

        return Color.getHSBColor(0.62222224F - p_76731_1_ * 0.05F, 0.5F + p_76731_1_ * 0.1F, 1.0F).getRGB();
    }

}
