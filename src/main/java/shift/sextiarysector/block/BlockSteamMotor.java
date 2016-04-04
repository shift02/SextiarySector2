package shift.sextiarysector.block;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.tileentity.TileEntitySteamMotor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSteamMotor extends BlockEnergyMotor {

    @Override
    public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        if (super.onBlockActivated(par1World, x, y, z, par5EntityPlayer, par6, par7, par8, par9)) {
            return true;
        }

        if (par1World.isRemote) {
            return true;
        } else {
            TileEntitySteamMotor tileentityfurnace = (TileEntitySteamMotor) par1World.getTileEntity(x, y, z);
            if (tileentityfurnace != null) {
                par5EntityPlayer.openGui(SextiarySector.instance, 60, par1World, x, y, z);
            }

            return true;
        }
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntitySteamMotor();
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random p_149734_5_) {
        TileEntitySteamMotor tileentityfurnace = (TileEntitySteamMotor) p_149734_1_.getTileEntity(p_149734_2_, p_149734_3_, p_149734_4_);

        if (tileentityfurnace.canWork()) {
            int l = tileentityfurnace.getDirection().ordinal();

            for (int i = 0; i < 2; i++) {

                float f = (float) p_149734_2_ + 0.5F;
                float f1 = (float) p_149734_3_ + 0.0F + p_149734_5_.nextFloat() * 6.0F / 16.0F;
                float f2 = (float) p_149734_4_ + 0.5F;
                float f3 = 0.52F;
                float f4 = p_149734_5_.nextFloat() * 0.6F - 0.3F;

                p_149734_1_.spawnParticle("explode", (double) (f - f3), (double) f1, (double) (f2 + f4), 0.0D, 0.0D, 0.0D);

                p_149734_1_.spawnParticle("explode", (double) (f + f3), (double) f1, (double) (f2 + f4), 0.0D, 0.0D, 0.0D);

                p_149734_1_.spawnParticle("explode", (double) (f + f4), (double) f1, (double) (f2 - f3), 0.0D, 0.0D, 0.0D);

                p_149734_1_.spawnParticle("explode", (double) (f + f4), (double) f1, (double) (f2 + f3), 0.0D, 0.0D, 0.0D);

            }

        }
    }

    @Override
    public int getRenderType() {
        return SextiarySector.proxy.steamMotorType;
    }
}
