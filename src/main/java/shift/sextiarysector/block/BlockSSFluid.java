package shift.sextiarysector.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import shift.sextiarysector.api.SextiarySectorAPI;

public class BlockSSFluid extends BlockFluidClassic {

    //public static final Material water = new SSMaterial();

    int field_149815_a;

    public BlockSSFluid(Fluid fluid) {
        super(fluid, Material.water);
        this.setCreativeTab(SextiarySectorAPI.TabSSFluid);
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        super.updateTick(world, x, y, z, rand);

        byte b0 = 1;

        int l = this.func_149804_e(world, x, y, z);

        if (l > 0) {

            byte b1 = -100;
            this.field_149815_a = 0;
            int l1 = this.func_149810_a(world, x - 1, y, z, b1);
            l1 = this.func_149810_a(world, x + 1, y, z, l1);
            l1 = this.func_149810_a(world, x, y, z - 1, l1);
            l1 = this.func_149810_a(world, x, y, z + 1, l1);
            int j1 = l1 + b0;

            if (j1 >= 8 || l1 < 0) {
                j1 = -1;
            }

            if (this.func_149804_e(world, x, y + 1, z) >= 0) {
                int k1 = this.func_149804_e(world, x, y + 1, z);

                if (k1 >= 8) {
                    j1 = k1;
                } else {
                    j1 = k1 + 8;
                }
            }

            if (this.field_149815_a >= 2 && this.blockMaterial == Material.water) {
                if (world.getBlock(x, y - 1, z).getMaterial().isSolid()) {
                    j1 = 0;
                } else if (world.getBlock(x, y - 1, z).getMaterial() == this.blockMaterial && world.getBlockMetadata(x, y - 1, z) == 0) {
                    j1 = 0;
                }
            }

            if (j1 != l) {

                if (j1 == 0) {
                    world.setBlockMetadataWithNotify(x, y, z, j1, 2);
                    world.notifyBlocksOfNeighborChange(x, y, z, this);
                }
            }
        }

    }

    protected int func_149810_a(World p_149810_1_, int p_149810_2_, int p_149810_3_, int p_149810_4_, int p_149810_5_) {

        int i1 = this.func_149804_e(p_149810_1_, p_149810_2_, p_149810_3_, p_149810_4_);

        if (i1 < 0) {
            return p_149810_5_;
        } else {
            if (i1 == 0) {
                ++this.field_149815_a;
            }

            if (i1 >= 8) {
                i1 = 0;
            }

            return p_149810_5_ >= 0 && i1 >= p_149810_5_ ? p_149810_5_ : i1;
        }
    }

    protected int func_149804_e(World p_149804_1_, int p_149804_2_, int p_149804_3_, int p_149804_4_) {
        return p_149804_1_.getBlock(p_149804_2_, p_149804_3_, p_149804_4_).getMaterial() == this.blockMaterial && p_149804_1_.getBlock(p_149804_2_, p_149804_3_, p_149804_4_) == this ? p_149804_1_.getBlockMetadata(p_149804_2_, p_149804_3_, p_149804_4_) : -1;
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
    public void registerBlockIcons(IIconRegister p_149651_1_) {
        //this.blockIcon = p_149651_1_.registerIcon(this.getTextureName());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_) {
        return this.getFluid().getColor();
    }

    @Override
    public boolean canDisplace(IBlockAccess world, int x, int y, int z) {

        Block block = world.getBlock(x, y, z);

        if (!(block instanceof BlockFluidBase)) {
            if (world.getBlock(x, y, z).getMaterial().isLiquid()) return false;
        }

        if (block == this && world.getBlockMetadata(x, y, z) != 0) return true;

        return super.canDisplace(world, x, y, z);

    }

    @Override
    public boolean displaceIfPossible(World world, int x, int y, int z) {

        Block block = world.getBlock(x, y, z);

        if (!(block instanceof BlockFluidBase)) {

            if (world.getBlock(x, y, z).getMaterial().isLiquid()) return false;

        }

        return super.displaceIfPossible(world, x, y, z);

    }

    //
    //    public static class SSMaterial extends MaterialLiquid {
    //
    //        public SSMaterial() {
    //            super(MapColor.waterColor);
    //            this.setNoPushMobility();
    //        }
    //
    //    }

}
