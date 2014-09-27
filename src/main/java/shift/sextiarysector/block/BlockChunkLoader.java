package shift.sextiarysector.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import shift.sextiarysector.module.ModuleChunkLoader;
import shift.sextiarysector.module.ModuleChunkLoader.IChunkLoaderBlock;

public class BlockChunkLoader extends Block implements IChunkLoaderBlock{

	public BlockChunkLoader() {
		super(Material.iron);
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
		if(!par1World.isRemote && par5EntityPlayer.isSneaking()){

			if(par1World.getBlockMetadata(par2, par3, par4)==0){
				par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 2);
				ModuleChunkLoader.setBlockTicket(par1World, par2, par3, par4);
			}else{
				par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 2);
				ModuleChunkLoader.deleteBlockTicket(par1World, par2, par3, par4);
			}

			return true;

		}

		return false;
    }

	public int getLightValue(IBlockAccess world, int x, int y, int z)
    {
		if(world.getBlockMetadata(x, y, z)==1)return 15;
		return 0;
    }

	@Override
	public boolean canLoad(World world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z)==1;
	}

}
