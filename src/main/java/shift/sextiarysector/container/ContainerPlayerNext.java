package shift.sextiarysector.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import shift.sextiarysector.api.IEquipment;
import shift.sextiarysector.player.EntityPlayerManager;
import shift.sextiarysector.player.EquipmentType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class ContainerPlayerNext  extends Container {

	public InventoryPlayerNext equipment;
	private EntityPlayer player;

	public ContainerPlayerNext(InventoryPlayer par1InventoryPlayer,EntityPlayer p_i1819_3_)
    {
        //this.equipment = par2TileEntityFurnace;
		this.player = p_i1819_3_;
		this.equipment = EntityPlayerManager.getEquipmentStats(p_i1819_3_).inventory;

		/*
		int c = 0 ;
		for(int i = 0;i<5;i++){

			for(int j =0;j<4;j++){
				this.addSlotToContainer(new Slot(this.equipment,c, 80 + i * 18, 8 + j * 18));
				c++;
			}

		}*/


		//バニラ

        int i;

        //0
		for (i = 0; i < 4; ++i)
        {
            final int k = i;
            this.addSlotToContainer(new Slot(par1InventoryPlayer, par1InventoryPlayer.getSizeInventory() - 1 - i, 8, 8 + i * 18)
            {
                private static final String __OBFID = "CL_00001755";
                /**
                 * Returns the maximum stack size for a given slot (usually the same as getInventoryStackLimit(), but 1
                 * in the case of armor slots)
                 */
                public int getSlotStackLimit()
                {
                    return 1;
                }
                /**
                 * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
                 */
                public boolean isItemValid(ItemStack p_75214_1_)
                {
                    if (p_75214_1_ == null) return false;
                    return p_75214_1_.getItem().isValidArmor(p_75214_1_, k, player);
                }
                /**
                 * Returns the icon index on items.png that is used as background image of the slot.
                 */
                @SideOnly(Side.CLIENT)
                public IIcon getBackgroundIconIndex()
                {
                    return ItemArmor.func_94602_b(k);
                }
            });
        }

		//4
		for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, i, 8 + i * 18, 142));
        }

		//13
        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }


        //40

        this.addSlotToContainer(new SlotEquipment(EquipmentType.Necklace, this.equipment, 0, 80 + 0 * 18, 8 + 0 * 18));

		this.addSlotToContainer(new SlotEquipment(EquipmentType.Ring, this.equipment, 1, 80 + 0 * 18, 8 + 1 * 18));
		this.addSlotToContainer(new SlotEquipment(EquipmentType.Ring, this.equipment, 2, 80 + 0 * 18, 8 + 2 * 18));
		this.addSlotToContainer(new SlotEquipment(EquipmentType.Ring, this.equipment, 3, 80 + 0 * 18, 8 + 3 * 18));

		this.addSlotToContainer(new SlotEquipment(EquipmentType.Face, this.equipment, 4, 80 + 1 * 18, 8 + 0 * 18));
		this.addSlotToContainer(new SlotEquipment(EquipmentType.Bag, this.equipment, 5, 80 + 1 * 18, 8 + 1 * 18));
		this.addSlotToContainer(new SlotEquipment(EquipmentType.Hand, this.equipment, 6, 80 + 1 * 18, 8 + 2 * 18));
		this.addSlotToContainer(new SlotEquipment(EquipmentType.Belt, this.equipment, 7, 80 + 1 * 18, 8 + 3 * 18));

		this.addSlotToContainer(new SlotEquipment(EquipmentType.Unit, this.equipment, 8, 80 + 2 * 18, 8 + 0 * 18));
		this.addSlotToContainer(new SlotEquipment(EquipmentType.Unit, this.equipment, 9, 80 + 2 * 18, 8 + 1 * 18));
		this.addSlotToContainer(new SlotEquipment(EquipmentType.Unit, this.equipment, 10, 80 + 2 * 18, 8 + 2 * 18));
		this.addSlotToContainer(new SlotEquipment(EquipmentType.Unit, this.equipment, 11, 80 + 2 * 18, 8 + 3 * 18));

		this.addSlotToContainer(new SlotEquipment(EquipmentType.Unit, this.equipment, 12, 80 + 3 * 18, 8 + 0 * 18));
		this.addSlotToContainer(new SlotEquipment(EquipmentType.Unit, this.equipment, 13, 80 + 3 * 18, 8 + 1 * 18));
		this.addSlotToContainer(new SlotEquipment(EquipmentType.Unit, this.equipment, 14, 80 + 3 * 18, 8 + 2 * 18));
		this.addSlotToContainer(new SlotEquipment(EquipmentType.Unit, this.equipment, 15, 80 + 3 * 18, 8 + 3 * 18));

		this.addSlotToContainer(new SlotEquipment(EquipmentType.Other, this.equipment, 16, 80 + 4 * 18, 8 + 0 * 18));
		this.addSlotToContainer(new SlotEquipment(EquipmentType.Other, this.equipment, 17, 80 + 4 * 18, 8 + 1 * 18));
		this.addSlotToContainer(new SlotEquipment(EquipmentType.Other, this.equipment, 18, 80 + 4 * 18, 8 + 2 * 18));
		this.addSlotToContainer(new SlotEquipment(EquipmentType.Other, this.equipment, 19, 80 + 4 * 18, 8 + 3 * 18));


		System.out.println("AAAA : "+this.inventorySlots.size());
    }

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return true;
	}

	/*
	public ItemStack slotClick(int p_75144_1_, int p_75144_2_, int p_75144_3_, EntityPlayer p_75144_4_)
    {
		return super.slotClick(p_75144_1_, p_75144_2_, p_75144_3_, p_75144_4_);
    }*/

	public ItemStack transferStackInSlot(EntityPlayer p_82846_1_, int p_82846_2_)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(p_82846_2_);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (itemstack.getItem() instanceof ItemArmor && !((Slot)this.inventorySlots.get(5 + ((ItemArmor)itemstack.getItem()).armorType)).getHasStack())
            {
                int j = 0 + ((ItemArmor)itemstack.getItem()).armorType;

                if (!this.mergeItemStack(itemstack1, j, j + 1, false))
                {
                    return null;
                }
            }
            else if (itemstack.getItem() instanceof IEquipment && ((IEquipment)itemstack.getItem()).isItemValid(EquipmentType.Necklace, itemstack))
            {

                if (!this.mergeItemStack(itemstack1,40,41, false))
                {
                    return null;
                }

            }
            else if (itemstack.getItem() instanceof IEquipment && ((IEquipment)itemstack.getItem()).isItemValid(EquipmentType.Ring, itemstack))
            {

                if (!this.mergeItemStack(itemstack1,41,44, false))
                {
                    return null;
                }

            }
            else if (itemstack.getItem() instanceof IEquipment && ((IEquipment)itemstack.getItem()).isItemValid(EquipmentType.Face, itemstack))
            {

                if (!this.mergeItemStack(itemstack1,44,45, false))
                {
                    return null;
                }

            }
            else if (itemstack.getItem() instanceof IEquipment && ((IEquipment)itemstack.getItem()).isItemValid(EquipmentType.Bag, itemstack))
            {

                if (!this.mergeItemStack(itemstack1,45,46, false))
                {
                    return null;
                }

            }
            else if (itemstack.getItem() instanceof IEquipment && ((IEquipment)itemstack.getItem()).isItemValid(EquipmentType.Hand, itemstack))
            {

                if (!this.mergeItemStack(itemstack1,46,47, false))
                {
                    return null;
                }

            }
            else if (itemstack.getItem() instanceof IEquipment && ((IEquipment)itemstack.getItem()).isItemValid(EquipmentType.Belt, itemstack))
            {

                if (!this.mergeItemStack(itemstack1,47,48, false))
                {
                    return null;
                }

            }
            else if (itemstack.getItem() instanceof IEquipment && ((IEquipment)itemstack.getItem()).isItemValid(EquipmentType.Unit, itemstack))
            {

                if (!this.mergeItemStack(itemstack1,48,56, false))
                {
                    return null;
                }

            }
            else if (itemstack.getItem() instanceof IEquipment && ((IEquipment)itemstack.getItem()).isItemValid(EquipmentType.Other, itemstack))
            {

                if (!this.mergeItemStack(itemstack1,56,60, false))
                {
                    return null;
                }

            }
            else if (p_82846_2_ >= 4 && p_82846_2_ < 13)
            {
                if (!this.mergeItemStack(itemstack1, 13, 40, false))
                {
                    return null;
                }
            }
            else if (p_82846_2_ >= 13 && p_82846_2_ < 40)
            {
                if (!this.mergeItemStack(itemstack1, 4, 13, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 13, 40, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(p_82846_1_, itemstack1);
        }

        return itemstack;
    }

}
