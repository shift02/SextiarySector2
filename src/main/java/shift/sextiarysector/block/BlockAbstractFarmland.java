package shift.sextiarysector.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.SSCrops;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.api.agriculture.ICrop;
import shift.sextiarysector.api.agriculture.TileFarmland;
import shift.sextiarysector.tileentity.TileEntityCrop;
import shift.sextiarysector.tileentity.TileEntityFarmland;

public abstract class BlockAbstractFarmland extends BlockContainer {

    public BlockAbstractFarmland() {
        super(Material.ground);
        this.setCreativeTab(SextiarySectorAPI.TabSSAgriculture);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {

        ItemStack item = player.getCurrentEquippedItem();

        TileFarmland farmland = (TileFarmland) world.getTileEntity(x, y, z);

        if (item == null) return false;

        //水バケツだったら
        if (item.getItem() == Items.water_bucket) return this.addWater(world, player, item, farmland);

        ICrop crop = SSCrops.cropManager.getCrop(item, player);

        if (crop == null) return false;

        if (!crop.canBlockStay(getName(), farmland)) return false;

        boolean isSet = world.setBlock(x, y + 1, z, SSBlocks.crop);

        if (isSet) {

            TileEntityCrop tCrop = (TileEntityCrop) world.getTileEntity(x, y + 1, z);

            tCrop.setCrop(crop);

            if (!player.capabilities.isCreativeMode && !world.isRemote) {
                --item.stackSize;
            }

        }

        return true;

    }

    private boolean addWater(World world, EntityPlayer player, ItemStack item, TileFarmland farmland) {

        if (!player.capabilities.isCreativeMode && !world.isRemote) {
            --item.stackSize;

            ItemStack container = item.getItem().getContainerItem(item);

            if (container != null) {

                if (item.stackSize == 0) {

                    player.inventory.setInventorySlotContents(player.inventory.currentItem, container);

                } else if (!player.inventory.addItemStackToInventory(container)) {
                    player.dropPlayerItemWithRandomChoice(container, false);
                }

            }

        }

        int add = farmland.addWater(15);

        return true;

    }

    public abstract String getName();

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityFarmland();
    }

}
