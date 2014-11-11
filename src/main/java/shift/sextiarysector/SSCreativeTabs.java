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
		SextiarySectorAPI.TabSSFluid = new CreativeTabSSFluid();
		SextiarySectorAPI.TabSSMachine = new CreativeTabSSMachine();
		SextiarySectorAPI.TabSSAgriculture = new CreativeTabSSAgriculture();
		SextiarySectorAPI.TabSSEconomy = new CreativeTabSSEconomy();
		SextiarySectorAPI.TabSSMagic = new CreativeTabSSMagic();

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

	private static class CreativeTabSSFluid extends CreativeTabs{

		public CreativeTabSSFluid() {
			super("ss.fluid");
		}

		@Override
		public Item getTabIconItem() {
			return null;//SSBlocks.LargeFurnace.g;
		}

		@SideOnly(Side.CLIENT)
	    public ItemStack getIconItemStack()
	    {
			return new ItemStack(SSBlocks.bottle,1);
	    }

	}

	private static class CreativeTabSSMachine extends CreativeTabs{

		public CreativeTabSSMachine() {
			super("ss.machine");
		}

		@Override
		public Item getTabIconItem() {
			return null;//SSBlocks.LargeFurnace.g;
		}

		@SideOnly(Side.CLIENT)
	    public ItemStack getIconItemStack()
	    {
			return new ItemStack(SSBlocks.woodShaft,1);
	    }

	}

	private static class CreativeTabSSAgriculture extends CreativeTabs{

		public CreativeTabSSAgriculture() {
			super("ss.agriculture");
		}

		@Override
		public Item getTabIconItem() {
			return null;//SSBlocks.LargeFurnace.g;
		}

		@SideOnly(Side.CLIENT)
	    public ItemStack getIconItemStack()
	    {
			return new ItemStack(SSBlocks.turnip,1);
	    }

	}

	private static class CreativeTabSSEconomy extends CreativeTabs{

		public CreativeTabSSEconomy() {
			super("ss.economy");
		}

		@Override
		public Item getTabIconItem() {
			return null;//SSBlocks.LargeFurnace.g;
		}

		@SideOnly(Side.CLIENT)
	    public ItemStack getIconItemStack()
	    {
			return new ItemStack(SSBlocks.creeperChest,1);
	    }

	}

	private static class CreativeTabSSMagic extends CreativeTabs{

		public CreativeTabSSMagic() {
			super("ss.magic");
		}

		@Override
		public Item getTabIconItem() {
			return null;//SSBlocks.LargeFurnace.g;
		}

		@SideOnly(Side.CLIENT)
	    public ItemStack getIconItemStack()
	    {
			return new ItemStack(SSItems.magicDust,1);
	    }

	}

}
