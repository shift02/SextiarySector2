package shift.sextiarysector.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.module.FertilizerManager;
import shift.sextiarysector.tileentity.TileEntityFarmland;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSSFarmland extends BlockFarmland  implements ITileEntityProvider{

	public BlockSSFarmland()
    {
        super();
        this.setTickRandomly(false);
        this.setHardness(0.6F);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, (1.0f/16.0f)*14 , 1.0F);
        this.setStepSound(soundTypeGravel);
        this.setLightOpacity(255);
        this.useNeighborBrightness = true;
    }

	@Override
	public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6,float par7, float par8, float par9) {

		if(FertilizerManager.getFertilizer(par5EntityPlayer.getCurrentEquippedItem())!=null){

			TileEntityFarmland t = (TileEntityFarmland) par1World.getTileEntity(x, y, z);

			if(t.getFertilizer()!=null){
				return false;
			}

			t.setFertilizer(FertilizerManager.getFertilizer(par5EntityPlayer.getCurrentEquippedItem()).getName());

			if (!par5EntityPlayer.capabilities.isCreativeMode)
	        {
	            --par5EntityPlayer.getCurrentEquippedItem().stackSize;
	        }

			par1World.markBlockForUpdate(x, y, z);

			return true;

		}

		return false;
	}

	public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_)
    {
        super.breakBlock(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);
        p_149749_1_.removeTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);
    }

    public boolean onBlockEventReceived(World p_149696_1_, int p_149696_2_, int p_149696_3_, int p_149696_4_, int p_149696_5_, int p_149696_6_)
    {
        super.onBlockEventReceived(p_149696_1_, p_149696_2_, p_149696_3_, p_149696_4_, p_149696_5_, p_149696_6_);
        TileEntity tileentity = p_149696_1_.getTileEntity(p_149696_2_, p_149696_3_, p_149696_4_);
        return tileentity != null ? tileentity.receiveClientEvent(p_149696_5_, p_149696_6_) : false;
    }

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityFarmland();
	}

	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
		return Blocks.farmland.getIcon(p_149691_1_, p_149691_2_);
    }

	public int getRenderType()
    {
        return SextiarySector.proxy.farmlandType;
    }

}
