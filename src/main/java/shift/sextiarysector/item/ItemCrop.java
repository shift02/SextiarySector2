package shift.sextiarysector.item;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import shift.sextiarysector.api.SextiarySectorAPI;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCrop extends ItemFoodDrink{

	public static ArrayList<ItemCrop> crops = new ArrayList<ItemCrop>();

	public int cost;

	public ItemCrop(int food, float foodM, int drink, float drinkM,int stamina, float staminaM, boolean p_i45339_3_) {
		super(food, foodM, drink, drinkM, stamina, staminaM, p_i45339_3_);
		this.hasSubtypes = true;

		crops.add(this);

		this.setCreativeTab(SextiarySectorAPI.TabSSAgriculture);

	}

	public String getItemStackDisplayName(ItemStack p_77653_1_)
    {

		String s = "";
		if(p_77653_1_.getItemDamage()==1){
			s ="" + EnumChatFormatting.AQUA + StatCollector.translateToLocal("item.ss.crop.big");
		}

        return ("" + s + " " + StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(p_77653_1_) + ".name")).trim();
    }

	@SideOnly(Side.CLIENT)
    public void getSubItems(Item p_150895_1_, CreativeTabs p_150895_2_, List p_150895_3_)
    {
		p_150895_3_.add(new ItemStack(p_150895_1_, 1, 0));
		p_150895_3_.add(new ItemStack(p_150895_1_, 1, 1));
    }

}
