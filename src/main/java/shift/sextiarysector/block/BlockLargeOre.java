package shift.sextiarysector.block;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockLargeOre extends BlockSSOreBase {

	private final Item oreItem;
	private final Block oreBlock;

	public BlockLargeOre(Item item, Block block, int level) {
		super(level);
		this.oreItem = item;
		this.oreBlock = block;
		this.setResistance(5.0F);
		this.setHardness(3.0F);
	}

	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
	{
		return oreItem;
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
	{
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

		int count = quantityDropped(metadata, fortune, world.rand);
		for (int i = 0; i < count; i++)
		{
			Item item = getItemDropped(metadata, world.rand, fortune);
			if (item != null)
			{
				ret.add(new ItemStack(item, 1, damageDropped(metadata)));
				if (oreBlock == Blocks.coal_ore) {
					ret.add(new ItemStack(Items.coal, 2));
				} else {
					ret.add(new ItemStack(oreBlock, 1, damageDropped(metadata)));
				}

			}
		}
		return ret;
	}

	/*
	public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean willHarvest)
	{
		if(!willHarvest){
			return super.removedByPlayer(world, player, x, y, z, willHarvest);
		}else if(world.getBlockMetadata(x, y, z)==0){
			return world.setBlock(x, y, z, oreBlock);
		}

		return world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z)-1, 4);

	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_)
	{
		for(int i=0;i<4;i++){
			p_149666_3_.add(new ItemStack(p_149666_1_,1,i));
		}
	}*/

	@Override
	protected boolean canSilkHarvest()
	{
		return false;
	}

}
