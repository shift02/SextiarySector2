package shift.sextiarysector.container;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import shift.sextiarysector.SSItems;
import shift.sextiarysector.event.ClientEventHandler;

public class SlotQuiver extends Slot {
    public SlotQuiver(IInventory p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_) {
        super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
    }

    /*
        このアイテムは動かせない、つかめないようにする。
     */
    @Override
    public boolean canTakeStack(EntityPlayer p_82869_1_) {
        return !(getHasStack() && getStack().getItem() == SSItems.quiver);
    }

    //矢以外無理
    @Override
    public boolean isItemValid(ItemStack stack) {

        return stack.getItem() == Items.arrow;

    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getBackgroundIconIndex() {
        return ClientEventHandler.slotArrow;
    }

}
