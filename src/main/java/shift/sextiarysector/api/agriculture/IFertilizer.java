package shift.sextiarysector.api.agriculture;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public interface IFertilizer {

    public String getName();

    public ItemStack getFertilizer();

    @SideOnly(Side.CLIENT)
    public void registerFertilizerIcons(IIconRegister iconRegister);

    @SideOnly(Side.CLIENT)
    public IIcon getFertilizerIcon();

}
