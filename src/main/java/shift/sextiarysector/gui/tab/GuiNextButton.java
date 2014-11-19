package shift.sextiarysector.gui.tab;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class GuiNextButton extends GuiButton{

	private int cornerX;
	private int cornerY;
	private List buttonList;

	public GuiNextButton(int p_i1021_1_, int p_i1021_2_, int p_i1021_3_,int p_i1021_4_, int p_i1021_5_, int x, int y, List buttonList) {
		super(p_i1021_1_, p_i1021_2_, p_i1021_3_, p_i1021_4_, p_i1021_5_, ">");
		this.cornerX = x;
		this.cornerY = y;
		this.buttonList = buttonList;
	}


	@Override
    public boolean mousePressed (Minecraft mc, int mouseX, int mouseY)
    {
        boolean inWindow = this.enabled && this.visible && mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;

        if (inWindow && (TabManager.getSelectPage()<TabManager.getPageSize()))
        {
        	TabManager.setSelectPage(TabManager.getSelectPage()+1);
        	TabManager.updateTabValues(cornerX, cornerY, buttonList, TabManager.getSelectedButton().getClass(),true);
        }

        return inWindow;
    }
}
