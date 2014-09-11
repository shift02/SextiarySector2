package shift.sextiarysector.item;

import java.util.ArrayList;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.api.machine.item.IGearForceItem;
import shift.sextiarysector.api.machine.item.IGearForceItemManager;

public class GearForceItemManager implements IGearForceItemManager {

	//private final int[] r = new int[Item.itemsList.length];
	private final ArrayList<Item> r = new ArrayList<Item>();

	//減らす
	@Override
	public int storeEnergy(ItemStack itemStack, int power, int speed, boolean simulate) {

		if(!(itemStack.getItem() instanceof IGearForceItem)){
			return 0;
		}

		IGearForceItem item = (IGearForceItem)itemStack.getItem();

		if(this.getSpeed(itemStack)<=0){
			return 0;
		}

		int sp = speed;

		if(item.getMaxPowerStored(itemStack)<power){
			sp = (int) (speed*Math.pow(4, power-item.getMaxPowerStored(itemStack)));
		}

		if(item.getMaxPowerStored(itemStack)>power){
			sp = (int) (speed*Math.pow(0.25, item.getMaxPowerStored(itemStack)-power));
		}

		int speedReceived = 0;
		int speedReceived2 = 0;

		if(this.getSpeed(itemStack)>=sp){
			speedReceived = this.getSpeed(itemStack) - sp;
			speedReceived2 = sp;
		}else{
			speedReceived = 0;
			speedReceived2 = this.getSpeed(itemStack);
		}

        if (!simulate)
        {
                this.setEnergy(itemStack, item.getMaxPowerStored(itemStack), speedReceived);
        }

        return speedReceived2;
	}

	//増やす
	@Override
	public int reduceEnergy(ItemStack itemStack, int power, int speed, boolean simulate) {


		if(!(itemStack.getItem() instanceof IGearForceItem)){
			return 0;
		}

		IGearForceItem item = (IGearForceItem)itemStack.getItem();

		if(item.getMaxPowerStored(itemStack)>power){
			return 0;
		}

		int sp = speed;

		if(item.getMaxPowerStored(itemStack)<power){
			sp = (int) (speed*Math.pow(4, power-item.getMaxPowerStored(itemStack)));
		}

		int speedReceived = 0;
		int speedReceived2 = 0;

		if(item.getMaxSpeedStored(itemStack)>this.getSpeed(itemStack)+sp){
			speedReceived = this.getSpeed(itemStack)+sp;
			speedReceived2 = sp;
		}else{
			speedReceived = item.getMaxSpeedStored(itemStack);
			speedReceived2 = item.getMaxSpeedStored(itemStack)-this.getSpeed(itemStack);
		}

        if (!simulate)
        {
                this.setEnergy(itemStack, item.getMaxPowerStored(itemStack) , speedReceived);
        }

        return speedReceived2;
	}

	@Override
	public int getPower(ItemStack theItem) {

		if (theItem.getTagCompound() == null)
        {
			theItem.setTagCompound(new NBTTagCompound());
			return 0;
        }

		return theItem.getTagCompound().getInteger("gfpower");

	}

	@Override
	public int getSpeed(ItemStack theItem) {

		if (theItem.getTagCompound() == null)
        {
			theItem.setTagCompound(new NBTTagCompound());
			return 0;
        }

		return theItem.getTagCompound().getInteger("gfspeed");
	}

	@Override
    public boolean setEnergy(ItemStack itemStack, int power, int speed)
    {

		if(!(itemStack.getItem() instanceof IGearForceItem)){
			return false ;
		}

		IGearForceItem item = (IGearForceItem)itemStack.getItem();

		if (itemStack.getTagCompound() == null)
        {
                itemStack.setTagCompound(new NBTTagCompound());
        }

		int d = 100;

        int powerStored = Math.max(Math.min(power, item.getMaxPowerStored(itemStack)), 0);
        int speedStored = Math.max(Math.min(speed, item.getMaxSpeedStored(itemStack)), 0);
        itemStack.getTagCompound().setInteger("gfpower", powerStored);
        if(speedStored==0)itemStack.getTagCompound().setInteger("gfpower", 0);
        itemStack.getTagCompound().setInteger("gfspeed", speedStored);
        int damage = (int) (d - (speedStored / (double) item.getMaxSpeedStored(itemStack)) * d);
        if(speedStored==0){
        	damage = 0;
        }
        if(speedStored==item.getMaxSpeedStored(itemStack)){
        	damage = 1;
        }
        if(damage==0&&speedStored!=0){
        	damage = 1;
        }

        if(!r.contains(itemStack.getItem())&&((IGearForceItem)itemStack.getItem()).isCustomDamage(itemStack)){
        	SextiarySector.proxy.registerItemRenderer(itemStack.getItem());
        	r.add(itemStack.getItem());
        }

        //itemStack.setItemDamage(damage);
		return true;
    }

	@Override
	public boolean canUse(ItemStack itemStack, int speed) {

		if(speed<0||!(itemStack.getItem() instanceof IGearForceItem)){
			return false;
		}

		IGearForceItem item = (IGearForceItem)itemStack.getItem();

		if (itemStack.getTagCompound() == null)
        {
                itemStack.setTagCompound(new NBTTagCompound());
        }

		long speedStored = itemStack.getTagCompound().getInteger("gfspeed");

		if(item.getMaxSpeedStored(itemStack)<speed||speedStored<speed){
			return false;
		}

		return speed<=speedStored;

	}

	@Override
	public boolean use(ItemStack itemStack, int speed, EntityLivingBase entity) {

		if(speed<0||!(itemStack.getItem() instanceof IGearForceItem)){
			return false;
		}

		IGearForceItem item = (IGearForceItem)itemStack.getItem();

		if (itemStack.getTagCompound() == null)
        {
                itemStack.setTagCompound(new NBTTagCompound());
        }

		int speedStored = itemStack.getTagCompound().getInteger("gfspeed");

		if(item.getMaxSpeedStored(itemStack)<speed||speedStored<speed){
			return false;
		}

		this.setEnergy(itemStack, item.getMaxPowerStored(itemStack), speedStored-speed);

		return true;
	}

	public boolean isGearForceItem(ItemStack itemStack){
		return itemStack.getItem() instanceof IGearForceItem;
	}

}
