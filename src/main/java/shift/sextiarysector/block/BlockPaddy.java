/*
* 作成者: Shift02
* 作成日: 2016/02/25 - 15:52:34
*/
package shift.sextiarysector.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.SSCrops;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.api.agriculture.AgricultureAPI;
import shift.sextiarysector.api.agriculture.ICrop;
import shift.sextiarysector.api.agriculture.IFertilizer;
import shift.sextiarysector.api.agriculture.TileFarmland;
import shift.sextiarysector.tileentity.TileEntityPaddy;

public class BlockPaddy extends BlockAbstractFarmland {

    public BlockPaddy() {
        super();
        this.setHardness(0.4f);
        this.setLightOpacity(255);
        this.setStepSound(soundTypeGrass);
        this.useNeighborBrightness = true;
    }

    @Override
    public String getName() {
        return AgricultureAPI.PADDY;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {

        ItemStack item = player.getCurrentEquippedItem();

        TileFarmland farmland = (TileFarmland) world.getTileEntity(x, y, z);

        if (item == null) return false;

        if (!world.isAirBlock(x, y + 1, z)) return false;

        //種
        ICrop crop = SSCrops.cropManager.getCrop(item, player);

        if (crop != null) return this.setCrop(world, x, y, z, player, item, farmland, crop);

        //肥料
        IFertilizer fertilizer = SSCrops.fertilizerManager.getFertilizer(item);

        if (fertilizer != null) return this.setFertilizer(world, x, y, z, player, item, fertilizer);

        return false;

    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
        return Blocks.dirt.getIcon(p_149691_1_, p_149691_2_);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass() {
        return 1;
    }

    //当たり判定
    @Override
    public void addCollisionBoxesToList(World p_149743_1_, int p_149743_2_, int p_149743_3_, int p_149743_4_, AxisAlignedBB p_149743_5_, List p_149743_6_, Entity p_149743_7_) {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.625f, 1.0F);
        super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
        float f = 0.125F;

        if (!this.isSame(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, ForgeDirection.getOrientation(4))) {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
            super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
        }

        if (!this.isSame(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, ForgeDirection.getOrientation(2))) {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
            super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
        }

        if (!this.isSame(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, ForgeDirection.getOrientation(5))) {
            this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
        }

        if (!this.isSame(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, ForgeDirection.getOrientation(3))) {
            this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
            super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
        }

        this.setBlockBoundsForItemRender();

    }

    private boolean isSame(World p_149743_1_, int p_149743_2_, int p_149743_3_, int p_149743_4_, ForgeDirection d) {

        if (p_149743_1_.getBlock(p_149743_2_ + d.offsetX, p_149743_3_ + d.offsetY, p_149743_4_ + d.offsetZ) == this) return true;

        return false;
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {

        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

        ret.add(new ItemStack(Blocks.dirt, 1));

        return ret;
    }

    //ブロックの線
    @Override
    public void setBlockBoundsForItemRender() {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public int getRenderType() {
        return SextiarySector.proxy.paddyType;
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
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return Blocks.dirt.getItemDropped(0, p_149650_2_, p_149650_3_);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityPaddy();
    }
}
