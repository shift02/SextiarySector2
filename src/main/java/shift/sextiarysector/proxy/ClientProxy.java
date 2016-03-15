package shift.sextiarysector.proxy;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.SextiarySector;
import shift.sextiarysector.entity.EntityMineboat;
import shift.sextiarysector.entity.EntityMineboatChest;
import shift.sextiarysector.entity.EntityMineboatTank;
import shift.sextiarysector.gui.tab.TabManager;
import shift.sextiarysector.packet.PacketGuiId;
import shift.sextiarysector.packet.SSPacketHandler;
import shift.sextiarysector.plugin.IPlugin;
import shift.sextiarysector.plugin.SSPlugins;
import shift.sextiarysector.renderer.block.RendererBlockBottle;
import shift.sextiarysector.renderer.block.RendererChest;
import shift.sextiarysector.renderer.block.RendererCrop;
import shift.sextiarysector.renderer.block.RendererFan;
import shift.sextiarysector.renderer.block.RendererFarmland;
import shift.sextiarysector.renderer.block.RendererFigure;
import shift.sextiarysector.renderer.block.RendererFluidCrafter;
import shift.sextiarysector.renderer.block.RendererFunnel;
import shift.sextiarysector.renderer.block.RendererGearShaft;
import shift.sextiarysector.renderer.block.RendererGutter;
import shift.sextiarysector.renderer.block.RendererHalfGutter;
import shift.sextiarysector.renderer.block.RendererHole;
import shift.sextiarysector.renderer.block.RendererKnife;
import shift.sextiarysector.renderer.block.RendererLargeWindmill;
import shift.sextiarysector.renderer.block.RendererLeafBed;
import shift.sextiarysector.renderer.block.RendererMonitor;
import shift.sextiarysector.renderer.block.RendererMotor;
import shift.sextiarysector.renderer.block.RendererOreStone;
import shift.sextiarysector.renderer.block.RendererPaddy;
import shift.sextiarysector.renderer.block.RendererPipe;
import shift.sextiarysector.renderer.block.RendererSaw;
import shift.sextiarysector.renderer.block.RendererShaft;
import shift.sextiarysector.renderer.block.RendererShopMonitor;
import shift.sextiarysector.renderer.block.RendererSmallWaterwheel;
import shift.sextiarysector.renderer.block.RendererSmallWindmill;
import shift.sextiarysector.renderer.block.RendererSquare;
import shift.sextiarysector.renderer.block.RendererSteamMotor;
import shift.sextiarysector.renderer.block.RendererTank;
import shift.sextiarysector.renderer.block.RendererWindmill;
import shift.sextiarysector.renderer.block.RendererWood;
import shift.sextiarysector.renderer.block.RendererWoodHopper;
import shift.sextiarysector.renderer.block.RendererWoodenGutter;
import shift.sextiarysector.renderer.entity.RenderMineboat;
import shift.sextiarysector.renderer.entity.RenderMineboatTank;
import shift.sextiarysector.renderer.item.RenderGF;
import shift.sextiarysector.tileentity.TileEntityBlockBottle;
import shift.sextiarysector.tileentity.TileEntityFan;
import shift.sextiarysector.tileentity.TileEntityFigure;
import shift.sextiarysector.tileentity.TileEntityFluidCrafter;
import shift.sextiarysector.tileentity.TileEntityGearShaft;
import shift.sextiarysector.tileentity.TileEntityGutter;
import shift.sextiarysector.tileentity.TileEntityHalfGutter;
import shift.sextiarysector.tileentity.TileEntityKnife;
import shift.sextiarysector.tileentity.TileEntityLargeWindmill;
import shift.sextiarysector.tileentity.TileEntityMonitor;
import shift.sextiarysector.tileentity.TileEntityPipe;
import shift.sextiarysector.tileentity.TileEntitySSChest;
import shift.sextiarysector.tileentity.TileEntitySaw;
import shift.sextiarysector.tileentity.TileEntityShaft;
import shift.sextiarysector.tileentity.TileEntityShopMonitor;
import shift.sextiarysector.tileentity.TileEntitySmallWaterwheel;
import shift.sextiarysector.tileentity.TileEntitySmallWindmill;
import shift.sextiarysector.tileentity.TileEntitySquare;
import shift.sextiarysector.tileentity.TileEntitySteamMotor;
import shift.sextiarysector.tileentity.TileEntityTank;
import shift.sextiarysector.tileentity.TileEntityWindmill;

