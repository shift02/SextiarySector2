package shift.sextiarysector.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import shift.sextiarysector.api.SextiarySectorAPI;

public class BlockSSOre extends  Block{

	private Item oreItem;

	public BlockSSOre(Item item,int level) {
		super(Material.rock);
		this.oreItem = item;
		this.setHarvestLevel("pickaxe", level);
		this.setResistance(5.0F);
		this.setStepSound(Block.soundTypeStone);
		this.setHardness(3.0F);
		this.setCreativeTab(SextiarySectorAPI.TabSSMining);
	}

	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
		return oreItem !=null ? this.oreItem : super.getItemDropped(p_149650_1_, p_149650_2_, p_149650_3_);
    }

}
