package shift.sextiarysector.player;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import shift.mceconomy2.api.MCEconomyAPI;
import shift.sextiarysector.api.season.SeasonAPI;
import shift.sextiarysector.container.InventoryShippingBox;
import shift.sextiarysector.module.ModuleStatistics;

public class ShippingBoxStats {

	public final String NBT_ID = "ssshippingbox";

	public InventoryShippingBox inventory;

	public ShippingBoxStats() {

		inventory = new InventoryShippingBox();

	}

	public void onUpdate(EntityPlayer entityPlayer) {

		if (SeasonAPI.getHour(entityPlayer.worldObj, 1) != 7 || SeasonAPI.getMinute(entityPlayer.worldObj) != 0) return;

		for (int i = 0; i < inventory.getSizeInventory(); i++) {

			ItemStack item = inventory.getStackInSlot(i);

			if (item == null) continue;

			if (MCEconomyAPI.getPurchase(item) < 0) continue;

			int mp = MCEconomyAPI.getPurchase(item) * item.stackSize;

			int addmp = MCEconomyAPI.addPlayerMP(entityPlayer, mp, true);

			if (addmp != mp) continue;

			MCEconomyAPI.addPlayerMP(entityPlayer, addmp, false);
			entityPlayer.addStat(ModuleStatistics.objectSellStats[Item.getIdFromItem(item.getItem())], item.stackSize);

			inventory.setInventorySlotContents(i, null);
			inventory.markDirty();

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
