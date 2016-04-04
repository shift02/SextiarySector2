package shift.sextiarysector.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import shift.mceconomy2.api.MCEconomyAPI;
import shift.sextiarysector.SSMaterials;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.api.shop.IShopMemory;
import shift.sextiarysector.item.ItemSpanner;
import shift.sextiarysector.tileentity.TileEntityDirection;
import shift.sextiarysector.tileentity.TileEntityShopMonitor;

public class BlockShopMonitor extends BlockContainer {

    private final Random rand = new Random();

    public BlockShopMonitor() {
        super(SSMaterials.machine);
        this.setHardness(3.0F);
        this.setResistance(40.0F);
        this.setHarvestLevel("spanner", 1);
        this.setStepSound(soundTypeMetal);
        //this.setBlockUnbreakable();
        this.setCreativeTab(SextiarySectorAPI.TabSSEconomy);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {

        TileEntityShopMonitor tileEntity = (TileEntityShopMonitor) world.getTileEntity(x, y, z);

        ItemStack item = player.getCurrentEquippedItem();

        if (item != null && item.getItem() instanceof IShopMemory) {

            if (tileEntity.getMemory() != null) return false;

            tileEntity.setMemory(item);

            player.inventory.setInventorySlotContents(player.inventory.currentItem, null);

            //if (!world.isRemote) world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, "random.click", 0.3F, 0.6F);
            if (!world.isRemote) world.playSoundEffect(x, y, z, "random.wood_click", 1.0F, world.rand.nextFloat() * 0.1F + 0.9F);
            //if (!world.isRemote) world.playAuxSFX(1001, x, y, z, 0);

            return true;

        }

        if (item != null && item.getItem() instanceof ItemSpanner) {

            if (tileEntity.getMemory() == null) return false;

            ItemStack memory = tileEntity.getMemory();
            tileEntity.setMemory(null);

            if (!world.isRemote) {

                world.playSoundEffect(x, y, z, "random.break", 1.0F, world.rand.nextFloat() * 0.1F + 0.9F);

                EntityItem eItem = new EntityItem(world, x + 0.5d, y + 0.6d, z + 0.5d, memory);

                world.spawnEntityInWorld(eItem);
            }

            return true;

        }

        ItemStack tItem = tileEntity.getMemory();

        if (player.isSneaking()) {

            if (tItem == null) {

                if (!world.isRemote) world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, "random.click", 0.3F, 0.6F);
                return true;

            }

            tileEntity.changeON();
            world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);

            return true;

        } else if (tileEntity.on) {

            if (tItem == null) {

                tileEntity.changeON();
                world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
                return true;

            }

            if (!(tItem.getItem() instanceof IShopMemory)) return true;

            IShopMemory memory = (IShopMemory) tItem.getItem();

            MCEconomyAPI.openShopGui(memory.getShopID(world, player), player, world, x, y, z);

        }

        return true;

    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        if (world.getBlockMetadata(x, y, z) == 1) return 15;
        return 0;
    }

    @Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4) {
        super.onBlockAdded(par1World, par2, par3, par4);
        this.setDefaultDirection(par1World, par2, par3, par4);
    }

    private void setDefaultDirection(World par1World, int par2, int par3, int par4) {

        if (!par1World.isRemote) {
            Block block = par1World.getBlock(par2, par3, par4 - 1);
            Block block1 = par1World.getBlock(par2, par3, par4 + 1);
            Block block2 = par1World.getBlock(par2 - 1, par3, par4);
            Block block3 = par1World.getBlock(par2 + 1, par3, par4);

            TileEntityDirection tileEntity = (TileEntityDirection) par1World.getTileEntity(par2, par3, par4);

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

            tileEntity.direction = ForgeDirection.getOrientation(b0);

            //par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 2);
        }
    }

    @Override
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase,
            ItemStack par6ItemStack) {
        int l = MathHelper.floor_double(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

        if (l == 0) {
            ((TileEntityDirection) par1World.getTileEntity(par2, par3, par4)).direction = ForgeDirection
                    .getOrientation(2);
        }

        if (l == 1) {
            ((TileEntityDirection) par1World.getTileEntity(par2, par3, par4)).direction = ForgeDirection
                    .getOrientation(5);
        }

        if (l == 2) {
            ((TileEntityDirection) par1World.getTileEntity(par2, par3, par4)).direction = ForgeDirection
                    .getOrientation(3);
        }

        if (l == 3) {
            ((TileEntityDirection) par1World.getTileEntity(par2, par3, par4)).direction = ForgeDirection
                    .getOrientation(4);
        }

        par1World.markBlockForUpdate(par2, par3, par4);

    }

    @Override
    public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6) {

        TileEntityShopMonitor tileentity = (TileEntityShopMonitor) par1World.getTileEntity(par2, par3, par4);

        if (tileentity.getMemory() != null) {

            this.dropItem(tileentity.getMemory(), par1World, par2, par3, par4);
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
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public int getRenderType() {
        return SextiarySector.proxy.shopMonitorType;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int p_149915_2_) {
        return new TileEntityShopMonitor();
    }

}
