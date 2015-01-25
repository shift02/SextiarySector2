package shift.sextiarysector.gui;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import shift.sextiarysector.tileentity.TileEntitySimpleMachine;

public class GuiTimeMachine extends GuiSimpleMachine{

	private static final ResourceLocation machineGuiTextures = new ResourceLocation("sextiarysector:textures/guis/machine/time_machine.png");

	public GuiTimeMachine(InventoryPlayer par1InventoryPlayer, TileEntitySimpleMachine par2TileEntity) {
		super(par1InventoryPlayer, par2TileEntity);
	}

	@Override
	protected ResourceLocation getBindTexture() {
		return machineGuiTextures;
	}
}
