package shift.sextiarysector.agriculture;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
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

    public CropReHarvest(String name, Item crop, Season[] season, int[] day) {
        super(name, crop, season, day);
    }

    @Override
    public boolean click(TileCrop crop, TileFarmland farmland, EntityPlayer player) {

        if (this.day[this.day.length - 2] >= crop.getDay()) return false;

        World w = crop.getWorld();
        int x = crop.getX();
        int y = crop.getY();
        int z = crop.getZ();

        if (w.isRemote) return true;

        w.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, SSBlocks.crop.stepSound.func_150496_b(), (SSBlocks.crop.stepSound.getVolume() + 1.0F) / 2.0F, SSBlocks.crop.stepSound.getPitch() * 0.8F);

        ItemStack cropItem = new ItemStack(this.crop);

        //突然変異の処理
        if (farmland.getFertilizer() != null) {

            IFertilizer f = farmland.getFertilizer();

            ItemStack after = AgricultureAPI.getMutationItem(f, cropItem);

            if (after != null) cropItem = after;

        }

        float var10 = w.rand.nextFloat() * 0.8F + 0.1F;
        float var11 = w.rand.nextFloat() * 0.8F + 0.1F;
        float var12 = w.rand.nextFloat() * 0.8F + 0.1F;

        EntityItem var14 = new EntityItem(w, (x + var10), (y + var11), (z + var12), cropItem);
        w.spawnEntityInWorld(var14);

        NBTTagCompound nbt = crop.getExtendedCropProperties();
        nbt.setBoolean("reharvest", true);

        crop.doDecline(this.day[this.day.length - 1]);

        //w.func_147480_a(x, y, z, false);
        //w.setBlockToAir(x, y, z);

        return true;

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

}
