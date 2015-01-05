package shift.sextiarysector.container;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.FluidContainerRegistry;
import shift.sextiarysector.event.ClientEventHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SlotFluid extends Slot{

	public SlotFluid(IInventory p_i1824_1_, int p_i1824_2_, int p_i1824_3_,int p_i1824_4_) {
		super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
	}

	@Override
	public boolean isItemValid(ItemStack stack) {

		return FluidContainerRegistry.isContainer(stack)  ;

	}

	@SideOnly(Side.CLIENT)
    public IIcon getBackgroundIconIndex()
    {
        return ClientEventHandler.slotFluid;
    }

}
