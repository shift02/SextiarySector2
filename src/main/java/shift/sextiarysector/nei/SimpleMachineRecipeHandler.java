package shift.sextiarysector.nei;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import shift.sextiarysector.gui.GuiSimpleMachine;
import shift.sextiarysector.recipe.RecipeSimpleMachine;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public abstract class SimpleMachineRecipeHandler  extends TemplateRecipeHandler{

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
            /*
            if(ingred.item.getItemDamage() == -1)
            {
                PositionedStack stack = ingred.copy();
                int maxDamage = 0;
                do
                {
                    maxDamage++;
                    stack.item.setItemDamage(maxDamage);
                }
                while(NEIClientUtils.isValidItem(stack.item));

                stack.item.setItemDamage(cycle % maxDamage);
                return stack;
            }*/

            this.randomRenderPermutation(ingred, cycleticks / 20);

            return ingred;
        }

        @Override
		public PositionedStack getResult()
        {
            return result;
        }

        /*
        @Override
		public PositionedStack getOtherStack()
        {
            return afuels.get((cycleticks/48) % afuels.size()).stack;
        }*/

        PositionedStack ingred;
        PositionedStack result;
    }

	public PositionedStack getResult() {
	    return null;
	  }

	@Override
	  public String getOverlayIdentifier() {
	    return getHandlerName();
	  }

	@Override
	public void loadTransferRects() {
	    transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(74, 23, 24, 18), getHandlerName()));
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
        //drawProgressBar(52, 25, 176, 0, 14, 14, 48, 7);
        drawProgressBar(74, 23, 176, 14, 24, 16, 48, 0);
    }

	abstract Class<? extends SimpleMachineRecipeHandler> getHandlerClass();
	abstract String getHandlerName();
	abstract RecipeSimpleMachine getRecipe();
	abstract String getGuiRecipeName();
	public abstract Class<? extends GuiSimpleMachine> getGuiClass();

	@Override
	public String getRecipeName() {
		return I18n.format("nei."+getGuiRecipeName());
	}


}
