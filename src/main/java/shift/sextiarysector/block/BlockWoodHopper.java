package shift.sextiarysector.block;

import net.minecraft.block.BlockHopper;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.api.SextiarySectorAPI;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockWoodHopper extends BlockHopper {

	private IIcon field_149923_M;
	private IIcon field_149921_b;
	private IIcon field_149924_N;

	public BlockWoodHopper() {
		this.setHardness(0.5f);
		this.setStepSound(soundTypeWood);
		this.setCreativeTab(SextiarySectorAPI.TabSSCore);
	}

	@Override
	public int getRenderType()
	{
		return SextiarySector.proxy.woodHopperType;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getItemIconName()
	{
		return "sextiarysector:wood_hopper";
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
		this.field_149921_b = p_149651_1_.registerIcon("sextiarysector:wood_hopper_outside");
		this.field_149923_M = p_149651_1_.registerIcon("sextiarysector:wood_hopper_top");
		this.field_149924_N = p_149651_1_.registerIcon("sextiarysector:wood_hopper_inside");
	}

	@SideOnly(Side.CLIENT)
	public IIcon getHopperIcon2(String p_149916_0_)
	{
		return p_149916_0_.equals("hopper_outside") ? this.field_149921_b : (p_149916_0_.equals("hopper_inside") ? this.field_149924_N : null);
	}

}
