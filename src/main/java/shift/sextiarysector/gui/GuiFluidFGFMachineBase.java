package shift.sextiarysector.gui;

import java.text.NumberFormat;
import java.util.ArrayList;

import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

import org.lwjgl.opengl.GL11;

import shift.sextiarysector.container.ContainerFluidGFMachineBase;
import shift.sextiarysector.tileentity.TileEntityFluidFGFMachineBase;

public abstract class GuiFluidFGFMachineBase extends GUIGearMachine
{
	private static final ResourceLocation furnaceGuiTextures = new ResourceLocation("sextiarysector:textures/guis/fluid_furnace.png");
	private final TileEntityFluidFGFMachineBase machineInventory;
	float s = 0;

	public GuiFluidFGFMachineBase(InventoryPlayer p_i1091_1_, TileEntityFluidFGFMachineBase p_i1091_2_)
	{
		super(new ContainerFluidGFMachineBase(p_i1091_1_, p_i1091_2_));
		this.machineInventory = p_i1091_2_;
	}

	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of the items)
	 */
	@Override
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
	{
		String s = this.machineInventory.hasCustomInventoryName() ? this.machineInventory.getInventoryName() : I18n.format(this.machineInventory.getInventoryName(), new Object[0]);
		this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(getBindTexture());
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

		int i1;

		if (this.machineInventory.isCharging() || this.machineInventory.isWorking() || (this.machineInventory.inPower != 0 || this.machineInventory.inSpeed != 0)) {
			s = (this.mc.getSystemTime() / 20) % 360;
		}

		this.drawGear(0, (int) -s, 46, 35);

		this.mc.getTextureManager().bindTexture(getBindTexture());

		this.drawTexturedModalRect(k + 57, l + 36, 176, 0, 14, 14);//中央のgear

		this.drawGear(0, (int) -s, 26, 30);

		this.drawGear(1, (int) s, 37, 29);

		this.drawGear(2, (int) s, 16, 26);

		this.mc.getTextureManager().bindTexture(getBindTexture());

		i1 = this.machineInventory.getWorkProgressScaled(24);
		this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);

		this.drawTexturedModalRect(k + 7, l + 16, 201, 0, 18, 18);

		i1 = this.machineInventory.getEnergyProgressScaled(16);
		//this.drawTexturedModalRect(k + 8, l + 17 + 16 - i1, 219, 14 + 16 - i1, 16, i1);
		this.drawTexturedModalRect(k + 8, l + 17 + 16 - i1, 219, 16 - i1, 16, i1);

		//Fluid
		if (this.machineInventory.isFluid())
		{
			//FluidStack f = this.tileFurnace.getTank().getFluid();
			this.drawFluidTank(k + 107, l + 15, this.machineInventory.getTank(), 34, 36);

		}
		this.mc.getTextureManager().bindTexture(getBindTexture());
		this.drawTexturedModalRect(k + 108, l + 17, 176, 31, 32, 36);

	}

	@Override
	public void drawScreen(int par1, int par2, float par3)
	{
		super.drawScreen(par1, par2, par3);

		ArrayList arraylist = new ArrayList();

		String f = "None";

		if (this.machineInventory.isFluid())
		{
			f = this.machineInventory.getTank().getFluid().getFluid().getLocalizedName(this.machineInventory.getTank().getFluid());
		}

		NumberFormat nfNum = NumberFormat.getNumberInstance();

		arraylist.add(0, "" + EnumChatFormatting.RESET + "Fluid Tank" + EnumChatFormatting.RESET);
		arraylist.add(1, "" + EnumChatFormatting.GRAY + "Fluid  : " + f);
		arraylist.add(2, "" + EnumChatFormatting.GRAY + "Amount : " + nfNum.format(this.machineInventory.getTank().getFluidAmount()) + " / " + nfNum.format(this.machineInventory.getTank().getCapacity()) + " mB");

		GL11.glPushMatrix();
		if (this.func_146978_c(106, 14, 36, 38, par1, par2))
		{
			drawHoveringText(arraylist, par1, par2, fontRendererObj);
		}
		GL11.glPopMatrix();

		ArrayList arraylist2 = new ArrayList();

		int p = machineInventory.inPower;
		int s = machineInventory.inSpeed;

		arraylist2.add(0, "" + EnumChatFormatting.RESET + "Machine Status" + EnumChatFormatting.RESET);
		arraylist2.add(1, "" + EnumChatFormatting.RED + "In Power" + EnumChatFormatting.RESET);
		arraylist2.add(2, "" + EnumChatFormatting.GRAY + p);
		arraylist2.add(3, "" + EnumChatFormatting.BLUE + "In Speed" + EnumChatFormatting.RESET);
		arraylist2.add(4, EnumChatFormatting.GRAY + "" + s);

		GL11.glPushMatrix();
		if (this.func_146978_c(56, 35, 16, 16, par1, par2))
		{
			drawHoveringText(arraylist2, par1, par2, fontRendererObj);
		}
		GL11.glPopMatrix();

		ArrayList arraylist3 = new ArrayList();

		//NumberFormat nfNum = NumberFormat.getNumberInstance();

		int p2 = machineInventory.storage.getPowerStored();
		long s2 = machineInventory.storage.getSpeedStored();

		int p2_1 = machineInventory.storage.getMaxPower();
		long s2_1 = machineInventory.storage.getMaxSpeed();

		arraylist3.add(0, "" + EnumChatFormatting.RESET + "Machine Storage" + EnumChatFormatting.RESET);
		arraylist3.add(1, "" + EnumChatFormatting.RED + "Power" + EnumChatFormatting.RESET);
		arraylist3.add(2, "" + EnumChatFormatting.GRAY + p2 + " / " + p2_1);
		arraylist3.add(3, "" + EnumChatFormatting.BLUE + "Speed" + EnumChatFormatting.RESET);
		arraylist3.add(4, EnumChatFormatting.GRAY + "" + nfNum.format(s2) + " / " + nfNum.format(s2_1));

		GL11.glPushMatrix();
		if (this.func_146978_c(6, 16, 18, 18, par1, par2))
		{
			drawHoveringText(arraylist3, par1, par2, fontRendererObj);
		}
		GL11.glPopMatrix();

	}

	public void drawFluidTank(int x, int y, FluidTank fluidTank, int width, int height) {

		GL11.glColor3f(1, 1, 1);

		FluidStack fluidStack = fluidTank.getFluid();

		ResourceLocation r;
		if (fluidStack.getFluid().getSpriteNumber() == 0) {
			r = TextureMap.locationBlocksTexture;
		} else {
			r = TextureMap.locationItemsTexture;
		}

		this.mc.getTextureManager().bindTexture(r);
		this.setColor3ubFromInt(fluidStack.getFluid().getColor(fluidStack));

		int widthR = width;//*(fluidTank.getFluidAmount()/fluidTank.getCapacity());
		int heightR = (int) (width * ((float) fluidTank.getFluidAmount() / (float) fluidTank.getCapacity()));

		int yR = y + (height - heightR);

		int widthL = 0;
		int heightL = 0;
		IIcon icon = (fluidStack.getFluid().getIcon(fluidStack));

		for (int i = 0; i < widthR; i += 16) {

			for (int j = 0; j < heightR; j += 16) {

				widthL = Math.min(widthR - i, 16);
				heightL = Math.min(heightR - j, 16);
				this.drawTexturedModelRectFromIcon(x + i, yR + j, icon, widthL, heightL);

			}
		}

		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0F);

	}

	public static void setColor3ubFromInt(int color) {

		GL11.glColor3ub((byte) (color >> 16 & 0xFF), (byte) (color >> 8 & 0xFF), (byte) (color & 0xFF));

	}

	protected abstract ResourceLocation getBindTexture();
}
