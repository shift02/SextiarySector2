/*
* 作成者: Shift02
* 作成日: 2016/03/11 - 10:43:18
*/
package shift.sextiarysector.recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class RecipeAnvil {

    protected final HashMap<Object[], ItemStack> oreSimpleMachineList = new HashMap<Object[], ItemStack>();

    public void add(Object itemStackLeft, Object itemStackRight, boolean reversal, ItemStack ItemStackOutput) {
        oreSimpleMachineList.put(new Object[] { itemStackLeft, itemStackRight, new Boolean(reversal) }, ItemStackOutput);
    }

    public ItemStack getResult(ItemStack itemStackLeft, ItemStack itemStackRight) {

        if (itemStackLeft == null && itemStackRight == null) {
            return null;
        }
        for (Entry<Object[], ItemStack> entry : oreSimpleMachineList.entrySet()) {

            Object[] key = entry.getKey();

            if (key[0] instanceof String && key[1] instanceof String) {

                //両方共文字列
                if (this.checkItem((String) key[0], itemStackLeft) && (this.checkItem((String) key[1], itemStackRight))) {
                    return entry.getValue().copy();
                } else if (((Boolean) key[2]).booleanValue() && this.checkItem((String) key[1], itemStackLeft) && (this.checkItem((String) key[0], itemStackRight))) {
                    return entry.getValue().copy();
                }

            } else if (key[0] instanceof ItemStack && key[1] instanceof String) {

                //左だけアイテム
                if (this.checkItem((ItemStack) key[0], itemStackLeft) && (this.checkItem((String) key[1], itemStackRight))) {
                    return entry.getValue().copy();
                } else if (((Boolean) key[2]).booleanValue() && this.checkItem((String) key[1], itemStackLeft) && (this.checkItem((ItemStack) key[0], itemStackRight))) {
                    return entry.getValue().copy();
                }

            } else if (key[0] instanceof String && key[1] instanceof ItemStack) {

                //右だけアイテム
                if (this.checkItem((String) key[0], itemStackLeft) && (this.checkItem((ItemStack) key[1], itemStackRight))) {
                    return entry.getValue().copy();
                } else if (((Boolean) key[2]).booleanValue() && this.checkItem((ItemStack) key[1], itemStackLeft) && (this.checkItem((String) key[0], itemStackRight))) {
                    return entry.getValue().copy();
                }

            } else if (key[0] instanceof ItemStack && key[1] instanceof ItemStack) {

                //両方アイテム
                if (this.checkItem((ItemStack) key[0], itemStackLeft) && (this.checkItem((ItemStack) key[1], itemStackRight))) {
                    return entry.getValue().copy();
                } else if (((Boolean) key[2]).booleanValue() && this.checkItem((ItemStack) key[1], itemStackLeft) && (this.checkItem((ItemStack) key[0], itemStackRight))) {
                    return entry.getValue().copy();
                }

            }

        }

        return null;

    }

    private boolean checkItem(ItemStack p_151397_1_, ItemStack p_151397_2_) {

        return p_151397_2_.getItem() == p_151397_1_.getItem() &&
                (p_151397_2_.getItemDamage() == 32767 || p_151397_2_.getItemDamage() == p_151397_1_.getItemDamage());

    }

    private boolean checkItem(String key, ItemStack item) {

        ArrayList<ItemStack> items = OreDictionary.getOres(key);
        for (int i = 0; i < items.size(); i++) {
            if (checkItem(item, items.get(i))) {
                return true;
            }

        }

        return false;

    }

    public Map<Object[], ItemStack> getList() {
        return oreSimpleMachineList;
    }

}
