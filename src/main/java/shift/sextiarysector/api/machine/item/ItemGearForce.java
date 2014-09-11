package shift.sextiarysector.api.machine.item;

import java.text.NumberFormat;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

/**
 * ItemGearForce {@link IGearForceItem} の実装例
 * @see IGearForceItem
 * @version 1.0.0
 * @author Shift02
 */
public class ItemGearForce  extends Item implements IGearForceItem{

	int power;
	int speed;

	int slot;

	public ItemGearForce(int power, int maxSpeed, int slot){

		this.setMaxStackSize(1);
        this.setNoRepair();

		this.power=power;
		this.speed=maxSpeed;

		this.slot=slot;

	}

	@Override
    public void addInformation(ItemStack itemStack, EntityPlayer par3EntityPlayer, List list, boolean par4)
    {

		/*
		if(!par3EntityPlayer.capabilities.isCreativeMode){
			return ;
		}*/

		//EnumChatFormatting color;// = "";

		NumberFormat nf = NumberFormat.getNumberInstance();

		int power = GearForceItem.manager.getPower(itemStack);
		int speed = GearForceItem.manager.getSpeed(itemStack);

		list.add(""+ EnumChatFormatting.RED + "Power " + EnumChatFormatting.GRAY + power +" / "+this.getMaxPowerStored(itemStack)+ "");
		list.add(""+ EnumChatFormatting.BLUE + "Speed " + EnumChatFormatting.GRAY + nf.format(speed) +" / "+ nf.format(this.getMaxSpeedStored(itemStack))+ "");

    }

	@Override
    public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List)
    {
		ItemStack empty = new ItemStack(this);
		ItemStack full = new ItemStack(this);

		GearForceItem.manager.setEnergy(empty, this.getMaxPowerStored(empty), 0);
		GearForceItem.manager.setEnergy(full, this.getMaxPowerStored(empty), this.getMaxSpeedStored(full));

		par3List.add(empty);
		par3List.add(full);
    }

	@Override
	public int getMaxPowerStored(ItemStack container) {
		return this.power;
	}

	@Override
	public int getMaxSpeedStored(ItemStack container) {
		return this.speed;
	}

	@Override
	public boolean isCustomDamage(ItemStack container) {
		return true;
	}

	@Override
	public boolean canSetSlot(int power) {
		return slot>=power;
	}

}
