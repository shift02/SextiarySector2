package shift.sextiarysector.gui.tab;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class TabButton  extends GuiButton{

	ResourceLocation texture = new ResourceLocation("textures/gui/container/creative_inventory/tabs.png");
	private AbstractTab tab;
    RenderItem itemRenderer = new RenderItem();

    public TabButton(AbstractTab tab)
    {
        super(0,0,0, 28, 32, "");
        this.tab = tab;
    }


	@Override
    public void drawButton (Minecraft mc, int mouseX, int mouseY)
    {
        if (this.visible)
        {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

            int yTexPos = this.enabled ? 3 : 32;
            int ySize = this.enabled ? 25 : 32;
            int xOffset = this.id == 2 ? 0 : (this.id == 7 ? 5 : 1 );
            int yPos = this.yPosition + (this.enabled ? 3 : 0);

            mc.renderEngine.bindTexture(this.texture);
            this.drawTexturedModalRect(this.xPosition, yPos, xOffset * 28, yTexPos, 28, ySize);

            RenderHelper.enableGUIStandardItemLighting();
            this.zLevel = 100.0F;
            this.itemRenderer.zLevel = 100.0F;
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            this.itemRenderer.renderItemAndEffectIntoGUI(mc.fontRenderer, mc.renderEngine, tab.getItemStack(), xPosition + 6, yPosition + 8);
            this.itemRenderer.renderItemOverlayIntoGUI(mc.fontRenderer, mc.renderEngine, tab.getItemStack(), xPosition + 6, yPosition + 8);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_BLEND);
            this.itemRenderer.zLevel = 0.0F;
            this.zLevel = 0.0F;
            RenderHelper.disableStandardItemLighting();
        }
    }

    @Override
    public boolean mousePressed (Minecraft mc, int mouseX, int mouseY)
    {
        boolean inWindow = this.enabled && this.visible && mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;

        if (inWindow)
        {
            this.tab.onTabClicked();
        }

        return inWindow;
    }

    public boolean shouldAddToList () {
		return true;
	}

}
