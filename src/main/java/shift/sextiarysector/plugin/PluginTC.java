package shift.sextiarysector.plugin;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.common.MinecraftForge;
import shift.sextiarysector.api.equipment.EquipmentType;
import shift.sextiarysector.item.ItemMagicContactLenses;
import shift.sextiarysector.player.EntityPlayerManager;
import shift.sextiarysector.player.EquipmentStats;
import thaumcraft.api.IGoggles;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PluginTC implements IPlugin {

    private boolean eventDraw = false;

    public static Item magicContactLenses;

    @Override
    public String getModName() {
        return "Thaumcraft";
    }

    @Override
    public void prePlugin(FMLPreInitializationEvent event) {

        magicContactLenses = new ItemMagicContactLenses().setUnlocalizedName("ss.magic_contact_lenses").setTextureName("sextiarysector:face/magic_contact_lenses");
        GameRegistry.registerItem(magicContactLenses, "MagicContactLenses");

    }

    @Override
    public void preClientPlugin(FMLPreInitializationEvent event) {

        if (event.getSide().isClient()) {
            MinecraftForge.EVENT_BUS.register(this);
        }

    }

    @Override
    public void initPlugin(FMLInitializationEvent event) {

    }

    @Override
    public void postPlugin(FMLPostInitializationEvent event) {

    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    //(priority = EventPriority.HIGHEST)
    public void blockHighlight(DrawBlockHighlightEvent event) {

        if (eventDraw) return;

        EntityPlayer player = event.player;

        EquipmentStats e = EntityPlayerManager.getEquipmentStats(player);

        ItemStack contactLenses = e.inventory.getStackInSlot(EquipmentType.Face.getSlot(0));

        if (contactLenses == null) return;

        if (contactLenses.getItem() != this.magicContactLenses) return;

        ItemStack item = player.getEquipmentInSlot(4);

        if (item != null && item.getItem() instanceof IGoggles) return;

        eventDraw = true;

        player.setCurrentItemOrArmor(4, contactLenses);
        ForgeHooksClient.onDrawBlockHighlight(event.context, player, event.target, event.subID, event.currentItem, event.partialTicks);
        player.setCurrentItemOrArmor(4, item);

        eventDraw = false;

    }

    /*
    @SideOnly(Side.CLIENT)
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void blockHighlightPost(DrawBlockHighlightEvent event)
    {
    
    }*/

}
