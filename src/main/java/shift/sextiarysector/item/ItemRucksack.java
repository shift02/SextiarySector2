package shift.sextiarysector.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.api.IEquipment;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.packet.PacketGuiId;
import shift.sextiarysector.packet.SSPacketHandler;
import shift.sextiarysector.player.EquipmentType;

public class ItemRucksack  extends Item implements IEquipment, ISSEquipment{

	public ItemRucksack()
    {
        super();
        this.setMaxStackSize(1);
        this.setCreativeTab(SextiarySectorAPI.TabSSPlayer);
    }

	@Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        player.openGui(SextiarySector.instance, 205, world, (int)player.posX, (int)player.posY, (int)player.posZ);
        return itemStack;
    }

	@Override
	public boolean canTakeStack(EquipmentType equipment, ItemStack stack,EntityPlayer player) {
		return equipment.ordinal() == EquipmentType.Bag.ordinal();
	}

	@Override
	public boolean isItemValid(EquipmentType equipment, ItemStack stack) {
		return equipment.ordinal() == EquipmentType.Bag.ordinal();
	}

	@Override
	public boolean shouldAddToList(EquipmentType equipment, ItemStack stack,EntityPlayer player) {
		return true;
	}

	@Override
	public void onTabClicked(EquipmentType equipment, ItemStack stack,EntityPlayer player) {
		SSPacketHandler.INSTANCE.sendToServer(new PacketGuiId(206));

	}

	@Override
	public String getTabName(EquipmentType equipment, ItemStack stack,EntityPlayer player) {
		return this.getUnlocalizedName() ;
	}

}
