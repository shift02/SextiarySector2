package shift.sextiarysector.agriculture;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.SSItems;
import shift.sextiarysector.api.agriculture.AgricultureAPI;
import shift.sextiarysector.api.agriculture.CropRendererType;
import shift.sextiarysector.api.agriculture.ICrop;
import shift.sextiarysector.api.agriculture.TileCrop;
import shift.sextiarysector.api.agriculture.TileFarmland;
import shift.sextiarysector.api.season.Season;
import shift.sextiarysector.api.season.SeasonAPI;

public class CropBase implements ICrop {

    public String name;
    public Item crop;
    public Season[] season;
    public int[] day;

    @SideOnly(Side.CLIENT)
    public IIcon[] icons;

    public CropBase(String name, Item crop, Season[] season, int[] day) {
        this.name = name;
        this.crop = crop;
        this.season = season;
        this.day = day;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean isSeed(ItemStack seed, EntityPlayer player) {

        if (seed.getItem() != SSItems.seeds) return false;

        return this.name.equals(SSItems.seeds.getSeedName(seed));

    }

    @Override
    public void update(TileCrop crop, TileFarmland farmland) {

    }

    @Override
    public boolean click(TileCrop crop, EntityPlayer player) {

        if (this.day[this.day.length - 1] >= crop.getDay()) return false;

        World w = crop.getWorld();
        int x = crop.getX();
        int y = crop.getY();
        int z = crop.getZ();

        if (w.isRemote) return true;

        w.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, SSBlocks.crop.stepSound.func_150496_b(), (SSBlocks.crop.stepSound.getVolume() + 1.0F) / 2.0F, SSBlocks.crop.stepSound.getPitch() * 0.8F);

        ItemStack cropItem = new ItemStack(this.crop);

        float var10 = w.rand.nextFloat() * 0.8F + 0.1F;
        float var11 = w.rand.nextFloat() * 0.8F + 0.1F;
        float var12 = w.rand.nextFloat() * 0.8F + 0.1F;

        EntityItem var14 = new EntityItem(w, (x + var10), (y + var11), (z + var12), cropItem);
        w.spawnEntityInWorld(var14);

        w.func_147480_a(x, y, z, false);
        w.setBlockToAir(x, y, z);

        return true;

    }

    @Override
    public boolean canBlockStay(String name, TileFarmland farmland) {
        return name.equals(AgricultureAPI.FARMLAND);
    }

    @Override
    public boolean canWither(TileCrop crop, TileFarmland farmland) {

        return this.canGrowSeason(crop, farmland);

    }

    public boolean canGrowSeason(TileCrop crop, TileFarmland farmland) {

        World w = crop.getWorld();
        Season s = SeasonAPI.getSeason(w);

        for (Season ss : this.season) {
            if (s == ss) return false;
        }

        return true;

    }

    @Override
    public boolean canGrow(TileCrop crop, TileFarmland farmland) {
        return true;
    }

    @Override
    public int getConsumptionMoisture(TileCrop crop, TileFarmland farmland) {
        return 5;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerCropIcons(IIconRegister register) {

        this.icons = new IIcon[day.length + 1];

        for (int i = 0; i < this.icons.length; i++) {
            this.icons[i] = register.registerIcon("sextiarysector:crop/" + this.name + "_stage_" + i);
        }

    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getCropIcon(TileCrop crop) {

        for (int i = this.day.length - 1; i >= 0; i--) {

            int d = this.day[i];
            if (crop.getDay() > d) return this.icons[i + 1];

        }

        return this.icons[0];

    }

    @Override
    public CropRendererType getRenderType() {
        return CropRendererType.Normal;
    }

}
