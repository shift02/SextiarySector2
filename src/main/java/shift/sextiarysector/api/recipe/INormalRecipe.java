package shift.sextiarysector.api.recipe;

import java.util.Map;

import net.minecraft.item.ItemStack;

/**
 *
 * 通常の加工レシピ用Interface
 *
 * @author Shift02
 *
 */
public interface INormalRecipe {

    /**
     * レシピの追加
     *
     * @param material 材料 (ItemStack)
     * @param result 完成品
     */
    public void add(ItemStack material, ItemStack result);

    /**
     * レシピの追加
     *
     * @param oreDictionary 材料 (String)
     * @param result 完成品
     */
    public void add(String oreDictionary, ItemStack result);

    /**
     * 材料から完成品を取得する
     *
     * @param material 材料
     * @return 完成品  ない場合はnullが返る
     */
    public ItemStack getResult(ItemStack material);

    /**
     * レシピの管理リスト
     *
     * @return ItemStackで管理しているリスト
     */
    public Map<ItemStack, ItemStack> getMetaList();

    /**
     * レシピの管理リスト
     *
     * @return OreDictionaryで管理しているリスト
     */
    public Map<String, ItemStack> getOreList();

}
