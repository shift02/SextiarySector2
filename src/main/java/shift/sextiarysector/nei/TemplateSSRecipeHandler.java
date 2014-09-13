package shift.sextiarysector.nei;

import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import codechicken.nei.guihook.GuiContainerManager;
import codechicken.nei.guihook.IContainerInputHandler;
import codechicken.nei.guihook.IContainerTooltipHandler;
import codechicken.nei.recipe.GuiRecipe;
import codechicken.nei.recipe.TemplateRecipeHandler;

public abstract class TemplateSSRecipeHandler extends TemplateRecipeHandler{

	//GUIから飛ぶ時のツールチップ
	public static class RecipeSSTransferRectHandler extends RecipeTransferRectHandler implements IContainerInputHandler, IContainerTooltipHandler
    {

		@Override
        public List<String> handleTooltip(GuiContainer gui, int mousex, int mousey, List<String> currenttip) {

			List<String> s =super.handleTooltip(gui, mousex, mousey, currenttip);

			return formatRecipe(s);

        }

    }

	static {
        GuiContainerManager.addInputHandler(new RecipeSSTransferRectHandler());
        GuiContainerManager.addTooltipHandler(new RecipeSSTransferRectHandler());
    }

	public TemplateSSRecipeHandler() {
        loadTransferRects();
        RecipeSSTransferRectHandler.registerRectsToGuis(getRecipeTransferRectGuis(), transferRects);
    }

	//Recipe内のツールチップ
	@Override
    public List<String> handleTooltip(GuiRecipe gui, List<String> currenttip, int recipe) {

		List<String> s =super.handleTooltip(gui, currenttip, recipe);

		return formatRecipe(s);

    }

	//変換処理 Langファイルで"nei.recipe=レシピ"と書けば日本語化されます。
	private static List<String> formatRecipe(List<String> currenttip){

		for(int i =0;i<currenttip.size();i++){

			String r = currenttip.get(i);

			if(r.equals("Recipes")){
				currenttip.set(i, I18n.format("nei.recipe"));
			}

		}

		return currenttip;

	}

}
