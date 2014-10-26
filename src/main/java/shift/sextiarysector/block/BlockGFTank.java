package shift.sextiarysector.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.api.machine.energy.IEnergyHandler;
import shift.sextiarysector.tileentity.TileEntityDirection;
import shift.sextiarysector.tileentity.TileEntityGFTank;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGFTank  extends BlockDirection{

	@SideOnly(Side.CLIENT)
    protected IIcon in;
    protected IIcon out;

    private int guiID;

    private int type;

	public BlockGFTank(Material p_i45386_1_, int gui,int type) {
		super(p_i45386_1_);
		this.guiID = gui;
		this.type = type;
		this.setHardness(0.8F);
		this.setCreativeTab(SextiarySectorAPI.TabSSMachine);
	}

	@Override
	public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6,float par7, float par8, float par9) {

		boolean f = super.onBlockActivated(par1World, x, y, z, par5EntityPlayer, par6, par7, par8, par9);

		if(f)return true;

		if (par1World.isRemote)
		{
			return true;
		}
		else
		{
			par5EntityPlayer.openGui(SextiarySector.instance, this.guiID, par1World, x, y, z);

			return true;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)

    public IIcon getIcon(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {

		int di = ((TileEntityDirection)par1IBlockAccess.getTileEntity(par2, par3, par4)).direction.ordinal();

		if(di==par5)return out;

		ForgeDirection d = ForgeDirection.getOrientation(par5);

		TileEntity t = par1IBlockAccess.getTileEntity(par2 + d.offsetX, par3 + d.offsetY, par4 + d.offsetZ);

		if(t instanceof IEnergyHandler && ((IEnergyHandler) t).canInterface(d.getOpposite()))return in;

		return this.blockIcon;
    }

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int par1, int par2)
	{

		if (par1 == 3)
		{
			return this.out;
		}else
		{
			return this.blockIcon;
		}

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon(this.getTextureName());
		this.in = par1IconRegister.registerIcon(this.getTextureName()+"_in");
		this.out = par1IconRegister.registerIcon(this.getTextureName()+"_out");
	}

	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4)
	{
		super.onBlockAdded(par1World, par2, par3, par4);
		this.setDefaultDirection(par1World, par2, par3, par4);
	}

	private void setDefaultDirection(World par1World, int par2, int par3, int par4)
	{
		if (!par1World.isRemote)
		{
			Block block = par1World.getBlock(par2, par3, par4 - 1);
            Block block1 = par1World.getBlock(par2, par3, par4 + 1);
            Block block2 = par1World.getBlock(par2 - 1, par3, par4);
            Block block3 = par1World.getBlock(par2 + 1, par3, par4);

            byte b0 = 3;

            if (block.func_149730_j() && !block1.func_149730_j())
            {
                b0 = 3;
            }

            if (block1.func_149730_j() && !block.func_149730_j())
            {
                b0 = 2;
            }

            if (block2.func_149730_j() && !block3.func_149730_j())
            {
                b0 = 5;
            }

            if (block3.func_149730_j() && !block2.func_149730_j())
            {
                b0 = 4;
            }

			((TileEntityDirection) par1World.getTileEntity(par2, par3, par4)).direction = ForgeDirection.getOrientation(b0);

			//par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 2);
		}
	}

	public void onBlockPlacedBy(World p_149689_1_, int par2, int par3, int par4, EntityLivingBase p_149689_5_, ItemStack p_149689_6_)
    {
        int l = BlockPistonBase.determineOrientation(p_149689_1_, par2, par3, par4, p_149689_5_);
        p_149689_1_.setBlockMetadataWithNotify(par2, par3, par4, l, 2);
        ((TileEntityDirection) p_149689_1_.getTileEntity(par2, par3, par4)).direction = ForgeDirection.getOrientation(l);

        p_149689_1_.markBlockForUpdate(par2, par3, par4);

    }

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityGFTank(this.type);
	}

}
