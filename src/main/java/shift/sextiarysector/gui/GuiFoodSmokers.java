package shift.sextiarysector.gui;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import shift.sextiarysector.tileentity.TileEntityFluidMachineBase;

public class GuiFoodSmokers extends GuiFluidMachineBase {

    private static final ResourceLocation furnaceGuiTextures = new ResourceLocation("sextiarysector:textures/guis/fluid_furnace.png");

    public GuiFoodSmokers(InventoryPlayer p_i1091_1_, TileEntityFluidMachineBase p_i1091_2_) {
        super(p_i1091_1_, p_i1091_2_);
    }

    @Override
    protected ResourceLocation getBindTexture() {
        return furnaceGuiTextures;
    }
}
