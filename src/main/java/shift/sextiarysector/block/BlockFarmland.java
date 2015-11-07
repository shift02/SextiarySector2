package shift.sextiarysector.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.SSCrops;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.api.agriculture.ICrop;
import shift.sextiarysector.tileentity.TileEntityFarmland;

public class BlockFarmland extends BlockContainer {

    public BlockFarmland() {
        super(Material.ground);
        this.setCreativeTab(SextiarySectorAPI.TabSSAgriculture);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {

        ItemStack item = player.getCurrentEquippedItem();

        if (item == null) return false;

        ICrop crop = SSCrops.cropManager.getCrop(item);

        if (crop == null) return false;

        world.setBlock(x, y + 1, z, SSBlocks.crop);

        return true;

    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityFarmland();
    }

}
