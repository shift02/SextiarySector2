package shift.sextiarysector.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import shift.sextiarysector.SSPlayerTabs;
import shift.sextiarysector.container.ContainerRucksack;
import shift.sextiarysector.container.InventoryPlayerNext;
import shift.sextiarysector.gui.tab.TabManager;

public class GuiRucksack extends GuiContainer {
    private static final ResourceLocation texture = new ResourceLocation("sextiarysector:textures/guis/rucksack.png");
    private boolean tab = false;

    public GuiRucksack(InventoryPlayer inventoryPlayer) {
        super(new ContainerRucksack(inventoryPlayer));
        //this.ySize = 222;
    }

    public GuiRucksack(InventoryPlayer inventoryPlayer, InventoryPlayerNext inventoryPlayerN) {
        super(new ContainerRucksack(inventoryPlayer, inventoryPlayerN));
        tab = true;
        //this.ySize = 222;
    }

    @Override
    public void initGui() {

        this.buttonList.clear();

        super.initGui();

        if (!tab) return;

        int cornerX = this.guiLeft;

        int cornerY = this.guiTop;

        TabManager.updateTabValues(cornerX, cornerY, this.buttonList, SSPlayerTabs.rucksack, false);

    }

    /*
        ChestとかInventoryとか文字を描画する
     */
    @Override
    protected void drawGuiContainerForegroundLayer(int x, int p_146979_2_) {
        //描画する文字, X, Y, 色
        this.fontRendererObj.drawString(
                I18n.format("gui.ss.rucksack"),
                this.xSize / 2 - this.fontRendererObj.getStringWidth(I18n.format("gui.ss.rucksack")) / 2, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    /*
        背景の描画
     */
    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(texture);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }
}
