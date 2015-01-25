package shift.sextiarysector.nei;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import shift.sextiarysector.recipe.RecipeSimpleMachine;
import codechicken.nei.ItemList;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public abstract class AbstractSimpleRecipeHandler  extends TemplateSSRecipeHandler{

	public class SimpleMachinePair extends CachedRecipe
    {
        public SimpleMachinePair(ItemStack ingred, ItemStack result)
        {
            ingred.stackSize = 1;
            this.ingred = new PositionedStack(ingred, 51, 6);
            this.result = new PositionedStack(result, 111, 24);
        }

        public SimpleMachinePair(List<ItemStack> ingred, ItemStack result)
        {
        	for(int i = 0;i<ingred.size();i++){
        		ingred.get(i).stackSize = 1;
        	}
            //ingred.stackSize = 1;
            this.ingred = new PositionedStack(ingred, 51, 6);
            this.result = new PositionedStack(result, 111, 24);
        }

        @Override
		public PositionedStack getIngredient()
        {
            int cycle = cycleticks / 48;

            this.randomRenderPermutation(ingred, cycleticks / 20);

            return ingred;
        }

        @Override
		public PositionedStack getResult()
        {
            return result;
        }

        public PositionedStack getOtherStack() {
            return afuels.get((cycleticks / 48) % afuels.size()).stack;
        }

        PositionedStack ingred;
        PositionedStack result;
    }

	public static class FuelPair
    {
        public FuelPair(ItemStack ingred, int burnTime) {
            this.stack = new PositionedStack(ingred, 51, 42, false);
            this.burnTime = burnTime;
        }

        public PositionedStack stack;
        public int burnTime;
    }

	public static ArrayList<FuelPair> afuels;

	public PositionedStack getResult() {
	    return null;
	  }

	@Override
	  public String getOverlayIdentifier() {
	    return getHandlerName();
	  }

	@Override
	public void loadTransferRects() {
		transferRects.add(new RecipeTransferRect(new Rectangle(50, 23, 18, 18), getFuelHandlerName()));
	    transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(74, 23, 24, 18), getHandlerName()));
	}

	@Override
    public TemplateRecipeHandler newInstance() {
        if (afuels == null)
            findFuels();
        return super.newInstance();
    }

	@Override
    public void loadCraftingRecipes(String outputId, Object... results)
    {
        if(outputId.equals(getHandlerName()) && getClass() == getHandlerClass())//don't want subclasses getting a hold of this
        {
            HashMap<String, ItemStack> recipes = (HashMap<String, ItemStack>) getRecipe().getOreList();
            HashMap<ItemStack, ItemStack> metarecipes = (HashMap<ItemStack, ItemStack>) getRecipe().getMetaList();


            for(Entry<String, ItemStack> recipe : recipes.entrySet())
            {
                ItemStack item = recipe.getValue();
                if(OreDictionary.getOres(recipe.getKey()).size()>=1){
                	arecipes.add(new SimpleMachinePair(OreDictionary.getOres(recipe.getKey()), item));
                }

            }

            if(metarecipes == null)return;
            for(Entry<ItemStack, ItemStack> recipe : metarecipes.entrySet())
            {
                ItemStack item = recipe.getValue();
                arecipes.add(new SimpleMachinePair(recipe.getKey(), item));
            }
        }
        else
        {
            super.loadCraftingRecipes(outputId, results);
        }
    }

	@Override
    public void loadCraftingRecipes(ItemStack result)
    {

		HashMap<String, ItemStack> recipes = (HashMap<String, ItemStack>) getRecipe().getOreList();
        HashMap<ItemStack, ItemStack> metarecipes = (HashMap<ItemStack, ItemStack>) getRecipe().getMetaList();

        for(Entry<String, ItemStack> recipe : recipes.entrySet())
        {
            ItemStack item = recipe.getValue();
            if(NEIServerUtils.areStacksSameType(item, result))
            {
            	if(OreDictionary.getOres(recipe.getKey()).size()>=1){
            		arecipes.add(new SimpleMachinePair(OreDictionary.getOres(recipe.getKey()), item));
            	}

            }
        }

        if(metarecipes == null)return;
        for(Entry<ItemStack, ItemStack> recipe : metarecipes.entrySet())
        {
            ItemStack item = recipe.getValue();
            if(NEIServerUtils.areStacksSameType(item, result))
            {
                arecipes.add(new SimpleMachinePair(recipe.getKey(), item));
            }
        }
    }

	@Override
    public void loadUsageRecipes(String inputId, Object... ingredients) {
        if (inputId.equals(getFuelHandlerName()) && getClass() == getHandlerClass())//don't want subclasses getting a hold of this
            loadCraftingRecipes(getHandlerName());
        else
            super.loadUsageRecipes(inputId, ingredients);
    }

	@Override
    public void loadUsageRecipes(ItemStack ingredient)
    {

		HashMap<String, ItemStack> recipes = (HashMap<String, ItemStack>) getRecipe().getOreList();
        HashMap<ItemStack, ItemStack> metarecipes = (HashMap<ItemStack, ItemStack>) getRecipe().getMetaList();

        for(Entry<String, ItemStack> recipe : recipes.entrySet())
        {
            ItemStack item = recipe.getValue();
            ArrayList<ItemStack> items = OreDictionary.getOres(recipe.getKey());
        	for(int i = 0; i< items.size() ; i++){
        		if(this.checkItem(items.get(i),ingredient)){
        			arecipes.add(new SimpleMachinePair(items, item));
        			break;
        		}
        	}
        }

        if(metarecipes == null)return;
        for(Entry<ItemStack, ItemStack> recipe : metarecipes.entrySet())
        {
            ItemStack item = recipe.getValue();
            //if(ingredient.itemID == recipe.getKey().get(0) && ingredient.getItemDamage() == recipe.getKey().get(1))
            if(this.checkItem(ingredient, recipe.getKey()))
            {
                arecipes.add(new SimpleMachinePair(ingredient, item));
            }
        }
    }

	private boolean checkItem(ItemStack p_151397_1_, ItemStack p_151397_2_)
    {
        return p_151397_2_.getItem() == p_151397_1_.getItem() && (p_151397_2_.getItemDamage() == 32767 || p_151397_2_.getItemDamage() == p_151397_1_.getItemDamage());
    }

	@Override
    public void drawExtras(int recipe)
    {
        drawProgressBar(51, 25, 176, 0, 14, 14, 48, 7);
        drawProgressBar(74, 23, 176, 14, 24, 16, 48, 0);
    }

	private static Set<Item> excludedFuels() {
        Set<Item> efuels = new HashSet<Item>();
        efuels.add(Item.getItemFromBlock(Blocks.brown_mushroom));
        efuels.add(Item.getItemFromBlock(Blocks.red_mushroom));
        efuels.add(Item.getItemFromBlock(Blocks.standing_sign));
        efuels.add(Item.getItemFromBlock(Blocks.wall_sign));
        efuels.add(Item.getItemFromBlock(Blocks.wooden_door));
        efuels.add(Item.getItemFromBlock(Blocks.trapped_chest));
        return efuels;
    }

    private void findFuels() {
        afuels = new ArrayList<FuelPair>();
        Set<Item> efuels = excludedFuels();
        for (ItemStack item : ItemList.items)
            if (!efuels.contains(item.getItem())) {
                int burnTime = getFuelTime(item);
                if (burnTime > 0)
                    afuels.add(new FuelPair(item.copy(), burnTime));
            }
    }

	abstract Class<? extends AbstractSimpleRecipeHandler> getHandlerClass();
	abstract String getHandlerName();
	abstract RecipeSimpleMachine getRecipe();
	abstract String getGuiRecipeName();
	public abstract Class<? extends GuiContainer> getGuiClass();
	abstract String getFuelHandlerName();
	abstract int getFuelTime(ItemStack item);

	@Override
	public String getRecipeName() {
		return I18n.format("nei."+getGuiRecipeName());
	}
}
