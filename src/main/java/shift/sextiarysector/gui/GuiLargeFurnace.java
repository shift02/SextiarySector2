package shift.sextiarysector.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import shift.sextiarysector.container.ContainerLargeFurnace;
import shift.sextiarysector.recipe.FurnaceCraftingManager;
import shift.sextiarysector.tileentity.TileEntityLargeFurnace;

public class GuiLargeFurnace  extends GuiContainer{

	private static final ResourceLocation furnaceGuiTextures = new ResourceLocation("sextiarysector:textures/guis/crafting_furnace.png");
    private final TileEntityLargeFurnace furnaceInventory;

    public GuiLargeFurnace(InventoryPlayer par1InventoryPlayer, TileEntityLargeFurnace par2TileEntityFurnace)
    {
        super(new ContainerLargeFurnace(par1InventoryPlayer, par2TileEntityFurnace));
        this.furnaceInventory = par2TileEntityFurnace;
        this.ySize =222;
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
        this.fontRendererObj.drawString(I18n.format("container.sub.inventory", new Object[0]), 8, this.ySize - 96 - 48 -2 + 2 -2, 4210752);
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

        if (this.furnaceInventory.isBurning())
        {
            i1 = this.furnaceInventory.getBurnTimeRemainingScaled(12);
            //this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
            this.drawTexturedModalRect(k + 15, l + 29 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
        }

        i1 = this.furnaceInventory.getCookProgressScaled(24);
        //this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
        this.drawTexturedModalRect(k + 100 + 1, l + 41, 176, 14, i1 + 1, 16);



        ItemStack itemstack = FurnaceCraftingManager.getInstance().findMatchingRecipe(this.furnaceInventory.craftMatrix, this.furnaceInventory.getWorldObj());

        if (itemstack != null )
        {
            int k1 = (this.width - this.xSize) / 2;
            int l1 = (this.height - this.ySize) / 2;
            GL11.glPushMatrix();
            //ItemStack itemstack = merchantrecipe.getItemToBuy();
            //ItemStack itemstack1 = merchantrecipe.getSecondItemToBuy();
            RenderHelper.enableGUIStandardItemLighting();
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glEnable(GL11.GL_COLOR_MATERIAL);
            GL11.glEnable(GL11.GL_LIGHTING);
            itemRender.zLevel = 100.0F;
            //itemRenderer.renderItemAndEffectIntoGUI(this.fontRenderer, this.mc.getTextureManager(), itemstack, k + 36, l + 24);
            //itemRenderer.renderItemOverlayIntoGUI(this.fontRenderer, this.mc.getTextureManager(), itemstack, k + 36, l + 24);

            /*
            if (itemstack1 != null)
            {
                itemRenderer.renderItemAndEffectIntoGUI(this.fontRenderer, this.mc.getTextureManager(), itemstack1, k + 62, l + 24);
                itemRenderer.renderItemOverlayIntoGUI(this.fontRenderer, this.mc.getTextureManager(), itemstack1, k + 62, l + 24);
            }
            */

            itemRender.renderItemAndEffectIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), itemstack, k1 + 105, l1 + 23);
            itemRender.renderItemOverlayIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), itemstack, k1 + 105, l1 + 23);
            itemRender.zLevel = 0.0F;
            GL11.glDisable(GL11.GL_LIGHTING);

            //itemRender.

            /*
            if (this.isPointInRegion(36, 24, 16, 16, par1, par2))
            {
                this.drawItemStackTooltip(itemstack, par1, par2);
            }
            else if (itemstack1 != null && this.isPointInRegion(62, 24, 16, 16, par1, par2))
            {
                this.drawItemStackTooltip(itemstack1, par1, par2);
            }
            else*/ if(this.func_146978_c(105, 23, 16, 16, par2, par3))
            {
                this.renderToolTip(itemstack, par2, par3);
            }

            GL11.glPopMatrix();
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            RenderHelper.enableStandardItemLighting();
        }

    }

	@Override
	public void drawScreen(int par1, int par2, float par3)
    {
        super.drawScreen(par1, par2, par3);


    }

}
