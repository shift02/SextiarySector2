package shift.sextiarysector.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import shift.mceconomy2.api.MCEconomyAPI;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.SSItems;
import shift.sextiarysector.SSShops.SSProductList;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.api.season.SeasonAPI;
import shift.sextiarysector.tileentity.TileEntityDirection;
import shift.sextiarysector.tileentity.TileEntityMonitor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMonitor extends BlockContainer {

    public BlockMonitor() {
        super(Material.iron);
        this.setStepSound(soundTypeMetal);
        this.setBlockUnbreakable();
        this.setCreativeTab(SextiarySectorAPI.TabSSEconomy);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        TileEntityMonitor tileEntity = (TileEntityMonitor) world.getTileEntity(x, y, z);

        if (par5EntityPlayer.getCurrentEquippedItem() == null) {

            if (par5EntityPlayer.isSneaking()) {

                tileEntity.changeON();
                world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);

                return true;

            } else if (tileEntity.on) {

                MCEconomyAPI.openShopGui(tileEntity.type.getList(world).id, par5EntityPlayer, world, x, y, z);

            }

            return true;
        }

        if (!world.isRemote && par5EntityPlayer.getCurrentEquippedItem() != null && par5EntityPlayer.getCurrentEquippedItem().getItem() == SSItems.ironSpanner) {

            EntityItem item = new EntityItem(world, x + 0.5d, y + 0.5d, z + 0.5d, new ItemStack(this, 1, tileEntity.type.ordinal()));

            world.spawnEntityInWorld(item);

            world.setBlockToAir(x, y, z);
            world.removeTileEntity(x, y, z);
            return true;
        }
        return false;

    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        if (world.getBlockMetadata(x, y, z) == 1) return 15;
        return 0;
    }

    public enum MonitorType {

        creeper("creeper", "creeper"), robot("robot", "robot"), unknown("", "");

        public String name;
        private ResourceLocation resource;
        private SSProductList list;
        private SSProductList[] sList;

        MonitorType(String name, String resource) {
            this.name = name;
            this.setResource(new ResourceLocation("sextiarysector:textures/models/monitor_" + resource + ".png"));
        }

        public ResourceLocation getResource() {
            return resource;
        }

        private void setResource(ResourceLocation resource) {
            this.resource = resource;
        }

        public SSProductList getList(World world) {

            if (world == null || sList == null) return this.getList();

            return sList[SeasonAPI.getSeason(world).ordinal()];
        }

        public SSProductList getList() {
            return list;
        }

        public void setList(SSProductList list) {
            this.list = list;
        }

        public void setList(SSProductList[] list) {
            this.sList = list;
            this.list = list[0];
        }

    }

    public static ItemStack getMonitor(MonitorType t) {
        return new ItemStack(SSBlocks.monitor, 1, t.ordinal());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_) {
        for (int i = 0; i < MonitorType.values().length - 1; i++) {
            p_149666_3_.add(new ItemStack(p_149666_1_, 1, i));
        }
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

            ((TileEntityDirection) par1World.getTileEntity(par2, par3, par4)).direction = ForgeDirection.getOrientation(b0);

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
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public int getRenderType() {
        return SextiarySector.proxy.monitorType;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int p_149915_2_) {
        return new TileEntityMonitor();
    }

}
