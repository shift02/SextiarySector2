package shift.sextiarysector.nei;

import static codechicken.lib.gui.GuiDraw.*;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.lwjgl.opengl.GL11;

import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import shift.sextiarysector.recipe.RecipeSimpleFluid;

public abstract class SimpleFluidRecipeHandler extends TemplateSSRecipeHandler {

    public class SimpleFluidPair extends CachedRecipe {
        public SimpleFluidPair(ItemStack ingred, ItemStack result, FluidStack fluidStack) {
            ingred.stackSize = 1;
            this.ingred = new PositionedStack(ingred, 51, 6);
            if (result != null) this.result = new PositionedStack(result, 111 - 9, 24 + 18);
            if (ingred.getItem().getContainerItem(ingred.copy()) != null) {
                this.result2 = new PositionedStack(ingred.getItem().getContainerItem(ingred.copy()), 111 - 9 + 18, 24 + 18);
            }

            this.fluidStack = fluidStack;
        }

        public SimpleFluidPair(List<ItemStack> ingred, ItemStack result, FluidStack fluidStack) {
            for (int i = 0; i < ingred.size(); i++) {
                ingred.get(i).stackSize = 1;
            }
            //ingred.stackSize = 1;
            this.ingred = new PositionedStack(ingred, 51, 6);
            if (result != null) this.result = new PositionedStack(result, 111 - 9, 24 + 18);
            for (ItemStack item : ingred) {
                if (item.getItem().getContainerItem(item.copy()) != null) {
                    this.result2 = new PositionedStack(item.getItem().getContainerItem(item.copy()), 111 - 9 + 18, 24 + 18);
                    break;
                }
            }
            this.fluidStack = fluidStack;
        }

