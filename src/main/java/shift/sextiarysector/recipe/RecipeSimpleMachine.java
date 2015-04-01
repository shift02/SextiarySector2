package shift.sextiarysector.recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import shift.sextiarysector.api.recipe.INormalRecipe;

public class RecipeSimpleMachine implements INormalRecipe {

	protected final HashMap<ItemStack, ItemStack> metaSimpleMachineList = new HashMap<ItemStack, ItemStack>();
	protected final HashMap<String, ItemStack> oreSimpleMachineList = new HashMap<String, ItemStack>();

	@Override
	public void add(ItemStack par1ItemStack, ItemStack par2ItemStack)
	{
		metaSimpleMachineList.put(par1ItemStack, par2ItemStack);
	}

	@Override
	public void add(String par1String, ItemStack par2ItemStack)
	{
		oreSimpleMachineList.put(par1String, par2ItemStack);
	}

	@Override
	public ItemStack getResult(ItemStack item)
	{
		if (item == null)
		{
			return null;
		}
		for (String key : oreSimpleMachineList.keySet()) {
			ArrayList<ItemStack> items = OreDictionary.getOres(key);
			for (int i = 0; i < items.size(); i++) {
				if (checkItem(item, items.get(i))) {
					return oreSimpleMachineList.get(key).copy();
				}
			}
		}

		Iterator iterator = this.metaSimpleMachineList.entrySet().iterator();
		Entry entry;

		do
		{
			if (!iterator.hasNext())
			{
				return null;
			}

			entry = (Entry) iterator.next();
		} while (!this.checkItem(item, (ItemStack) entry.getKey()));

		return ((ItemStack) entry.getValue()).copy();

		//return metaSimpleMachineList.get( (Arrays.asList( item.itemID, item.getItemDamage() ) ));
	}

	private boolean checkItem(ItemStack p_151397_1_, ItemStack p_151397_2_)
	{
		return p_151397_2_.getItem() == p_151397_1_.getItem() && (p_151397_2_.getItemDamage() == 32767 || p_151397_2_.getItemDamage() == p_151397_1_.getItemDamage());
	}

	@Override
	public Map<ItemStack, ItemStack> getMetaList()
	{
		return metaSimpleMachineList;
	}

	@Override
	public Map<String, ItemStack> getOreList()
	{
		return oreSimpleMachineList;
	}

}
