package shift.sextiarysector.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import shift.sextiarysector.api.SextiarySectorAPI;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBuoyBase extends Block {

    public String itemIcon;

    public BlockBuoyBase() {
        super(Material.plants);
        //this.slipperiness = 2.6F;
        this.setHardness(0.2f);
        float f = 0.5F;
        float f1 = 0.015625F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f1, 0.5F + f);
        this.setCreativeTab(SextiarySectorAPI.TabSSTransport);
        //this.setCreativeTab(SextiarySector.tabsSextiarySector);
    }

    @Override
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4) {
        return super.canPlaceBlockAt(par1World, par2, par3, par4) && canBlockStay(par1World, par2, par3, par4);
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
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
        return null;
    }

    @Override
    public void addCollisionBoxesToList(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity) {
        if (par7Entity == null || !(par7Entity instanceof EntityBoat)) {
            super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
        }
    }

    @Override
    public boolean canBlockStay(World par1World, int par2, int par3, int par4) {
        return par3 >= 0 && par3 < 256 ? par1World.getBlock(par2, par3 - 1, par4).getMaterial() == Material.water && par1World.getBlockMetadata(par2, par3 - 1, par4) == 0 : false;
    }

    @Override
    public Block setBlockTextureName(String p_149658_1_) {
        this.textureName = p_149658_1_;
        this.itemIcon = p_149658_1_;
        return this;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getItemIconName() {
        return itemIcon;
    }

}
