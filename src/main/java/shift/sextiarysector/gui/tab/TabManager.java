package shift.sextiarysector.gui.tab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import net.minecraftforge.client.event.GuiScreenEvent;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TabManager {

	//private static ArrayList<AbstractTab> tabList = new ArrayList<AbstractTab>();
	private static HashMap<Integer,AbstractTab> tabMap = new HashMap<Integer,AbstractTab>();
	private static int tabSize = 0;

	private static Minecraft mc = FMLClientHandler.instance().getClient();
	private static EntityPlayer getPlayer(){
		return FMLClientHandler.instance().getClient().thePlayer;
	}

	private static final String Select_Page = "selectpage";
	private static final String Selected_Button = "selectedbutton";

    public static void registerTab (AbstractTab tab)
    {
    	tabMap.put(tabSize, tab);
        tabSize++;
    }

    public static int getTabNumber(AbstractTab tab){

    	for(int i = 0;i<tabMap.size();i++){
    		if(tabMap.get(i).equals(tab)){
    			return i;
    		}
    	}
    	return 0;

    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void guiPostInit (GuiScreenEvent.InitGuiEvent.Post event)
    {

        if ((event.gui instanceof GuiInventory))
        {
            int xSize = 176;
            int ySize = 166;
            int guiLeft = (event.gui.width - xSize) / 2;
            int guiTop = (event.gui.height - ySize) / 2;

            updateTabValues(guiLeft, guiTop,event.buttonList, InventoryTabVanilla.class,false);
            //addTabsToList(event.buttonList);
        }
    }

    public static void updateTabValues (int cornerX, int cornerY,List buttonList, Class<? extends AbstractTab> selectedButton, boolean reset)
    {



    	if(reset){

    		GuiButton[] r = (GuiButton[]) buttonList.toArray(new GuiButton[0]);

    		buttonList.clear();

    		for(int i=0;i<r.length;i++){
    			if(!(r[i] instanceof TabButton) && !(r[i] instanceof GuiBackButton) && !(r[i] instanceof GuiNextButton)){
    				buttonList.add(r[i]);
    			}
    		}
    	}

        int count = 3;
        int pCount = getSelectPage();
        ArrayList<AbstractTab> tabs = getTabListFromPage(pCount);
        System.out.println("AAAAA"+tabs.size()+ " : " + buttonList.size());

        TabButton buttonV = new TabButton(tabMap.get(0));
    	buttonV.id = 2;
    	buttonV.xPosition = cornerX ;
    	buttonV.yPosition = cornerY - 28;
    	buttonV.enabled = !InventoryTabVanilla.class.equals(selectedButton);
    	buttonList.add(buttonV);


        for (int i = 0; i < tabs.size(); i++)
        {
            AbstractTab t = tabs.get(i);

            if (t.shouldAddToList())
            {
            	TabButton button = new TabButton(t);
            	button.id = count;
            	button.xPosition = cornerX + 32 + (count - 3) * 29;
            	button.yPosition = cornerY - 28;
            	button.enabled = !t.getClass().equals(selectedButton);
            	buttonList.add(button);
                count++;
            }
        }

        int xSize = 176;
        int ySize = 166;
        int tabCount = getUpdateTab().size();
        if (tabCount > 5)
        {
            buttonList.add(new GuiBackButton(8, cornerX -24 ,                       cornerY - 22, 20, 20,cornerX,cornerY,buttonList));
            buttonList.add(new GuiNextButton(9, cornerX + xSize - 20 + 24, cornerY - 22, 20, 20,cornerX,cornerY,buttonList));
        }

        setSelectedButton(selectedButton);

    }

    private static void setSelectedButton(Class<? extends AbstractTab> selectedButton){

    	for (int i = 0; i < tabMap.size(); i++)
        {
    		if(tabMap.get(i).getClass().equals(selectedButton)){
    			getPlayer().getEntityData().setInteger(Selected_Button, i);
    			return;
    		}
        }

    }

    public static AbstractTab getSelectedButton(){
    	return tabMap.get(getPlayer().getEntityData().getInteger(Selected_Button));
    }

    private static ArrayList<AbstractTab> getTabListFromPage(int i){

    	ArrayList<AbstractTab> tabs = new ArrayList<AbstractTab>();
    	ArrayList<AbstractTab> naw = getUpdateTab();
    	int j = i - 1;

    	if(i>getPageSize()){
    		setSelectPage(1);
    		j = 0;
    	}

    	for(int k=j*5; k<naw.size()&&k<(j*5+5); k++){
    		tabs.add(naw.get(k));
    	}

		return tabs;

    }

    @SideOnly(Side.CLIENT)
    private static ArrayList<AbstractTab> getUpdateTab()
    {
    	ArrayList<AbstractTab> tabs = new ArrayList<AbstractTab>();

    	for (int i = 1; i < tabMap.size(); i++)
        {
            AbstractTab t = tabMap.get(i);
            if (t.shouldAddToList())
            {
            	tabs.add(t);
            }
        }

    	return tabs;

    }

    public static int getPageSize(){
    	ArrayList<AbstractTab> naw = getUpdateTab();

    	return naw.size()%5==0 ? naw.size()/5 :(naw.size()/5) + 1;
    }

    public static int getSelectPage()
    {
    	int i = getPlayer().getEntityData().getInteger(Select_Page);
    	if(i==0){
    		setSelectPage(1);
    		return 1;
    	}

		return i;
    }

    public static void setSelectPage(int i){
    	getPlayer().getEntityData().setInteger(Select_Page, i);
    }

    public static void openInventoryGui ()
    {
        mc.thePlayer.sendQueue.addToSendQueue(new C0DPacketCloseWindow(mc.thePlayer.openContainer.windowId));
        GuiInventory inventory = new GuiInventory(mc.thePlayer);
        mc.displayGuiScreen(inventory);
    }

    /*
    @SideOnly(Side.CLIENT)
    public static void updateTab(EntityPlayer player)
    {
    	ArrayList<AbstractTab> tabs = new ArrayList<AbstractTab>();

    	for (int i = 1; i < tabList.size(); i++)
        {
            AbstractTab t = tabList.get(i);
            if (t.shouldAddToList(player))
            {
            	tabs.add(t);
            }
        }

    	//EntityPlayerManager.getCustomPlayerData(player).setTabList(tabs);

    }


    public static void updateTabValues (EntityPlayer player,int cornerX, int cornerY, Class<? extends AbstractTab> selectedButton)
    {
    	int count = 2;
    	AbstractTab[] tabs = getTabList(player)[getPageNumber(player,selectedButton)];
        for (int i = 0; tabs[i]!=null; i++)
        {
            	AbstractTab t = tabList.get(i);

                //t.id = count;
                //t.xPosition = cornerX + (count - 2) * 28;
               // t.yPosition = cornerY - 28;
                //t.enabled = !t.getClass().equals(selectedButton);
                //count++;


        }

    }

    @SideOnly(Side.CLIENT)
    private static AbstractTab[][] getTabList(EntityPlayer player){

    	updateTab(player);

    	ArrayList<AbstractTab> tabList = null;//EntityPlayerManager.getCustomPlayerData(player).getTabList();
    	int p = tabList.size()%5==0 ? 0 : 1;
    	AbstractTab[][] tabs = new AbstractTab[(tabList.size()/5)+p][5];

    	int page=0;
    	for(int i = 0;i<tabList.size();i++){

    		tabs[page][i%5] = tabList.get(i);

    		if(i%5==0 && i!=0){
    			page++;
    		}
    	}

		return tabs;
    }

    public static int getPageNumber(EntityPlayer player, Class<? extends AbstractTab> selectedButton){

    	AbstractTab[][] tabs = getTabList(player);
    	for(int i = 0;i<tabs.length;i++){
    		for(int j=0;j<tabs[i].length;++j){
    			if(selectedButton.equals(tabs[i][j])){
    				return i;
    			}
    		}
    	}
		return 0;

    }


    public static boolean isNextPage(EntityPlayer player){

    	AbstractTab[][] i = getTabList(player);

    	boolean f = false;

    	if(i.length>EntityPlayerManager.getCustomPlayerData(player).getSelectPage()+1){
    		f = true;
    	}else{
    		EntityPlayerManager.getCustomPlayerData(player).setSelectPage(i.length-1);;
    	}

		return f;
    }

    public static boolean isBackPage(EntityPlayer player){

    	AbstractTab[][] i = getTabList(player);

    	boolean f = false;

    	if(EntityPlayerManager.getCustomPlayerData(player).getSelectPage()>0){
    		f = true;
    	}else if(EntityPlayerManager.getCustomPlayerData(player).getSelectPage()<0){
    		EntityPlayerManager.getCustomPlayerData(player).setSelectPage(0);
    	}

		return f;
    }*/


}
