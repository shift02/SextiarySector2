package shift.sextiarysector.plugin;

import java.lang.reflect.Field;
import java.util.ArrayList;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import shift.sextiarysector.gui.tab.TabManager;

public class PluginTconTab implements IPlugin {

    @SideOnly(Side.CLIENT)
    private static TconTab openTab;

    @SideOnly(Side.CLIENT)
    private static Minecraft mc;

    @Override
    public String getModName() {
        return "TConstruct";
    }

    @Override
    public void prePlugin(FMLPreInitializationEvent event) {
        // TODO 自動生成されたメソッド・スタブ

    }

    @Override
    public void preClientPlugin(FMLPreInitializationEvent event) {

        MinecraftForge.EVENT_BUS.register(this);

        mc = FMLClientHandler.instance().getClient();

    }

    @Override
    public void initPlugin(FMLInitializationEvent event) {
        // TODO 自動生成されたメソッド・スタブ

    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void guiPostInit(GuiScreenEvent.InitGuiEvent.Post event) {

        if (openTab != null) {

            int xSize = 176;
            int ySize = 166;
            int guiLeft = (event.gui.width - xSize) / 2;
            int guiTop = (event.gui.height - ySize) / 2;

            if (TabManager.hasPotion()) {
                guiLeft = 160 + (event.gui.width - xSize - 200) / 2;
            }

            TabManager.updateTabValues(guiLeft, guiTop, event.buttonList, openTab, false);

            openTab = null;

        }

    }

    @Override
    public void postPlugin(FMLPostInitializationEvent event) {

        if (event.getSide().isClient()) clientPost();

    }

    @SideOnly(Side.CLIENT)
    public void clientPost() {

        ArrayList<tconstruct.client.tabs.AbstractTab> tabs = tconstruct.client.tabs.TabRegistry.getTabList();

        if (tabs.size() < 2) return;

        for (int i = 1; i < tabs.size(); i++) {
            TabManager.registerTab(new TconTab(tabs.get(i)));
        }

        tabs.clear();

    }

    public class TconTab extends shift.sextiarysector.gui.tab.AbstractTab {

        tconstruct.client.tabs.AbstractTab tab;
        ItemStack item;
        String tabName;

        public TconTab(tconstruct.client.tabs.AbstractTab tab) {

            this.tab = tab;
            this.tabName = tab.getClass().getSimpleName();
            if (this.tabName.indexOf("InventoryTab") != -1) {
                this.tabName = this.tabName.replaceFirst("InventoryTab", "");
            }

            try {

                Class<tconstruct.client.tabs.AbstractTab> c = tconstruct.client.tabs.AbstractTab.class;
                Field f = c.getDeclaredField("renderStack");
                f.setAccessible(true);
                this.item = (ItemStack) f.get(this.tab);

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onTabClicked() {

            openTab = this;
            tab.onTabClicked();

        }

        @Override
        public ItemStack getItemStack() {
            return this.item;
        }

        @Override
        public String getTabName() {
            return this.tabName;
        }

        @Override
        public boolean shouldAddToList() {
            return tab.shouldAddToList();
        }

    }

}
