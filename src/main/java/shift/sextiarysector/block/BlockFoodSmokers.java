package shift.sextiarysector.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.api.gearforce.item.IHammer;
import shift.sextiarysector.tileentity.TileEntityFluidMachineBase;
import shift.sextiarysector.tileentity.TileEntityFoodSmokers;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFoodSmokers extends BlockFluidMachineBase {

    @SideOnly(Side.CLIENT)
    private IIcon furnaceIconTop;
    @SideOnly(Side.CLIENT)
    private IIcon[] furnaceIconFront;
    @SideOnly(Side.CLIENT)
    private IIcon furnaceIconTopOn;

    public BlockFoodSmokers() {
        super(Material.iron);
        this.setHardness(1.0F);
        this.setStepSound(soundTypeMetal);
    }

    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        if (par1World.isRemote) {
            return true;
        } else {
            TileEntityFluidMachineBase tileentityfurnace = (TileEntityFluidMachineBase) par1World.getTileEntity(par2, par3, par4);

            if (par5EntityPlayer.getCurrentEquippedItem() != null) {
                if (par5EntityPlayer.getCurrentEquippedItem().getItem() instanceof IHammer
                        && ((IHammer) par5EntityPlayer.getCurrentEquippedItem().getItem()).canUse(par5EntityPlayer.getCurrentEquippedItem(), par5EntityPlayer, 2)) {

                    tileentityfurnace.on = !tileentityfurnace.on;
                    ((IHammer) par5EntityPlayer.getCurrentEquippedItem().getItem()).use(par5EntityPlayer.getCurrentEquippedItem(), par5EntityPlayer, 2);
                    par1World.markBlockForUpdate(par2, par3, par4);
                    return true;

                }
            }

            if (tileentityfurnace != null) {
                par5EntityPlayer.openGui(SextiarySector.instance, 2, par1World, par2, par3, par4);
            }

            return true;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2) {

        return par1 == 1 ? this.furnaceIconTop : (par1 == 0 ? this.furnaceIconTop : (par1 != 3 ? this.blockIcon : this.furnaceIconFront[1]));

    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess p_149673_1_, int x, int y, int z, int side) {

        TileEntityFoodSmokers tileEntity = (TileEntityFoodSmokers) p_149673_1_.getTileEntity(x, y, z);

        int meta = tileEntity.getDirection().ordinal();//p_149673_1_.getBlockMetadata(x, y, z);

        return side == 1 ? (tileEntity.on ? this.furnaceIconTopOn : this.furnaceIconTop)
                : (side == 0 ? this.furnaceIconTop : (side != meta ? this.blockIcon : (tileEntity.isFuel() ? this.furnaceIconFront[0] : this.furnaceIconFront[1])));

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        this.blockIcon = par1IconRegister.registerIcon("sextiarysector:food_smokers_side");
        this.furnaceIconFront = new IIcon[2];
        this.furnaceIconFront[0] = par1IconRegister.registerIcon("sextiarysector:food_smokers_front_on");
        this.furnaceIconFront[1] = par1IconRegister.registerIcon("sextiarysector:food_smokers_front_off");
        this.furnaceIconTop = par1IconRegister.registerIcon("sextiarysector:food_smokers_top");
        this.furnaceIconTopOn = par1IconRegister.registerIcon("sextiarysector:food_smokers_top_on");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random p_149734_5_) {
        TileEntityFluidMachineBase tileentityfurnace = (TileEntityFluidMachineBase) p_149734_1_.getTileEntity(p_149734_2_, p_149734_3_, p_149734_4_);

        if (tileentityfurnace.isFuel()) {
            int l = tileentityfurnace.getDirection().ordinal();

            for (int i = 0; i < 5; i++) {

                float f = p_149734_2_ + 0.5F;
                float f1 = p_149734_3_ + 0.0F + p_149734_5_.nextFloat() * 6.0F / 16.0F;
                float f2 = p_149734_4_ + 0.5F;
                float f3 = 0.52F;
                float f4 = p_149734_5_.nextFloat() * 0.6F - 0.3F;

                if (l == 4) {
                    p_149734_1_.spawnParticle("explode", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
                    p_149734_1_.spawnParticle("flame", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
                } else if (l == 5) {
                    p_149734_1_.spawnParticle("explode", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
                    p_149734_1_.spawnParticle("flame", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
                } else if (l == 2) {
                    p_149734_1_.spawnParticle("explode", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
                    p_149734_1_.spawnParticle("flame", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
                } else if (l == 3) {
                    p_149734_1_.spawnParticle("explode", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
                    p_149734_1_.spawnParticle("flame", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
                }

            }

        }
    }

    @Override
    public TileEntity createNewTileEntity(World par1World, int p_149915_2_) {
        return new TileEntityFoodSmokers();
    }

}
