package shift.sextiarysector.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.SSItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPowerStone extends Block{

	public BlockPowerStone() {
		super(Material.rock);
		this.setTickRandomly(true);
		this.setResistance(5.0F);
		this.setStepSound(Block.soundTypeStone);
		this.setHardness(3.0F);
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z)
    {
		if(this.isGlowing(world, x, y, z)){
			return (int)(15.0F * 0.725F);
		}
		return 0;

    }

	public boolean isGlowing(IBlockAccess world, int par2, int par3, int par4)
    {
		return world.getBlockMetadata(par2, par3, par4)!=0;
    }

    @Override
	public int tickRate(World par1World)
    {
        return 30;
    }

    @Override
	public void onBlockClicked(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer)
    {
        this.glow(par1World, par2, par3, par4);
        super.onBlockClicked(par1World, par2, par3, par4, par5EntityPlayer);
    }

    @Override
	public void onEntityWalking(World par1World, int par2, int par3, int par4, Entity par5Entity)
    {
        this.glow(par1World, par2, par3, par4);
        super.onEntityWalking(par1World, par2, par3, par4, par5Entity);
    }

    @Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        this.glow(par1World, par2, par3, par4);
        return super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
    }

    private void glow(World par1World, int par2, int par3, int par4)
    {
        this.sparkle(par1World, par2, par3, par4);

        if (0 == par1World.getBlockMetadata(par2, par3, par4))
        {
            par1World.setBlock(par2, par3, par4, this,1,1);
        }
    }

    @Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (this.isGlowing(par1World, par2, par3, par4))
        {
            par1World.setBlock(par2, par3, par4, this,0,2);
        }
    }

    @Override
	public Item getItemDropped(int par1, Random par2Random, int par3)
    {
    	if(this==SSBlocks.blueStoneOre){
    		return SSItems.blueStoneDust;
    	}else{
    		return SSItems.yellowStoneDust;
    	}

    }

    @Override
	public int quantityDroppedWithBonus(int par1, Random par2Random)
    {
        return this.quantityDropped(par2Random) + par2Random.nextInt(par1 + 1);
    }

    @Override
	public int quantityDropped(Random par1Random)
    {
        return 4 + par1Random.nextInt(2);
    }

    @Override
	public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
    {
        super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, par7);

    }

    @Override
	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (this.isGlowing(par1World, par2, par3, par4))
        {
            this.sparkle(par1World, par2, par3, par4);
        }
    }

    private void sparkle(World par1World, int par2, int par3, int par4)
    {
        Random random = par1World.rand;
        double d0 = 0.0625D;

        for (int l = 0; l < 6; ++l)
        {
        	double d1 = (double)((float)par2 + random.nextFloat());
            double d2 = (double)((float)par3 + random.nextFloat());
            double d3 = (double)((float)par4 + random.nextFloat());

            if (l == 0 && !par1World.getBlock(par2, par3 + 1, par4).isOpaqueCube())
            {
                d2 = (double)(par3 + 1) + d0;
            }

            if (l == 1 && !par1World.getBlock(par2, par3 - 1, par4).isOpaqueCube())
            {
                d2 = (double)(par3 + 0) - d0;
            }

            if (l == 2 && !par1World.getBlock(par2, par3, par4 + 1).isOpaqueCube())
            {
                d3 = (double)(par4 + 1) + d0;
            }

            if (l == 3 && !par1World.getBlock(par2, par3, par4 - 1).isOpaqueCube())
            {
                d3 = (double)(par4 + 0) - d0;
            }

            if (l == 4 && !par1World.getBlock(par2 + 1, par3, par4).isOpaqueCube())
            {
                d1 = (double)(par2 + 1) + d0;
            }

            if (l == 5 && !par1World.getBlock(par2 - 1, par3, par4).isOpaqueCube())
            {
                d1 = (double)(par2 + 0) - d0;
            }

            if (d1 < (double)par2 || d1 > (double)(par2 + 1) || d2 < 0.0D || d2 > (double)(par3 + 1) || d3 < (double)par4 || d3 > (double)(par4 + 1))
            {
            	if(this==SSBlocks.blueStoneOre){
            		par1World.spawnParticle("reddust", d1, d2, d3, -0.3D, 0.0D, 1.0D);
            	}else{
            		par1World.spawnParticle("reddust", d1, d2, d3, 0.0D, 0.8D, 0.0D);
            	}
            }

        }
    }

}
