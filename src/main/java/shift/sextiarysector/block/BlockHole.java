package shift.sextiarysector.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.SextiarySector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockHole extends Block{

	public BlockHole() {
		super(Material.grass);
		this.setLightOpacity(255);
	}

	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
		return Blocks.dirt.getIcon(p_149691_1_, p_149691_2_);
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
