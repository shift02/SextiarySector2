package shift.sextiarysector.api.agriculture;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class SimpleFertilizer implements IFertilizer {

    private String name;

    private ItemStack fertilizer;
    private ItemStack before;
    private ItemStack after;

    private String iconName;
    private IIcon icon;

    public SimpleFertilizer(String name, String iconName, ItemStack fertilizer, ItemStack before, ItemStack after) {
        this.name = name;

        this.fertilizer = fertilizer;
        this.before = before;
        this.after = after;

        this.iconName = iconName;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ItemStack getFertilizer() {
        return this.fertilizer;
    }

    @Override
    public ItemStack getBefore() {
        return this.before;
    }

    @Override
    public ItemStack getAfter() {
        return this.after;
    }

    @Override
    public int getProbability() {
        return 255;
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
