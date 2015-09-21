package shift.sextiarysector.api.gearforce.item;

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
public class ItemGearForce extends Item implements IGearForceItem {

    int power;
    int speed;

    int slot;

    public ItemGearForce(int power, int maxSpeed, int slot) {

        this.setMaxStackSize(1);
        this.setNoRepair();

        this.power = power;
        this.speed = maxSpeed;

        this.slot = slot;

    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer par3EntityPlayer, List list, boolean par4) {

        /*
        if(!par3EntityPlayer.capabilities.isCreativeMode){
        	return ;
        }*/

        //EnumChatFormatting color;// = "";

        NumberFormat nf = NumberFormat.getNumberInstance();

        int power = GearForceItemAPI.manager.getPower(itemStack);
        int speed = GearForceItemAPI.manager.getSpeed(itemStack);

        list.add("" + EnumChatFormatting.RED + "Power " + EnumChatFormatting.GRAY + power + " / " + this.getMaxPower(itemStack) + "");
        list.add("" + EnumChatFormatting.BLUE + "Speed " + EnumChatFormatting.GRAY + nf.format(speed) + " / " + nf.format(this.getMaxSpeed(itemStack)) + "");

    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {

        if (GearForceItemAPI.manager.getSpeed(stack) == 0) return false;

        return true;//stack.isItemDamaged();
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        return 1.0D - ((double) GearForceItemAPI.manager.getSpeed(stack) / (double) this.getMaxSpeed(stack));
    }

    @Override
    public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        ItemStack empty = new ItemStack(this);
        ItemStack full = new ItemStack(this);

        GearForceItemAPI.manager.setEnergy(empty, this.getMaxPower(empty), 0);
        GearForceItemAPI.manager.setEnergy(full, this.getMaxPower(empty), this.getMaxSpeed(full));

        par3List.add(empty);
        par3List.add(full);
    }

    @Override
    public int getMaxPower(ItemStack container) {
        return this.power;
    }

    @Override
    public int getMaxSpeed(ItemStack container) {
        return this.speed;
    }

    @Override
    public boolean canSetSlot(int power) {
        return slot == power;
    }

}