public class ClientProxy extends CommonProxy {

    @SideOnly(Side.CLIENT)
    public shift.sextiarysector.renderer.model.ModelShiftHat model = new shift.sextiarysector.renderer.model.ModelShiftHat();

    @Override
    public EntityPlayer getClientPlayer() {
        return Minecraft.getMinecraft().thePlayer;
    }

    @Override
    public void setCustomRenderers() {

        this.motorType = RenderingRegistry.getNextAvailableRenderId();

        this.holeType = RenderingRegistry.getNextAvailableRenderId();

        this.bottleType = RenderingRegistry.getNextAvailableRenderId();

        this.squareType = RenderingRegistry.getNextAvailableRenderId();

        this.gutterType = RenderingRegistry.getNextAvailableRenderId();

        this.halfGutterType = RenderingRegistry.getNextAvailableRenderId();

        this.fluidCrafterType = RenderingRegistry.getNextAvailableRenderId();

        this.woodHopperType = RenderingRegistry.getNextAvailableRenderId();

        this.leafBedType = RenderingRegistry.getNextAvailableRenderId();

        this.cropType = RenderingRegistry.getNextAvailableRenderId();

        this.tankType = RenderingRegistry.getNextAvailableRenderId();
        this.funnelType = RenderingRegistry.getNextAvailableRenderId();
        this.pipeType = RenderingRegistry.getNextAvailableRenderId();
        this.woodenGutterType = RenderingRegistry.getNextAvailableRenderId();

        this.ShaftRenderType = RenderingRegistry.getNextAvailableRenderId();

        this.GearShaftRenderType = RenderingRegistry.getNextAvailableRenderId();

        this.smallWindMillType = RenderingRegistry.getNextAvailableRenderId();
        this.windMillType = RenderingRegistry.getNextAvailableRenderId();
        this.largeWindMillType = RenderingRegistry.getNextAvailableRenderId();
        this.smallWaterwheel = RenderingRegistry.getNextAvailableRenderId();
        this.steamMotorType = RenderingRegistry.getNextAvailableRenderId();

        this.fanType = RenderingRegistry.getNextAvailableRenderId();
        this.sawType = RenderingRegistry.getNextAvailableRenderId();

        this.chestType = RenderingRegistry.getNextAvailableRenderId();

        this.oreStoneType = RenderingRegistry.getNextAvailableRenderId();

        this.monitorType = RenderingRegistry.getNextAvailableRenderId();

        this.shopMonitorType = RenderingRegistry.getNextAvailableRenderId();

        this.farmlandType = RenderingRegistry.getNextAvailableRenderId();

        this.paddyType = RenderingRegistry.getNextAvailableRenderId();

        this.woodType = RenderingRegistry.getNextAvailableRenderId();

        RenderingRegistry.registerBlockHandler(new RendererMotor());

        RenderingRegistry.registerBlockHandler(new RendererHole());

        RenderingRegistry.registerBlockHandler(new RendererBlockBottle());

        RenderingRegistry.registerBlockHandler(new RendererSquare());

        RenderingRegistry.registerBlockHandler(new RendererGutter());
        RenderingRegistry.registerBlockHandler(new RendererHalfGutter());

        RenderingRegistry.registerBlockHandler(new RendererFluidCrafter());

        RenderingRegistry.registerBlockHandler(new RendererWoodHopper());

        RenderingRegistry.registerBlockHandler(new RendererLeafBed());

        RenderingRegistry.registerBlockHandler(new RendererCrop());

        RenderingRegistry.registerBlockHandler(new RendererTank());
        RenderingRegistry.registerBlockHandler(new RendererFunnel());
        RenderingRegistry.registerBlockHandler(new RendererPipe());
        RenderingRegistry.registerBlockHandler(new RendererWoodenGutter());

        RenderingRegistry.registerBlockHandler(new RendererShaft());

        RenderingRegistry.registerBlockHandler(new RendererGearShaft());

        RenderingRegistry.registerBlockHandler(new RendererSmallWindmill());
        RenderingRegistry.registerBlockHandler(new RendererWindmill());
        RenderingRegistry.registerBlockHandler(new RendererLargeWindmill());
        RenderingRegistry.registerBlockHandler(new RendererSmallWaterwheel());
        RenderingRegistry.registerBlockHandler(new RendererSteamMotor());

        RenderingRegistry.registerBlockHandler(new RendererFan());
        RenderingRegistry.registerBlockHandler(new RendererSaw());

        RenderingRegistry.registerBlockHandler(new RendererChest());

        RenderingRegistry.registerBlockHandler(new RendererOreStone());

        RenderingRegistry.registerBlockHandler(new RendererMonitor());
        RenderingRegistry.registerBlockHandler(new RendererShopMonitor());

        RenderingRegistry.registerBlockHandler(new RendererFarmland());
        RenderingRegistry.registerBlockHandler(new RendererPaddy());
        RenderingRegistry.registerBlockHandler(new RendererWood());

        this.setCustomClientRenderers();

    }

