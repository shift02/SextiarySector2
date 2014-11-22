package shift.sextiarysector.player;

import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;

public enum EquipmentType {

	Necklace("necklace"),
	Ring("ring"),
	Face("face"),
	Hand("hand"),
	Bag("bag"),
	Belt("belt"),
	Unit("unit"),
	Other("other")
	;

	private IIcon icon;
	private String iconName;

	EquipmentType(String name){
		this.iconName = name;
	}

	public void registerIcon(TextureMap map){
		this.setIcon(map.registerIcon("sextiarysector:gui/slot_"+this.iconName));
	}

	public IIcon getIcon() {
		return icon;
	}

	public void setIcon(IIcon icon) {
		this.icon = icon;
	}

}
