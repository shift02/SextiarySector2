package shift.sextiarysector.api.agriculture;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import shift.sextiarysector.api.season.Season;
import shift.sextiarysector.api.season.SeasonAPI;

/**
 * バニラ(小麦)と同じシステムの作物をSS2システムに変換するクラス
 * @author Shift02
 *
 */
public class CropVanilla implements ICrop {

    public String name;
    public Item seed;
    public Block crop;
    public Season[] season;
    public int[] day;

    public CropVanilla(String name, Item seed, Block crop, Season[] season, int[] day) {
        this.name = name;
        this.seed = seed;
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
        return this.seed == seed.getItem();
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

        w.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, this.crop.stepSound.func_150496_b(), (this.crop.stepSound.getVolume() + 1.0F) / 2.0F, this.crop.stepSound.getPitch() * 0.8F);

        ItemStack cropItem = new ItemStack(this.crop.getItemDropped(7, w.rand, 0));

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
    public void registerCropIcons(IIconRegister register) {

    }

    @Override
    public IIcon getCropIcon(TileCrop crop) {

        for (int i = this.day.length - 1; i >= 0; i--) {

            int d = this.day[i];
            if (crop.getDay() > d) return this.crop.getIcon(0, i + 1);

        }

        return this.crop.getIcon(0, 0);

    }

    @Override
    public CropRendererType getRenderType() {
        return CropRendererType.Normal;
    }

}
