package shift.sextiarysector.api.agriculture;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class FertilizerBase implements IFertilizer {

    public String name;
    public ItemStack fertilizer;

    public String iconName;
    public IIcon icon;

    public FertilizerBase(String name, ItemStack fertilizer) {
        this.name = name;
        this.fertilizer = fertilizer;
    }

    public FertilizerBase setIconName(String name) {
        this.iconName = name;
        return this;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public ItemStack getFertilizer() {
        return this.fertilizer;
    }

    @Override
    public void registerFertilizerIcons(IIconRegister iconRegister) {

        if (iconName == null) return;

        this.icon = iconRegister.registerIcon(iconName);

    }

    @Override
    public IIcon getFertilizerIcon() {
        return this.icon;
    }

}
