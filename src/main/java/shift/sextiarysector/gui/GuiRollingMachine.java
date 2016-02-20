package shift.sextiarysector.gui;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import shift.sextiarysector.tileentity.TileEntitySimpleMachine;

public class GuiRollingMachine extends GuiSimpleMachine {

    private static final ResourceLocation machineGuiTextures = new ResourceLocation("sextiarysector:textures/guis/machine/rolling_machine.png");

    public GuiRollingMachine(InventoryPlayer par1InventoryPlayer, TileEntitySimpleMachine par2TileEntity) {
        super(par1InventoryPlayer, par2TileEntity);
    }

    @Override
    protected ResourceLocation getBindTexture() {
        return machineGuiTextures;
    }
}
