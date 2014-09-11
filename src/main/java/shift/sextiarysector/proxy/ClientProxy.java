package shift.sextiarysector.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import shift.sextiarysector.renderer.block.RendererShaft;
import shift.sextiarysector.renderer.block.RendererSmallWindmill;
import shift.sextiarysector.renderer.item.RenderGF;
import shift.sextiarysector.tileentity.TileEntityShaft;
import shift.sextiarysector.tileentity.TileEntitySmallWindmill;
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

		this.ShaftRenderType = RenderingRegistry.getNextAvailableRenderId();

		this.smallWindMillType = RenderingRegistry.getNextAvailableRenderId();

		RenderingRegistry.registerBlockHandler(new RendererShaft());

		RenderingRegistry.registerBlockHandler(new RendererSmallWindmill());

		this.setCustomClientRenderers();

	}

	@SideOnly(Side.CLIENT)
	public void setCustomClientRenderers() {

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityShaft.class, new RendererShaft());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySmallWindmill.class, new RendererSmallWindmill());

	}

	@Override
	public void registerItemRenderer(Item item) {
		MinecraftForgeClient.registerItemRenderer(item, new RenderGF());
	}

}
