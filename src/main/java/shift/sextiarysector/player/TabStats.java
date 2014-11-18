package shift.sextiarysector.player;

import java.util.ArrayList;

import shift.sextiarysector.gui.tab.AbstractTab;

public class TabStats {

	ArrayList<AbstractTab> tabList = new ArrayList<AbstractTab>();

	public int selectPage = 0;

	public void setTabList(ArrayList<AbstractTab> tabList){
		this.tabList = tabList;
	}

	public ArrayList<AbstractTab> getTabList(){
		return this.tabList;
	}

}
