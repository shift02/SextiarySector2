package shift.sextiarysector.renderer.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import shift.sextiarysector.api.machine.item.GearForceItem;
import shift.sextiarysector.api.machine.item.IGearForceItem;
import shift.sextiarysector.event.ClientEventHandler;
import cpw.mods.fml.client.FMLClientHandler;

public class RenderGF implements IItemRenderer {

	private final RenderItem renderItem = new RenderItem();

	public static final ResourceLocation icons = new ResourceLocation("sextiarysector:textures/items/damage/damage.png");

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return type == ItemRenderType.INVENTORY;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

		if(item.getItem() instanceof IGearForceItem && type == ItemRenderType.INVENTORY)
		{

			int k = (int)Math.round(GearForceItem.manager.getSpeed(item) * 13.0D / ((IGearForceItem)item.getItem()).getMaxSpeedStored(item));

			this.renderItem.renderIcon(0, 0, item.getItem().getIcon(item, 0), 16, 16);

			int y = 0;

			if (item.isItemDamaged())
            {
				y-=1;
            }

			if(k==0)return;

			//this.bind(icons);

			//this.drawTexturedModalRect(2, 13, 0, 2, 14, 2);
			this.renderItem.renderIcon(0, y, ClientEventHandler.itemGF[0], 16, 16);

			//this.renderItem.renderIcon(0, y, ((ItemGearForce) item.getItem()).getIcon2(item, 1), 16, 16);

			GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            //GL11.glDisable(GL11.GL_TEXTURE_2D);

			int num = (int)Minecraft.getSystemTime() / 100 % 26;
			//System.out.println("renderItem :"+num);

			y += 13;

			this.bind(icons);

			this.drawTexturedModalRect(2, y, num, 0, k, 1);

			//this.renderItem.renderIcon(0, y, ClientEventHandler.itemGF[1], 16, 16);


			//GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

			this.bind(TextureMap.locationItemsTexture);

		}
	}

	private void bind(ResourceLocation res)
    {
        FMLClientHandler.instance().getClient().getTextureManager().bindTexture(res);
    }

    public void drawTexturedModalRect(int par1, int par2, int par3, int par4, int par5, int par6)
    {
        float zLevel = -90.0F;

        float f = 0.00390625F;
        float f1 = 0.00390625F;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV((par1 + 0), (par2 + par6), zLevel, ((par3 + 0) * f), ((par4 + par6) * f1));
        tessellator.addVertexWithUV((par1 + par5), (par2 + par6), zLevel, ((par3 + par5) * f), ((par4 + par6) * f1));
        tessellator.addVertexWithUV((par1 + par5), (par2 + 0), zLevel, ((par3 + par5) * f), ((par4 + 0) * f1));
        tessellator.addVertexWithUV((par1 + 0), (par2 + 0), zLevel, ((par3 + 0) * f), ((par4 + 0) * f1));
        tessellator.draw();
    }

}
