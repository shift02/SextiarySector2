package shift.sextiarysector.item;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.renderer.model.ModelShiftHat;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemShiftHat extends ItemArmor{

	public ItemShiftHat() {
		super(ArmorMaterial.DIAMOND, 0, 0);
		this.setCreativeTab(SextiarySectorAPI.TabSSCore);
	}

	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
        return "sextiarysector:textures/models/shift_hat.png";
    }

	public static ModelShiftHat model = new ModelShiftHat();

	@SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
    {
        return model;
    }

}
