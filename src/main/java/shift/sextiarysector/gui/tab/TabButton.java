package shift.sextiarysector.gui.tab;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.I18n;
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


            GL11.glEnable(GL11.GL_DEPTH_TEST);

            float z = this.zLevel;
            this.zLevel = 100.0F;
            this.itemRenderer.zLevel = 100.0F;
            mc.renderEngine.bindTexture(this.texture);
            this.drawTexturedModalRect(this.xPosition, yPos, xOffset * 28, yTexPos, 28, ySize);
            this.itemRenderer.zLevel = 0.0F;
            this.zLevel = 0.0F;

            RenderHelper.enableGUIStandardItemLighting();
            this.zLevel = 200.0F;
            this.itemRenderer.zLevel = 200.0F;
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            this.itemRenderer.renderItemAndEffectIntoGUI(mc.fontRenderer, mc.renderEngine, tab.getItemStack(), xPosition + 6, yPosition + 8);
            this.itemRenderer.renderItemOverlayIntoGUI(mc.fontRenderer, mc.renderEngine, tab.getItemStack(), xPosition + 6, yPosition + 8);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_BLEND);
            this.itemRenderer.zLevel = 0.0F;
            this.zLevel = 0.0F;
            RenderHelper.disableStandardItemLighting();

            this.field_146123_n = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            //int k = this.getHoverState(this.field_146123_n);

            if(this.field_146123_n){
            	//this.drawCenteredString(mc.fontRenderer, I18n.format(this.tab.getTabName()), mouseX, mouseY, 0xffffff);
            	List name = new ArrayList<String>();
            	name.add(I18n.format(this.tab.getTabName()));
            	this.drawHoveringText(name, mouseX, mouseY, mc.fontRenderer, mc.currentScreen);
            	//((GuiContainer)mc.currentScreen).drawHoveringText(name, mouseX + 20, mouseY + 5, mc.fontRenderer);
            }

            GL11.glDisable(GL11.GL_DEPTH_TEST);

        }
    }

	protected void drawHoveringText(List p_146283_1_, int p_146283_2_, int p_146283_3_, FontRenderer font , GuiScreen gui)
    {
        if (!p_146283_1_.isEmpty())
        {
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            //RenderHelper.disableStandardItemLighting();
            //GL11.glDisable(GL11.GL_LIGHTING);
            //GL11.glDisable(GL11.GL_DEPTH_TEST);
            int k = 0;
            Iterator iterator = p_146283_1_.iterator();

            while (iterator.hasNext())
            {
                String s = (String)iterator.next();
                int l = font.getStringWidth(s);

                if (l > k)
                {
                    k = l;
                }
            }

            int j2 = p_146283_2_ + 12;
            int k2 = p_146283_3_ - 12;
            int i1 = 8;

            if (p_146283_1_.size() > 1)
            {
                i1 += 2 + (p_146283_1_.size() - 1) * 10;
            }

            if (j2 + k > gui.width)
            {
                j2 -= 28 + k;
            }

            if (k2 + i1 + 6 > this.height + 25)
            {
                k2 = this.height + 25 - i1 - 6;
            }

            this.zLevel = 300.0F;
            itemRenderer.zLevel = 300.0F;
            int j1 = -267386864;
            this.drawGradientRect(j2 - 3, k2 - 4, j2 + k + 3, k2 - 3, j1, j1);
            this.drawGradientRect(j2 - 3, k2 + i1 + 3, j2 + k + 3, k2 + i1 + 4, j1, j1);
            this.drawGradientRect(j2 - 3, k2 - 3, j2 + k + 3, k2 + i1 + 3, j1, j1);
            this.drawGradientRect(j2 - 4, k2 - 3, j2 - 3, k2 + i1 + 3, j1, j1);
            this.drawGradientRect(j2 + k + 3, k2 - 3, j2 + k + 4, k2 + i1 + 3, j1, j1);
            int k1 = 1347420415;
            int l1 = (k1 & 16711422) >> 1 | k1 & -16777216;
            this.drawGradientRect(j2 - 3, k2 - 3 + 1, j2 - 3 + 1, k2 + i1 + 3 - 1, k1, l1);
            this.drawGradientRect(j2 + k + 2, k2 - 3 + 1, j2 + k + 3, k2 + i1 + 3 - 1, k1, l1);
            this.drawGradientRect(j2 - 3, k2 - 3, j2 + k + 3, k2 - 3 + 1, k1, k1);
            this.drawGradientRect(j2 - 3, k2 + i1 + 2, j2 + k + 3, k2 + i1 + 3, l1, l1);

            GL11.glDisable(GL11.GL_DEPTH_TEST);
            for (int i2 = 0; i2 < p_146283_1_.size(); ++i2)
            {
                String s1 = (String)p_146283_1_.get(i2);
                font.drawStringWithShadow(s1, j2, k2, -1);

                if (i2 == 0)
                {
                    k2 += 2;
                }

                k2 += 10;
            }
            GL11.glEnable(GL11.GL_DEPTH_TEST);

            this.zLevel = 0.0F;
            itemRenderer.zLevel = 0.0F;
            //GL11.glEnable(GL11.GL_LIGHTING);
            //GL11.glEnable(GL11.GL_DEPTH_TEST);
            //RenderHelper.enableStandardItemLighting();
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        }
    }

    @Override
    public boolean mousePressed (Minecraft mc, int mouseX, int mouseY)
    {
        boolean inWindow = this.enabled && this.visible && mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;

        if (inWindow)
        {
        	//mc.thePlayer.openContainer = null;
            this.tab.onTabClicked();
        }

        return inWindow;
    }

    public boolean shouldAddToList () {
		return true;
	}

}
