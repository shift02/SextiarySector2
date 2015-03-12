package shift.sextiarysector.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import shift.mceconomy2.api.MCEconomyAPI;
import shift.sextiarysector.SSShops.SSProductList;
import shift.sextiarysector.api.IEquipment;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.api.season.SeasonAPI;
import shift.sextiarysector.gui.tab.AbstractTab;
import shift.sextiarysector.gui.tab.TabManager;
import shift.sextiarysector.player.EquipmentType;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemShopRing extends Item implements IEquipment, ISSEquipment {

	private SSProductList list;
	private SSProductList[] sList;
	public AbstractTab tab;

	@SideOnly(Side.CLIENT)
	public boolean isRing;

	public ItemShopRing() {
		this.setMaxStackSize(1);
		this.setCreativeTab(SextiarySectorAPI.TabSSEconomy);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public SSProductList getList(World world) {

		if (world == null || sList == null) return this.getList();

		return sList[SeasonAPI.getSeason(world).ordinal()];
	}

	public SSProductList getList() {
		return list;
	}

	public void setList(SSProductList list) {
		this.list = list;
	}

	public void setList(SSProductList[] list) {
		this.sList = list;
		this.list = list[0];
	}

	public void setTab(AbstractTab tab) {
		this.tab = tab;
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onOpenShopGuiEvent(shift.mceconomy2.api.shop.OpenShopGuiEvent event) {

		//if(!isShop(event.shopID))return;
		if (!this.isRing) return;

		this.isRing = false;

		int xSize = 176;
		int ySize = 166;
		int guiLeft = (event.gui.width - xSize) / 2;
		int guiTop = (event.gui.height - ySize) / 2;

		TabManager.updateTabValues(guiLeft, guiTop, event.buttonList, tab, false);

	}

	private boolean isShop(int id) {

		if (sList != null) {
			for (SSProductList l : sList) {
				if (l.id == id) return true;
			}
		}

		return list.id == id;

	}

	@Override
	public String getTabName(EquipmentType equipment, ItemStack stack, EntityPlayer player) {
		return this.getUnlocalizedName();
	}

	@Override
	public boolean shouldAddToList(EquipmentType equipment, ItemStack stack, EntityPlayer player) {
		return true;
	}

	@Override
	public void onTabClicked(EquipmentType equipment, ItemStack stack, EntityPlayer player) {
		this.isRing = true;
		MCEconomyAPI.openShopGui(this.getList(player.worldObj).id, player, player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);
	}

	@Override
	public boolean canTakeStack(EquipmentType equipment, ItemStack stack, EntityPlayer player) {
		return equipment.equals(EquipmentType.Ring);
	}

	@Override
	public boolean isItemValid(EquipmentType equipment, ItemStack stack) {
		return equipment.equals(EquipmentType.Ring);
	}

}
