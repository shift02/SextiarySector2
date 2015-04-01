package shift.sextiarysector.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeHooks;
import shift.sextiarysector.api.gearforce.item.IHammer;

public class ItemHammer extends Item implements IHammer {

	public ItemHammer() {

		this.setHarvestLevel("hammer", 1);
		this.maxStackSize = 1;
		this.setMaxDamage(140);

	}

	@Override
	public float getDigSpeed(ItemStack stack, Block block, int meta)
	{
		if (ForgeHooks.isToolEffective(stack, block, meta))
		{
			return 6.0f;
		}
		return super.getDigSpeed(stack, block, meta);
	}

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
	public boolean canUse(ItemStack item, EntityPlayer player, int damage) {
		return true;
	}

	@Override
	public boolean use(ItemStack item, EntityPlayer player, int damage) {

		//item.setItemDamage(damage);
		item.damageItem(damage, player);

		return true;

	}

}
