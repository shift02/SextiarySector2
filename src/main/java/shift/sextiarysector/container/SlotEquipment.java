package shift.sextiarysector.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import shift.sextiarysector.api.equipment.EquipmentType;
import shift.sextiarysector.api.equipment.IEquipment;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SlotEquipment extends Slot {

    private EquipmentType equipment;

    public SlotEquipment(EquipmentType type, IInventory p_i1824_1_, int p_i1824_2_, int x, int y) {
        super(p_i1824_1_, p_i1824_2_, x, y);
        equipment = type;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {

        return stack.getItem() instanceof IEquipment && ((IEquipment) stack.getItem()).isItemValid(this.equipment, stack);

    }

    @Override
    public boolean canTakeStack(EntityPlayer player) {

        return this.getStack() != null && ((IEquipment) this.getStack().getItem()).canTakeStack(equipment, this.getStack(), player);
    }

    @Override
    public int getSlotStackLimit() {
        return 1;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getBackgroundIconIndex() {
        return equipment.getIcon();
    }
}
