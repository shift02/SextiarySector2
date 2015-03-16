package shift.sextiarysector.gui;

import java.text.NumberFormat;
import java.util.ArrayList;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import shift.sextiarysector.container.ContainerSimpleMachine;
import shift.sextiarysector.tileentity.TileEntitySimpleMachine;

public abstract class GuiSimpleMachine  extends GUIGearMachine{

	private static final ResourceLocation machineGuiTextures = new ResourceLocation("textures/gui/container/furnace.png");
    protected final TileEntitySimpleMachine machineInventory;
    float s =0;

	public GuiSimpleMachine(InventoryPlayer par1InventoryPlayer, TileEntitySimpleMachine par2TileEntity) {
		super(new ContainerSimpleMachine(par1InventoryPlayer, par2TileEntity));
		this.machineInventory = par2TileEntity;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        String s = this.machineInventory.hasCustomInventoryName() ? this.machineInventory.getInventoryName() : I18n.format(this.machineInventory.getInventoryName());
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(getBindTexture());
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        int i1;

        if(this.machineInventory.isCharging()||this.machineInventory.isWorking()||(this.machineInventory.inPower!=0||this.machineInventory.inSpeed!=0)){
        	s=(this.mc.getSystemTime()/20)%360;
        }

        this.drawGear( 0, (int) -s, 46, 35);

        this.mc.getTextureManager().bindTexture(getBindTexture());

        /*
        GL11.glPushMatrix();

        float scale =  0.25f;
        GL11.glScalef(scale,scale,scale);

        int i2 = 7;
        GL11.glTranslatef((k + 57 + i2)*4, (l + 36 + i2)*4, 0);
        GL11.glRotatef(s, 0, 0, 1);
        this.drawTexturedModalRect(-i2*4, -i2*4, 176, 31, 14 * 4, (14) * 4);
        GL11.glPopMatrix();
        */
        this.drawTexturedModalRect(k+57, l+36, 190, 0, 14 , 14);
        //Main
        //this.drawGear( 4, (int) -s, 56, 35);

        //this.drawGear( 4, (int) 0, 56, 35);


        this.drawGear( 0, (int) -s, 26, 30);

        this.drawGear( 1, (int) s, 37, 29);

        this.drawGear( 2, (int) s, 16, 26);



        this.mc.getTextureManager().bindTexture(getBindTexture());

        i1 = this.machineInventory.getWorkProgressScaled(24);
        this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);

        this.drawTexturedModalRect(k + 7, l + 16 , 217,0 , 18 , 18);

        i1 = this.machineInventory.getEnergyProgressScaled(16);
        this.drawTexturedModalRect(k + 8, l + 17 + 16 - i1, 201,14 + 16 - i1, 16 , i1);


    }

	@Override
	public void drawScreen(int par1, int par2, float par3)
    {
		super.drawScreen(par1, par2, par3);

		ArrayList arraylist = new ArrayList();

		int p = machineInventory.inPower;
		int s = machineInventory.inSpeed;

		arraylist.add(0,"" + EnumChatFormatting.RESET+ "Machine Status" + EnumChatFormatting.RESET );
		arraylist.add(1,"" + EnumChatFormatting.RED + "In Power" + EnumChatFormatting.RESET );
		arraylist.add(2, ""+ EnumChatFormatting.GRAY + p );
		arraylist.add(3,"" + EnumChatFormatting.BLUE + "In Speed" + EnumChatFormatting.RESET );
		arraylist.add(4,  EnumChatFormatting.GRAY + "" + s  );


		GL11.glPushMatrix();
		if (this.func_146978_c(56, 35, 16, 16, par1, par2))
        {
			drawHoveringText(arraylist, par1, par2,fontRendererObj);
        }
        GL11.glPopMatrix();

        ArrayList arraylist2 = new ArrayList();

        NumberFormat nfNum = NumberFormat.getNumberInstance();

		int p2 = machineInventory.storage.getPowerStored();
		long s2 = machineInventory.storage.getSpeedStored();

		int p2_1 = machineInventory.storage.getMaxPower();
		long s2_1 = machineInventory.storage.getMaxSpeed();

		arraylist2.add(0,"" + EnumChatFormatting.RESET+ "Machine Storage" + EnumChatFormatting.RESET );
		arraylist2.add(1,"" + EnumChatFormatting.RED + "Power" + EnumChatFormatting.RESET );
		arraylist2.add(2, ""+ EnumChatFormatting.GRAY + p2 +" / "+p2_1);
		arraylist2.add(3,"" + EnumChatFormatting.BLUE + "Speed" + EnumChatFormatting.RESET );
		arraylist2.add(4,  EnumChatFormatting.GRAY + "" + nfNum.format(s2) +" / "+ nfNum.format(s2_1) );


		GL11.glPushMatrix();
		if (this.func_146978_c(6, 16, 18, 18, par1, par2))
        {
			drawHoveringText(arraylist2, par1, par2,fontRendererObj);
        }
        GL11.glPopMatrix();

    }

	protected abstract  ResourceLocation getBindTexture();

}
