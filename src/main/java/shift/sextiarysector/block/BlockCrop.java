package shift.sextiarysector.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import shift.sextiarysector.SSCrops;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.api.agriculture.CropRendererType;
import shift.sextiarysector.api.agriculture.ICrop;
import shift.sextiarysector.api.agriculture.TileFarmland;
import shift.sextiarysector.tileentity.TileEntityCrop;

public class BlockCrop extends BlockContainer {

    public BlockCrop() {
        super(Material.plants);
        this.setHardness(3.5F);
        this.setStepSound(soundTypeGrass);
        this.setCreativeTab(SextiarySectorAPI.TabSSAgriculture);
    }

    //作物に橋渡し
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {

        TileEntityCrop tileCrop = (TileEntityCrop) world.getTileEntity(x, y, z);

        TileFarmland tileFarmland = (TileFarmland) world.getTileEntity(x, y - 1, z);

        if (tileCrop.getCrop() == null) return false;

        //作物のクリック処理を呼ぶ
        return tileCrop.getCrop().click(tileCrop, tileFarmland, par5EntityPlayer);

    }

    @Override
    public float getBlockHardness(World world, int x, int y, int z) {

        TileEntityCrop tileCrop = (TileEntityCrop) world.getTileEntity(x, y, z);

        TileFarmland tileFarmland = (TileFarmland) world.getTileEntity(x, y - 1, z);

        if (tileCrop.getCrop() == null) return super.getBlockHardness(world, x, y, z);

        //作物の処理を呼ぶ
        return tileCrop.getCrop().getHardness(tileCrop, tileFarmland);

    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z) {

        TileEntityCrop tileCrop = (TileEntityCrop) world.getTileEntity(x, y, z);

        TileFarmland tileFarmland = (TileFarmland) world.getTileEntity(x, y - 1, z);

        if (tileCrop.getCrop() == null) return super.getLightValue(world, x, y, z);

        //作物の処理を呼ぶ
        return tileCrop.getCrop().getLightValue(tileCrop, tileFarmland);

    }

    //接触時
    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity p_149670_5_) {
        TileEntityCrop tileCrop = (TileEntityCrop) world.getTileEntity(x, y, z);

        TileFarmland tileFarmland = (TileFarmland) world.getTileEntity(x, y - 1, z);

        if (tileCrop.getCrop() == null) {
            super.onEntityCollidedWithBlock(world, x, y, z, p_149670_5_);
            return;
        }

        //作物の処理を呼ぶ
        tileCrop.getCrop().onEntityCollidedWithCrop(tileCrop, tileFarmland, p_149670_5_);

    }

    @Override
    public boolean isBurning(IBlockAccess world, int x, int y, int z) {

        TileEntityCrop tileCrop = (TileEntityCrop) world.getTileEntity(x, y, z);

        TileFarmland tileFarmland = (TileFarmland) world.getTileEntity(x, y - 1, z);

        if (tileCrop.getCrop() == null) return super.isBurning(world, x, y, z);

        //作物の処理を呼ぶ
        return tileCrop.getCrop().isBurning(tileCrop, tileFarmland);
    }

    @Override
    public float getEnchantPowerBonus(World world, int x, int y, int z) {
        TileEntityCrop tileCrop = (TileEntityCrop) world.getTileEntity(x, y, z);

        TileFarmland tileFarmland = (TileFarmland) world.getTileEntity(x, y - 1, z);

        if (tileCrop.getCrop() == null) return super.getEnchantPowerBonus(world, x, y, z);

        //作物の処理を呼ぶ
        return tileCrop.getCrop().getEnchantPowerBonus(tileCrop, tileFarmland);
    }

    //ドロップ
    @Override
    public int quantityDropped(Random p_149745_1_) {
        return 0;
    }

    //農地かどうか
    @Override
    public void onNeighborBlockChange(World world, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_) {
        super.onNeighborBlockChange(world, p_149695_2_, p_149695_3_, p_149695_4_, p_149695_5_);
        this.checkAndDropBlock(world, p_149695_2_, p_149695_3_, p_149695_4_);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    @Override
    public void updateTick(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_) {
        this.checkAndDropBlock(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_);
    }

    /**
     * checks if the block can stay, if not drop as item
     */
    protected void checkAndDropBlock(World p_149855_1_, int p_149855_2_, int p_149855_3_, int p_149855_4_) {
        if (!this.canBlockStay(p_149855_1_, p_149855_2_, p_149855_3_, p_149855_4_)) {
            this.dropBlockAsItem(p_149855_1_, p_149855_2_, p_149855_3_, p_149855_4_, p_149855_1_.getBlockMetadata(p_149855_2_, p_149855_3_, p_149855_4_), 0);
            p_149855_1_.setBlockToAir(p_149855_2_, p_149855_3_, p_149855_4_);
        }
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {

        if (!(world.getTileEntity(x, y - 1, z) instanceof TileFarmland)) return false;

        TileFarmland tileFarmland = (TileFarmland) world.getTileEntity(x, y - 1, z);
        TileEntityCrop tileCrop = (TileEntityCrop) world.getTileEntity(x, y, z);

        ICrop crop = tileCrop.getCrop();
        Block farmland = world.getBlock(x, y - 1, z);

        if (crop == null) return false;

        String name = SSCrops.farmlandRegistry.getFarmlandName(farmland);

        if (!crop.canBlockStay(name, tileFarmland)) return false;

        return true;

    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityCrop();
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
        return null;
    }

    @Override
    public void setBlockBoundsForItemRender() {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0f, 1.0F);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {

        try {

            TileEntityCrop tileCrop = (TileEntityCrop) world.getTileEntity(x, y, z);

            if (tileCrop == null) this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0f, 1.0F);

            ICrop crop = tileCrop.getCrop();

            if (crop == null) this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0f, 1.0F);
            if (crop.getRenderType() == null) this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0f, 1.0F);

            if (crop.getRenderType().equals(CropRendererType.Normal)) {
                float f = 0.5F;
                this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
            } else {
                float f = 0.2F;
                this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 3.0F, 0.5F + f);
            }

        } catch (NullPointerException e) {

        }

    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getRenderType() {

        //return 0;//1;

        return SextiarySector.proxy.cropType;

    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {

        TileEntityCrop tCrop = (TileEntityCrop) world.getTileEntity(x, y, z);

        if (tCrop == null) return this.getIcon(side, world.getBlockMetadata(x, y, z));

        ICrop crop = tCrop.getCrop();

        if (crop == null) return this.getIcon(side, world.getBlockMetadata(x, y, z));

        return crop.getCropIcon(tCrop);

    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
        return Blocks.dirt.getIcon(p_149691_1_, p_149691_2_);
    }

}
