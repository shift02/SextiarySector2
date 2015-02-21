package shift.sextiarysector.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import shift.sextiarysector.api.machine.item.IHammer;

public class ItemHammer extends Item implements IHammer {

	/*
	@Override
	public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
	{
		Block block = p_77648_3_.getBlock(p_77648_4_, p_77648_5_, p_77648_6_);

		if (block == Blocks.anvil) {

			if (p_77648_3_.isRemote) return true;

			p_77648_2_.openGui(SextiarySector.instance, 10, p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_);
			return true;

		}

		return false;
	}*/

	@Override
	public boolean canUse(ItemStack item) {
		return true;
	}

	@Override
	public boolean use(ItemStack item) {
		return true;
	}

}
