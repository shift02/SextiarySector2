package shift.sextiarysector.potion;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PotionSS extends Potion {

	protected static final ResourceLocation field_147001_a = new ResourceLocation("sextiarysector:textures/guis/potion.png");
	private final int iconID;

	public PotionSS(int id, boolean isBadEffect, int liquidColor, int icon) {
		super(id, isBadEffect, liquidColor);
		this.iconID = icon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void renderInventoryEffect(int x, int y, PotionEffect effect, net.minecraft.client.Minecraft mc) {

		mc.getTextureManager().bindTexture(field_147001_a);
		mc.currentScreen.drawTexturedModalRect(x + 6, y + 7, (iconID % 14) * 18, (iconID / 14) * 18, 18, 18);

	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasStatusIcon()
	{
		return false;
	}

}
