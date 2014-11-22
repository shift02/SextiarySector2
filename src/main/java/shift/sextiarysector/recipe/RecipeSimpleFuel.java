package shift.sextiarysector.recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class RecipeSimpleFuel {

	protected final HashMap<ItemStack, Integer> metaSimpleFuelList = new HashMap<ItemStack, Integer>();
	protected final HashMap<String, Integer> oreSimpleFuelList = new HashMap<String, Integer>();

	public void add(ItemStack par1ItemStack, int par2ItemStack)
    {
        metaSimpleFuelList.put(par1ItemStack, par2ItemStack);
    }

	public void add(String par1String, int par2ItemStack)
    {
		oreSimpleFuelList.put(par1String, par2ItemStack);
    }

	public int getResult(ItemStack item)
    {
        if (item == null)
        {
            return 0;
        }
        for (String key : oreSimpleFuelList.keySet()) {
        	ArrayList<ItemStack> items = OreDictionary.getOres(key);
        	for(int i = 0; i< items.size() ; i++){
        		if(checkItem(item,items.get(i))){
        			return oreSimpleFuelList.get(key);
        		}
        	}
        }

        Iterator iterator = this.metaSimpleFuelList.entrySet().iterator();
        Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return 0;
            }

            entry = (Entry)iterator.next();
        }
        while (!this.checkItem(item, (ItemStack)entry.getKey()));

        return (Integer) entry.getValue();

        //return metaSimpleMachineList.get( (Arrays.asList( item.itemID, item.getItemDamage() ) ));
    }

	private boolean checkItem(ItemStack p_151397_1_, ItemStack p_151397_2_)
    {
        return p_151397_2_.getItem() == p_151397_1_.getItem() && (p_151397_2_.getItemDamage() == 32767 || p_151397_2_.getItemDamage() == p_151397_1_.getItemDamage());
    }

	public Map<ItemStack, Integer> getMetaList()
    {
        return metaSimpleFuelList;
    }

	public Map<String, Integer> getOreList()
    {
        return oreSimpleFuelList;
    }

}
