package shift.sextiarysector.gui.tab;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;

import org.lwjgl.opengl.GL11;

public class GuiBackButton extends GuiButton{

	private int cornerX;
	private int cornerY;
	private List buttonList;

	public GuiBackButton(int p_i1021_1_, int p_i1021_2_, int p_i1021_3_,int p_i1021_4_, int p_i1021_5_, int x, int y, List buttonList) {
		super(p_i1021_1_, p_i1021_2_, p_i1021_3_, p_i1021_4_, p_i1021_5_, "<");
		this.cornerX = x;
		this.cornerY = y;
		this.buttonList = buttonList;
	}


	@Override
    public boolean mousePressed (Minecraft mc, int mouseX, int mouseY)
    {
        boolean inWindow = this.enabled && this.visible && mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;

        if (inWindow && (TabManager.getSelectPage()>1))
        {
        	TabManager.setSelectPage(TabManager.getSelectPage()-1);
        	TabManager.updateTabValues(cornerX, cornerY, buttonList, TabManager.getSelectedButton(),true);
        }

        return inWindow;
    }

	@Override
    public void drawButton (Minecraft mc, int mouseX, int mouseY)
    {
		if (this.visible)
        {
            FontRenderer fontrenderer = mc.fontRenderer;
            mc.renderEngine.bindTexture(buttonTextures);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.field_146123_n = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            int k = this.getHoverState(this.field_146123_n);
            //GL11.glEnable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            //OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            //GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            this.zLevel = 0.0F;
            this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 46 + k * 20, this.width / 2, this.height);
            this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 200 - this.width / 2, 46 + k * 20, this.width / 2, this.height);
            this.mouseDragged(mc, mouseX, mouseY);
            int l = 14737632;

            if (packedFGColour != 0)
            {
                l = packedFGColour;
            }
            else if (!this.enabled)
            {
                l = 10526880;
            }
            else if (this.field_146123_n)
            {
                l = 16777120;
            }

            this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, l);
            this.zLevel = 0.0F;
            GL11.glDisable(GL11.GL_DEPTH_TEST);
        }
    }
}
