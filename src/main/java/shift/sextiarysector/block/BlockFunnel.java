package shift.sextiarysector.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.tileentity.TileEntityFunnel;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFunnel extends BlockContainer {

	private IIcon field_149923_M;
	private IIcon field_149921_b;
	private IIcon field_149924_N;

	public BlockFunnel() {
		super(Material.iron);
		this.setHardness(0.7f);
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
			TileEntityFunnel tileentityfurnace = (TileEntityFunnel) par1World.getTileEntity(par2, par3, par4);

			if (tileentityfurnace != null)
			{
				par5EntityPlayer.openGui(SextiarySector.instance, 11, par1World, par2, par3, par4);
			}

			return true;
		}
	}

	@Override
	public int getRenderType()
	{
		return SextiarySector.proxy.funnelType;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int p_149691_1_, int p_149691_2_)
	{
		return p_149691_1_ == 1 ? this.field_149923_M : this.field_149921_b;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister p_149651_1_)
	{
		this.blockIcon = p_149651_1_.registerIcon(this.textureName + "_outside");
		this.field_149921_b = p_149651_1_.registerIcon(this.textureName + "_outside");
		this.field_149923_M = p_149651_1_.registerIcon(this.textureName + "_top");
		this.field_149924_N = p_149651_1_.registerIcon(this.textureName + "_inside");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getItemIconName()
	{
		return this.textureName;
	}

	@SideOnly(Side.CLIENT)
	public IIcon getFunnelIcon(String p_149916_0_)
	{
		return p_149916_0_.equals("funnel_outside") ? this.field_149921_b : (p_149916_0_.equals("funnel_inside") ? this.field_149924_N : null);
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityFunnel();
	}

}
