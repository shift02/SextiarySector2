package shift.sextiarysector.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import shift.sextiarysector.container.ContainerCraftFurnace;
import shift.sextiarysector.recipe.FurnaceCraftingManager;
import shift.sextiarysector.tileentity.TileEntityCraftFurnace;

public class GuiCraftFurnace  extends GuiContainer{

	private static final ResourceLocation furnaceGuiTextures = new ResourceLocation("sextiarysector:textures/guis/craft_furnace.png");
    private final TileEntityCraftFurnace furnaceInventory;

    public GuiCraftFurnace(InventoryPlayer par1InventoryPlayer, TileEntityCraftFurnace par2TileEntityFurnace)
    {
        super(new ContainerCraftFurnace(par1InventoryPlayer, par2TileEntityFurnace));
        this.furnaceInventory = par2TileEntityFurnace;
        //this.ySize =222;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    @Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        String s = this.furnaceInventory.hasCustomInventoryName() ? this.furnaceInventory.getInventoryName() : I18n.format(this.furnaceInventory.getInventoryName());
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    @Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(furnaceGuiTextures);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        int i1;

        if (this.furnaceInventory.isFuel())
        {
            i1 = this.furnaceInventory.getEnergyProgressScaled(12);
            this.drawTexturedModalRect(k + 15, l + 29 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
        }

        i1 = this.furnaceInventory.getWorkProgressScaled(24);
        this.drawTexturedModalRect(k + 100 + 1, l + 41, 176, 14, i1 + 1, 16);

        ItemStack itemstack = FurnaceCraftingManager.getInstance().findMatchingRecipe(this.furnaceInventory.craftMatrix, this.furnaceInventory.getWorldObj());

        if (itemstack != null )
        {
            int k1 = (this.width - this.xSize) / 2;
            int l1 = (this.height - this.ySize) / 2;
            GL11.glPushMatrix();
            RenderHelper.enableGUIStandardItemLighting();
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glEnable(GL11.GL_COLOR_MATERIAL);
            GL11.glEnable(GL11.GL_LIGHTING);
            itemRender.zLevel = 100.0F;

            itemRender.renderItemAndEffectIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), itemstack, k1 + 105, l1 + 23);
            itemRender.renderItemOverlayIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), itemstack, k1 + 105, l1 + 23);
            itemRender.zLevel = 0.0F;
            GL11.glDisable(GL11.GL_LIGHTING);

            if(this.func_146978_c(105, 23, 16, 16, par2, par3))
            {
                this.renderToolTip(itemstack, par2, par3);
            }

            GL11.glPopMatrix();
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            RenderHelper.enableStandardItemLighting();
        }

    }
}
