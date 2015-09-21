package shift.sextiarysector.api.equipment;

import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;

public enum EquipmentType {

    DecorationHelmet("decoration_helmet", new int[] { 0 }), DecorationPlate("decoration_plate", new int[] { 1 }), DecorationLegs("decoration_legs", new int[] { 2 }), DecorationBoots("decoration_boots", new int[] { 3 }),
    //Necklace("necklace", new int[] { 4 }),
    //Ring("ring", new int[] { 5, 6, 7 }),
    Face("face", new int[] { 4 }), Bag("bag", new int[] { 5 }), Hand("hand", new int[] { 6 }), Belt("belt", new int[] { 7 }), Unit("unit", new int[] { 8, 9, 10, 11, 12, 13, 14, 15 }), Other("other",
            new int[] { 16, 17, 18, 19 });

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
                return DecorationHelmet;

            case 1:
                return DecorationPlate;

            case 2:
                return DecorationLegs;

            case 3:
                return DecorationBoots;

            //case 4:
            //	return Necklace;

            //case 5:
            //case 6:
            //case 7:
            //	return Ring;

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
                //case 16:
                //case 17:
                //case 18:
                //case 19:
                return Unit;
            case 16:
            case 17:
            case 18:
            case 19:
                return Unit;
        }
        return Unit;
    }

}
