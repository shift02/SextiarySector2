package shift.sextiarysector.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import shift.sextiarysector.api.SextiarySectorAPI;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemHoverBoots extends ItemArmor {

    @SideOnly(Side.CLIENT)
    private static net.minecraft.client.model.ModelBiped boots;

    public ItemHoverBoots() {
        super(ArmorMaterial.DIAMOND, 0, 3);
        this.setCreativeTab(SextiarySectorAPI.TabSSPlayer);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return "sextiarysector:textures/models/shift_hat.png";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public net.minecraft.client.model.ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
        if (boots == null) {
            boots = new net.minecraft.client.model.ModelBiped();
        }

        return boots;
    }
}
