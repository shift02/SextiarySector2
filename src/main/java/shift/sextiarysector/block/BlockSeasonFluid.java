package shift.sextiarysector.block;

import java.util.Random;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialTransparent;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.Fluid;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSeasonFluid extends BlockFluidFinite {

    public static final Material season = new SSMaterial();

    public BlockSeasonFluid(Fluid fluid) {
        super(fluid, season);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
        if (p_149691_2_ == 0) {
            return this.getFluid().getStillIcon();
        } else {
            return this.getFluid().getFlowingIcon();
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_) {
        return this.getFluid().getColor();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random p_149734_5_) {

        if (p_149734_5_.nextInt(12) == 0) {

            float f = p_149734_2_ + 0.5F;
            float f1 = p_149734_3_ + 0.0F + p_149734_5_.nextFloat() * 6.0F / 16.0F;
            float f2 = p_149734_4_ + 0.5F;
            float f3 = 0.52F;
            float f4 = p_149734_5_.nextFloat() * 0.6F - 0.3F;

            p_149734_1_.spawnParticle("explode", f - f3, f1, f2 + f4, 0.0D, 0.05D, 0.0D);

            p_149734_1_.spawnParticle("explode", f + f3, f1, f2 + f4, 0.0D, 0.05D, 0.0D);

            p_149734_1_.spawnParticle("explode", f + f4, f1, f2 - f3, 0.0D, 0.05D, 0.0D);

            p_149734_1_.spawnParticle("explode", f + f4, f1, f2 + f3, 0.0D, 0.05D, 0.0D);

        }
    }

    //Material
    public static class SSMaterial extends MaterialTransparent {

        public SSMaterial() {
            super(MapColor.airColor);
            this.setNoPushMobility();
        }

    }

    /*
    @Override
    public boolean isAir(IBlockAccess world, int x, int y, int z)
    {
    	return true;//getMaterial() == Material.air;
    }*/

    /*
    @Override
    public boolean isReplaceable(IBlockAccess world, int x, int y, int z)
    {
    	return true;//blockMaterial.isReplaceable();
    }
    
    @Override
    public boolean isBlockSolid(IBlockAccess p_149747_1_, int p_149747_2_, int p_149747_3_, int p_149747_4_, int p_149747_5_)
    {
    	return false;//p_149747_1_.getBlock(p_149747_2_, p_149747_3_, p_149747_4_).getMaterial().isSolid();
    }*/
}
