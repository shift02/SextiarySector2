package shift.sextiarysector.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import shift.sextiarysector.block.BlockSSFluid;

public class ItemBlockSSFluid extends ItemBlockMeta {

    public ItemBlockSSFluid(Block p_i45328_1_) {
        super(p_i45328_1_);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack itemstack, int p_82790_2_) {

        return ((BlockSSFluid) this.field_150939_a).getFluid().getColor();

    }

}
