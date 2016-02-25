package shift.sextiarysector.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import shift.sextiarysector.SSFluids;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.module.FertilizerManager;
import shift.sextiarysector.tileentity.TileEntityFarmland2;
import shift.sextiarysector.tileentity.TileEntityWood;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockWood2 extends BlockContainer {

    private IIcon blockTopIcon;
    private IIcon blockTop2Icon;

    public BlockWood2() {
        super(Material.wood);
        this.setLightOpacity(255);
        this.setStepSound(soundTypeGrass);
        this.setBlockBounds(2.0f / 16.0f, 0.0f, 2.0f / 16.0f, 14.0f / 16.0f, 15.0f / 16.0f, 14.0f / 16.0f);
        this.setHardness(1.2f);
        this.setStepSound(soundTypeWood);
        this.useNeighborBrightness = true;
    }

    @Override
    public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {

        if (FertilizerManager.getFertilizer(par5EntityPlayer.getCurrentEquippedItem()) != null && par1World.isAirBlock(x, y + 1, z)) {

            return this.setFertilizer(par1World, x, y, z, par5EntityPlayer);

        }

        if (par5EntityPlayer.getCurrentEquippedItem() != null) {

            FluidStack f = FluidContainerRegistry.getFluidForFilledItem(par5EntityPlayer.getCurrentEquippedItem());

            if (f != null) {

                TileEntityWood t = (TileEntityWood) par1World.getTileEntity(x, y, z);

                if (t.fill(ForgeDirection.getOrientation(par6), f, true) > 0) {

                    ItemStack item = par5EntityPlayer.getCurrentEquippedItem().getItem().getContainerItem(par5EntityPlayer.getCurrentEquippedItem());

                    if (!par5EntityPlayer.capabilities.isCreativeMode && !par1World.isRemote) {
                        --par5EntityPlayer.getCurrentEquippedItem().stackSize;

                        if (item != null) {

                            if (par5EntityPlayer.getCurrentEquippedItem().stackSize == 0) {

                                par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem, item);

                            } else if (!par5EntityPlayer.inventory.addItemStackToInventory(item)) {
                                par5EntityPlayer.dropPlayerItemWithRandomChoice(item, false);
                            }

                        }

                    }

                    return true;

                }

            } else {
                return false;
            }

        }

        return false;
    }

    private boolean setFertilizer(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer) {

        TileEntityWood t = (TileEntityWood) par1World.getTileEntity(x, y, z);

        if (t.getFertilizer() != null) {
            return false;
        }

        t.setFertilizer(FertilizerManager.getFertilizer(par5EntityPlayer.getCurrentEquippedItem()).getFertilizer());

        if (!par5EntityPlayer.capabilities.isCreativeMode && !par1World.isRemote) {
            --par5EntityPlayer.getCurrentEquippedItem().stackSize;
        }

        par1World.markBlockForUpdate(x, y, z);

        return true;

    }

    public boolean addWater(World par1World, int x, int y, int z) {

        TileEntityFarmland2 t = (TileEntityFarmland2) par1World.getTileEntity(x, y, z);

        return t.fill(ForgeDirection.UP, new FluidStack(SSFluids.drinkingWater, 1000), true) > 0;

    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityWood();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_) {
        this.blockIcon = p_149651_1_.registerIcon(this.getTextureName());
        this.blockTopIcon = p_149651_1_.registerIcon(this.getTextureName() + "_top");
        this.blockTop2Icon = p_149651_1_.registerIcon(this.getTextureName() + "_top2");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
        if (p_149691_1_ != 0 && p_149691_1_ != 1) {
            return this.blockIcon;
        }

        if (p_149691_2_ == 0) {
            return this.blockTopIcon;
        } else {
            return this.blockTop2Icon;
        }

    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getRenderType() {
        return SextiarySector.proxy.woodType;
    }

}
