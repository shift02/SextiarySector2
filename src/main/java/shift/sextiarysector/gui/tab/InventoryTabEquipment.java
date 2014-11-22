package shift.sextiarysector.gui.tab;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import shift.sextiarysector.item.ISSEquipment;
import shift.sextiarysector.player.EntityPlayerManager;
import shift.sextiarysector.player.EquipmentType;
import cpw.mods.fml.client.FMLClientHandler;

public class InventoryTabEquipment extends AbstractTab {

	private static Minecraft mc = FMLClientHandler.instance().getClient();

	EquipmentType type;
	ItemStack item;

	public InventoryTabEquipment(EquipmentType type,ItemStack item){
		this.type = type;
		this.item = item;
	}

	private boolean hasItem(){
		return false;
	}

	private ItemStack getSlotItem(){

		for(int i:type.getSlot()){

			ItemStack slot = EntityPlayerManager.getEquipmentStats(mc.thePlayer).inventory.getStackInSlot(i);

			if(slot!=null && slot.isItemEqual(item)){
				return slot;
			}

		}


		return null;
	}

	@Override
	public void onTabClicked() {

		if(getSlotItem()==null){
			return ;
		}

		((ISSEquipment)this.getItemStack().getItem()).onTabClicked(type, getSlotItem(), mc.thePlayer);

	}

	@Override
	public ItemStack getItemStack() {
		return item;
	}

	@Override
	public boolean shouldAddToList() {

		if(getSlotItem()==null){
			return false;
		}

		return ((ISSEquipment)this.getItemStack().getItem()).shouldAddToList(type, getSlotItem(), mc.thePlayer);
	}

	@Override
	public String getTabName() {

		if(getSlotItem()==null){
			return "player.tab."+"";
		}

		return "player.tab."+((ISSEquipment)this.getItemStack().getItem()).getTabName(type, getSlotItem(), mc.thePlayer);
	}


}
