package shift.sextiarysector.api.agriculture;

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
public class CropWither implements ICrop {

    @Override
    public String getName() {
        return "wither";
    }

    @Override
    public boolean isSeed(ItemStack seed, EntityPlayer player) {
        return false;
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
