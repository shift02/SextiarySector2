package shift.sextiarysector.nei;

import static codechicken.nei.NEIClientUtils.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.GuiRecipe;

public abstract class AbstractFuelRecipeHandler extends AbstractSimpleRecipeHandler {

    public class CachedFuelRecipe extends CachedRecipe {
        public FuelPair fuel;

        public CachedFuelRecipe(FuelPair fuel) {
            this.fuel = fuel;
        }

        @Override
        public PositionedStack getIngredient() {
            return mfurnace.get(cycleticks / 48 % mfurnace.size()).ingred;
        }

        @Override
        public PositionedStack getResult() {
            return mfurnace.get(cycleticks / 48 % mfurnace.size()).result;
        }

        @Override
        public PositionedStack getOtherStack() {
            return fuel.stack;
        }
    }

    private ArrayList<SimpleMachinePair> mfurnace = new ArrayList<AbstractSimpleRecipeHandler.SimpleMachinePair>();

    public AbstractFuelRecipeHandler() {
        super();
        loadAllSmelting();
    }

    public String getRecipeName() {
        return I18n.format("nei." + getGuiRecipeName());
    }

    private void loadAllSmelting() {

        HashMap<String, ItemStack> recipes = (HashMap<String, ItemStack>) getRecipe().getOreList();
        HashMap<ItemStack, ItemStack> metarecipes = (HashMap<ItemStack, ItemStack>) getRecipe().getMetaList();

        for (Entry<String, ItemStack> recipe : recipes.entrySet()) {
            ItemStack item = recipe.getValue();
            if (OreDictionary.getOres(recipe.getKey()).size() >= 1) {
                mfurnace.add(new SimpleMachinePair(OreDictionary.getOres(recipe.getKey()), item));
            }

        }

        if (metarecipes == null) return;
        for (Entry<ItemStack, ItemStack> recipe : metarecipes.entrySet()) {
            ItemStack item = recipe.getValue();
            mfurnace.add(new SimpleMachinePair(recipe.getKey(), item));
        }
    }

    @Override
    public void loadCraftingRecipes(String outputId, Object... results) {
        if (outputId.equals(getFuelHandlerName()) && getClass() == getFuelHandlerClass())
            for (FuelPair fuel : afuels)
            arecipes.add(new CachedFuelRecipe(fuel));
    }

    public void loadUsageRecipes(ItemStack ingredient) {
        for (FuelPair fuel : afuels)
            if (fuel.stack.contains(ingredient))
                arecipes.add(new CachedFuelRecipe(fuel));
    }

    public String getOverlayIdentifier() {
        return getFuelHandlerName();
    }

    @Override
    public List<String> handleItemTooltip(GuiRecipe gui, ItemStack stack, List<String> currenttip, int recipe) {
        CachedFuelRecipe crecipe = (CachedFuelRecipe) arecipes.get(recipe);
        FuelPair fuel = crecipe.fuel;
        float burnTime = fuel.burnTime / 200F;

        if (gui.isMouseOver(fuel.stack, recipe) && burnTime < 1) {
            burnTime = 1F / burnTime;
            String s_time = Float.toString(burnTime);
            if (burnTime == Math.round(burnTime))
                s_time = Integer.toString((int) burnTime);

            currenttip.add(translate("recipe.fuel.required", s_time));
        } else if ((gui.isMouseOver(crecipe.getResult(), recipe) || gui.isMouseOver(crecipe.getIngredient(), recipe)) && burnTime > 1) {
            String s_time = Float.toString(burnTime);
            if (burnTime == Math.round(burnTime))
                s_time = Integer.toString((int) burnTime);

            currenttip.add(translate("recipe.fuel." + (gui.isMouseOver(crecipe.getResult(), recipe) ? "produced" : "processed"), s_time));
        }

        return currenttip;
    }

    abstract Class<? extends AbstractSimpleRecipeHandler> getFuelHandlerClass();

    @Override
    Class<? extends AbstractSimpleRecipeHandler> getHandlerClass() {
        return null;
    }

    @Override
    String getHandlerName() {
        return "";
    }

}
