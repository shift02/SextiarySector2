package shift.sextiarysector.agriculture;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import shift.sextiarysector.api.agriculture.ICrop;
import shift.sextiarysector.api.agriculture.ICropManager;

public class CropManager implements ICropManager {

    public Map<String, ICrop> crops = new HashMap<String, ICrop>();

    @Override
    public void registerCrop(ICrop crop) {

        if (crops.containsKey(crop.getName())) throw new IllegalArgumentException("This name is already registered : " + crop.getName());

        crops.put(crop.getName(), crop);

    }

    public ICrop getCrop(ItemStack item, EntityPlayer player) {

        for (ICrop c : crops.values()) {

            if (c.isSeed(item, player)) return c;

        }

        return null;

    }

    public ICrop getCrop(String name) {

        for (ICrop c : crops.values()) {

            if (c.getName().equals(name)) return c;

        }

        return null;

    }

}
