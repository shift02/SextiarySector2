package shift.sextiarysector.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.tileentity.TileEntityDirection;

public class ItemBlockShaft extends ItemBlock{

	public ItemBlockShaft(Block par1) {
		super(par1);
	}


	@Override
    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
    {
		boolean result = super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ,metadata);

		if(result && world.getTileEntity(x, y, z) instanceof TileEntityDirection){
			TileEntityDirection tile = (TileEntityDirection)world.getTileEntity(x, y, z);
			tile.direction = ForgeDirection.getOrientation(side);
		}

		return result;
    }

}
