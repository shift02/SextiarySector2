package shift.sextiarysector;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import shift.sextiarysector.api.SextiarySectorAPI;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SSCreativeTabs {

	public static void initCreativeTabs(){

		SextiarySectorAPI.TabSSCore = new CreativeTabSSCore();

	}

	private static class CreativeTabSSCore extends CreativeTabs{

		public CreativeTabSSCore() {
			super("ss.core");
		}

		@Override
		public Item getTabIconItem() {
			return null;//SSBlocks.LargeFurnace.g;
		}

		@SideOnly(Side.CLIENT)
	    public ItemStack getIconItemStack()
	    {
			return new ItemStack(SSBlocks.LargeFurnace,1);
	    }

	}

}
