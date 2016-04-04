package shift.sextiarysector.item;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.api.agriculture.TileFarmland;
import shift.sextiarysector.block.BlockCrop;

public class ItemWateringCan extends Item {

    protected ToolMaterial theToolMaterial;
    private final Random Rand = new Random();

    public ItemWateringCan(ToolMaterial par2EnumToolMaterial) {
        this.theToolMaterial = par2EnumToolMaterial;
        this.maxStackSize = 1;
        this.setFull3D();
        this.setMaxDamage(par2EnumToolMaterial.getMaxUses());
        this.setCreativeTab(SextiarySectorAPI.TabSSAgriculture);
    }

    @Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {

        if (par1ItemStack.getItemDamage() != 0) {

            scoopWater(par1ItemStack, par3World, par2EntityPlayer);

        }

        //System.out.println("AA"+ par3World.isRemote);

        if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack) || par1ItemStack.getMaxDamage() - par1ItemStack.getItemDamage() == 1) {
            return false;
        } else {
            return this.addWater(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7);
        }

    }

    public boolean addWater(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7) {

        int x = par4;
        int y = par5;
        int z = par6;

        if (par3World.getBlock(x, y, z) != SSBlocks.farmland && par3World.getBlock(x, y, z) != SSBlocks.wood && !(par3World.getBlock(x, y, z) instanceof BlockCrop)) {
            return false;
        }

        if (par3World.getBlock(x, y, z) instanceof BlockCrop) y--;

        Block b = par3World.getBlock(x, y, z);

        TileEntity t = par3World.getTileEntity(x, y, z);

        if (!(t instanceof TileFarmland)) return false;

        TileFarmland f = (TileFarmland) t;

        int add = f.addWater(10);

        if (par3World.isRemote) {
            this.spawnParticle(par3World, x, y + 1, z);
        } else {
            par3World.playSoundAtEntity(par2EntityPlayer, "liquid.water", 1.0F, 1.0F);
            par1ItemStack.damageItem(1, par2EntityPlayer);
        }

        return true;

    }

    public void spawnParticle(World par3World, int x, int y, int z) {

        for (int j = 0; j < 1.0D + 10 * 60.0D; ++j) {

            if (this.itemRand.nextBoolean()) {

                int i1 = Rand.nextInt(4);
                float f = -0.0625F;
                double d0 = (x + Rand.nextFloat());
                double d1 = (y + Rand.nextFloat());
                double d2 = (z + Rand.nextFloat());

                if (i1 == 0) {
                    d0 = x - f;
                }

                if (i1 == 1) {
                    d0 = x + 1 + f;
                }

                if (i1 == 2) {
                    d2 = z - f;
                }

                if (i1 == 3) {
                    d2 = z + 1 + f;
                }

                double d3 = 0.0D;
                double d4 = 0.0D;

                if (i1 == 0) {
                    d3 = (-f);
                }

                if (i1 == 1) {
                    d3 = f;
                }

                if (i1 == 2) {
                    d4 = (-f);
                }

                if (i1 == 3) {
                    d4 = f;
                }

                par3World.spawnParticle("splash", d0, d1, d2, d3, 0.1D, d4);

            }

        }

    }

    public ItemStack scoopWater(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {

        MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, true);

        if (movingobjectposition == null) {
            return par1ItemStack;
        } else {
            if (movingobjectposition.typeOfHit == MovingObjectType.BLOCK) {
                int i = movingobjectposition.blockX;
                int j = movingobjectposition.blockY;
                int k = movingobjectposition.blockZ;

                if (!par2World.canMineBlock(par3EntityPlayer, i, j, k)) {
                    return par1ItemStack;
                }

                if (!par3EntityPlayer.canPlayerEdit(i, j, k, movingobjectposition.sideHit, par1ItemStack)) {
                    return par1ItemStack;
                }

                if (par2World.getBlock(i, j, k).getMaterial() == Material.water) {
                    par2World.playSoundAtEntity(par3EntityPlayer, "liquid.swim", 1.0F, 1.0F);
                    par1ItemStack.setItemDamage(1);
                }
            }

            return par1ItemStack;
        }
    }

    @Override
    public void addInformation(ItemStack itemstack, EntityPlayer par1EntityPlayer, List list, boolean flag) {
        list.add("Amount" + " : " + (itemstack.getMaxDamage() - 1 - itemstack.getItemDamageForDisplay()) + " / " + (itemstack.getMaxDamage() - 2));
    }

    @Override
    public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List itemList) {

        itemList.add(new ItemStack(this, 1, (this.getMaxDamage() - 1)));
        itemList.add(new ItemStack(this, 1, 1));

    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isFull3D() {
        return true;
    }

    @Override
    public Item setTextureName(String p_111206_1_) {
        this.iconString = "sextiarysector:tool/" + p_111206_1_;
        return this;
    }

}
