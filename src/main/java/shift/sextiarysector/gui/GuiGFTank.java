package shift.sextiarysector.gui;

import java.text.NumberFormat;
import java.util.ArrayList;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import shift.sextiarysector.container.ContainerGFTank;
import shift.sextiarysector.tileentity.TileEntityGFTank;

public class GuiGFTank  extends GUIGearMachine{

	private static final ResourceLocation tankGuiTextures = new ResourceLocation("sextiarysector:textures/guis/machine/wood_gf_tank.png");
    protected final TileEntityGFTank tankInventory;
    float s =0;
    float s2 =0;

    public GuiGFTank(InventoryPlayer par1InventoryPlayer, TileEntityGFTank par2TileEntity) {
		super(new ContainerGFTank(par1InventoryPlayer, par2TileEntity));
		this.tankInventory = par2TileEntity;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        String s = this.tankInventory.hasCustomInventoryName() ? this.tankInventory.getInventoryName() : I18n.format(this.tankInventory.getInventoryName());
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

        if((this.tankInventory.inPower!=0||this.tankInventory.inSpeed!=0)){
        	s=(this.mc.getSystemTime()/20)%360;
        }
        if((this.tankInventory.outPower!=0||this.tankInventory.outSpeed!=0)){
        	s2=(this.mc.getSystemTime()/20)%360;
        }

        this.drawGear( 0, (int) -s, 50, 28);
        this.drawGear( 2, (int) -s2, 120, 45);

        this.mc.getTextureManager().bindTexture(getBindTexture());

        this.drawTexturedModalRect(k+45, l+36, 190, 0, 14 , 14);
        this.drawTexturedModalRect(k+117, l+36, 190, 0, 14 , 14);

        //in
        this.drawGear( 2, (int) -s, 69, 30);
        this.drawGear( 1, (int) s, 60, 23);

        //out
        this.drawGear( 1, (int) -s, 82, 48);
        this.drawGear( 2, (int) -s, 103, 60);

        this.drawGear( 0, (int) s, 92, 52);
        this.drawGear( 0, (int) s, 116, 55);

        //this.drawGear( 1, (int) s, 37, 29);

        //this.drawGear( 2, (int) s, 16, 26);

        this.mc.getTextureManager().bindTexture(getBindTexture());

        this.drawTexturedModalRect(k + 78, l + 34 , 217,0 , 18 , 18);

        i1 = this.tankInventory.getEnergyProgressScaled(16);
        this.drawTexturedModalRect(k + 79, l + 35 + 16 - i1, 201,14 + 16 - i1, 16 , i1);

    }

	@Override
	public void drawScreen(int par1, int par2, float par3)
    {
		super.drawScreen(par1, par2, par3);

		ArrayList arraylist = new ArrayList();

		int p = tankInventory.inPower;
		int s = tankInventory.inSpeed;

		arraylist.add(0,"" + EnumChatFormatting.RESET+ "Machine Status" + EnumChatFormatting.RESET );
		arraylist.add(1,"" + EnumChatFormatting.RED + "In Power" + EnumChatFormatting.RESET );
		arraylist.add(2, ""+ EnumChatFormatting.GRAY + p );
		arraylist.add(3,"" + EnumChatFormatting.BLUE + "In Speed" + EnumChatFormatting.RESET );
		arraylist.add(4,  EnumChatFormatting.GRAY + "" + s  );


		GL11.glPushMatrix();
		if (this.func_146978_c(44, 35, 16, 16, par1, par2))
        {
			drawHoveringText(arraylist, par1, par2,fontRendererObj);
        }
        GL11.glPopMatrix();

        ArrayList arraylist2 = new ArrayList();

		int p2 = tankInventory.outPower;
		int s2 = tankInventory.outSpeed;

		arraylist2.add(0,"" + EnumChatFormatting.RESET+ "Machine Status" + EnumChatFormatting.RESET );
		arraylist2.add(1,"" + EnumChatFormatting.RED + "Out Power" + EnumChatFormatting.RESET );
		arraylist2.add(2, ""+ EnumChatFormatting.GRAY + p2 );
		arraylist2.add(3,"" + EnumChatFormatting.BLUE + "Out Speed" + EnumChatFormatting.RESET );
		arraylist2.add(4,  EnumChatFormatting.GRAY + "" + s2  );


		GL11.glPushMatrix();
		if (this.func_146978_c(116, 35, 16, 16, par1, par2))
        {
			drawHoveringText(arraylist2, par1, par2,fontRendererObj);
        }
        GL11.glPopMatrix();

        ArrayList arraylist3 = new ArrayList();

        NumberFormat nfNum = NumberFormat.getNumberInstance();

		int p3 = tankInventory.storage.getPowerStored();
		long s3 = tankInventory.storage.getSpeedStored();

		int p2_1 = tankInventory.storage.getMaxPowerStored();
		long s2_1 = tankInventory.storage.getMaxSpeedStored();

		arraylist3.add(0,"" + EnumChatFormatting.RESET+ "Machine Storage" + EnumChatFormatting.RESET );
		arraylist3.add(1,"" + EnumChatFormatting.RED + "Power" + EnumChatFormatting.RESET );
		arraylist3.add(2, ""+ EnumChatFormatting.GRAY + p3 +" / "+p2_1);
		arraylist3.add(3,"" + EnumChatFormatting.BLUE + "Speed" + EnumChatFormatting.RESET );
		arraylist3.add(4,  EnumChatFormatting.GRAY + "" + nfNum.format(s3) +" / "+ nfNum.format(s2_1) );


		GL11.glPushMatrix();
		if (this.func_146978_c(78, 34, 18, 18, par1, par2))
        {
			drawHoveringText(arraylist3, par1, par2,fontRendererObj);
        }
        GL11.glPopMatrix();

    }

	protected  ResourceLocation getBindTexture() {
		return this.tankGuiTextures;
	}

}
