package shift.sextiarysector.player;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import shift.sextiarysector.api.equipment.EquipmentType;
import shift.sextiarysector.api.equipment.IEquipment;
import shift.sextiarysector.container.InventoryPlayerNext;

public class EquipmentStats {

	public final String NBT_ID = "ssequipment";

	public InventoryPlayerNext inventory;

	public EquipmentStats() {

		inventory = new InventoryPlayerNext();

	}

	public void onUpdate(EntityPlayer entityPlayer) {

		for (int i = 0; i < inventory.getSizeInventory(); i++) {

			if (inventory.getStackInSlot(i) == null) continue;
			if (!(inventory.getStackInSlot(i).getItem() instanceof IEquipment)) continue;

			IEquipment e = (IEquipment) inventory.getStackInSlot(i).getItem();

			e.onUpdate(EquipmentType.getEquipmentTypeFromSlot(i), inventory.getStackInSlot(i), entityPlayer.worldObj, entityPlayer, i);

		}

	}

	public void writeNBT(NBTTagCompound compound) {

		NBTTagCompound nbt = new NBTTagCompound();

		inventory.writeToNBT(nbt);

		compound.setTag(NBT_ID, nbt);

	}

	public void readNBT(NBTTagCompound compound) {

		if (compound.hasKey(NBT_ID)) {
			inventory.readFromNBT(compound.getCompoundTag(NBT_ID));
		}

	}

}