        @Override
        public PositionedStack getIngredient() {
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
        public PositionedStack getResult() {
            return result;
        }

        @Override
        public PositionedStack getOtherStack() {
            return result2;
        }

        /*
        @Override
        public PositionedStack getOtherStack()
        {
            return afuels.get((cycleticks/48) % afuels.size()).stack;
        }*/

        PositionedStack ingred;
        PositionedStack result;
        PositionedStack result2;
        FluidStack fluidStack;
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
    public void loadCraftingRecipes(String outputId, Object... results) {
        if (outputId.equals(getHandlerName()) && getClass() == getHandlerClass()) //don't want subclasses getting a hold of this
        {
            HashMap<String, Object[]> recipes = (HashMap<String, Object[]>) getRecipe().getOreList();
            HashMap<ItemStack, Object[]> metarecipes = (HashMap<ItemStack, Object[]>) getRecipe().getMetaList();

            for (Entry<String, Object[]> recipe : recipes.entrySet()) {
                ItemStack item = (ItemStack) recipe.getValue()[0];
                if (OreDictionary.getOres(recipe.getKey()).size() >= 1) {
                    arecipes.add(new SimpleFluidPair(OreDictionary.getOres(recipe.getKey()), item, (FluidStack) recipe.getValue()[1]));
                }

            }

            if (metarecipes == null) return;
            for (Entry<ItemStack, Object[]> recipe : metarecipes.entrySet()) {
                ItemStack item = (ItemStack) recipe.getValue()[0];
                arecipes.add(new SimpleFluidPair(recipe.getKey(), item, (FluidStack) recipe.getValue()[1]));
            }
        } else {
            super.loadCraftingRecipes(outputId, results);
        }
    }

    @Override
    public void loadCraftingRecipes(ItemStack result) {

        HashMap<String, Object[]> recipes = (HashMap<String, Object[]>) getRecipe().getOreList();
        HashMap<ItemStack, Object[]> metarecipes = (HashMap<ItemStack, Object[]>) getRecipe().getMetaList();

        for (Entry<String, Object[]> recipe : recipes.entrySet()) {
            ItemStack item = (ItemStack) recipe.getValue()[0];
            if (NEIServerUtils.areStacksSameType(item, result) || this.isResult(result, (FluidStack) recipe.getValue()[1])) {
                if (OreDictionary.getOres(recipe.getKey()).size() >= 1) {
                    arecipes.add(new SimpleFluidPair(OreDictionary.getOres(recipe.getKey()), item, (FluidStack) recipe.getValue()[1]));
                }

            }
        }

        if (metarecipes == null) return;
        for (Entry<ItemStack, Object[]> recipe : metarecipes.entrySet()) {
            ItemStack item = (ItemStack) recipe.getValue()[0];
            if (NEIServerUtils.areStacksSameType(item, result) || this.isResult(result, (FluidStack) recipe.getValue()[1])) {
                arecipes.add(new SimpleFluidPair(recipe.getKey(), item, (FluidStack) recipe.getValue()[1]));
            }
        }
    }

    private boolean isResult(ItemStack result, FluidStack f1) {

        FluidStack f = FluidContainerRegistry.getFluidForFilledItem(result);

        if (f == null || f1 == null) return false;

        if (f.isFluidEqual(f1)) return true;

        return false;

    }

    @Override
    public void loadUsageRecipes(ItemStack ingredient) {

        HashMap<String, Object[]> recipes = (HashMap<String, Object[]>) getRecipe().getOreList();
        HashMap<ItemStack, Object[]> metarecipes = (HashMap<ItemStack, Object[]>) getRecipe().getMetaList();

        /*
        for (Entry<String, Object[]> recipe : recipes.entrySet())
        {
        	ItemStack item = (ItemStack) recipe.getValue()[0];
        	ArrayList<ItemStack> items = OreDictionary.getOres(recipe.getKey());
        	for (int i = 0; i < items.size(); i++) {
        		if (this.checkItem(items.get(i), ingredient)) {
        			arecipes.add(new SimpleFluidPair(items, item, (FluidStack) recipe.getValue()[1]));
        			break;
        		}
        	}
        }*/

        for (String key : recipes.keySet()) {
            ArrayList<ItemStack> items = OreDictionary.getOres(key);
            for (int i = 0; i < items.size(); i++) {
                if (this.checkItem(ingredient, items.get(i))) {
                    arecipes.add(new SimpleFluidPair(items, (ItemStack) recipes.get(key)[0], (FluidStack) recipes.get(key)[1]));
                    break;
                }
            }
        }

        if (metarecipes == null) return;
        for (Entry<ItemStack, Object[]> recipe : metarecipes.entrySet()) {
            ItemStack item = (ItemStack) recipe.getValue()[0];
            //if(ingredient.itemID == recipe.getKey().get(0) && ingredient.getItemDamage() == recipe.getKey().get(1))
            if (this.checkItem(ingredient, recipe.getKey())) {
                arecipes.add(new SimpleFluidPair(ingredient, item, (FluidStack) recipe.getValue()[1]));
            }
        }
    }

    private boolean checkItem(ItemStack p_151397_1_, ItemStack p_151397_2_) {
        return p_151397_2_.getItem() == p_151397_1_.getItem() && (p_151397_2_.getItemDamage() == OreDictionary.WILDCARD_VALUE || p_151397_2_.getItemDamage() == p_151397_1_.getItemDamage());
    }

    @Override
    public void drawExtras(int recipe) {
        drawProgressBar(52, 25, 176, 0, 14, 14, 48, 7);
        drawProgressBar(74, 23, 176, 14, 24, 16, 48, 0);

        if (((SimpleFluidPair) this.arecipes.get(recipe)).fluidStack != null) {
            this.drawFluidTank(102, 4, ((SimpleFluidPair) this.arecipes.get(recipe)).fluidStack, 34, 36);
        }

        changeTexture(getGuiTexture());
        drawTexturedModalRect(103, 6, 176, 31, 32, 36);

    }

    public void drawFluidTank(int x, int y, FluidStack fluidStack, int width, int height) {

        ResourceLocation r;
        if (fluidStack.getFluid().getSpriteNumber() == 0) {
            r = TextureMap.locationBlocksTexture;
        } else {
            r = TextureMap.locationItemsTexture;
        }

        renderEngine.bindTexture(r);
        this.setColor3ubFromInt(fluidStack.getFluid().getColor(fluidStack));

        int widthR = width;//*(fluidTank.getFluidAmount()/fluidTank.getCapacity());
        int heightR = (int) (width * ((float) fluidStack.amount / (float) this.getMaxFluid()));

        int yR = y + (height - heightR);

        int widthL = 0;
        int heightL = 0;
        IIcon icon = (fluidStack.getFluid().getIcon(fluidStack));

        for (int i = 0; i < widthR; i += 16) {

            for (int j = 0; j < heightR; j += 16) {

                widthL = Math.min(widthR - i, 16);
                heightL = Math.min(heightR - j, 16);
                gui.drawTexturedModelRectFromIcon(x + i, yR + j, icon, widthL, heightL);

            }
        }

        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0F);

    }

    public static void setColor3ubFromInt(int color) {

        GL11.glColor3ub((byte) (color >> 16 & 0xFF), (byte) (color >> 8 & 0xFF), (byte) (color & 0xFF));

    }

    @Override
    public List<String> drawTooltip(int recipe, int offsetx, int offsety, List<String> currenttip) {

        if (((SimpleFluidPair) this.arecipes.get(recipe)).fluidStack == null) return currenttip;

        if (42 < offsety && offsety < 77 && 226 < offsetx && offsetx < 261) {

            FluidStack f = ((SimpleFluidPair) this.arecipes.get(recipe)).fluidStack;

            currenttip.add(f.getLocalizedName());
            currenttip.add(String.format("%1$,3d", f.amount) + " mB");

        }

        return currenttip;

    }

    abstract Class<? extends SimpleFluidRecipeHandler> getHandlerClass();

    abstract String getHandlerName();

    abstract RecipeSimpleFluid getRecipe();

    abstract String getGuiRecipeName();

    @Override
    public abstract Class<? extends GuiContainer> getGuiClass();

    abstract int getMaxFluid();

    @Override
    public String getRecipeName() {
        return I18n.format("nei." + getGuiRecipeName());
    }

}
