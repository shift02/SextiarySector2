package shift.sextiarysector.block;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.item.ItemBlockBottle;
import shift.sextiarysector.tileentity.TileEntityBlockBottle;

public class BlockBottle extends BlockContainer{

	public BlockBottle() {
		super(Material.glass);
		this.setHardness(0.4F);
		this.setStepSound(soundTypeGlass);
		this.setBlockBounds(2.0f/16.0f, 0, 2.0f/16.0f, 14.0f/16.0f, 1, 14.0f/16.0f);
		this.setCreativeTab(SextiarySectorAPI.TabSSCore);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		TileEntityBlockBottle tileEntity = (TileEntityBlockBottle)world.getTileEntity(x, y, z);

		if(par5EntityPlayer.isSneaking()){

			if(!world.isRemote){

				ItemStack stack;

				if(tileEntity.hasFluid()){
					stack = new ItemStack(this,1,tileEntity.getFluidID());
					((ItemBlockBottle)stack.getItem()).fill(stack, tileEntity.getFluidStack(), true);
				}else{
					stack = new ItemStack(this,1);
				}

				EntityItem item = new EntityItem(world, x+0.5d, y+0.5d, z+0.5d, stack);

				world.spawnEntityInWorld(item);

				world.setBlockToAir(x, y, z);
				world.removeTileEntity(x, y, z);

			}else{
				return true;
			}

		}

		return false;

	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	public int quantityDropped(Random p_149745_1_)
    {
        return 0;
    }

	@Override
	public int getRenderType()
    {
    	return SextiarySector.proxy.bottleType;
    }

	@Override
	public TileEntity createNewTileEntity(World world, int p_149915_2_) {
		return new TileEntityBlockBottle();
	}

}
