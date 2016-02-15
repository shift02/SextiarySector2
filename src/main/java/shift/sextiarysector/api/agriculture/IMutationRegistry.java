package shift.sextiarysector.api.agriculture;

import net.minecraft.item.ItemStack;

public interface IMutationRegistry {

    public void registeMutation(IMutation mutation);

    public ItemStack getMutationItem(IFertilizer fertilizer, ItemStack crop);

}
