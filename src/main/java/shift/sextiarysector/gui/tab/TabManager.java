package shift.sextiarysector.gui.tab;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;

public class TabManager {

    private TabManager() {
    }

    private static HashMap<Integer, AbstractTab> tabMap = new HashMap<Integer, AbstractTab>();
    private static int tabSize = 0;

    private static Minecraft mc = FMLClientHandler.instance().getClient();

    private static EntityPlayer getPlayer() {
        return FMLClientHandler.instance().getClient().thePlayer;
    }

    private static final String Select_Page = "selectpage";
    private static final String Selected_Button = "selectedbutton";

    private static boolean init;
    private static boolean initEvent;
    private static AbstractTab vanilla;

    public static WeakReference<List> buttons;

    public static void initTabManager() {

        if (!init) {
            init = true;
            vanilla = new InventoryTabVanilla();
            registerTab(vanilla);
        }

        initEvent();

    }

    public static void initEvent() {

        if (!initEvent) {
            initEvent = true;
            MinecraftForge.EVENT_BUS.register(new TabManager());
        }

    }

    public static void registerTab(AbstractTab tab) {
        if (!init) initTabManager();
        tabMap.put(tabSize, tab);
        tabSize++;
    }

    public static int getTabNumber(AbstractTab tab) {

        for (int i = 0; i < tabMap.size(); i++) {
            if (tabMap.get(i).equals(tab)) {
                return i;
            }
        }
        return 0;

    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void guiPreInit(GuiScreenEvent.InitGuiEvent.Pre event) {

        buttons = null;//event.buttonList;

    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void guiPostInit(GuiScreenEvent.InitGuiEvent.Post event) {

        if ((event.gui instanceof GuiInventory)) {

            int xSize = 176;
            int ySize = 166;
            int guiLeft = (event.gui.width - xSize) / 2;
            int guiTop = (event.gui.height - ySize) / 2;

            if (this.hasPotion()) {
                guiLeft = 160 + (event.gui.width - xSize - 200) / 2;
            }

            updateTabValues(guiLeft, guiTop, event.buttonList, vanilla, false);

        }
    }

    public static boolean hasPotion() {
        if (mc.thePlayer.getActivePotionEffects().isEmpty()) return false;

        return isNotNEI();

        //if (!Loader.isModLoaded("NotEnoughItems")) return true;

        //try
        //{
        //	Class<?> c = Class.forName("codechicken.nei.NEIClientConfig");
        //	Object hidden = c.getMethod("isHidden").invoke(null);
        //	Object enabled = c.getMethod("isEnabled").invoke(null);
        //
        //	if (hidden != null && hidden instanceof Boolean && enabled != null && enabled instanceof Boolean)
        //	{
        //		if ((Boolean) hidden || !((Boolean) enabled))
        //		{
        //			return true;
        //		}
        //	}

        ////} catch (Exception e)
        //}

        //return false;

    }

    private static boolean isNotNEI() {

        if (!Loader.isModLoaded("NotEnoughItems")) return true;

        try {
            Class<?> c = Class.forName("codechicken.nei.NEIClientConfig");
            Object hidden = c.getMethod("isHidden").invoke(null);
            Object enabled = c.getMethod("isEnabled").invoke(null);

            if (hidden != null && hidden instanceof Boolean && enabled != null && enabled instanceof Boolean) {
                if ((Boolean) hidden || !((Boolean) enabled)) {
                    return true;
                }
            }

        } catch (Exception e) {
        }

        return false;

    }

    /**
     * GUIにタブを付けて表示
     * @param cornerX
     * @param cornerY
     * @param buttonList ボタンList
     * @param selectedButton 選択しているタブ
     * @param reset 次へ and 戻るボタンを消す
     */
    public static void updateTabValues(int cornerX, int cornerY, List buttonList, AbstractTab selectedButton, boolean reset) {

        if (reset) {

            GuiButton[] r = (GuiButton[]) buttonList.toArray(new GuiButton[0]);

            buttonList.clear();

            for (int i = 0; i < r.length; i++) {
                if (!(r[i] instanceof TabButton) && !(r[i] instanceof GuiBackButton) && !(r[i] instanceof GuiNextButton)) {
                    buttonList.add(r[i]);
                }
            }
        }

        if (!reset) {
            buttons = new WeakReference<List>(buttonList);
        }

        int count = 3;
        int pCount = getSelectPage();
        ArrayList<AbstractTab> tabs = getTabListFromPage(pCount);

        TabButton buttonV = new TabButton(tabMap.get(0));
        buttonV.id = 2;
        buttonV.xPosition = cornerX;
        buttonV.yPosition = cornerY - 28;
        buttonV.enabled = !tabMap.get(0).equals(selectedButton);
        buttonList.add(buttonV);

        for (int i = 0; i < tabs.size(); i++) {
            AbstractTab t = tabs.get(i);

            if (t.shouldAddToList()) {
                TabButton button = new TabButton(t);
                button.id = count;
                button.xPosition = cornerX + 32 + (count - 3) * 29;
                button.yPosition = cornerY - 28;
                button.enabled = !t.equals(selectedButton);
                buttonList.add(button);
                count++;
            }
        }

        int xSize = 176;
        int ySize = 166;
        int tabCount = getUpdateTab().size();
        if (tabCount > 5) {
            buttonList.add(new GuiBackButton(8, cornerX - 24, cornerY - 22, 20, 20, cornerX, cornerY, buttonList));
            buttonList.add(new GuiNextButton(9, cornerX + xSize - 20 + 24, cornerY - 22, 20, 20, cornerX, cornerY, buttonList));
        }

        setSelectedButton(selectedButton);

    }

    @SideOnly(Side.CLIENT)
    private static void setSelectedButton(AbstractTab selectedButton) {

        for (int i = 0; i < tabMap.size(); i++) {
            if (tabMap.get(i).equals(selectedButton)) {
                getPlayer().getEntityData().setInteger(Selected_Button, i);
                return;
            }
        }

    }

    @SideOnly(Side.CLIENT)
    public static AbstractTab getSelectedButton() {
        return tabMap.get(getPlayer().getEntityData().getInteger(Selected_Button));
    }

    @SideOnly(Side.CLIENT)
    private static ArrayList<AbstractTab> getTabListFromPage(int i) {

        ArrayList<AbstractTab> tabs = new ArrayList<AbstractTab>();
        ArrayList<AbstractTab> now = getUpdateTab();
        int j = i - 1;

        if (i > getPageSize()) {
            setSelectPage(1);
            j = 0;
        }

        for (int k = j * 5; k < now.size() && k < (j * 5 + 5); k++) {
            tabs.add(now.get(k));
        }

        return tabs;

    }

    @SideOnly(Side.CLIENT)
    private static ArrayList<AbstractTab> getUpdateTab() {
        ArrayList<AbstractTab> tabs = new ArrayList<AbstractTab>();

        for (int i = 1; i < tabMap.size(); i++) {
            AbstractTab t = tabMap.get(i);
            if (t.shouldAddToList()) {
                tabs.add(t);
            }
        }

        return tabs;

    }

    public static int getPageSize() {
        ArrayList<AbstractTab> naw = getUpdateTab();

        return naw.size() % 5 == 0 ? naw.size() / 5 : (naw.size() / 5) + 1;
    }

    public static int getSelectPage() {
        int i = getPlayer().getEntityData().getInteger(Select_Page);
        if (i == 0) {
            setSelectPage(1);
            return 1;
        }

        return i;
    }

    public static void setSelectPage(int i) {
        getPlayer().getEntityData().setInteger(Select_Page, i);
    }

    public static void openInventoryGui() {
        mc.thePlayer.sendQueue.addToSendQueue(new C0DPacketCloseWindow(mc.thePlayer.openContainer.windowId));
        GuiInventory inventory = new GuiInventory(mc.thePlayer);
        mc.displayGuiScreen(inventory);
    }

}
