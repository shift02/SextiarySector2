package shift.sextiarysector.block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.tileentity.TileEntitySteamMotor;

public class BlockSteamMotor extends BlockMotor{

	@Override
	public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
		if(super.onBlockActivated(par1World, x, y, z, par5EntityPlayer, par6, par7, par8, par9)){
			return true;
		}

        if (par1World.isRemote)
        {
            return true;
        }
        else
        {
        	TileEntitySteamMotor tileentityfurnace = (TileEntitySteamMotor)par1World.getTileEntity(x, y, z);
            if (tileentityfurnace != null)
            {
                par5EntityPlayer.openGui(SextiarySector.instance, 60, par1World, x, y, z);
            }

            return true;
        }
    }

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntitySteamMotor();
	}

	@Override
	public int getRenderType()
    {
    	return SextiarySector.proxy.steamMotorType;
    }
}
