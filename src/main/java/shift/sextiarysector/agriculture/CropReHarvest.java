package shift.sextiarysector.agriculture;

import java.util.ArrayList;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.api.agriculture.AgricultureAPI;
import shift.sextiarysector.api.agriculture.CropRendererType;
import shift.sextiarysector.api.agriculture.IFertilizer;
import shift.sextiarysector.api.agriculture.TileCrop;
import shift.sextiarysector.api.agriculture.TileFarmland;
import shift.sextiarysector.api.season.Season;

/**
 * 再収穫できる作物
 * @author Shift02
 *
 */
public class CropReHarvest extends CropBase {

    public CropReHarvest(String name, ItemStack crop, Season[] season, int[] day) {
        super(name, crop, season, day);
    }

    @Override
    public ArrayList<ItemStack> harvest(TileCrop crop, TileFarmland farmland) {

        ArrayList<ItemStack> list = new ArrayList<ItemStack>();

        if (!this.canHarvest(crop, farmland)) return list;

        World w = crop.getWorld();
        int x = crop.getX();
        int y = crop.getY();
        int z = crop.getZ();

        ItemStack cropItem = this.crop.copy();

        //突然変異の処理
        if (farmland.getFertilizer() != null) {

            IFertilizer f = farmland.getFertilizer();

            ItemStack after = AgricultureAPI.getMutationItem(f, cropItem);

            if (after != null) cropItem = after;

        }

        list.add(cropItem);

        if (w.isRemote) return list;

        //再収穫用のフラグ
        NBTTagCompound nbt = crop.getExtendedCropProperties();
        nbt.setBoolean("reharvest", true);

        crop.doDecline(this.day[this.day.length - 1]);

        //サーバー側の処理
        w.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, SSBlocks.crop.stepSound.func_150496_b(), (SSBlocks.crop.stepSound.getVolume() + 1.0F) / 2.0F, SSBlocks.crop.stepSound.getPitch() * 0.8F);

        farmland.setFertilizer(null);
        w.markBlockForUpdate(farmland.getX(), farmland.getY(), farmland.getZ());

        return list;

    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getCropIcon(TileCrop crop) {

        NBTTagCompound nbt = crop.getExtendedCropProperties();

        if (crop.getDay() < this.day[this.day.length - 2] && nbt.getBoolean("reharvest")) {
            return this.icons[this.icons.length - 1];
        }

        for (int i = this.day.length - 2; i >= 0; i--) {

            int d = this.day[i];
            if (crop.getDay() > d) return this.icons[i + 1];

        }

        return this.icons[0];

    }

    @Override
    public CropRendererType getRenderType() {
        return CropRendererType.Cross;
    }

    @Override
    public int getGrowingPeriod() {
        return day[day.length - 2];
    }

}
