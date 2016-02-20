package shift.sextiarysector.gui;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import shift.sextiarysector.tileentity.TileEntitySimpleMachine;

public class GuiMillstone extends GuiSimpleMachine {

    private static final ResourceLocation machineGuiTextures = new ResourceLocation("sextiarysector:textures/guis/machine/millstone.png");

    public GuiMillstone(InventoryPlayer par1InventoryPlayer, TileEntitySimpleMachine par2TileEntity) {
        super(par1InventoryPlayer, par2TileEntity);
    }

    @Override
    protected ResourceLocation getBindTexture() {
        return machineGuiTextures;
    }

}
