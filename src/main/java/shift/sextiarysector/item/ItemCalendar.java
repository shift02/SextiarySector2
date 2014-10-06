package shift.sextiarysector.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCalendar extends Item{

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{

		this.itemIcon = new TextureCalendar(this.getIconString());

		((TextureMap)par1IconRegister).setTextureEntry(this.getIconString(), (TextureAtlasSprite)this.itemIcon);

	}

}
