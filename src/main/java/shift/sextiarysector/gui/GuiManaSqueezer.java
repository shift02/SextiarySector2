package shift.sextiarysector.gui;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import shift.sextiarysector.tileentity.TileEntityFluidFGFMachineBase;

public class GuiManaSqueezer extends GuiFluidFGFMachineBase {

    private static final ResourceLocation machineGuiTextures = new ResourceLocation("sextiarysector:textures/guis/machine/mana_squeezer.png");

    public GuiManaSqueezer(InventoryPlayer par1InventoryPlayer, TileEntityFluidFGFMachineBase par2TileEntity) {
        super(par1InventoryPlayer, par2TileEntity);
    }

    @Override
    protected ResourceLocation getBindTexture() {
        return machineGuiTextures;
    }

}
