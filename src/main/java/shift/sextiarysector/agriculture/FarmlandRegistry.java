package shift.sextiarysector.agriculture;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import shift.sextiarysector.api.agriculture.IFarmlandRegistry;

public class FarmlandRegistry implements IFarmlandRegistry {

    public Map<String, Block> farmlands = new HashMap<String, Block>();

    @Override
    public void registerFarmland(String name, Block block) {

        if (farmlands.containsKey(name)) throw new IllegalArgumentException("This name is already registered : " + name);

        farmlands.put(name, block);

    }

    @Override
    public Block getFarmland(String name) {

        return farmlands.get(name);

    }

    public String getFarmlandName(Block block) {

        for (Entry<String, Block> f : farmlands.entrySet()) {

            if (f.getValue() == block) return f.getKey();

        }

        return null;

    }

}
