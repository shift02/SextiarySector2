package shift.sextiarysector.proxy;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;

public class CommonProxy {

    public int leafBedType;

    public int motorType;

    public int holeType;

    public int bottleType;
    public int squareType;

    public int cropType;

    public int fluidCrafterType;

    public int woodHopperType;

    public int gutterType;
    public int halfGutterType;

    public int pipeType;
    public int tankType;
    public int funnelType;
    public int woodenGutterType;

    public int ShaftRenderType;

    public int GearShaftRenderType;

    public int smallWindMillType;
    public int windMillType;
    public int largeWindMillType;
    public int smallWaterwheel;
    public int steamMotorType;

    public int fanType;
    public int sawType;

    public int oreStoneType;

    public int monitorType;
    public int shopMonitorType;

    public int chestType;

    public int farmlandType;
    public int paddyType;
    public int woodType;

    public EntityPlayer getClientPlayer() {
        return null;
    }

    public void setCustomRenderers() {
    }

    public void setItemCustomRenderers() {
    }

    public void registerItemRenderer(Item item) {

    }

    public void openGUI(int id) {

    }

    public void registerInventoryTabs() {

    }

    public void setPluginCustomRenderers(FMLPreInitializationEvent event) {

    }

    public Object getShiftHat() {
        return null;
    }

}
