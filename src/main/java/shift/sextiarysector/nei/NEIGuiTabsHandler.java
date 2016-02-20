package shift.sextiarysector.nei;

import java.util.List;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import shift.sextiarysector.gui.tab.GuiNextButton;
import shift.sextiarysector.gui.tab.TabManager;
import codechicken.nei.VisiblityData;
import codechicken.nei.api.INEIGuiHandler;
import codechicken.nei.api.TaggedInventoryArea;

public class NEIGuiTabsHandler implements INEIGuiHandler {

    @Override
    public VisiblityData modifyVisiblity(GuiContainer gui, VisiblityData currentVisibility) {
        return currentVisibility;
    }

    @Override
    public Iterable<Integer> getItemSpawnSlots(GuiContainer gui, ItemStack item) {
        return null;
    }

    @Override
    public List<TaggedInventoryArea> getInventoryAreas(GuiContainer gui) {
        return null;
    }

    @Override
    public boolean handleDragNDrop(GuiContainer gui, int mousex, int mousey, ItemStack draggedStack, int button) {
        return false;
    }

    @Override
    public boolean hideItemPanelSlot(GuiContainer gui, int x, int y, int w, int h) {

        if (TabManager.buttons == null) return false;

        List<GuiButton> list = TabManager.buttons.get();

        if (list == null) return false;

        GuiButton next = null;

        for (GuiButton button : list) {
            if (button instanceof GuiNextButton) next = button;
        }

        if (next == null) return false;

        int xw = x + w;
        int yh = y + h;

        if (this.mousePressed(next, x, y)) return true;
        if (this.mousePressed(next, xw, y)) return true;
        if (this.mousePressed(next, x, yh)) return true;
        if (this.mousePressed(next, xw, yh)) return true;

        return false;
    }

    public boolean mousePressed(GuiButton p_146116_1_, int p_146116_2_, int p_146116_3_) {
        return p_146116_1_.enabled && p_146116_1_.visible && p_146116_2_ >= p_146116_1_.xPosition && p_146116_3_ >= p_146116_1_.yPosition && p_146116_2_ < p_146116_1_.xPosition + p_146116_1_.width
                && p_146116_3_ < p_146116_1_.yPosition + p_146116_1_.height;
    }

}
