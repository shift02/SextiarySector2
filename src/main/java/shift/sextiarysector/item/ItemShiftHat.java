package shift.sextiarysector.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.api.SextiarySectorAPI;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemShiftHat extends ItemArmor {

    public ItemShiftHat() {
        super(ArmorMaterial.DIAMOND, 0, 0);
        this.setCreativeTab(SextiarySectorAPI.TabSSPlayer);
    }

    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return "sextiarysector:textures/models/shift_hat.png";
    }

    @SideOnly(Side.CLIENT)
    public net.minecraft.client.model.ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
        return (net.minecraft.client.model.ModelBiped) SextiarySector.proxy.getShiftHat();
    }

}
