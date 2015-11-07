package shift.sextiarysector.agriculture;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import shift.sextiarysector.api.agriculture.ICrop;
import shift.sextiarysector.api.agriculture.TileCrop;
import shift.sextiarysector.api.agriculture.TileFarmland;

public class CropTest implements ICrop {

    @Override
    public String getName() {
        return "hoge";
    }

    @Override
    public boolean isSeed(ItemStack seed) {
        return seed.getItem() == Items.wheat_seeds;
    }

    @Override
    public void update(TileCrop crop) {
        // TODO 自動生成されたメソッド・スタブ

    }

    @Override
    public void click(TileCrop crop, EntityPlayer player) {
        // TODO 自動生成されたメソッド・スタブ

    }

    @Override
    public boolean canBlockStay(TileFarmland farmland) {
        return true;
    }

}
