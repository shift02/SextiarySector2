package shift.sextiarysector.block;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockHotSprings extends BlockSSFluid {

    public BlockHotSprings(Fluid fluid) {
        super(fluid);
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random p_149734_5_) {

        if (p_149734_1_.isAirBlock(p_149734_2_, p_149734_3_ + 1, p_149734_4_) && p_149734_5_.nextInt(3) == 0) {

            float f = (float) p_149734_2_ + 0.5F;
            float f1 = (float) p_149734_3_ + 0.0F + p_149734_5_.nextFloat() * 6.0F / 16.0F;
            float f2 = (float) p_149734_4_ + 0.5F;
            float f3 = 0.52F;
            float f4 = p_149734_5_.nextFloat() * 0.6F - 0.3F;

            p_149734_1_.spawnParticle("explode", (double) (f - f3), (double) f1, (double) (f2 + f4), 0.0D, 0.05D, 0.0D);

            p_149734_1_.spawnParticle("explode", (double) (f + f3), (double) f1, (double) (f2 + f4), 0.0D, 0.05D, 0.0D);

            p_149734_1_.spawnParticle("explode", (double) (f + f4), (double) f1, (double) (f2 - f3), 0.0D, 0.05D, 0.0D);

            p_149734_1_.spawnParticle("explode", (double) (f + f4), (double) f1, (double) (f2 + f3), 0.0D, 0.05D, 0.0D);

        }
    }

}
