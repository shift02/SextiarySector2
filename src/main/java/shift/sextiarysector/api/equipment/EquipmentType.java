package shift.sextiarysector.api.equipment;

import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;

public enum EquipmentType {

	Necklace("necklace", new int[] { 0 }),
	Ring("ring", new int[] { 1, 2, 3 }),
	Face("face", new int[] { 4 }),
	Bag("bag", new int[] { 5 }),
	Hand("hand", new int[] { 6 }),
	Belt("belt", new int[] { 7 }),
	Unit("unit", new int[] { 8, 9, 10, 11, 12, 13, 14, 15 }),
	Other("other", new int[] { 16, 17, 18, 19 });

	private IIcon icon;
	private String iconName;
	private int[] slot;

	EquipmentType(String name, int[] slot) {
		this.iconName = name;
		this.slot = slot;
	}

	public void registerIcon(TextureMap map) {
		this.setIcon(map.registerIcon("sextiarysector:gui/slot_" + this.iconName));
	}

	public IIcon getIcon() {
		return icon;
	}

	public void setIcon(IIcon icon) {
		this.icon = icon;
	}

	public int[] getSlots() {
		return this.slot;
	}

	public int getSlot(int id) {
		return this.slot[id];
	}

	public static EquipmentType getEquipmentTypeFromSlot(int slot) {

		switch (slot) {
		case 0:
			return Necklace;

		case 1:
		case 2:
		case 3:
			return Ring;

		case 4:
			return Face;
		case 5:
			return Bag;
		case 6:
			return Hand;
		case 7:
			return Belt;

		case 8:
		case 9:
		case 10:
		case 11:
		case 12:
		case 13:
		case 14:
		case 15:
			return Unit;
		}
		return Other;
	}

}
