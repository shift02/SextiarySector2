package shift.sextiarysector.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import shift.sextiarysector.block.BlockMonitor.MonitorType;
import shift.sextiarysector.tileentity.TileEntityMonitor;

public class ItemBlockMonitor extends ItemBlock {

    public ItemBlockMonitor(Block p_i45328_1_) {
        super(p_i45328_1_);
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer par3EntityPlayer, List list, boolean par4) {
        list.add("[" + StatCollector.translateToLocal("monitor.type") + "] " + StatCollector.translateToLocal("monitor." + MonitorType.values()[itemStack.getItemDamage()].name));
    }

    @Override
    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
        boolean result = super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);

        if (result) {
            TileEntityMonitor tile = (TileEntityMonitor) world.getTileEntity(x, y, z);
            tile.type = MonitorType.values()[stack.getItemDamage()];
        }

        return result;
    }

}
