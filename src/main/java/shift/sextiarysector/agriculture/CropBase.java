package shift.sextiarysector.agriculture;

import java.util.ArrayList;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import shift.sextiarysector.SSItems;
import shift.sextiarysector.api.agriculture.AgricultureAPI;
import shift.sextiarysector.api.agriculture.CropAbstract;
import shift.sextiarysector.api.agriculture.CropRendererType;
import shift.sextiarysector.api.agriculture.IFertilizer;
import shift.sextiarysector.api.agriculture.TileCrop;
import shift.sextiarysector.api.agriculture.TileFarmland;
import shift.sextiarysector.api.season.Season;
import shift.sextiarysector.api.season.SeasonAPI;

/**
 * 作物クラス
 * @author Shift02
 */
public class CropBase extends CropAbstract {

    public String name;
    public ItemStack crop;
    protected Season[] season;
    protected int[] day;

    /** 実績用の作物リスト */
    public static ArrayList<CropBase> crops = new ArrayList<CropBase>();

    @SideOnly(Side.CLIENT)
    public IIcon[] icons;

    public CropBase(String name, ItemStack crop, Season[] season, int[] day) {
        this.name = name;
        this.crop = crop;
        this.season = season;
        this.day = day;

        crops.add(this);

    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getGrowingPeriod() {
        return day[day.length - 1];
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
    public boolean click(TileCrop crop, TileFarmland farmland, EntityPlayer player) {

        if (!this.canHarvest(crop, farmland)) return false;

        World w = crop.getWorld();
        int x = crop.getX();
        int y = crop.getY();
        int z = crop.getZ();

        if (w.isRemote) return true;

        //w.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, SSBlocks.crop.stepSound.func_150496_b(), (SSBlocks.crop.stepSound.getVolume() + 1.0F) / 2.0F, SSBlocks.crop.stepSound.getPitch() * 0.8F);

        //収穫処理
        ArrayList<ItemStack> list = this.harvest(crop, farmland);

        //ドロップ処理
        for (ItemStack cropItem : list) {

            float var10 = w.rand.nextFloat() * 0.8F + 0.1F;
            float var11 = w.rand.nextFloat() * 0.8F + 0.1F;
            float var12 = w.rand.nextFloat() * 0.8F + 0.1F;

            EntityItem var14 = new EntityItem(w, (x + var10), (y + var11), (z + var12), cropItem);
            w.spawnEntityInWorld(var14);
        }

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
    public boolean canHarvest(TileCrop crop, TileFarmland farmland) {
        return this.getGrowingPeriod() < crop.getDay();
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

        //サーバー側の処理
        w.func_147480_a(x, y, z, false);
        w.setBlockToAir(x, y, z);

        farmland.setFertilizer(null);
        w.markBlockForUpdate(farmland.getX(), farmland.getY(), farmland.getZ());

        return list;

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

    public Season[] getSeason() {
        return season;
    }

    public void setSeason(Season[] season) {
        this.season = season;
    }

    public int[] getDays() {
        return day;
    }

    public void setDay(int[] day) {
        this.day = day;
    }

}
