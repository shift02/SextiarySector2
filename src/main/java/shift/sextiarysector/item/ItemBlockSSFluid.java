package shift.sextiarysector.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import shift.sextiarysector.block.BlockSSFluid;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockSSFluid extends ItemBlock {

    public ItemBlockSSFluid(Block p_i45328_1_) {
        super(p_i45328_1_);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack itemstack, int p_82790_2_) {

        return ((BlockSSFluid) this.field_150939_a).getFluid().getColor();

    }

}
