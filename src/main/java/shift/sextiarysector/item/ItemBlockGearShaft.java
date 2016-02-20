package shift.sextiarysector.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.tileentity.TileEntityDirection;

public class ItemBlockGearShaft extends ItemBlock {

    public ItemBlockGearShaft(Block par1) {
        super(par1);
        this.hasSubtypes = true;
    }

    public int getMetadata(int p_77647_1_) {
        return p_77647_1_;
    }

    @Override
    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
        boolean result = super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);

        if (result) {
            TileEntityDirection tile = (TileEntityDirection) world.getTileEntity(x, y, z);
            tile.direction = ForgeDirection.getOrientation(side);
        }

        return result;
    }

    @Override
    public void addInformation(ItemStack itemstack, EntityPlayer par1EntityPlayer, List list, boolean flag) {

        if (itemstack.getItemDamage() == 0) {
            list.add("[Mode] Up");
        } else {
            list.add("[Mode] Down");
        }

    }
}
