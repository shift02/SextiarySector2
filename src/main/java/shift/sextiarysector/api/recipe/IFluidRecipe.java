package shift.sextiarysector.api.recipe;

import java.util.Map;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public interface IFluidRecipe {

    public void add(ItemStack par1ItemStack, ItemStack par2ItemStack, FluidStack par3FluidStack);

    public void add(String par1String, ItemStack par2ItemStack, FluidStack par3FluidStack);

    public ItemStack getResult(ItemStack item);

    public FluidStack getFluidResult(ItemStack item);

    public Map<ItemStack, Object[]> getMetaList();

    public Map<String, Object[]> getOreList();

}
