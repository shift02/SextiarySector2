package shift.sextiarysector.player;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import shift.sextiarysector.container.InventoryPlayerNext;


public class EquipmentStats {

	public final String NBT_ID = "ssequipment";

	public InventoryPlayerNext inventory;

	public EquipmentStats(EntityPlayer player){

		inventory = new InventoryPlayerNext();

	}

	public void onUpdateEntity()
    {

    }

	public void writeNBT(NBTTagCompound compound) {

		NBTTagCompound nbt = new NBTTagCompound();

		inventory.writeToNBT(nbt);

		compound.setTag(NBT_ID, nbt);

	}

	public void readNBT(NBTTagCompound compound) {

		if(compound.hasKey(NBT_ID)){
			inventory.readFromNBT(compound.getCompoundTag(NBT_ID));
		}

	}

}
