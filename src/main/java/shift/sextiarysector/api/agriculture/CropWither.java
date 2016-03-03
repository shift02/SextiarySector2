package shift.sextiarysector.api.agriculture;

import java.util.ArrayList;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

/**
 * 枯れた作物
 * @author Shift02
 *
 */
public class CropWither extends CropAbstract {

    @Override
    public String getName() {
        return "wither";
    }

    @Override
    public int getGrowingPeriod() {
        return -1;
    }

    @Override
    public boolean isSeed(ItemStack seed, EntityPlayer player) {
        return false;
    }

    @Override
    public boolean canBlockStay(String name, TileFarmland farmland) {
        return true;
    }

    @Override
    public boolean canWither(TileCrop crop, TileFarmland farmland) {
        return false;
    }

    @Override
    public boolean canGrow(TileCrop crop, TileFarmland farmland) {
        return false;
    }

    @Override
    public int getConsumptionMoisture(TileCrop crop, TileFarmland farmland) {
        return 0;
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
    public void registerCropIcons(IIconRegister register) {

    }

    @Override
    public IIcon getCropIcon(TileCrop crop) {
        return Blocks.deadbush.getIcon(0, 0);
    }

    @Override
    public CropRendererType getRenderType() {
        return CropRendererType.Cross;
    }

}
