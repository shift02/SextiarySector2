package shift.sextiarysector.api.agriculture;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class SimpleFertilizer implements IFertilizer {

	private String name;
	private String iconName;
	private IIcon icon;

	public SimpleFertilizer(String name,String iconName) {
		this.name = name;
		this.iconName = iconName;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void registerFertilizerIcons(IIconRegister par1IconRegister) {
		this.icon = par1IconRegister.registerIcon(iconName);
	}

	@Override
	public IIcon getFertilizerIcon() {
		return icon;
	}

}