    @Override
    public void setItemCustomRenderers() {
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(SSBlocks.figure), new RendererFigure());
    }

    @SideOnly(Side.CLIENT)
    public void setCustomClientRenderers() {

        //ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCraftFurnace.class, new RendererCraftFurnace());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityShaft.class, new RendererShaft());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBlockBottle.class, new RendererBlockBottle());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySquare.class, new RendererSquare());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGutter.class, new RendererGutter());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHalfGutter.class, new RendererHalfGutter());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTank.class, new RendererTank());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTank.class, new RendererTank());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPipe.class, new RendererPipe());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityKnife.class, new RendererKnife());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFigure.class, new RendererFigure());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFluidCrafter.class, new RendererFluidCrafter());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGearShaft.class, new RendererGearShaft());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySmallWindmill.class, new RendererSmallWindmill());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWindmill.class, new RendererWindmill());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLargeWindmill.class, new RendererLargeWindmill());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySmallWaterwheel.class, new RendererSmallWaterwheel());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySteamMotor.class, new RendererSteamMotor());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFan.class, new RendererFan());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySaw.class, new RendererSaw());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySSChest.class, new RendererChest());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMonitor.class, new RendererMonitor());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityShopMonitor.class, new RendererShopMonitor());

        RenderingRegistry.registerEntityRenderingHandler(EntityMineboatChest.class, new RenderMineboat());
        RenderingRegistry.registerEntityRenderingHandler(EntityMineboatTank.class, new RenderMineboatTank());
        RenderingRegistry.registerEntityRenderingHandler(EntityMineboat.class, new RenderMineboat());

    }

    @Override
    public void registerItemRenderer(Item item) {
        MinecraftForgeClient.registerItemRenderer(item, new RenderGF());
    }

    @Override
    public void openGUI(int id) {
        SSPacketHandler.INSTANCE.sendToServer(new PacketGuiId(id));
    }

    @Override
    public void registerInventoryTabs() {
        /*
        if (!Loader.isModLoaded("TConstruct") || TabRegistry.getTabList().size() < 3)
        {
            TabRegistry.registerTab(new InventoryTabVanilla());
        }
        
        TabRegistry.registerTab(new InventoryTabSextiarysector());
        TabRegistry.registerTab(new InventoryTabSextiarysector());
        TabRegistry.registerTab(new InventoryTabSextiarysector());
        TabRegistry.registerTab(new InventoryTabSextiarysector());
        TabRegistry.registerTab(new InventoryTabSextiarysector());
        TabRegistry.registerTab(new InventoryTabSextiarysector());
        TabRegistry.registerTab(new InventoryTabSextiarysector());
        */
        TabManager.initTabManager();
        //TabManager.registerTab(new InventoryTabEquipment());
        //TabManager.registerTab(new InventoryTabEquipment());
        //TabManager.registerTab(new InventoryTabEquipment());
        //TabManager.registerTab(new InventoryTabEquipment());
        //TabManager.registerTab(new InventoryTabEquipment());
        //TabManager.registerTab(new InventoryTabEquipment());

    }

    @Override
    public void setPluginCustomRenderers(FMLPreInitializationEvent event) {

        for (IPlugin p : SSPlugins.plugins) {
            try {

                p.preClientPlugin(event);

            } catch (Exception e) {

                SextiarySector.Log.log(Level.WARN, p.getModName() + " integration was unsuccessful - please contact the author of this mod to let them know that the API may have changed.");
                SextiarySector.Log.catching(e);

            }
        }

    }

    @Override
    public Object getShiftHat() {
        return model;
    }

}
