package shift.sextiarysector.fmp;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import shift.sextiarysector.tileentity.TileEntityShaft;
import codechicken.lib.raytracer.RayTracer;
import codechicken.lib.vec.BlockCoord;
import codechicken.lib.vec.Vector3;

public class ItemBlockShaftPart extends ItemBlock {

    public ItemBlockShaftPart(Block par1) {
        super(par1);
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer par2EntityPlayer, World par3World, int x, int y, int z,
            int side, float hitX, float hitY, float hitZ) {
        MovingObjectPosition hit = RayTracer.reTrace(par3World, par2EntityPlayer);
        if (hit == null) {
            return false;
        }
        BlockCoord pos = new BlockCoord(x, y, z);
        Vector3 vhit = new Vector3(hitX, hitY, hitZ);
        double d = getHitDepth(vhit, side);
        if ((d < 1.0D) && (place(itemStack, par2EntityPlayer, par3World, pos, side, vhit))) {
            return true;
        }
        pos.offset(side);
        return place(itemStack, par2EntityPlayer, par3World, pos, side, vhit);

    }

    double getHitDepth(Vector3 vhit, int side) {
        return vhit.copy().scalarProject(codechicken.lib.vec.Rotation.axes[side]) + (side % 2 ^ 0x1);
    }

    private boolean place(ItemStack item, EntityPlayer player, World world, BlockCoord pos, int side, Vector3 vhit) {
        ShaftPart part = newPart(item, player, world, pos, side, vhit);
        if ((part == null) || (!PartRegistry.canPlacePart(world, pos, part))) {
            return false;
        }
        if (!world.isRemote) {
            PartRegistry.addPart(world, pos, part);
            world.playSoundEffect(pos.x + 0.5, pos.y + 0.5, pos.z + 0.5,
                    part.getBlock().stepSound.soundName,
                    (part.getBlock().stepSound.getVolume() + 1.0F) / 2.0F,
                    part.getBlock().stepSound.getPitch() * 0.8F);
            if (!player.capabilities.isCreativeMode) {
                item.stackSize--;
                if (item.stackSize == 0) {
                    player.inventory.mainInventory[player.inventory.currentItem] = null;
                    MinecraftForge.EVENT_BUS.post(new PlayerDestroyItemEvent(player, item));
                }
            }
        }
        return true;
    }

    public ShaftPart newPart(ItemStack arg0, EntityPlayer arg1, World arg2, BlockCoord arg3, int arg4, Vector3 arg5) {
        return new ShaftPart(1, ForgeDirection.getOrientation(arg4), arg2, arg3.x, arg3.y, arg3.z);
    }

    @Override
    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
        boolean result = super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);
        TileEntityShaft tile = (TileEntityShaft) world.getTileEntity(x, y, z);
        tile.direction = ForgeDirection.getOrientation(side);

        return result;
    }

    /*
    @Override
    public boolean canPlaceItemBlockOnSide(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer, ItemStack par7ItemStack)
    {
    	return true;
    }*/
}
