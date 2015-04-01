package shift.sextiarysector.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import shift.sextiarysector.api.gearforce.item.GearForceItemAPI;
import shift.sextiarysector.api.gearforce.item.ItemGearForce;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemGearStorage extends ItemGearForce{

	private IIcon full;

	public ItemGearStorage(int power, int maxSpeed, int slot) {
		super(power, maxSpeed, slot);
	}

	@SideOnly(Side.CLIENT)
    public IIcon getIconIndex(ItemStack p_77650_1_)
    {

		if(GearForceItemAPI.manager.getSpeed(p_77650_1_)>0){
			return this.full;
		}

        return this.getIconFromDamage(p_77650_1_.getItemDamage());
    }

	public IIcon getIcon(ItemStack stack, int pass)
    {
		if(GearForceItemAPI.manager.getSpeed(stack)>0){
			return this.full;
		}

        return this.getIconFromDamage(stack.getItemDamage());
    }

	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister p_94581_1_)
    {
        this.itemIcon = p_94581_1_.registerIcon(this.getIconString());
        this.full = p_94581_1_.registerIcon(this.getIconString()+"_full");
    }

}
