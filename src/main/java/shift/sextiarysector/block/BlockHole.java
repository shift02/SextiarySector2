package shift.sextiarysector.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.SSAchievement;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.SextiarySector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockHole extends Block {

	public BlockHole() {
		super(Material.grass);
		this.setLightOpacity(255);
		this.setStepSound(soundTypeGrass);
		this.useNeighborBrightness = true;
	}

	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
		return Blocks.dirt.getIcon(p_149691_1_, p_149691_2_);
    }

	public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {

        ItemStack itemstack = p_149727_5_.inventory.getCurrentItem();

        if (itemstack == null){
        	return false;
        }

        if(itemstack.getItem() == Items.bucket && p_149727_1_.getBlockMetadata(p_149727_2_, p_149727_3_, p_149727_4_)==1){
        	this.drawWater(p_149727_1_, p_149727_2_, p_149727_3_, p_149727_4_);
        	return true;

        }else if(itemstack.getItem() == Items.water_bucket  && p_149727_1_.getBlockMetadata(p_149727_2_, p_149727_3_, p_149727_4_)==0){
        	this.addWater(p_149727_1_, p_149727_2_, p_149727_3_, p_149727_4_);
        	p_149727_5_.addStat(SSAchievement.paddy, 1);
        	return true;
        }

		return false;

    }

	public void addWater(World w, int x, int y, int z){

		w.setBlock(x, y, z, SSBlocks.paddy);
		w.markBlockForUpdate(x, y, z);

		for(int i = 2;i<6;i++){

			int x1 = x + ForgeDirection.getOrientation(i).offsetX;
			int y1 = y + ForgeDirection.getOrientation(i).offsetY;
			int z1 = z + ForgeDirection.getOrientation(i).offsetZ;

			if(w.getBlock(x1, y1, z1)==this && w.getBlockMetadata(x1, y1, z1) == 0){
				((BlockHole)w.getBlock(x1, y1, z1)).addWater(w, x1, y1, z1);
			}

		}

	}

	public void drawWater(World w, int x, int y, int z){

		w.setBlockMetadataWithNotify(x, y, z, 0, 4);
		w.markBlockForUpdate(x, y, z);

		for(int i = 2;i<6;i++){

			int x1 = x + ForgeDirection.getOrientation(i).offsetX;
			int y1 = y + ForgeDirection.getOrientation(i).offsetY;
			int z1 = z + ForgeDirection.getOrientation(i).offsetZ;

			if(w.getBlock(x1, y1, z1)==this && w.getBlockMetadata(x1, y1, z1) == 1){
				((BlockHole)w.getBlock(x1, y1, z1)).drawWater(w, x1, y1, z1);
			}

		}

	}

	//当たり判定
	public void addCollisionBoxesToList(World p_149743_1_, int p_149743_2_, int p_149743_3_, int p_149743_4_, AxisAlignedBB p_149743_5_, List p_149743_6_, Entity p_149743_7_)
    {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.625f, 1.0F);
        super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
        float f = 0.125F;

        if(!this.isSame(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, ForgeDirection.getOrientation(4))){
        	this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
            super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
        }

        if(!this.isSame(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, ForgeDirection.getOrientation(2))){
        	this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
            super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
        }

        if(!this.isSame(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, ForgeDirection.getOrientation(5))){
        	this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
        }

        if(!this.isSame(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, ForgeDirection.getOrientation(3))){
        	this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
            super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
        }

        this.setBlockBoundsForItemRender();

    }

	private boolean isSame(World p_149743_1_, int p_149743_2_, int p_149743_3_, int p_149743_4_ ,ForgeDirection d){

		if(p_149743_1_.getBlock(p_149743_2_ + d.offsetX, p_149743_3_ + d.offsetY, p_149743_4_ + d.offsetZ) == this)return true;

		return false;
	}

	//ブロックの線
	public void setBlockBoundsForItemRender()
    {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

	public boolean isOpaqueCube()
    {
        return false;
    }

	public boolean renderAsNormalBlock()
    {
        return false;
    }

	public int getRenderType()
    {
        return SextiarySector.proxy.holeType;
    }

}
