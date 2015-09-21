package shift.sextiarysector.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import shift.sextiarysector.api.SextiarySectorAPI;

public class ItemSSArmor extends ItemArmor {

    private static String modelRoot = "sextiarysector:textures/models/armor/";

    public ItemSSArmor(ArmorMaterial p_i45325_1_, int p_i45325_2_, int p_i45325_3_) {
        super(p_i45325_1_, p_i45325_2_, p_i45325_3_);
        this.setCreativeTab(SextiarySectorAPI.TabSSPlayer);

    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {

        String s1 = String.format(modelRoot + "%s_layer_%d%s.png", this.getArmorMaterial().name(), (slot == 2 ? 2 : 1), type == null ? "" : String.format("_%s", type));

        return s1;
    }
}
