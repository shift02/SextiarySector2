package shift.sextiarysector.api.potion;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class PotionBase extends Potion {

	protected static final ResourceLocation field_147001_a = new ResourceLocation("sextiarysector:textures/guis/potion.png");

	public PotionBase(int id, boolean isBadEffect, int liquidColor, int icon) {
		super(id, isBadEffect, liquidColor);
		this.setIconIndex(icon % 8, icon / 8);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getStatusIconIndex() {
		Minecraft.getMinecraft().renderEngine.bindTexture(this.getPotionResource());
		return super.getStatusIconIndex();
	}

	abstract protected ResourceLocation getPotionResource();

}
