package shift.sextiarysector.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.client.FMLClientHandler;

public class GuiGearHelper {

    public static Minecraft mc = FMLClientHandler.instance().getClient();

	public static final ResourceLocation gears = new ResourceLocation("sextiarysector:textures/guis/gear.png");


	/*
	public static void drawGear(GuiContainer gui, int type,int angle,int x,int y){

		mc.getTextureManager().bindTexture(gears);

		int k = (gui.width - gui.xSize) / 2;
        int l = (gui.height - gui.ySize) / 2;

		GL11.glPushMatrix();

        float scale =  0.25f;
        GL11.glScalef(scale,scale,scale);

        int i2 = 16/2;
        GL11.glTranslatef((k + x + i2)*4, (l + y + i2)*4, 0);
        GL11.glRotatef(angle, 0, 0, 1);
        gui.drawTexturedModalRect(-i2*4, -i2*4, type*64, 0, 16 * 4, (16) * 4);
        GL11.glPopMatrix();

	}

	public void drawTexturedModalRect(GuiContainer gui, int par1, int par2, int par3, int par4, int par5, int par6,int zLevel)
    {
        float f = 1/256;
        float f1 = 1/256;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(par1 + 0, par2 + par6, zLevel, (par3 + 0) * f, (par4 + par6) * f1);
        tessellator.addVertexWithUV(par1 + par5, par2 + par6, zLevel, (par3 + par5) * f, (par4 + par6) * f1);
        tessellator.addVertexWithUV(par1 + par5, par2 + 0, zLevel, (par3 + par5) * f, (par4 + 0) * f1);
        tessellator.addVertexWithUV(par1 + 0, par2 + 0, zLevel, (par3 + 0) * f, (par4 + 0) * f1);
        tessellator.draw();
    }*/

}
