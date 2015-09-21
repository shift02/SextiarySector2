package shift.sextiarysector.module;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import shift.sextiarysector.SextiarySector;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * This code was referenced to OreDictionary.class.
 * This code was referenced to CustomizeVanillaRecipe.class. by FluidityFoodstuffs
 * */
public class ModuleVanillaRecipe implements IModule {

    private static ModuleVanillaRecipe instance;

    private ModuleVanillaRecipe() {
    }

    public static ModuleVanillaRecipe getInstance() {
        if (instance == null) {
            instance = new ModuleVanillaRecipe();
        }
        return instance;
    }

    private static Map<ItemStack, String> replaceTable = new HashMap<ItemStack, String>();

    private static ArrayList<ItemStack> exclusions = new ArrayList<ItemStack>();

    @Override
    public void preInit(FMLPreInitializationEvent event) {

        replaceTable.put(new ItemStack(Blocks.wool), "blockWool");

        for (int i = 0; i < 16; i++) {
            exclusions.add(new ItemStack(Blocks.wool, 1, i));
        }

    }

    @Override
    public void load(FMLInitializationEvent event) {

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {

        List<IRecipe> targetRecipes = CraftingManager.getInstance().getRecipeList();
        List<IRecipe> addRecipes = new ArrayList<IRecipe>();
        List<IRecipe> addOreRecipes = new ArrayList<IRecipe>();
        List<IRecipe> addShapelessRecipes = new ArrayList<IRecipe>();
        List<IRecipe> addShapelessOreRecipes = new ArrayList<IRecipe>();

        ItemStack[] replaces = replaceTable.keySet().toArray(new ItemStack[replaceTable.keySet().size()]);

        int count = 0;

        for (Object obj : targetRecipes) {
            if (obj instanceof ShapedRecipes) {
                ShapedRecipes recipe = (ShapedRecipes) obj;
                ItemStack output = recipe.getRecipeOutput();
                if (output != null && containsMatch(false, exclusions.toArray(new ItemStack[exclusions.size()]), output)) {
                    continue;
                }

                if (containsMatch(true, recipe.recipeItems, replaces)) {
                    addRecipes.add(recipe);
                }
            } else if (obj instanceof ShapelessRecipes) {
                ShapelessRecipes recipe = (ShapelessRecipes) obj;
                ItemStack output = recipe.getRecipeOutput();
                if (output != null && containsMatch(false, exclusions.toArray(new ItemStack[exclusions.size()]), output)) {
                    continue;
                }

                if (containsMatch(true, (ItemStack[]) recipe.recipeItems.toArray(new ItemStack[recipe.recipeItems.size()]), replaces)) {
                    addShapelessRecipes.add(recipe);
                }
            }
            if (obj instanceof ShapedOreRecipe) {
                ShapedOreRecipe recipe = (ShapedOreRecipe) obj;
                ItemStack output = recipe.getRecipeOutput();
                if (output != null && containsMatch(false, exclusions.toArray(new ItemStack[exclusions.size()]), output)) {
                    continue;
                }

                ArrayList<ItemStack> check = new ArrayList<ItemStack>();
                for (Object object : recipe.getInput()) {
                    ItemStack item = null;

                    if (object instanceof ItemStack) {
                        item = (ItemStack) object;
                    } else if (object instanceof Item) {
                        item = new ItemStack((Item) object);
                    } else if (object instanceof Block) {
                        item = new ItemStack((Block) object, 1, OreDictionary.WILDCARD_VALUE);
                    }

                    if (item != null) {
                        check.add(item);
                    }
                }

                if (!check.isEmpty() && containsMatch(true, check.toArray((new ItemStack[check.size()])), replaces)) {
                    addOreRecipes.add(recipe);
                }
            } else if (obj instanceof ShapelessOreRecipe) {
                ShapelessOreRecipe recipe = (ShapelessOreRecipe) obj;
                ItemStack output = recipe.getRecipeOutput();
                if (output != null && containsMatch(false, exclusions.toArray(new ItemStack[exclusions.size()]), output)) {
                    continue;
                }

                ArrayList<ItemStack> check = new ArrayList<ItemStack>();
                for (Object object : recipe.getInput()) {
                    ItemStack item = null;

                    if (object instanceof ItemStack) {
                        item = (ItemStack) object;
                    } else if (object instanceof Item) {
                        item = new ItemStack((Item) object);
                    } else if (object instanceof Block) {
                        item = new ItemStack((Block) object, 1, OreDictionary.WILDCARD_VALUE);
                    }

                    if (item != null) {
                        check.add(item);
                    }
                }

                if (!check.isEmpty() && containsMatch(true, check.toArray((new ItemStack[check.size()])), replaces)) {
                    addShapelessOreRecipes.add(recipe);
                }
            }
        }

        for (Object obj : addRecipes) {
            if (obj instanceof ShapedRecipes) {
                registerCustomShapedRecipe((ShapedRecipes) obj);
                count++;
            }
        }

        for (Object obj : addShapelessRecipes) {
            if (obj instanceof ShapelessRecipes) {
                registerCustomShapelessRecipe((ShapelessRecipes) obj);
                count++;
            }
        }

        for (Object obj : addOreRecipes) {
            if (obj instanceof ShapedOreRecipe) {
                registerCustomShapedOreRecipe((ShapedOreRecipe) obj);
                count++;
            }
        }

        for (Object obj : addShapelessOreRecipes) {
            if (obj instanceof ShapelessOreRecipe) {
                registerCustomShapelessOreRecipe((ShapelessOreRecipe) obj);
                count++;
            }
        }

        SextiarySector.Log.info("Replaced " + count + " recipes.");

    }

    //Shaped
    private static void registerCustomShapedRecipe(ShapedRecipes recipe) {
        ItemStack output = recipe.getRecipeOutput();
        int x = recipe.recipeWidth;
        int y = recipe.recipeHeight;
        ItemStack[] items = recipe.recipeItems;

        if (x * y > 9) return;

        ArrayList<Object> inputArray = new ArrayList<Object>();

        //3x3より大きなレシピには全く対応していない
        String[] s = { "A", "B", "C", "D", "E", "F", "G", "H", "I" };
        Character[] c = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I' };

        String[] returnArray = new String[y];

        //クラフト欄指定用のString[]の生成
        for (int i = 1; i < y + 1; i++) {
            String f = "";
            for (int k = 1; k < x + 1; k++) {
                f += items[k + ((i - 1) * x) - 1] == null ? " " : s[k + ((i - 1) * x) - 1];
            }
            returnArray[i - 1] = f;
        }

        for (int i = 0; i < returnArray.length; i++) {
            inputArray.add(returnArray[i]);
        }

        //ItemStack配列部分の追加
        //Character、ItemStack/String の順に追加していけば良い。空欄はnullのまま。
        for (int i = 0; i < x * y; i++) {
            if (items[i] != null) {
                String sign = s[i];
                ItemStack item = items[i];
                boolean b = false;

                for (Entry<ItemStack, String> entry : replaceTable.entrySet()) {
                    if (itemMatches(entry.getKey(), item, true)) {
                        String oreName = entry.getValue();

                        inputArray.add(c[i]);
                        inputArray.add(entry.getValue());
                        b = true;
                    }
                }

                if (!b) {
                    inputArray.add(c[i]);
                    inputArray.add(item);
                }
            }
        }

        Object[] newInputs = inputArray.toArray();
        GameRegistry.addRecipe(new ShapedOreRecipe(output, newInputs));
        SextiarySector.Log.info("Customized ShapdRecipe : " + inputArray.toString());
    }

    //Shapeless
    private static void registerCustomShapelessRecipe(ShapelessRecipes recipe) {
        ItemStack output = recipe.getRecipeOutput();
        List<ItemStack> items = recipe.recipeItems;
        ArrayList<Object> inputs = new ArrayList<Object>();

        for (ItemStack item : items) {
            boolean b = false;
            if (item != null) {
                for (Entry<ItemStack, String> entry : replaceTable.entrySet()) {
                    if (itemMatches(entry.getKey(), item, true)) {
                        String oreName = entry.getValue();
                        inputs.add(oreName);
                        b = true;
                    }
                }

                if (!b) {
                    inputs.add(item);
                }
            }
        }

        Object[] newInputs = inputs.toArray();
        GameRegistry.addRecipe(new ShapelessOreRecipe(output, newInputs));
        SextiarySector.Log.info("Customized ShapelessRecipe : " + inputs.toString());

    }

    //Shaped-Ore
    private static void registerCustomShapedOreRecipe(ShapedOreRecipe recipe) {
        ItemStack output = recipe.getRecipeOutput();
        Object[] objects = recipe.getInput();
        ArrayList<Object> inputs = new ArrayList<Object>();

        int x = 0;
        int y = 0;

        try {
            Field fieldW = recipe.getClass().getDeclaredField("width");
            fieldW.setAccessible(true);
            x = fieldW.getInt(recipe);

            Field fieldH = recipe.getClass().getDeclaredField("height");
            fieldH.setAccessible(true);
            y = fieldH.getInt(recipe);
        } catch (Exception e) {
            return;
        }

        if (x * y == 0 || x * y > 9) return;

        //3x3より大きなレシピには全く対応していない
        String[] s = { "A", "B", "C", "D", "E", "F", "G", "H", "I" };
        Character[] c = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I' };

        String[] returnArray = new String[y];

        //クラフト欄指定用のString[]の生成
        for (int i = 1; i < y + 1; i++) {
            String f = "";
            for (int k = 1; k < x + 1; k++) {
                f += objects[k + ((i - 1) * x) - 1] == null ? " " : s[k + ((i - 1) * x) - 1];
            }
            returnArray[i - 1] = f;
        }

        for (int i = 0; i < returnArray.length; i++) {
            inputs.add(returnArray[i]);
        }

        //item
        for (int i = 0; i < x * y; i++) {
            boolean b = false;
            ItemStack item = null;
            Object obj = objects[i];
            if (obj == null) continue;

            if (obj instanceof ItemStack) {
                item = (ItemStack) obj;
            } else if (obj instanceof Item) {
                item = new ItemStack((Item) obj);
            } else if (obj instanceof Block) {
                item = new ItemStack((Block) obj, 1, OreDictionary.WILDCARD_VALUE);
            } else if (obj instanceof ArrayList) {
                if ((ArrayList<ItemStack>) obj != null && !((ArrayList<ItemStack>) obj).isEmpty()) {
                    ArrayList<ItemStack> list = (ArrayList<ItemStack>) obj;
                    for (ItemStack oreItem : list) {
                        if (oreItem == null || OreDictionary.getOreIDs(oreItem).length == 0) continue;
                        int[] id = OreDictionary.getOreIDs(oreItem);
                        for (int j = 0; j < id.length; j++) {
                            String str = OreDictionary.getOreName(OreDictionary.getOreIDs(oreItem)[j]);
                            if (str != null) {
                                inputs.add(c[i]);
                                inputs.add(str);
                                b = true;
                                break;
                            }
                        }
                        if (b) break;
                    }
                }

                if (!b) {
                    inputs.add(c[i]);
                    inputs.add("Unknown");
                    b = true;
                }
            }

            if (item != null) {
                for (Entry<ItemStack, String> entry : replaceTable.entrySet()) {
                    if (itemMatches(entry.getKey(), item, true)) {
                        String oreName = entry.getValue();
                        inputs.add(c[i]);
                        inputs.add(oreName);
                        b = true;
                    }
                }

                if (!b) {
                    inputs.add(c[i]);
                    inputs.add(obj);
                }
            }
        }

        Object[] newInputs = inputs.toArray();
        GameRegistry.addRecipe(new ShapedOreRecipe(output, newInputs));
        SextiarySector.Log.info("Customized ShapedOreRecipe : " + inputs.toString());

    }

    //Shapeless-Ore
    private static void registerCustomShapelessOreRecipe(ShapelessOreRecipe recipe) {
        ItemStack output = recipe.getRecipeOutput();
        ArrayList<Object> objects = recipe.getInput();
        ArrayList<Object> inputs = new ArrayList<Object>();

        for (Object obj : objects) {
            boolean b = false;
            ItemStack item = null;

            if (obj instanceof ItemStack) {
                item = (ItemStack) obj;
            } else if (obj instanceof Item) {
                item = new ItemStack((Item) obj);
            } else if (obj instanceof Block) {
                item = new ItemStack((Block) obj, 1, OreDictionary.WILDCARD_VALUE);
            } else if (obj instanceof ArrayList) {
                if ((ArrayList<ItemStack>) obj != null && !((ArrayList<ItemStack>) obj).isEmpty()) {
                    ArrayList<ItemStack> list = (ArrayList<ItemStack>) obj;
                    for (ItemStack oreItem : list) {
                        if (oreItem == null || OreDictionary.getOreIDs(oreItem).length == 0) continue;
                        int[] id = OreDictionary.getOreIDs(oreItem);
                        for (int j = 0; j < id.length; j++) {
                            String str = OreDictionary.getOreName(OreDictionary.getOreIDs(oreItem)[j]);
                            if (str != null) {
                                inputs.add(str);
                                b = true;
                                break;
                            }
                        }
                        if (b) break;
                    }
                }

                if (!b) {
                    inputs.add("Unknown");
                    b = true;
                }
            }

            if (item != null) {
                for (Entry<ItemStack, String> entry : replaceTable.entrySet()) {
                    if (itemMatches(entry.getKey(), item, true)) {
                        String oreName = entry.getValue();
                        inputs.add(oreName);
                        b = true;
                    }
                }
            }

            if (!b) {
                inputs.add(obj);
            }
        }

        Object[] newInputs = inputs.toArray();
        GameRegistry.addRecipe(new ShapelessOreRecipe(output, newInputs));
        SextiarySector.Log.info("Customized ShapelessOreRecipe : " + inputs.toString());

    }

    private static boolean containsMatch(boolean strict, ItemStack[] inputs, ItemStack... targets) {
        for (ItemStack input : inputs) {
            for (ItemStack target : targets) {
                if (itemMatches(target, input, strict)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean itemMatches(ItemStack target, ItemStack input, boolean strict) {
        if (input == null && target != null || input != null && target == null) {
            return false;
        }
        return (target.getItem() == input.getItem() && ((target.getItemDamage() == OreDictionary.WILDCARD_VALUE && !strict) || target.getItemDamage() == input.getItemDamage()));
    }

}
