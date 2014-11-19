package shift.sextiarysector.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import shift.mceconomy2.packet.PacketHandler;
import shift.sextiarysector.gui.tab.InventoryTabEquipment;
import shift.sextiarysector.gui.tab.InventoryTabVanilla;
import shift.sextiarysector.gui.tab.TabManager;
import shift.sextiarysector.packet.PacketGuiId;
import shift.sextiarysector.renderer.block.RendererBlockBottle;
import shift.sextiarysector.renderer.block.RendererChest;
import shift.sextiarysector.renderer.block.RendererFarmland;
import shift.sextiarysector.renderer.block.RendererFluidCrafter;
import shift.sextiarysector.renderer.block.RendererGearShaft;
import shift.sextiarysector.renderer.block.RendererHole;
import shift.sextiarysector.renderer.block.RendererMonitor;
import shift.sextiarysector.renderer.block.RendererShaft;
import shift.sextiarysector.renderer.block.RendererSmallWindmill;
import shift.sextiarysector.renderer.block.RendererWindmill;
import shift.sextiarysector.renderer.item.RenderGF;
import shift.sextiarysector.tileentity.TileEntityBlockBottle;
import shift.sextiarysector.tileentity.TileEntityFluidCrafter;
import shift.sextiarysector.tileentity.TileEntityGearShaft;
import shift.sextiarysector.tileentity.TileEntityMonitor;
import shift.sextiarysector.tileentity.TileEntitySSChest;
import shift.sextiarysector.tileentity.TileEntityShaft;
import shift.sextiarysector.tileentity.TileEntitySmallWindmill;
import shift.sextiarysector.tileentity.TileEntityWindmill;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy{


	@Override
	public EntityPlayer getClientPlayer(){
		return Minecraft.getMinecraft().thePlayer;
	}

	@Override
	public void setCustomRenderers() {

		this.holeType = RenderingRegistry.getNextAvailableRenderId();

		this.bottleType = RenderingRegistry.getNextAvailableRenderId();

		this.fluidCrafterType = RenderingRegistry.getNextAvailableRenderId();

		this.ShaftRenderType = RenderingRegistry.getNextAvailableRenderId();

		this.GearShaftRenderType = RenderingRegistry.getNextAvailableRenderId();

		this.smallWindMillType = RenderingRegistry.getNextAvailableRenderId();
		this.windMillType = RenderingRegistry.getNextAvailableRenderId();

		this.chestType = RenderingRegistry.getNextAvailableRenderId();

		this.monitorType = RenderingRegistry.getNextAvailableRenderId();

		this.farmlandType = RenderingRegistry.getNextAvailableRenderId();

		RenderingRegistry.registerBlockHandler(new RendererHole());

		RenderingRegistry.registerBlockHandler(new RendererBlockBottle());

		RenderingRegistry.registerBlockHandler(new RendererFluidCrafter());

		RenderingRegistry.registerBlockHandler(new RendererShaft());

		RenderingRegistry.registerBlockHandler(new RendererGearShaft());

		RenderingRegistry.registerBlockHandler(new RendererSmallWindmill());
		RenderingRegistry.registerBlockHandler(new RendererWindmill());

		RenderingRegistry.registerBlockHandler(new RendererChest());

		RenderingRegistry.registerBlockHandler(new RendererMonitor());

		RenderingRegistry.registerBlockHandler(new RendererFarmland());

		this.setCustomClientRenderers();

	}

	@SideOnly(Side.CLIENT)
	public void setCustomClientRenderers() {

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityShaft.class, new RendererShaft());

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBlockBottle.class, new RendererBlockBottle());

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFluidCrafter.class, new RendererFluidCrafter());

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGearShaft.class, new RendererGearShaft());

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySmallWindmill.class, new RendererSmallWindmill());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWindmill.class, new RendererWindmill());


		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySSChest.class, new RendererChest());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMonitor.class, new RendererMonitor());

	}

	@Override
	public void registerItemRenderer(Item item) {
		MinecraftForgeClient.registerItemRenderer(item, new RenderGF());
	}

	public void openGUI(int id){
		PacketHandler.INSTANCE.sendToServer(new PacketGuiId(id));
	}

	public void registerInventoryTabs()
    {
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

		TabManager.registerTab(new InventoryTabVanilla());
		TabManager.registerTab(new InventoryTabEquipment());
		TabManager.registerTab(new InventoryTabEquipment());
		TabManager.registerTab(new InventoryTabEquipment());
		TabManager.registerTab(new InventoryTabEquipment());
		TabManager.registerTab(new InventoryTabEquipment());
		TabManager.registerTab(new InventoryTabEquipment());
		TabManager.registerTab(new InventoryTabEquipment());

    }

}
