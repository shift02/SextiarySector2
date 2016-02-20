package shift.sextiarysector.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;

public class SlotContainer extends Slot {

    private Item item;

    public SlotContainer(IInventory p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_, Item item) {
        super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
        this.item = item;
    }

    /*
        このアイテムは動かせない、つかめないようにする。
     */
    @Override
    public boolean canTakeStack(EntityPlayer p_82869_1_) {
        return !(getHasStack() && getStack().getItem() == this.item);
    }

}
