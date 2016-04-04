/*
* 作成者: Shift02
* 作成日: 2016/03/03 - 12:10:50
*/
package shift.sextiarysector.api.agriculture;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

/**
 * 観賞用の花
 * @author Shift02
 *
 */
public class CropFlower extends CropAbstract {

    public String name;
    public ItemStack flower;

    public CropFlower(String name, ItemStack flower) {
        this.name = name;
        this.flower = flower;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getGrowingPeriod() {
        return -1;
    }

    @Override
    public boolean isSeed(ItemStack seed, EntityPlayer player) {
        return seed.isItemEqual(flower);
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
        return AgricultureAPI.FARMLAND.equals(name);
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
    public int getConsumptionMoisture(TileCrop crop, TileFarmland farmland) {
        return 5;
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
        return Block.getBlockFromItem(this.flower.getItem()).getIcon(0, this.flower.getItemDamage());
    }

    @Override
    public CropRendererType getRenderType() {
        return CropRendererType.Cross;
    }

}
