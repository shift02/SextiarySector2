package shift.sextiarysector.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.api.agriculture.AgricultureAPI;

public class BlockFarmland extends BlockAbstractFarmland {

    public BlockFarmland() {
        super();
        this.setHardness(1.0F);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, (1.0f / 16.0f) * 14, 1.0F);
        this.setStepSound(soundTypeGravel);
        this.setLightOpacity(255);
        this.useNeighborBrightness = true;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {

        boolean f = super.onBlockActivated(world, x, y, z, player, par6, par7, par8, par9);

        if (f) return true;

        if (!world.isAirBlock(x, y + 1, z)) return false;

        ItemStack item = player.getCurrentEquippedItem();

        if (item == null) return false;

        if (par6 != 1) return false;

        world.setBlock(x, y, z, Blocks.farmland);

        boolean s = item.getItem().onItemUse(item, player, world, x, y, z, par6, par7, par8, par9);

        if (s) return true;

        world.setBlock(x, y, z, this);

        return false;

    }

    @Override
    public void onBlockClicked(World p_149699_1_, int p_149699_2_, int p_149699_3_, int p_149699_4_, EntityPlayer p_149699_5_) {

    }

    @Override
    public String getName() {
        return AgricultureAPI.FARMLAND;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
        return Blocks.farmland.getIcon(1, p_149691_2_);
    }

    @Override
    public int getRenderType() {
        return SextiarySector.proxy.farmlandType;
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
    public void onFallenUpon(World p_149746_1_, int p_149746_2_, int p_149746_3_, int p_149746_4_, Entity p_149746_5_, float p_149746_6_) {

        if (!p_149746_1_.isRemote && p_149746_1_.rand.nextFloat() < p_149746_6_ - 0.5F) {
            if (!(p_149746_5_ instanceof EntityPlayer) && !p_149746_1_.getGameRules().getGameRuleBooleanValue("mobGriefing")) {
                return;
            }

            p_149746_1_.setBlock(p_149746_2_, p_149746_3_, p_149746_4_, Blocks.dirt);
        }
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return Blocks.dirt.getItemDropped(0, p_149650_2_, p_149650_3_);
    }

}
