package shift.sextiarysector.block;

import java.util.Random;

import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class BlockSSOre extends BlockSSOreBase {

	private final Item oreItem;

	public IIcon stone;

	public BlockSSOre(Item item, int level) {
		super(level);
		this.oreItem = item;
		this.setResistance(5.0F);
		this.setHardness(3.0F);
	}

	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
	{
		return oreItem != null ? this.oreItem : super.getItemDropped(p_149650_1_, p_149650_2_, p_149650_3_);
	}

}
