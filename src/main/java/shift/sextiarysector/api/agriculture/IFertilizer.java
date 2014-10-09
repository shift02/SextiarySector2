package shift.sextiarysector.api.agriculture;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface IFertilizer {

	public String getName();

	@SideOnly(Side.CLIENT)
	public void registerFertilizerIcons(IIconRegister par1IconRegister);

	@SideOnly(Side.CLIENT)
	public IIcon getFertilizerIcon();

}
