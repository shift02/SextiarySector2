package shift.sextiarysector.container;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import shift.sextiarysector.api.machine.item.IGearForceItem;
import shift.sextiarysector.event.ClientEventHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SlotGF extends Slot{

	private int power;

	public SlotGF(IInventory p_i1824_1_, int p_i1824_2_, int x,int y, int p) {
		super(p_i1824_1_, p_i1824_2_, x, y);
		this.power = p;
	}

	@Override
	public boolean isItemValid(ItemStack stack) {

		return stack.getItem() instanceof IGearForceItem &&((IGearForceItem)stack.getItem()).canSetSlot(power);

	}

	@SideOnly(Side.CLIENT)
    public IIcon getBackgroundIconIndex()
    {
        return ClientEventHandler.slotGF;
    }

}
