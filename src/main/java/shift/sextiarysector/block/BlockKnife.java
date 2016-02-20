package shift.sextiarysector.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import net.minecraftforge.fluids.FluidStack;
import shift.sextiarysector.tileentity.TileEntityKnife;

public class BlockKnife extends BlockContainer {

    private final Random rand = new Random();

    public static ItemBlock block;

    public BlockKnife() {

        super(Material.wood);
        //float b = 1.0f / 16.0f;
        //this.setBlockBounds(b, 0, b, 1.0f - b, 1, 1.0f - b);
        this.setHardness(0.4f);
        this.setStepSound(soundTypeWood);

        this.block = new ItemBlock(this);

    }

    //プレイヤーの右クリック処理
    @Override
    public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {

        ItemStack itemstack = player.inventory.getCurrentItem();
        TileEntityKnife tank = (TileEntityKnife) par1World.getTileEntity(x, y, z);

        if (tank == null) return false;
        if (!FluidContainerRegistry.isContainer(itemstack)) return false;

        FluidStack fluid = tank.getFluidStack();

        if (FluidContainerRegistry.isFilledContainer(itemstack)) {
            return this.addFluid(par1World, x, y, z, itemstack, tank, player);
        } else if (fluid != null && fluid.getFluid() != null && FluidContainerRegistry.isContainer(itemstack)) {
            return this.drainFluid(par1World, x, y, z, itemstack, tank, player);
        }

        return false;

    }

    public boolean addFluid(World world, int x, int y, int z, ItemStack itemstack, TileEntityKnife tank, EntityPlayer player) {

        FluidStack fluid = FluidContainerRegistry.getFluidForFilledItem(itemstack);
        int put = tank.fill(ForgeDirection.UNKNOWN, fluid, false);

        if (put != fluid.amount) return false;

        tank.fill(ForgeDirection.UNKNOWN, fluid, true);
        this.addContainerItemToPlayer(itemstack, FluidContainerRegistry.drainFluidContainer(itemstack), player);

        tank.markDirty();
        world.markBlockForUpdate(x, y, z);

        world.playSoundAtEntity(player, "random.pop", 0.4F, 1.8F);

        return true;

    }

    public boolean drainFluid(World world, int x, int y, int z, ItemStack empty, TileEntityKnife tank, EntityPlayer player) {

        for (FluidContainerData f : FluidContainerRegistry.getRegisteredFluidContainerData()) {

            if (!(f.emptyContainer.isItemEqual(empty) && f.fluid.isFluidEqual(tank.getFluidStack()) && f.fluid.amount <= tank.tank.getFluidAmount())) continue;

            this.addContainerItemToPlayer(empty, FluidContainerRegistry.fillFluidContainer(tank.getFluidStack(), empty), player);
            tank.tank.drain(f.fluid.amount, true);

            tank.markDirty();
            world.markBlockForUpdate(x, y, z);

            world.playSoundAtEntity(player, "random.pop", 0.4F, 1.8F);

            return true;

        }

        return false;

    }

    public void addContainerItemToPlayer(ItemStack itemstack, ItemStack newstack, EntityPlayer player) {

        if (player.capabilities.isCreativeMode) return;

        if (itemstack.stackSize == 1 && player.worldObj.isRemote) return;

        --itemstack.stackSize;

        ItemStack emptyContainer = newstack;

        if (emptyContainer != null) {

            if (!player.capabilities.isCreativeMode) {

                if (itemstack.stackSize <= 0) {

                    player.inventory.setInventorySlotContents(player.inventory.currentItem, emptyContainer);

                } else {

                    if (!player.inventory.addItemStackToInventory(emptyContainer)) {
                        player.dropPlayerItemWithRandomChoice(emptyContainer, false);

                    }

                    player.inventory.markDirty();

                }

            }

        }

    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    //@Override
    //public int getRenderType() {
    //    return SextiarySector.proxy.tankType;
    //}

    @Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4) {
        super.onBlockAdded(par1World, par2, par3, par4);
    }

    /**
     * set a blocks direction
     */
    private void setDefaultDirection(World par1World, int par2, int par3, int par4) {

        if (!par1World.isRemote) {
            Block block = par1World.getBlock(par2, par3, par4 - 1);
            Block block1 = par1World.getBlock(par2, par3, par4 + 1);
            Block block2 = par1World.getBlock(par2 - 1, par3, par4);
            Block block3 = par1World.getBlock(par2 + 1, par3, par4);

            TileEntityKnife tileEntity = (TileEntityKnife) par1World.getTileEntity(par2, par3, par4);

            byte b0 = 3;

            if (block.func_149730_j() && !block1.func_149730_j()) {
                b0 = 3;
            }

            if (block1.func_149730_j() && !block.func_149730_j()) {
                b0 = 2;
            }

            if (block2.func_149730_j() && !block3.func_149730_j()) {
                b0 = 5;
            }

            if (block3.func_149730_j() && !block2.func_149730_j()) {
                b0 = 4;
            }

            par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 2);
            tileEntity.direction = ForgeDirection.getOrientation(b0);
        }
    }

    @Override
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {

        int l = MathHelper.floor_double(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
        TileEntityKnife tileEntity = (TileEntityKnife) par1World.getTileEntity(par2, par3, par4);

        if (l == 0) {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
            tileEntity.direction = ForgeDirection.VALID_DIRECTIONS[2];
        }

        if (l == 1) {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 5, 2);
            tileEntity.direction = ForgeDirection.VALID_DIRECTIONS[5];
        }

        if (l == 2) {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
            tileEntity.direction = ForgeDirection.VALID_DIRECTIONS[3];
        }

        if (l == 3) {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 4, 2);
            tileEntity.direction = ForgeDirection.VALID_DIRECTIONS[4];
        }

        tileEntity.setKnife(par6ItemStack);

    }

    @Override
    public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6) {

        TileEntityKnife tileentity = (TileEntityKnife) par1World.getTileEntity(par2, par3, par4);

        if (tileentity.getKnife() != null) {

            this.dropItem(tileentity.getKnife(), par1World, par2, par3, par4);
            par1World.func_147453_f(par2, par3, par4, par5);

        }

        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }

    private void dropItem(ItemStack itemstack, World par1World, int par2, int par3, int par4) {

        if (itemstack != null) {
            float f = this.rand.nextFloat() * 0.8F + 0.1F;
            float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
            float f2 = this.rand.nextFloat() * 0.8F + 0.1F;

            while (itemstack.stackSize > 0) {
                int k1 = this.rand.nextInt(21) + 10;

                if (k1 > itemstack.stackSize) {
                    k1 = itemstack.stackSize;
                }

                itemstack.stackSize -= k1;
                EntityItem entityitem = new EntityItem(par1World, par2 + f, par3 + f1, par4 + f2, new ItemStack(itemstack.getItem(), k1, itemstack.getItemDamage()));

                if (itemstack.hasTagCompound()) {
                    entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
                }

                float f3 = 0.05F;
                entityitem.motionX = (float) this.rand.nextGaussian() * f3;
                entityitem.motionY = (float) this.rand.nextGaussian() * f3 + 0.2F;
                entityitem.motionZ = (float) this.rand.nextGaussian() * f3;
                par1World.spawnEntityInWorld(entityitem);
            }
        }

    }

    @Override
    public int quantityDropped(Random p_149745_1_) {
        return 0;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityKnife();
    }
}
