package shift.sextiarysector.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.tileentity.TileEntityFluidCrafter;

public class BlockFluidCrafter extends BlockContainer{

	public BlockFluidCrafter() {
		super(Material.glass);
		this.setHardness(0.4F);
		this.setStepSound(soundTypeGlass);
		this.setBlockBounds(4.0f/16.0f, 4.0f/16.0f, 4.0f/16.0f, 12.0f/16.0f, 12.0f/16.0f, 12.0f/16.0f);
		this.setCreativeTab(SextiarySectorAPI.TabSSCore);
	}

	/*
	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
    {
		if(par6ItemStack.getItemDamage()!=0){
			TileEntityFluidCrafter tileEntity = (TileEntityFluidCrafter)par1World.getTileEntity(par2, par3, par4);
			tileEntity.f = new FluidStack(FluidRegistry.getFluid(par6ItemStack.getItemDamage()),1000);
		}

    }*/

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public int getRenderType()
    {
    	return SextiarySector.proxy.fluidCrafterType;
    }

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityFluidCrafter();
	}
}
