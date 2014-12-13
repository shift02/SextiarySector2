package shift.sextiarysector.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.api.machine.item.IHammer;
import shift.sextiarysector.tileentity.TileEntityFluidMachineBase;
import shift.sextiarysector.tileentity.TileEntityFoodSmokers;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFoodSmokers extends BlockFluidMachineBase{

	@SideOnly(Side.CLIENT)
    private IIcon furnaceIconTop;
    @SideOnly(Side.CLIENT)
    private IIcon[] furnaceIconFront = new IIcon[2];
    @SideOnly(Side.CLIENT)
	private IIcon furnaceIconTopOn;

    public BlockFoodSmokers() {
		super(Material.iron);
	}

    @Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        if (par1World.isRemote)
        {
            return true;
        }
        else
        {
        	TileEntityFluidMachineBase tileentityfurnace = (TileEntityFluidMachineBase)par1World.getTileEntity(par2, par3, par4);

        	if(par5EntityPlayer.getCurrentEquippedItem() != null){
        		if(par5EntityPlayer.getCurrentEquippedItem().getItem() instanceof IHammer && ((IHammer)par5EntityPlayer.getCurrentEquippedItem().getItem()).canUse(par5EntityPlayer.getCurrentEquippedItem())){

        			tileentityfurnace.on = !tileentityfurnace.on;
        			((IHammer)par5EntityPlayer.getCurrentEquippedItem().getItem()).use(par5EntityPlayer.getCurrentEquippedItem());
        			par1World.markBlockForUpdate(par2, par3, par4);
        			return true;

        		}
        	}



            if (tileentityfurnace != null)
            {
                par5EntityPlayer.openGui(SextiarySector.instance, 2, par1World, par2, par3, par4);
            }

            return true;
        }
    }

    @Override
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2)
    {

		return par1 == 1 ? this.furnaceIconTop : (par1 == 0 ? this.furnaceIconTop : (par1 != 3 ? this.blockIcon : this.furnaceIconFront[1]));

    }

	@SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess p_149673_1_, int x, int y, int z, int side)
    {

		TileEntityFoodSmokers tileEntity = (TileEntityFoodSmokers)p_149673_1_.getTileEntity(x, y, z);

    	int meta = tileEntity.getDirection().ordinal();//p_149673_1_.getBlockMetadata(x, y, z);

		return side == 1 ? (tileEntity.on ? this.furnaceIconTopOn : this.furnaceIconTop) : (side == 0 ? this.furnaceIconTop : (side != meta ? this.blockIcon : (tileEntity.isFuel() ? this.furnaceIconFront[0] : this.furnaceIconFront[1])));

    }

	@Override
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("sextiarysector:food_smokers_side");
        this.furnaceIconFront[0] = par1IconRegister.registerIcon("sextiarysector:food_smokers_front_on");
        this.furnaceIconFront[1] = par1IconRegister.registerIcon("sextiarysector:food_smokers_front_off");
        this.furnaceIconTop = par1IconRegister.registerIcon("sextiarysector:food_smokers_top");
        this.furnaceIconTopOn = par1IconRegister.registerIcon("sextiarysector:food_smokers_top_on");
    }

	@Override
	public TileEntity createNewTileEntity(World par1World, int p_149915_2_)
    {
        return new TileEntityFoodSmokers();
    }

}
