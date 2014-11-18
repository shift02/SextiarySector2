package shift.sextiarysector.gui.tab;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TabManager {

	private static ArrayList<AbstractTab> tabList = new ArrayList<AbstractTab>();

    public static void registerTab (AbstractTab tab)
    {
        tabList.add(tab);
    }

    public static int getTabNumber(AbstractTab tab){

    	for(int i = 0;i<tabList.size();i++){
    		if(tabList.get(i).equals(tab)){
    			return i;
    		}
    	}
    	return 0;

    }

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

    	/*if(i.length>EntityPlayerManager.getCustomPlayerData(player).getSelectPage()+1){
    		f = true;
    	}else{
    		EntityPlayerManager.getCustomPlayerData(player).setSelectPage(i.length-1);;
    	}*/

		return f;
    }

    public static boolean isBackPage(EntityPlayer player){

    	AbstractTab[][] i = getTabList(player);

    	boolean f = false;

    	/*if(EntityPlayerManager.getCustomPlayerData(player).getSelectPage()>0){
    		f = true;
    	}else if(EntityPlayerManager.getCustomPlayerData(player).getSelectPage()<0){
    		EntityPlayerManager.getCustomPlayerData(player).setSelectPage(0);
    	}*/

		return f;
    }


}
