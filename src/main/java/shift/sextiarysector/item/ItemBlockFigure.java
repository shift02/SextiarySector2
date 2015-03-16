package shift.sextiarysector.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.block.BlockFigure;
import shift.sextiarysector.tileentity.TileEntityFigure;

public class ItemBlockFigure extends ItemBlock {

	private static ItemStack fItem = new ItemStack(Blocks.dirt);

	public ItemBlockFigure(Block p_i45328_1_) {
		super(p_i45328_1_);
		this.hasSubtypes = true;
	}

	@Override
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
	{
		boolean result = super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);

		if (result && world.getTileEntity(x, y, z) instanceof TileEntityFigure) {
			TileEntityFigure tile = (TileEntityFigure) world.getTileEntity(x, y, z);

			tile.setFigure(BlockFigure.getFigureItem(stack));
			tile.setEdition(BlockFigure.getEdition(stack));
		}

		return result;
	}

	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer par1EntityPlayer, List list, boolean flag)
	{

		try {

			NBTTagCompound nbt = itemstack.stackTagCompound;

			if (nbt == null)
			{
				return;
			}

			if (nbt.hasKey("figure")) {

				fItem.readFromNBT(nbt.getCompoundTag("figure"));
				list.add(StatCollector.translateToLocal("tooltip.name") + " : " + fItem.getDisplayName());
				list.add(StatCollector.translateToLocal("tooltip.edition") + " : " + StatCollector.translateToLocal("edition." + BlockFigure.getEdition(itemstack)));

			}

		} catch (Exception e) {

			SextiarySector.Log.catching(e);

		}

	}

}
