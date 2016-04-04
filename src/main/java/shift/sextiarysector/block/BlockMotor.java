/*
* 作成者: Shift02
* 作成日: 2016/03/15 - 21:46:55
*/
package shift.sextiarysector.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.SextiarySector;

public class BlockMotor extends Block {

    @SideOnly(Side.CLIENT)
    private IIcon bottomIcon;
    @SideOnly(Side.CLIENT)
    private IIcon topIcon;

    private final boolean isSticky;

    public BlockMotor(boolean isSticky) {
        super(Material.rock);
        this.isSticky = isSticky;
        //this.setTickRandomly(true);

    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random p_149674_5_) {

        world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world));

        int meta = world.getBlockMetadata(x, y, z);

        //        if (meta / 6 == 0) {
        //            world.setBlockMetadataWithNotify(x, y, z, meta + 6, 2);
        //        } else {
        //            world.setBlockMetadataWithNotify(x, y, z, meta - 6, 2);
        //        }

        ForgeDirection direction = ForgeDirection.getOrientation(meta % 6);

        if (direction.equals(ForgeDirection.UP) && !this.isSticky) {

            Block block = world.getBlock(x, y + 1, z);

            ForgeDirection[] d = block.getValidRotations(world, x, y + 1, z);

            boolean f = block.rotateBlock(world, x, y + 1, z, d[0]);

            if (rotationTouch(world, x, y + 1, z)) {
                f = true;
            }

            if (f) {

                if (!world.isRemote) {
                    world.playSoundEffect(x, y + 1, z, block.stepSound.getStepResourcePath(), 1.0F, world.rand.nextFloat() * 0.1F + 0.6F);
                    world.markBlockForUpdate(x, y + 1, z);
                }

            }

        } else if (this.isSticky) {

            Block block = world.getBlock(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ);

            ForgeDirection[] d = block.getValidRotations(world, x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ);

            boolean f = false;

            if (d.length == 6) {
                f = block.rotateBlock(world, x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ, direction.getOpposite());
            } else {
                f = block.rotateBlock(world, x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ, d[0]);
            }

            if (rotationTouch(world, x, y + 1, z)) {
                f = true;
            }

            if (f) {

                if (!world.isRemote) {
                    world.markBlockForUpdate(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ);
                    world.playSoundEffect(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ, block.stepSound.getStepResourcePath(), 1.0F, world.rand.nextFloat() * 0.1F + 0.6F);
                }

            }

        }

    }

    private boolean rotationTouch(World world, int x, int y, int z) {

        for (int i = 2; i < 6; i++) {

            ForgeDirection d = ForgeDirection.getOrientation(i);

            if (!(world.getBlock(x + d.offsetX, y + d.offsetY, z + d.offsetZ) instanceof BlockTorch)) continue;

            int directionMeta = 6 - world.getBlockMetadata(x + d.offsetX, y + d.offsetY, z + d.offsetZ);

            if (directionMeta != d.ordinal()) continue;

            ForgeDirection dNext = d.getRotation(ForgeDirection.UP);

            if (!world.isAirBlock(x + dNext.offsetX, y + dNext.offsetY, z + dNext.offsetZ)) continue;

            Block block = world.getBlock(x + d.offsetX, y + d.offsetY, z + d.offsetZ);

            //block.rotateBlock(world, x + d.offsetX, y + d.offsetY, z + d.offsetZ, ForgeDirection.UP);

            int nweMeta = 6 - dNext.ordinal();//world.getBlockMetadata(x + d.offsetX, y + d.offsetY, z + d.offsetZ);

            world.setBlockToAir(x + d.offsetX, y + d.offsetY, z + d.offsetZ);

            world.setBlock(x + dNext.offsetX, y + dNext.offsetY, z + dNext.offsetZ, block, nweMeta, 2);

            world.markBlockForUpdate(x + dNext.offsetX * 2, y + dNext.offsetY * 2, z + dNext.offsetZ * 2);

            return true;

        }

        return false;

    }

    @Override
    public int tickRate(World p_149738_1_) {
        return 20;
    }

    @Override
    public void onBlockAdded(World p_149726_1_, int p_149726_2_, int p_149726_3_, int p_149726_4_) {
        super.onBlockAdded(p_149726_1_, p_149726_2_, p_149726_3_, p_149726_4_);
        //this.func_149930_e(p_149726_1_, p_149726_2_, p_149726_3_, p_149726_4_);
    }

    private void func_149930_e(World p_149930_1_, int p_149930_2_, int p_149930_3_, int p_149930_4_) {
        if (!p_149930_1_.isRemote) {
            Block block = p_149930_1_.getBlock(p_149930_2_, p_149930_3_, p_149930_4_ - 1);
            Block block1 = p_149930_1_.getBlock(p_149930_2_, p_149930_3_, p_149930_4_ + 1);
            Block block2 = p_149930_1_.getBlock(p_149930_2_ - 1, p_149930_3_, p_149930_4_);
            Block block3 = p_149930_1_.getBlock(p_149930_2_ + 1, p_149930_3_, p_149930_4_);
            byte b0 = 3;

            if (block.func_149730_j() && !block1.func_149730_j()) {
                b0 = 3;
            }

            if (block1.func_149730_j() && !block.func_149730_j()) {
                b0 = 2;
            }

            if (block2.func_149730_j() && !block3.func_149730_j()) {
                b0 = 5;
            }

            if (block3.func_149730_j() && !block2.func_149730_j()) {
                b0 = 4;
            }

            p_149930_1_.setBlockMetadataWithNotify(p_149930_2_, p_149930_3_, p_149930_4_, b0, 2);
        }
    }

    @Override
    public void onBlockPlacedBy(World p_149689_1_, int x, int y, int z, EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {

        int l = determineOrientation(p_149689_1_, x, y, z, p_149689_5_);

        p_149689_1_.setBlockMetadataWithNotify(x, y, z, l, 2);

        p_149689_1_.scheduleBlockUpdate(x, y, z, this, this.tickRate(p_149689_1_));

    }

    public static int determineOrientation(World p_150071_0_, int p_150071_1_, int p_150071_2_, int p_150071_3_, EntityLivingBase p_150071_4_) {

        if (MathHelper.abs((float) p_150071_4_.posX - p_150071_1_) < 2.0F && MathHelper.abs((float) p_150071_4_.posZ - p_150071_3_) < 2.0F) {
            double d0 = p_150071_4_.posY + 1.82D - p_150071_4_.yOffset;

            if (d0 - p_150071_2_ > 2.0D) {
                return 1;
            }

            if (p_150071_2_ - d0 > 0.0D) {
                return 0;
            }
        }

        int l = MathHelper.floor_double(p_150071_4_.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
        return l == 0 ? 2 : (l == 1 ? 5 : (l == 2 ? 3 : (l == 3 ? 4 : 0)));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side_, int meta) {

        ForgeDirection direction = ForgeDirection.getOrientation(meta % 6);
        ForgeDirection side = ForgeDirection.getOrientation(side_);

        if (direction.equals(side)) return this.topIcon;
        if (direction.getOpposite().equals(side)) return this.bottomIcon;

        return this.blockIcon;

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_) {
        this.blockIcon = p_149651_1_.registerIcon(this.getTextureName() + "_side");
        this.topIcon = p_149651_1_.registerIcon(this.isSticky ? this.getTextureName() + "_top_sticky" : this.getTextureName() + "_top_normal");
        this.bottomIcon = p_149651_1_.registerIcon(this.getTextureName() + "_bottom");
    }

    @Override
    public int getRenderType() {
        return SextiarySector.proxy.motorType;
    }

}
