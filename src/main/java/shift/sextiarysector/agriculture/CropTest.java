package shift.sextiarysector.agriculture;

import java.util.ArrayList;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import shift.sextiarysector.api.agriculture.AgricultureAPI;
import shift.sextiarysector.api.agriculture.CropAbstract;
import shift.sextiarysector.api.agriculture.CropRendererType;
import shift.sextiarysector.api.agriculture.TileCrop;
import shift.sextiarysector.api.agriculture.TileFarmland;

public class CropTest extends CropAbstract {

    @SideOnly(Side.CLIENT)
    public IIcon testIcon;

    @Override
    public String getName() {
        return "hoge";
    }

    @Override
    public boolean isSeed(ItemStack seed, EntityPlayer player) {
        return seed.getItem() == Items.apple;
    }

    @Override
    public void update(TileCrop crop, TileFarmland farmland) {

    }

    @Override
    public boolean click(TileCrop crop, TileFarmland farmland, EntityPlayer player) {
        return false;
    }

    @Override
    public boolean canBlockStay(String name, TileFarmland farmland) {
        return name.equals(AgricultureAPI.FARMLAND);
    }

    @Override
    public boolean canWither(TileCrop crop, TileFarmland farmland) {
        return false;
    }

    @Override
    public boolean canGrow(TileCrop crop, TileFarmland farmland) {
        return true;
    }

    @Override
    public boolean canHarvest(TileCrop crop, TileFarmland farmland) {
        return false;
    }

    @Override
    public ArrayList<ItemStack> harvest(TileCrop crop, TileFarmland farmland) {
        return new ArrayList<ItemStack>();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerCropIcons(IIconRegister register) {

        this.testIcon = register.registerIcon("stone");

    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getCropIcon(TileCrop crop) {

        if (crop.getDay() > 2) return Blocks.clay.getIcon(0, 0);

        return this.testIcon;
    }

    @Override
    public int getConsumptionMoisture(TileCrop crop, TileFarmland farmland) {
        return 5;
    }

    @Override
    public CropRendererType getRenderType() {
        return CropRendererType.Normal;
    }

    @Override
    public int getGrowingPeriod() {
        return -1;
    }

}
